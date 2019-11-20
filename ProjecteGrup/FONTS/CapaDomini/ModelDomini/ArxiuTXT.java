package CapaDomini.ModelDomini;

/**
 *
 * @author Juanjo.Navarro
 */
public class ArxiuTXT extends Arxiu {
    String contingut_chars;

    public ArxiuTXT() {
        
    }
    
    public ArxiuTXT(String path, String contingut) {
        super(path);
        this.contingut_chars = contingut;
    }

    public ArxiuTXT(String path, String contingut, Estadistiques e) {
        super(path, e);
        this.contingut_chars = contingut;
    }

    public String getContingut() {
        return contingut_chars;
    }

    public void setContingut(String s) {
        this.contingut_chars = s;
    }

}
