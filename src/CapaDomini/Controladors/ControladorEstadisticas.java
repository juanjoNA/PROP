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
    private String[][] result;
    
    
    public ControladorEstadisticas() {
        est = new Estadistiques();
    }
    
    public void executar() throws Exception {
        result = getEstGeneral();
    }

    public String[][] getResult() {
        return result;
    }

    private String[][] getEstGeneral() throws Exception {
        return est.getEstadisticasMitjana();
    }

    private String getAutomatic() throws Exception {
        return est.getAuto();   
    }

}
