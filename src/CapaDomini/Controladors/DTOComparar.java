/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.Controladors;

/**
 *
 * @author paumu
 */
public class DTOComparar {
    
    private byte[] contingutInicial;
    private byte[] contingutFinal;
    private double[] estadistiques;
    
    public DTOComparar(byte[] contingutInicial, byte[] contingutFinal, double[] estadistiques) {
        this.contingutInicial = contingutInicial;
        this.contingutFinal = contingutFinal;
        this.estadistiques = estadistiques;
    }
    
    public byte[] getContingutInicial() {
        return contingutInicial;
    }

    public byte[] getContingutFinal() {
        return contingutFinal;
    }

    public double[] getEstadistiques() {
        return estadistiques;
    }
}
