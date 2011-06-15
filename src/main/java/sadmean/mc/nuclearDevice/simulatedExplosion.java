package sadmean.mc.nuclearDevice;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;

/*
* This Class is still in early stages. It should not (can not?) be used to replace a standard explosion yet.
*
*
* What is a simulated explosion?
* In minecraft, a single, standard size explosion is enough to give the client pause.
* with recent enhancements to bukkit, it became simple to create a much larger then normal explosion.
* However, this highlighted the fact that the client simple dies when presented with a large enough explosion.
* and large enough in this case is only a small amount larger then a standard TnT explosion.
* so, a simulated explosion is this;
*
* step 1: given a central block, we scan for blocks around it (within a bounding area set by a provided yield. We place those blocks into a list
* step 2: we scan for players inside the bounding area. We create a standard explosion on them
* we do this because it causes them to hear the explosion and see some particles. It should not cause enough particles to produce lag
* kick them from the server rather then send lighting updates?
* step 3: we take all the blocks in our list and replace them with air
*should we delete bedrock?
*/

public class simulatedExplosion {

	//important members
	Location epicenter;
	double yield;
	List<Block> explodeBlocks;
	boolean kickPlayers = true; //should we force players caught in the explosion to reconnect?



	//the constructors
	public simulatedExplosion(Location givenEpicenter, double givenYield) {
		//epicenter is the center point of the explosion
		//yield is the total value of the explosion.
		//as provided by a function that would give the same value for a simillar sized explosion for a world.createExplosion()
		//this means that 3 would yield a same-size explosion as a creeper, and 4 for TnT

		epicenter = givenEpicenter;
		yield = convertToSimulatedYield(givenYield);

	}

	public simulatedExplosion(Location givenEpicenter, double givenYield, boolean kick) {
		//same contructor as above, but takes a boolean to kick or not kick players

		kickPlayers = kick;
		epicenter = givenEpicenter;
		yield = convertToSimulatedYield(givenYield);

	}





	public boolean explode() {
		//this is where we do everything
		//returns false if something went wrong
		double epicenterX = epicenter.getX();
		double epicenterY = epicenter.getY();
		double epicenterZ = epicenter.getZ();


		return false;
	}


	private double convertToSimulatedYield(double givenYield) {
		//the given yield is probably going to be too low of a number for us to work with.
		//so this function will, at some point, convert it to our standard value
		double convertedYield;
		convertedYield = givenYield;

		return convertedYield;

	}



}
