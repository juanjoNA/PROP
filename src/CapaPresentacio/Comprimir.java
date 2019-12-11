/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacio;

import CapaDomini.Controladors.ControladorAlgoritmes;
import CapaDomini.Controladors.ControladorComprimir;
import Excepcions.CaracterNoASCII;
import Excepcions.DatosIncorrectos;
import Excepcions.VersionPPMIncorrecta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Juanjo
 */
public class Comprimir extends javax.swing.JPanel {

    ControladorComprimir ctrComprimir;
    ControladorAlgoritmes ctrAlgoritmes;
    /**
     * Creates new form Comprimir
     */
    public Comprimir() {
        initComponents();
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
        bgSubsampling = new javax.swing.ButtonGroup();
        lSelectFitxer = new java.awt.Label();
        tfPath = new javax.swing.JTextField();
        panelRadioButtons = new javax.swing.JPanel();
        bBrowser = new javax.swing.JButton();
        bComprimir = new javax.swing.JButton();
        panelEstadistiques = new javax.swing.JPanel();
        jVelocitat = new javax.swing.JLabel();
        labelVelCompr = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelPercCompr = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelTempsCompr = new javax.swing.JLabel();
        panelDadesJPEG = new javax.swing.JPanel();
        panelSubsampling = new javax.swing.JPanel();
        labelSubsampling = new javax.swing.JLabel();
        radio444 = new javax.swing.JRadioButton();
        radio440 = new javax.swing.JRadioButton();
        radio411 = new javax.swing.JRadioButton();
        radio422 = new javax.swing.JRadioButton();
        radio420 = new javax.swing.JRadioButton();
        panelSlider = new javax.swing.JPanel();
        sliderJPEG = new javax.swing.JSlider();
        labelGrauCompr = new javax.swing.JLabel();
        labelValorGrauCompresio = new javax.swing.JLabel();

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

        panelRadioButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 60, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        add(panelRadioButtons, gridBagConstraints);

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
        bComprimir.setVisible(false);
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

        panelEstadistiques.setLayout(new java.awt.GridBagLayout());
        panelEstadistiques.setVisible(false);

        jVelocitat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jVelocitat.setText("Velocitat de Compressio: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        panelEstadistiques.add(jVelocitat, gridBagConstraints);

        labelVelCompr.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelVelCompr.setPreferredSize(new java.awt.Dimension(200, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 0);
        panelEstadistiques.add(labelVelCompr, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Percentatge de Compressió: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panelEstadistiques.add(jLabel4, gridBagConstraints);

        labelPercCompr.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelPercCompr.setPreferredSize(new java.awt.Dimension(200, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panelEstadistiques.add(labelPercCompr, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Temps total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 200, 20, 0);
        panelEstadistiques.add(jLabel1, gridBagConstraints);

        labelTempsCompr.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTempsCompr.setPreferredSize(new java.awt.Dimension(200, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 20, 0);
        panelEstadistiques.add(labelTempsCompr, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 10, 10);
        add(panelEstadistiques, gridBagConstraints);

        panelDadesJPEG.setLayout(new java.awt.GridBagLayout());
        panelDadesJPEG.setVisible(false);

        panelSubsampling.setLayout(new java.awt.GridBagLayout());

        labelSubsampling.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelSubsampling.setText("Subsampling");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        panelSubsampling.add(labelSubsampling, gridBagConstraints);

        bgSubsampling.add(radio444);
        radio444.setText("4 : 4 : 4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelSubsampling.add(radio444, gridBagConstraints);

        bgSubsampling.add(radio440);
        radio440.setText("4 : 4 : 0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelSubsampling.add(radio440, gridBagConstraints);

        bgSubsampling.add(radio411);
        radio411.setText("4 : 1 : 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelSubsampling.add(radio411, gridBagConstraints);

        bgSubsampling.add(radio422);
        radio422.setText("4 : 2 : 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelSubsampling.add(radio422, gridBagConstraints);

        bgSubsampling.add(radio420);
        radio420.setText("4 : 2 : 0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelSubsampling.add(radio420, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 40, 0, 0);
        panelDadesJPEG.add(panelSubsampling, gridBagConstraints);

        panelSlider.setLayout(new java.awt.GridBagLayout());

        sliderJPEG.setMajorTickSpacing(99);
        sliderJPEG.setMinimum(1);
        sliderJPEG.setMinorTickSpacing(99);
        sliderJPEG.setPaintLabels(true);
        sliderJPEG.setPaintTicks(true);
        sliderJPEG.setToolTipText("");
        sliderJPEG.setValue(0);
        sliderJPEG.setPreferredSize(new java.awt.Dimension(400, 50));
        sliderJPEG.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderJPEGStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelSlider.add(sliderJPEG, gridBagConstraints);

        labelGrauCompr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelGrauCompr.setText("Grau Compressió");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 15, 0);
        panelSlider.add(labelGrauCompr, gridBagConstraints);

        labelValorGrauCompresio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelValorGrauCompresio.setText("1");
        labelValorGrauCompresio.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelValorGrauCompresio.setMaximumSize(new java.awt.Dimension(35, 22));
        labelValorGrauCompresio.setMinimumSize(new java.awt.Dimension(35, 22));
        labelValorGrauCompresio.setPreferredSize(new java.awt.Dimension(35, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 0);
        panelSlider.add(labelValorGrauCompresio, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        panelDadesJPEG.add(panelSlider, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        add(panelDadesJPEG, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void bComprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bComprimirActionPerformed
        String alg;
        double[] resultat; 
        if(bgAlgoritmos.getSelection()==null){
            JOptionPane.showMessageDialog(this, "Selecciona un botó");
            return;
        } else if(tfPath.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Selecciona un fitxer");
            return;
        }  
            
        alg = bgAlgoritmos.getSelection().getActionCommand(); //Esta String se la pasamos al controlador para que seleccione el algoritmo de compresion
        int guardar = JOptionPane.showConfirmDialog(this,"Vols guardar el fitxer", "Guardar", JOptionPane.YES_NO_OPTION);

        if (guardar==JOptionPane.YES_OPTION){
            ctrComprimir = new ControladorComprimir(tfPath.getText(), alg, true);
        }else {
            ctrComprimir = new ControladorComprimir(tfPath.getText(), alg, false);
        }
        
        try {
            ctrComprimir.executar();
            resultat = ctrComprimir.getResult();
            JOptionPane.showMessageDialog(this, "Fitxer comprimit");
            tfPath.setText("");
            panelRadioButtons.removeAll();
            bComprimir.setVisible(false);
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
            crearBotones();
            bComprimir.setVisible(true);
            panelEstadistiques.setVisible(false);
        }
    }//GEN-LAST:event_bBrowserActionPerformed

    private void sliderJPEGStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderJPEGStateChanged
        labelValorGrauCompresio.setText(String.valueOf(sliderJPEG.getValue()));
    }//GEN-LAST:event_sliderJPEGStateChanged

    public void crearBotones(){
        ArrayList<String> botons;
        panelRadioButtons.removeAll();
        bgAlgoritmos.clearSelection();
        if(tfPath.getText().endsWith(".txt")){
           ctrAlgoritmes = new ControladorAlgoritmes("txt");
        }else{
           ctrAlgoritmes = new ControladorAlgoritmes("imatge");
        }
        botons = ctrAlgoritmes.getAlgoritmes();
        for(String nom : botons){
            JRadioButton b = new JRadioButton();
            b.setText(nom);
            b.setActionCommand(nom);
            b.setName(nom);
            bgAlgoritmos.add(b);
            panelRadioButtons.add(b);
            ActionListener al = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(nom.equals("JPEG")) panelDadesJPEG.setVisible(true);
                    else panelDadesJPEG.setVisible(false);
                }
            };
            b.addActionListener(al);
        }
        panelRadioButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 60, 5));
        panelRadioButtons.setSize(tfPath.getSize());
        panelRadioButtons.invalidate();
        panelRadioButtons.validate();
        
        if(panelDadesJPEG.isVisible()) panelDadesJPEG.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBrowser;
    private javax.swing.JButton bComprimir;
    private javax.swing.ButtonGroup bgAlgoritmos;
    private javax.swing.ButtonGroup bgSubsampling;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jVelocitat;
    private java.awt.Label lSelectFitxer;
    private javax.swing.JLabel labelGrauCompr;
    private javax.swing.JLabel labelPercCompr;
    private javax.swing.JLabel labelSubsampling;
    private javax.swing.JLabel labelTempsCompr;
    private javax.swing.JLabel labelValorGrauCompresio;
    private javax.swing.JLabel labelVelCompr;
    private javax.swing.JPanel panelDadesJPEG;
    private javax.swing.JPanel panelEstadistiques;
    private javax.swing.JPanel panelRadioButtons;
    private javax.swing.JPanel panelSlider;
    private javax.swing.JPanel panelSubsampling;
    private javax.swing.JRadioButton radio411;
    private javax.swing.JRadioButton radio420;
    private javax.swing.JRadioButton radio422;
    private javax.swing.JRadioButton radio440;
    private javax.swing.JRadioButton radio444;
    private javax.swing.JSlider sliderJPEG;
    private javax.swing.JTextField tfPath;
    // End of variables declaration//GEN-END:variables
}


