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
		for(int i = 0; i < centroids.length; i++){
			centroids[i] = new Centroid(minLat + (latSpan * random.nextDouble()),
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

	/**
	 * Returns a string written in JSON format which represents the clusters
	 * of restaurants provided from the list of restaurant clusters.
	 * @param clusters is the list of restaurant clusters.
	 * @return a string in JSON format which represents the list of clusters
	 */
	public static String convertClustersToJSON(List<Set<Restaurant>> clusters) {
		// TODO: Implement this method
		
		if(clusters.size() == 0)
			return "";
		
		String returnVal = "[";
		
		//For all clusters...
		for(int i = 0; i < clusters.size(); i++){
			//For all restaurants in each cluster, add the JSON formatted
			//String to the return value
			for(Restaurant r: clusters.get(i)){
				returnVal = returnVal + restaurantStringJSON(i+1,1.0,r) + ", ";
			}
		}
		
		returnVal = returnVal.substring(0,returnVal.length() - 2);//Trim extra ", "
		returnVal = returnVal + "]";
		
		return returnVal;
	}

	public static MP5Function getPredictor(User u, RestaurantDB db, MP5Function featureFunction) {
		// TODO: Implement this method
		double S_xx, S_xy, meanX, meanY;
		
		//TODO: Use proper method to get all the user's reviews
		Set<Review> userReviews = new HashSet<Review>();
		
		//S_vals = [S_xx, S_xy, S_yy, meanX, meanY]
		double[] S_vals = getSValues(userReviews, db, featureFunction);
		
		S_xx = S_vals[0];
		S_xy = S_vals[1];
		meanX = S_vals[3];
		meanY = S_vals[4];
		
		double slope = S_xy / S_xx;
		double intercept = meanY - (meanX * slope);
		
		return new LinRegFunction(slope,intercept,featureFunction);
	}

	public static MP5Function getBestPredictor(User u, RestaurantDB db, List<MP5Function> featureFunctionList) {
		// TODO: Implement this method
		return null;
	}
	
	
	//TODO: Make all below functions private for final release
	
	
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


	/**
	 * Returns a string representing a restaurant's info in JSON format
	 * expressed in terms voronoi can interpret
	 * @param cluster is the cluster number (ID)
	 * @param weight is the restaurant's weight
	 * @param r is the restaurant object
	 * @return The formatted string.
	 */
	public static String restaurantStringJSON(int cluster, double weight, Restaurant r) {
		
		String returnVal = "{";
		returnVal = returnVal + "\"x\": " + r.getLatitude();
		returnVal = returnVal + ", \"y\": " + r.getLongitude();
		returnVal = returnVal + ", \"name\": \"" + r.getName() + "\"";
		returnVal = returnVal + ", \"cluster\": " + cluster;
		returnVal = returnVal + ", \"weight\": " + weight + "}";
		return returnVal;
	}
	
	/**
	 * Returns an array containing the S-values associated with a users review
	 * history and a specified featureFunction.
	 * @param userReviews is the set of the users reviews
	 * @param db is the restaurant Database
	 * @param featureFunction is the chosen feaureFunction
	 * @return an array containing the S values represented internally as:
	 * [S_xx, S_xy, S_yy, meanX, meanY]
	 */
	public static double[] getSValues(Set<Review> userReviews, RestaurantDB db, MP5Function featureFunction){

		double S_xx = 0.0, S_xy = 0.0, S_yy = 0.0, meanX = 0.0, meanY = 0.0, count = 0.0;

		//Sum up all the x and y values (for mean calculation)
		for(Review r : userReviews){
			//This inner loop should only ever iterate over the one restaurant found
			//TODO: Use proper query
			for(Restaurant restaurant: db.query("Restaurant with proper ID")){
				if(featureFunction.f(restaurant, db) != -10000.0){
					count += 1.0;
					meanX += featureFunction.f(restaurant, db);
					meanY += r.getStars();
				}
			}
		}//End summation
		
		//Give the means their final values
		meanX = meanX / count;
		meanY = meanY / count;
		
		//Calculate S_xx, S_xy, S_yy
		for(Review r : userReviews){
			//This inner loop should only ever iterate over the one restaurant found
			//TODO: Use proper query
			for(Restaurant restaurant: db.query("Restaurant with proper ID")){
				if(featureFunction.f(restaurant, db) != -10000.0){
					S_xx += Math.pow((featureFunction.f(restaurant, db) - meanX),2);
					S_xy += (featureFunction.f(restaurant,db) - meanX) *
							(r.getStars() - meanY);
					S_yy += Math.pow((r.getStars() - meanY), 2);
				}
			}
		}//End summation
		
		double[] returnVal = {S_xx, S_xy, S_yy, meanX, meanY};
		
		return returnVal;
	}
}