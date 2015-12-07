package ca.ece.ubc.cpen221.mp5;

import java.util.*;

public class Centroid {

	private double latitude, longitude;
	private Set<Restaurant> restaurants;
	
	public Centroid(double latitude, double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
		restaurants = new HashSet<Restaurant>();
	}
	
	public void setLatitude(double latitude){
		this.latitude = latitude;
	}
	
	
	public void setlongitude(double longitude){
		this.longitude = longitude;
	}
	
	public double getLatitude(){
		return latitude;
	}
	
	public double getLongitude(){
		return longitude;
	}
	
	public void addRestaurant(Restaurant r){
		restaurants.add(r);
	}
	
	public Set<Restaurant> getRestaurants(){
		return new HashSet<Restaurant>(restaurants);
	}
	
	public void clearRestaurants(){
		restaurants.clear();
	}
	
	/**
	 * Causes the object to shift location to the new centroid
	 * of the restaurants contained in its internal set. 
	 * Returns true if it shifted.
	 * @return True if the centroid shifts, false otherwise.
	 */
	public boolean shift(){
		double latSum = 0, longSum = 0;
		double newLat, newLong, restaurantCountDouble;
		int restaurantCount = restaurants.size();
		boolean returnVal = false;
		
		//Doesn't move if it's not linked to any restaurants
		if(restaurantCount == 0)
			return returnVal;
		
		restaurantCountDouble = (double) restaurantCount;
		
		for(Restaurant r : restaurants){
			latSum += r.getLatitude();
			longSum += r.getLongitude();
		}
		
		newLat = latSum / restaurantCountDouble;
		newLong = longSum / restaurantCountDouble;
		
		if(newLat != latitude){
			returnVal = true;
			latitude = newLat;
		}
		
		if(newLong != longitude){
			returnVal = true;
			longitude = newLong;
		}
		
		return returnVal;
	}
	
	//New methods for equals and hashCode
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Centroid))
			return false;
		
		Centroid other = (Centroid) o;
		
		if(other.getLatitude() == this.latitude && other.getLongitude() == this.longitude)
			return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		Double hash = latitude * longitude;
		return hash.hashCode();
	}
	
}
