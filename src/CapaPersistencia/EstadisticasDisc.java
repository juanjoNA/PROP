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
    private String[] est;
    
    /**
     * Constructora por defecto de la classe
     * @throws Exception
     */
    public EstadisticasDisc() throws Exception {
        path = "./DBCompresorProp.json";
        inicializaralg(alg);
        inicializarest(est);
        File f = new File(this.path);
        if(!f.exists()){
            resetEstDisc();
        }
    }

private void inicializaralg(String[] s) {
    alg = new String[4];
    alg[0] = "LZW";
    alg[1] = "LZSS";
    alg[2] = "LZ78";
    alg[3] = "JPEG";
}

private void inicializarest(String[] e) {
    est = new String[9];
    est[0] = "id";
    est[1] = "velocitat_compressio";
    est[2] = "percentatge_compressio";
    est[3] = "temps_compressio";
    est[4] = "velocitat_descompressio";
    est[5] = "percentatge_descompressio";
    est[6] = "temps_descompressio";
    est[7] = "num_compressions";
    est[8] = "num_descompressions";
}
    
private void resetEstDisc() throws Exception {
    JSONArray est = new JSONArray();
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < alg.length; i++){
        JSONObject algoritmo = new JSONObject();
        algoritmo.put("id", alg[i]);
        for(int j = 1; j < this.est.length; j++) {
            algoritmo.put(this.est[j],new Double(0));
        }
        est.add(algoritmo);
        }
    sb.append(est.toJSONString());
    IOArxius ioa = new IOArxius();
    ioa.guardaArxiuTXT(this.path, sb.toString(),false);
    //Files.write(Paths.get(path), sb.toString().getBytes());
}
    
    /**
     * Funcion para leer todas las estadisticas de un algoritmo indicado
     * @param algorithm
     * @return estadisticas(double[])
     */
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
            for(int j = 1; j < this.est.length; j++) {
                est[j-1] = (double) obj.get(this.est[j]);
            }    
        }
    }
    return est;
} catch (Exception e) {
    e.printStackTrace();
}
return null;
} 
    
    /**
     * Fujncion para escribir estadisticas de compression en la base de datos
     * @param temps
     * @param perct
     * @param vel
     * @param algorithm
     * @throws IOException
     * @throws ParseException
     * @throws Exception
     */
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

    /** 
     * Funcion para escribir estadisticas de descompression en la base de datos
     * @param temps
     * @param perct
     * @param vel
     * @param algorithm
     * @throws IOException
     * @throws ParseException
     * @throws Exception
     */
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
            obj.put("num_descompressions",new Double(ndes+1));
        }
    }
        Files.write(Paths.get(path), jsonArray.toString().getBytes());

}

    /**
     * Funcion para obtener el algoritmo mas eficiente en funcion del porcentage de compression
     * @return algoritmo(String)
     * @throws Exception
     */
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

    /**
     * Funcion para obtener todos los algoritmos que existen en la base dedatos
     * @return algoritmos(String[])
     */
    public String[] getalgoritmos() {
        return this.alg;
    }
    
    /**
     * Funcion para obtener todas las estadisticas que se guardan en la base de datos
     * @return estadisticas(String[])
     */
    public String[] getnomEst() {
        return this.est;
    }
}