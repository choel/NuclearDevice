package sadmean.mc.nuclearDevice;

import org.bukkit.Location;
import org.bukkit.block.Block;
//import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRedstoneEvent;



public class nuclearBlockListener extends BlockListener {

	public void onBlockRedstoneChange(BlockRedstoneEvent event) {
		
		//recieved redstone event
		nuclearDevice.log_It("finer", "recieved change");
		
		Block block = event.getBlock();
		nuclearBomb bomb = new nuclearBomb(block.getLocation());
		bomb.explode(10);
		nuclearDevice.log_It("finer", "attempted exploson at source");
		
		Location LocTemp = block.getLocation();
		LocTemp = new Location(LocTemp.getWorld(), LocTemp.getX() - 1, LocTemp.getY(), LocTemp.getZ());
		bomb = new nuclearBomb(LocTemp);
		bomb.explode(10);
		nuclearDevice.log_It("finer", "attempted exploson at X - 1");	
		
		LocTemp = block.getLocation();
		LocTemp = new Location(LocTemp.getWorld(), LocTemp.getX() + 1, LocTemp.getY(), LocTemp.getZ());
		bomb = new nuclearBomb(LocTemp);
		bomb.explode(10);
		nuclearDevice.log_It("finer", "attempted exploson at X + 1");
		
		LocTemp = block.getLocation();
		LocTemp = new Location(LocTemp.getWorld(), LocTemp.getX(), LocTemp.getY(), LocTemp.getZ() - 1);
		bomb = new nuclearBomb(LocTemp);
		bomb.explode(10);
		nuclearDevice.log_It("finer", "attempted exploson at Z - 1");	
		
		LocTemp = block.getLocation();
		LocTemp = new Location(LocTemp.getWorld(), LocTemp.getX(), LocTemp.getY(), LocTemp.getZ() + 1);
		bomb = new nuclearBomb(LocTemp);
		bomb.explode(10);
		nuclearDevice.log_It("finer", "attempted exploson at Z + 1");	
	}
	
	
	public static nuclearDevice plugin; public nuclearBlockListener(nuclearDevice instance) { 
		 
        plugin = instance;
 }
	
}
