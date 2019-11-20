/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import CapaDomini.ModelDomini.EstadistiquesAlg;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author lucas
 */
public class DriverEstadistiquesAlg {
    
        private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));

        public static void main(String[] args) throws IOException {
            int opcion;
        boolean salir=false;
        EstadistiquesAlg e = new EstadistiquesAlg();
        
        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. Ver num compressions."
                         + "\n\t2. Ver num descompressions."
                         + "\n\t3. Introducir datos"                    
                         + "\n\t4. Salir.");
            
            opcion = Integer.parseInt(intro.readLine());
            
            switch(opcion){
                case 1:
                    System.out.println(e.getNum_compressions());
                    break;
                case 2: 
                    System.out.println(e.getNum_descompressions());
                    break;
                case 3:
                    System.out.println("Introduce numero de compressiones:");
                    Integer ncomp = Integer.parseInt(intro.readLine());
                    e.setNum_compressions(ncomp);
                    System.out.println("Introduce el numero de descompressiones:");
                    Integer ndesc = Integer.parseInt(intro.readLine());
                    e.setNum_descompressions(ndesc);
                    System.out.println("Datos introducidos correctamente!");                    
                    break;
                case 4:
                    salir = true;
                    break;
                default: 
                    System.out.println("Introduce una opcion correcta");
                    break;
                  
            }            
            
        }
            
        }
}
