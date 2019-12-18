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
    private Estadistiques est;
    private boolean auto;
    private long[] e;
    
    public ControladorEstadisticas() {
        est = new Estadistiques();
        this.auto = false;
    }
    
    public ControladorEstadisticas(boolean auto) {
        est = new Estadistiques();
        this.auto = auto;  
    }
    
    public String[][] executar() throws Exception {
        if(!auto) return getEstGeneral();
        else return getAutomatic();
    }
    
    private String[][] getEstGeneral() throws Exception {
        return est.getEstadisticasMitjana();
    }

    private String[][] getAutomatic() throws Exception {
        String auto[][] = new String[1][1];
        auto[0][0] = est.getAuto();
        return auto;   
    }
    
}
