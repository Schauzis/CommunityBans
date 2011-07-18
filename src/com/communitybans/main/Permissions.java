package com.communitybans.main;
/*
 * This entire section of code has been commented out in favor of a
 * far simpler permissions system that is recommended by the dev
 * himself. Because of this, I plan on phasing this entire class
 * file out in the final package. When I get a working permissions
 * system in place, I will completely delete this file. 
 * package com.mcbans.firestar.mcbans;


import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

@SuppressWarnings("unused")
public class bukkitPermissions {
	private static PermissionHandler permissionHandler = null;
	private bukkitInterface MCBans;

	private Settings Config;

	public bukkitPermissions(Settings cf, bukkitInterface p) {
		MCBans = p;
		Config = cf;
	}

	public void setupPermissions() {
		Plugin permissionsPlugin = MCBans.pluginInterface("Permissions");
		if (permissionHandler == null) {
			if (permissionsPlugin != null) {
				permissionHandler = ((Permissions) permissionsPlugin)
						.getHandler();
				System.out.print("MCBans: Permissions found!");
			} else {
				System.out.print("MCBans: No permission found!");
				MCBans.getPluginLoader().disablePlugin(MCBans);
			}
		}
	}
/*
 * This section of code has been commented out because it is far overly complicated
 * and will be replaced in favor of a much easier permissions execution solution.
 * When the new system has been completely implemented and is no longer remotely close
 * to the old MCBans' way, this section of code, along with this message will be deleted.
	public boolean isAllow(String WorldName, String PlayerName,
			String PermissionNode) {
		// if( permissionHandler.has( WorldName, PlayerName,
		// "mcbans."+PermissionNode ) ){
		// return true;
		// }
		Player target = MCBans.getServer().getPlayer(PlayerName);
		if (target != null) {
			return isAllow(target, PermissionNode);
		}
		return false;
	}

	public boolean isAllow(Player Player, String PermissionNode) {
		if (permissionHandler.has(Player, "mcbans." + PermissionNode)) {
			return true;
		}
		return false;
	}

	public boolean inGroup(String WorldName, String PlayerName, String GroupName) {
		if (permissionHandler.inGroup(WorldName, PlayerName, GroupName)) {
			return true;
		}
		return false;
	}
}
*/