
package Drivers;

import CapaDomini.ModelDomini.ArxiuTXT;
import CapaDomini.ModelDomini.Arxiu;
import CapaDomini.ModelDomini.LZW;
import CapaPersistencia.IOArxius;
import Excepcions.CaracterNoASCII;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ivgasa99
 */
public class DriverLZW {

    private static BufferedReader intro = new BufferedReader(new InputStreamReader(System.in));

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int opcion;
        boolean salir=false;

        while(!salir){
            System.out.println("Selecciona una opció: ");
            System.out.println("\n\t1. Descomprimir Arxiu."
                         + "\n\t2. Comprimir Arxiu."
                         + "\n\t3. Salir.");

            try{
                opcion = Integer.parseInt(intro.readLine());
            }catch(NumberFormatException ne){
                opcion = 0;
            }
            String path;
            Arxiu a;
            String contingut;
            ArxiuTXT b;
            String guardar;
            switch(opcion) {
                case 1: {
                    System.out.println("Introdueix el path de l'arxiu: ");
                    path = intro.readLine();
                    System.out.println("Vols guardar? S/N: ");
                    guardar = intro.readLine();

                    contingut = llegeix_arxiu(path,".lzw");
                    b = new ArxiuTXT(path,contingut);
                    ArxiuTXT processat = descomprimeix(b);
                    System.out.println("Descomprimit amb exit!");
                    if(guardar.equals("S")) {
                        IOArxius i = new IOArxius();
                        i.guardaArxiuTXT(processat.getPath(), processat.getContingut());
                    }
                    break;


                }

                case 2: {
                    System.out.println("Introdueix el path de l'arxiu: ");
                    path = intro.readLine();
                    System.out.println("Vols guardar? S/N: ");
                    guardar = intro.readLine();

                        try {
                            contingut = llegeix_arxiu(path,".txt");
                            b = new ArxiuTXT(path,contingut);
                            ArxiuTXT processat = comprimeix(b);
                            System.out.println("Comprimit amb exit!");
                            if(guardar.equals("S")) {
                            guardar(processat);
                        }
                            break;
                        } catch (CaracterNoASCII ex) {
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

    static String llegeix_arxiu (String path,String extensio){
        IOArxius c;
        c = new IOArxius();
        byte[] con = c.llegeixArxiuBinari(path,extensio);
        String s = new String(con);
        return s;
    }

    static ArxiuTXT comprimeix(ArxiuTXT a) throws CaracterNoASCII{

        LZW c = new LZW();
        try {
            ArxiuTXT comprimit = c.comprimir(a);
            return comprimit;
        } catch (CaracterNoASCII ex) {
            throw new CaracterNoASCII();
        }
    }

    static ArxiuTXT descomprimeix(ArxiuTXT a) {

        LZW c = new LZW();
        ArxiuTXT descomprimit = c.descomprimir(a);
        return descomprimit;
    }

    static void guardar(ArxiuTXT comprimit) {
        IOArxius c = new IOArxius();
        String newpath = comprimit.getPath();
        String newcontingut = comprimit.getContingut();
        c.guardaArxiuTXT(newpath,newcontingut,false);
    }

}
