package ca.ubc.ece.cpen221.mp4.vehicles;

import java.util.Random;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.*;
import ca.ubc.ece.cpen221.mp4.items.Item;

public class Truck implements ArenaVehicle{

	private static final int ACCELERATION = 1;
	private static final int STRENGTH = 1100;
	private static final int MAX_SPEED = 5;
	private static final int MAX_TURNING_SPEED = 2;
	private static final int INITIAL_GAS = 400;
	private static final int VIEW_RANGE = 8;
	private static final Random r = new Random();
	
	private int gas;
	private int speed;
	Direction currentDirection;
	
	private Location location;
	private static final ImageIcon carImage = Util.loadImage("trucks.gif");
	
	public Truck(Location loc){
		this.location = loc;
		this.gas = INITIAL_GAS;
		this.speed = 0;
		this.currentDirection = Util.getRandomDirection();
	}
	
	@Override
	public int getCoolDownPeriod() {
		if(speed == 0)
			return 1;
		else
			return 16/ speed;
	}

	@Override
	public Command getNextAction(World world) {
		int x = location.getX();
		int y = location.getY();
		gas--;
		
		if(detectCollision(world, currentDirection) == 0){
			
			int rNum = r.nextInt(3);
			if(rNum == 1)
				accelerate();
			else if (rNum == 2)
				decelerate();
		}
		else if(detectCollision(world,currentDirection) == 1 && speed > MAX_TURNING_SPEED){
			gas = 0;
			speed = 0;
		}
		else if (speed > MAX_TURNING_SPEED){
			decelerate();
		}
		else{
			int cNorth = detectCollision(world, Direction.NORTH);
			int cSouth = detectCollision(world, Direction.SOUTH);
			int cEast = detectCollision(world, Direction.EAST);
			int cWest = detectCollision(world, Direction.WEST);
			boolean skip = false;
			
			if(cNorth == 0){
				turn(Direction.NORTH);
				skip = true;
			}
			else if(cEast == 0){
				turn(Direction.EAST);
				skip = true;
			}
			else if(cSouth == 0){
				//Turn
				turn(Direction.SOUTH);
				skip = true;
			}
			else if(cWest == 0){
				turn(Direction.WEST);
				skip = true;
			}
			
			//No directions are good
			if(!skip){
				int maxDistance = Math.max(Math.max(cNorth,  cSouth), Math.max(cEast,  cWest));
				
				if(cNorth == maxDistance)
					turn(Direction.NORTH);
				if(cEast == maxDistance)
					turn(Direction.EAST);
				if(cSouth == maxDistance)
					turn(Direction.SOUTH);
				if(cWest == maxDistance)
					turn(Direction.WEST);
			}
			
		}
			
		
		//Beginning of the command phase
		
		if(speed == 0)
			return new WaitCommand();
		
		
		if(currentDirection == Direction.NORTH){
			if(!Util.isValidLocation(world, new Location(x,y+1)) || 
					!Util.isLocationEmpty(world, new Location(x,y+1))){
				
				for(Item i : world.searchSurroundings(new Location(x,y + 1), 1)){
					if(i.getLocation().equals(new Location(x,y+1)))
						i.loseEnergy(10000000);
				}
				
				return new WaitCommand();
			}
			
			return new MoveCommand(this, new Location(x,y + 1));
		}
		
		if(currentDirection == Direction.SOUTH){
			if(!Util.isValidLocation(world, new Location(x,y-1)) || 
					!Util.isLocationEmpty(world, new Location(x,y-1))){
				
				for(Item i : world.searchSurroundings(new Location(x,y - 1), 1)){
					if(i.getLocation().equals(new Location(x,y-1)))
						i.loseEnergy(10000000);
				}
				
				return new WaitCommand();
			}
			
			return new MoveCommand(this, new Location(x,y - 1));
		}
		if(currentDirection == Direction.EAST){
			if(!Util.isValidLocation(world, new Location(x+1,y)) || 
					!Util.isLocationEmpty(world, new Location(x+1,y))){
				
				for(Item i : world.searchSurroundings(new Location(x+1,y), 1)){
					if(i.getLocation().equals(new Location(x+1,y))){
						i.loseEnergy(10000000);
					}
				}
				
				return new WaitCommand();
			}
			
			return new MoveCommand(this, new Location(x+1,y));
		}
		//At this point it must be direction West with >0 speed
		if(!Util.isValidLocation(world, new Location(x-1,y)) || 
				!Util.isLocationEmpty(world, new Location(x-1,y))){
			
			for(Item i : world.searchSurroundings(new Location(x-1,y), 1)){
				if(i.getLocation().equals(new Location(x-1,y)))
					i.loseEnergy(10000000);
			}
			
			return new WaitCommand();
		}
		
		return new WaitCommand();
		
		
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
		return carImage;
	}

	@Override
	public String getName() {
		return "Chitty Chitty Bang Bang";
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
		gas = gas - energy;
		
	}

	@Override
	public boolean isDead() {
		 if(gas <= 0){
			 return true;
		 }
		return false;
	}

	@Override
	public int getPlantCalories() {
		return 0;//This is a machine...
	}

	@Override
	public int getMeatCalories() {
		return 0;//This is a machine....
	}

	@Override
	public int getRemainingGas() {
		return gas;
	}

	@Override
	public int getSpeed() {
		return speed;
	}
	
	@Override
	public void accelerate(){
		
		speed = Math.min(MAX_SPEED, speed + ACCELERATION);
	}
	
	@Override
	public void decelerate(){
		speed = Math.max(0, speed - ACCELERATION);
	}
	
	public void turn(Direction d){
		if(speed <= MAX_TURNING_SPEED)
			currentDirection = d;
	}
	
	@Override
	public int detectCollision(World world, Direction d){
		int x = location.getX();
		int y = location.getY();
		int collisionDist = 1000000; //Arbitrary Large number
		
		if(d == Direction.NORTH){
			for(Item i : world.searchSurroundings(location, VIEW_RANGE)){
				if(i.getStrength() < STRENGTH)
					continue;
				int iX = i.getLocation().getX();
				int iY = i.getLocation().getY();
				if(iX == x && iY > y)
					collisionDist = Math.min(collisionDist, iY - y);
					
			}
			collisionDist = Math.min(collisionDist, world.getHeight() - y);	
		}
		
		if(d == Direction.SOUTH){
			for(Item i : world.searchSurroundings(location, VIEW_RANGE)){
				if(i.getStrength() < STRENGTH)
					continue;
				int iX = i.getLocation().getX();
				int iY = i.getLocation().getY();
				if(iX == x && iY < y)
					collisionDist = Math.min(collisionDist, y - iY);
					
			}
			collisionDist = Math.min(collisionDist, y);		
		}
		
		if(d == Direction.EAST){
			for(Item i : world.searchSurroundings(location, VIEW_RANGE)){
				if(i.getStrength() < STRENGTH)
					continue;
				int iX = i.getLocation().getX();
				int iY = i.getLocation().getY();
				if(iY == y && iX > x)
					collisionDist = Math.min(collisionDist, iX - x);
					
			}
			collisionDist = Math.min(collisionDist, world.getWidth() - x);	
		}
		
		if(d == Direction.WEST){
			for(Item i : world.searchSurroundings(location, VIEW_RANGE)){
				if(i.getStrength() < STRENGTH)
					continue;
				int iX = i.getLocation().getX();
				int iY = i.getLocation().getY();
				if(iY == y && x > iX)
					collisionDist = Math.min(collisionDist, x-iX);
					
			}
			collisionDist = Math.min(collisionDist, x);	
		}
		
		if(collisionDist > VIEW_RANGE){
			
			return 0;
		}
		
		return collisionDist;
			
	}

}
