package CapaDomini.ModelDomini;

/**
 *
 * @author Juanjo.Navarro
 */
public class ArxiuTXT extends Arxiu {
    String contingut_chars;

    /**
     * Constructora por defecto
     */
    public ArxiuTXT() {
        
    }
    
    /**
     * Constructora con un path y un contenido
     * @param path
     * @param contingut
     */
    public ArxiuTXT(String path, String contingut) {
        super(path);
        this.contingut_chars = contingut;
    }

    /**
     * Constructora con un path, un contenido y unas estadisticas
     * @param path
     * @param contingut
     * @param e
     */
    public ArxiuTXT(String path, String contingut, Estadistiques e) {
        super(path, e);
        this.contingut_chars = contingut;
    }

    /** 
     * Funcion para obtener el contenido del fichero ArxiuTXT
     * @return contingut_chars(String)
     */
    public String getContingut() {
        return contingut_chars;
    }

    /**
     * Funcion para guardar el contenido del ArxiuTXT
     * @param s
     */
    public void setContingut(String s) {
        this.contingut_chars = s;
    }

}
