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

    public DTOImatge( HashMap<String,Integer> m, byte[] b){
        map = m;
        bytes = b;
    }

     public HashMap<String, Integer> getMap() {
        return map;
    }

    public byte[] getBytes() {
        return bytes;
    }

}
