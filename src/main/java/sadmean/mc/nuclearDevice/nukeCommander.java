package sadmean.mc.nuclearDevice;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;


public class nukeCommander {
			
	//Parts
	CommandSender senderPlayer;
	Command CommandSent;
	String[] arguements;
	String chatStarter = ChatColor.BLUE + "[ND] " + ChatColor.GRAY;
	int value; //stored for later use
	
			nukeCommander(CommandSender sender, Command cmd, String[] args) {
				senderPlayer = sender;
				CommandSent = cmd;
				arguements = args;
				
			}
			
			public boolean commandHelper() {
					
				if (arguements.length <= 1) return false;
				if (!arguements[0].equalsIgnoreCase("setKick") && !arguements[0].equalsIgnoreCase("setBedrock") && !arguements[0].equalsIgnoreCase("setIgnite") && !arguements[0].equalsIgnoreCase("setUSE") && !arguements[0].equalsIgnoreCase("setPayload") && !arguements[0].equalsIgnoreCase("setCap")) {
					senderPlayer.sendMessage(chatStarter + "Improper arguement. Valid arguments:");
					senderPlayer.sendMessage(ChatColor.GRAY + "/nuclearDevice [setUSE, setPayload, setCap] [values]");
				} else {
					if (arguements[0].equalsIgnoreCase("setUSE")) {
						if(arguements[1].equalsIgnoreCase("false")) {
							nuclearDevice.setUSE(false);
							senderPlayer.sendMessage(chatStarter + "set UseSimulatedExplosion to false");
						} else {
							if(arguements[1].equalsIgnoreCase("true")) {
								nuclearDevice.setUSE(true);
								senderPlayer.sendMessage(chatStarter + "set UseSimulatedExplosion to true");
							} else {
								senderPlayer.sendMessage(chatStarter + "Improper arguement error");
							}
						}
					}
					
					if (arguements[0].equalsIgnoreCase("setPayload")) {
						try {
							value = Integer.parseInt(arguements[1]);
						} catch(NumberFormatException e) {
							value = 0;
						}
						if (value != 0) {
							nuclearDevice.setPayload(value);
							senderPlayer.sendMessage(chatStarter + "payloadTypeID set to " + arguements[1]);
						} else {
							senderPlayer.sendMessage(chatStarter + "NumberFormatException. Maybe NaN, also Can't use 0.");
						}
					}
					
					if (arguements[0].equalsIgnoreCase("setCap")) {
						try {
							value = Integer.parseInt(arguements[1]);
						} catch(NumberFormatException e) {
							value = 0;
						}
						if (value != 0) {
							nuclearDevice.setCap(value);
							senderPlayer.sendMessage(chatStarter + "capTypeID set to " + arguements[1]);
						} else {
							senderPlayer.sendMessage(chatStarter + "NumberFormatException. Maybe NaN, also Can't use 0.");
						}
					}
					
					if (arguements[0].equalsIgnoreCase("setBedrock")) {
						if(arguements[1].equalsIgnoreCase("false")) {
							nuclearDevice.setDestroyBedrock(false);
							senderPlayer.sendMessage(chatStarter + "set DestroyBedrock to false");
						} else {
							if(arguements[1].equalsIgnoreCase("true")) {
								nuclearDevice.setDestroyBedrock(true);
								senderPlayer.sendMessage(chatStarter + "set DestroyBedrock to true");
							} else {
								senderPlayer.sendMessage(chatStarter + "Improper arguement error");
							}
						}
					}
					
					if (arguements[0].equalsIgnoreCase("setIgnite")) {
						if(arguements[1].equalsIgnoreCase("false")) {
							nuclearDevice.setIgniteOuterLayer(false);
							senderPlayer.sendMessage(chatStarter + "set IgniteOuterLayer to false");
						} else {
							if(arguements[1].equalsIgnoreCase("true")) {
								nuclearDevice.setIgniteOuterLayer(true);
								senderPlayer.sendMessage(chatStarter + "set IgniteOuterLayer to true");
							} else {
								senderPlayer.sendMessage(chatStarter + "Improper arguement error");
							}
						}
					}
					
					if (arguements[0].equalsIgnoreCase("setKick")) {
						if(arguements[1].equalsIgnoreCase("false")) {
							nuclearDevice.setKickPlayers(false);
							senderPlayer.sendMessage(chatStarter + "set KickPlayers to false");
						} else {
							if(arguements[1].equalsIgnoreCase("true")) {
								nuclearDevice.setKickPlayers(true);
								senderPlayer.sendMessage(chatStarter + "set KickPlayers to true");
							} else {
								senderPlayer.sendMessage(chatStarter + "Improper arguement error");
							}
						}
					}
					
				}
			return true;	
			}
			
}
