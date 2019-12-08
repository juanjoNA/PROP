/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacio;

import CapaDomini.Controladors.ControladorComprimir;
import CapaDomini.Controladors.ControladorDescomprimir;
import Excepcions.CaracterNoASCII;
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
    /**
     * Creates new form Descomprimir
     */
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
        jPanel1 = new javax.swing.JPanel();

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

        jPanel1.setMinimumSize(new java.awt.Dimension(500, 100));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 878, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 189, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 30);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void bDescomprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDescomprimirActionPerformed
        
        String alg;
        if(tfPath.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Selecciona un fitxer");
            return;
        }  
            
        alg = seleccionaAlg(tfPath.getText());
        int guardar = JOptionPane.showConfirmDialog(this,"Vols guardar el fitxer", "Guardar", JOptionPane.YES_NO_OPTION);

        if (guardar==JOptionPane.YES_OPTION){
            ctrDescomprimir = new ControladorDescomprimir(tfPath.getText(), alg, true);
        }else {
            ctrDescomprimir = new ControladorDescomprimir(tfPath.getText(), alg, true);
        }
        
        try {
            ctrDescomprimir.executar();
            JOptionPane.showMessageDialog(this, "Fitxer descomprimit");
            tfPath.setText("");
        } catch (VersionPPMIncorrecta ex) {
            Logger.getLogger(Comprimir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatosIncorrectos ex) {
            Logger.getLogger(Comprimir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Comprimir.class.getName()).log(Level.SEVERE, null, ex);
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
        }        
    }//GEN-LAST:event_bBrowserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBrowser;
    private javax.swing.JButton bDescomprimir;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label lSelectFitxer;
    private javax.swing.JTextField tfPath;
    // End of variables declaration//GEN-END:variables

    private String seleccionaAlg(String text) {
        String extensio;
        
        if(tfPath.getText().endsWith(".lzss")) extensio="LZSS";
        else if(tfPath.getText().endsWith(".lz78")) extensio="LZ78";
        else if(tfPath.getText().endsWith(".lzw")) extensio="LZW";
        else extensio="JPEG";
        
        return extensio;
    }
}
