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
import Excepcions.VersionPPMIncorrecta;
import java.io.IOException;
/**
 *
 * @author Juanjo.Navarro
 */
public class ControladorComprimir {
    private String path;
    private int algoritmo;
    private boolean guardar;
    private double[] result;

    public double[] getResult() {
        return result;
    }

    public ControladorComprimir (String path, int algoritmo, boolean guardar) {
        this.path = path;
        this.algoritmo = algoritmo;
        this.guardar = guardar;
        this.result = new double[3];
    }

    public void executar() throws VersionPPMIncorrecta,DatosIncorrectos, IOException, CaracterNoASCII {
        Arxiu resultat = null;
        IOArxius i = new IOArxius();
        switch(algoritmo) {
            //JPEG
            case 1:{
                byte[] contingut = i.llegeixArxiuBinari(path,".ppm");
                Imatge imatgeLlegida = new Imatge(path,contingut);
                JPEG compressor = new JPEG();
                ImatgeComprimida comprimit = compressor.comprimir(imatgeLlegida);
                resultat = comprimit;
                if (guardar) {
                    i.guardarImatgeComprimida(comprimit.getPath(),comprimit.getDecoder(),comprimit.getHeader(),comprimit.getContingut());
                }
                break;
            }
            //LZW
            case 2: {
                byte[] con = i.llegeixArxiuBinari(path,".txt");
                String contingut = new String(con);
                ArxiuTXT b = new ArxiuTXT(path,contingut);
                LZW c = new LZW();
                ArxiuTXT comprimit = new ArxiuTXT();
                comprimit = c.comprimir(b);
                resultat=comprimit;
                if (guardar) {
                    i.guardaArxiuTXT(comprimit.getPath(),comprimit.getContingut());
                }
                break;
            }

            //LZSS
            case 3: {
                String cont = i.llegeixArxiuTxt(path);
                ArxiuTXT normal = new ArxiuTXT(path, cont);
                LZSS lzss = new LZSS();
                ArxiuBytes comprimit = lzss.comprimir(normal);
                resultat=comprimit;
                if(guardar) i.guardaArxiuBinari(comprimit.getPath(), comprimit.getContingut());
                break;
            }
            
            //LZ78
            case 4: {
                byte[] cont = i.llegeixArxiuBinari(path, ".txt");
                ArxiuBytes normal = new ArxiuBytes(path,cont);
                LZ78 c = new LZ78();
                ArxiuBytes comprimit = c.comprimir(normal);
                resultat=comprimit;
                if(guardar) {
                    i.guardaArxiuBinari(comprimit.getPath(), comprimit.getContingut());
                
                break;
                }
            }
                
                
                

        }

        Estadistiques e = resultat.getEstadistiques();
        result[0] = e.getTemps_compressio();
        result[1] = e.getPercentatge_compressio();
        result[2] = e.getVelocitat_compressio();

    }


}
