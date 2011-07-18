package com.communitybans.main;

import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPreLoginEvent;

public class CBPlayerListener extends PlayerListener {
	public CommunityBans CB;

	public void playerListener(CommunityBans plugin) {
		CB = plugin;
	}
	//This gets called right before a player joins a server.
	
	public void onPlayerPreLogin(PlayerPreLoginEvent event) {
		boolean banStatus = true; //Will be changed when c-s is created
		String reason = null; //Will be changed when c-s is created
		if (banStatus == true) {
			event.disallow(PlayerPreLoginEvent.Result.KICK_BANNED, reason);
		}
	}
/*    @Override
	public void onPlayerPreLogin(PlayerPreLoginEvent event) {
		String playerIP = event.getAddress().getHostAddress();
		String player = event.getName();
		connect playerConnect = new connect(MCBans);
//		String result = playerConnect.exec(player, playerIP); <-- Prevent event sending
//		if (result != null) {
//			event.disallow(PlayerPreLoginEvent.Result.KICK_BANNED, result);
//		}
	}
*/

	//This activates when a player actually joins  a server.
    @Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (Settings.getBoolean("bOfflineProtection") == true) {
			event.getPlayer().sendMessage(ChatColor.DARK_GREEN
					+ "Server is secured by CommunityBans!");
		}
	}
//This has been commented out because there is no purpose for our plugin to check when
//Someone leaves the server. If this functionality is needed, then it can be added.
/*	
	//This is called when a player leaves the server to free up recourses.
    @Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		String playerName = event.getPlayer().getName();
		disconnect disconnectHandler = new disconnect(MCBans, playerName);
//		disconnectHandler.run(); <-- Prevent event sending
	}
*/
}