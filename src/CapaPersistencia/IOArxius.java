package CapaPersistencia;

import CapaDomini.Controladors.DTOImatge;
import Excepcions.ExtensionIncorrecta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Juanjo.Navarro
 */
public class IOArxius {

    public IOArxius() {
    }
    
    public void guardaCabezeraArxiuCarpeta(String path_cc,String path_intern_comp,byte[] tamany_bytes) throws IOException {
        File f = new File(path_cc);
        OutputStream o = new FileOutputStream(path_cc,true);
        o.write(path_intern_comp.getBytes());
        o.write('\n');
        o.write(tamany_bytes);
        o.write('\n');
        o.close();
    }
    public byte[] llegeixArxiuBinari(String path,String extensio) throws ExtensionIncorrecta {
        if ((!path.contains(".ppm") && extensio.contains(".ppm")) ||
                (!path.contains(".lz78") && extensio.contains(".lz78")) ||
                (!path.contains(".lzss") && extensio.contains(".lzss")) ||
                (!path.contains(".lzw") && extensio.contains(".lzw")) ||
                 (extensio.contains("driver"))) {
            throw new ExtensionIncorrecta();
        }

         try {
             InputStream is = null;
             File file = new File(path);
             is = new FileInputStream(file);
             long length = file.length();
             byte[] content = new byte[(int) length];
             is.read(content);
             is.close();
             return content;
         } catch (FileNotFoundException ex) {
             Logger.getLogger(IOArxius.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(IOArxius.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;

    }

    public void guardaArxiuBinari(String path, byte[] contingut, boolean append) {

            OutputStream o = null;
         try {

             o = new FileOutputStream(path,append);
             o.write(contingut);
             //o.write('\n');
             o.close();
           } catch (FileNotFoundException ex) {
               Logger.getLogger(IOArxius.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IOException ex) {
               Logger.getLogger(IOArxius.class.getName()).log(Level.SEVERE, null, ex);
           } finally {
               try {
                   o.close();
               } catch (IOException ex) {
                   Logger.getLogger(IOArxius.class.getName()).log(Level.SEVERE, null, ex);
               }
           }


      }


    public void guardaImatge(String path, String header, byte[] content) throws IOException {
        OutputStream os = new FileOutputStream(path);
        os.write(header.getBytes());
        os.write(content);
        os.close();
    }

    public void guardarImatgeComprimida(String path, HashMap<String,Integer> resultMap, String header, byte[] content) {
         FileOutputStream o = null;
         try {
             System.out.println(path);
             o = new FileOutputStream(path);
             ObjectOutputStream  oos = new ObjectOutputStream (o);
             oos.writeObject(resultMap);
             long totalLength = header.getBytes().length + content.length;
             oos.writeLong(totalLength);
             oos.write(header.getBytes());
             oos.write(content);
             oos.close();
             o.close();
         } catch (FileNotFoundException ex) {
             Logger.getLogger(IOArxius.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(IOArxius.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    public DTOImatge llegeixImatgeComprimida(String path) throws ExtensionIncorrecta {
        if (!path.contains(".jimg")) {
            throw new ExtensionIncorrecta();
        }
        byte[] result = null;
        DTOImatge resultDTO = null;
        try{
            File file = new File(path);
            FileInputStream fs = new FileInputStream(file);
            ObjectInputStream  os = new ObjectInputStream (fs);
            HashMap<String,Integer> readedHashMap = (HashMap)os.readObject();
            long fullLength = os.readLong();
            result = new byte[(int)fullLength];
            os.readFully(result);
            os.close();
            fs.close();
            resultDTO = new DTOImatge(readedHashMap, result);
        } catch (FileNotFoundException ex) {
             Logger.getLogger(IOArxius.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(IOArxius.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ClassNotFoundException ex) {
            Logger.getLogger(IOArxius.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultDTO;
    }

    public String llegeixArxiuTxt(String path) {
        FileReader fr=null;
        String s="";
        StringBuilder sb = new StringBuilder();
         try {
             fr = new FileReader(path);
             BufferedReader br = new BufferedReader(fr);
             while ((s =br.readLine()) != null){
                 sb.append(s);
                 sb.append("\n");
             }
             fr.close();
         } catch (FileNotFoundException ex) {
             Logger.getLogger(IOArxius.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(IOArxius.class.getName()).log(Level.SEVERE, null, ex);
         } catch (Exception e) {
             e.printStackTrace();
         }

         return sb.toString();
    }

    public void guardaArxiuTXT(String path, String contingut, boolean append) {

            BufferedWriter bw;
            try {
                bw = new BufferedWriter(new FileWriter(new File(path),append));
                bw.write(contingut);
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(IOArxius.class.getName()).log(Level.SEVERE, null, ex);
            }

    }





}
