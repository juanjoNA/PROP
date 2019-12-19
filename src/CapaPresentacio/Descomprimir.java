/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacio;

import CapaDomini.Controladors.ControladorDescomprimir;
import Excepcions.DatosIncorrectos;
import Excepcions.VersionPPMIncorrecta;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Juanjo
 */
public class Descomprimir extends javax.swing.JPanel {

    ControladorDescomprimir ctrDescomprimir;
    
    public Descomprimir() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lSelectFitxer = new java.awt.Label();
        tfPath = new javax.swing.JTextField();
        bBrowser = new javax.swing.JButton();
        bDescomprimir = new javax.swing.JButton();
        panelEstadistiques = new javax.swing.JPanel();
        jVelocitat = new javax.swing.JLabel();
        labelVelCompr = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelPercCompr = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelTempsCompr = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(900, 164));
        setPreferredSize(new java.awt.Dimension(900, 164));
        setLayout(new java.awt.GridBagLayout());

        lSelectFitxer.setMaximumSize(new java.awt.Dimension(32767, 30));
        lSelectFitxer.setText("Selecciona un fitxer:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 30);
        add(lSelectFitxer, gridBagConstraints);

        tfPath.setEditable(false);
        tfPath.setMaximumSize(new java.awt.Dimension(2147483647, 30));
        tfPath.setMinimumSize(new java.awt.Dimension(100, 30));
        tfPath.setPreferredSize(new java.awt.Dimension(4, 29));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        add(tfPath, gridBagConstraints);

        bBrowser.setText("Browser");
        bBrowser.setMaximumSize(new java.awt.Dimension(160, 90));
        bBrowser.setMinimumSize(new java.awt.Dimension(120, 50));
        bBrowser.setPreferredSize(new java.awt.Dimension(120, 50));
        bBrowser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBrowserActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 30);
        add(bBrowser, gridBagConstraints);

        bDescomprimir.setText("Descomprimir");
        bDescomprimir.setVisible(false);
        bDescomprimir.setMinimumSize(new java.awt.Dimension(120, 50));
        bDescomprimir.setPreferredSize(new java.awt.Dimension(120, 50));
        bDescomprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDescomprimirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 30);
        add(bDescomprimir, gridBagConstraints);

        panelEstadistiques.setLayout(new java.awt.GridBagLayout());
        panelEstadistiques.setVisible(false);

        jVelocitat.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jVelocitat.setText("Velocitat de Compressio: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        panelEstadistiques.add(jVelocitat, gridBagConstraints);

        labelVelCompr.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelVelCompr.setText("vel");
        labelVelCompr.setMaximumSize(new java.awt.Dimension(150, 20));
        labelVelCompr.setMinimumSize(new java.awt.Dimension(150, 20));
        labelVelCompr.setPreferredSize(new java.awt.Dimension(150, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 0);
        panelEstadistiques.add(labelVelCompr, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("Percentatge de Compresdió: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panelEstadistiques.add(jLabel4, gridBagConstraints);

        labelPercCompr.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelPercCompr.setText("perc");
        labelPercCompr.setMaximumSize(new java.awt.Dimension(200, 20));
        labelPercCompr.setMinimumSize(new java.awt.Dimension(150, 20));
        labelPercCompr.setPreferredSize(new java.awt.Dimension(150, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panelEstadistiques.add(labelPercCompr, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Temps total:");
        jLabel1.setPreferredSize(new java.awt.Dimension(900, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 150, 20, 0);
        panelEstadistiques.add(jLabel1, gridBagConstraints);

        labelTempsCompr.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        labelTempsCompr.setText("temp");
        labelTempsCompr.setMaximumSize(new java.awt.Dimension(150, 20));
        labelTempsCompr.setMinimumSize(new java.awt.Dimension(150, 20));
        labelTempsCompr.setName(""); // NOI18N
        labelTempsCompr.setPreferredSize(new java.awt.Dimension(150, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 0);
        panelEstadistiques.add(labelTempsCompr, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(75, 0, 0, 0);
        add(panelEstadistiques, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void bDescomprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDescomprimirActionPerformed
        
        String alg;
        double resultat[];
        if(tfPath.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Selecciona un fitxer");
            return;
        }  
            
        int guardar = JOptionPane.showConfirmDialog(this,"Vols guardar el fitxer", "Guardar", JOptionPane.YES_NO_OPTION);

        if (guardar==JOptionPane.YES_OPTION){
            ctrDescomprimir = new ControladorDescomprimir(tfPath.getText(), true);
        }else {
            ctrDescomprimir = new ControladorDescomprimir(tfPath.getText(), false);
        }
        
        try {
            ctrDescomprimir.executar();
            resultat= ctrDescomprimir.getResult();
            JOptionPane.showMessageDialog(this, "Fitxer descomprimit");
            tfPath.setText("");
            bDescomprimir.setVisible(false);
            panelEstadistiques.setVisible(true);
            labelPercCompr.setText(String.format("%.2f", resultat[1]) + " %");
            labelTempsCompr.setText(Double.toString(resultat[0]) + " ms");
            labelVelCompr.setText(String.format("%.2f",resultat[2]) + " KB/s");
        } catch (VersionPPMIncorrecta ex) {
            Logger.getLogger(Comprimir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatosIncorrectos ex) {
            Logger.getLogger(Comprimir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Comprimir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Descomprimir.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_bDescomprimirActionPerformed

    private void bBrowserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBrowserActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "LZSS, LZW, LZ78 & JIMG", "lzss", "lz78", "lzw", "jimg");
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            tfPath.setText(chooser.getSelectedFile().getPath());
            bDescomprimir.setVisible(true);
            panelEstadistiques.setVisible(false);
        }        
    }//GEN-LAST:event_bBrowserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBrowser;
    private javax.swing.JButton bDescomprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jVelocitat;
    private java.awt.Label lSelectFitxer;
    private javax.swing.JLabel labelPercCompr;
    private javax.swing.JLabel labelTempsCompr;
    private javax.swing.JLabel labelVelCompr;
    private javax.swing.JPanel panelEstadistiques;
    private javax.swing.JTextField tfPath;
    // End of variables declaration//GEN-END:variables

}
