package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.*;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.*;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Tiger implements ArenaAnimal {
	
	Location location;
	private static int INITIAL_ENERGY = 300;
	private static int MAX_ENERGY = 400;
	private final int STRENGTH = 800; //I think 8 foxes could take on a tiger... maybe
	private static int COOLDOWN = 3;
	private static int VIEW_RANGE = 7; //Tigers have good eyes, I think.
	private static int BREEDING_ENERGY = 200;
	private static final ImageIcon tigerImage = Util.loadImage("tiger.gif");
	
	public int energy;
	
	public Tiger(Location initialLocation){
		this.location = initialLocation;
		this.energy = INITIAL_ENERGY;
	}
	
	@Override
	public int getEnergy() {
		return energy;
	}

	@Override
	public LivingItem breed() {
		Tiger child = new Tiger(location);
		child.energy = energy/2;
		energy = energy/2;
		return child;
	}

	@Override
	public void eat(Food food) {
		energy=Math.min(MAX_ENERGY,  energy + food.getMeatCalories());
	}

	@Override
	public void moveTo(Location targetLocation) {
		location = targetLocation;
		
	}

	@Override
	public int getMovingRange() {
		return 2; //Can pounce two spaces
	}

	@Override
	public ImageIcon getImage() {
		return tigerImage;
	}

	@Override
	public String getName() {
		return "Area under the Gaussiam, squared"; 
		//That's Pi, but he also goes by Big P once you get to know him
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public int getStrength() {
		return STRENGTH;
	}

	@Override
	public void loseEnergy(int energy) {
		this.energy = this.energy - energy;
		
	}

	@Override
	public boolean isDead() {
		return energy <= 0;
	}

	@Override
	public int getPlantCalories() {
		return 0; //Is not plant
	}

	@Override
	public int getMeatCalories() {
		return energy;
		//Conservation of energy
	}

	@Override
	public int getCoolDownPeriod() {
		return COOLDOWN;
	}

	@Override
	public Command getNextAction(World world) {
		energy = energy - 2; //And time takes its toll on the ol' Tiger
		Direction d = null;
		Item food = null;
		
		if(getEnergy() >= 360){
			Location x = Util.getRandomEmptyAdjacentLocation(world, location);
			if(x != null)
				return new BreedCommand(this, x);
		}
		
		for(Item i : world.searchSurroundings(this)){
			if(i.getMeatCalories() > 0 && getStrength() > i.getStrength()){
				if(getXDistance(location, i.getLocation()) == 1 &&
						getYDistance(location, i.getLocation()) == 0 ||
						getXDistance(location, i.getLocation()) == 0 &&
						getYDistance(location, i.getLocation()) == 1)
					return new EatCommand(this, i);
				else if(getXDistance(location, i.getLocation()) == 
						getYDistance(location, i.getLocation())){
					
					if(i.getLocation().getX() - location.getX() > 0)
						d = Direction.EAST;
					else
						d = Direction.WEST;
					food = i;
					continue;
				}
						
						
				d =Util.getDirectionTowards(location, i.getLocation());
				food = i;
			}
		}
		if(d == Direction.WEST){
			if(getXDistance(location, food.getLocation()) > 2 && Util.isValidLocation(world, new Location(location.getX() - 2, location.getY()))
					&& Util.isLocationEmpty(world, new Location(location.getX() - 2, location.getY())))
					return new MoveCommand(this, new Location(location.getX() - 2, location.getY()));
			
			else if(Util.isValidLocation(world, new Location(location.getX() - 1, location.getY()))
					&& Util.isLocationEmpty(world, new Location(location.getX() - 1, location.getY())))
				return new MoveCommand(this, new Location(location.getX() - 1, location.getY()));
		}
		
		if(d == Direction.EAST){
			if(getXDistance(location, food.getLocation()) > 2 && Util.isValidLocation(world, new Location(location.getX() + 2, location.getY()))
					&& Util.isLocationEmpty(world, new Location(location.getX() + 2, location.getY())))
					return new MoveCommand(this, new Location(location.getX() + 2, location.getY()));
			
			else if(Util.isValidLocation(world, new Location(location.getX() + 1, location.getY()))
					&& Util.isLocationEmpty(world, new Location(location.getX() + 1, location.getY())))
				return new MoveCommand(this, new Location(location.getX() + 1, location.getY()));
		}
		
		if(d == Direction.NORTH){
			if(getYDistance(location, food.getLocation()) > 2 && Util.isValidLocation(world, new Location(location.getX(), location.getY() + 2))
					&& Util.isLocationEmpty(world, new Location(location.getX(), location.getY() + 2)))
					return new MoveCommand(this, new Location(location.getX(), location.getY() + 2));
			
			else if(Util.isValidLocation(world, new Location(location.getX(), location.getY() + 1))
					&& Util.isLocationEmpty(world, new Location(location.getX(), location.getY() + 1)))
				return new MoveCommand(this, new Location(location.getX(), location.getY() + 1));
		}
		
		if(d == Direction.SOUTH){
			if(getYDistance(location, food.getLocation()) > 2 && Util.isValidLocation(world, new Location(location.getX(), location.getY() - 2))
					&& Util.isLocationEmpty(world, new Location(location.getX(), location.getY() - 2)))
					return new MoveCommand(this, new Location(location.getX(), location.getY() - 2));
			
			else if(Util.isValidLocation(world, new Location(location.getX(), location.getY() - 1))
					&& Util.isLocationEmpty(world, new Location(location.getX(), location.getY() - 1)))
				return new MoveCommand(this, new Location(location.getX(), location.getY() - 1));
		}
		
		return new WaitCommand();
	}

	@Override
	public int getMaxEnergy() {
		return MAX_ENERGY;
	}

	@Override
	public int getViewRange() {
		return VIEW_RANGE;
	}

	@Override
	public int getMinimumBreedingEnergy() {
		return BREEDING_ENERGY;
	}
	
	private static int getXDistance(Location from, Location to){
		int x = to.getX() - from.getX();
		if(x<0) x = -1 * x;
		return x;
	}
	
	private static int getYDistance(Location from, Location to){
		int y = to.getY() - from.getY();
		if(y < 0) y = -1 * y;
		return y;
	}

}
