package CapaDomini.Controladors;

import java.util.ArrayList;

/**
 *
 * @author Juanjo
 */
public class ControladorAlgoritmes {
    
    ArrayList<String> algoritmes = new ArrayList<>();
    public ControladorAlgoritmes(String extensio){
        if(extensio.equals("txt")){
            algoritmes.add("LZW");
            algoritmes.add("LZSS");
            algoritmes.add("LZ78");
        }else{
            algoritmes.add("JPEG");
        }
        algoritmes.add("Automàtic");
    }
    
    public ArrayList<String> getAlgoritmes(){
        return algoritmes;
    }
}
