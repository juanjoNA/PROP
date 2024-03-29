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
    private String pathIni;
    private String pathFi;
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
        this.pathFi = "";
        this.pathIni = "";
    }
    
    public DTOComparar(byte[] contingutInicial, byte[] contingutFinal, double[] estadistiques, String pathIni, String pathFi) {
        this.contingutInicial = contingutInicial;
        this.contingutFinal = contingutFinal;
        this.estadistiques = estadistiques;
        this.pathFi = pathFi;
        this.pathIni = pathIni;
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
    /**
     * Funcion para asignar el contenido inicial
     * @param contingutInicial
     */
    void setContingutInicial(byte[] contingutInicial) {
        this.contingutInicial = contingutInicial;
    }
    /**
     * Funcion para asignar el contenido final
     * @param contingutFinal
     */
    void setContingutFinal(byte[] contingutFinal) {
        this.contingutFinal = contingutFinal;
    }
    /**
     * Funcion para asignar las estadisticas 
     * @param res
     */
    void setEstadisticas(double[] res) {
        this.estadistiques = res;
    }
    
    /** Funcion para devolver el path inicial
     * @return String pathIni
     */ 
    public String getPathIni() {
        return pathIni;
    }

    /** Funcion para devolver el path final
     * @return String pathFi
     */ 
    public String getPathFi() {
        return pathFi;
    }
    
    /**
     * Funcion para asignar el path inicial
     * @param pathIni
     */
    public void setPathIni(String pathIni) {
        this.pathIni = pathIni;
    }
    
    /**
     * Funcion para asignar las el path final 
     * @param pathFi
     */
    public void setPathFi(String pathFi) {
        this.pathFi = pathFi;
    }
    
}
