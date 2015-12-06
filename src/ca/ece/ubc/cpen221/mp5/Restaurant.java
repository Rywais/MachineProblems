package ca.ece.ubc.cpen221.mp5;

import java.util.*;

// TODO: Use this class to represent a restaurant.
// State the rep invariant and abs

public class Restaurant {

	private boolean isOpen;
	private String url, businessID, name, state, type, city, fullAddress, photoURL;
	private Set<String> neighborhoods, categories, schools;
	private double latitude, longitude, stars;
	private int reviewCount, price;
	
	//boolean get/set methods:
	
	public void setOpen(boolean isOpen){
		this.isOpen = isOpen;
	}
	
	public boolean isOpen(){
		return isOpen;
	}
	
	//String get/set methods
	
	public void setURL(String url){
		this.url = url;
	}
	
	public void setBusinessID(String businessID){
		this.businessID = businessID;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public void setFullAddress(String fullAddress){
		this.fullAddress = fullAddress;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setphotoURL(String photoURL){
		this.photoURL = photoURL;
	}
	
	public String getURL(){
		return url;
	}
	
	public String getBusinessID(){
		return businessID;
	}
	
	public String getState(){
		return state;
	}
	
	public String getType(){
		return type;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getFullAddress(){
		return fullAddress;
	}
	
	public String getName(){
		return name;
	}
	
	public String getphotoURL(){
		return photoURL;
	}
	
	//Get/Set/Add/Remove methods for List<String>'s
	
	public void setNeighborhoods(Set<String> neighborhoods){
		this.neighborhoods = new HashSet<String>(neighborhoods);
	}
	
	public void setCategories(Set<String> categories){
		this.categories = new HashSet<String>(categories);
	}
	
	public void setSchools(Set<String> schools){
		this.schools = new HashSet<String>(schools);
	}
	
	public Set<String> getNeighborhoods(){
		return new HashSet<String>(neighborhoods);
	}
	
	public Set<String> getCategories(){
		return new HashSet<String>(categories);
	}
	
	public Set<String> getSchools(){
		return new HashSet<String>(schools);
	}
	
	public void addNeighborhood(String neighborhood){
		neighborhoods.add(neighborhood);
	}
	
	public void addCategory(String category){
		categories.add(category);
	}
	
	public void addSchool(String school){
		schools.add(school);
	}
	
	public boolean removeNeighborhood(String neighborhood){
		return neighborhoods.remove(neighborhood);
	}
	
	public boolean removeCategory(String category){
		return categories.remove(category);
	}
	
	public boolean removeSchool(String school){
		return schools.remove(school);
	}
	
	//get/set methods for doubles
	
	public void setLatitude(double latitude){
		this.latitude = latitude;
	}
	
	public void setLongitude(double longitude){
		this.longitude = longitude;
	}
	
	public void setStars(double stars){
		this.stars = stars;
	}
	
	public double getLatitude(){
		return latitude;
	}
	
	public double getLongitude(){
		return longitude;
	}
	
	public double getStars(){
		return stars;
	}
	
	//get/set methods for int values
	
	public void setReviewCount(int reviewCount){
		this.reviewCount = reviewCount;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public int getReviewCount(){
		return reviewCount;
	}
	
	public int getPrice(){
		return price;
	}
	
}
