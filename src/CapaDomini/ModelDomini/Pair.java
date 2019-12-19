/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDomini.ModelDomini;

import java.util.Objects;

/**
 *
 * @author Juanjo
 */
public class Pair <T1, T2> {
    
    private T1 first;
    private T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
    
    @Override
    public String toString() {
        return first + "," + second;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + first.hashCode();
        result = prime * result + second.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
            final Pair<?, ?> other = (Pair <?, ?>) obj;
            return Objects.equals(getFirst(), other.getFirst()) && Objects.equals(getSecond(), other.getSecond());
    }

    public T1 getFirst() {
        return first;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }
    
}
