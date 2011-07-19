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
	@Override
	public void onPlayerPreLogin(PlayerPreLoginEvent event) {
		boolean banStatus = true; //Will be changed when c-s is created
		String reason = null; //Will be changed when c-s is created
		if (banStatus == true) {
			event.disallow(PlayerPreLoginEvent.Result.KICK_BANNED, reason);
		}
	}
	
	//This activates when a player actually joins  a server.
    @Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (Settings.getBoolean("bOfflineProtection") == true) {
			event.getPlayer().sendMessage(ChatColor.DARK_GREEN
					+ "Server is secured by CommunityBans!");
		}
	}
}