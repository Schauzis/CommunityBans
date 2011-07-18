package com.communitybans.main;

import java.io.File;

import org.bukkit.util.config.Configuration;

public class Language {
	private static Configuration config;
	
	static String UBanToAdminsSuccess;
	static String UBanToAdminsFailure;

	static String SBanToPlayer;
	static String SBanToAdminsSuccess;
	static String SBanToAdminsFailure;
	static String SBanToAdminsAlready;

	static String CBanToPlayer;
	static String CBanToAdminsSuccess;
	static String CBanToAdminsFailure;
	static String CBanToAdminsAlready; 

	static String TBanToPlayer;
	static String TBanToAdminsSuccess;
	static String TBanToAdminsFailure;
	static String TBanToAdminsAlready;

	static String KickToPlayer;
	static String KickToAdminsSuccess;
	static String KickToAdminsFailure;
	static String KickToAdminsAlready;
	
	static String MuteToPlayer;
	static String MuteToAdminsSuccess;
	static String MuteToAdminsFailure;
	static String MuteToAdminsAlready;

	static String CommandError;
	static String PermissionError;
	static String PreviousBans;

	public static void languageMethod(String filename) {
		new File("plugins/communitybans").mkdir();
		File languageFile = new File("plugins/communitybans/language.yml");
		Configuration language = new Configuration(languageFile);
		if (!languageFile.exists()) {
			try {
				languageFile.createNewFile();
				language.load();
				language.setProperty("UBan.ToAdminsSuccess", "%PLAYER% has been successfully banned");
				language.setProperty("UBan.ToAdminsFailure", "Error removing the ban for %PLAYER%!");
				language.setProperty("SBan.ToPlayer", "You have been banned, check communitybans.com");
				language.setProperty("SBan.ToAdminsSuccess", "%PLAYER% has been banned [%REASON%]");
				language.setProperty("SBan.ToAdminsFailure", "Error adding ban for %PLAYER%!");
				language.setProperty("SBan.ToAdminsAlready", "%PLAYER% is already banned from this server!");
				language.setProperty("CBan.ToPlayer", "You have been banned, check www.Communitybans.com");
				language.setProperty("CBan.ToAdminsSuccess", "%PLAYER% has been banned [%REASON%]");
				language.setProperty("CBan.ToAdminsFailure", "Error adding ban for %PLAYER%!");
				language.setProperty("CBan.ToAdminsAlready", "%PLAYER% is already banned from this server!");
				language.setProperty("TBan.ToPlayer", "%REASON%");
				language.setProperty("TBan.ToAdminsSuccess", "%PLAYER% has been temporarily banned [%REASON%]");
				language.setProperty("TBan.ToAdminsFailure", "Error adding ban for %PLAYER%!");
				language.setProperty("TBan.ToAdminsAlready", "%PLAYER% is already banned from this server!");
				language.setProperty("Kick.ToPlayer", "%REASON%");
				language.setProperty("Kick.ToAdminsSuccess", "%PLAYER% has been kicked!");
				language.setProperty("Kick.ToAdminsFailure", "%PLAYER% has not been kicked successfully!");
				language.setProperty("Kick.ToAdminsAlready", "%PLAYER% has already left the game!");
				language.setProperty("Mute.ToPlayer", "You were just muted!");
				language.setProperty("Mute.ToAdminsSuccess", "%PLAYER% was successfully muted.");
				language.setProperty("Mute.ToAdminsFailure", "%PLAYER% was not muted!");
				language.setProperty("Mute.ToAdminsAlready", "%PLAYER% was already muted!");
				language.setProperty("CommandError", "Incorrect parameters! Please try again.");
				language.setProperty("PermissionError", "You do not have permission to do that!");
				language.setProperty("PreviousBans", "%PLAYER% has  been communally banned XXX times!");
				language.save();
			} catch (Exception e) {
				CommunityBans.log.warning("CommunityBans could not create the language file");
				e.printStackTrace();
			}
		}
		UBanToAdminsSuccess = (String) language.getProperty("UBan.ToAdminSuccess");
		UBanToAdminsFailure = (String) language.getProperty("UBan.ToAdminFailure");
		SBanToPlayer = (String) language.getProperty("SBan.ToPlayer");
		SBanToAdminsSuccess = (String) language.getProperty("SBan.ToAdminSuccess");
		SBanToAdminsFailure = (String) language.getProperty("SBan.ToAdminFailure");
		SBanToAdminsAlready = (String) language.getProperty("SBan.ToAdminsAlready");
		CBanToPlayer = (String) language.getProperty("CBan.ToPlayer");
		CBanToAdminsSuccess = (String) language.getProperty("CBan.ToAdminsSuccess");
		CBanToAdminsFailure = (String) language.getProperty("CBan.ToAdminsFailure");
		CBanToAdminsAlready = (String) language.getProperty("CBan.ToAdminsAlready");
		TBanToPlayer = (String) language.getProperty("TBan.ToPlayer");
		TBanToAdminsSuccess = (String) language.getProperty("TBan.ToAdminsSuccess");
		TBanToAdminsFailure = (String) language.getProperty("TBan.ToAdminsFailure");
		TBanToAdminsAlready = (String) language.getProperty("TBan.ToAdminsAlready");
		KickToPlayer = (String) language.getProperty("Kick.ToPlayer");
		KickToAdminsSuccess = (String) language.getProperty("Kick.ToAdminsSuccess");
		KickToAdminsFailure = (String) language.getProperty("Kick.ToAdminsFailure");
		KickToAdminsAlready = (String) language.getProperty("Kick.ToAdminsAlready");
		MuteToPlayer = (String) language.getProperty("Mute.ToPlayer");
		MuteToAdminsSuccess = (String) language.getProperty("Mute.ToAdminsSuccess");
		MuteToAdminsFailure = (String) language.getProperty("Mute.ToAdminsFailure");
		MuteToAdminsAlready = (String) language.getProperty("Mute.ToAdminsAlready");
		CommandError = (String) language.getProperty("CommandError");
		PermissionError = (String) language.getProperty("PermissionError");
		PreviousBans = (String) language.getProperty("PreviousBans");
		language.load();
	
	}
	public static String getFormat(String Message) {
		return config.getString(Message, "No language file found!");
	}

	public static String getFormatMessageView(String Message, String Sender,
			String Date, String message) {
		return config.getString(Message, "No language file loaded!")
				.replaceAll("%SENDER%", Sender).replaceAll("%DATE%", Date)
				.replaceAll("%MESSAGE%", message);
	}

	public static String getFormat(String Message, String playerAction) {
		return config.getString(Message, "No language file loaded!")
				.replaceAll("%PLAYER%", playerAction);
	}

	public static String getFormat(String Message, String playerAction,
			String playerAdmin) {
		return config.getString(Message, "No language file loaded!")
				.replaceAll("%PLAYER%", playerAction)
				.replaceAll("%ADMIN%", playerAdmin);
	}

	public static String getFormat(String Message, String PlayerAction,
			String PlayerAdmin, String Reason) {
		return config.getString(Message, "No language file loaded!")
				.replaceAll("%PLAYER%", PlayerAction)
				.replaceAll("%ADMIN%", PlayerAdmin)
				.replaceAll("%REASON%", Reason);
	}
}