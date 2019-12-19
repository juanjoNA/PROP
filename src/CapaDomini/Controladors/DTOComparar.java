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
    
    /**
     * Constructora con un contenido inicial, un contenido final y unas estadisticas
     * @param contingutInicial
     * @param contingutFinal
     * @param estadistiques
     */
    public DTOComparar(byte[] contingutInicial, byte[] contingutFinal, double[] estadistiques) {
        this.contingutInicial = contingutInicial;
        this.contingutFinal = contingutFinal;
        this.estadistiques = estadistiques;
    }

    DTOComparar() {
    }
    
    /**
     * Funcion para obtener el contingut inicial
     * @return contingutInicial(byte[])
     */
    public byte[] getContingutInicial() {
        return contingutInicial;
    }

    /**
     * Funcion para obtener el contenido final
     * @return contingutFinal(byte[])
     */
    public byte[] getContingutFinal() {
        return contingutFinal;
    }

    /**
     * Funcion para obtener las estadisticas
     * @return estadistiques(double[])
     */ 
    public double[] getEstadistiques() {
        return estadistiques;
    }

    void setContingutInicial(byte[] contingutInicial) {
        this.contingutInicial = contingutInicial;
    }

    void setContingutFinal(byte[] contingutFinal) {
        this.contingutFinal = contingutFinal;
    }

    void setEstadisticas(double[] res) {
        this.estadistiques = res;
    }
}
