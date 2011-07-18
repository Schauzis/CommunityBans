/*
 * This entire class file has been commented out because it has been melded into the
 * ban class file. I plan on putting all of the commands into one file and calling it
 * the Commands Class. I will then move this class file into the main package and remove lots of package imports.
 * package com.communitybans.actions;
 * 
 * package com.communitybans.actions;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.json.JSONException;
import org.json.JSONObject;

import com.communitybans.main.CommunityBans;
import com.communitybans.communicate.jsonHandler;

public class lookup extends Thread {
	private CommunityBans MCBans;
	private String PlayerName;
	private String PlayerAdmin;

	public lookup(CommunityBans p, String playerName, String playerAdmin) {
		MCBans = p;
		PlayerName = playerName;
		PlayerAdmin = playerAdmin;
	}

	public void run() {
		CommunityBans.sLog.info(PlayerAdmin + " has looked up the " + PlayerName + ".");
		HashMap<String, String> url_items = new HashMap<String, String>();
		jsonHandler webHandle = new jsonHandler(MCBans);
		url_items.put("player", PlayerName);
		url_items.put("admin", PlayerAdmin);
		url_items.put("exec", "playerLookup");
		JSONObject result = webHandle.hdl_jobj(url_items);
		String totalBans = null;
		try {
			totalBans = result.getString("total");
		} catch (JSONException e1) {
			CommunityBans.sLog.warning("CommunityBans has encountered an error. Please report error 002 to the CommunityBans staff. Thank you.");
			e1.printStackTrace();
		}
		String banTense = "ban";
		if (Integer.parseInt(totalBans) > 1) {
			banTense = "bans";
		}
		try {
			MCBans.broadcastPlayer(PlayerAdmin, ChatColor.YELLOW + PlayerName
					+ " has " + totalBans + banTense);
			for (int v = 0; v < result.getJSONArray("local").length(); v++) {
				MCBans.broadcastPlayer(PlayerAdmin, "[Local] " + ChatColor.AQUA
						+ result.getJSONArray("local").getString(v));
			}
			for (int v = 0; v < result.getJSONArray("global").length(); v++) {
				MCBans.broadcastPlayer(PlayerAdmin, "[Global] "
						+ ChatColor.DARK_RED
						+ result.getJSONArray("global").getString(v));
			}
		} catch (JSONException e) {
		}
	}
}
*/