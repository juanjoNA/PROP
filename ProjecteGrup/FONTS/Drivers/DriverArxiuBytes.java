/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import CapaDomini.ModelDomini.ArxiuBytes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ivgasa99
 */
public class DriverArxiuBytes {

    private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        
        
        System.out.println("Introdueix el path per crear un arxiu:");
        String path = intro.readLine();
        System.out.println("Introdueix el contingut d'aquest Arxiu:");
        byte[] contingut = intro.readLine().getBytes();
        ArxiuBytes a = new ArxiuBytes(path, contingut);
        System.out.println("Arxiu creat.");
        
        
        int opcion;
        boolean salir=false;
        String in;
        
        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. Cambiar contingut."
                         + "\n\t2. Mostrar contingut bytes."
                         + "\n\t3. Mostrar contingut String."
                         + "\n\t4. Salir.");
            
            try{
                opcion = Integer.parseInt(intro.readLine());
            }catch(NumberFormatException ne){
                opcion = 0;
            }

            switch(opcion) {
                case 1: 
                    System.out.println("Introdueix el contingut nou: ");
                    in = intro.readLine();
                    a.setContingut(in.getBytes());
                    System.out.println("Contingut cambiat correctament");
                    break;
                
                case 2: 
                    System.out.println("Contingut (bytes): ");
                    byte[] b = a.getContingut();
                    for(int i=0; i< b.length ; i++) {
                        System.out.print(b[i] +", ");
                    }
                    
                    break;
                
                case 3: 
                    System.out.println("Contingut (String): ");
                    String s = new String(a.getContingut());
                    System.out.println(s);
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
