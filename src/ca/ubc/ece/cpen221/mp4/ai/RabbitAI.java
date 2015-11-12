package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Iterator;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;
import ca.ubc.ece.cpen221.mp4.items.animals.Fox;
import ca.ubc.ece.cpen221.mp4.items.animals.Rabbit;

/**
 * Your Rabbit AI.
 */
public class RabbitAI extends AbstractAI {

	private int closest = 10; // max number; greater than rabbit's view range
	private int temp;
	private boolean foxFound;

	public RabbitAI() {
	}

	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
		int x = animal.getLocation().getX();
		int y = animal.getLocation().getY();
		
		int N = 0,S = 0,E = 0,W = 0; //Directional weights (positive is good)
		int eatDesire = (int) (10.0 * (1.0 - ((double) animal.getEnergy() / animal.getMaxEnergy())));
		for(Item i : world.searchSurroundings(animal)){
			if(i.getStrength() > animal.getStrength()){
				N = N - (10 * getYDist(animal, i));
				S = S + (10 * getYDist(animal, i));
				E = E - (10 * getXDist(animal, i));
				W = W + (10 * getXDist(animal, i));
			}
			else if (i.getPlantCalories() > 0){
				N = N - (eatDesire * getYDist(animal, i));
				S = S + (eatDesire * getYDist(animal, i));
				E = E - (eatDesire * getXDist(animal, i));
				W = W + (eatDesire * getXDist(animal, i));
			}
		}
		//Weights are assigned
		
		int[] weights = {N,S,E,W};
		
		sortWeights(weights);
		
		for(int i = 3; i >= 0; i--){
			Item activeItem = null;
			if(N == weights[i]){
				for(Item j : world.searchSurroundings(animal)){
					if(j.getLocation().getX() == x && j.getLocation().getY() == y+1){
						activeItem = j;
						break;
					}
				}
				if(activeItem == null){
					if(Util.isValidLocation(world,(new Location(x,y+1)))){
						return new MoveCommand(animal,new Location(x,y+1));
					}
					else continue;
				}
					
				else if(activeItem.getPlantCalories() > 0)
					return new EatCommand(animal,activeItem);
				else
					continue;
			}
			
			if(E == weights[i]){
				for(Item j : world.searchSurroundings(animal)){
					if(j.getLocation().getX() == x+1 && j.getLocation().getY() == y){
						activeItem = j;
						break;
					}
				}
				if(activeItem == null){
					if(Util.isValidLocation(world,(new Location(x+1,y)))){
						return new MoveCommand(animal,new Location(x+1,y));
					}
					else continue;
				}
					
				else if(activeItem.getPlantCalories() > 0)
					return new EatCommand(animal,activeItem);
				else
					continue;
			}
			
			if(S == weights[i]){
				for(Item j : world.searchSurroundings(animal)){
					if(j.getLocation().getX() == x && j.getLocation().getY() == y-1){
						activeItem = j;
						break;
					}
				}
				if(activeItem == null){
					if(Util.isValidLocation(world,(new Location(x,y-1)))){
						return new MoveCommand(animal,new Location(x,y-1));
					}
					else continue;
				}
					
				else if(activeItem.getPlantCalories() > 0)
					return new EatCommand(animal,activeItem);
				else
					continue;
			}
			
			else if(W == weights[i]){
				for(Item j : world.searchSurroundings(animal)){
					if(j.getLocation().getX() == x-1 && j.getLocation().getY() == y){
						activeItem = j;
						break;
					}
				}
				if(activeItem == null){
					if(Util.isValidLocation(world,(new Location(x-1,y)))){
						return new MoveCommand(animal,new Location(x-1,y));
					}
					else continue;
				}
					
				else if(activeItem.getPlantCalories() > 0)
					return new EatCommand(animal,activeItem);
				else
					continue;
			}
			
		}
		
		return new WaitCommand();
	}
	
	int getXDist(Item a, Item b){
		return b.getLocation().getX() - a.getLocation().getX();
	}
	
	int getYDist(Item a, Item b){
		return b.getLocation().getY() - a.getLocation().getY();
	}
	
	//sorts array from lowest to highest
	public void sortWeights(int[] w){
		for(int i = 0; i < w.length; i++){
			for(int j = 0; j < w.length - 1; j++){
				if(w[j] > w[j+1]){
					int temp = w[j];
					w[j] = w[j+1];
					w[j+1] = temp;
				}
			}
				
		}
	}
}
