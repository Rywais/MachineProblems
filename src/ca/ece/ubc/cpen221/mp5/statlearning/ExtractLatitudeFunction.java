package ca.ece.ubc.cpen221.mp5.statlearning;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class ExtractLatitudeFunction implements MP5Function{

	
	/**
	 * Returns the restaurant's Latitude if it's stored within the db,
	 * -10000.0 otherwise
	 */
	@Override
	public double f(Restaurant yelpRestaurant, RestaurantDB db) {
		//TODO: Add proper call
		if(db.query("All Restaurants").contains(yelpRestaurant)){
			return (double) yelpRestaurant.getLatitude();
		}
		
		return -10000.0;
	}
	
}
