/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;


import CapaPersistencia.IOArxius;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Juanjo.Navarro
 */
public class DriverIOArxius {

    private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        
        int opcion;
        IOArxius cd = new IOArxius();
        boolean salir=false;
        
        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. llegir arxiu binari."
                         + "\n\t2. llegir arxiu de text."
                         + "\n\t3. escriure arxiu binari."
                         + "\n\t4. escriure arxiu txt."
                         + "\n\t5. sortir");
            
            opcion = Integer.parseInt(intro.readLine());
            String path;
            IOArxius io = new IOArxius();
            byte[] contingutBytes;
            String contingutChars;
            switch(opcion){
                case 1:
                    System.out.println("Introdueix el path de l'arxiu a llegir: ");
                    path = intro.readLine();
                    io = new IOArxius();
                    byte[] cont = null;
                    
                    cont = io.llegeixArxiuBinari(path);
                    System.out.println("El contingut del ficher " + path + "es :");
                    for (int i = 0; i < cont.length;++i) System.out.print((char) cont[i]);
                    
                    break;
                case 2: 
                    System.out.println("Introdueix el path de l'arxiu a llegir: ");
                    path = intro.readLine();
                    io = new IOArxius();
                    contingutChars = io.llegeixArxiuTxt(path);
                    System.out.println("El contingut del ficher " + path + "es :");
                    System.out.print(contingutChars);
                    break;
                case 3: 
                    System.out.println("Introdueix el path on vols guardar el contingut: ");
                    path = intro.readLine();
                    System.out.println("Introdueix el contingut a escriure: ");
                    String contingutEntrat = intro.readLine();
                    contingutBytes = contingutEntrat.getBytes();
                    io.guardaArxiuBinari(path, contingutBytes,false);
                    break;
                case 4: 
                    System.out.println("Introdueix el path on vols guardar el contingut: ");
                    path = intro.readLine();
                    System.out.println("Introdueix el contingut a escriure: ");
                    contingutChars = intro.readLine();
                    io.guardaArxiuTXT(path, contingutChars,false);
                    break;
                case 5:
                    salir = true;
                    break;
                default: 
                    System.out.println("Introdueix opció valida");
                    break;
            }            
            
        }
        
        
    }
}
