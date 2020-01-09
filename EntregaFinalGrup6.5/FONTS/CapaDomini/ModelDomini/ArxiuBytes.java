package CapaDomini.ModelDomini;

/**
 *
 * @author ivgasa99
 */
public class ArxiuBytes extends Arxiu{

    private byte[] contingut;

    /**
     * Constructora con un path y contenido
     * @param path
     * @param contingut
     */
    public ArxiuBytes(String path, byte[] contingut) {
        super(path);
        this.contingut = contingut;
    }

    /**
     * Constructora con un path, un contenido y unas estadisticas
     * @param path
     * @param contingut
     * @param e
     */
    public ArxiuBytes(String path, byte[] contingut, Estadistiques e) {
        super(path, e);
        this.contingut = contingut;
    }

    /** 
     * Funcion para obtener el contenido de un ArxiuBytes
     * @return contingut(byte[])
     */
    public byte[] getContingut() {
        return contingut;
    }

    /**
     * Funcion para guardar contenido de un ArxiuBytes
     * @param contingut
     */
    public void setContingut(byte[] contingut) {
        this.contingut = contingut;
    }



}
