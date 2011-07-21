package com.communitybans.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import org.bukkit.util.config.Configuration;

/**
 * @author Hretsam
 * 
 * This class holds all strings that are bound by the language.
 * If a string is used it will be stored in a hashmap.
 * This way you dont need to load a lot of string that wont be used. (like error messages)
 * 
 */
public class Language {

    /** The default language */
    private static final String defaultLanguage = "english";
    /** The yaml file with the stored keys */
    private static Configuration languageConfig;
    /** The values of the language config when there loaded */
    private static HashMap<String, String> values;

    public static boolean languageMethod(File datafolder, String languageString) {
        // Get location of language files
        datafolder = new File(datafolder + File.separator + "language");
        // Make directory is there is none
        datafolder.mkdirs();
        // The location that should be loaded
        File languageFile = new File(datafolder + File.separator + languageString + ".yml");
        // Check if language file exists, otherwise it will revert to default
        if (!languageFile.exists()) {
            // Link to default language file
            languageFile = new File(datafolder + File.separator + defaultLanguage + ".yml");
            
            // Check if default language file exists
            // Otherwise create a new one
            if (!languageFile.exists()) {
                try {
                    // Create a new file, and copy the contents of english.yml in the default package!
                    languageFile.createNewFile();
                    // Load input stream
                    InputStream stream = CommunityBans.class.getResourceAsStream("/defaultLanguageFile.yml");
                    // load output steam
                    OutputStream out = new FileOutputStream(languageFile);

                    // Copy contents
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = stream.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }

                    // Close streams
                    try {
                        stream.close();
                        out.close();
                    } catch (IOException ioe) {
                        //ignore
                    }
                    // Report
                    CommunityBans.log.info(CommunityBans.pluginName + "New language file generated!");
                } catch (IOException ioe) {
                    CommunityBans.log.severe(CommunityBans.pluginName + "Cannot create default language file! Language error 001 " + ioe.getMessage());
                    return false;
                }
            }
        }

        // Initialize the config
        languageConfig = new Configuration(languageFile);
        // Load the config file
        languageConfig.load();
        // Initialize the hashmap that will hold loaded strings
        values = new HashMap<String, String>();

        // Return succesfull load
        return true;
    }

    /**
     * Return the value of the key
     * @param key
     * @return 
     */
    public static String getString(String key) {
        // Check if already loaded
        if (!values.containsKey(key)) {
            // Load the string
            values.put(key, languageConfig.getString(key, "Missing string! Language error 002"));
        }
        // Return value
        return values.get(key);
    }

    /**
     * Returns value of the given string where all replacements are replaced
     * @param key
     * @param replacements (replacements like %player% will be automaticly replaced
     * @return Finished string
     */
    public static String getString(String key, HashMap<String, String> replacements) {
        // Get string
        String value = getString(key);

        // Loop trough all replacement keys
        for (String repKey : replacements.keySet()) {
            // Replaces the replacement keys with the values
            value = value.replaceAll(repKey, replacements.get(repKey));
        }

        // return finished string
        return value;
    }
}