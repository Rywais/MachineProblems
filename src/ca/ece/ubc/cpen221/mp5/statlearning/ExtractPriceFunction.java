package ca.ece.ubc.cpen221.mp5.statlearning;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class ExtractPriceFunction implements MP5Function {

	/**
	 * Returns the restaurant's price if it's stored within the db,
	 * -10000.0 otherwise
	 */
	@Override
	public double f(Restaurant yelpRestaurant, RestaurantDB db) {
		
		if(db.getRestaurantSet().contains(yelpRestaurant)){
			return (double) yelpRestaurant.getPrice();
		}
		
		return -10000.0;
	}
	
	
	
}
