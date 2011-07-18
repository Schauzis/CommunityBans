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
    static int pAddress;
    static int pWebPort;
    static int pRawPort;
    static int vMinOnlinePlayers;
    static int vPercentVotingPlayers;
    static int vPercentKickRequired;
    static boolean pEnabled;
    static boolean pHTTPServer;
    static boolean bLoginNotify;
    static boolean bBanNotify;
    static boolean bShowAlerts;
    static boolean bOfflineProtection;
    static boolean vEnabled;
    static boolean vAnnounceVotes;
    static String language;
    static String pPass;
    static String bPrefix;
    static String bDefaultBan;
    static String bDefaultKick;
    static String vMessageColor;
    static String vYesColor;
    static String vNoColor;

    public static boolean loadConfig(File dataFolder) {
        File configFile = new File(dataFolder + File.separator + "config.yml");
        if (!configFile.exists()) {
            try {
                // Create a new file, and copy the contents of config.yml in the default package!
                configFile.createNewFile();
                InputStream stream = CommunityBans.class.getResourceAsStream("/config.yml");
                OutputStream out = new FileOutputStream(configFile);

                byte[] buf = new byte[1024];
                int len;
                while ((len = stream.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                stream.close();
                out.close();
                CommunityBans.log.info("Config file not found, created new file");
            } catch (IOException iex) {
                CommunityBans.log.severe("Cannot create config file! " + iex.getMessage());
                return false;
            }
//			try {
//				configFile.createNewFile();
//				config.load();
////				config.setProperty("Language", "English");
//				config.setProperty("Premium.address", 0);
//				config.setProperty("Premium.enabled", true);
//				config.setProperty("Premium.http_server", true);
//				config.setProperty("Premium.webport", 20034);
//				config.setProperty("Premium.rawport", 20035);
//				config.setProperty("Premium.pass", "<password>");
//				config.setProperty("Ban.login_notify", true);
//				config.setProperty("Ban.ban_notify", true);
//				config.setProperty("Ban.show_alerts", true);
//				config.setProperty("Ban.prefix", "[C] ");
//				config.setProperty("Ban.default_ban_reason", "Griefing");
//				config.setProperty("Ban.default_kick_reason", "Profanity");
//				config.setProperty("Ban.offline_protection", true);
//				config.setProperty("Voting.enabled", true);
//				config.setProperty("Voting.announce_votes", true);
//				config.setProperty("Voting.message_color", "blue");
//				config.setProperty("Voting.yes_color", "green");
//				config.setProperty("Voting.no_color", "red");
//				config.setProperty("Voting.min_online_players", 4);
//				config.setProperty("Voting.percent_voting_players", 50);
//				config.setProperty("Voting.percent_kick_required", 70);
//				config.save();
//			} catch (Exception e) {
//				CommunityBans.log.info(bPrefix + " Error when creating config file.");
//			}
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
        bLoginNotify = config.getBoolean("Ban.login_notify", true);
        bBanNotify = config.getBoolean("Ban.ban_notify", true);
        bShowAlerts = config.getBoolean("Ban.show_alerts", true);
        bPrefix = config.getString("Ban.prefix", "[CommunityBans] ");
        bDefaultBan = config.getString("Ban.default_ban_reason", "Griefing");
        bDefaultKick = config.getString("Ban.default_kick_reason", "Profanity");
        bOfflineProtection = config.getBoolean("Ban.offline_protection", true);
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