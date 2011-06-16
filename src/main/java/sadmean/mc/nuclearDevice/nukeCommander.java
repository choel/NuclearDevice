package sadmean.mc.nuclearDevice;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class nukeCommander {
			
	//Parts
	CommandSender senderPlayer;
	Command CommandSent;
	String[] arguements;
	
			nukeCommander(CommandSender sender, Command cmd, String[] args) {
				senderPlayer = sender;
				CommandSent = cmd;
				arguements = args;
				
			}
			
			public void commandHelper() {
				
				senderPlayer.sendMessage("I recieved your command and did nothing with it");
				
			}
			
}
