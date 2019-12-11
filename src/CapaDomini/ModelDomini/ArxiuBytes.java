package CapaDomini.ModelDomini;

/**
 *
 * @author ivgasa99
 */
public class ArxiuBytes extends Arxiu{

    private byte[] contingut;

    public ArxiuBytes(String path, byte[] contingut) {
        super(path);
        this.contingut = contingut;
    }

    public ArxiuBytes(String path, byte[] contingut, Estadistiques e) {
        super(path, e);
        this.contingut = contingut;
    }

    public byte[] getContingut() {
        return contingut;
    }

    public void setContingut(byte[] contingut) {
        this.contingut = contingut;
    }



}
