/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.ModelDomini;

import Excepcions.VersionPPMIncorrecta;
import java.util.HashMap;

/**
 *
 * @author paumu
 */
public class ImatgeComprimida extends Imatge{

    private int modifiedSizeH;
    private int modifiedSizeV;
    private HashMap <String, Integer> decoder;
    private int numPairs;
    int ratioCompression;
    String subsampling;
    
    
     /**
     * Constructora con todos los parametros de creacion de una imagen comprimida
     * @param path
     * @param content
     * @param version
     * @param sizev
     * @param sizeh
     * @param maxValue
     * @param modSizeV
     * @param modSizeH
     * @param dec
     * @param numberPairs
     * @param ratioCompression
     * @param subsampling
     * @throws VersionPPMIncorrecta
     */
    public ImatgeComprimida (String path, byte[] content, String version, int sizev, int sizeh, int maxValue, int modSizeV, int modSizeH, HashMap <String,Integer>dec , int numberPairs, int ratioCompression, String subsampling) throws VersionPPMIncorrecta {
        super(path,content,version,sizev,sizeh,maxValue);
         this.modifiedSizeV = modSizeV;
         this.modifiedSizeH = modSizeH;
         this.decoder = dec;
         this.numPairs = numberPairs;
         this.ratioCompression = ratioCompression;
         this.subsampling = subsampling;
     }

    /**
     * Constructora con un path, un contenido de imagen comprimida en byte[] y el hashmap de descompresion
     * @param path
     * @param content
     * @param decodingHashMap
     * @throws decodingHashMap
     */
   public  ImatgeComprimida(String path, byte[] content, HashMap<String,Integer> decodingHashMap) throws VersionPPMIncorrecta {
        super(path,content);
        decoder = decodingHashMap;
        byte[] contingutActual = super.getContingut();
        StringBuilder modifiedSizes = new StringBuilder();
        int newPos = super.readLine(modifiedSizes,0,contingutActual);
        String modifiedSizesString = modifiedSizes.toString();
        modifiedSizes.setLength(0);
        String[] parsedModifiedSizes = modifiedSizesString.split("\\s+");
        modifiedSizeV = Integer.parseInt(parsedModifiedSizes[0]);
        modifiedSizeH = Integer.parseInt(parsedModifiedSizes[1]);
        newPos = super.readLine(modifiedSizes, newPos, contingutActual);
        numPairs = Integer.parseInt(modifiedSizes.toString());
        modifiedSizes.setLength(0);
        newPos = super.readLine(modifiedSizes,newPos,contingutActual);
        this.ratioCompression = Integer.parseInt(modifiedSizes.toString());
        modifiedSizes.setLength(0);
        newPos = super.readLine(modifiedSizes, newPos, contingutActual);
        this.subsampling = modifiedSizes.toString();
        modifiedSizes.setLength(0);
        byte[] finalContent = new byte[contingutActual.length-newPos];
        for (int i = 0; i < finalContent.length; ++i) {
            finalContent[i] = contingutActual[i+newPos];
        }
        super.setContingut(finalContent);
    }
   
    /**
     * Getter del tamano horizontal modificado
     * @return int modifiedSizeH
     */
    public int getModifiedSizeH() {
        return modifiedSizeH;
    }
    
    /**
     * Getter del tamano vertical modificado
     * @return int modifiedSizeV
     */
    public int getModifiedSizeV() {
        return modifiedSizeV;
    }

    /**
     * Getter del hashmap decodificador
     * @return HashMap (String,Integer) decoder
     */
    public HashMap <String,Integer> getDecoder() {
        return decoder;
    }
    
    /**
     * Getter del numero total de pairs de la imagen del Run Length Encoding
     * @return int numPairs
     */
    public int getNumPairs() {
       return numPairs;
    }

    
    /**
     * Getter del header de la imagen comprimida
     * @return String header
     */
    @Override
    public String getHeader() {
       String header = super.getVersion() + "\n" + Integer.toString(super.getSizeH()) + " " + Integer.toString(super.getSizeV()) + "\n" + Integer.toString(super.getMaxVal()) + "\n" +Integer.toString(modifiedSizeV) + " " + Integer.toString(modifiedSizeH) + "\n" + Integer.toString(numPairs) + "\n" + Integer.toString(this.ratioCompression) + "\n" + this.subsampling + "\n";
       return header;
    }

    /**
     * Getter del tamano del fichero
     * @return int tamanoFichero
     */
    @Override
    public int getMida() {
        int mida = getHeader().length() + decoder.toString().length() + super.getContingut().length;
        return mida;
    }
    
    /**
     * Getter del ratio de compresion
     * @return int ratioCompressio
     */
    public int getRatioCompression() {
        return ratioCompression;
    }
    
    /**
     * Getter de los parametros de subsampling
     * @return String subsamppling
     */

    public String getSubsampling() {
        return subsampling;
    }
}
