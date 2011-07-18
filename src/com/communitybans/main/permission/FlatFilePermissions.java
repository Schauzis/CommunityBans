package com.communitybans.main.permission;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.config.Configuration;

/**
 *
 * @author Hretsam
 * 
 * This is a FlatFile permission handler.
 * The handler works, only not tested for speed & memory usage
 */
public class FlatFilePermissions implements PermissionResolver {

    // File itself
    private Configuration permissionFile;
    // Files with the mappings
    private HashMap<String, String> players;
    private HashMap<String, HashSet<String>> groups;

    public FlatFilePermissions() {
    }

    @Override
    public boolean load(Plugin plugin) {
        // Location of the file
        File playerFile = new File(plugin.getDataFolder() + File.separator + "players.yml");

        if (!playerFile.exists()) {
            // Playerfile not found, do not load this.
            return false;
        }

        // Load config
        permissionFile = new Configuration(playerFile);
        permissionFile.load();

        // Init the maps
        groups = new HashMap<String, HashSet<String>>();
        players = new HashMap<String, String>();


        // Load groups
        List<String> groupnodes = permissionFile.getKeys("groups"), permissionlist;
        // Nullcheck
        if (groupnodes != null) {
            // Loop trough groups
            for (String group : groupnodes) {
                // Get permission list
                permissionlist = permissionFile.getStringList("groups." + group, groupnodes);
                // Null check
                if (permissionlist != null) {
                    // Make perm set
                    HashSet<String> permSet = new HashSet<String>();
                    // Look trough all permissions and add them to the set
                    for (String perm : permissionlist) {
                        permSet.add(perm);
                    }
                    // Put group in map
                    groups.put(group, permSet);
                }
            }
        }

        // Load users
        List<String> usernodes = permissionFile.getKeys("users");
        // Nullcheck
        if (usernodes != null) {
            // Loop trough users
            for (String usernode : usernodes) {
                // Put username + group in mapping, default group is ""
                players.put(usernode.toLowerCase(), permissionFile.getString("users." + usernode, ""));
            }
        }
        
        // Drop reference to file, as its not used after this.x
        permissionFile = null;
        
        
        return true;
    }

    @Override
    public boolean hasPermission(Player player, String permission) {
        // Check if player is in the list
        if (players.containsKey(player.getName().toLowerCase())) {
            // Get groupname
            String groupname = players.get(player.getName().toLowerCase());
            // Check group is mapped
            if (groups.containsKey(groupname)) {
                // Check if group got permission
                return groups.get(groupname).contains(permission);
            }
        }
        return false;
    }
}
