/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import CapaDomini.Controladors.DTOImatge;
import CapaDomini.ModelDomini.Estadistiques;
import CapaDomini.ModelDomini.Imatge;
import CapaDomini.ModelDomini.ImatgeComprimida;
import CapaDomini.ModelDomini.JPEG;
import CapaPersistencia.IOArxius;
import Excepcions.DatosIncorrectos;
import Excepcions.ExtensionIncorrecta;
import Excepcions.VersionPPMIncorrecta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *
 * @author paumu
 */
public class DriverJPEG {
    
    private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));
    
    
    public static void main(String[] args) throws IOException {
       int opcion;
        boolean salir=false;
        
        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. Comprimir Arxiu."
                         + "\n\t2. Descomprimir Arxiu."
                         + "\n\t3. Salir.");
            
            opcion = Integer.parseInt(intro.readLine());
            String path;
            String guardar;
            byte[] contingut = null;
            JPEG compressor = new JPEG();
            IOArxius persistencia = new IOArxius();
            
            switch(opcion) {
                case 1: {
                    System.out.println("Introdueix el path de l'arxiu: ");
                    //path = intro.readLine();
                    path = "D:\\Java\\assets\\pooh.ppm";
                    System.out.println("Vols guardar arxiu a disc \n S/N: ");
                    //guardar = intro.readLine();
                    try {
                        contingut = persistencia.llegeixArxiuBinari(path,".ppm");
                        Imatge imatgeLlegida = new Imatge(path,contingut);
                        //Imatge desprocessat = compressor.comprimir(imatgeLlegida);
                        ImatgeComprimida comprimit = compressor.comprimir(imatgeLlegida);
                        //if (guardar.equals("S")) {
                            System.out.println("Introdueix el path de on es guarda l'arxiu");
                            //desprocessat.setPath(desprocessat.getPath().replace(".ppm", "desco.ppm"));
                            System.out.println("Going to save");
                            //persistencia.guardaImatge(desprocessat.getPath(), desprocessat.getHeader(), desprocessat.getContingut());
                            persistencia.guardarImatgeComprimida(comprimit.getPath(),comprimit.getDecoder(),comprimit.getHeader(),comprimit.getContingut());
                        //}
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
                    break;
                }
                case 2: {
                    System.out.println("Introdueix el path de l'arxiu: ");
                    //path = intro.readLine();
                    path = "D:\\Java\\assets\\pooh.jimg";
                    System.out.println("Vols guardar arxiu a disc \n S/N: ");
                    //guardar = intro.readLine();
                    guardar = "S";
                    try {
                        DTOImatge llegit =persistencia.llegeixImatgeComprimida(path);
                        contingut = llegit.getBytes();
                        HashMap<String,Integer> map = llegit.getMap();
                        ImatgeComprimida imatgeLlegida = new ImatgeComprimida(path,contingut,map);
                        contingut = imatgeLlegida.getContingut();
                        Imatge desprocessat = compressor.descomprimir(imatgeLlegida);

                        if (guardar.equals("S")) {
                            System.out.println("Introdueix el path de on es guarda l'arxiu");
                            desprocessat.setPath(desprocessat.getPath().replace(".ppm", "desco.ppm"));
                            persistencia.guardaImatge(desprocessat.getPath(), desprocessat.getHeader(), desprocessat.getContingut());
                        }
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
                    break;
                    
                }
                case 3: 
                    salir = true;
                    break;
                default: 
                    System.out.println("Introdueix una opció correcta");
                    break;
                
            }
            
        }
    }
}
