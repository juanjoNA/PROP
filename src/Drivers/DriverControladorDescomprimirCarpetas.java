/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drivers;

import CapaDomini.Controladors.ControladorDescomprimirCarpeta;
import Excepcions.DatosIncorrectos;
import Excepcions.ExtensionIncorrecta;
import Excepcions.VersionPPMIncorrecta;
import java.io.IOException;

/**
 *
 * @author ivgasa99
 */
public class DriverControladorDescomprimirCarpetas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExtensionIncorrecta, IOException, ClassNotFoundException, VersionPPMIncorrecta, DatosIncorrectos, Exception {
    ControladorDescomprimirCarpeta cdc = new ControladorDescomprimirCarpeta("/home/ivgasa99/Escritorio/testlzw.carp",true);
    cdc.executar();
    }
    
}
