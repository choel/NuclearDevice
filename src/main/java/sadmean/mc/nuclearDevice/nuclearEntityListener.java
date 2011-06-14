package sadmean.mc.nuclearDevice;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.ItemSpawnEvent;

//THIS ENTIRE CLASS MAY ACTUALLY BE USELESS NOW. FUCK THAT. LEAVING IT HERE FOR NOW

public class nuclearEntityListener extends EntityListener {

	
	public void onItemSpawn(ItemSpawnEvent event) {
		Location eventLocation = event.getLocation();
		double eventX = eventLocation.getX();
		double eventY = eventLocation.getY();
		double eventZ = eventLocation.getZ();
		
		//Logger log = nuclearDevice.log;

		if(2 == 1) { //at some point this should be "if nuke explosion in less the one second or one second ago"
			if (2 == 1) { //this one checks if Y is in our range
				if(2 == 1) { //this one should be if X is in our range
					if ( 2 == 1) { //and this one checks our Z
						//*****************
						//STOP ITEM SPAWN HERE
						//*******************
					} else {
						nuclearDevice.log_It("finest", "did nothing cause location (Z) is out of our range");
					}
				} else {
					nuclearDevice.log_It("finest", "did nothing cause location (X) is out of our range");
				}
			} else {
				nuclearDevice.log_It("finest", "did nothing cause location (Y) is out of our range");
			}
		} else {
			nuclearDevice.log_It("finest", "did nothing cause no explosion is sup");
		}
		
	}
	
	public static nuclearDevice plugin; public nuclearEntityListener(nuclearDevice instance) { 
		 
        plugin = instance;
 }
}
