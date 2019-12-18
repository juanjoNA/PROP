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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getContingutBytes() {
        return contingutBytes;
    }

    public void setContingutBytes(byte[] contingutBytes) {
        this.contingutBytes = contingutBytes;
    }

    public String getContingutChars() {
        return contingutChars;
    }

    public void setContingutChars(String contingutChars) {
        this.contingutChars = contingutChars;
    }

    public HashMap<String, Integer> getReadedHashMap() {
        return readedHashMap;
    }

    public void setReadedHashMap(HashMap<String, Integer> readedHashMap) {
        this.readedHashMap = readedHashMap;
    }
    


    
    
    
    public ArxCarpetaComp(String path, byte[] contingut, HashMap<String,Integer> readedHashMap) {
        this.path = path;
        this.contingutBytes = contingut;
        this.readedHashMap = readedHashMap;
    }
    
    public ArxCarpetaComp(String path, byte[] contingut) {
        this.path = path;
        this.contingutBytes = contingut;
    }
    
    public ArxCarpetaComp(String path, String contingut) {
        this.path = path;
        this.contingutChars = contingut;
    }
        public ArxCarpetaComp(String path) {
        this.path = path;
    }
    
}
