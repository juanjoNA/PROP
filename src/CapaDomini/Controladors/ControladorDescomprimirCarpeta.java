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
import Excepcions.ExtensionIncorrecta;
import Excepcions.VersionPPMIncorrecta;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.instrument.Instrumentation;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ivgasa99
 */
public class ControladorDescomprimirCarpeta {
    private String path;
    private boolean guardar;
    private double[] result;

    /**
     * Constructora con un path y un bool para guardar
     * @param path
     * @param guardar
     */
    public ControladorDescomprimirCarpeta (String path, boolean guardar) {
        this.path=path;
        this.guardar=guardar;
        this.result=new double[3];
    }

    /**
     * Funcion principal del controlador, descomprime una carpeta y deja las estadisticas del proceso en la variable result
     * @throws ExtensionIncorrecta
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws VersionPPMIncorrecta
     * @throws DatosIncorrectos
     * @throws Exception
     */
    public void executar() throws ExtensionIncorrecta, IOException, ClassNotFoundException, VersionPPMIncorrecta, DatosIncorrectos, Exception {
        if (!path.contains(".carp")) throw new ExtensionIncorrecta();
        IOArxius io = new IOArxius();
        Arxiu resultat = new Arxiu();
        File fi = new File(path);
        String carpdescomp = path.replace(".carp", "");
        File f = new File(carpdescomp);
        f.mkdir();
        if (fi.length()> 0) {
            ArrayList<ArxCarpetaComp> arxius = io.llegeixCarpComp(path);
            for (ArxCarpetaComp acc: arxius) {
                String path = acc.getPath();
                String path_file = carpdescomp+path;
                if (path.contains(".jimg")) {
                    ImatgeComprimida imatgeLlegida = new ImatgeComprimida(path,acc.getContingutBytes(),acc.getReadedHashMap());
                    JPEG compressor = new JPEG();
                        Imatge desprocessat = compressor.descomprimir(imatgeLlegida);
                        if (guardar) {
                            crearDirectoriosPadres(path_file,false);
                            path_file = path_file.replace(".jimg", ".ppm");
                            io.guardaImatge(path_file, desprocessat.getHeader(), desprocessat.getContingut());
                        }
                    resultat = desprocessat;
                    guardaEstadisticas("JPEG",desprocessat);
                }
                else if (path_file.contains(".lzw")) {
                    ArxiuTXT b = new ArxiuTXT(path_file,acc.getContingutChars());
                    LZW des = new LZW();
                    ArxiuTXT d = des.descomprimir(b);
                    if (guardar) {
                        crearDirectoriosPadres(path_file,false);
                        path_file = path_file.replace(".lzw", ".txt");
                        io.guardaArxiuTXT(path_file,d.getContingut(),true);
                    }
                    resultat = d;
                    guardaEstadisticas("LZW",d);
                }
                else if (path_file.contains(".lzss")) {
                    ArxiuBytes b = new ArxiuBytes(path_file,acc.getContingutBytes());
                    LZSS des = new LZSS();
                    ArxiuTXT d = des.descomprimir(b);
                    if (guardar) {
                        crearDirectoresPadres(path_file,false);
                        path_file = path_file.replace(".lzss", ".txt");
                        io.guardaArxiuTXT(path_file,d.getContingut(),true);
                    }
                    resultat = d;
                    guardaEstadisticas("LZSS",d);

                }
                else if (path_file.contains(".lz78")) {
                    ArxiuBytes b = new ArxiuBytes(path_file,acc.getContingutBytes());
                    LZ78 des = new LZ78();
                    ArxiuTXT d = des.descomprimir(b);
                    if (guardar) {
                        crearDirectoriosPadres(path_file,false);
                        path_file = path_file.replace(".lz78", ".txt");
                        io.guardaArxiuTXT(path_file,d.getContingut(),true);
                    }
                    resultat = d;
                    guardaEstadisticas("LZ78",d);

                }
                else {
                    crearDirectoresPadres(path_file,true);
                }                
            }
        }
    }
    /**
     * Funcion para crear en la carpeta descomprimida, los directores padres de cada fichero
     * @param path_file
     * @param carpeta
     */
    private void crearDirectoresPadres(String path_file, boolean carpeta) {
        File f_act = new File(path_file);
        ArrayList<String> subdirectorios = new ArrayList<>();
        while (!f_act.exists()) {
            int tamany_path_intern = path_file.length()-1;
            while(path_file.charAt(tamany_path_intern) != '/') --tamany_path_intern;
            String path_padre = path_file.substring(0, tamany_path_intern);
            String actual = path_file.substring(tamany_path_intern,path_file.length());
            subdirectorios.add(actual);
            f_act = new File(path_padre);
            path_file = path_padre;
        }
        for (int j = subdirectorios.size()-1; j >= 0; --j) {
            f = new File(path_file+subdirectorios.get(j));
            if (!carpeta & j != 0) f.mkdir();
            path_file += subdirectorios.get(j);
        }
    }
        /**
     * Funcion para guardar las estadisticas de cada compresion de archivos en disco 
     * @param alg
     * @param d
     * @throws Excepcion
     */
    private void guardaEstadisticas(String alg, Arxiu d) throws Exception {  
        Estadistiques e = d.getEstadistiques();
        result[0] = e.getTemps_compressio();
        result[1] = e.getPercentatge_compressio();
        result[2] = e.getVelocitat_compressio();
        e.guardaEst(result, alg, false);
    }

}
