package sadmean.mc.nuclearDevice;

import java.util.logging.Logger;

//import org.bukkit.event.Event;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class nuclearDevice extends JavaPlugin {
	
	//setup and stuff
    private static nuclearDevice thisPlugin = null; //I don't know what this does. Necessary for fancy log
    private final nuclearBlockListener blockListener = new nuclearBlockListener(this); //the player listener.
    private final nuclearEntityListener entityListener = new nuclearEntityListener(this); //the entity listener.
	
	public static Logger log = Logger.getLogger("Minecraft"); //logger object. can be written to directly with "log.info("herp derp")
    
    public static nuclearDevice getThisPlugin() { //I do not know. Needed for fancy log
        return thisPlugin; 
    }

    private static void setThisPlugin(final nuclearDevice thisPlugin) //also need for fancy log and other things
    {
    	nuclearDevice.thisPlugin = thisPlugin;
    }
	

    public void onLoad() //onLoad is called the instant this plugin is touched.
    {
        setThisPlugin(this); //not 100% sure
    }
	
    public void onEnable(){  //onEnable is called after onLoad
		PluginManager pm = this.getServer().getPluginManager(); //register this plugin
		pm.registerEvent(Event.Type.REDSTONE_CHANGE, blockListener, Event.Priority.Normal, this); //register our playerListener
		pm.registerEvent(Event.Type.ITEM_SPAWN, entityListener, Event.Priority.Normal, this); //register our playerListener
		//pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this); //register our serverListener (not needed)?
		log_It("info", "Enabled started");
    }
    
	public void onDisable(){ 
		log_It("info", "Disabled Completed"); //log us not doing anything. 
	}
	
	//logging functions
	
	public static void log_It(String message) {
		//converts 1 string log_it to a 2 string log it. Fixes leftovers.
		String level = "undefined";
		log_It("warning", "this message's priority was not properly set");
		log_It(level, message);
	}
	
	/**
	 * @param level
	 * @param message
	 */
	public static void log_It(String level, String message) {
		PluginDescriptionFile thisYAML = getThisPlugin().getDescription();
		String pluginName = thisYAML.getName();
		String pluginVersion = thisYAML.getVersion();
		String fullName = "[" + pluginName + "][" + pluginVersion + "] ";
		//convert our level into an int for logging
		int level_int = 0;
		
		if(level == "finest") level_int = 0;
		if(level == "finer") level_int = 1;
		if(level == "fine") level_int = 2;
		if(level == "info") level_int = 3;
		if(level == "warning") level_int = 4;
		if(level == "severe") level_int = 5;
		if(level == "undefined") level_int = 6;
		
	
		switch (level_int) {
		case 0: log.finest(fullName + message); break; //for people who like logs in the hexabytes
		case 1: log.finer(fullName + message); break; //for people who like log file sizes in the petabytes
		case 2: log.fine(fullName + message); break; //for people who like log file sizes in the terabytes
		case 3: log.info(fullName + message); break; //for people who like log file sizes in the gigabytes
		case 4: log.warning(fullName + message); break; //for people who like log file sizes in the megabytes
		case 5: log.severe(fullName + message); break; //for people who like log file sizes in the kilobytes
		case 6: log.warning(fullName + message); break; //for me, 'cause I forgot to specify what level of logging
		default: log.warning(fullName + "warning defaulted, maybe a typo: " + message); //also for me, because I spelled the logging level wrong
			break;
		}
	}

}
