/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import CapaDomini.ModelDomini.ZigZag;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author paumu
 */
public class DriverZigZag {
    
    private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));
     public static void main(String[] args) throws IOException {
            int opcion;
            boolean salir=false;
            int[][] mat = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
             };
            ZigZag z = new ZigZag(mat,0,0);
        while(!salir){
            System.out.println("Selecciona una opció: ");
             System.out.println("\n\t1. Comprovar final de matriu"
                        +  "\n\t2. NovaMatriu"
                         + "\n\t3. Obtenir matriu"
                         + "\n\t4. Escriure numero"
                         + "\n\t5. Llegir numero "
                         + "\n\t6. Salir.");
            
            opcion = Integer.parseInt(intro.readLine());
            switch(opcion){
                case 1:
                    System.out.println ("Final de matriu = " + z.isEnd());
                    break;
                case 2: 
                    System.out.println("Escriu una matriu de 8x8 de enters");
                    for (int i = 0; i < 8; ++i) {
                        for (int j = 0; j < 8; ++j) {
                            mat[i][j] = Integer.parseInt(intro.readLine());
                        }
                    }
                    System.out.println("La teva matriu es la seguent");
                    for (int i = 0; i < 8; ++i) {
                        for (int j = 0; j < 8; ++j) {
                            System.out.print(mat[i][j] + " ");
                        }
                        System.out.println();
                    }
                    z = new ZigZag(mat,0,0);
                    break;
                case 3: 
                    System.out.println("La matriu actual es : ");
                    int[][] obtemat = z.getMatrix();
                    for (int i = 0; i < 8; ++i) {
                        for (int j = 0; j < 8; ++j) {
                            System.out.print(obtemat[i][j] + " ");
                        }
                        System.out.println();
                    }
                    break;
                case 4: 
                    System.out.println("Introdueix un numero enter");
                    int n = Integer.parseInt(intro.readLine());
                    System.out.println(n);
                    z.writeNum(n);
                    break;
                 case 5: 
                    System.out.println("El numero actual es : " + z.getNextNumber());
                    break;
                case 6:
                    salir = true;
                    break;
                default: 
                    System.out.println("Introduce una opcion correcta");
                    break;
            }
                  
            }            
            
        }
}
