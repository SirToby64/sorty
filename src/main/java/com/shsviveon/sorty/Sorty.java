/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shsviveon.sorty;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author kockt
 */
public class Sorty {
    
    private final List<File> fileList = new ArrayList<>();
    private ListIterator<File> iter = null;
    private final File directory;
    private boolean lastDirRight = true;
    private BufferedImage currentImage = null;
    private File currentFile = null;
    
    public Sorty(File directory) {
        this.directory = directory;
    }
    
    public void readImages() {
        fileList.addAll(Arrays.asList(directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                final String lowerName = name.toLowerCase();
                return lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg") || lowerName.endsWith(".png");
            }
        })));
        iter = fileList.listIterator();
    }
    
    public BufferedImage nextImage() {
        if (iter.hasNext()) {
            if (!lastDirRight) {
                lastDirRight = true;
                iter.next();
            }
            return getImage(iter.next());
        }
        return null;
    }
    
    public BufferedImage previousImage() {
        if (iter.hasPrevious()) {
            if (lastDirRight) {
                lastDirRight = false;
                iter.previous();
            }
            return getImage(iter.previous());
        }
        return null;
    }
    
    public BufferedImage getCurrentImage() {
        return this.currentImage;
    }
    
    public File getCurrentFile() {
        return this.currentFile;
    }
    
    private BufferedImage getImage(File file) {
        try {
            final BufferedImage image = ImageIO.read(file);
            currentImage = image;
            currentFile = file;
            return image;
        } catch (IOException ex) {
            Logger.getLogger(Sorty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void sortyFile() {
        final File sortyDir = new File(directory, "sorty");
        if (!sortyDir.exists()) {
            sortyDir.mkdir();
        }
        
        final File selectedFile = new File(sortyDir, currentFile.getName());
        if (!selectedFile.exists()) {
            try {
                Files.copy(currentFile.toPath(), selectedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(Sorty.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void unsortyFile() {
        final File sortyDir = new File(directory, Config.FOLDER_SORTIED);
        if (!sortyDir.exists()) {
            return;
        }
        
        final File selectedFile = new File(sortyDir, currentFile.getName());
        if (selectedFile.exists()) {
            selectedFile.delete();
        }
    }
    
    public boolean isSortied() {
        final File sortyDir = new File(directory, Config.FOLDER_SORTIED);
        if (!sortyDir.exists()) {
            return false;
        }
        
        final File selectedFile = new File(sortyDir, currentFile.getName());
        return selectedFile.exists();
    }
}
