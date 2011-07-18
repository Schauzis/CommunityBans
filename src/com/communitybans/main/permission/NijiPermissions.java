package com.communitybans.main.permission;

import com.nijiko.permissions.PermissionHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Hretsam
 */
public class NijiPermissions implements PermissionResolver {

    private PermissionHandler permissions;

    public NijiPermissions() {
    }

    @Override
    public boolean load(Plugin plugin) {
        
        // Get plugin
        Plugin nijiPermissions = plugin.getServer().getPluginManager().getPlugin("Permissions");
        // Nullcheck
        if (nijiPermissions != null) {
            // Set plugin reference
            permissions = ((com.nijikokun.bukkit.Permissions.Permissions) nijiPermissions).getHandler();
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Player player, String permission) {
        // Check for permission
        return permissions.permission(player, permission);
    }
}
