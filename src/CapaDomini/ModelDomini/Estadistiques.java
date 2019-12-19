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

    /**
     *
     */
    public Estadistiques() {

    }

    /**
     * Contructora con el tiempo de start, el final, el tamaño inicial y el tamaño final del arxivo
     * @param start
     * @param end
     * @param tamIni
     * @param tamFi
     */
    public Estadistiques(long start, long end, int tamIni, int tamFi){
        this.percentatge_compressio = Math.abs(((float)(tamFi-tamIni)/(float)tamIni)) * 100;
        this.temps_compressio = (end-start);
        this.velocitat_compressio = ((float)tamIni/(float)(end-start))/1000;
    }

    /**
     * Constructora con la velocidad, el tiempo y el porcentage de compression
     * @param vel
     * @param temps
     * @param perc
     */
    public Estadistiques(double vel, double temps, double perc){
        this.velocitat_compressio = vel;
        this.temps_compressio = temps;
        this.percentatge_compressio = perc;
    }

    /**
     * Funcion para obtener la velocidad de compression
     * @return velocitat_compressio(double)
     */
    public double getVelocitat_compressio() {
        return velocitat_compressio;
    }

    /**
     * Funcion para guardar una velocidad de compression
     * @param velocitat_compressio
     */
    public void setVelocitat_compressio(double velocitat_compressio) {
        this.velocitat_compressio = velocitat_compressio;
    }

    /**
     * Funcion para obtener la tiempo de compression
     * @return tiempo_compressio(double)
     */
    public double getTemps_compressio() {
        return temps_compressio;
    }

    /**
     * Funciomn para guardar el tiempo de compression
     * @param temps_compressio
     */
    public void setTemps_compressio(double temps_compressio) {
        this.temps_compressio = temps_compressio;
    }

    /**
     * Funcion para obtener el porcentage de compression
     * @return percentatge_compressio(double)
     */
    public double getPercentatge_compressio() {
        return percentatge_compressio;
    }

    /**
     * Funcion para guardar un porcentage de compression
     * @param percentatge_compressio
     */
    public void setPercentatge_compressio(double percentatge_compressio) {
        this.percentatge_compressio = percentatge_compressio;
    }

    /**
     * Funciom para obtener las estadisticas
     * @return est(double[])
     */
    public double[] getEstadistiques() {
        double[] est = new double[3];
        est[0] = velocitat_compressio;
        est[1] = temps_compressio;
        est[2] = percentatge_compressio;
        return est;
    }
    
    /**
     * Funcion para obtener el algoritmo con mejor ratio de porcentage de compression
     * @return algorithm(String)
     * @throws Exception
     */
    public String getAuto() throws Exception {
        EstadisticasDisc estd = new EstadisticasDisc();
        return estd.getBestAlgorithm();
    }

    /**
     * Funcion para guardar estadisticas en disco
     * @param result
     * @param algorithm
     * @param comp
     * @throws ParseException
     * @throws Exception
     */
    public void guardaEst(double[] result, String algorithm, boolean comp) throws ParseException, Exception {
        EstadisticasDisc estd = new EstadisticasDisc();
        if(comp) estd.writeEstCompressio(result[0], result[1], result[2], algorithm);
        else estd.writeEstDescompressio(result[0], result[1], result[2], algorithm);
    }

    /**
     * Funcion para obtener las estadisticas guardadas en disco
     * @return
     * @throws Exception
     */
    public String[][] getEstadisticasMitjana() throws Exception {
        EstadisticasDisc estd = new EstadisticasDisc();
        String[] alg = estd.getalgoritmos();
        String[][] res = new String[alg.length+1][9];
        res[0] = estd.getnomEst();
        for(int i = 0; i < alg.length; i++) {
            double[] ealg = estd.readEstDisc(alg[i]);
            res[i+1] = canviarformato(alg[i],ealg);
        }
        return res;
    }

    private String[] canviarformato(String algorithm, double[] ealg) {
        String[] res = new String[9];
        res[0] = algorithm;
        res[1] = String.format("%.2f", ealg[0]) + uvel(ealg[0]);
        res[2] = String.format("%.2f", ealg[1])+ "%";
        res[3] = String.format("%.2f", ealg[2]) + utiempo(ealg[2]);
        res[4] = String.format("%.2f", ealg[3])+ uvel(ealg[3]);
        res[5] = String.format("%.2f", ealg[4]) + "%";
        res[6] = String.format("%.2f", ealg[5])+ utiempo(ealg[5]);
        res[7] = String.format("%.0f", ealg[6]);
        res[8] = String.format("%.0f", ealg[7]);
        return res;
    }
     /**
     * Funcion para ajustar unidades de velocidad segun su magnitud
     * @param d
     * @return unidad(String)
     */
    private String uvel(double d) {
        String unidad= new String();
        if(d > 1000) {
            d = d/1000;
            unidad = "MB/s";
            return unidad;
        }
        unidad = "KB/s";
        return unidad;
    }
     /**
     * Funcion para ajustar unidades de tiempo segun su magnitud
     * @param d
     * @return unidad(String)
     */
    private String utiempo(double d) {
        String unidad= new String();
        if(d > 1000) {
            d = d/1000;
            unidad = "s";
            return unidad;
        }
        unidad = "ms";
        return unidad;
    }

}
