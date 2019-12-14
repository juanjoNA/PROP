package CapaPersistencia;


import CapaDomini.Controladors.ArxCarpetaComp;
import CapaDomini.Controladors.DTOImatge;
import Excepcions.ExtensionIncorrecta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
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
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
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

    public ArrayList<ArxCarpetaComp> llegeixCarpComp(String carpcomp) throws FileNotFoundException, IOException, ClassNotFoundException {
        File f = new File(carpcomp);
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        HashMap<String,Integer> readedHashMap;
        byte[] contingut;
        StringBuilder sb =new StringBuilder();
        ArrayList<ArxCarpetaComp> result = new ArrayList<>();
        byte b;
        while(fis.available() >0) {
            sb = new StringBuilder();
            b = ois.readByte();
            while (b != 10) {
                    sb.append((char) b);
                    b = ois.readByte();

            }
            String path = sb.toString();
            if (path.contains(".jimg")) {
                readedHashMap = (HashMap)ois.readObject();
                long fullLength = ois.readLong();
                contingut = new byte[(int)fullLength];
                ois.readFully(contingut);
                ArxCarpetaComp acc = new ArxCarpetaComp(path,contingut,readedHashMap);
                result.add(acc);
            }
            else {
                int tamany = ois.readInt();
                if (path.contains(".lzw")) {
                    int count = 0;
                    StringBuilder contchars=new StringBuilder();
                    while (count < tamany) {
                        char c = ois.readChar();
                        contchars.append(c);
                        ++count;
                    }
                    ArxCarpetaComp acc = new ArxCarpetaComp(path,contchars.toString());
                    result.add(acc);
                }
                else {
                    contingut = new byte[tamany];
                    ois.readFully(contingut);
                    ArxCarpetaComp acc = new ArxCarpetaComp(path,contingut);
                    result.add(acc);
                }
                b=ois.readByte();
            }
        }
        return result;
    }
    public ByteBuffer llegeixArxiuTXTCarpetaComprimida(File f) throws FileNotFoundException, IOException {
        ByteBuffer ret = ByteBuffer.allocate((int)f.length()); 
        FileInputStream fis = new FileInputStream(f);
        FileChannel fc = fis.getChannel();
        fc.read(ret);
        return ret; 
    }
    
    public void guardaPathRelatiuArxCarp(String path_cc,String path_intern_comp, boolean inici) throws IOException {
        File f = new File(path_cc);
        FileOutputStream o = new FileOutputStream(path_cc,true);
        if(!inici) {
            MyObjectOutputStream dos = new MyObjectOutputStream(o);
            dos.write(path_intern_comp.getBytes());
            dos.writeByte(10);
            dos.close();
            o.close();
        }
        else {
            ObjectOutputStream dos = new ObjectOutputStream(o);
            dos.write(path_intern_comp.getBytes());
            dos.writeByte(10);
            dos.close();
            o.close();
        }

    }
    
    public void guardaContBytesCarp(String path_cc,byte[] cont) throws IOException {
        File f = new File(path_cc);
        FileOutputStream o = new FileOutputStream(path_cc,true);
        MyObjectOutputStream dos = new MyObjectOutputStream(o);
        dos.write(cont);
        dos.writeByte(10);
        dos.close();
        o.close();
    }

    
    public void guardaContCharsCarp(String path_cc,String cont) throws IOException {
        File f = new File(path_cc);
        FileOutputStream o = new FileOutputStream(path_cc,true);
        MyObjectOutputStream dos = new MyObjectOutputStream(o);
        for (int i = 0; i < cont.length();++i) {
            dos.writeChar(cont.charAt(i));
        }
        dos.writeByte(10);
        dos.close();
        o.close();
    }
    
    public void guardaContImatgeCarp(String path_cc,HashMap<String,Integer> resultMap, String header, byte[] content) {
         FileOutputStream o = null;
         try {
             o = new FileOutputStream(path_cc,true);
             MyObjectOutputStream  oos = new MyObjectOutputStream (o);
             oos.writeObject(resultMap);
             long totalLength = header.getBytes().length + content.length;
             System.out.println(totalLength);
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
    
    
    public void guardaTamanyArxiuTXTCarpeta(String path_cc,int tamany_bytes) throws IOException {
        File f = new File(path_cc);
        FileOutputStream o = new FileOutputStream(path_cc,true);
        MyObjectOutputStream dos = new MyObjectOutputStream(o);
        dos.writeInt(tamany_bytes);
        dos.close();
        o.close();
    }
    
    public byte[] llegeixArxiuBinari(String path,String extensio) throws ExtensionIncorrecta {
        if ((!path.contains(".ppm") && extensio.contains(".ppm")) ||
                (!path.contains(".lzso") && extensio.contains(".lzso")) ||
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

    public void guardaArxiuBinari(String path, byte[] contingut) {

            OutputStream o = null;
         try {

             o = new FileOutputStream(path,true);
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
             o = new FileOutputStream(path,true);
             ObjectOutputStream  oos = new ObjectOutputStream (o);
             oos.writeObject(resultMap);
             long totalLength = header.getBytes().length + content.length;
             System.out.println(totalLength);
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

    public void guardaArxiuTXT(String path, String contingut) {

            BufferedWriter bw;
            try {
                bw = new BufferedWriter(new FileWriter(new File(path),true));
                bw.write(contingut);
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(IOArxius.class.getName()).log(Level.SEVERE, null, ex);
            }

    }





}
