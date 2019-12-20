/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.ModelDomini;

import Excepcions.ExtensionIncorrecta;
import Excepcions.VersionPPMIncorrecta;

/**
 *
 * @author paumu
 */
public class Imatge extends ArxiuBytes {
    private final String version;
    private final int sizeH;
    private final int sizeV;
    private final int maxVal;

    /**
     * Constructora con todos los parametros de una imagen
     * @param path
     * @param contingut
     * @param version
     * @param sizev
     * @param sizeh
     * @param maxValue
     * @throws VersionPPMIncorrecta
     */
    public Imatge (String path, byte[] contingut, String v, int sv, int sh, int maxValue) throws VersionPPMIncorrecta, ExtensionIncorrecta {
        super(path,contingut);
        if (!v.equals("P6")) {
            throw new VersionPPMIncorrecta();
        }
        version = v;
        sizeH = sh;
        sizeV = sv;
        maxVal = maxValue;

    }

    /**
     * Constructora con un path y todo el contenido de una imagen
     * @param path
     * @param content
     * @throws VersionPPMIncorrecta
     */
    public Imatge (String path, byte [] content) throws VersionPPMIncorrecta {
        super(path,content);
        //Aquesta constructora no fa throw de si la imatge es ppm perque nomes es crida des de imatgeComprimida
        int position = 0;
        StringBuilder line = new StringBuilder();
        position = readLine(line,position,content);
        version = line.toString();
        line.setLength(0);
        if (version.length() < 2 && !version.substring(0,2).equals("P6")) {
            throw new VersionPPMIncorrecta();
        }
        position = readLine(line,position,content);
        String sizes = line.toString();
        line.setLength(0);
        String[] parsed = sizes.split(" ");
        sizeH = Integer.parseInt(parsed[0]);
        sizeV = Integer.parseInt(parsed[1]);

        position = readLine(line,position,content);
        String maxValString = line.toString();
        maxVal = Integer.parseInt(maxValString);
        byte[] contingutFinal = new byte[content.length - position];
        for (int i = 0; i < contingutFinal.length; ++i) {
            contingutFinal[i] = content[i+position];
        }
        super.setContingut(contingutFinal);
    }

    /**
     * Funcion para leer una linea de texto en un byte[]
     * @param line
     * @param pos
     * @param content
     */
    protected int readLine(StringBuilder line, int pos, byte[] content) {
        while ((char)content[pos] != '\n') {
            line.append((char)content[pos]);
            ++pos;
        }
        ++pos;
        return pos;
    }

    /**
     * Getter de la version de la imagen
     */
    public String getVersion() {
        return version;
    }

    /**
     * Getter del tamano vertical de la imagen
     */
    public int getSizeH() {
        return sizeH;
    }

    /**
     * Getter del tamano horizontal de la imagen
     */
    public int getSizeV() {
        return sizeV;
    }

    /**
     * Getter del valor maximo de la imagen
     */
    public int getMaxVal() {
        return maxVal;
    }

    /**
     * Getter del header de la imagen
     */
    public  String getHeader() {
        String header = version + "\n" + String.valueOf(sizeH) + " " + String.valueOf(sizeV) + "\n" + String.valueOf(maxVal) + "\n";
        return header;
    }

    /**
     * Getter del tamano de la imagen
     */
    public int getMida() {
        int mida = getHeader().length() + super.getContingut().length;
        return mida;
    }

}
