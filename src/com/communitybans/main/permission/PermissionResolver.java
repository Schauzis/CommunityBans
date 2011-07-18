package com.communitybans.main.permission;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Hretsam
 */
public interface PermissionResolver {
    public boolean load(Plugin plugin);
    public boolean hasPermission(Player player, String permission);
}