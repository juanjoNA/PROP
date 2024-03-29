/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacio;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Juanjo
 */
public class ComparacioFitxers extends javax.swing.JPanel {

    JPanel comparar;
    JFrame main;
    byte[] textInicial, textFinal;
    
    public ComparacioFitxers(JPanel comparar, JFrame main, byte[] textInicial, byte[] textFinal) {
        initComponents();
        this.comparar = comparar;
        this.main = main;
        editorPaneInici.setText(new String(textInicial));
        editorPaneFinal.setText(new String(textFinal));
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

        labelFitxerNorm = new javax.swing.JLabel();
        labelFitxerDesc = new javax.swing.JLabel();
        scrollFitxNorm = new javax.swing.JScrollPane();
        editorPaneInici = new javax.swing.JEditorPane();
        scrollFitxProcs = new javax.swing.JScrollPane();
        editorPaneFinal = new javax.swing.JEditorPane();
        bTornar = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        labelFitxerNorm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelFitxerNorm.setText("Fitxer Inicial");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        add(labelFitxerNorm, gridBagConstraints);

        labelFitxerDesc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelFitxerDesc.setText("Fitxer Processat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        add(labelFitxerDesc, gridBagConstraints);

        scrollFitxNorm.setViewportView(editorPaneInici);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 0);
        add(scrollFitxNorm, gridBagConstraints);

        scrollFitxProcs.setViewportView(editorPaneFinal);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        add(scrollFitxProcs, gridBagConstraints);

        bTornar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        bTornar.setText("Tornar");
        bTornar.setMaximumSize(new java.awt.Dimension(150, 50));
        bTornar.setMinimumSize(new java.awt.Dimension(110, 40));
        bTornar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTornarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        add(bTornar, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void bTornarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTornarActionPerformed
        main.setLocationRelativeTo(null);
        main.setSize(comparar.getWidth(), comparar.getHeight());
        main.setLocationRelativeTo(null);
        main.setContentPane(comparar);
        main.invalidate();
        main.validate();
    }//GEN-LAST:event_bTornarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bTornar;
    private javax.swing.JEditorPane editorPaneFinal;
    private javax.swing.JEditorPane editorPaneInici;
    private javax.swing.JLabel labelFitxerDesc;
    private javax.swing.JLabel labelFitxerNorm;
    private javax.swing.JScrollPane scrollFitxNorm;
    private javax.swing.JScrollPane scrollFitxProcs;
    // End of variables declaration//GEN-END:variables
}
