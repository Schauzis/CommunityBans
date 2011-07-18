/*
 * These sections have been commented out because CB will not be creating a "private" log file.
 * There is no reason to make one because the standard MC log file will be fine. I cannot come up
 * with a reason that a private log file would benefit this plugin in any way, so I will not be using one.
 * Because there will not be a log file, this entire class is redundant and  unnecesarilly bulky.
 */
/*
 package com.communitybans.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionLog {

    private File logFile;
    private final static DateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss]");

    public ActionLog(File dataFolder) {
        logFile = new File(dataFolder + File.separator + "CBans.log");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException ex) {
                CommunityBans.sLog.severe("Cannot create log file!");
            }
        }
    }

    public void write(String msg) {
        if (Settings.getBoolean("logEnable")) {
            write(logFile, msg);
        } else {
            CommunityBans.log(msg, false);
        }
    }

    public void write(Exception e) {
        if (Settings.getBoolean("logEnable")) {
            write(logFile, e.getMessage());
        } else {
            CommunityBans.log("Exception: " + e.getMessage(), false);
        }
    }

    public static void write(File file, String msg) {
        try {
            String currentTime = ActionLog.dateFormat.format(new Date());
            FileWriter aWriter = new FileWriter(file, true);
            aWriter.write(currentTime + " " + msg + System.getProperty("line.separator"));
            aWriter.flush();
            aWriter.close();
        } catch (Exception e) {
            CommunityBans.log("Error writing log! " + e.getMessage(), true);
        }
    }

    @SuppressWarnings("unused")
	@Deprecated
    private static String stack2string(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            // PrintWriter pw = new PrintWriter(sw);
            // e.printStackTrace(pw);
            return "------\r\n" + sw.toString() + "------\r\n";
        } catch (Exception e2) {
            return "bad stack2string";
        }
    }
}
*/