/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacio;

import CapaDomini.Controladors.ControladorAlgoritmes;
import CapaDomini.Controladors.ControladorComprimir;
import CapaDomini.Controladors.ControladorDescomprimir;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Juanjo
 */
public class Comparar extends javax.swing.JPanel {

    ControladorComprimir ctrComprimir;
    ControladorDescomprimir ctrDescomprimir;
    ControladorAlgoritmes ctrAlgoritmes;
    JFrame pare;
    
    public Comparar(JFrame pare) {
        initComponents();
        this.pare = pare;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bgAlgoritmos = new javax.swing.ButtonGroup();
        bgSubsampling = new javax.swing.ButtonGroup();
        lSelectFitxer = new java.awt.Label();
        tfPath = new javax.swing.JTextField();
        bBrowser = new javax.swing.JButton();
        bComparar = new javax.swing.JButton();
        panelRadioButtons = new javax.swing.JPanel();
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
        panelResultatComparacio = new javax.swing.JPanel();
        labelTextComparacio = new javax.swing.JLabel();
        bVeureFitxers = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1000, 400));
        setLayout(new java.awt.GridBagLayout());

        lSelectFitxer.setMaximumSize(new java.awt.Dimension(32767, 30));
        lSelectFitxer.setText("Selecciona un fitxer:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 0, 30);
        add(lSelectFitxer, gridBagConstraints);

        tfPath.setEditable(false);
        tfPath.setMaximumSize(new java.awt.Dimension(2147483647, 30));
        tfPath.setMinimumSize(new java.awt.Dimension(50, 30));
        tfPath.setPreferredSize(new java.awt.Dimension(50, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 0, 30);
        add(tfPath, gridBagConstraints);

        bBrowser.setText("Browser");
        bBrowser.setMaximumSize(new java.awt.Dimension(120, 50));
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
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 0, 30);
        add(bBrowser, gridBagConstraints);

        bComparar.setText("Comparar");
        bComparar.setMaximumSize(new java.awt.Dimension(120, 50));
        bComparar.setMinimumSize(new java.awt.Dimension(120, 50));
        bComparar.setVisible(false);
        bComparar.setPreferredSize(new java.awt.Dimension(120, 50));
        bComparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCompararActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 20, 30);
        add(bComparar, gridBagConstraints);

        panelRadioButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 60, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 30);
        add(panelRadioButtons, gridBagConstraints);

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

        panelResultatComparacio.setLayout(new java.awt.GridBagLayout());

        labelTextComparacio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTextComparacio.setText("Els fitxers són ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        panelResultatComparacio.add(labelTextComparacio, gridBagConstraints);

        bVeureFitxers.setText("Veure fitxers");
        bVeureFitxers.setMaximumSize(new java.awt.Dimension(200, 50));
        bVeureFitxers.setMinimumSize(new java.awt.Dimension(200, 50));
        bVeureFitxers.setPreferredSize(new java.awt.Dimension(200, 50));
        bVeureFitxers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bVeureFitxersActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        panelResultatComparacio.add(bVeureFitxers, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        add(panelResultatComparacio, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void sliderJPEGStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderJPEGStateChanged
        labelValorGrauCompresio.setText(String.valueOf(sliderJPEG.getValue()));
    }//GEN-LAST:event_sliderJPEGStateChanged

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
            bComparar.setVisible(true);
        }
    }//GEN-LAST:event_bBrowserActionPerformed

    private void bCompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCompararActionPerformed
        
    }//GEN-LAST:event_bCompararActionPerformed

    private void bVeureFitxersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bVeureFitxersActionPerformed
        pare.setContentPane(new ComparacioFitxers(this, pare));
        pare.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pare.invalidate();
        pare.validate();
    }//GEN-LAST:event_bVeureFitxersActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBrowser;
    private javax.swing.JButton bComparar;
    private javax.swing.JButton bVeureFitxers;
    private javax.swing.ButtonGroup bgAlgoritmos;
    private javax.swing.ButtonGroup bgSubsampling;
    private java.awt.Label lSelectFitxer;
    private javax.swing.JLabel labelGrauCompr;
    private javax.swing.JLabel labelSubsampling;
    private javax.swing.JLabel labelTextComparacio;
    private javax.swing.JLabel labelValorGrauCompresio;
    private javax.swing.JPanel panelDadesJPEG;
    private javax.swing.JPanel panelRadioButtons;
    private javax.swing.JPanel panelResultatComparacio;
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


    // METODOS AUXILIARES
    private void crearBotones(){
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


}
