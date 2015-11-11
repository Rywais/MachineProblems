package ca.ubc.ece.cpen221.mp4.vehicles;

import ca.ubc.ece.cpen221.mp4.*;
import ca.ubc.ece.cpen221.mp4.items.*;

public interface ArenaVehicle extends Actor, MoveableItem{
	
	/**
	 * Returns the amount of gas remaining in the vehicle specified
	 * @return an integer representing the remaining gas in a vehicle
	 */
	int getRemainingGas();
	
	/**
	 * Returns an integer representing the speed of the vehicle
	 * in units of distance/16ticks
	 * @return an integer representing the vehicle's speed.
	 */
	int getSpeed();
	
	/**
	 * Causes the vehicle to accelerate in its current facing direction
	 */
	void accelerate();
	
	/**
	 * Causes the vehicle to decelerate in its current facing direction
	 */
	void decelerate();
	
	/**
	 * Causes the vehicle to change direction
	 * @param d the new direction to go
	 */
	void turn(Direction d);
	
	/**
	 * returns the value of the nearest collision within the vehicle's
	 * viewing distance or 0 if no threats are imminent
	 * @return the distance to the nearest collision or 0 if none are detectable
	 */
	int detectCollision(World world, Direction d);
}
