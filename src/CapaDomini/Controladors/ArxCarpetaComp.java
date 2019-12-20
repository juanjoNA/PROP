/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.Controladors;

import java.util.HashMap;

/**
 *
 * @author ivgasa99
 */
public class ArxCarpetaComp {
    
    private String path;
    private byte[] contingutBytes;
    private String contingutChars;
    private HashMap<String,Integer> readedHashMap;

    /**
     * Funcion para obtener el path
     * @return path(String)
     */
    public String getPath() {
        return path;
    }

    /**
     * Funcion para guardar un path
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Funcion para obtener un array de bytes con el contenido.
     * @return contingutBytes(byte[])
     */
    public byte[] getContingutBytes() {
        return contingutBytes;
    }

    /**
     * Funcion para guardar el contenido de bytes.
     * @param contingutBytes
     */
    public void setContingutBytes(byte[] contingutBytes) {
        this.contingutBytes = contingutBytes;
    }

    /**
     * Funcion para obtener el contenido en chars
     * @return contingutChars(String)
     */
    public String getContingutChars() {
        return contingutChars;
    }

    /**
     * Funcion para guardar el contenido en chars
     * @param contingutChars
     */
    public void setContingutChars(String contingutChars) {
        this.contingutChars = contingutChars;
    }

    /**
     * Funcion para leer el HashMap
     * @return readedHashMao(HashMap(String, Integer))
     */
    public HashMap<String, Integer> getReadedHashMap() {
        return readedHashMap;
    }

    /**
     * Funcion para guardar el Hashmap de lectura
     * @param readedHashMap
     */
    public void setReadedHashMap(HashMap<String, Integer> readedHashMap) {
        this.readedHashMap = readedHashMap;
    }
    
    /**
     * Constructora con un path, el contenido y un readerHashmap
     * @param path
     * @param contingut
     * @param readedHashMap
     */
    public ArxCarpetaComp(String path, byte[] contingut, HashMap<String,Integer> readedHashMap) {
        this.path = path;
        this.contingutBytes = contingut;
        this.readedHashMap = readedHashMap;
    }
    
    /**
     * Constructora con path y contenido en bytes
     * @param path
     * @param contingut
     */
    public ArxCarpetaComp(String path, byte[] contingut) {
        this.path = path;
        this.contingutBytes = contingut;
    }
    
    /**
     * Constructora con path y contenido en String
     * @param path
     * @param contingut
     */
    public ArxCarpetaComp(String path, String contingut) {
        this.path = path;
        this.contingutChars = contingut;
    }

    /**
     * Constructora con un path
     * @param path
     */
    public ArxCarpetaComp(String path) {
        this.path = path;
    }
    
}
