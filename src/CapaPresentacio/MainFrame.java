package CapaPresentacio;

import java.awt.Desktop;
import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Juanjo
 */
public class MainFrame extends javax.swing.JFrame {

    private HashMap<Integer, String> excepciones = new HashMap<Integer, String>(){
        {
            put(1, "Caracter no ASCII al fitxer seleccionat");
            put(2, "Extensió de fitxer incorrecte");
            put(3, "Dades imatge incorrectes");
            put(4, "Version PPM incorrecta. Deberia ser P6");
            put(5, "Error al leer el fichero");
            put(6, "Error al acceder al fichero ");
        }
    };
    
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
        menuDescomprimir = new javax.swing.JMenu();
        menuComparar = new javax.swing.JMenu();
        menuEstadisticas = new javax.swing.JMenu();
        menuHelp = new javax.swing.JMenu();

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
        menuInici.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        menuInici.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuIniciMouseClicked(evt);
            }
        });
        menu.add(menuInici);

        menuComprimir.setText("Comprimir");
        menuComprimir.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        menuComprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuComprimirMouseClicked(evt);
            }
        });
        menu.add(menuComprimir);

        menuDescomprimir.setText("Descomprimir");
        menuDescomprimir.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        menuDescomprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuDescomprimirMouseClicked(evt);
            }
        });
        menu.add(menuDescomprimir);

        menuComparar.setText("Comparar");
        menuComparar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        menuComparar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCompararMouseClicked(evt);
            }
        });
        menu.add(menuComparar);

        menuEstadisticas.setText("Estadistiques");
        menuEstadisticas.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        menuEstadisticas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuEstadisticasMouseClicked(evt);
            }
        });
        menu.add(menuEstadisticas);

        menuHelp.setText("Help");
        menuHelp.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        menuHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuHelpMouseClicked(evt);
            }
        });
        menu.add(menuHelp);

        setJMenuBar(menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuIniciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuIniciMouseClicked
        cambiarPanel(new Inici(this));
    }//GEN-LAST:event_menuIniciMouseClicked

    private void menuEstadisticasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuEstadisticasMouseClicked
        try {
            cambiarPanel(new Estadistiques());
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuEstadisticasMouseClicked

    private void menuCompararMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCompararMouseClicked
        cambiarPanel(new Comparar(this));
    }//GEN-LAST:event_menuCompararMouseClicked

    private void menuComprimirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuComprimirMouseClicked
        cambiarPanel(new Comprimir(this));
    }//GEN-LAST:event_menuComprimirMouseClicked

    private void menuDescomprimirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuDescomprimirMouseClicked
        cambiarPanel(new Descomprimir(this));
    }//GEN-LAST:event_menuDescomprimirMouseClicked

    private void menuHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHelpMouseClicked
        try {
            File f = new File("ProjecteGrup/DOCS/Documentacion.pdf");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(f.getCanonicalFile());
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(this, returnException(6) + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuHelpMouseClicked

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
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuComparar;
    private javax.swing.JMenu menuComprimir;
    private javax.swing.JMenu menuDescomprimir;
    private javax.swing.JMenu menuEstadisticas;
    private javax.swing.JMenu menuHelp;
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
    
    public String returnException(int key){
        return excepciones.get(key);
    }


}
