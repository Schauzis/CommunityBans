
package com.communitybans.permission;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Hretsam
 * 
 * Untested permission handler for Bukkit's "SuperPerms"
 */
public class BukkitPermissions implements PermissionResolver {

    @Override
    public boolean load(Plugin plugin) {
        // Check if there are any permission loaded
        return (plugin.getDescription().getPermissions() == null || plugin.getDescription().getPermissions().isEmpty());
    }

    @Override
    public boolean hasPermission(Player player, String permission) {
        // Check permission
        return player.hasPermission(permission);
    }
    
}
