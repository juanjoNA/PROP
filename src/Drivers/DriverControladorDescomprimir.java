/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import CapaDomini.Controladors.ControladorDescomprimir;
import CapaPersistencia.IOArxius;
import Excepcions.DatosIncorrectos;
import Excepcions.VersionPPMIncorrecta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ivgasa99
 */
public class DriverControladorDescomprimir {
    private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {

        int opcion;
        IOArxius cd = new IOArxius();
        boolean salir=false;
/*
        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. Descomprimir Arxiu."
                         + "\n\t2. Sortir");

            opcion = Integer.parseInt(intro.readLine());
            String path;
            int algoritmo;
            boolean guardar;

            switch(opcion){
                case 1:
                    System.out.println("Introdueix el path de l'arxiu: ");
                    path = intro.readLine();
                    algoritmo = seleccionarAlgoritme(path);
                    System.out.println("Vols guardar arxiu a disc \n S/N: ");
                    String g = intro.readLine();
                    if (g.equals("S")) guardar=true;
                    else guardar = false;
                    ControladorDescomprimir cc = new ControladorDescomprimir(path,algoritmo,guardar);
                    try {
                        cc.executar();
                        double[] est = cc.getResult();
                        System.out.println("Temps d'execució: " + est[0] + "ms");
                        System.out.println("Percentatge de descompressió: " + est[1]*100 + "%");
                        System.out.println("Velocitat de descompressió: " + est[2] + "B/ms");
                    }
                    catch (VersionPPMIncorrecta e) {
                        System.out.println("Versió de PPM incorrecta.");
                    }
                    catch (DatosIncorrectos e) {
                        System.out.println("Les dades del fitxer son incorrectes");
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
*/
    }

    static public int seleccionarAlgoritme(String path) {
        if (path.contains(".jpeg")) return 1;
        else if (path.contains(".lzw")) return 2;
        else if (path.contains(".lzss")) return 3;
        else return 4;

    }

}
