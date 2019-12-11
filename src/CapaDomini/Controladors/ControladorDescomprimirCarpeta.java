/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.Controladors;

import CapaDomini.ModelDomini.Arxiu;
import CapaDomini.ModelDomini.ArxiuBytes;
import CapaDomini.ModelDomini.ArxiuTXT;
import CapaDomini.ModelDomini.Estadistiques;
import CapaDomini.ModelDomini.Imatge;
import CapaDomini.ModelDomini.ImatgeComprimida;
import CapaDomini.ModelDomini.JPEG;
import CapaDomini.ModelDomini.LZ78;
import CapaDomini.ModelDomini.LZSS;
import CapaDomini.ModelDomini.LZW;
import CapaPersistencia.IOArxius;
import Excepcions.ExtensionIncorrecta;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author ivgasa99
 */
public class ControladorDescomprimirCarpeta {
    private String path;
    private boolean guardar;
    private double[] result;
    
    public ControladorDescomprimirCarpeta (String path, boolean guardar) {
        this.path=path;
        this.guardar=guardar;
        this.result=new double[3];
    }
    
    public void executar() throws ExtensionIncorrecta {
        if (!path.contains(".carp")) throw new ExtensionIncorrecta();
    }
    
}
