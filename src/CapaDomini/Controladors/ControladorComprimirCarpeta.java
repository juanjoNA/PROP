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
import Excepcions.CaracterNoASCII;
import Excepcions.DatosIncorrectos;
import Excepcions.ExtensionIncorrecta;
import Excepcions.VersionPPMIncorrecta;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ivgasa99
 */
public class ControladorComprimirCarpeta {
    private String path;
    private int alg_txt;
    private boolean guardar;
    private double[] result;
    
    public ControladorComprimirCarpeta(String path, int alg_txt, boolean guardar) {
        this.path = path;
        this.alg_txt = alg_txt;
        this.guardar = guardar;
        this.result = new double[3];
        
    }

    public double[] getResult() {
        return result;
    }
    
    private static byte[] TamToBytes(long size) {
        long itero = size;
        ArrayList<Byte> tam = new ArrayList<Byte>();
        while (itero >= 256) {
            itero = itero/256;
            byte b = (byte) (itero%256);
            tam.add(b);
        }
        tam.add((byte) itero);
        byte[] ret = new byte[tam.size()];
        for (int i = 0; i < tam.size(); ++i) {
            ret[i] = tam.get(tam.size()-1-i);
        }
        return ret;
    }
    
    private static ArrayList<String> Obte_paths(String path) throws IOException {
        ArrayList<String> paths = new ArrayList<String>();
        File f = new File(path);
        File [] ficheros = f.listFiles();
        for (int i = 0; i < ficheros.length; ++i) {
            String path_hijo = ficheros[i].getCanonicalPath();
            if (!ficheros[i].isDirectory()) {
                paths.add(path_hijo);
            }
            else paths.addAll(Obte_paths(path_hijo));
        }
        return paths;
    }
    
    public void executar() throws ExtensionIncorrecta, IOException, CaracterNoASCII, VersionPPMIncorrecta, DatosIncorrectos {
        File carpeta = new File(path);
        if (carpeta.isFile()) throw new ExtensionIncorrecta();
        ArrayList<String> paths = Obte_paths(path);
        IOArxius io = new IOArxius();
        String path_cc = path + ".carp";
        for (int i = 0; i < paths.size(); ++i) {
            String path_intern = paths.get(i);
            Arxiu resultat = new Arxiu();
            if (path_intern.contains(".txt")) {
                switch(alg_txt) {
                    //LZW
                    case 1: {
                        byte[] con = io.llegeixArxiuBinari(path_intern,".txt");
                        String contingut = new String(con);
                        ArxiuTXT b = new ArxiuTXT(path_intern,contingut);
                        LZW c = new LZW();
                        ArxiuTXT comprimit_lzw = c.comprimir(b);
                        resultat=comprimit_lzw;
                        if (guardar) {
                            long tamany_long = (comprimit_lzw.getContingut()).length();
                            byte[] tamany_bytes = TamToBytes(tamany_long);
                            String path_intern_comp = comprimit_lzw.getPath();
                            path_intern_comp = path_intern_comp.replaceAll(path, "");
                            io.guardaCabezeraArxiuCarpeta(path_cc,path_intern_comp,tamany_bytes);
                            io.guardaArxiuTXT(path_cc, comprimit_lzw.getContingut(),true);
                        }
                        break;
                    }
                        
                        //LZSS
                    case 2: {
                        byte[] cont = io.llegeixArxiuBinari(path_intern,".txt");
                        String contingut = new String(cont);
                        ArxiuTXT normal = new ArxiuTXT(path_intern, contingut);
                        LZSS lzss = new LZSS();
                        ArxiuBytes comprimit = lzss.comprimir(normal);
                        resultat=comprimit;
                        if(guardar) {
                            long tamany_long = (comprimit.getContingut()).length;
                            byte[] tamany_bytes = TamToBytes(tamany_long);
                            String path_intern_comp = comprimit.getPath();
                            path_intern_comp = path_intern_comp.replaceAll(path, "");
                            io.guardaCabezeraArxiuCarpeta(path_cc,path_intern_comp,tamany_bytes);
                            io.guardaArxiuBinari(path_cc, comprimit.getContingut(),true);
                        }
                        break;
                    }
                        
                    case 3: {
                        byte[] cont = io.llegeixArxiuBinari(path_intern, ".txt");
                        String contingut = new String(cont);
                        ArxiuTXT normal = new ArxiuTXT(path_intern,contingut);
                        LZ78 c = new LZ78();
                        ArxiuBytes comprimit = c.comprimir(normal);
                        resultat=comprimit;
                        if(guardar) {
                            long tamany_long = (comprimit.getContingut()).length;
                            byte[] tamany_bytes = TamToBytes(tamany_long);
                            String path_intern_comp = comprimit.getPath();
                            path_intern_comp = path_intern_comp.replaceAll(path, "");
                            io.guardaCabezeraArxiuCarpeta(path_cc,path_intern_comp,tamany_bytes);
                            io.guardaArxiuBinari(path_cc, comprimit.getContingut(),true);
                        } 
                        break;
                    }
                }
            }
            else if (path_intern.contains(".ppm")) {
                byte[] contingut = io.llegeixArxiuBinari(path,".ppm");
                Imatge imatgeLlegida = new Imatge(path,contingut);
                JPEG compressor = new JPEG();
                ImatgeComprimida comprimit = compressor.comprimir(imatgeLlegida);
                resultat = comprimit;
                if (guardar) {
                    io.guardarImatgeComprimida(comprimit.getPath(),comprimit.getDecoder(),comprimit.getHeader(),comprimit.getContingut());
                }
                break;
            }
            else throw new ExtensionIncorrecta();
            
            Estadistiques e = resultat.getEstadistiques();
            result[0] = result[0] + e.getTemps_compressio();
            result[1] = result[1] + e.getPercentatge_compressio();
            result[2] = result[2] + e.getVelocitat_compressio();
 
        }
        result[0] /= paths.size();
        result[1] /= paths.size();
        result[2] /= paths.size();
        
    }

}
