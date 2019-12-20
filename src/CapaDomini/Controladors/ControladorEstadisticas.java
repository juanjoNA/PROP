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
    
    /**
     * Constructora por defecto
     */
    public ControladorEstadisticas() {
        est = new Estadistiques();
    }
    
    /**
     * Funcion principal, devuelve estadisticas generales de todo el programa en la variable result
     * @throws Exception
     */
    public void executar() throws Exception {
        result = getEstGeneral();
    }

    /**
     * Funcion para obtener la variable result
     * @return restult(String[][])
     */
    public String[][] getResult() {
        return result;
    }

    private String[][] getEstGeneral() throws Exception {
        return est.getEstadisticasMitjana();
    }

    /**
     * Funcion que devuelve el mejor algoritmo en cuanto a ratio de compression 
     * @return auto((String)
     * @throws Exception
     */
    public String getAutomatic(String path) throws Exception {
        if(path.endsWith(".ppm")) return "JPEG";
        return est.getAuto();   
    }

}
