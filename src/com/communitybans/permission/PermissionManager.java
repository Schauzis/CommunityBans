package com.communitybans.permission;

import com.communitybans.main.CommunityBans;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * 
 * @author Hretsam
 * 
 * 
 * Permission manager that is used to check if the user got permission for an action.
 * 
 */
public class PermissionManager implements PermissionResolver {

    private PermissionResolver permissionResolver;

    /**
     * Load a permission setting
     * 
     * Order of checks:
     * 1. Permission plugin
     * 2. Bukkit permission
     * 3. CBans flatfile system
     * 4. OP
     * @param plugin 
     */
    public PermissionManager(Plugin plugin) {
        if (tryNijiPermissions(plugin)) {
            CommunityBans.log.info(CommunityBans.pluginName + "Permissions plugin found, using Permissions!");
            return;
        }
        if (tryBukkitPermissions(plugin)){
            CommunityBans.log.info(CommunityBans.pluginName + "Bukkit permissions found, using bukkit \"superperms\"!");
            return;
        }
        if (tryFlatFilePermissions(plugin)) {
            CommunityBans.log.info(CommunityBans.pluginName + "Flat file setting found, using FlatFile!");
            return;
        }
        
        CommunityBans.log.warning(CommunityBans.pluginName + "No permission settings found, reverting back to defaulting OP!");
        permissionResolver = new OPPermissions();
    }

    @Override
    public boolean load(Plugin plugin) {
        return false;
    }

    private boolean tryNijiPermissions(Plugin plugin) {
        permissionResolver = new NijiPermissions();
        return permissionResolver.load(plugin);
    }
    
    private boolean tryBukkitPermissions(Plugin plugin) {
        permissionResolver = new BukkitPermissions();
        return permissionResolver.load(plugin);
    }

    private boolean tryFlatFilePermissions(Plugin plugin) {
        permissionResolver = new FlatFilePermissions();
        return permissionResolver.load(plugin);
    }

    /**
     * Checks the permission system if the player got permission for this action
     * @param player
     * @param permission
     * @return 
     */
    @Override
    public boolean hasPermission(Player player, String permission) {
        return permissionResolver.hasPermission(player, permission);
    }

    /**
     * Basicly the same as the other hasPermission, only if the CommandSender is not a player it will return true.
     * @param sender
     * @param permission
     * @return 
     */
    public boolean hasPermission(CommandSender sender, String permission) {
        if (sender instanceof Player) {
            return hasPermission((Player) sender, permission);
        }
        return true;
    }
}