/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import CapaDomini.ModelDomini.Imatge;
import Excepcions.ExtensionIncorrecta;
import Excepcions.VersionPPMIncorrecta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author lucas
 */
public class DriverImatge {

    private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException, VersionPPMIncorrecta, ExtensionIncorrecta{
        
        
        
        
        int opcion;
        boolean salir=false;
        String in;
        Imatge i = null;
        
        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. Crear imatge."
                         + "\n\t2. Mostrar informació imatge."
                         + "\n\t3. Salir.");
            
            try{
                opcion = Integer.parseInt(intro.readLine());
            }catch(NumberFormatException ne){
                opcion = 0;
            }

            switch(opcion) {
                case 1: 
                    System.out.println("Introdueix el path per crear una imatge:");
                    String path = intro.readLine();
                    System.out.println("Introdueix el contingut d'aquesta Imatge:");
                    byte[] contingut = intro.readLine().getBytes();  
                    System.out.println("Vols introduir una imatge amb mida \n S/N: ");
                    String mida = intro.readLine();                    
                    if(mida.equals("S")) {
                    System.out.println("Introdueix la versio: ");
                    String version = intro.readLine();
                    System.out.println("Introdueix la mida horitzontal: ");
                    Integer midah = Integer.parseInt(intro.readLine());
                    System.out.println("Introdueix la mida vertical: ");
                    Integer midav = Integer.parseInt(intro.readLine());
                    i = new Imatge(path,contingut,version,midah,midav,100);
                    }else i = new Imatge(path, contingut);
                    System.out.println("Imatge creada correctament");
                    break;
                case 2: 
                    if(i == null) {
                        System.out.println("No existeix imatge!");
                        break;
                    }
                    System.out.println("Dades de la imatge: ");
                    System.out.println("versio: ");
                    System.out.print(i.getVersion());
                    System.out.println("Mida horitzontal: ");
                    System.out.print(i.getSizeH());
                    System.out.println("Mida vertical: ");
                    System.out.print(i.getSizeV());                    
                    System.out.println("Header: ");
                    System.out.print(i.getHeader());
                    System.out.println("Mida: ");
                    System.out.print(i.getMida());
                    break;
                    
                case 3: 
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
