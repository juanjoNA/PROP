/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.ModelDomini;

import Excepcions.CaracterNoASCII;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Lucas.Pinilla
 */
public class LZ78 extends LZ{
    
        private int unsignedToBytes(byte b) {
            return b & 0xFF;
        }
        public int byteToUnsignedInt(byte b) {
        return b & 0xFF;
    }
        
        private byte[] intToByteArray(int x) {
            byte b[] = new byte[2];
            b[0] = (byte)x;
            b[1] = (byte)((x>>8) & 0xFF);
            return b;
        }
        
        private int byteArrayToInt(byte[] bytes) {
            return (bytes[1] & 0xFF) << 8 | (bytes[0] & 0xFF);        
        }
        
    
        public ArxiuBytes comprimir(ArxiuTXT f) throws IOException, CaracterNoASCII {
            long start = System.currentTimeMillis();
            int pos = 0;
            int j = 1;
            char a;
            String ini = "";
            String act = "";
            byte posb = 0;
            byte x = 0;
            int flag = 0;
            int cb = 0;
            int[] fpos = new int[8];
            byte[] fx = new byte[8];
            byte[] posx = new byte[2];
            //ArxiuBytes  b = (ArxiuBytes) f;
            //byte[] data = b.getContingut();
            String data = f.getContingut();
            ByteArrayOutputStream res = new ByteArrayOutputStream();
            List<String> index = new ArrayList<String>(); //LISTA QUE CONTIENE RELACIÓN ENTRE POSICÓN I LA CLAVE DEL MAP
            HashMap<String, Integer> map = new HashMap<> ();
            String path = f.getPath();
            for(int i = 0; i < data.length();i++) {
                
                if(data.charAt(i)<0 || data.charAt(i)>255) throw new CaracterNoASCII();

                //x = (byte)unsignedToBytes(data[i]);
                //a = (char)unsignedToBytes(x); //LEEMOS CARACTER A CARACTER
                a = data.charAt(i);
                posb = (byte)pos;
                act = ini + a;
                if(map.containsKey(act)) {
                    ini = act;
                    pos = map.get(act);
                } else {
                    if(index.size() > j && index.get(j-1) != null) { //METODO PARA BORRAR ELEMENTOS DEL MAP EN CASO DE QUE SE LLENE
                        map.remove(index.get(j-1));
                        index.remove(j-1);
                    }
                    map.put(act,j);
                    index.add(j-1,act);
                    j++; 

                    fpos[cb] = pos; //VECTOR QUE ALMACENA LOS VALORES DE LOS CARACTERES
                    flag = (flag << 1);
                    if(pos > 255) flag = flag | 0x01; //CALCULO DEL BYTE DE FLAG
                    //flag = (flag << 1);
                    fx[cb] = (byte)a; //VECTOR QUE ALMACENA LOS VALORES DE LOS CARACTERES
                    cb++;
                    if(cb > 7) { //CUANDO EL BYTE FLAG ESTA LLENO SE ESCRIBE EL CONTENIDO DE LOS VECTORES
                        res.write((byte)flag);
                        for(int k = 0; k < 8; k++) {
                            if(fpos[k] > 0xFF) { //SI LA POSICION NO ENTRA EN UN BYTE SE SEPARA EN DOS
                                posx = intToByteArray(fpos[k]);
                                res.write(posx[0]);
                                res.write(posx[1]);
                            }else res.write((byte)fpos[k]);
                            res.write(fx[k]);
                        }
                        cb = 0;
                        flag = 0x00; //RESETEAMOS VARIABLES DEL FLAG
                    }
                    ini = "";
                    pos = 0;
                }
                if(j == 0xFFFF) j = 1; //SI MAP LLEGA A SU LIMITE SE SOBREESCRIBE
            }
            if(cb != 0) {
                flag = flag << (8-cb);
                res.write((byte)flag);
                for(int k = 0; k < cb; k++) {
                    if(fpos[k] > 0xFF) { //SI LA POSICION NO ENTRA EN UN BYTE SE SEPARA EN DOS
                        posx = intToByteArray(fpos[k]);
                        res.write(posx[0]);
                        res.write(posx[1]);
                    }else res.write((byte)fpos[k]);
                    res.write(fx[k]);
                }
                //res.write(posb);
                //res.write(x);
            } //AL ACABAR ITERWACIÓN SE ESCRIBE EL RESULTADO PENDIENTE
            String npath = f.getPath();
            npath = npath.replace(".txt",".lz78"); 
            byte[] result = res.toByteArray();
            ArxiuBytes comp = new ArxiuBytes(npath,result);
            long end = System.currentTimeMillis();
            Estadistiques e = new Estadistiques(start,end,f.getContingut().length(), result.length);
            comp.setEstadistiques(e);
            return comp;
        }
        
        public ArxiuTXT descomprimir(ArxiuBytes f) throws FileNotFoundException, IOException { //falla a descomprimir un numero
            long start = System.currentTimeMillis();
            int pos = 1;
            int j = 1;
            char a;
            int ca = 0;
            int cb = 0;
            int flag = 0;
            byte posb = 1;
            byte[] posx = new byte[2];
            String act = "";
            String result = "";
            StringBuilder sb = new StringBuilder();
            HashMap<Integer, String> map = new HashMap<Integer, String> ();
            ArxiuBytes  b = (ArxiuBytes) f;
            byte[] data = b.getContingut();
            for(int i = 0; i < data.length-1;i+=2) {
                if(cb == 0) {
                    flag = unsignedToBytes(data[i]); //SE LEE EL BYTE DE FLAG QUE INDICA EL NUMERO DE BYTES QUE OCUPA LA POSICIÓN DE LOS PROXIMOS 8 PAREJAS
                    i++;
                }
                pos = unsignedToBytes(data[i]);
                if(((flag >> 7) & 0x01) == 1) { //SE MIRA SI EL BIT CORRESPONDIENTE DEL FLAG ES 1 PARA LEER DOS BYTES
                    posx[0] = data[i];
                    posx[1] = (data[i+1]);
                    pos = byteArrayToInt(posx);
                    i++;
                }
                flag = (flag << 1); //SE PREPARA FLAG PARA LA PROXIMA ITERACIÓN
                cb++;
                if(cb == 8) cb = 0; //CONTADOR, CUANDO ACABA SE VUELVE A LEER FLAG
                //ca = unsignedToBytes(data[i+1]);
                a = (char)byteToUnsignedInt(data[i+1]); //SE LEE EL CARACTER EN CODIFICACIÓN POSITIVA
                //a = (char)data[i+1];
                act = "";
                if(pos == 0) {
                    sb.append(a); //SE ESCRIBE A AL RESULTAD0
                    act = act + a; 
                    map.put(j,act);
                }else {
                    act = map.get(pos) + a; //CALCULO DE LA SEQUENCIA, SEQUENCIA DEL MAP+CHARACTER
                    map.put(j,act);
                    sb.append(act);
                } j++;
                if(j == 0xFFFF) j = 1; //SI MAP SE PASA DE TAMAÑO SE VUELVE POS A 1 PARA SOBRESCRIBIRLO
            }
            long end = System.currentTimeMillis();
            String npath = f.getPath();
            npath = npath.replace(".lz78","(2).txt"); //SE CANVIA EXTENSION DEL ARXIVO
            ArxiuTXT desc = new ArxiuTXT(npath,sb.toString());
            Estadistiques e = new Estadistiques(start,end,f.getContingut().length,sb.toString().getBytes().length);
            desc.setEstadistiques(e);
            return desc;
        }
}
