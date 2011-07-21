package com.communitybans.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.util.config.Configuration;

public class Settings {
    /*
     * This file creates the Settings file if it has not already been created.
     * In this case, it then SETS all of the properties of it automatically
     * If the file has not already been created, it just loads the settings file
     * and then proceeds to GET all of the properties so that the plugin can use
     * them. They are available globally because the variables have been initialized
     * inside of the class, not the method.
     */

    private static Configuration config;
    /*
     * As a general rule, p = premium, v = vote, b = ban (Kinda misc)
     */
    public static int pAddress;
    public static int pWebPort;
    public static int pRawPort;
    public static int vMinOnlinePlayers;
    public static int vPercentVotingPlayers;
    public static int vPercentKickRequired;
    public static boolean pEnabled;
    public static boolean pHTTPServer;
    public static boolean vEnabled;
    public static boolean vAnnounceVotes;
    public static String language;
    public static String pPass;
    public static String bDefaultBan;
    public static String bDefaultKick;
    public static String vMessageColor;
    public static String vYesColor;
    public static String vNoColor;
    
    /** Debugging setting (prints out more logs) */
    public static boolean isDebugging = true;
    

    public static boolean loadConfig(String dataFolder) {
        File configFile = new File(dataFolder + File.separator + "config.yml");
        if (!configFile.exists()) {
            try {
                // Create a new file, and copy the contents of config.yml in the default package!
                configFile.createNewFile();
                InputStream stream = CommunityBans.class.getResourceAsStream("/DefaultConfigFile.yml");
                OutputStream out = new FileOutputStream(configFile);

                byte[] buf = new byte[1024];
                int len;
                while ((len = stream.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                stream.close();
                out.close();
                CommunityBans.log.info(CommunityBans.pluginName + "Config file not found, created new file");
            } catch (IOException iex) {
                CommunityBans.log.severe(CommunityBans.pluginName + "Cannot create config file! " + iex.getMessage());
                return false;
            }
        }
        config = new Configuration(configFile);
        config.load();

//		language =  config.getString("Language", "english");
        pAddress = config.getInt("Premium.address", 0);
        pEnabled = config.getBoolean("Premium.enabled", true);
        pHTTPServer = config.getBoolean("Premium.http_server", true);
        pWebPort = config.getInt("Premium.webport", 20034);
        pRawPort = config.getInt("Premium.rawport", 20035);
        pPass = config.getString("Premium.pass", "<password>");
        bDefaultBan = config.getString("Ban.default_ban_reason", "Griefing");
        bDefaultKick = config.getString("Ban.default_kick_reason", "Profanity");
        vEnabled = config.getBoolean("Voting.enabled", true);
        vAnnounceVotes = config.getBoolean("Voting.announce_votes", true);
        vMessageColor = config.getString("Voting.message_color", "blue");
        vYesColor = config.getString("Voting.yes_color", "green");
        vNoColor = config.getString("Voting.no_color", "red");
        vMinOnlinePlayers = config.getInt("Voting.min_online_players", 4);
        vPercentVotingPlayers = config.getInt("Voting.percent_voting_players", 50);
        vPercentKickRequired = config.getInt("Voting.percent_kick_required", 70);

        return true;
    }

    public static String getString(String variable) {
        return config.getString(variable, "");
    }

    public static Integer getInteger(String variable) {
        return config.getInt(variable, 0);
    }

    public static Boolean getBoolean(String variable) {
        return config.getBoolean(variable, true);
    }
}