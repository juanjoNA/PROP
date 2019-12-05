
package Drivers;

import CapaDomini.ModelDomini.ArxiuTXT;
import CapaDomini.ModelDomini.Arxiu;
import CapaDomini.ModelDomini.ArxiuBytes;
import CapaDomini.ModelDomini.LZ78;
import CapaPersistencia.IOArxius;
import Excepcions.CaracterNoASCII;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class DriverLZ78 {
    
    private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, CaracterNoASCII {
        int opcion;
        boolean salir=false;
        IOArxius ioa = new IOArxius();
        
        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. Descomprimir Arxiu."
                         + "\n\t2. Comprimir Arxiu."
                         + "\n\t3. Salir.");
            
            opcion = Integer.parseInt(intro.readLine());
            String path;
            String guardar;
            Arxiu a;
            byte[] contingut;
            ArxiuBytes comprimit, normal;
            ArxiuTXT descomprimit;

            switch(opcion) {
                case 1: {
                    System.out.println("Introdueix el path de l'arxiu: ");
                    path = intro.readLine();
                    System.out.println("Vols guardar arxiu a disc \n S/N: ");
                    guardar = intro.readLine();
                    boolean ext_corr = path.contains(".lz78");
                    if (!ext_corr) {
                        System.out.println("L'arxiu no té l'extensió lz78!");
                        break;
                    }
                    contingut = llegeix_arxiu(path,".lz78");
                    comprimit = new ArxiuBytes(path,contingut);
                    descomprimit = LZ78.descomprimir(comprimit);
                    if (guardar.equals("S")) ioa.guardaArxiuTXT(descomprimit.getPath(),descomprimit.getContingut());
                
                    

                    break;
                }
                case 2: {
                    System.out.println("Introdueix el path de l'arxiu: ");
                    path = intro.readLine();
                    System.out.println("Vols guardar arxiu a disc \n S/N: ");
                    guardar = intro.readLine();
                try {
                    contingut = llegeix_arxiu(path,".txt");
                    normal = new ArxiuBytes(path,contingut);
                    comprimit = LZ78.comprimir(normal);
                    if (guardar.equals("S")) ioa.guardaArxiuBinari(comprimit.getPath(), comprimit.getContingut());
                 break;
                }
                catch (CaracterNoASCII ex) {
                     System.out.println("L'arxiu conté caracters no ASCII!");
                }
                finally {    
                    break;
                }
   
                }
                case 3: 
                    salir = true;
                    break;
                default: 
                    System.out.println("Introdueix una opció correcta");
                    break;
                
            }
            
        }
    }
    
    static byte[] llegeix_arxiu (String path,String extensio) {
        IOArxius c;
        c = new IOArxius();
        byte[] con = c.llegeixArxiuBinari(path,extensio);
        return con;
    }
}
