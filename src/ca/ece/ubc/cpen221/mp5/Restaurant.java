package ca.ece.ubc.cpen221.mp5;

import java.util.*;


//Rep invariant: price is between 0 & 5.0, and once a value is assigned is
//Should never again be null
//Abstract value: This is the businessID

public class Restaurant {

	private boolean isOpen;
	private String url, businessID, name, state, type, city, fullAddress, photoURL;
	private Set<String> neighborhoods, categories, schools;
	private double latitude, longitude, stars;
	private long reviewCount, price;
	
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
	
	public void setReviewCount(long l){
		this.reviewCount = l;
	}
	
	public void setPrice(long l){
		this.price = l;
	}
	
	public long getReviewCount(){
		return reviewCount;
	}
	
	public long getPrice(){
		return price;
	}
	
	//New equals and hashCode methods
	
	@Override
	public boolean equals(Object o){
		
		if(!(o instanceof Restaurant))
			return false;
		
		Restaurant other = (Restaurant) o;
		
		if(other.getBusinessID().equals(this.getBusinessID()))
			return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		try{
		int returnVal = ((int) businessID.charAt(0)) << 16;
		returnVal += businessID.charAt(1);
		return returnVal;
		} catch(Exception e){
			return 0;
		}
	}
	
}
