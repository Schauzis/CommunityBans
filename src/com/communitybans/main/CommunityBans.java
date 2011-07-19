package com.communitybans.main;

import com.communitybans.main.permission.PermissionManager;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CommunityBans extends JavaPlugin {

    public Language language = null;
    /** Main thread */
    private mainCallBack callbackThread = null;
 //   String version = getDescription().getVersion();
    
    /** Command handler */
	public static String playerBanned = null;
	public static String playerAdmin = null;
	public static String reason = null;
//	public String action = null;
	public static String duration = null;
	public static String message = null;
	
	
    /** Playerlistener */
    private CBPlayerListener pListener = new CBPlayerListener();
    /** Handler for permissions */
    private PermissionManager permManager;
    private static CommunityBans plugin;
    /** Plugin name prefix "[<pluginname>] " */
    public String pluginName = "[CommunityBans v" + getDescription().getVersion() + "]";
    /** Used to log things to the server log file */
    public final static Logger log = Logger.getLogger("Minecraft");
    /** Publicly initializes the ServerBanFile */
    File serverBanFile = null;

    @Override
    public void onDisable() {
        log.warning("CommunityBans is disabled!");
        if (callbackThread != null && callbackThread.isAlive()) {
            callbackThread.interrupt();
        }
    }

    @Override
    public void onEnable() {
        // Pluginmanager
        PluginManager pm = getServer().getPluginManager();
        
    	// Check for offline mode
        if (!getServer().getOnlineMode()) { // Stops the plugin if the server is not
            // running in online mode
            log.warning(pluginName + " cannot function while in offline mode.");
            pm.disablePlugin(this);
            return;
        }
        
//        // Sets the prefix [CommunityBans]
//        plugin_name_prefix = "[" + getDescription().getName() + "] ";
        
        //Sets up the language file
        Language.languageMethod("english.yml");
        
        //Sets up the settings file
        Settings.loadConfig(getDataFolder());

        //Sets up the serverBan file.
        File serverBanFile = new File(getDataFolder() + File.separator + "serverBan.txt");
        if (!serverBanFile.exists()) {
            try {
                // Create a new file if it already doesn't exist.
                serverBanFile.createNewFile();
            } catch (IOException e) {
            	log.severe(pluginName + " was unable to create the ServerBans file!");
            	e.printStackTrace();
            }
        }
        // Set plugin reference
        plugin = this;

        // Makes the data folder
        getDataFolder().mkdirs();

        // Load settings
        if (!Settings.loadConfig(this.getDataFolder())) {
            pm.disablePlugin(this);
        }



        // Registers events for the plugin to use
        pm.registerEvent(Event.Type.PLAYER_JOIN, pListener, Priority.High, this);
//        pm.registerEvent(Event.Type.PLAYER_PRELOGIN, pListener, Priority.High, this); <-- Will uncomment when c-s is established
        pm.registerEvent(Event.Type.PLAYER_CHAT, pListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, pListener, Priority.High, this);

        // Loads the permission handler
        permManager = new PermissionManager(plugin);
        
//        command = new Commands();

        callbackThread = new mainCallBack(this);
//        callbackThread.start(); < dont start the thread, it will start sending requests

        log.info("CommunityBans v" + getDescription().getVersion() + " is now active!");
    }

    //Deals with in-game commands -- Not currently working
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			playerBanned = null;
			playerAdmin = sender.toString();
			reason = null;
//			private String action = null;
				duration = null;
			message = null;
			Player player = (Player) sender;
			if (label.equalsIgnoreCase("communityban") && getPermissionManager().hasPermission(player, "communitybans.ban.community")) {
				Commands.communityBan();
			}
			if (label.equalsIgnoreCase("serverban") && getPermissionManager().hasPermission(player, "communitybans.ban.server")) {
				Commands.serverBan();
			}
			if (label.equalsIgnoreCase("tempban") && getPermissionManager().hasPermission(player, "communitybans.ban.temp")) {
				Commands.tempBan();
			}
			if (label.equalsIgnoreCase("unban") && getPermissionManager().hasPermission(player, "communitybans.ban.unban")) {
				Commands.unBan();
			}
			if (label.equalsIgnoreCase("lookup") && getPermissionManager().hasPermission(player, "communitybans.ban.lookup")) {
				Commands.lookup();
			}
			if (label.equalsIgnoreCase("kick") && getPermissionManager().hasPermission(player,  "communitybans.kick")) {
				Commands.kick();
			}
        }
		return false;
	}

    /**
     * Returns the plugin itself
     * @return 
     */
    public static CommunityBans getPlugin() {
        return plugin;
    }

    /**
     * Returns the permission manager
     * @return 
     */
    public static PermissionManager getPermissionManager() {
        return plugin.permManager;
    }

    /**
     * Retuns the settings
     * @return 
     */
 //   public static Settings getSettings() {
 //       return plugin.getSettings();
 //   }

    /**
     * Logs the message
     * Auto adds '[<pluginname>] '(default) prefix before it. 
     * 
     * @param message
     * @param set to true if its an error
     */

    public void adminMessage(String msg, String perm) {
        for (Player admin : this.getServer().getOnlinePlayers()) {
            if (getPermissionManager().hasPermission(admin, perm)) {
                admin.sendMessage(pluginName + " " + msg);
            }
        }
    }
/*
    public void broadcastAll(String msg) {
        for (Player player : this.getServer().getOnlinePlayers()) {
            player.sendMessage(Settings.getString("bprefix") + " " + msg);
        }
    }

    public void broadcastPlayer(String Player, String msg) {
        Player target = this.getServer().getPlayer(Player);
        if (target != null) {
            target.sendMessage(Settings.getString("prefix") + " " + msg);
        } else {
            System.out.print(Settings.getString("prefix") + " " + msg);
        }
    }

    public void broadcastPlayer(Player target, String msg) {
        target.sendMessage(Settings.getString("prefix") + " " + msg);
    }
*/
}