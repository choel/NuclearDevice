package sadmean.mc.nuclearDevice;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class nuclearBomb {

	//parts
	Location centerDiamondBlockLocation;
	Location bottomGoldBlockLocation;
	Location topGoldBlockLocation;
	int totalDiamondBlocks = 1;
	int yield;
	boolean lowerCap = false;
	boolean upperCap = false;
	public Player player;
	
	//constrctor
	public nuclearBomb(Location centerDiamondBlock_build) {
		nuclearDevice.log_It("fine", "attempting constructor");
		//bottomGoldBlockLocation = bottomGoldBlock_build;
		//topGoldBlockLocation = topGoldBlock_build;
		double currentY;
		Block holderBlock;
		centerDiamondBlockLocation = centerDiamondBlock_build;
		holderBlock = centerDiamondBlockLocation.getBlock();
		Location cursor = centerDiamondBlockLocation;
		
		if(holderBlock.getTypeId() == 57) {

		//look for diamond above our switch


		
		currentY = cursor.getY();
		Location newLocation = new Location(cursor.getWorld(), cursor.getX(), currentY, cursor.getZ());		
		holderBlock = newLocation.getBlock();

		
		currentY = cursor.getY();
		//check blocks below our switch
		nuclearDevice.log_It("fine", "checked FIRST BLOCK type is " + Integer.toString(holderBlock.getTypeId()));
		while(holderBlock.getTypeId() == 57) {

			currentY = currentY - 1;
			newLocation = new Location(cursor.getWorld(), cursor.getX(), currentY, cursor.getZ());		
			holderBlock = newLocation.getBlock();
			totalDiamondBlocks++;			
		}
		
		//make sure we have gold caps, nigger!
		if(holderBlock.getTypeId() == 41) lowerCap = true;
		nuclearDevice.log_It("fine", "checked lowerCap type is " + Integer.toString(holderBlock.getTypeId()) + " but our bool is " + Boolean.toString(lowerCap));
		
		
		currentY = cursor.getY();
		newLocation = new Location(cursor.getWorld(), cursor.getX(), currentY, cursor.getZ());		
		holderBlock = newLocation.getBlock();
		nuclearDevice.log_It("fine", "reset checker");
		nuclearDevice.log_It("fine", "reset blockholder type is " + Integer.toString(holderBlock.getTypeId()));
		//check blocks above our switch
		while(holderBlock.getTypeId() == 57) {

			currentY = currentY + 1;
			newLocation = new Location(cursor.getWorld(), cursor.getX(), currentY, cursor.getZ());		
			holderBlock = newLocation.getBlock();
			totalDiamondBlocks++;			
		}
		if(holderBlock.getTypeId() == 41) upperCap = true;
		nuclearDevice.log_It("fine", "checked upperCap type is " + Integer.toString(holderBlock.getTypeId()) + " but our bool is " + Boolean.toString(lowerCap));
		
		//at this point, we have our total diamond blocks and gold caps. 
		//lets get the explosive yield
		
		yield = totalDiamondBlocks * 9;
		yield = totalDiamondBlocks + yield;
		
		} else {
			
		
			nuclearDevice.log_It("fine", "This is not a valid bomb design");
	
		}
	}
	
	public void explode(int time) {
		World explodeWorld = centerDiamondBlockLocation.getWorld();
		nuclearDevice.log_It("finest", "attempted exploson command");

		nuclearDevice.log_It("fine", "world name is " + explodeWorld.getName());
		//String checkWorld = explodeWorld.getName();
		if (lowerCap && upperCap) {
		//if (checkWorld == "herpina") {
			explodeWorld.createExplosion(centerDiamondBlockLocation, yield);
			explodeWorld.createExplosion(centerDiamondBlockLocation, yield);
			explodeWorld.createExplosion(centerDiamondBlockLocation, yield);
			explodeWorld.createExplosion(centerDiamondBlockLocation, yield);
			explodeWorld.createExplosion(centerDiamondBlockLocation, yield);
			explodeWorld.createExplosion(centerDiamondBlockLocation, yield);
			//explodeWorld.createExplosion(centerDiamondBlockLocation, yield);
			//explodeWorld.createExplosion(centerDiamondBlockLocation, yield);
			//explodeWorld.createExplosion(centerDiamondBlockLocation, yield);
			nuclearDevice.log_It("info", "Bomb armed on " + explodeWorld.getName() + ". DUN DUN DUN.");
			//warn all players
				int Xmessage = centerDiamondBlockLocation.getBlockX();
				int Zmessage = centerDiamondBlockLocation.getBlockZ();
				int playerNumber = 0;
				List<Player> players = explodeWorld.getPlayers();
				while (playerNumber < players.size()) {
					players.get(playerNumber);
					player.sendMessage("WARNING: NUKE DETECTED AT " + Integer.toString(Xmessage) + " BY " + Integer.toString(Zmessage));
					playerNumber++;
				}
				players.get(playerNumber);
				
			nuclearDevice.log_It("info", "yield is " + Integer.toString(yield));
		}
		
		

	}
}
