package CapaPresentacio;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Juanjo
 */
public class MainFrame extends javax.swing.JFrame {

    public MainFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        cambiarPanel(new Inici(this));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pMostrar = new javax.swing.JPanel();
        menu = new javax.swing.JMenuBar();
        menuInici = new javax.swing.JMenu();
        menuComprimir = new javax.swing.JMenu();
        bCompFitxer = new javax.swing.JMenuItem();
        bCompCarpeta = new javax.swing.JMenuItem();
        bDescomprimir = new javax.swing.JMenu();
        bDescFitxer = new javax.swing.JMenuItem();
        bDescCarpeta = new javax.swing.JMenuItem();
        menuComparar = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        menuCompararTXT = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setName("Main"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setResizable(false);
        getContentPane().setLayout(new java.awt.BorderLayout(20, 20));

        javax.swing.GroupLayout pMostrarLayout = new javax.swing.GroupLayout(pMostrar);
        pMostrar.setLayout(pMostrarLayout);
        pMostrarLayout.setHorizontalGroup(
            pMostrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 603, Short.MAX_VALUE)
        );
        pMostrarLayout.setVerticalGroup(
            pMostrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        getContentPane().add(pMostrar, java.awt.BorderLayout.NORTH);

        menuInici.setText("Inici");
        menuInici.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuIniciMouseClicked(evt);
            }
        });
        menu.add(menuInici);

        menuComprimir.setText("Comprimir");

        bCompFitxer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FileIcon.png"))); // NOI18N
        bCompFitxer.setText("Comprimir fitxer");
        bCompFitxer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCompFitxerActionPerformed(evt);
            }
        });
        menuComprimir.add(bCompFitxer);

        bCompCarpeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/folderIcon.png"))); // NOI18N
        bCompCarpeta.setText("Comprimir carpeta");
        bCompCarpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCompCarpetaActionPerformed(evt);
            }
        });
        menuComprimir.add(bCompCarpeta);

        menu.add(menuComprimir);

        bDescomprimir.setText("Descomprimir");

        bDescFitxer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FileIcon.png"))); // NOI18N
        bDescFitxer.setText("Descomprimir Fitxer");
        bDescFitxer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDescFitxerActionPerformed(evt);
            }
        });
        bDescomprimir.add(bDescFitxer);

        bDescCarpeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/folderIcon.png"))); // NOI18N
        bDescCarpeta.setText("Descomprimir Carpeta");
        bDescCarpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDescCarpetaActionPerformed(evt);
            }
        });
        bDescomprimir.add(bDescCarpeta);

        menu.add(bDescomprimir);

        menuComparar.setText("Comparar");

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/IconIMG.png"))); // NOI18N
        jMenu5.setText("Comparar IMG");
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });
        menuComparar.add(jMenu5);

        menuCompararTXT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/IconTXT.png"))); // NOI18N
        menuCompararTXT.setText("Comparar TXT");
        menuCompararTXT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCompararTXTMouseClicked(evt);
            }
        });
        menuComparar.add(menuCompararTXT);

        menu.add(menuComparar);

        jMenu1.setText("Estadistiques");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        menu.add(jMenu1);

        setJMenuBar(menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCompFitxerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCompFitxerActionPerformed
        cambiarPanel(new Comprimir());
    }//GEN-LAST:event_bCompFitxerActionPerformed

    private void bCompCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCompCarpetaActionPerformed
        cambiarPanel(new Comprimir());
    }//GEN-LAST:event_bCompCarpetaActionPerformed

    private void bDescFitxerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDescFitxerActionPerformed
        cambiarPanel(new Descomprimir());
    }//GEN-LAST:event_bDescFitxerActionPerformed

    private void bDescCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDescCarpetaActionPerformed
        cambiarPanel(new Descomprimir());
    }//GEN-LAST:event_bDescCarpetaActionPerformed

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
        cambiarPanel(new Comparar(this));
    }//GEN-LAST:event_jMenu5ActionPerformed

    private void menuIniciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuIniciMouseClicked
        cambiarPanel(new Inici(this));
    }//GEN-LAST:event_menuIniciMouseClicked

    private void menuCompararTXTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCompararTXTMouseClicked
        cambiarPanel(new Comparar(this));
    }//GEN-LAST:event_menuCompararTXTMouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        try {
            cambiarPanel(new Estadistiques());
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem bCompCarpeta;
    private javax.swing.JMenuItem bCompFitxer;
    private javax.swing.JMenuItem bDescCarpeta;
    private javax.swing.JMenuItem bDescFitxer;
    private javax.swing.JMenu bDescomprimir;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuComparar;
    private javax.swing.JMenu menuCompararTXT;
    private javax.swing.JMenu menuComprimir;
    private javax.swing.JMenu menuInici;
    private javax.swing.JPanel pMostrar;
    // End of variables declaration//GEN-END:variables


    public void cambiarPanel(JPanel panel){
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setSize(this.WIDTH, this.HEIGHT);
        this.setLocationRelativeTo(null);
        this.invalidate();
        this.validate();
        
        
    }


}
