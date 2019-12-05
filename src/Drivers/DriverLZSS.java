package Drivers;

import CapaDomini.ModelDomini.ArxiuBytes;
import CapaDomini.ModelDomini.ArxiuTXT;
import CapaDomini.ModelDomini.LZSS;
import CapaPersistencia.IOArxius;
import Excepcions.CaracterNoASCII;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanjo.Navarro
 */
public class DriverLZSS {
    
    private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
               
        IOArxius ioa = new IOArxius();
        int opcion;
        boolean salir=false;
        String in;
        LZSS lzss = new LZSS();
        ArxiuTXT normal, descomprimit;
        ArxiuBytes comprimit;
        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. Comprimir arxiu."
                         + "\n\t2. Descomprimir arxiu."
                         + "\n\t3. Mostrar resultat del proces complet."
                         + "\n\t4. Salir.");
            
            try{
                opcion = Integer.parseInt(intro.readLine());
            }catch(NumberFormatException ne){
                opcion = 0;
            }

            switch(opcion) {
                case 1: 
                    System.out.println("Intodueix el path del fitxer a COMPRIMIR:");
                    in = intro.readLine();
                    String contingut = ioa.llegeixArxiuTxt(in);
                    normal = new ArxiuTXT(in, contingut);
                    try{
                        comprimit = lzss.comprimir(normal);
                    }catch(CaracterNoASCII ex){
                        System.out.println("caracter no ascii al fitxer");
                        break;
                    }
                    System.out.println("Arxiu comprimit correctament: ");
                    System.out.println(comprimit.getContingut());
                    ioa.guardaArxiuBinari(comprimit.getPath(), comprimit.getContingut());
                    break;
                
                case 2: 
                    System.out.println("Intodueix el path del fitxer a DESCOMPRIMIR:");
                    in = intro.readLine();
                    byte[] contB;
                    
                    contB = ioa.llegeixArxiuBinari(in,".lzss");
                    comprimit = new ArxiuBytes(in, contB);
                    descomprimit = lzss.descomprimir(comprimit);
                    System.out.println("Arxiu descomprimit: ");
                    ioa.guardaArxiuTXT(descomprimit.getPath(), descomprimit.getContingut());
                
                    break;
                
                case 3: 
                    System.out.println("Intodueix el path del fitxer a COMPRIMIR i DESCOMPRIMIR:");
                    in = intro.readLine();
                    String cont = ioa.llegeixArxiuTxt(in);
                    normal = new ArxiuTXT(in, cont);
                    try{
                        comprimit = lzss.comprimir(normal);
                    }catch(CaracterNoASCII ex){
                        System.out.println("caracter no ascii al fitxer");
                        break;
                    }
                    descomprimit = lzss.descomprimir(comprimit);
                    System.out.println("------------------------ RESULTATS -------------------");
                    System.out.println("FITXER INTRODUIT");
                    System.out.println(normal.getContingut());
                    System.out.println("\nFITXER DESCOMPRIMIT");
                    System.out.println(descomprimit.getContingut());
                    System.out.print("\nSON IGUALS? : ");
                    if(descomprimit.getContingut().equals(normal.getContingut())) System.out.print("SI\n");
                    else System.out.print("NO\n");
                            
                    break;
                    
                case 4: 
                    salir = true;
                    break;
                    
                default: 
                    System.out.println("Introdueix una opció correcta");
                    break;
            }
            
            System.out.println();
            
        }
    }
    
}
