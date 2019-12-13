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
    
public EstadisticasDisc() throws Exception {
    path = System.getProperty("user.home") + "/DB.json";
    alg = new String[4];
    alg[0] = "LZW";
    alg[1] = "LZSS";
    alg[2] = "LZ78";
    alg[3] = "JPEG";
    resetEstDisc();
}
    
private void resetEstDisc() throws Exception {
    JSONArray est = new JSONArray();
    StringBuilder sb = new StringBuilder();
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
    sb.append(est.toJSONString());
    IOArxius ioa = new IOArxius();
    ioa.guardaArxiuTXT(this.path, sb.toString(),false);
    //Files.write(Paths.get(path), sb.toString().getBytes());
}
    
public double[] readEstDisc(String algorithm) {

JSONParser jsonParser = new JSONParser();
try {

    FileReader reader = new FileReader(this.path);
    JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
    File f = new File(this.path);
    if(!f.exists()) resetEstDisc();
    Iterator<?> i = jsonArray.iterator();
    double[] est = new double[8];
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
            est[6] = (double) obj.get("num_compressions");
            est[7] = (double) obj.get("num_descompressions");
        }
    }
    return est;
} catch (Exception e) {
    e.printStackTrace();
}
return null;
} 
    

public void writeEstCompressio(double temps, double perct, double vel, String algorithm) throws IOException, ParseException, Exception {
    JSONParser jsonParser = new JSONParser();
        File f = new File(path);

    if(!f.exists()) resetEstDisc();
        FileReader reader = new FileReader(path);

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
            obj.put("num_compressions",new Double(ncomp+1));
        }
    }
        Files.write(Paths.get(path), jsonArray.toString().getBytes());

}


public void writeEstDescompressio(double temps, double perct, double vel, String algorithm) throws IOException, ParseException, Exception {
    JSONParser jsonParser = new JSONParser();
        File f = new File(path);

    if(!f.exists()) resetEstDisc();
        FileReader reader = new FileReader(path);

    JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

    Iterator<?> i = jsonArray.iterator();

    while (i.hasNext()) {
        JSONObject obj = (JSONObject) i.next();
        String id =(String) obj.get("id");
        if(id.equals(algorithm)) {
            double tempsdescomp = (double) obj.get("temps_descompressio");
            double velodes = (double) obj.get("velocitat_descompressio");
            double perdes = (double) obj.get("percentatge_descompressio");
            double ndes = (double)obj.get("num_descompressions");
            obj.put("temps_descompressio", new Double(tempsdescomp*ndes+temps)/(ndes+1));
            obj.put("velocitat_descompressio", new Double(velodes*ndes+vel)/(ndes+1));
            obj.put("percentatge_descompressio", new Double(perdes*ndes+perct)/(ndes+1));
            obj.put("num_descompressions",new Double(ndes)+1);
        }
    }
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