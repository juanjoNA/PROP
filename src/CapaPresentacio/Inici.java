package CapaPresentacio;

import java.util.logging.Level;
import java.util.logging.Logger;
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

        bComprimir = new javax.swing.JButton();
        bDescomprimir = new javax.swing.JButton();
        bComparar = new javax.swing.JButton();
        bEstadisticas = new javax.swing.JButton();
        bSortir = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1000, 700));
        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.GridBagLayout());

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

        bEstadisticas.setText("Estadístiques");
        bEstadisticas.setMaximumSize(new java.awt.Dimension(300, 75));
        bEstadisticas.setMinimumSize(new java.awt.Dimension(300, 75));
        bEstadisticas.setPreferredSize(new java.awt.Dimension(300, 75));
        bEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEstadisticasActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(35, 0, 0, 0);
        add(bEstadisticas, gridBagConstraints);

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
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(35, 0, 35, 0);
        add(bSortir, gridBagConstraints);
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

    private void bEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEstadisticasActionPerformed
        try {
            cambiarPanel(new Estadistiques());
        } catch (Exception ex) {
            Logger.getLogger(Inici.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bEstadisticasActionPerformed

    private void cambiarPanel(JPanel panel){
        pare.setContentPane(panel);
        pare.invalidate();
        pare.validate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bComparar;
    private javax.swing.JButton bComprimir;
    private javax.swing.JButton bDescomprimir;
    private javax.swing.JButton bEstadisticas;
    private javax.swing.JButton bSortir;
    // End of variables declaration//GEN-END:variables
}
