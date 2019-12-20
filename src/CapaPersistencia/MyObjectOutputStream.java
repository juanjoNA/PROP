/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPersistencia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author ivgasa99
 */
public class MyObjectOutputStream extends ObjectOutputStream{
     /**
     * Constructora con un FileOutputStream 
     * @param fos
     * @throws java.io.IOException
     */
    public MyObjectOutputStream(FileOutputStream fos) throws IOException {
        super(fos);
    }
     /**
     * Funcion reescribida para que no inserte cabecera cada vez que insertamos contenido en un fichero
     * @throws IOException
     */
    @Override
    protected void writeStreamHeader() throws IOException {
        
    }
    
}
