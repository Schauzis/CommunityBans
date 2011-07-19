package com.communitybans.main.permission;

import com.communitybans.main.CommunityBans;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PermissionManager implements PermissionResolver {

    private PermissionResolver permissionResolver;

    public PermissionManager(Plugin plugin) {
        if (tryNijiPermissions(plugin)) {
            CommunityBans.log.info("Permissions plugin found, using Permissions!");
            return;
        }
        if (tryFlatFilePermissions(plugin)) {
            CommunityBans.log.info("Flat file setting found, using FlatFile!");
            return;
        }
        
        CommunityBans.log.warning("No permission settings found, reverting back to defaulting OP!");
        permissionResolver = new OPPermissions();
        
        /*
         * TODO
         * Add support for the built in bukkit permission system.
         */
    }

    @Override
    public boolean load(Plugin plugin) {
        return false;
    }

    private boolean tryNijiPermissions(Plugin plugin) {
        permissionResolver = new NijiPermissions();
        return permissionResolver.load(plugin);
    }

    private boolean tryFlatFilePermissions(Plugin plugin) {
        permissionResolver = new FlatFilePermissions();
        return permissionResolver.load(plugin);
    }

    @Override
    public boolean hasPermission(Player player, String permission) {
        return permissionResolver.hasPermission(player, permission);
    }

    public boolean hasPermission(CommandSender sender, String permission) {
        if (sender instanceof Player) {
            return hasPermission((Player) sender, permission);
        }
        return true;
    }
}