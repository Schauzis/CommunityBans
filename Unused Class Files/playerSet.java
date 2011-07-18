/*
 * I (supersam654) have commented out this entire section of code because I cannot
 * figure out what purpose it serves. I believe it has to deal with creating that
 * API key when you join their special server. However, in our model, you will receive
 * an API key when you activate your account from the emailed link. Also, we are
 * not calling it an API key because that makes no sense, we are just saying ID Key.
 */


/*package com.communitybans.actions;

import java.util.HashMap;

import org.bukkit.ChatColor;

import com.communitybans.communicate.jsonHandler;
import com.communitybans.main.CommunityBans;

public class playerSet extends Thread {
	private CommunityBans MCBans;
	private String PlayerName;
	private String ConfirmCode;

	public playerSet(CommunityBans p, String playerName, String confirmCode) {
		MCBans = p;
		PlayerName = playerName;
		ConfirmCode = confirmCode;
	}

	public void run() {
		HashMap<String, String> url_items = new HashMap<String, String>();
		jsonHandler webHandle = new jsonHandler(MCBans);
		url_items.put("player", PlayerName);
		url_items.put("string", ConfirmCode);
		url_items.put("exec", "playerSet");
		HashMap<String, String> response = webHandle.mainRequest(url_items);
		if (response.containsKey("result")) {
			if (!response.get("result").equals("n")) {
				MCBans.broadcastPlayer(PlayerName, ChatColor.DARK_GREEN
						+ "Welcome to MCBans: " + response.get("result"));
			} else {
				MCBans.broadcastPlayer(PlayerName, ChatColor.DARK_RED
						+ "Error, could not authenticate, sure you signed up?");
			}
		} else {
			MCBans.broadcastPlayer(
					PlayerName,
					ChatColor.DARK_RED
							+ "Error, could not authenticate, did you enter it correctly?");
		}
	}
}
*/