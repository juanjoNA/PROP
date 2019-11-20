package CapaDomini.ModelDomini;


/**
 *
 * @author Juanjo.Navarro
 */
public class Arxiu {

    private String path;
    private Estadistiques e;

    public Arxiu () {

    }

    public Arxiu(String path) {
        this.path = path;
        this.e = new Estadistiques();
    }

    public Arxiu(String path, Estadistiques e) {
        this.path = path;
        this.e = e;
    }

    public String getPath() {
        return path;
    }

    public Estadistiques getEstadistiques() {
        return e;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setEstadistiques(Estadistiques e) {
        this.e = e;
    }


}
