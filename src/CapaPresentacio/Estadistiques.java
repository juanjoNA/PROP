/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacio;

import CapaDomini.Controladors.ControladorEstadisticas;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Juanjo
 */
public class Estadistiques extends javax.swing.JPanel {

    ControladorEstadisticas ctrEstadistiques;
    public Estadistiques() throws Exception {
        initComponents();
        addRows();
    }

    
    private void addRows() throws Exception{
        String[][] resultat;
        int sizeX, sizeY;
        
        ctrEstadistiques = new ControladorEstadisticas();
        ctrEstadistiques.executar();
        resultat = ctrEstadistiques.getResult();
        sizeX = resultat.length;
        sizeY = resultat[0].length;
        DefaultTableModel model = (DefaultTableModel) tablaEstadisticas.getModel();
        
        for(int i=0; i<sizeY; i++){
            model.addColumn(resultat[0][i]);
        }
        for(int j=1; j<sizeX; j++){
            model.addRow(resultat[j]);
        }
        
        tablaEstadisticas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPanelTable.setAutoscrolls(true);
    }
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanelTable = new javax.swing.JScrollPane();
        tablaEstadisticas = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1000, 700));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.GridBagLayout());

        scrollPanelTable.setMinimumSize(new java.awt.Dimension(600, 31));
        scrollPanelTable.setName(""); // NOI18N
        scrollPanelTable.setPreferredSize(new java.awt.Dimension(900, 402));
        scrollPanelTable.setRequestFocusEnabled(false);

        tablaEstadisticas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaEstadisticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaEstadisticas.setMaximumSize(new java.awt.Dimension(1000, 600));
        tablaEstadisticas.setMinimumSize(new java.awt.Dimension(600, 0));
        tablaEstadisticas.setRowHeight(50);
        scrollPanelTable.setViewportView(tablaEstadisticas);

        add(scrollPanelTable, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPanelTable;
    private javax.swing.JTable tablaEstadisticas;
    // End of variables declaration//GEN-END:variables
}
