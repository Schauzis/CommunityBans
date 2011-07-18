/*
 * This entire class file has been commented out because it has been melded into the
 * ban class file. I plan on putting all of the commands into one file and calling it
 * the Commands Class. I will then move this class file into the main package and remove lots of package imports.
 * package com.communitybans.actions;

import org.bukkit.ChatColor;

import com.communitybans.main.Settings;
import com.communitybans.main.CommunityBans;
@SuppressWarnings("unused")
public class kick extends Thread {
	private Settings Config;
	private CommunityBans MCBans;
	private String PlayerName = null;
	private String PlayerAdmin = null;
	private String Reason = null;

	public kick(Settings cf, CommunityBans p, String playerName,
			String playerAdmin, String reason) {
		Config = cf;
		MCBans = p;
		PlayerName = playerName;
		PlayerAdmin = playerAdmin;
		Reason = reason;
	}

	public void run() {

		if (MCBans.getServer().getPlayer(PlayerName) != null) {
			CommunityBans.sLog.info(PlayerAdmin + " has kicked " + PlayerName + "["
					+ Reason + "]");
			MCBans.getServer()
					.getPlayer(PlayerName)
					.kickPlayer(
							MCBans.language.getFormat("kickMessagePlayer",
									PlayerName, PlayerAdmin, Reason));
			MCBans.broadcastAll(ChatColor.DARK_RED
					+ MCBans.language.getFormat("kickMessageSuccess",
							PlayerName, PlayerAdmin, Reason));
		} else {
			MCBans.broadcastAll(ChatColor.DARK_RED
					+ MCBans.language.getFormat("kickMessageNoPlayer",
							PlayerName, PlayerAdmin, Reason));
		}

	}
}
*/