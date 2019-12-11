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
    
public EstadisticasDisc() {
    path = System.getProperty("user.home") + "/DB.json";            
}
    
public void resetEstDisc() throws Exception {
    //path = "/home/lucas/Escritorio/DB.json";
    JSONObject LZW = new JSONObject();
    LZW.put("id", "LZW");
    LZW.put("velocitat_compressio", 0);
    LZW.put("percentatge_compressio", 0);
    LZW.put("temps_compressio", 0);
    LZW.put("num_compressions",0);
    LZW.put("num_descompressions", 0);
    LZW.put("velocitat_descompressio", 0);
    LZW.put("percentatge_descompressio", 0);
    LZW.put("temps_descompressio", 0);

    
    JSONObject LZSS = new JSONObject();
    LZSS.put("id", "LZSS");
    LZSS.put("velocitat_compressio", 0);
    LZSS.put("percentatge_compressio", 0);
    LZSS.put("temps_compressio", 0);
    LZSS.put("num_compressions",0);
    LZSS.put("num_descompressions", 0);
    LZSS.put("velocitat_descompressio", 0);
    LZSS.put("percentatge_descompressio", 0);
    LZSS.put("temps_descompressio", 0);


    JSONObject LZ78 = new JSONObject();
    LZ78.put("id", "LZ78");
    LZ78.put("velocitat_compressio", 0);
    LZ78.put("percentatge_compressio", 0);
    LZ78.put("temps_compressio", 0);
    LZ78.put("num_compressions",0);
    LZ78.put("num_descompressions", 0);
    LZ78.put("velocitat_descompressio", 0);
    LZ78.put("percentatge_descompressio", 0);
    LZ78.put("temps_descompressio", 0);

    
    JSONObject JPEG = new JSONObject();
    JPEG.put("id", "JPEG");
    JPEG.put("velocitat_compressio", 0);
    JPEG.put("percentatge_compressio", 0);
    JPEG.put("temps_compressio", 0);
    JPEG.put("num_compressions",0);
    JPEG.put("num_descompressions", 0);
    JPEG.put("velocitat_descompressio", 0);
    JPEG.put("percentatge_descompressio", 0);
    JPEG.put("temps_descompressio", 0);

    
    JSONArray est = new JSONArray();
    est.add(LZW);
    est.add(LZSS);
    est.add(LZ78);
    est.add(JPEG);

    StringBuilder sb = new StringBuilder();
    sb.append(est.toJSONString());
    Files.write(Paths.get(path), sb.toString().getBytes());
}
    
public long[] readEstDisc(String algorithm) {

JSONParser jsonParser = new JSONParser();
try {

    //FileReader reader = new FileReader("/home/lucas/Escritorio/DB.json");
    FileReader reader = new FileReader(path);
    JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
    File f = new File(path);
    //File f = new File("/home/lucas/Escritorio/DB.json");
    if(!f.exists()) resetEstDisc();
    Iterator<?> i = jsonArray.iterator();
    long[] est = new long[6];
    while (i.hasNext()) {
        JSONObject obj = (JSONObject) i.next();
        String id =(String) obj.get("id");
        if(id.equals(algorithm)) {
            est[0] = (long) obj.get("velocitat_compressio");
            est[1] = (long) obj.get("temps_compressio");
            est[2] = (long) obj.get("percentatge_compressio");
            est[3] = (long) obj.get("velocitat_descompressio");
            est[4] = (long) obj.get("percentatge_descompressio");
            est[5] = (long) obj.get("temps_descompressio");
        }
    }
    return est;
} catch (Exception e) {
    e.printStackTrace();
}
return null;
} 
    

public void writeEstCompressio(long temps, long perct, long vel, String algorithm) throws IOException, ParseException, Exception {
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
            long tempscomp = (long) obj.get("temps_compressio");
            long veloc = (long) obj.get("velocitat_compressio");
            long perc = (long) obj.get("percentatge_compressio");
            //System.out.println(tempscomp);
            //System.out.print(veloc);
            long ncomp = (long)obj.get("num_compressions");
            obj.put("temps_compressio", (tempscomp*ncomp+temps)/(ncomp+1));
            obj.put("velocitat_compressio", (veloc*ncomp+vel)/(ncomp+1));
            obj.put("percentatge_compressio", (perc*ncomp+perct)/(ncomp+1));
            obj.put("num_compressions",((int)ncomp)+1);
        }
    }
        //path = "/home/lucas/Escritorio/DB.json";

        Files.write(Paths.get(path), jsonArray.toString().getBytes());

}


public void writeEstDescompressio(long temps, long perct, long vel, String algorithm) throws IOException, ParseException, Exception {
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
            obj.put("temps_descompressio", (tempsdescomp*ndes+temps)/(ndes+1));
            obj.put("velocitat_descompressio", (velodes*ndes+vel)/(ndes+1));
            obj.put("percentatge_descompressio", (perdes*ndes+perct)/(ndes+1));
            obj.put("num_descompressions",((int)ndes)+1);
        }
    }
        //path = "/home/lucas/Escritorio/DB.json";

        Files.write(Paths.get(path), jsonArray.toString().getBytes());

}
}