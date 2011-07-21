package com.communitybans.report;

import java.util.Date;
import java.util.HashMap;
import org.bukkit.Location;

/**
 *
 * @author Hretsam
 * 
 * Interface for the reporting of block related reports
 * 
 */
public interface BlockReport {
    
    public HashMap<BlockType, Location> getValuableBlockBreaks(String playername, Date from, Date to);
    
}
