package com.communitybans.actions;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.json.JSONException;
import org.json.JSONObject;

import com.communitybans.main.CommunityBans;
import com.communitybans.main.Language;
import com.communitybans.communicate.jsonHandler;

public class ban extends Thread {
	private CommunityBans CB;
	private String playerBanned = null;
	private String playerAdmin = null;
	private String reason = null;
	private String action = null;
	private String duration = null;
	private String message = null;
	private HashMap<String, Integer> banHashMap = new HashMap<String, Integer>();

	public ban(CommunityBans p, String action, String playerBanned,
			String playerAdmin, String reason,
			String duration, String message) {
		CB = p;
		this.playerBanned = playerBanned;
		this.playerAdmin = playerAdmin;
		this.reason = reason;
		this.duration = duration;
		this.message = message;
		this.action = action;
		banHashMap.put("community", 0);
		banHashMap.put("server", 1);
		banHashMap.put("temp", 2);
		banHashMap.put("unBan", 3);
	}

	public void run() {
		switch (banHashMap.get(action)) {
		case 0:
			communityBan();
			break;
		case 1:
			serverBan();
			break;
		case 2:
			tempBan();
			break;
		case 3:
			unBan();
			break;
		case 4:
			kick();
			break;
		}
	}

	public void unBan() {
		jsonHandler webHandle = new jsonHandler(CB);
		HashMap<String, String> url_items = new HashMap<String, String>();
		url_items.put("player", playerBanned);
		url_items.put("admin", playerAdmin);
		url_items.put("exec", "unBan");
		HashMap<String, String> response = webHandle.mainRequest(url_items);
		if (!response.containsKey("result")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat("unBanMessageError",
									playerBanned, playerAdmin));
			return;
		}
		if (response.get("result").equals("y")) {
			CommunityBans.sLog.info(playerAdmin + " unbanned " + playerBanned + "!");
			CB.broadcastBanView(ChatColor.DARK_RED
					+ CB.language.getFormat("unBanMessageSuccess",
							playerBanned, playerAdmin));
			return;
		} else if (response.get("result").equals("e")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat("unBanMessageError",
									playerBanned, playerAdmin));
		} else if (response.get("result").equals("s")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat("unBanMessageGroup",
									playerBanned, playerAdmin));
		} else if (response.get("result").equals("n")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat("unBanMessageNot",
									playerBanned, playerAdmin));
		}
		CommunityBans.sLog.info(playerAdmin + " tried to unban " + playerBanned + "!");
	}

	public void serverBan() {
		jsonHandler webHandle = new jsonHandler(CB);
		HashMap<String, String> url_items = new HashMap<String, String>();
		url_items.put("player", playerBanned);
//		url_items.put("playerip");
		url_items.put("reason", reason);
		url_items.put("admin", playerAdmin);
		url_items.put("exec", "localBan");
		HashMap<String, String> response = webHandle.mainRequest(url_items);
		if (!response.containsKey("result")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat("localBanMessageError",
									playerBanned, playerAdmin, reason));
			return;
		}
		if (response.get("result").equals("y")) {
			CommunityBans.sLog.info(playerBanned
					+ " has been banned with a local type ban [" + reason
					+ "] [" + playerAdmin + "]!");
			if (CB.getServer().getPlayer(playerBanned) != null) {
				CB.getServer()
						.getPlayer(playerBanned)
						.kickPlayer(
								CB.language.getFormat(
										"localBanMessagePlayer", playerBanned,
										playerAdmin, reason));
			}
			CB.broadcastAll(ChatColor.DARK_RED
					+ CB.language.getFormat("localBanMessageSuccess",
							playerBanned, playerAdmin, reason));
			return;
		} else if (response.get("result").equals("e")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat("localBanMessageError",
									playerBanned, playerAdmin, reason));
		} else if (response.get("result").equals("s")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat("localBanMessageGroup",
									playerBanned, playerAdmin, reason));
		} else if (response.get("result").equals("a")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat(
									"localBanMessageAlready", playerBanned,
									playerAdmin, reason));
		}
		CommunityBans.sLog.info(playerAdmin + " has tried to ban " + playerBanned
				+ " with a local type ban [" + reason + "]!");
	}

	public void communityBan() {
		jsonHandler webHandle = new jsonHandler(CB);
		HashMap<String, String> url_items = new HashMap<String, String>();
		url_items.put("player", playerBanned);
		url_items.put("reason", reason);
		url_items.put("admin", playerAdmin);
		url_items.put("exec", "globalBan");
		HashMap<String, String> response = webHandle.mainRequest(url_items);
		if (!response.containsKey("result")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat(
									"globalBanMessageError", playerBanned,
									playerAdmin, reason));
			return;
		}
		if (response.get("result").equals("y")) {
			CommunityBans.sLog.info(playerBanned
					+ " has been banned with a global type ban [" + reason
					+ "] [" + playerAdmin + "]!");
			if (CB.getServer().getPlayer(playerBanned) != null) {
				CB.getServer()
						.getPlayer(playerBanned)
						.kickPlayer(
								CB.language.getFormat(
										"globalBanMessagePlayer", playerBanned,
										playerAdmin, reason));
			}
			CB.broadcastAll(ChatColor.DARK_RED
					+ CB.language.getFormat("globalBanMessageSuccess",
							playerBanned, playerAdmin, reason));
			return;
		} else if (response.get("result").equals("e")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat(
									"globalBanMessageError", playerBanned,
									playerAdmin, reason));
		} else if (response.get("result").equals("s")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat(
									"globalBanMessageGroup", playerBanned,
									playerAdmin, reason));
		} else if (response.get("result").equals("a")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat(
									"globalBanMessageAlready", playerBanned,
									playerAdmin, reason));
		}
		CommunityBans.sLog.info(playerAdmin + " has tried to ban " + playerBanned
				+ " with a global type ban [" + reason + "]!");
	}

	public void tempBan() {
		jsonHandler webHandle = new jsonHandler(CB);
		HashMap<String, String> url_items = new HashMap<String, String>();
		url_items.put("player", playerBanned);
		url_items.put("reason", reason);
		url_items.put("admin", playerAdmin);
		url_items.put("duration", duration);
		url_items.put("message", message);
		url_items.put("exec", "tempBan");
		HashMap<String, String> response = webHandle.mainRequest(url_items);
		if (!response.containsKey("result")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat("tempBanMessageError",
									playerBanned, playerAdmin, reason));
			return;
		}
		if (response.get("result").equals("y")) {
			CommunityBans.sLog.info(playerBanned
					+ " has been banned with a global type ban [" + reason
					+ "] [" + playerAdmin + "]!");
			if (CB.getServer().getPlayer(playerBanned) != null) {
				CB.getServer()
						.getPlayer(playerBanned)
						.kickPlayer(
								CB.language.getFormat(
										"tempBanMessagePlayer", playerBanned,
										playerAdmin, reason));
			}
			CB.broadcastAll(ChatColor.DARK_RED
					+ CB.language.getFormat("tempBanMessageSuccess",
							playerBanned, playerAdmin, reason));
			return;
		} else if (response.get("result").equals("e")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat("tempBanMessageError",
									playerBanned, playerAdmin, reason));
		} else if (response.get("result").equals("s")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat("tempBanMessageGroup",
									playerBanned, playerAdmin, reason));
		} else if (response.get("result").equals("a")) {
			CB.broadcastPlayer(
					playerAdmin,
					ChatColor.DARK_RED
							+ CB.language.getFormat(
									"tempBanMessageAlready", playerBanned,
									playerAdmin, reason));
		}
		CommunityBans.sLog.info(playerAdmin + " has tried to ban " + playerBanned
				+ " with a temp type ban [" + reason + "]!");
	}
	public void kick() {
		//I use playerBanned here becasue it is really just the player that is having the
		//action done to them, even though I understand that it says (playerBANNED)
		if (CB.getServer().getPlayer(playerBanned) != null) {
			CommunityBans.sLog.info(playerAdmin + " has kicked " + playerBanned + "["
					+ reason + "]");
			CB.getServer()
					.getPlayer(playerBanned)
					.kickPlayer(
							CB.language.getFormat("kickMessagePlayer",
									playerBanned, playerAdmin, reason));
			CB.broadcastAll(ChatColor.DARK_RED
					+ CB.language.getFormat("kickMessageSuccess",
							playerBanned, playerAdmin, reason));
		} else {
			CB.broadcastAll(ChatColor.DARK_RED + CB.language.getFormat("kickMessageNoPlayer",	playerBanned, playerAdmin, reason));
		}
	}
	public void lookup() {
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