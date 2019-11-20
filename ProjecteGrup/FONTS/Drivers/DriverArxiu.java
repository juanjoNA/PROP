package Drivers;

import CapaDomini.ModelDomini.Arxiu;
import CapaDomini.ModelDomini.Estadistiques;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Juanjo.Navarro
 */
public class DriverArxiu {

   private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        
        
        System.out.println("Introdueix el path per crear un arxiu:");
        String path = intro.readLine();
        Arxiu a = new Arxiu(path);
        System.out.println("Arxiu creat.");
        
        
        int opcion;
        boolean salir=false;
        String in;
        
        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. Cambiar Path."
                         + "\n\t2. Cambiar Estadistiques."
                         + "\n\t3. Obtenir Path."
                         + "\n\t4. Obtenir Estadistiques."
                         + "\n\t5. Salir.");
            
            try{
                opcion = Integer.parseInt(intro.readLine());
            }catch(NumberFormatException ne){
                opcion = 0;
            }

            switch(opcion) {
                case 1: {
                    System.out.println("Introdueix el path nou: ");
                    in = intro.readLine();
                    a.setPath(in);
                    System.out.println("Path cambiat correctament");
                    break;
                }
                case 2: {
                    double vel, perc;
                    long temps;
                    do{
                        System.out.println("Introdueix la velocitat de compresio (positiva): ");
                        in = intro.readLine();
                    }while( in.equals("") || (vel = Double.parseDouble(in)) < 0);
                      
                    do{
                        System.out.println("Introdueix el temps d'execució (positiu): ");
                        in = intro.readLine();
                    }while( in.equals("") || (temps = Long.parseLong(in)) < 0);
                    
                    do{
                        System.out.println("Introdueix el percentatge de compresió (positiu): ");
                        in = intro.readLine();
                    }while( in.equals("") || (perc = Double.parseDouble(in)) < 0);
                    
                    Estadistiques e = new Estadistiques(vel, temps, perc);
                    a.setEstadistiques(e);
                    
                    System.out.println("Estadistiques cambiades correctament");
                    
                    break; 
                }
                
                case 3:
                    System.out.println("Path: "+a.getPath());
                    break;
                    
                case 4:
                    Estadistiques e = a.getEstadistiques();
                    System.out.println("Velocitat compressió: "+e.getVelocitat_compressio()+"\nTemps compressió: "+e.getTemps_compressio()+"\nPercentatge compresió: "+e.getPercentatge_compressio());
                    break;
                case 5: 
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
