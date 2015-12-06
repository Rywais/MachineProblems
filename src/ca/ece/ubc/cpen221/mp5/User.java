package ca.ece.ubc.cpen221.mp5;

//Abstract value is the userID

public class User {
	private String url, type, userID, name;
	private int funnyVotes, usefulVotes, coolVotes, reviewCount;
	private double averageStars;
	
	public User(){} //Default constructor
	
	//Verbose constructor (Takes all internal values as args)
	public User(String url, String  type, String userID, String name,
			int funnyVotes, int usefulVotes, int coolVotes, int reviewCount,
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
	
	public int getFunnyVotes(){
		return funnyVotes;
	}
	
	public int getUsefulVotes(){
		return usefulVotes;
	}
	
	public int getCoolVotes(){
		return coolVotes;
	}
	
	public int getReviewCount(){
		return reviewCount;
	}
	
	public double getAverageStars(){
		return averageStars;
	}
}
