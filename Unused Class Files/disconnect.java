/*
 * This class file has been commented out simply because it is not
 * needed. There is no need to track player disconnects and there
 * is no reason to tell the CB server about it. Because of this, I am
 * commenting out this file and will be deleting it in my next upload to
 * GitHub
 */

/*package com.communitybans.actions;

import java.util.HashMap;

import com.communitybans.main.CommunityBans;
import com.communitybans.communicate.jsonHandler;

public class disconnect extends Thread {
	private CommunityBans MCBans;
	private String PlayerName;

	public disconnect(CommunityBans p, String Player) {
		MCBans = p;
		PlayerName = Player;
	}

	public void run() {
		CommunityBans.sLog.info(PlayerName + " has disconnected!");
		jsonHandler webhandle = new jsonHandler(MCBans);
		HashMap<String, String> url_items = new HashMap<String, String>();
		url_items.put("player", PlayerName);
		url_items.put("exec", "playerDisconnect");
		webhandle.mainRequest(url_items);
	}
}
*/