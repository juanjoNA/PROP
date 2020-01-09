/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.Controladors;

import java.util.HashMap;

/**
 *
 * @author paumu
 */
public class DTOImatge {
    HashMap<String,Integer> map;
    byte[] bytes;

    /**
     * Constructora con un hashmap y un contenido en bytes
     * @param m
     * @param b
     */
    public DTOImatge( HashMap<String,Integer> m, byte[] b){
        map = m;
        bytes = b;
    }

    /**
     * Funcion para obtener el map
     * @return map(HashMap(String, Integer))
     */ 
    public HashMap<String, Integer> getMap() {
        return map;
    }

    /**
     * Funcion para obtener el contenido de bytes
     * @return bytes(byte[])
     */
    public byte[] getBytes() {
        return bytes;
    }

}
