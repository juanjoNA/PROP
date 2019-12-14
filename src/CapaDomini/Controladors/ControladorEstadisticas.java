/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.Controladors;

import CapaDomini.ModelDomini.Estadistiques;
import CapaPersistencia.EstadisticasDisc;
import org.json.simple.parser.ParseException;

/**
 *
 * @author lucas
 */
public class ControladorEstadisticas {
    //private String path;
    private EstadisticasDisc est;
    private boolean guardar;
    private boolean comprimir;
    private String algoritmo;
    private long[] e;
    
    public ControladorEstadisticas() throws Exception {
        est = new EstadisticasDisc();
        guardar = false;
    }
    
    public ControladorEstadisticas(double[] estad, boolean comprimir, String algoritmo)throws Exception {
        est = new EstadisticasDisc();
        this.guardar = true;
        this.comprimir = comprimir;
        this.algoritmo = algoritmo;
        this.e = new long[3];
        this.e[0] = Double.valueOf(estad[0]).longValue();
        this.e[1] = Double.valueOf(estad[1]).longValue();
        this.e[2] = Double.valueOf(estad[2]).longValue();
    }
    
    public String[][] executar() throws Exception {
        if(!guardar) return getEstGeneral();
        else if(comprimir) guardarEstadisticasComp(e, algoritmo);
        else guardarEstadisticasDescomp(e, algoritmo);
        return null;
    }
    
    private void guardarEstadisticasComp(long[] a, String algoritmo) throws ParseException, Exception {
        est.writeEstCompressio(a[0],a[1],a[2],algoritmo);
}
    
    private void guardarEstadisticasDescomp(long[] a, String algoritmo) throws ParseException, Exception {
        est.writeEstDescompressio(a[0],a[1],a[2],algoritmo);
}
    
    private double[] getEstadisticas(String algoritmo) {
        return est.readEstDisc(algoritmo);
    }
    
    private String[][] getEstGeneral() throws Exception {
        Estadistiques e = new Estadistiques();
        return e.getEstadisticasMitjana();
    }
    
}