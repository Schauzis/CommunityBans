package com.communitybans.main;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.communitybans.communicate.jsonHandler;

/* Although exact purpose still needs to be deciphered,
 * The jist of this class deals with API keys, alerting server admins of
 * an updated plugin version, and something with JSON
 * which will be more explained as the web dev side delves into that
 * part of the code.
 */
/*
 * As a side note, this file currently does not fully function. Because it deals with
 * server-side communication, I do not want to fix it, just to find out that what I am fixing will
 * never work the way I want it to. Instead, I am just leaving this file in a non-working state
 * until some client-server communication is beginning to take shape.
 */

public class mainCallBack extends Thread {
	private CommunityBans CB = null;
	public mainCallBack(CommunityBans p){
		CB = p;
	}
	public void run(){
		int callBacInterval = Settings.getInteger("callBackInterval");
		while(true){
			this.mainRequest();
			try {
				Thread.sleep(callBacInterval);
			} catch (InterruptedException e) {
			}
		}
	}
	private void mainRequest(){
		jsonHandler webHandle = new jsonHandler( CB );
		HashMap<String, String> url_items = new HashMap<String, String>();
//The below line is commented out because there is no reason to know the maximum amount of players on a server.
		//url_items.put( "maxPlayers", String.valueOf( CB.getServer().getMaxPlayers() ) );
		url_items.put( "playerList", this.playerList() );
		url_items.put( "version", CB.getDescription().getVersion() );
		url_items.put( "exec", "callBack" );
		HashMap<String, String> response = webHandle.mainRequest(url_items);
		if(response.containsKey("oldVersion")){
			if(!response.get("oldVersion").equals("")){
				CB.adminMessage( ChatColor.DARK_GREEN + "CommunityBans version " + response.get("oldVersion") + " now available!" );
			}
		}
// This purpose of the if statement below is not exactly understood and I will be deleting it
// in the next build unless someone can find a valid use for it (even in our "new" system).
		/*
		if(response.containsKey("newMessages")){
			if(!response.get("newMessages").equals("")){
				String[] Players = response.get("newMessages").split(",");
				for( String player: Players ){
					String[] params = player.split(":");
					CB.broadcastPlayer(params[0], ChatColor.GOLD + Language.getFormat( "newMessage") );
				}
			}
		}
		*/
	}
	private String playerList(){
		String playerList= null;
		for(Player player: CB.getServer().getOnlinePlayers()){
			if(playerList.equals(null)){
				playerList = player.getName();
			}else{
				playerList += "," + player.getName();
			}
		}
		return playerList;
	}
}