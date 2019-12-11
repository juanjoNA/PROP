/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;


import CapaDomini.Controladors.ControladorEstadisticas;
import CapaPersistencia.EstadisticasDisc;
import Excepcions.ExtensionIncorrecta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class DriverControladorEstadisticas {

    private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException, Exception {
        
        int opcion;
        boolean salir=false;
        EstadisticasDisc est = new EstadisticasDisc();
        
        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. escriure estadisticas compressió."
                         + "\n\t2. escriure estadisticas descompressió."
                         + "\n\t3. lleguir estadisticas."
                         + "\n\t4. Sortir.");
            
            opcion = Integer.parseInt(intro.readLine());
            String algoritmo;                    
            double estad[] = new double[3];
            switch(opcion){
                case 1:
                    System.out.println("Indica un algoritme: ");           
                    algoritmo = intro.readLine();
                    System.out.println("Introdueix temps de compressio:");
                    int temps = Integer.parseInt(intro.readLine());
                    System.out.println("Introdueix percentatge de compressio:");
                    int perc = Integer.parseInt(intro.readLine());
                    System.out.println("Introdueix velocitat de compressio:");
                    int vel = Integer.parseInt(intro.readLine());
                    //double estad[] = new double[3];
                    estad[0] = temps;
                    estad[1] = perc;
                    estad[2] = vel;
                    ControladorEstadisticas ce = new ControladorEstadisticas(estad,true,algoritmo);
                    ce.executar();
                    //est.writeEstCompressio(temps, perc, vel, algoritmo);
                    break;
                case 2: 
                    System.out.println("Indica un algoritme: ");           
                    algoritmo = intro.readLine();
                    System.out.println("Introdueix temps de compressio:");
                    int tempsdesc = Integer.parseInt(intro.readLine());
                    System.out.println("Introdueix percentatge de compressio:");
                    int percdes = Integer.parseInt(intro.readLine());
                    System.out.println("Introdueix velocitat de compressio:");
                    int veldes = Integer.parseInt(intro.readLine());
                    //double estad[] = new double[3];
                    estad[0] = tempsdesc;
                    estad[1] = percdes;
                    estad[2] = veldes;
                    ControladorEstadisticas ced = new ControladorEstadisticas(estad,false,algoritmo);
                    ced.executar(); 
                    //est.writeEstCompressio(tempsdesc, percdes, veldes, algoritmo);
                    break;
                case 3: 
                    System.out.println("Indica un algoritme: ");           
                    algoritmo = intro.readLine();
                    ControladorEstadisticas cer = new ControladorEstadisticas(algoritmo);
                    long res[] = cer.executar();
                            //est.readEstDisc(algoritmo);
                    System.out.println("Las estadisticas són: ");
                    System.out.print("temps de compressio: ");
                    System.out.println(res[0]);
                    System.out.print("percentatge de compressio: ");
                    System.out.println(res[1]);
                    System.out.print("velocitat de compressio: ");
                    System.out.println(res[2]);
                    System.out.print("temps de descompressio: ");
                    System.out.println(res[3]);
                    System.out.print("percentatge de descompressio: ");
                    System.out.println(res[4]);
                    System.out.print("velocitat de descompressio: ");
                    System.out.println(res[5]);
                    break;
                case 4:
                    salir = true;
                    break;
                default: 
                    System.out.println("Introdueix opció valida");
                    break;
            }            
            
        } 
    }
}
