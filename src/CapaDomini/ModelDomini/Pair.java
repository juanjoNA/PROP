/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.ModelDomini;

import java.util.Objects;

/**
 *
 * @author Pau Murciano
 */
public class Pair <T1, T2> {
    
    private T1 first;
    private T2 second;
    
    /**
     * Constructora dada dos valores
     * @param first
     * @param second
     */
    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
    
    /**
     * Funcion que convierte el pair en string
     * @return String stringedPair
     */
    @Override
    public String toString() {
        return first + "," + second;
    }

    /**
     * Funcion que calcula el hashCode del pair
     * @return int hashCode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + first.hashCode();
        result = prime * result + second.hashCode();
        return result;
    }

    /**
     * Funcion que compara dos pairs
     * @return boolean isEqual
     */
    @Override
    public boolean equals(Object obj) {
            final Pair<?, ?> other = (Pair <?, ?>) obj;
            return Objects.equals(getFirst(), other.getFirst()) && Objects.equals(getSecond(), other.getSecond());
    }

    /**
     * Funcion que devuelve el primer valor del pair
     * @return T1 first
     */
    public T1 getFirst() {
        return first;
    }

    /**
     * Funcion que cambia el primer valor del pair
     * @param T1 first
     */
    public void setFirst(T1 first) {
        this.first = first;
    }
    
    /**
     * Funcion que devuelve el segundo valor del pair
     * @return T2 second
     */
    public T2 getSecond() {
        return second;
    }

    /**
     * Funcion que cambia el segundo valor del pair
     * @param T2 second
     */
    public void setSecond(T2 second) {
        this.second = second;
    }
    
}
