/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import CapaDomini.Controladors.ControladorComprimir;
import CapaPersistencia.IOArxius;
import Excepcions.CaracterNoASCII;
import Excepcions.DatosIncorrectos;
import Excepcions.ExtensionIncorrecta;
import Excepcions.VersionPPMIncorrecta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ivgasa99
 */
public class DriverControladorComprimir {


    private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {

        int opcion;
        IOArxius cd = new IOArxius();
        boolean salir=false;

        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. Comprimir Arxiu."
                         + "\n\t2. Sortir");

            opcion = Integer.parseInt(intro.readLine());
            String path;
            int algoritmo;
            boolean guardar;

            switch(opcion){
                case 1:
                    System.out.println("Introdueix el path de l'arxiu: ");
                    path = intro.readLine();
                    System.out.println("Selecciona una opció: ");
                    System.out.println("\n\t1. JPEG."
                         + "\n\t2. LZW."
                         + "\n\t3. LZSS."
                         + "\n\t4. LZ78."
                         + "\n\t5. NO SELECCIONAT");
                    algoritmo = Integer.parseInt(intro.readLine());
                    if (algoritmo == 5) algoritmo = seleccionarAlgoritme(path);
                    System.out.println("Vols guardar arxiu a disc \n S/N: ");
                    String g = intro.readLine();
                    if (g.equals("S")) guardar=true;
                    else guardar = false;
                    ControladorComprimir cc = new ControladorComprimir(path,algoritmo,guardar);
                    try {
                        cc.executar();
                        double[] est = cc.getResult();
                        System.out.println("Temps d'execució: " + est[0] + "ms");
                        System.out.println("Percentatge de compressió: " + est[1]*100 + "%");
                        System.out.println("Velocitat de compressió: " + est[2] + "B/ms");
                    }
                    catch (VersionPPMIncorrecta e) {
                        System.out.println("Versió de PPM incorrecta.");
                    }
                    catch (ExtensionIncorrecta e) {
                        System.out.println("Extensió de la imatge incorrecte");
                    }
                    catch (DatosIncorrectos e) {
                        System.out.println("Les dades del fitxer son incorrectes");
                    }
                    catch (CaracterNoASCII e) {
                        System.out.println("Hi ha algun caracter no ascii al fitxer");
                    }
                    break;
                case 2:
                    salir = true;
                    break;
                default:
                    System.out.println("Introdueix una opció correcta");
                    break;

            }

        }

    }

    static public int seleccionarAlgoritme(String path) {
        if (path.contains(".ppm")) return 1;
        else return 2;
    }
}
