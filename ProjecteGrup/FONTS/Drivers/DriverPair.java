/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import CapaDomini.ModelDomini.Pair;
import Excepcions.DatosIncorrectos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author lucas
 */
public class DriverPair {
    
        private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));

        public static void main(String[] args) throws IOException {
            int opcion;
        boolean salir=false;
        Pair p = null;
        
        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. Introducir pair."
                         + "\n\t2. Obtenir valors."
                         + "\n\t3. Salir.");
            
            opcion = Integer.parseInt(intro.readLine());
            
            switch(opcion){
                case 1:
                    System.out.println("Introdueix el primer valor: ");
                    Integer first = Integer.parseInt(intro.readLine());
                    System.out.println("Introdueix el primer valor: ");
                    Integer second = Integer.parseInt(intro.readLine());
                    p = new Pair(first,second);
                    break;
                case 2: 
                    if(p ==null) {
                        System.out.println("Pair vacio!");
                        break;
                    }
                    System.out.println(p.getFirst());
                    System.out.println(p.getSecond());
                    break;
                case 3:
                    salir = true;
                    break;
                default: 
                    System.out.println("Introduce una opcion correcta");
                    break;
                  
            }            
            
        }
            
        }
}
