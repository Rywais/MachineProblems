package ca.ece.ubc.cpen221.mp5.statlearning;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class ExtractPriceFunction implements MP5Function {

	/**
	 * Returns the restaurant's price if it's stored within the db
	 */
	@Override
	public double f(Restaurant yelpRestaurant, RestaurantDB db) {
		//TODO: Add proper call
		if(db.query("All Restaurants").contains(yelpRestaurant)){
			return (double) yelpRestaurant.getPrice();
		}
		
		return -10000.0;
	}
	
	
	
}
