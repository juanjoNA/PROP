package CapaDomini.ModelDomini;

import Excepcions.CaracterNoASCII;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Juanjo.Navarro
 */
public class LZSS extends LZ{
    
// ----------------------------- VARIABLES ---------------------------------------
    
    //Variables Globals
    public static int WINDOW_SIZE = 4096;
    public static int MIN_COINCIDENCIA = 3;
    public static int MAX_LENGHT = 16;
    public static EstadistiquesAlg E_ALG;
    
    //key = char, values = array de next positions
    private HashMap<Character,ArrayList<Integer>> diccionario;                      //Diccionario donde guardaremos el caracter y las posiciones de los siguientes caracteres donde aparece.
    private char sinCodificar[] = new char[MAX_LENGHT];                             //Array que contendra los caracteres para codificar
    private char slidingWindow[] = new char[WINDOW_SIZE];                           //Ventana deslizante donde guardaremos los caracteres codificados
    
   // --------------------------- CONSTRUCTORS -------------------------------
    
    public LZSS(){
        E_ALG = new EstadistiquesAlg();
    }
    
// ----------------------------- METODES ----------------------------------
    
     /**
     * Funcion para comprimir un archivo txt en un archivo en bytes
     * @param a
     * @throws IOException
     * @throws CaracterNoASCII
     * @return archivo comprimido(ArxiuBytes)
     */
    public ArxiuBytes comprimir(ArxiuTXT a) throws IOException, CaracterNoASCII{
        
        long start = System.currentTimeMillis();
        String contenido = a.getContingut();
        int len;                                                        //len = lenght palabra leida
        int posCont;                                                    //Posicion del contenido leido
        int i;                                                          //Variable para los bucles
        Pair<Integer,Integer> p;                            //Par que contiene posicion, desplazamiento
        int windowPos;                                                  //Puntero a posicion del slidingWindow para buscar coincidencias
        int codificarPos;                                               //Puntero a vector sinCodificar para buscar coincidencias
        byte flag = 0x00000000;                                         //Flag para saber si es caracter o desplazamiento
        int contFlag=0;                                                 //Contador de flag para saber cuando escribir (cada 8 flags)
        ByteArrayOutputStream escribir = new ByteArrayOutputStream();   //Array donde guardaremos lo que vamos a escribir
        ByteArrayOutputStream salida = new ByteArrayOutputStream();     //ByteArray de la salida comprimida
               
        if(contenido.equals("")){
            long end = System.currentTimeMillis();
            Estadistiques e = new Estadistiques(start,end,a.getContingut().getBytes().length, salida.toByteArray().length);
            return new ArxiuBytes(cambiarPath(a.getPath(), ".lzss"), contenido.getBytes(), e);
        }
        inicializarEstructuras();
        
        
        //llenamos el vector sin codificar
        for(i=0; i<MAX_LENGHT && i<contenido.length(); i++){
            if(contenido.charAt(i)<0 || contenido.charAt(i)>255) throw new CaracterNoASCII();
            sinCodificar[i]=contenido.charAt(i);
        }
        
        len = posCont = i;                      //El len y la posicion del contenido la ponemos en i, que son los caracteres leidos
        windowPos = codificarPos = 0;           //Las posiciones de los vectores las ponemos a 0
        
        //Buscamos las primeras coincidencias
        p = buscarCoincidencia(codificarPos);
        
        while(len>0){
            
            if(p.getSecond()>len) p.setSecond(len);
            
            //Si p.getValue < MIN_COINCIDENCIA, no se codifica
            //else codificamos posicion+desplazamiento
            if(p.getSecond()<MIN_COINCIDENCIA){
                
                flag = (byte) (flag << 1);                      //desplazamos los flags y ponemos un 0
                contFlag++;
                escribir.write(byteToUnsignedInt((byte) sinCodificar[codificarPos]));
                p = new Pair(p.getFirst(), 1);
                
            }else{
                
                flag = (byte) ((flag << 1) | 0x00000001);   //desplazamos 1 bit el flag y le metemos un 1
                contFlag++;
                escribirPosMasLen(escribir, p.getFirst(), p.getSecond());
                
            }
            
            i=0;
            //Leemos tantos caracteres como hemos guardado previamente para mantener el len siempre 
            while(i<p.getSecond() && posCont<contenido.length()){
                
                //Añadimos el contenido que hemos guardado al Sliding i cambiamos esos caracteres por los leidos
                AniadirContenido(windowPos, sinCodificar[codificarPos]);
                if(contenido.charAt(posCont)<0 && contenido.charAt(posCont)>255) throw new CaracterNoASCII();
                sinCodificar[codificarPos] = contenido.charAt(posCont);
                
                //Aumentamos todos los contadores
                posCont++;
                windowPos = controlarLimits(windowPos, WINDOW_SIZE);
                codificarPos = controlarLimits(codificarPos, MAX_LENGHT);
                i++;
            }
            
            //si hemos llegado al final del fichero acabamos de leer el vector sinCodificar
            while(i<p.getSecond()){
                AniadirContenido(windowPos, sinCodificar[codificarPos]);
                codificarPos = controlarLimits(codificarPos, MAX_LENGHT);
                windowPos = controlarLimits(windowPos, WINDOW_SIZE);
                i++;
                len--;
            }
            
            if(contFlag >= 8 || len==0){
                if(contFlag!=8) flag = (byte) (flag << (8-contFlag));
                salida.write(flag);
                salida.write(escribir.toByteArray());
                escribir.reset();
                flag = 0x00000000;
                contFlag = 0;
            }
            
            if(len>0) p = buscarCoincidencia(codificarPos);
            if( ( (windowPos>p.getFirst()) && (windowPos < (p.getFirst()+p.getSecond())) ) && p.getSecond()>=MIN_COINCIDENCIA){
                p.setSecond(windowPos-p.getFirst());
            }
        }
        //Calculamos las estadisticas y las actualizamos
        long end = System.currentTimeMillis();
        Estadistiques e = new Estadistiques(start,end,a.getContingut().getBytes().length, salida.toByteArray().length);
        modificarMitjanaEstadistiques(e);
        return new ArxiuBytes(cambiarPath(a.getPath(), ".lzss"), salida.toByteArray(), e);
    }
    
     /**
     * Funcion para descomprimir un archivo txt en un archivo en bytes
     * @param a
     * @return archivo descomprimido(ArxiuTXT)
     */
    public ArxiuTXT descomprimir(ArxiuBytes a){
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("");   //Creador de l'String del contenido de salida
        byte flag;                                  //byte que guardara los flags
        int i=0;                                    //posicion lectura contenido y escritura de caracteres
        int posFlag = 7;                            //posicion del flag
        char caracter;
        Pair<Integer,Integer> p;
        int contSlid=0;
        int len, pos;
        byte[] contenido = a.getContingut();
        
        inicializarEstructuras();
        E_ALG.aumentaDescompressions();
        
        if(contenido.length<=0) {
            long end = System.currentTimeMillis();
            Estadistiques e = new Estadistiques(start,end,contenido.length, 0);
            return new ArxiuTXT(cambiarPath(a.getPath(), "_desc.txt"), sb.toString(),e);
        }
        
        flag = contenido[i];
        i++;
        
        while(i<contenido.length){
                    
            int bandera = (flag >> posFlag) & 1;
            
            //leemos un caracter normal
            if(bandera==0){
                caracter = (char) byteToUnsignedInt(contenido[i]);
                sb.append(caracter);
                slidingWindow[contSlid] = (char) byteToUnsignedInt(contenido[i]);
                contSlid = controlarLimits(contSlid, WINDOW_SIZE);
                i++;
            }else{  //leemos posicion y desplazamiento
                p = leerPosMasLen(contenido[i], contenido[i+1]);
                len = p.getSecond();
                pos = p.getFirst();
                for(int j = 0; j<len; j++){
                    sb.append(slidingWindow[pos]);
                    slidingWindow[contSlid] = slidingWindow[pos];
                    contSlid = controlarLimits(contSlid, WINDOW_SIZE);
                    pos = controlarLimits(pos, WINDOW_SIZE);
                }
                i += 2;
            }
            posFlag--;
            
             
            if(posFlag<0 && i<contenido.length){
                if(i<contenido.length) flag = contenido[i];
                posFlag=7;
                i++;
            }
        }
        long end = System.currentTimeMillis();
        String result = sb.toString();
        Estadistiques e = new Estadistiques(start,end,contenido.length, result.getBytes().length);
        return new ArxiuTXT(cambiarPath(a.getPath(), "_desc.txt"), result,e);
    }
    

    private Pair<Integer,Integer> buscarCoincidencia(int posC){
        
        int len=0;
        int posSliding=-1;
        ArrayList<Integer> posiciones;
        
        if(diccionario.containsKey(sinCodificar[posC])){
            posiciones = diccionario.get(sinCodificar[posC]);
            posC = controlarLimits(posC, MAX_LENGHT);
            int tamany;
            for(Integer pos : posiciones){
                tamany=1;
                //Si coincide, miramos cuantos caracteres se repiten
                if(slidingWindow[pos] == sinCodificar[posC]){
                    int s=posC;
                    int w=pos;
                    //Mientras el tamaño sea menor que el permitido
                    while(tamany<MAX_LENGHT-1){
                        //Vamos comprobando que la cadena del Sliding sea igual que el de la cadena sin codificar
                        if(slidingWindow[w] == sinCodificar[s]) tamany++;
                        else break;
                        w = controlarLimits(w, WINDOW_SIZE);
                        s = controlarLimits(s, MAX_LENGHT);
                    }
                    if(tamany > len){
                        len = tamany;           //Agafem el tamany mes gran
                        posSliding = pos-1;       //Guardem la posicio aquesta que te el tamany mes gran
                    }          
                }
            }
        }
        return new Pair(posSliding, len);
    }
    
    private void AniadirContenido(int posSliding, char c){
            
        Integer posicion = controlarLimits(posSliding, WINDOW_SIZE);
         
        if(slidingWindow[posSliding] != WINDOW_SIZE+1){
            char caracter = slidingWindow[posSliding];
            diccionario.get(caracter).remove(posicion);
        }
        
        slidingWindow[posSliding] = c;
        
        if(diccionario.containsKey(c)) diccionario.get(c).add(posicion);
        else{
           diccionario.put(c, new ArrayList<>());
           diccionario.get(c).add(posicion);
        }
    }
    
    private void inicializarEstructuras(){
        
        diccionario = new HashMap<>();
        for(int i=0; i<WINDOW_SIZE; i++){
            slidingWindow[i] = (char) (WINDOW_SIZE+1);
        }
        sinCodificar = new char[MAX_LENGHT];
    }
    
    private int controlarLimits(int num, int limit){
        num++;
        if(num<limit) return num;
        else return num%limit;
    }
    
    private static void escribirPosMasLen(ByteArrayOutputStream array, int pos, int len){
        
        byte b1 = 0;
        byte b2 = 0;
        int i;
        
        for(i=1; i<MAX_LENGHT; i*=2){
            b2 = (byte) ( b2 | (pos&i) );
        }
        b2 = (byte) (b2 << 4);
        
        for(i=8; i>0; i/=2){
            b2 = (byte) ( b2 | (len&i) );
        }
        
        pos= pos >> 4;
        
        b1=(byte) pos;
        
        array.write(b2);
        array.write(b1);
    }
    
    private Pair<Integer,Integer> leerPosMasLen(byte b2, byte b1){
        int pos=0;
        int len=0;
        int i;
        
        for(i=1; i<MAX_LENGHT; i*=2){
            len = len | (b2 & i);
        }
        b2 = (byte) ( 0x0F & (b2 >> 4));
        
        for(i=1; i<WINDOW_SIZE; i*=2){
            pos = pos | (b2 & i);
            if(i>=MAX_LENGHT && (b1 & (i/16))!=0){
                pos += i ;
            }
        }
        return new Pair(pos, len);
    }
    
    private int byteToUnsignedInt(byte b){
        return b & 0xFF;
    }
    
    private void modificarMitjanaEstadistiques(Estadistiques e){
        int n = E_ALG.getNum_compressions()+1;
        
        double velM = E_ALG.getVelocitat_compressio();
        double tempsM = E_ALG.getTemps_compressio();
        double percM = E_ALG.getPercentatge_compressio();
        
        velM = (velM+e.getVelocitat_compressio())/n;
        tempsM = (tempsM+e.getTemps_compressio())/n;
        percM = (percM+e.getPercentatge_compressio())/n;
        
        //Actualitzem les estadistiques mitjanes
        E_ALG.setNum_compressions(n);
        E_ALG.setPercentatge_compressio(percM);
        E_ALG.setTemps_compressio(tempsM);
        E_ALG.setVelocitat_compressio(velM);
    }
    
    private String cambiarPath(String path, String ext){
        if(ext.equals(".lzss")) return path.replace(".txt", ext);
        else return path.replace(".lzss", ext);
    }
}
