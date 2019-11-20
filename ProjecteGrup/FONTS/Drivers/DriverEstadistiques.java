/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import CapaDomini.ModelDomini.Estadistiques;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author lucas
 */
public class DriverEstadistiques {
    
        private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));

        public static void main(String[] args) throws IOException {
            int opcion;
        boolean salir=false;
        Estadistiques e = new Estadistiques();

        
        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. Ver velocidad de compression."
                         + "\n\t2. Ver tiempo de compression."
                         + "\n\t3. Ver porcentaje de compression."
                         + "\n\t4. Introducir estadisticas."
                         + "\n\t5. Salir.");
            
            opcion = Integer.parseInt(intro.readLine());
            //Estadistiques e = new Estadistiques();
            
            switch(opcion){
                case 1:
                    System.out.println(e.getVelocitat_compressio());
                    break;
                case 2: 
                    System.out.println(e.getTemps_compressio());

                    break;
                case 3: 
                    System.out.println(e.getPercentatge_compressio());
                    break;
                case 4: 
                    System.out.println("Introduce la velocidad de compression:");
                    Integer vel = Integer.parseInt(intro.readLine());
                    e.setVelocitat_compressio(vel);
                    System.out.println("Introduce el tiempo de compression:");
                    Integer tiempo = Integer.parseInt(intro.readLine());
                    e.setTemps_compressio(tiempo);
                    System.out.println("Introduce el porcentage de compression:");
                    Integer per = Integer.parseInt(intro.readLine());
                    e.setTemps_compressio(per);
                    System.out.println("Estadisticas introducidads correctamente!");
                    break;
                case 5: 
                    salir = true;
                    break;
                default: 
                    System.out.println("Introduce una opcion correcta");
                    break;
                  
            }            
            
        }
            
        }
}