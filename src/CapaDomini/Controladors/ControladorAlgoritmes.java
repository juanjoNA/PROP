package CapaDomini.Controladors;

import java.util.ArrayList;

/**
 *
 * @author Juanjo
 */
public class ControladorAlgoritmes {
    
    private ArrayList<String> algoritmes = new ArrayList<>();

    /**
     * Construtora que se le pasa por parametro una extension de un archivo
     * @param extensio
     */
    public ControladorAlgoritmes(String extensio){
        if(extensio.equals("txt")){
            algoritmes.add("LZW");
            algoritmes.add("LZSS");
            algoritmes.add("LZ78");
        }else{
            algoritmes.add("JPEG");
        }
        algoritmes.add("Automatic");
    }
    
    /**
     * Funcion para obtener una lista de algoritmos
     * @return algoritmes(ArrayList<String>)
     */
    public ArrayList<String> getAlgoritmes(){
        return algoritmes;
    }
}
