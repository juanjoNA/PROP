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

    public ImatgeComprimida (String path, byte[] content, String v, int sv, int sh, int maxValue, int modSizeV, int modSizeH, HashMap <String,Integer>dec , int numberPairs) throws VersionPPMIncorrecta, ExtensionIncorrecta {
        super(path,content,v,sv,sh,maxValue);
         modifiedSizeV = modSizeV;
         modifiedSizeH = modSizeH;
         decoder = dec;
         numPairs = numberPairs;
     }

   public  ImatgeComprimida(String path, byte[] content, HashMap<String,Integer> dec) throws VersionPPMIncorrecta {
        super(path,content);
        decoder = dec;
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
        byte[] finalContent = new byte[contingutActual.length-newPos];
        for (int i = 0; i < finalContent.length; ++i) {
            finalContent[i] = contingutActual[i+newPos];
        }
        super.setContingut(finalContent);
    }

    public int getModifiedSizeH() {
        return modifiedSizeH;
    }

    public int getModifiedSizeV() {
        return modifiedSizeV;
    }

    public HashMap <String,Integer> getDecoder() {
        return decoder;
    }
    public int getNumPairs() {
       return numPairs;
    }

    @Override
    public String getHeader() {
       String header = super.getVersion() + "\n" + Integer.toString(super.getSizeH()) + " " + Integer.toString(super.getSizeV()) + "\n" + Integer.toString(super.getMaxVal()) + "\n" +Integer.toString(modifiedSizeV) + " " + Integer.toString(modifiedSizeH) + "\n" + Integer.toString(numPairs) + "\n";
       return header;
    }

    @Override
    public int getMida() {
        int mida = getHeader().length() + decoder.toString().length() + super.getContingut().length;
        return mida;
    }
}
