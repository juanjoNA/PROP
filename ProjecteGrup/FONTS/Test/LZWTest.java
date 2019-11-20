package Test;

import Excepcions.CaracterNoASCII;
import Excepcions.ExtensionIncorrecta;
import org.junit.Test;
import static junit.framework.Assert.*;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;


/**
 *
 * @author ivgasa99
 */
public class LZWTest {

    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(LZWTest.class);
    }

    /**
     * Test of comprimir method, of class LZW.
     * This test check the compression of empty files
     */
    @Test
    public void testComprimirArxiuBuit() throws Exception {
        System.out.println("comprimir arxiu buit");
        ArxiuTXT b = new ArxiuTXT("a.txt","");
        LZW l = new LZW();
        boolean excepcio = false;
        ArxiuTXT processat = new ArxiuTXT();
        try {
          processat = l.comprimir(b);

        } catch (CaracterNoASCII ex) {
           excepcio=true;
        } catch (ExtensionIncorrecta ex) {
           excepcio=true;
        }

        assertEquals("a.lzw", processat.getPath());
        assertEquals("", processat.getContingut());

    }

    /**
     * Test of comprimir method, of class LZW.
     * This test check the compression of txt files only
     */
    @Test
    public void testComprimirArxiuNoTXT() throws Exception {
        System.out.println("comprimir arxiu no txt");
        ArxiuTXT b = new ArxiuTXT("a.lzw","");
        LZW l = new LZW();
        boolean excepcio = false;
        try {
          ArxiuTXT processat = l.comprimir(b);

        } catch (CaracterNoASCII ex) {
           excepcio=true;
        } catch (ExtensionIncorrecta ex) {
           excepcio=true;
        }
        assertEquals(excepcio,true);
    }

    /**
     * Test of comprimir method, of class LZW.
     * This test check the compression of files which only contains ASCII characters
     */
    @Test
    public void testComprimirArxiuAmbContNoASCII() throws Exception {
        System.out.println("comprimir arxiu amb contingut no ascii");
        ArxiuTXT b = new ArxiuTXT("/etc/a.txt","ǵ");
        LZW l = new LZW();
        boolean excepcio=false;
        try {
          ArxiuTXT processat = l.comprimir(b);

        } catch (CaracterNoASCII ex) {
           excepcio=true;
        } catch (ExtensionIncorrecta ex) {
           excepcio=true;
        }

        assertEquals(excepcio,true);
    }

    /**
     * Test of comprimir method, of class LZW.
     * This test check the correctness of the LZW compression algorithm
     */
    @Test
    public void testComprimirArxiuCorrectes() throws Exception {
        System.out.println("comprimir arxiu amb contingut no ascii");
        ArxiuTXT b = new ArxiuTXT("/etc/a.txt","hola hola hola hola hola hola hola hola");
        Arxiu processat = new Arxiu ();
        LZW l = new LZW();
        boolean excepcio=false;
        try {
          processat = l.comprimir(b);

        } catch (CaracterNoASCII ex) {
           excepcio=true;
        } catch (ExtensionIncorrecta ex) {
           excepcio=true;
        }
        ArxiuTXT comprimit = (ArxiuTXT) processat;
        assertEquals("/etc/a.lzw", comprimit.getPath());
        assertEquals("hola ĀĂĄāăąĉĈćĆĊča", comprimit.getContingut());
    }
    /**
     * Test of descomprimir method, of class LZW.
     * This test check the descompression of empty files
     */
    @Test
    public void testDescomprimirArxiuBuit() {
        System.out.println("descomprimir arxiu buit");
        ArxiuTXT b = new ArxiuTXT("/etc/a.lzw","");
        Arxiu processat = new Arxiu ();
        LZW l = new LZW();
        boolean excepcio = false;
        try {
          processat = l.descomprimir(b);

        } catch (ExtensionIncorrecta ex) {
           excepcio=true;
        }
        ArxiuTXT comprimit = (ArxiuTXT) processat;
        assertEquals("/etc/a(2).txt", comprimit.getPath());
        assertEquals("", comprimit.getContingut());
    }
    /**
     * Test of descomprimir method, of class LZW.
     * This test check the descompression of only lzw files
     */
    @Test
    public void testDescomprimirArxiuNoLZW() {
        System.out.println("descomprimir arxiu no LZW");
        ArxiuTXT b = new ArxiuTXT("/etc/a.txt","");
        LZW l = new LZW();
        boolean excepcio = false;
        try {
          ArxiuTXT processat = l.descomprimir(b);

        } catch (ExtensionIncorrecta ex) {
           excepcio=true;
        }
        assertEquals(excepcio, true);
    }

    /**
     * Test of comprimir method, of class LZW.
     * This test check the correctness of the LZW descompression algorithm
     */
    @Test
    public void testDescomprimirArxiuCorrectes() throws Exception {
        System.out.println("comprimir arxiu amb contingut no ascii");
        ArxiuTXT b = new ArxiuTXT("/etc/a.lzw","hola ĀĂĄāăąĉĈćĆĊča");
        LZW l = new LZW();
        boolean excepcio=false;
        ArxiuTXT processat = new ArxiuTXT();
        try {
          processat = l.descomprimir(b);

        } catch (ExtensionIncorrecta ex) {
           excepcio=true;
        }
        assertEquals("/etc/a(2).txt", processat.getPath());
        assertEquals("hola hola hola hola hola hola hola hola", processat.getContingut());
    }

}
