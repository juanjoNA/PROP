/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.ModelDomini;

/**
 *
 * @author Lucas.pinilla
 */
public class EstadistiquesAlg extends Estadistiques {

    private int num_compressions;
    private int num_descompressions;

    public EstadistiquesAlg(){
        super();
        this.num_compressions = 0;
        this.num_descompressions = 0;
    }
    public EstadistiquesAlg(double vel, long temps, double perc) {
        super(vel, temps, perc);
        this.num_compressions = 0;
        this.num_descompressions = 0;
    }

    public EstadistiquesAlg(int num_compressions, int num_descompressions) {
        super();
        this.num_compressions = num_compressions;
        this.num_descompressions = num_descompressions;
    }

    public EstadistiquesAlg(int num_compressions, int num_descompressions, double vel, long temps, double perc) {
        super(vel, temps, perc);
        this.num_compressions = num_compressions;
        this.num_descompressions = num_descompressions;
    }

    public int getNum_compressions() {
        return num_compressions;
    }

    public void setNum_compressions(int num_compressions) {
        this.num_compressions = num_compressions;
    }

    public int getNum_descompressions() {
        return num_descompressions;
    }

    public void setNum_descompressions(int num_descompressions) {
        this.num_descompressions = num_descompressions;
    }

    public void aumentaCompressions(){
        this.num_compressions++;
    }

    public void aumentaDescompressions(){
        this.num_descompressions++;
    }
}
