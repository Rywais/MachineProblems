package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.*;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class ExtractCategoryFunction  implements MP5Function{

	
	/**
	 * Returns the restaurant's Categories' value if it's stored within the db,
	 * -10000.0 otherwise. The value is determined by a priority system following
	 * the value system: Dietary Restrictions Accommodation > Novelty of
	 * Restaurant category > Classical dining.
	 */
	@Override
	public double f(Restaurant yelpRestaurant, RestaurantDB db){
		
		if(!db.query("All Restaurants").contains(yelpRestaurant)){
			return -10000.0;
		}
		
		Set<String> myList = yelpRestaurant.getCategories();
		
		//Meets certain Dietary restrictions
		if(myList.contains("Vegan"))
			return 20;
		if(myList.contains("Vegetarian"))
			return 20;
		if(myList.contains("Halal"))
			return 20;
		
		//Exotic
		if(myList.contains("Vietnamese"))
			return 15;
		if(myList.contains("Asian Fusion"))
			return 15;
		if(myList.contains("Persian/Iranian"))
			return 15;
		if(myList.contains("Greek"))
			return 15;
		if(myList.contains("Mediterranean"))
			return 15;
		if(myList.contains("Turkish"))
			return 15;
		if(myList.contains("Middle Eastern"))
			return 15;
		if(myList.contains("African"))
			return 15;
		if(myList.contains("Caribbean"))
			return 15;
		if(myList.contains("Dim Sum"))
			return 15;
		if(myList.contains("Specialty Food"))
			return 15;
		
		//Somewhat Exotic
		if(myList.contains("Thai"))
			return 10;
		if(myList.contains("Fondue"))
			return 10;
		if(myList.contains("Chinese"))
			return 10;
		if(myList.contains("Mexican"))
			return 10;
		if(myList.contains("Indian"))
			return 10;
		if(myList.contains("American (New)"))
			return 10;
		if(myList.contains("NightLife"))
			return 10;
		if(myList.contains("Italian"))
			return 10;
		if(myList.contains("Korean"))
			return 10;
		if(myList.contains("Creperies"))
			return 10;
		if(myList.contains("Japanese"))
			return 10;
		if(myList.contains("Street Vendors"))
			return 10;
		if(myList.contains("Food Stands"))
			return 10;
		if(myList.contains("Sushi Bars"))
			return 10;
		if(myList.contains("Brazilian"))
			return 10;
		
		return 5; //Doesn't qualify as exotic
		
	}
	
}
