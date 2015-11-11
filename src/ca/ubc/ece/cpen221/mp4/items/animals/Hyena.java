package ca.ubc.ece.cpen221.mp4.items.animals;

import java.util.Random;

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

public class Hyena implements ArenaAnimal {
	
	Location location;
	private static int INITIAL_ENERGY = 120;
	private static int MAX_ENERGY = 150;
	private final int STRENGTH = 200; //2 foxes ~= 1 hyena
	private static int COOLDOWN = 3;
	private static int VIEW_RANGE = 5; 
	private static int BREEDING_ENERGY = 80;
	private static final ImageIcon hyenaImage = Util.loadImage("hyena.gif");
	
	public int energy;
	
	public Hyena(Location initialLocation){
		this.location = initialLocation;
		this.energy = INITIAL_ENERGY;
	}
	
	@Override
	public int getEnergy() {
		return energy;
	}

	@Override
	public LivingItem breed() {
		Hyena child = new Hyena(location);
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
		return 1;
	}

	@Override
	public ImageIcon getImage() {
		return hyenaImage;
	}

	@Override
	public String getName() {
		Random r = new Random();
		String name;
		switch(r.nextInt(12)){
		case 0:
			name = "Stan";
			break;
		case 1:
			name = "Kyle";
			break;
		case 2:
			name = "Eric";
			break;
		case 3:
			name = "Kenny";
			break;
		case 4:
			name = "Mr. Hankey";
			break;
		case 5:
			name = "Mr. Garrison";
			break;
		case 6:
			name = "P.C. Principal";
			break;
		case 7:
			name = "Jimmy";
			break;
		case 8:
			name = "Craig";
			break;
		case 9:
			name = "Butters";
			break;
		case 10:
			name = "Randy";
			break;
		default:
			name = "Token";
		}
		
		
		return name;
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
		energy = energy - 1;
		Direction d = null;
		
		if(getEnergy() >= 100){
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
					continue;
				}
				d =Util.getDirectionTowards(location, i.getLocation());
			}
		}
		if(d == Direction.WEST){
			
			if(Util.isValidLocation(world, new Location(location.getX() - 1, location.getY()))
					&& Util.isLocationEmpty(world, new Location(location.getX() - 1, location.getY())))
				return new MoveCommand(this, new Location(location.getX() - 1, location.getY()));
		}
		
		if(d == Direction.EAST){
			
			if(Util.isValidLocation(world, new Location(location.getX() + 1, location.getY()))
					&& Util.isLocationEmpty(world, new Location(location.getX() + 1, location.getY())))
				return new MoveCommand(this, new Location(location.getX() + 1, location.getY()));
		}
		
		if(d == Direction.NORTH){
			
			if(Util.isValidLocation(world, new Location(location.getX(), location.getY() + 1))
					&& Util.isLocationEmpty(world, new Location(location.getX(), location.getY() + 1)))
				return new MoveCommand(this, new Location(location.getX(), location.getY() + 1));
		}
		
		if(d == Direction.SOUTH){
			if(Util.isValidLocation(world, new Location(location.getX(), location.getY() - 1))
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
