/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPersistencia;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author lucas
 */
public class EstadisticasDisc {
    private String path;
    private String[] alg;
    
public EstadisticasDisc() {
    path = System.getProperty("user.home") + "/DB.json";
    alg = new String[4];
    alg[0] = "LZW";
    alg[1] = "LZSS";
    alg[2] = "LZ78";
    alg[3] = "JPEG";
}
    
private void resetEstDisc() throws Exception {
    JSONArray est = new JSONArray();
    for(int i = 0; i < alg.length; i++){
        JSONObject algoritmo = new JSONObject();
        algoritmo.put("id", alg[i]);
        algoritmo.put("velocitat_compressio", new Double(0));
        algoritmo.put("percentatge_compressio", new Double(0));
        algoritmo.put("temps_compressio", new Double(0));
        algoritmo.put("num_compressions",new Double(0));
        algoritmo.put("num_descompressions", new Double(0));
        algoritmo.put("velocitat_descompressio", new Double(0));
        algoritmo.put("percentatge_descompressio", new Double(0));
        algoritmo.put("temps_descompressio", new Double(0));
        
        est.add(algoritmo);
        }
    StringBuilder sb = new StringBuilder();
    sb.append(est.toJSONString());
    Files.write(Paths.get(path), sb.toString().getBytes());
}
    
public double[] readEstDisc(String algorithm) {

JSONParser jsonParser = new JSONParser();
try {

    FileReader reader = new FileReader(path);
    JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
    File f = new File(path);
    if(!f.exists()) resetEstDisc();
    Iterator<?> i = jsonArray.iterator();
    double[] est = new double[6];
    while (i.hasNext()) {
        JSONObject obj = (JSONObject) i.next();
        String id =(String) obj.get("id");
        if(id.equals(algorithm)) {
            est[0] = (double) obj.get("velocitat_compressio");
            est[1] = (double) obj.get("temps_compressio");
            est[2] = (double) obj.get("percentatge_compressio");
            est[3] = (double) obj.get("velocitat_descompressio");
            est[4] = (double) obj.get("temps_descompressio");
            est[5] = (double) obj.get("temps_descompressio");
        }
    }
    return est;
} catch (Exception e) {
    e.printStackTrace();
}
return null;
} 
    

public void writeEstCompressio(double temps, double perct, double vel, String algorithm) throws IOException, ParseException, Exception {
    //cojer numero de compressiones i descompressiones i volver a hacer la media con los nuevos valores.
    JSONParser jsonParser = new JSONParser();
        File f = new File(path);

//    File f = new File("/home/lucas/Escritorio/DB.json");
    if(!f.exists()) resetEstDisc();
        FileReader reader = new FileReader(path);

    //FileReader reader = new FileReader("/home/lucas/Escritorio/DB.json");

    JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

    Iterator<?> i = jsonArray.iterator();

    while (i.hasNext()) {
        JSONObject obj = (JSONObject) i.next();
        String id =(String) obj.get("id");
        if(id.equals(algorithm)) {
            double tempscomp = (double) obj.get("temps_compressio");
            double veloc = (double) obj.get("velocitat_compressio");
            double perc = (double) obj.get("percentatge_compressio");
            double ncomp = (double)obj.get("num_compressions");
            obj.put("temps_compressio", new Double(tempscomp*ncomp+temps)/(ncomp+1));
            obj.put("velocitat_compressio", new Double(veloc*ncomp+vel)/(ncomp+1));
            obj.put("percentatge_compressio", new Double(perc*ncomp+perct)/(ncomp+1));
            obj.put("num_compressions",new Double((int)ncomp)+1);
        }
    }
        Files.write(Paths.get(path), jsonArray.toString().getBytes());

}


public void writeEstDescompressio(double temps, double perct, double vel, String algorithm) throws IOException, ParseException, Exception {
    //cojer numero de compressiones i descompressiones i volver a hacer la media con los nuevos valores.
    JSONParser jsonParser = new JSONParser();
        File f = new File(path);

    //File f = new File("/home/lucas/Escritorio/DB.json");
    if(!f.exists()) resetEstDisc();
        FileReader reader = new FileReader(path);

   // FileReader reader = new FileReader("/home/lucas/Escritorio/DB.json");

    JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

    Iterator<?> i = jsonArray.iterator();

    while (i.hasNext()) {
        JSONObject obj = (JSONObject) i.next();
        String id =(String) obj.get("id");
        //System.out.println(obj.get("id"));
        if(id.equals(algorithm)) {
            //System.out.println("entra if");
            long tempsdescomp = (long) obj.get("temps_descompressio");
            long velodes = (long) obj.get("velocitat_descompressio");
            long perdes = (long) obj.get("percentatge_descompressio");
            //System.out.println(tempscomp);
            //System.out.print(veloc);
            long ndes = (long)obj.get("num_descompressions");
            obj.put("temps_descompressio", new Double(tempsdescomp*ndes+temps)/(ndes+1));
            obj.put("velocitat_descompressio", new Double(velodes*ndes+vel)/(ndes+1));
            obj.put("percentatge_descompressio", new Double(perdes*ndes+perct)/(ndes+1));
            obj.put("num_descompressions",new Double((int)ndes)+1);
        }
    }
        //path = "/home/lucas/Escritorio/DB.json";

        Files.write(Paths.get(path), jsonArray.toString().getBytes());

}

public String getBestAlgorithm() throws Exception{
    File f = new File(path);
    if(!f.exists()) resetEstDisc();
    double[] lzss = readEstDisc("LZSS");
    double[] lzw = readEstDisc("LZW");
    double[] lz78 = readEstDisc("LZ78");
    return comparar(lzss,lzw,lz78);
}

    private String comparar(double[] lzss, double[] lzw, double[] lz78) {
        if(lzss[2]>lzw[2] && lzss[2] > lzw[2]){
            if(!massalent(lzss,lzw,lz78)) return ("LZSS");
        }
        if(lzw[2]>lz78[2]) {
            if(!massalent(lzw,lzss,lz78)) return "LZW";
        }
        else return "LZ78";
        return "LZW";
    }

    private boolean massalent(double[] lzss, double[] lzw, double[] lz78) {
        if((lzss[1]>1.25*lzw[1]) || (lzss[1]>lz78[1]*1.25)) return true;
        return false;
    }

    public String[] getalgoritmos() {
        return this.alg;
    }
}