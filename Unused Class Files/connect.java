/*
 * This entire class file has been commented out because it was written extremely poorly.
 * It creates a hashmap with 6 repsonses when only 2 of them do anything (an easy if/else).
 * Instead, I (supersam654) am moving it to the playerlistener under the player pre-logon event.
 */


/*package com.communitybans.actions;

import java.util.HashMap;

import com.communitybans.communicate.jsonHandler;
import com.communitybans.main.CommunityBans;

public class connect {
	private CommunityBans CB;
	private HashMap<String, Integer> responses = new HashMap<String, Integer>();

	public connect(CommunityBans p) {
		responses.put("n", 0);
		responses.put("g", 1);
		responses.put("s", 2);
		responses.put("i", 3);
//		responses.put("b", 4);
		responses.put("t", 5);
		responses.put("l", 6);
		CB = p;
	}

	public String exec(String PlayerName) {
		String s = null;
		jsonHandler webHandle = new jsonHandler(CB);
		HashMap<String, String> url_items = new HashMap<String, String>();
		url_items.put("player", PlayerName);
		url_items.put("exec", "playerConnect");
		HashMap<String, String> response = webHandle.mainRequest(url_items);
		if (!response.containsKey("banStatus")) {
			return null;
		}
			switch (responses.get(response.get("banStatus"))) {
			case 0:
				CommunityBans.sLog.info(PlayerName + " has connected!");
				s = null;
				break;
			case 1:
			case 2:
			case 3:
			case 5:
			case 6:
				s = response.get("banReason");
				CommunityBans.sLog.info(PlayerName + " access denied!");
				break;
			}
		return s;
	}
}
*/