package com.communitybans.main;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.json.JSONException;
import org.json.JSONObject;

import com.communitybans.communicate.jsonHandler;

public class Commands extends Thread {
	private static CommunityBans CB;
	private static String playerAction = null;
	private static String playerAdmin = null;
	private static String reason = null;
//	private String action = null;
	private static String duration = null;
	private static String message = null;
//	private HashMap<String, Integer> banHashMap = new HashMap<String, Integer>();
/*
 * This code is never referenced so I see no reason to keep it.
 * 
	public static void ban(CommunityBans p, String playerAction,
			String playerAdmin, String reason,
			String duration, String message) {
		CB = p;
		Commands.playerAction = playerAction;
		Commands.playerAdmin = playerAdmin;
		Commands.reason = reason;
		Commands.duration = duration;
		Commands.message = message;
//		this.action = action;
	}
*/ /** The unBan() method can undo a server ban, but not a community ban */
	public static void unBan() {
		String status = null;
		String isSuccess = null;
		jsonHandler webHandle = new jsonHandler(CB);
		HashMap<String, String> url_items = new HashMap<String, String>();
		url_items.put("player", playerAction);
		url_items.put("admin", playerAdmin);
		url_items.put("exec", "unBan");
		HashMap<String, String> response = webHandle.mainRequest(url_items);
		if (!response.containsKey("result") || response.get("result").equals("n")) {
			status = "unsuccessfully";
			isSuccess = "UBanToAdminsFailure";
		}
		if (response.get("result").equals("y")) {
			status = "successfully";
			isSuccess = "UBanToAdminsSucess";
		}
		CB.getServer().getPlayer(playerAdmin).sendMessage(ChatColor.DARK_RED + Language.getFormat(isSuccess));
		CommunityBans.log.info(playerAdmin + " has unbanned " + playerAction + status + "!");
	}
/** The serverBan() method can ban someone from only your own server, no proof required. */
	public static void serverBan() {
		char canBan = 'n';
		String successLevel = "unsuccessfully";
		String message = "SBanToAdminsFailure";
		//Insert code to find if a name is already in the ServerBanFile.
		//Remember to set canBan to 'y'!
		if (canBan == 'n') {
			message = "SBanToAdminsFailure";
			successLevel = "unsuccessfully";
		}
		if (canBan == 'y') {
			CB.getServer().getPlayer(playerAction).kickPlayer(Language.getFormat(
				"SBanToPlayer", playerAction, playerAdmin, reason));
			message = "SBanToAdminsSuccess";
			successLevel = "successfully";
		} 
		if (canBan == 'a') {
			message = "SBanToAdminsAlready";
			successLevel = "already";
		}
		CB.adminMessage(ChatColor.RED + Language.getFormat(message,
						playerAction, playerAdmin, reason), "communitybans.messages.ban");
		CommunityBans.log.info(playerAdmin + " has " + successLevel + " tried to ban " + playerAction
				+ " with a server ban for " + reason + "!");
		canBan = 'n';
		successLevel = "unsuccessfully";
	}
/** The communityBan() method will eventually ban someone on all CB servers. REQUIRES PROOF */
	public static void communityBan() {
		jsonHandler webHandle = new jsonHandler(CB);
		HashMap<String, String> url_items = new HashMap<String, String>();
		url_items.put("player", playerAction);
		url_items.put("reason", reason);
		url_items.put("admin", playerAdmin);
		url_items.put("exec", "globalBan");
		HashMap<String, String> response = webHandle.mainRequest(url_items);
		if (!response.containsKey("result")) {
			CB.getServer().getPlayer(playerAdmin).sendMessage(
					ChatColor.DARK_RED
							+ Language.getFormat(
									"globalBanMessageError", playerAction,
									playerAdmin, reason));
			return;
		}
		if (response.get("result").equals("y")) {
			CommunityBans.log.info(playerAction
					+ " has been banned with a global type ban [" + reason
					+ "] [" + playerAdmin + "]!");
			if (CB.getServer().getPlayer(playerAction) != null) {
				CB.getServer().getPlayer(playerAction)
						.kickPlayer(
								Language.getFormat(
										"globalBanMessageplayer", playerAction,
										playerAdmin, reason));
			}
			CB.getServer().broadcastMessage(ChatColor.DARK_RED
					+ Language.getFormat("globalBanMessageSuccess",
							playerAction, playerAdmin, reason));
			return;
		} else if (response.get("result").equals("e")) {
			CB.getServer().getPlayer(playerAdmin).sendMessage(ChatColor.DARK_RED + Language.getFormat(
									"globalBanMessageError", playerAction,
									playerAdmin, reason));
		} else if (response.get("result").equals("s")) {
			CB.getServer().getPlayer(playerAdmin).sendMessage(ChatColor.DARK_RED + Language.getFormat(
						"globalBanMessageGroup", playerAction, playerAdmin, reason));
		} else if (response.get("result").equals("a")) {
			CB.getServer().getPlayer(playerAdmin).sendMessage(
					ChatColor.DARK_RED
							+ Language.getFormat(
									"globalBanMessageAlready", playerAction,
									playerAdmin, reason));
		}
		CommunityBans.log.info(playerAdmin + " has tried to ban " + playerAction
				+ " with a global type ban [" + reason + "]!");
	}
/** Temporarily bans a player from your server (temp serverBan) */
	public static void tempBan() {
		jsonHandler webHandle = new jsonHandler(CB);
		HashMap<String, String> url_items = new HashMap<String, String>();
		url_items.put("player", playerAction);
		url_items.put("reason", reason);
		url_items.put("admin", playerAdmin);
		url_items.put("duration", duration);
		url_items.put("message", message);
		url_items.put("exec", "tempBan");
		HashMap<String, String> response = webHandle.mainRequest(url_items);
		if (!response.containsKey("result")) {
			CB.getServer().getPlayer(playerAdmin).sendMessage(
					ChatColor.DARK_RED
							+ Language.getFormat("tempBanMessageError",
									playerAction, playerAdmin, reason));
			return;
		}
		if (response.get("result").equals("y")) {
			CommunityBans.log.info(playerAction
					+ " has been banned with a global type ban [" + reason
					+ "] [" + playerAdmin + "]!");
			if (CB.getServer().getPlayer(playerAction) != null) {
				CB.getServer()
						.getPlayer(playerAction)
						.kickPlayer(
								Language.getFormat(
										"tempBanMessageplayer", playerAction,
										playerAdmin, reason));
			}
			CB.getServer().broadcastMessage(ChatColor.DARK_RED
					+ Language.getFormat("tempBanMessageSuccess",
							playerAction, playerAdmin, reason));
			return;
		} else if (response.get("result").equals("e")) {
			CB.getServer().getPlayer(playerAdmin).sendMessage(
					ChatColor.DARK_RED
							+ Language.getFormat("tempBanMessageError",
									playerAction, playerAdmin, reason));
		} else if (response.get("result").equals("s")) {
			CB.getServer().getPlayer(playerAdmin).sendMessage(
					ChatColor.DARK_RED
							+ Language.getFormat("tempBanMessageGroup",
									playerAction, playerAdmin, reason));
		} else if (response.get("result").equals("a")) {
			CB.getServer().getPlayer(playerAdmin).sendMessage(
					ChatColor.DARK_RED
							+ Language.getFormat(
									"tempBanMessageAlready", playerAction,
									playerAdmin, reason));
		}
		CommunityBans.log.info(playerAdmin + " has tried to ban " + playerAction
				+ " with a temp type ban [" + reason + "]!");
	}
	/** kicks a player from the server, but they can rejoin instantly */
	public static void kick() {
		if (CB.getServer().getPlayer(playerAction) != null) {
			CommunityBans.log.info(playerAdmin + " has kicked " + playerAction + "["
					+ reason + "]");
			CB.getServer()
					.getPlayer(playerAction)
					.kickPlayer(
							Language.getFormat("kickMessageplayer",
									playerAction, playerAdmin, reason));
			CB.getServer().broadcastMessage(ChatColor.DARK_RED
					+ Language.getFormat("kickMessageSuccess",
							playerAction, playerAdmin, reason));
		} else {
			CB.getServer().broadcastMessage(ChatColor.DARK_RED + Language.getFormat("kickMessageNoplayer",	playerAction, playerAdmin, reason));
		}
	}
	/** Looks up a player's username to see if they are in the CommunityBans DB */
	public static void lookup() {
		CommunityBans.log.info(playerAdmin + " has looked up the " + playerAction + ".");
		HashMap<String, String> url_items = new HashMap<String, String>();
		jsonHandler webHandle = new jsonHandler(CB);
		url_items.put("player", playerAction);
		url_items.put("admin", playerAdmin);
		url_items.put("exec", "playerLookup");
		JSONObject result = webHandle.hdl_jobj(url_items);
		String totalBans = null;
		try {
			totalBans = result.getString("total");
		} catch (JSONException e1) {
			CommunityBans.log.warning("CommunityBans has encountered an error. Please report error 002 to the CommunityBans staff. Thank you.");
			e1.printStackTrace();
		}
		String banTense = "ban";
		if (Integer.parseInt(totalBans) > 1) {
			banTense = "bans";
		}
		try {
			CB.adminMessage(ChatColor.YELLOW + playerAction
					+ " has " + totalBans + banTense, "communitybans.messages.login");
			for (int v = 0; v < result.getJSONArray("local").length(); v++) {
				CB.getServer().getPlayer(playerAdmin).sendMessage( "[Local] " + ChatColor.AQUA
						+ result.getJSONArray("local").getString(v));
			}
			for (int v = 0; v < result.getJSONArray("global").length(); v++) {
				CB.getServer().getPlayer(playerAdmin).sendMessage( "[Global] "
						+ ChatColor.DARK_RED
						+ result.getJSONArray("global").getString(v));
			}
		} catch (JSONException e) {
		}
	}
	/** Mutes a player until they log out and rejoin the server */
	public static void mute() {
		if (CB.getServer().getPlayer(playerAction) !=null) {
			CommunityBans.log.info(playerAdmin + " has muted " + playerAction + ".");
		}
		
	}
}