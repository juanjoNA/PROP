/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacio;

/**
 *
 * @author bubu0
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        this.setContentPane(new Inici(this));
        this.invalidate();
        this.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pMostrar = new javax.swing.JPanel();
        menu = new javax.swing.JMenuBar();
        menuInici = new javax.swing.JMenu();
        menuComprimir = new javax.swing.JMenu();
        menuDescomprimir = new javax.swing.JMenu();
        menuComparar = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        menuCompararTXT = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(2500, 2000));
        setMinimumSize(new java.awt.Dimension(800, 300));
        setName("Main"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1000, 507));
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

        getContentPane().add(pMostrar, java.awt.BorderLayout.CENTER);

        menuInici.setText("Inici");
        menu.add(menuInici);

        menuComprimir.setText("Comprimir");
        menu.add(menuComprimir);

        menuDescomprimir.setText("Descomprimir");
        menu.add(menuDescomprimir);

        menuComparar.setText("Comparar");

        jMenu5.setText("Comparar IMG");
        menuComparar.add(jMenu5);

        menuCompararTXT.setText("Comparar TXT");
        menuComparar.add(menuCompararTXT);

        menu.add(menuComparar);

        setJMenuBar(menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuComparar;
    private javax.swing.JMenu menuCompararTXT;
    private javax.swing.JMenu menuComprimir;
    private javax.swing.JMenu menuDescomprimir;
    private javax.swing.JMenu menuInici;
    private javax.swing.JPanel pMostrar;
    // End of variables declaration//GEN-END:variables
}
