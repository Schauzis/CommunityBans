package com.communitybans.main;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.json.JSONException;
import org.json.JSONObject;

import com.communitybans.communicate.jsonHandler;

public class Commands extends Thread {
	private static CommunityBans CB;
/*
	private static String playerAction = null;
	private static String playerAdmin = null;
	private static String reason = null;
//	private String action = null;
	private static String duration = null;
	private static String message = null;
*/
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
	public static void unBan(String playerAdmin, String playerUBanned) {
		String status = null;
		String isSuccess = null;
		jsonHandler webHandle = new jsonHandler(CB);
		HashMap<String, String> url_items = new HashMap<String, String>();
		url_items.put("player", playerUBanned);
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
		CB.getServer().getPlayer(playerAdmin).sendMessage(ChatColor.DARK_RED + OldLanguage.getFormat(isSuccess));
		CommunityBans.log.info(playerAdmin + " has unbanned " + playerUBanned + status + "!");
	}
/** The serverBan() method can ban someone from only your own server, no proof required. */
	public static void serverBan(String playerAdmin, String playerSBanned, String reason) {
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
			CB.getServer().getPlayer(playerSBanned).kickPlayer(OldLanguage.getFormatPAR(
				"SBanToPlayer", playerSBanned, playerAdmin, reason));
			message = "SBanToAdminsSuccess";
			successLevel = "successfully";
		} 
		if (canBan == 'a') {
			message = "SBanToAdminsAlready";
			successLevel = "already";
		}
		CB.adminMessage(ChatColor.RED + OldLanguage.getFormatPAR(message,
						playerSBanned, playerAdmin, reason), "communitybans.messages.action.ban");
		CommunityBans.log.info(playerAdmin + " has " + successLevel + " tried to ban " + playerSBanned
				+ " with a server ban for " + reason + "!");
		canBan = 'n';
		successLevel = "unsuccessfully";
	}
/** The communityBan() method will eventually ban someone on all CB servers. REQUIRES PROOF */
	public static void communityBan(String playerAdmin, String playerCBanned, String reason) {
		jsonHandler webHandle = new jsonHandler(CB);
		HashMap<String, String> url_items = new HashMap<String, String>();
		url_items.put("player", playerCBanned);
		url_items.put("reason", reason);
		url_items.put("admin", playerAdmin);
		url_items.put("exec", "globalBan");
		HashMap<String, String> response = webHandle.mainRequest(url_items);
		if (!response.containsKey("result")) {
			CB.getServer().getPlayer(playerAdmin).sendMessage(
					ChatColor.DARK_RED
							+ OldLanguage.getFormatPAR(
									"globalBanMessageError", playerCBanned,
									playerAdmin, reason));
			return;
		}
		if (response.get("result").equals("y")) {
			CommunityBans.log.info(playerCBanned
					+ " has been banned with a global type ban [" + reason
					+ "] [" + playerAdmin + "]!");
			if (CB.getServer().getPlayer(playerCBanned) != null) {
				CB.getServer().getPlayer(playerCBanned)
						.kickPlayer(
								OldLanguage.getFormatPAR(
										"globalBanMessageplayer", playerCBanned,
										playerAdmin, reason));
			}
			CB.getServer().broadcastMessage(ChatColor.DARK_RED
					+ OldLanguage.getFormatPAR("globalBanMessageSuccess",
							playerCBanned, playerAdmin, reason));
			return;
		} else if (response.get("result").equals("e")) {
			CB.getServer().getPlayer(playerAdmin).sendMessage(ChatColor.DARK_RED + OldLanguage.getFormatPAR(
									"globalBanMessageError", playerCBanned,
									playerAdmin, reason));
		} else if (response.get("result").equals("s")) {
			CB.getServer().getPlayer(playerAdmin).sendMessage(ChatColor.DARK_RED + OldLanguage.getFormatPAR(
						"globalBanMessageGroup", playerCBanned, playerAdmin, reason));
		} else if (response.get("result").equals("a")) {
			CB.getServer().getPlayer(playerAdmin).sendMessage(
					ChatColor.DARK_RED
							+ OldLanguage.getFormatPAR(
									"globalBanMessageAlready", playerCBanned,
									playerAdmin, reason));
		}
		CommunityBans.log.info(playerAdmin + " has tried to ban " + playerCBanned
				+ " with a global type ban [" + reason + "]!");
	}
/** Temporarily bans a player from your server (temp serverBan) */
	public static void tempBan(String playerAdmin, String playerTBanned, String time, String reason) {
		char tBan = 'n';
/*	
 * This entire section has been commented out because tempBans have nothing to do with the community at large
 * and thus there is no purpose in clogging our server so we get to know whenever someone makes a temp ban! /rant	
 * jsonHandler webHandle = new jsonHandler(CB);
		HashMap<String, String> url_items = new HashMap<String, String>();
		url_items.put("player", playerTBanned);
		url_items.put("reason", reason);
		url_items.put("admin", playerAdmin);
		url_items.put("duration", duration);
		url_items.put("message", message);
		url_items.put("exec", "tempBan");
		HashMap<String, String> response = webHandle.mainRequest(url_items);
*/
		if (tBan == 'n') {
			CB.getServer().getPlayer(playerAdmin).sendMessage(
					ChatColor.DARK_RED
							+ OldLanguage.getFormatPAR("tempBanMessageError",
									playerTBanned, playerAdmin, reason));
		}
		if (tBan == 'y') {
			CommunityBans.log.info(CommunityBans.pluginName + playerTBanned
					+ " has been temporarily banned for" + reason + "by" + playerAdmin);
			//Instert message to player here.
			//Insert chunk unloading code here.
			CB.getServer().broadcastMessage(ChatColor.DARK_RED
					+ OldLanguage.getFormatPAR("tempBanMessageSuccess",
							playerTBanned, playerAdmin, reason));
		}
		if (tBan == 'a') {
			CB.getServer().getPlayer(playerAdmin).sendMessage(
					ChatColor.DARK_RED
							+ OldLanguage.getFormatPAR(
									"tempBanMessageAlready", playerTBanned,
									playerAdmin, reason));
		}
		CommunityBans.log.info(playerAdmin + " has tried to ban " + playerTBanned
				+ " with a temp type ban [" + reason + "]!");
		tBan = 'n';
	}
	/** kicks a player from the server, but they can rejoin instantly */
	public static void kick(String playerAdmin, String playerKicked, String reason) {
		if (CB.getServer().getPlayer(playerKicked) != null) {
			CommunityBans.log.info(playerAdmin + " has kicked " + playerKicked + "["
					+ reason + "]");
			CB.getServer()
					.getPlayer(playerKicked)
					.kickPlayer(
							OldLanguage.getFormatPAR("kickMessageplayer",
									playerKicked, playerAdmin, reason));
			CB.getServer().broadcastMessage(ChatColor.DARK_RED
					+ OldLanguage.getFormatPAR("kickMessageSuccess",
							playerKicked, playerAdmin, reason));
		} else {
			CB.getServer().broadcastMessage(ChatColor.DARK_RED + OldLanguage.getFormatPAR("kickMessageNoplayer",	playerKicked, playerAdmin, reason));
		}
	}
	/** Looks up a player's username to see if they are in the CommunityBans DB */
	public static void lookup(String playerAdmin, String playerLooked) {
		CommunityBans.log.info(playerAdmin + " has looked up the " + playerLooked + ".");
		HashMap<String, String> url_items = new HashMap<String, String>();
		jsonHandler webHandle = new jsonHandler(CB);
		url_items.put("player", playerLooked);
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
			CB.adminMessage(ChatColor.YELLOW + playerLooked
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
	public static void mute(String playerAdmin, String playerMuted) {
		if (CB.getServer().getPlayer(playerMuted) !=null) {
			CommunityBans.muted.add(playerMuted);
			CommunityBans.log.info(playerAdmin + " has muted " + playerMuted + ".");
		} else {
			CB.getServer().getPlayer(playerAdmin).sendMessage(ChatColor.DARK_RED
			+ OldLanguage.getFormatPA("MuteToAdminFailure",
					playerMuted, playerAdmin));
		}
		
	}
	public static void unmute(String playerAdmin, String playerMuted) {
		if (CB.getServer().getPlayer(playerMuted) !=null) {
			CommunityBans.muted.remove(playerMuted);
			CB.adminMessage(ChatColor.RED + OldLanguage.getFormatPA("UMutetoAdminsSuccess", playerMuted, playerAdmin), "communitybans.messages.action.kick");
			CommunityBans.log.info(playerAdmin + " has unmuted " + playerMuted + ".");
		} else {
			CB.getServer().getPlayer(playerAdmin).sendMessage(ChatColor.DARK_RED
			+ OldLanguage.getFormatPA("UMuteToAdminAlready",
					playerMuted, playerAdmin));
		}
		
	}
}