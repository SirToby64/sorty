/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sirtoby.sorty.ui;

import com.sirtoby.sorty.Config;
import com.sirtoby.sorty.Sorty;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

/**
 *
 * @author kockt
 */
public class SortyUI extends javax.swing.JFrame {
    
    private Sorty sorty = null;
    private int currentRotation = 0;
    
    private static final String NAV_RIGHT = "NAV_RIGHT";
    private static final List<Integer> REGISTERED_KEY_EVENTS = new ArrayList<>();
    
    private enum RotationDirection { LEFT, RIGHT }

    /**
     * Creates new form SortyUI
     */
    public SortyUI() {
        initComponents();
        REGISTERED_KEY_EVENTS.add(KeyEvent.VK_RIGHT);
        REGISTERED_KEY_EVENTS.add(KeyEvent.VK_LEFT);
        REGISTERED_KEY_EVENTS.add(Config.BUTTON_SORTY);
        REGISTERED_KEY_EVENTS.add(Config.BUTTON_UNSORTY);
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

        jLabelContentImage = new javax.swing.JLabel();
        jPanelButtons = new javax.swing.JPanel();
        jButtonRotateLeft = new javax.swing.JButton();
        jButtonRotateRight = new javax.swing.JButton();
        jLabelFilename = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        preferencesMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sorty");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setLocationByPlatform(true);
        setMinimumSize(new Dimension(Config.WINDOW_MINIMUM_WIDTH, Config.WINDOW_MINIMUM_HEIGHT));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_RIGHT));
        jLabelContentImage.getActionMap().put(NAV_RIGHT, new NavigateImagesAction());
        jLabelContentImage.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, NAV_RIGHT);
        getContentPane().add(jLabelContentImage, java.awt.BorderLayout.CENTER);

        jPanelButtons.setLayout(new java.awt.GridBagLayout());

        jButtonRotateLeft.setFocusable(false);
        jButtonRotateLeft.setLabel("Links drehen");
        jButtonRotateLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRotateLeftActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanelButtons.add(jButtonRotateLeft, gridBagConstraints);

        jButtonRotateRight.setFocusable(false);
        jButtonRotateRight.setLabel("Rechts drehen");
        jButtonRotateRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRotateRightActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanelButtons.add(jButtonRotateRight, gridBagConstraints);

        jLabelFilename.setText("Keine Datei ausgewählt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        jPanelButtons.add(jLabelFilename, gridBagConstraints);

        getContentPane().add(jPanelButtons, java.awt.BorderLayout.PAGE_END);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Datei");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Ordner öffnen...");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Beenden");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Bearbeiten");

        preferencesMenuItem.setMnemonic('d');
        preferencesMenuItem.setText("Einstellungen");
        preferencesMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                preferencesMenuItemMouseReleased(evt);
            }
        });
        editMenu.add(preferencesMenuItem);

        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(this);
        
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return;
        }
        
        sorty = new Sorty(chooser.getSelectedFile());
        sorty.readImages();
        scaleImage(sorty.nextImage());
        jLabelFilename.setText(sorty.getCurrentFile().getName());
        
        if (sorty.isSortied()) {
            jLabelContentImage.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Config.BORDER_COLOR_SORTIED));
        }
        
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
        if (sorty == null) {
            return;
        }
        
        if (!REGISTERED_KEY_EVENTS.contains(evt.getKeyCode())) {
            return;
        }
        
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT || evt.getKeyCode() == KeyEvent.VK_LEFT) {
            BufferedImage image;
            if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                image = sorty.nextImage();
            }
            else {
                image = sorty.previousImage();
            }
            
            if (image == null) {
                return;
            }
            
            scaleImage(image);
            jLabelFilename.setText(sorty.getCurrentFile().getName());
        }
        else if (evt.getKeyCode() == Config.BUTTON_SORTY) {
            sorty.sortyFile();
        }
        else if (evt.getKeyCode() == Config.BUTTON_UNSORTY) {
            sorty.unsortyFile();
        }
        
        if (sorty.isSortied()) {
            jLabelContentImage.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Config.BORDER_COLOR_SORTIED));
        }
        else {
            jLabelContentImage.setBorder(null);
        }
    }//GEN-LAST:event_formKeyPressed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        if (jLabelContentImage.getIcon() != null) {
            scaleImage(sorty.getCurrentImage());
        }
        Config.WINDOW_CURRENT_HEIGHT = (int) getSize().getHeight();
        Config.WINDOW_CURRENT_WIDTH = (int) getSize().getWidth();
        Config.saveConfig();
    }//GEN-LAST:event_formComponentResized

    private void preferencesMenuItemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_preferencesMenuItemMouseReleased
        System.out.println("test");
        SortyOptionUI.getInstance().setVisible(true);
        Config.saveConfig();
    }//GEN-LAST:event_preferencesMenuItemMouseReleased

    private void jButtonRotateLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRotateLeftActionPerformed
        System.out.println("rotateLeft");
        rotateImage(RotationDirection.LEFT);
    }//GEN-LAST:event_jButtonRotateLeftActionPerformed

    private void jButtonRotateRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRotateRightActionPerformed
        System.out.println("rotateRight");
        rotateImage(RotationDirection.RIGHT);
    }//GEN-LAST:event_jButtonRotateRightActionPerformed
    
    private void rotateImage(RotationDirection rotationDirection) {
        
        int rotation = 0;
        
        if (rotationDirection.equals(RotationDirection.LEFT)) {
            rotation = currentRotation - 90;
        }
        else if (rotationDirection.equals(RotationDirection.RIGHT)) {
            rotation = currentRotation + 90;
        }
        
        if (rotation >= 360 || rotation < 0) {
            rotation = 0;
        }
        
        scaleImage(sorty.getCurrentImage(), rotation);
        currentRotation = rotation;
    }
    
    private void scaleImage(BufferedImage image) {
        scaleImage(image, 0);
    }
    
    private void scaleImage(BufferedImage image, int degree) {
        final int imageWidth = image.getWidth(jLabelContentImage);
        final int imageHeight = image.getHeight(jLabelContentImage);
        
        if (jLabelContentImage.getWidth() < imageWidth || jLabelContentImage.getHeight() < imageHeight) {
            /*
                Todo: the image rescaling here seems to be incorrect in case of 90 and 270 degree. Play around with height and width
            */
            double percentage = -1.0;
            double widthPercentage = (double) jLabelContentImage.getWidth() / (double) imageWidth;
            double heightPercentage = (double) jLabelContentImage.getHeight() / (double) imageHeight;
            
            if (widthPercentage < 1.0 && widthPercentage < heightPercentage) {
                percentage = widthPercentage;
            } else if (heightPercentage < 1.0 && heightPercentage < widthPercentage) {
                percentage = heightPercentage;
            }
            
            final int width = (int) (imageWidth * percentage);
            final int height = (int) (imageHeight * percentage);
            
            final BufferedImage resizedImage;
            if (degree == 0 || degree == 180) {
                resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            }
            else {
                resizedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
            }
            
            final Graphics2D g2 = resizedImage.createGraphics();
            
            final HashMap<RenderingHints.Key, Object> hintMap = new HashMap<>();
            hintMap.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            hintMap.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
            hintMap.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            hintMap.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            
            g2.setRenderingHints(hintMap);
            if (degree > 0) {
                double angle = Math.toRadians(degree);
//                double sin = Math.abs(Math.sin(angle));
//                double cos = Math.abs(Math.cos(angle));
//                
//                int newWidth = (int) Math.floor(width*cos + height*sin);
//                int newHeight = (int) Math.floor(height*cos + width*sin);
//                
//                g2.translate((newWidth - width)/2, (newHeight - height)/2);
                if (degree == 180) {
                    g2.rotate(angle);
                }
                else {
                    g2.rotate(angle, width/2, height/2);
                }
            }
            
            if (degree == 0) {
                g2.drawImage(image, 0, 0, width, height, null);
            }
            else if (degree == 90) {
                g2.drawImage(image, 0, -height, width, height, null);
            }
            else if (degree == 180) {
                g2.drawImage(image, -width, -height, width, height, null);
            }
            else if (degree == 270) {
                g2.drawImage(image, 0, -height, width, height, null);
            }

            g2.dispose();
            
            image = resizedImage;
        }
        jLabelContentImage.setIcon(new ImageIcon(image));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        Config.initConfig();
        
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
            java.util.logging.Logger.getLogger(SortyUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SortyUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SortyUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SortyUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SortyUI().setVisible(true);
            }
        });
    }
    
    class NavigateImagesAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("e.getActionCommand() = " + e.getActionCommand());
//            navigateImages(e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton jButtonRotateLeft;
    private javax.swing.JButton jButtonRotateRight;
    private javax.swing.JLabel jLabelContentImage;
    private javax.swing.JLabel jLabelFilename;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem preferencesMenuItem;
    // End of variables declaration//GEN-END:variables

}
