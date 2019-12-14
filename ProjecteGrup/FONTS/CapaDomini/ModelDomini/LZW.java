/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.ModelDomini;

import java.util.HashMap;
import Excepcions.CaracterNoASCII;

/**
 *
 * @author Juanjo.Navarro
 */
public class LZW extends LZ{

     public ArxiuTXT descomprimir (ArxiuTXT a) {
        long start = System.currentTimeMillis();
        HashMap <Character,String> traductor = new HashMap <> ();
        for (int i = 0; i < 256; ++i) {
            String letra = Character.toString((char) i);
            traductor.put((char) i,letra);
	}
        String path = a.getPath();
        String contenido = a.getContingut();
        if(contenido.length() == 0) {
            String pathnuevo = path.replace(".lzw","(2).txt");
            return new ArxiuTXT(pathnuevo,contenido);
        }
            
        StringBuilder result = new StringBuilder("");
        Character codviejo = contenido.charAt(0);
        String caracter = traductor.get(codviejo);
        result.append(caracter);
        int valor = 256;
        String insertar = new String();
        String char_prim = new String();
        for (int i = 1; i < contenido.length(); ++i) {
            Character codnuevo = contenido.charAt(i);

            if (traductor.containsKey(codnuevo)) insertar = traductor.get(codnuevo);
            else {
                caracter = Character.toString(char_prim.charAt(0));
                insertar = traductor.get(codviejo) + caracter;
            }
            result.append(insertar);
            char_prim = Character.toString(insertar.charAt(0));

            if (valor < 32768) {
                String entra = traductor.get(codviejo) + char_prim;
                traductor.put((char) valor, entra);
                ++valor;
            }

             codviejo = codnuevo;
        }
        long end = System.currentTimeMillis();
        String pathnuevo = a.getPath();
        pathnuevo = pathnuevo.replace(".lzw","(2).txt");
        ArxiuTXT arxiudescomprimit = new ArxiuTXT(pathnuevo,result.toString());
        Estadistiques e = new Estadistiques(start,end,result.toString().getBytes().length,contenido.getBytes().length);
        arxiudescomprimit.setEstadistiques(e);
        return arxiudescomprimit;
    }

    public ArxiuTXT comprimir (ArxiuTXT a) throws CaracterNoASCII {
        long start = System.currentTimeMillis();
        HashMap <String, Character> traductor = new HashMap <> ();
        for (int i = 0; i < 256; ++i) {
            String letra = Character.toString((char) i);
            traductor.put(letra,(char) i);
	}
        StringBuilder resultado = new StringBuilder("");
        int pos = 256;
        Character k;
        String wk = new String();
        String contingut = a.getContingut();
        String path = a.getPath();
        String w = new String();
        for (int i = 0; i < contingut.length(); ++i) {
            k = contingut.charAt(i);
            if ((int) k > 255) throw new CaracterNoASCII();
            wk = w+k;
            //System.out.print(k);
            if (traductor.containsKey(wk)) {
                w = wk;
            }

            else {
                //System.out.println(traductor.get(w));
                resultado.append(traductor.get(w));
                if (pos < 32768) {
                    traductor.put(wk, (char) pos);
                    pos = pos+1;
                }
                w=Character.toString(k);
            }
        }
        long end = System.currentTimeMillis();
        resultado.append(w);
        String pathnuevo = a.getPath();
        pathnuevo = pathnuevo.replace(".txt", ".lzw");
        ArxiuTXT arxiucomprimit = new ArxiuTXT(pathnuevo,resultado.toString());
        Estadistiques e = new Estadistiques(start,end,contingut.getBytes().length,resultado.toString().getBytes().length);
        arxiucomprimit.setEstadistiques(e);
        return arxiucomprimit;
    }

}
