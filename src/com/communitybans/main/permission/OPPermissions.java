package com.communitybans.main.permission;

import com.communitybans.main.CommunityBans;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Hretsam
 * 
 * Op permission handler. This is not an advised setting.x
 */
public class OPPermissions implements PermissionResolver {

    public OPPermissions() {
        CommunityBans.log.warning("Current permissions setting insecure, OPs have access to all commands!");
    }

    @Override
    public boolean load(Plugin plugin) {
        return true;
    }

    @Override
    public boolean hasPermission(Player player, String permission) {
        return player.isOp();
    }
}
