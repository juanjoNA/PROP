package CapaDomini.ModelDomini;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ivgasa99
 */
public class LZWTestRunner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(LZWTest.class);
        //junit.run(LZWTest.class);
    }
    
}
