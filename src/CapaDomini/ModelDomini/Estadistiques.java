/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.ModelDomini;

import CapaPersistencia.EstadisticasDisc;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Lucas.Pinilla
 */
public class Estadistiques {
    private double velocitat_compressio;
    private double temps_compressio;
    private double percentatge_compressio;

    public Estadistiques() {
        
    }

    public Estadistiques(long start, long end, int tamIni, int tamFi){
        this.percentatge_compressio = Math.abs(1-((float)tamFi/(float)tamIni)) * 100;
        this.temps_compressio = (end-start);
        this.velocitat_compressio = ((float)tamIni/(float)(end-start))/1000;
    }
    
    public Estadistiques(double vel, long temps, double perc){
        this.velocitat_compressio = vel;
        this.temps_compressio = temps;
        this.percentatge_compressio = perc;
    }
    
    public double getVelocitat_compressio() {
        return velocitat_compressio;
    }

    public void setVelocitat_compressio(double velocitat_compressio) {
        this.velocitat_compressio = velocitat_compressio;
    }


    public double getTemps_compressio() {
        return temps_compressio;
    }

    public void setTemps_compressio(double temps_compressio) {
        this.temps_compressio = temps_compressio;
    }

    public double getPercentatge_compressio() {
        return percentatge_compressio;
    }

    public void setPercentatge_compressio(double percentatge_compressio) {
        this.percentatge_compressio = percentatge_compressio;
    }

    public double[] getEstadistiques() {
        double[] est = new double[3];
        est[0] = velocitat_compressio;
        est[1] = temps_compressio;
        est[2] = percentatge_compressio;
        return est;
    }

    public void guardaEst(double[] result, String algorithm, boolean comp) throws ParseException, Exception {
        EstadisticasDisc estd = new EstadisticasDisc();
        if(comp) estd.writeEstCompressio(result[0], result[1], result[2], algorithm);
        else estd.writeEstDescompressio(result[0], result[1], result[2], algorithm);
    }

    public String[][] getEstadisticasMitjana() throws Exception {
        EstadisticasDisc estd = new EstadisticasDisc();
        String[] alg = estd.getalgoritmos();
        String[][] res = new String[alg.length][6];
        for(int i = 0; i < alg.length; i++) {
            double[] ealg = estd.readEstDisc(alg[i]);
            res[i] = canviarformato(alg[i],ealg);
        }
        return res;
    }

    private String[] canviarformato(String algorithm, double[] ealg) {
        String[] res = new String[7];
        res[0] = algorithm;
        res[1] = Double.toString(ealg[0]) + uvel(ealg[0]);
        res[2] = Double.toString(ealg[1]) + utiempo(ealg[1]);
        res[3] = Double.toString(ealg[2]) + "%";
        res[4] = Double.toString(ealg[3]) + uvel(ealg[3]);
        res[5] = Double.toString(ealg[4]) + utiempo(ealg[4]);
        res[6] = Double.toString(ealg[5]) + "%";
        return res;
    }

    private String uvel(double d) {
        if(d > 1000) {
            d = d/1000;
            return "MB/s";
        }
        return "KB/s";
    }

    private String utiempo(double d) {
        if(d > 1000) {
            d = d/1000;
            return "s";
        }
        return "ms";
    }

}