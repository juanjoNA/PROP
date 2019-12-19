/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.Controladors;

import CapaDomini.ModelDomini.Arxiu;
import CapaDomini.ModelDomini.ArxiuBytes;
import CapaDomini.ModelDomini.ArxiuTXT;
import CapaDomini.ModelDomini.Estadistiques;
import CapaDomini.ModelDomini.Imatge;
import CapaDomini.ModelDomini.ImatgeComprimida;
import CapaDomini.ModelDomini.JPEG;
import CapaDomini.ModelDomini.LZ78;
import CapaDomini.ModelDomini.LZSS;
import CapaDomini.ModelDomini.LZW;
import CapaPersistencia.IOArxius;
import Excepcions.DatosIncorrectos;
import Excepcions.VersionPPMIncorrecta;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Juanjo.Navarro
 */
public class ControladorDescomprimir {
    private String path;
    private boolean guardar;
    private double[] result;

    /**
     * Funcion para obtener la variable result con todas las estadistocas de descompression
     * @return result(double[])
     */
    public double[] getResult() {
        return result;
    }

    /**
     * Constructora con un path i un bool para saber si hay que guardar en disco el fichero
     * @param path
     * @param guardar
     */
    public ControladorDescomprimir (String path, boolean guardar) {
        this.path = path;
        this.guardar = guardar;
        this.result = new double[3];
    }

    /**
     * Funcion principal del controlador, descomprime un arxivo en bytes y deja el resultado de las estadisticas en la variable result
     * @throws VersionPPMIncorrecta
     * @throws DatosIncorrectos
     * @throws IOException
     * @throws Exception
     */
    public void executar() throws VersionPPMIncorrecta, DatosIncorrectos, IOException, Exception {
        IOArxius i = new IOArxius();
        Arxiu descomprimit = null;
        String algoritmo = seleccionaAlg(path);
        switch(algoritmo) {
            //JPEG
            case "JPEG":{
                DTOImatge llegit =i.llegeixImatgeComprimida(path);
                byte[] contingut = llegit.getBytes();
                HashMap<String,Integer> map = llegit.getMap();
                ImatgeComprimida imatgeLlegida = new ImatgeComprimida(path,contingut,map);
                contingut = imatgeLlegida.getContingut();
                JPEG compressor = new JPEG();
                Imatge desprocessat = compressor.descomprimir(imatgeLlegida);
                descomprimit = desprocessat;
                if (guardar) {
                    System.out.println("Introdueix el path de on es guarda l'arxiu");
                    i.guardaImatge(desprocessat.getPath(), desprocessat.getHeader(), desprocessat.getContingut());
                }
                break;
            }
            //LZW
            case "LZW": {
                byte[] con = i.llegeixArxiuBinari(path);
                String contingut = new String(con);
                ArxiuTXT b = new ArxiuTXT(path,contingut);
                LZW c = new LZW();
                ArxiuTXT l = c.descomprimir(b);
                descomprimit = l;
                if (guardar) {
                    i.guardaArxiuTXT(l.getPath(),l.getContingut(),false);
                }
                break;
            }

            //LZSS
            case "LZSS": {
                byte[] con = i.llegeixArxiuBinari(path);
                ArxiuBytes b = new ArxiuBytes(path,con);
                LZSS des = new LZSS();
                ArxiuTXT d = des.descomprimir(b);
                descomprimit = d;
                if (guardar) {
                    i.guardaArxiuTXT(d.getPath(),d.getContingut(),false);
                }
                break;

            }
            //LZ78
            case "LZ78": {
                byte[] con = i.llegeixArxiuBinari(path);
                ArxiuBytes b = new ArxiuBytes(path,con);
                LZ78 des = new LZ78();
                ArxiuTXT d = des.descomprimir(b);
                descomprimit = d;
                if (guardar) {
                    i.guardaArxiuTXT(d.getPath(),d.getContingut(),false);
                }
                break;

            }

        }
        Estadistiques e = descomprimit.getEstadistiques();
        result[0] = e.getTemps_compressio();
        result[1] = e.getPercentatge_compressio();
        result[2] = e.getVelocitat_compressio();
        e.guardaEst(result, algoritmo, false);
    }
    private String seleccionaAlg(String text) {
        String extensio;
        
        if(text.endsWith(".lzss")) extensio="LZSS";
        else if(text.endsWith(".lz78")) extensio="LZ78";
        else if(text.endsWith(".lzw")) extensio="LZW";
        else extensio="JPEG";
        
        return extensio;
    }
}