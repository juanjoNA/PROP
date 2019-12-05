/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacio;

import CapaDomini.Controladors.ControladorComprimir;
import Excepcions.CaracterNoASCII;
import Excepcions.DatosIncorrectos;
import Excepcions.VersionPPMIncorrecta;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Juanjo
 */
public class Comprimir extends javax.swing.JPanel {

    JFrame pare;
    ControladorComprimir ctrComprimir;
    /**
     * Creates new form Comprimir
     */
    public Comprimir() {
        initComponents();
        //this.pare = pare;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bgAlgoritmos = new javax.swing.ButtonGroup();
        lSelectFitxer = new java.awt.Label();
        tfPath = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        bLZW = new javax.swing.JRadioButton();
        bLZSS = new javax.swing.JRadioButton();
        bLZ78 = new javax.swing.JRadioButton();
        bJPEG = new javax.swing.JRadioButton();
        bAuto = new javax.swing.JRadioButton();
        bBrowser = new javax.swing.JButton();
        bComprimir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

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
        tfPath.setMinimumSize(new java.awt.Dimension(50, 30));
        tfPath.setPreferredSize(new java.awt.Dimension(50, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        add(tfPath, gridBagConstraints);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 60, 5));

        bgAlgoritmos.add(bLZW);
        bLZW.setText("LZW");
        jPanel1.add(bLZW);

        bgAlgoritmos.add(bLZSS);
        bLZSS.setText("LZSS");
        jPanel1.add(bLZSS);

        bgAlgoritmos.add(bLZ78);
        bLZ78.setText("LZ78");
        jPanel1.add(bLZ78);

        bgAlgoritmos.add(bJPEG);
        bJPEG.setText("JEPG");
        jPanel1.add(bJPEG);

        bgAlgoritmos.add(bAuto);
        bAuto.setText("Automàtic");
        bAuto.setActionCommand("AUTO");
        jPanel1.add(bAuto);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        add(jPanel1, gridBagConstraints);

        bBrowser.setText("Browser");
        bBrowser.setMaximumSize(new java.awt.Dimension(150, 50));
        bBrowser.setMinimumSize(new java.awt.Dimension(120, 45));
        bBrowser.setPreferredSize(new java.awt.Dimension(120, 45));
        bBrowser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBrowserActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 30);
        add(bBrowser, gridBagConstraints);

        bComprimir.setText("Comprmir");
        bComprimir.setMaximumSize(new java.awt.Dimension(190, 65));
        bComprimir.setMinimumSize(new java.awt.Dimension(120, 45));
        bComprimir.setPreferredSize(new java.awt.Dimension(120, 45));
        bComprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bComprimirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 30);
        add(bComprimir, gridBagConstraints);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 10, 10);
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void bComprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bComprimirActionPerformed
        String alg;
        if(bgAlgoritmos.getSelection()==null){
            JOptionPane.showMessageDialog(this, "Selecciona un botó");
            return;
        } else if(tfPath.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Selecciona un fitxer");
            return;
        } else {
            alg = bgAlgoritmos.getSelection().getActionCommand(); //Esta String se la pasamos al controlador para que seleccione el algoritmo de compresion
        }

        int guardar = JOptionPane.showConfirmDialog(this,"Vols guardar el fitxer", "Guardar", JOptionPane.YES_NO_OPTION);

        if (guardar==JOptionPane.YES_OPTION){
            ctrComprimir = new ControladorComprimir(tfPath.getText(), alg, true);
        }else {
            ctrComprimir = new ControladorComprimir(tfPath.getText(), alg, false);
        }
        
        try {
            ctrComprimir.executar();
        } catch (VersionPPMIncorrecta ex) {
            Logger.getLogger(Comprimir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatosIncorrectos ex) {
            Logger.getLogger(Comprimir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Comprimir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CaracterNoASCII ex) {
            Logger.getLogger(Comprimir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bComprimirActionPerformed

    private void bBrowserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBrowserActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "txt & ppm", "txt", "ppm");
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            tfPath.setText(chooser.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_bBrowserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bAuto;
    private javax.swing.JButton bBrowser;
    private javax.swing.JButton bComprimir;
    private javax.swing.JRadioButton bJPEG;
    private javax.swing.JRadioButton bLZ78;
    private javax.swing.JRadioButton bLZSS;
    private javax.swing.JRadioButton bLZW;
    private javax.swing.ButtonGroup bgAlgoritmos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private java.awt.Label lSelectFitxer;
    private javax.swing.JTextField tfPath;
    // End of variables declaration//GEN-END:variables
}


