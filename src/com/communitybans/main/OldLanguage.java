package com.communitybans.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.util.config.Configuration;

public class OldLanguage {
	/*
	 * Pay careful attention to if something is ToAdmin or ToAdminS! If something says
	 * ToAdmins, then the message will be directed at everyone with a specific permission. However,
	 * if something says toAdmin, then that message will only be directed at the person who isseud the
	 * command (When someone is muted, all admins know about it, but when someone issues a mute command
	 * unsuccessfully, only that person will find out. Regardless, the console will always log it.
	 */
	private static Configuration config;
	
	static String UBanToAdminsSuccess;
	static String UBanToAdminFailure;
	static String UBanToAdminAlready;

	static String SBanToPlayer;
	static String SBanToAdminsSuccess;
	static String SBanToAdminFailure;
	static String SBanToAdminAlready;

	static String CBanToPlayer;
	static String CBanToAdminsSuccess;
	static String CBanToAdminFailure;

	static String TBanToPlayer;
	static String TBanToAdminsSuccess;
	static String TBanToAdminFailure;
	static String TBanToAdminAlready;

	static String KickToPlayer;
	static String KickToAdminsSuccess;
	static String KickToAdminFailure;
	static String KickToAdminAlready;
	
	static String MuteToPlayer;
	static String MuteToAdminsSuccess;
	static String MuteToAdminFailure;
	static String MuteToAdminAlready;
	
	static String UMuteToPlayer;
	static String UMuteToAdminsSuccess;
	static String UMuteToAdminFailure;
	static String UMuteToAdminAlready;
	
	static String CommandError;
	static String PermissionError;
	static String PreviousBans;
    public static boolean loadLanguage(String dataFolder) {
        File languageFile = new File(dataFolder + "/english.yml");
        if (!languageFile.exists()) {
            try {
                // Create a new file, and copy the contents of config.yml in the default package!
                languageFile.createNewFile();
                InputStream stream = CommunityBans.class.getResourceAsStream("/language.yml");
                OutputStream out = new FileOutputStream(languageFile);

                byte[] buf = new byte[1024];
                int len;
                while ((len = stream.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                stream.close();
                out.close();
                CommunityBans.log.info(CommunityBans.pluginName + "Config file not found, created new file");
            } catch (IOException iex) {
                CommunityBans.log.severe(CommunityBans.pluginName + "Cannot create config file! " + iex.getMessage());
                return false;
            }
        }
        Configuration language = new Configuration(languageFile);
        language.load();
/*
	public static void languageMethod(String filename) {
		new File("plugins/communitybans").mkdir();
		File languageFile = new File("plugins/communitybans/language.yml");
		Configuration language = new Configuration(languageFile);
		if (!languageFile.exists()) {
			try {
				languageFile.createNewFile();
				language.load();
				language.setProperty("UBan.ToAdminsSuccess", "%PLAYER% has been successfully banned by %ADMIN%");
				language.setProperty("UBan.ToAdminFailure", "An unknown problem has prevented you from unbanning %PLAYER%");
				language.setProperty("UBan.ToAdminAready", "%PLAYER% is not currently banned!");
				language.setProperty("SBan.ToPlayer", "You have been banned for %REASON%!");
				language.setProperty("SBan.ToAdminsSuccess", "%PLAYER% has been banned by %ADMIN% for %REASON%.");
				language.setProperty("SBan.ToAdminFailure", "An unknown error has prevented you from server banning %PLAYER%!");
				language.setProperty("SBan.ToAdminAlready", "%PLAYER% is already banned from this server!");
				language.setProperty("CBan.ToPlayer", "You have been banned, check www.Communitybans.com");
				language.setProperty("CBan.ToAdminsSuccess", "%PLAYER% has been banned [%REASON%]");
				language.setProperty("CBan.ToAdminsFailure", "Error adding ban for %PLAYER%!");
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
				language.setProperty("UMute.ToPlayer", "You have just been muted by %ADMIN%");
				language.setProperty("UMute.ToAdminsSucess", "%PLAYER% has just been unmuted by %ADMIN%");
				language.setProperty("UMute.ToAdminFailure", "For some reason, %PLAYER% could not be unmuted.");
				language.setProperty("CommandError", "Incorrect parameters! Please try again.");
				language.setProperty("PermissionError", "You do not have permission to do that!");
				language.setProperty("PreviousBans", "%PLAYER% has  been communally banned XXX times!");
				language.save();
			} catch (Exception e) {
				CommunityBans.log.warning(CommunityBans.pName + "could not create the language file");
				e.printStackTrace();
			}
		}
*/
		UBanToAdminsSuccess = (String) language.getProperty("UBan.ToAdminSuccess");
		UBanToAdminFailure = (String) language.getProperty("UBan.ToAdminFailure");
		UBanToAdminAlready = (String) language.getProperty("UBan.ToAdminAlready");
		SBanToPlayer = (String) language.getProperty("SBan.ToPlayer");
		SBanToAdminsSuccess = (String) language.getProperty("SBan.ToAdminSuccess");
		SBanToAdminFailure = (String) language.getProperty("SBan.ToAdminFailure");
		CBanToPlayer = (String) language.getProperty("CBan.ToPlayer");
		CBanToAdminsSuccess = (String) language.getProperty("CBan.ToAdminsSuccess");
		CBanToAdminFailure = (String) language.getProperty("CBan.ToAdminFailure");
		TBanToPlayer = (String) language.getProperty("TBan.ToPlayer");
		TBanToAdminsSuccess = (String) language.getProperty("TBan.ToAdminsSuccess");
		TBanToAdminFailure = (String) language.getProperty("TBan.ToAdminFailure");
		TBanToAdminAlready = (String) language.getProperty("TBan.ToAdminAlready");
		KickToPlayer = (String) language.getProperty("Kick.ToPlayer");
		KickToAdminsSuccess = (String) language.getProperty("Kick.ToAdminsSuccess");
		KickToAdminFailure = (String) language.getProperty("Kick.ToAdminFailure");
		KickToAdminAlready = (String) language.getProperty("Kick.ToAdminAlready");
		MuteToPlayer = (String) language.getProperty("Mute.ToPlayer");
		MuteToAdminsSuccess = (String) language.getProperty("Mute.ToAdminsSuccess");
		MuteToAdminFailure = (String) language.getProperty("Mute.ToAdminFailure");
		MuteToAdminAlready = (String) language.getProperty("Mute.ToAdminAlready");
		UMuteToPlayer = (String) language.getProperty("UMute.ToPlayer");
		UMuteToAdminsSuccess = (String) language.getProperty("UMute.ToAdminsSuccess");
		UMuteToAdminFailure = (String) language.getProperty("UMute.ToAdminFailure");
		UMuteToAdminAlready = (String) language.getProperty("UMute.ToAdminAlready");
		CommandError = (String) language.getProperty("CommandError");
		PermissionError = (String) language.getProperty("PermissionError");
		PreviousBans = (String) language.getProperty("PreviousBans");
		return true;
	
	}
	public static String getFormat(String Message) {
		return config.getString(Message, "No language file found!");
	}
/*
	public static String getFormatMessageView(String Message, String Sender,
			String Date, String message) {
		return config.getString(Message, "No language file loaded!")
				.replaceAll("%SENDER%", Sender)
				.replaceAll("%DATE%", Date)
				.replaceAll("%MESSAGE%", message);
	}
*/
	public static String getFormatP(String Message, String playerAction) {
		return config.getString(Message, "No language file loaded!")
				.replaceAll("%PLAYER%", playerAction);
	}

	public static String getFormatPA(String Message, String playerAction,
			String playerAdmin) {
		return config.getString(Message, "No language file loaded!")
				.replaceAll("%PLAYER%", playerAction)
				.replaceAll("%ADMIN%", playerAdmin);
	}

	public static String getFormatPAR(String Message, String PlayerAction,
			String PlayerAdmin, String Reason) {
		return config.getString(Message, "No language file loaded!")
				.replaceAll("%PLAYER%", PlayerAction)
				.replaceAll("%ADMIN%", PlayerAdmin)
				.replaceAll("%REASON%", Reason);
	}
}