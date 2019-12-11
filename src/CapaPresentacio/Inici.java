package CapaPresentacio;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Juanjo
 */
public class Inici extends javax.swing.JPanel {

    JFrame pare;
    
    public Inici(JFrame pare) {
        initComponents();
        this.pare = pare;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bSortir = new javax.swing.JButton();
        bComparar = new javax.swing.JButton();
        bDescomprimir = new javax.swing.JButton();
        bComprimir = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        bSortir.setText("Sortir");
        bSortir.setMaximumSize(new java.awt.Dimension(200, 100));
        bSortir.setMinimumSize(new java.awt.Dimension(300, 70));
        bSortir.setPreferredSize(new java.awt.Dimension(300, 70));
        bSortir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSortirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(35, 0, 35, 0);
        add(bSortir, gridBagConstraints);

        bComparar.setText("Comparar");
        bComparar.setMaximumSize(new java.awt.Dimension(200, 9));
        bComparar.setMinimumSize(new java.awt.Dimension(300, 70));
        bComparar.setPreferredSize(new java.awt.Dimension(300, 70));
        bComparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCompararActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(35, 0, 0, 0);
        add(bComparar, gridBagConstraints);

        bDescomprimir.setText("Descomprimir");
        bDescomprimir.setMaximumSize(new java.awt.Dimension(200, 9));
        bDescomprimir.setMinimumSize(new java.awt.Dimension(300, 70));
        bDescomprimir.setPreferredSize(new java.awt.Dimension(300, 70));
        bDescomprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDescomprimirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(35, 0, 0, 0);
        add(bDescomprimir, gridBagConstraints);

        bComprimir.setText("Comprimir");
        bComprimir.setMaximumSize(new java.awt.Dimension(200, 9));
        bComprimir.setMinimumSize(new java.awt.Dimension(300, 70));
        bComprimir.setPreferredSize(new java.awt.Dimension(300, 70));
        bComprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bComprimirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(35, 0, 0, 0);
        add(bComprimir, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void bComprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bComprimirActionPerformed
        cambiarPanel(new Comprimir());
    }//GEN-LAST:event_bComprimirActionPerformed

    private void bDescomprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDescomprimirActionPerformed
        cambiarPanel(new Descomprimir());
    }//GEN-LAST:event_bDescomprimirActionPerformed

    private void bCompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCompararActionPerformed
        cambiarPanel(new Comparar(pare));
    }//GEN-LAST:event_bCompararActionPerformed

    private void bSortirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSortirActionPerformed
        pare.dispose();
    }//GEN-LAST:event_bSortirActionPerformed

    private void cambiarPanel(JPanel panel){
        pare.setContentPane(panel);
        pare.invalidate();
        pare.validate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bComparar;
    private javax.swing.JButton bComprimir;
    private javax.swing.JButton bDescomprimir;
    private javax.swing.JButton bSortir;
    // End of variables declaration//GEN-END:variables
}