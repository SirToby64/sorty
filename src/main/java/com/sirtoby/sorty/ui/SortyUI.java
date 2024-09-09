package com.sirtoby.sorty.ui;

import com.sirtoby.sorty.Config;
import com.sirtoby.sorty.Sorty;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class SortyUI extends JFrame {
    private BufferedImage image;
    private ImagePanel imagePanel;
    private JLabel fileNameLabel;
    private int rotationAngle = 0;
    private Sorty sorty = null;

    private static final java.util.List<Integer> REGISTERED_KEY_EVENTS = new ArrayList<>();

    public SortyUI() {
        super("Sorty");

        imagePanel = new ImagePanel();
        add(imagePanel, BorderLayout.CENTER);

        // Create buttons for rotating the image
        JButton rotateLeftButton = new JButton("Rotate Left");
        JButton rotateRightButton = new JButton("Rotate Right");

        // Add action listeners to rotate the image
        rotateLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotateImage(-90);
            }
        });

        rotateRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotateImage(90);
            }
        });

        // Panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rotateLeftButton);
        buttonPanel.add(rotateRightButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Label for filename
        fileNameLabel = new JLabel("Bitte Ordner mit Bildern auswählen");
        fileNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel to hold Label
        JPanel fileNamePanel = new JPanel();
        fileNamePanel.add(fileNameLabel);

        add(fileNameLabel, BorderLayout.NORTH);

        // File Menu
        final JMenu fileMenu = new JMenu();
        fileMenu.setMnemonic('f');
        fileMenu.setText("Datei");

        // Items for File Menu
        final JMenuItem openMenuItem = new JMenuItem();
        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Ordner öffnen...");
        openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                openFileChooser(evt);
            }
        });
        fileMenu.add(openMenuItem);

        JMenuItem exitMenuItem = new JMenuItem();
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Beenden");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        // Edit Menu
        final JMenu editMenu = new JMenu();
        editMenu.setMnemonic('e');
        editMenu.setText("Bearbeiten");

        // Items for Edit Menu
        final JMenuItem preferencesMenuItem = new JMenuItem();
        preferencesMenuItem.setMnemonic('d');
        preferencesMenuItem.setText("Einstellungen");
        preferencesMenuItem.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                openPreferencesMenu(evt);
            }
        });
        editMenu.add(preferencesMenuItem);

        // Add Menu to MenuBar
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);

        // Add KeyLister for navigation of images
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                checkKeyPressed(evt);
            }
        });
        setFocusable(true);
        requestFocusInWindow();
        
        // register Key Events
        REGISTERED_KEY_EVENTS.add(KeyEvent.VK_RIGHT);
        REGISTERED_KEY_EVENTS.add(KeyEvent.VK_LEFT);
        REGISTERED_KEY_EVENTS.add(Config.BUTTON_SORTY);
        REGISTERED_KEY_EVENTS.add(Config.BUTTON_UNSORTY);

        // Set up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void rotateImage(int angle) {
        rotationAngle = (rotationAngle + angle) % 360;
        imagePanel.setRotationAngle(rotationAngle);
    }

    private void openFileChooser(ActionEvent evt) {
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(this);

        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return;
        }

        sorty = new Sorty(chooser.getSelectedFile());
        sorty.readImages();
        image = sorty.nextImage();
        imagePanel.setImage(image);
        imagePanel.repaint();
        fileNameLabel.setText(sorty.getCurrentFile().getName());

        if (sorty.isSortied()) {
            imagePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Config.BORDER_COLOR_SORTIED));
        }

    }

    private void openPreferencesMenu(MouseEvent evt) {
        System.out.println("test");
        SortyOptionUI.getInstance().setVisible(true);
        Config.saveConfig();
    }

    private void checkKeyPressed(KeyEvent evt) {

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

            fileNameLabel.setText(sorty.getCurrentFile().getName());
            imagePanel.setImage(image);
            imagePanel.repaint();
        }
        else if (evt.getKeyCode() == Config.BUTTON_SORTY) {
            if (sorty.isSortied()) {
                sorty.unsortyFile();
            }
            else {
                sorty.sortyFile();
            }
        }
        else if (evt.getKeyCode() == Config.BUTTON_UNSORTY) {
            sorty.unsortyFile();
        }

        if (sorty.isSortied()) {
            imagePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Config.BORDER_COLOR_SORTIED));
        }
        else {
            imagePanel.setBorder(null);
        }
    }

    public static void main(String[] args) {
        
        // Set look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SortyUIOld.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SortyUI();
            }
        });
    }
}

class ImagePanel extends JPanel {
    private BufferedImage image;
    private int rotationAngle;


    public ImagePanel() {
        this.image = null;
        this.rotationAngle = 0;
    }

    public ImagePanel(BufferedImage image) {
        this.image = image;
        this.rotationAngle = 0;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setRotationAngle(int rotationAngle) {
        this.rotationAngle = rotationAngle;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g.create();

        // Calculate the rotated image dimensions
        int iw = image.getWidth();
        int ih = image.getHeight();

        if (rotationAngle == 90 || rotationAngle == 270) {
            // Swap width and height for 90 or 270 degrees rotation
            int temp = iw;
            iw = ih;
            ih = temp;
        }

        // Calculate the scaling factor to fit the rotated image within the panel
        double scaleFactor = Math.min((double) getWidth() / iw, (double) getHeight() / ih);

        // Calculate the translation needed to center the image
        AffineTransform transform = new AffineTransform();
        transform.translate(getWidth() / 2, getHeight() / 2);
        transform.rotate(Math.toRadians(rotationAngle));
        transform.scale(scaleFactor, scaleFactor);
        transform.translate(-image.getWidth() / 2, -image.getHeight() / 2);

        // Draw the transformed image
        g2d.drawImage(image, transform, this);
        g2d.dispose();
    }
}
