/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.Controladors;

import CapaDomini.ModelDomini.Arxiu;
import CapaDomini.ModelDomini.ArxiuBytes;
import CapaDomini.ModelDomini.ArxiuTXT;
import CapaDomini.ModelDomini.Imatge;
import CapaDomini.ModelDomini.ImatgeComprimida;
import CapaDomini.ModelDomini.JPEG;
import CapaDomini.ModelDomini.LZ78;
import CapaDomini.ModelDomini.LZSS;
import CapaDomini.ModelDomini.LZW;
import CapaPersistencia.IOArxius;
import Excepcions.CaracterNoASCII;
import Excepcions.DatosIncorrectos;
import Excepcions.VersionPPMIncorrecta;
import java.io.IOException;

/**
 *
 * @author paumu
 */
public class ControladorComparar {
    
    private final String pathLlegir;
    private final String pathGuardar;
    private final String algoritmo;
    private final DTOComparar result;
    private final int ratioCompression;
    private final String subsampling;
    
    /**
     * Constructora con un path de lectura y un algoritmo
     * @param pathLlegir
     * @param algoritmo
     */
    public ControladorComparar(String pathLlegir, String algoritmo) {
        this.algoritmo = algoritmo;
        this.pathLlegir = pathLlegir;
        this.pathGuardar = ".notvalid.";
        this.result = new DTOComparar();
        this.ratioCompression = -1;
        this.subsampling = "";
        
    }
    
    /**
     * Constructora con un path de lectura, un path para guardar y un algoritmo
     * @param pathLlegir
     * @param pathGuardar
     * @param algoritmo
     */
    public ControladorComparar(String pathLlegir, String pathGuardar, String algoritmo) {
        this.algoritmo = algoritmo;
        this.pathLlegir = pathLlegir;
        this.pathGuardar = pathGuardar;
        this.result = new DTOComparar();
        this.ratioCompression = -1;
        this.subsampling = "";
    }
    
    /**
     * Constructora con un path de lectura, un algoritmo, ratio de compression y subsampling
     * @param pathLlegir
     * @param algoritmo
     * @param ratioCompression
     * @param subsampling
     */
    public ControladorComparar(String pathLlegir, String algoritmo, int ratioCompression, String subsampling) {
        this.algoritmo = algoritmo;
        this.pathLlegir = pathLlegir;
        this.pathGuardar = ".notvalid.";
        this.result = new DTOComparar();
        this.ratioCompression = ratioCompression;
        this.subsampling = subsampling;
    }
    
    /**
     * Constructora con un path de lectura, un path para guardar, un algoritmo, un ratio de compression y un subsampling
     * @param pathLlegir
     * @param pathGuardar
     * @param algoritmo
     * @param ratioCompression
     * @param subsampling
     */
    public ControladorComparar(String pathLlegir, String pathGuardar, String algoritmo, int ratioCompression, String subsampling) {
        this.algoritmo = algoritmo;
        this.pathLlegir = pathLlegir;
        this.pathGuardar = pathGuardar;
        this.result = new DTOComparar();
        this.ratioCompression = ratioCompression;
        this.subsampling = subsampling;
    }
    
    /**
     * Funcion principal del Controlador, comprime y descomprime un fichero dejando el resultado en result.
     * @throws CaracterNoASCII
     * @throws IOException
     * @throws VersionPPMIncorrecta
     * @throws DatosIncorrectos
     */
    public void executar() throws CaracterNoASCII, IOException, VersionPPMIncorrecta, DatosIncorrectos {
        IOArxius io = new IOArxius();
        Arxiu processat = null;
        Arxiu resultat = null;
        byte[] contingutInicial = null;
        byte[] contingutFinal = null;
        switch (algoritmo) {
            case "JPEG": {
                byte[] contingut = io.llegeixArxiuBinari(pathLlegir);
                contingutInicial = contingut;
                Imatge imatgeLlegida = new Imatge(pathLlegir,contingut);
                contingutInicial = new byte[imatgeLlegida.getHeader().getBytes().length + imatgeLlegida.getContingut().length];
                JPEG compressor = new JPEG();
                ImatgeComprimida comprimit = compressor.comprimir(imatgeLlegida, ratioCompression, subsampling);
                processat = comprimit;
                Imatge desprocessat = compressor.descomprimir(comprimit);
                resultat = desprocessat;
                contingutFinal = new byte[desprocessat.getHeader().getBytes().length + desprocessat.getContingut().length];
                System.arraycopy(desprocessat.getHeader().getBytes(), 0, contingutFinal, 0, desprocessat.getHeader().getBytes().length);
                System.arraycopy(desprocessat.getContingut(), 0, contingutFinal, desprocessat.getHeader().getBytes().length, desprocessat.getContingut().length);
                if (pathGuardar != ".notvalid.") {
                    io.guardaImatge(pathGuardar, desprocessat.getHeader(), desprocessat.getContingut());
                }
                break;
            }
            case "LZW": {
                byte[] con = io.llegeixArxiuBinari(pathLlegir);
                contingutInicial = con;
                String contingut = new String(con);
                ArxiuTXT arxiuNormal = new ArxiuTXT(pathLlegir,contingut);
                LZW compressor = new LZW();
                ArxiuTXT comprimit = new ArxiuTXT();
                comprimit = compressor.comprimir(arxiuNormal);
                processat = comprimit;
                ArxiuTXT descomprimit = compressor.descomprimir(comprimit);
                contingutFinal = descomprimit.getContingut().getBytes();
                resultat = descomprimit;
                if (pathGuardar != ".notvalid.") {
                    io.guardaArxiuTXT(pathGuardar,descomprimit.getContingut(),false);
                }
                break;
            }
            case "LZSS": {
                String cont = io.llegeixArxiuTxt(pathLlegir);
                contingutInicial = cont.getBytes();
                ArxiuTXT arxiuNormal = new ArxiuTXT(pathLlegir, cont);
                LZSS compressor = new LZSS();
                ArxiuBytes comprimit = compressor.comprimir(arxiuNormal);
                processat = comprimit;
                ArxiuTXT descomprimit = compressor.descomprimir(comprimit);
                resultat = descomprimit;
                contingutFinal = descomprimit.getContingut().getBytes();
                if (pathGuardar != ".notvalid.") {
                    io.guardaArxiuTXT(pathGuardar,descomprimit.getContingut(),false);
                }
                break;
            }
            case "LZ78": {
                String con = io.llegeixArxiuTxt(pathLlegir);
                contingutInicial = con.getBytes();
                ArxiuTXT arxiuNormal = new ArxiuTXT(pathLlegir,con);
                LZ78 compressor = new LZ78();
                ArxiuBytes comprimit = compressor.comprimir(arxiuNormal);
                 processat = comprimit;
                ArxiuTXT descomprimit = compressor.descomprimir(comprimit);
                contingutFinal = descomprimit.getContingut().getBytes();
                resultat = descomprimit;
                if (pathGuardar != ".notvalid.") {
                    io.guardaArxiuTXT(pathGuardar,descomprimit.getContingut(),false);
                }
                break;
            }
            default : {
                
            }
        }
        double res[] = new double[3];
        res[0] = processat.getEstadistiques().getTemps_compressio() + resultat.getEstadistiques().getTemps_compressio();
        res[1] = processat.getEstadistiques().getPercentatge_compressio();
        res[2] = (processat.getEstadistiques().getVelocitat_compressio() +resultat.getEstadistiques().getVelocitat_compressio()) / 2.0 ;
        result.setContingutInicial(contingutInicial);
        result.setContingutFinal(contingutFinal);
        result.setEstadisticas(res);
    }

    /**
     * Funcion para obtener el resultado de la funcion executar
     * @return result(DTOComparar)
     */
    public DTOComparar getResult() {
        return result;
    }
}
