package ca.ece.ubc.cpen221.mp5.statlearning;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class LinRegFunction implements MP5Function{

	private final double slope, intercept;
	private final MP5Function featureFunction;
	
	/**
	 * Creates a linear regression based MP5Function with specified slope
	 * & intercept, as well as user defined featureFunction for evaluating a restaurant
	 * to determine the effective "x-value"
	 * @param slope is the Linear regression slope
	 * @param intercept is the linear regression intercept
	 * @param featureFunction is the user-defined feature function for
	 * converting a restaurant to an "x-value"
	 */
	public LinRegFunction(double slope, double intercept, MP5Function featureFunction){
		this.slope = slope;
		this.intercept = intercept;
		this.featureFunction = featureFunction;
	}
	
	/**
	 * Returns the users predicted rating based on the linear regression
	 * formula associated with this object. Returns -10000.0 if the
	 * restaurant is not within the database.
	 */
	@Override
	public double f(Restaurant yelpRestaurant, RestaurantDB db) {
		
		//TODO: Enter the proper query
		if(!db.query("All restaurants").contains(yelpRestaurant))
			return -10000.0;
		
		double returnVal = (featureFunction.f(yelpRestaurant, db) * slope) + intercept;
		
		//Account for out-of-bounds predictions
		if(returnVal > 5.0)
			return 5.0;
		else if(returnVal < 0.0)
			return 0.0;
		
		return returnVal; 
	}
	
	
}
