package ca.ece.ubc.cpen221.mp5;

//Abstract value is the userID

public class User {
	private String url, type, userID, name;
	private long funnyVotes, usefulVotes, coolVotes, reviewCount;
	private double averageStars; 
	
	public User(){} //Default constructor
	
	//Verbose constructor (Takes all internal values as args)
	public User(String url, String  type, String userID, String name,
			long funnyVotes, long usefulVotes, long coolVotes, long reviewCount,
			double averageStars){
		
		this.url = url;
		this.type = type;
		this.userID = userID;
		this.name = name;
		this.funnyVotes = funnyVotes;
		this.usefulVotes = usefulVotes;
		this.coolVotes = coolVotes;
		this.reviewCount = reviewCount;
		this.averageStars = averageStars;
	}
	
	public void setURL(String url){
		this.url = url;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public void setUserID(String id) {
		this.userID = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFunnyVotes(int funnyVotes) {
		this.funnyVotes = funnyVotes;
	}

	public void setUsefulVotes(int usefulVotes) {
		this.usefulVotes = usefulVotes;
	}

	public void setCoolVotes(int coolVotes) {
		this.coolVotes = coolVotes;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public void setAverageStars(double avgStars) {
		this.averageStars = avgStars;
	}
	
	public String getURL(){
		return url;
	}
	
	public String getType(){
		return type;
	}
	
	public String getUserID(){
		return userID;
	}
	
	public String getName(){
		return name;
	}
	
	public long getFunnyVotes(){
		return funnyVotes;
	}
	
	public long getUsefulVotes(){
		return usefulVotes;
	}
	
	public long getCoolVotes(){
		return coolVotes;
	}
	
	public long getReviewCount(){
		return reviewCount;
	}
	
	public double getAverageStars(){
		return averageStars;
	}
	
	//New methods for equals & hashCode
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof User))
			return false;
		
		User other = (User) o;
		
		if(other.getUserID() == userID)
			return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		int hash = ((int) userID.charAt(0)) << 16;
		hash += (int) userID.charAt(1);
		return hash;
	}
}
