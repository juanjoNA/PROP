package Drivers;

import CapaDomini.ModelDomini.Arxiu;
import CapaDomini.ModelDomini.ArxiuTXT;
import CapaDomini.ModelDomini.Estadistiques;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Juanjo.Navarro
 */
public class DriverArxiuTXT {
    
    private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        
        
        System.out.println("Introdueix el path per crear un arxiu:");
        String path = intro.readLine();
        System.out.println("Introdueix el contingut d'aquest Arxiu:");
        String contingut = intro.readLine();
        ArxiuTXT a = new ArxiuTXT(path, contingut);
        System.out.println("Arxiu creat.");
        
        
        int opcion;
        boolean salir=false;
        String in;
        
        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. Cambiar contingut."
                         + "\n\t2. Obtenir contingut."
                         + "\n\t3. Salir.");
            
            try{
                opcion = Integer.parseInt(intro.readLine());
            }catch(NumberFormatException ne){
                opcion = 0;
            }

            switch(opcion) {
                case 1: 
                    System.out.println("Introdueix el contingut nou: ");
                    in = intro.readLine();
                    a.setContingut(in);
                    System.out.println("Contingut cambiat correctament");
                    break;
                
                case 2: 
                    System.out.println("Contingut: ");
                    System.out.println(a.getContingut());
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
