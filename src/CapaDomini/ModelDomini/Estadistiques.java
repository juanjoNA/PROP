/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.ModelDomini;

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

}
