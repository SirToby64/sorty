/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shsviveon.sorty;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kockt
 */
public class Config {
    
    public static int BUTTON_SORTY = KeyEvent.VK_SPACE;
    public static int BUTTON_UNSORTY = KeyEvent.VK_N;
    public static Color BORDER_COLOR_SORTIED = new Color(22, 211, 0);
    public static String FOLDER_SORTIED = "sorty";
    public static int WINDOW_CURRENT_WIDTH = 1024;
    public static int WINDOW_CURRENT_HEIGHT = 768;
    public static int WINDOW_MINIMUM_WIDTH = 440;
    public static int WINDOW_MINIMUM_HEIGHT = 380;
    
    private static final String SORTY_BUTTON_SORTY = "sorty.button.sorty";
    private static final String SORTY_BUTTON_UNSORTY = "sorty.button.unsorty";
    private static final String SORTY_FOLDER_SORTIED = "sorty.folder.sortied";
    private static final String SORTY_BORDER_COLOR = "sorty.border.color";
    private static final String SORTY_WINDOW_CURRENT_WIDTH = "sorty.window.current.width";
    private static final String SORTY_WINDOW_CURRENT_HEIGHT = "sorty.window.current.height";
    private static final String SORTY_WINDOW_MINIMUM_WIDTH = "sorty.window.minimum.width";
    private static final String SORTY_WINDOW_MINIMUM_HEIGHT = "sorty.window.minimum.height";
    
    private static final File configFile = new File(System.getProperty("user.home") + "\\.sortyconf");
    
    public static void initConfig() {
        
        if (!configFile.exists()) {
            return;
        }
        
        final Properties props = new Properties();
        try {
            props.load(new FileInputStream(configFile));
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        BUTTON_SORTY = Integer.parseInt((String) props.get(SORTY_BUTTON_SORTY));
        BUTTON_UNSORTY = Integer.parseInt((String) props.get(SORTY_BUTTON_UNSORTY));
        FOLDER_SORTIED = (String) props.get(SORTY_FOLDER_SORTIED);
        final String[] rgb = ((String) props.get(SORTY_BORDER_COLOR)).split(",");
        BORDER_COLOR_SORTIED = new Color(Integer.valueOf(rgb[0]), Integer.valueOf(rgb[1]), Integer.valueOf(rgb[2]));
        WINDOW_CURRENT_WIDTH = Integer.parseInt((String) props.get(SORTY_WINDOW_CURRENT_WIDTH));
        WINDOW_CURRENT_HEIGHT = Integer.parseInt((String) props.get(SORTY_WINDOW_CURRENT_HEIGHT));
        WINDOW_MINIMUM_WIDTH = Integer.parseInt((String) props.get(SORTY_WINDOW_MINIMUM_WIDTH));
        WINDOW_MINIMUM_HEIGHT = Integer.parseInt((String) props.get(SORTY_WINDOW_MINIMUM_HEIGHT));
        
    }
    
    public static void saveConfig() {
        
        final Properties props = new Properties();
        
        props.setProperty(SORTY_BUTTON_SORTY, String.valueOf(BUTTON_SORTY));
        props.setProperty(SORTY_BUTTON_UNSORTY, String.valueOf(BUTTON_UNSORTY));
        props.setProperty(SORTY_FOLDER_SORTIED, FOLDER_SORTIED);
        final String rgb = BORDER_COLOR_SORTIED.getRed() + "," + BORDER_COLOR_SORTIED.getGreen() + "," + BORDER_COLOR_SORTIED.getBlue();
        props.setProperty(SORTY_BORDER_COLOR, rgb);
        props.setProperty(SORTY_WINDOW_CURRENT_WIDTH, String.valueOf(WINDOW_CURRENT_WIDTH));
        props.setProperty(SORTY_WINDOW_CURRENT_HEIGHT, String.valueOf(WINDOW_CURRENT_HEIGHT));
        props.setProperty(SORTY_WINDOW_MINIMUM_WIDTH, String.valueOf(WINDOW_MINIMUM_WIDTH));
        props.setProperty(SORTY_WINDOW_MINIMUM_HEIGHT, String.valueOf(WINDOW_MINIMUM_HEIGHT));
        
        try {
            props.store(new FileOutputStream(configFile, false), "Property Datei zum persistiereen/modifzieren von Sorty Einstellungen");
        } catch (IOException ex) {
            System.out.println("Exception occured: ");
            ex.printStackTrace();
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
