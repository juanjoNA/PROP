package CapaDomini.ModelDomini;


/**
 *
 * @author Juanjo.Navarro
 */
public class Arxiu {

    private String path;
    private Estadistiques e;

    /**
     *
     */
    public Arxiu () {

    }

    /**
     * Constructora con un path
     * @param path
     */
    public Arxiu(String path) {
        this.path = path;
        this.e = new Estadistiques();
    }

    /**
     * Constructora con un path y unas estadisticas
     * @param path
     * @param e
     */
    public Arxiu(String path, Estadistiques e) {
        this.path = path;
        this.e = e;
    }

    /**
     * Funcion para obtener el path de un arxivo
     * @return path(String)
     */
    public String getPath() {
        return path;
    }

    /**
     * Funcioon para obtener las estadisticas
     * @return estadisticas(Estadistiques)
     */
    public Estadistiques getEstadistiques() {
        return e;
    }

    /**
     * Funcion para guardar el path de un archivo
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Funcion para guardar estadisticas del fichero
     * @param e
     */
    public void setEstadistiques(Estadistiques e) {
        this.e = e;
    }


}
