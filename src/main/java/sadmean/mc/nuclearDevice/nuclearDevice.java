package sadmean.mc.nuclearDevice;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class nuclearDevice extends JavaPlugin {
	
	//needed files
	static String mainDirectory = "plugins/nuclearDevice";
	static public File configFile = new File(mainDirectory + File.separator + "config.yml");
	
	//permissions
	public static PermissionHandler permissionHandler;
	
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
	
    public static int capTypeID = 41;
    public static int payloadTypeID = 57;
    public static boolean useSimulatedExplosion = false;
    public static boolean destroyBedrock = false;
    public static boolean igniteOuterLayer = false;
    public static boolean kickPlayers = false;
    //default cap/payload. overwritten by yaml
	
    
    public void onEnable(){  //onEnable is called after onLoad
		PluginManager pm = this.getServer().getPluginManager(); //register this plugin
		pm.registerEvent(Event.Type.REDSTONE_CHANGE, blockListener, Event.Priority.Normal, this); //register our playerListener
		pm.registerEvent(Event.Type.ITEM_SPAWN, entityListener, Event.Priority.Normal, this); //register our playerListener
		//pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this); //register our serverListener (not needed)?
		log_It("info", "Enabled started");
		setupPermissions(); //enabled permissions
		new File(mainDirectory).mkdir();  //makes our directory if needed
		if(!configFile.exists()){ //if your config does not exist then ...
	         try { 
	        	 configFile.createNewFile(); //... we create it then ...
	        	 Configuration configYAML = getThisPlugin().getConfiguration(); //... load the blank new file ...
	        	 configYAML.setProperty("nuclearValues.capTypeID", capTypeID); //..set values.
	        	 configYAML.setProperty("nuclearValues.payloadTypeID", payloadTypeID); //... then set some values`
	        	 configYAML.setProperty("nuclearValues.useSimulatedExplosion", useSimulatedExplosion); //... then set some values`
	        	 configYAML.setProperty("nuclearValues.DestroyBedrock", destroyBedrock); //... then set some values`
	        	 configYAML.setProperty("nuclearValues.igniteOuterLayer", igniteOuterLayer); //... then set some values
	        	 configYAML.setProperty("nuclearValues.kickPlayers", kickPlayers); //... then set some values
	        	 
	     		if(!configYAML.save()) { //attempt to save, if fails then
	     			log_It("severe", "Attempted to save config.yml, got saving error!"); //IT FAILED!
	     		}
	         } catch (IOException ex) { 
	             ex.printStackTrace(); //not needed anymore probably
	         }
	 
		} else { 
			//it does exist?
		}
		//start setting values
		Configuration configYAML = getThisPlugin().getConfiguration();
		configYAML.load();
		capTypeID = configYAML.getInt("nuclearValues.capTypeID", 0);
		payloadTypeID = configYAML.getInt("nuclearValues.payloadTypeID", 0);
		useSimulatedExplosion = configYAML.getBoolean("nuclearValues.useSimulatedExplosion", false);
		igniteOuterLayer = configYAML.getBoolean("nuclearValues.destroyBedrock", false);
		destroyBedrock = configYAML.getBoolean("nuclearValues.igniteOuterLayer", false);
		kickPlayers = configYAML.getBoolean("nuclearValues.kickPlayers", false);
		
		if(capTypeID == 0 && payloadTypeID == 0) {
			log_It("severe", "both cap and payload returned 0");
			log_It("severe", "Setting values to stupid numbers to preserve stability");
			capTypeID = 9001; //if we leave payload and cap at 0, any air touched by redstone becomes a valid bomb design. 
			payloadTypeID = 1337; //the world would not survive
		}
		
    }
    
	public void onDisable(){ 
		log_It("info", "Disabled Completed"); //log us not doing anything. 
	}
	//command handler
	 public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		 //set a variable or 2
		 Server server = sender.getServer();
		 Player playerSender = server.getPlayer(sender.toString());
		 if(cmd.getName().equalsIgnoreCase("nuclearDevice")){ // If the player typed /nuclearDevice then do the following...
			  //if (getThisPlugin().permissionHandler.has(playerSender, "nuclearDevice.configure")) {  
			      nukeCommander commander = new nukeCommander(sender, cmd, args); //creates a nukeCommander to deal with our commands
			      if(!commander.commandHelper()) return false; //tell the commander helper to do its thing
			      return true;	
			 // }
			 // else {
			//	  return false; 
			  }
		// } //If this has happened the function will break and return true. if this hasn't happened the a value of false will be returned.
		 return false; 
	 }

	 //helpers
	 public static boolean setCap(int setCapID) {
		 capTypeID = setCapID;
		 if (updateYAML("nuclearValues.capTypeID", setCapID)) {
			 return true;
		 } else {
			 return false;
		 }
		 
		 
	 }
	 
	 public static boolean setPayload(int setPayloadID) {
		 payloadTypeID = setPayloadID;
		 if (updateYAML("nuclearValues.payloadTypeID", setPayloadID)) {
			 return true;
		 } else {
			 return false;
		 }
		 
		 
	 }
	 
	 public static boolean setUSE(boolean USE) {
		 if (updateYAML("nuclearValues.useSimulatedExplosion", true)) {
			 return true;
		 } else {
			 return false;
		 }
	 }
	 
	 public static boolean setDestroyBedrock(boolean USE) {
		 if (updateYAML("nuclearValues.destroyBedrock", true)) {
			 return true;
		 } else {
			 return false;
		 }
	 }
	 
	 public static boolean setKickPlayers(boolean USE) {
		 if (updateYAML("nuclearValues.kickPlayers", true)) {
			 return true;
		 } else {
			 return false;
		 }
	 }
	 
	 public static boolean setIgniteOuterLayer(boolean USE) {
		 if (updateYAML("nuclearValues.igniteOuterLayer", true)) {
			 return true;
		 } else {
			 return false;
		 }
	 }
	 
	 static boolean updateYAML(String path, int value) {
		 Configuration configYAML = getThisPlugin().getConfiguration();
		 configYAML.setProperty(path, value);
		 if (configYAML.save()) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	 
	  private void setupPermissions() {
	      Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

	      if (getThisPlugin().permissionHandler == null) {
	          if (permissionsPlugin != null) {
	              getThisPlugin().permissionHandler = ((Permissions) permissionsPlugin).getHandler();
	          } else {
	              log.info("Permission system not detected, defaulting to OP");
	          }
	      }
	  }

	 
	 static boolean updateYAML(String path, boolean value) {
		 Configuration configYAML = getThisPlugin().getConfiguration();
		 configYAML.setProperty(path, value);
		 if (configYAML.save()) {
			 return true;
		 }
		 else {
			 return false;
		 }
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
