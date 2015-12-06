package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.*;
import ca.ece.ubc.cpen221.mp5.*;
import java.math.*;

public class Algorithms {

	//A random number generator
	static Random random = new Random();
	
	/**
	 * Use k-means clustering to compute k clusters for the restaurants in the
	 * database.
	 * 
	 * @param k is the number of clusters desired. Must be greater than 0.
	 * @param db is the restaurant database being used.
	 * @return A List of sets, with each set representing a cluster of restaurants.
	 */
	public static List<Set<Restaurant>> kMeansClustering(int k, RestaurantDB db) {
		// TODO: Implement this method
		Centroid[] centroids = new Centroid[k];
		double maxLat, minLat, maxLong, minLong, latSpan, longSpan;
		
		//TODO: Use proper method of getting set of restaurants from DB
		Set<Restaurant> restaurants = db.query("I DON'T KNOW!!!");
		
		//If there are no restaurants return an empty list
		if(restaurants.isEmpty())
			return new ArrayList<Set<Restaurant>>();
		
		//Assign initial values to [min/max][latitude/longitude]
		maxLat = ((Restaurant) restaurants.toArray()[0]).getLatitude();
		minLat = maxLat;
		maxLong = ((Restaurant) restaurants.toArray()[0]).getLongitude();
		minLong = maxLong;
		
		//Iterate over all restaurants latitudes and longitudes to
		//assign proper [max/min][latitudes/longitudes]
		for(Restaurant r : restaurants){
			double latitude = r.getLatitude();
			double longitude = r.getLongitude();
			
			if(latitude > maxLat)
				maxLat = latitude;
			
			if(latitude < minLat)
				minLat = latitude;
			
			if(longitude > maxLong){
				maxLong = longitude;
				continue;
			}
			
			if(longitude < minLong)
				minLong = longitude;
		}
		
		latSpan = maxLat-minLat;
		longSpan = maxLong-minLong;
		
		//Create new centroids to be pseudo-randomly distributed with 
		//latitudes between minLat & maxLat, and longitudes between
		//minLong & maxLong
		for(Centroid c : centroids){
			c = new Centroid(minLat + (latSpan * random.nextDouble()),
					minLong + (longSpan * random.nextDouble()));
		}
		
		boolean continueFlag = true;
		
		//The actual k-means clustering algorithm
		while(continueFlag){
	
			//Assume this is the last iteration, unless one
			//of the centroids moves
			continueFlag = false;
			
			//Clear each centroids internal restaurant list
			for(Centroid c : centroids){
				c.clearRestaurants();
			}
			
			//Iterate over all restaurants, adding them to their
			//closest repective centroids
			for(Restaurant r : restaurants){
				centroids[closestCentroidIndex(r,centroids)].addRestaurant(r);
			}
			
			//Iterate over the centroids, attempting to shift each
			//one. If one moves, then another iteration of k-means
			//will occur.
			for(Centroid c : centroids){
				if(c.shift()){
					continueFlag = true;
				}
			}
			
		} //End of k-means
		
		List<Set<Restaurant>> returnVal = new ArrayList<Set<Restaurant>>();
		
		for(Centroid c : centroids){
			returnVal.add(c.getRestaurants());
		}
		
		return returnVal;
	}

	public static String convertClustersToJSON(List<Set<Restaurant>> clusters) {
		// TODO: Implement this method
		return null;
	}

	public static MP5Function getPredictor(User u, RestaurantDB db, MP5Function featureFunction) {
		// TODO: Implement this method
		return null;
	}

	public static MP5Function getBestPredictor(User u, RestaurantDB db, List<MP5Function> featureFunctionList) {
		// TODO: Implement this method
		return null;
	}
	
	/**
	 * Determines the closest centroid to a restaurant
	 */
	private static int closestCentroidIndex(Restaurant r, Centroid[] c){
		//Larger than the latitude/longitude system can permit
		double minDist = 10000.0;
		int index = 0; //Closest centroid's index
		
		//Iterate over all centroids to find minimum distance centroid's index
		for(int i = 0; i < c.length; i++){
			if(distance(r,c[i]) < minDist){
				index = i;
				minDist = distance(r,c[i]);
			}
		}
		
		return index;
	}
	
	/**
	 * Returns the distance between a restaurant and a centroid.
	 * @param r is the restaurant to be measured from
	 * @param c is the centroid to be measured to
	 * @return The distance in approximate degree between the restaurant
	 * and centroid.
	 */
	private static double distance(Restaurant r, Centroid c){
		double returnVal = Math.pow(r.getLatitude() - c.getLatitude(),2);
		returnVal += Math.pow(r.getLongitude() - c.getLongitude(), 2);
		returnVal = Math.sqrt(returnVal);
		return returnVal;
	}
}