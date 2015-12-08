package ca.ece.ubc.cpen221.mp5;


public class Review {

	private String type, businessID, reviewID, text, userID, date;
	private long coolVotes, usefulVotes, funnyVotes, stars;
	
	public Review(){} //Default constructor
	
	//Verbose constructor (INcludes all internal values as args)
	public Review(String type, String businessID, String reviewID, String text,
			String userID, String date, long coolVotes, long usefulVotes,
			long funnyVotes, long stars){
		
		this.type = type;
		this.businessID = businessID;
		this.reviewID = reviewID;
		this.text = text;
		this.userID = userID;
		this.date = date;
		this.coolVotes = coolVotes;
		this.usefulVotes = usefulVotes;
		this.funnyVotes = funnyVotes;
		this.stars = stars;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	void setBusinessID(String businessID) {
		this.businessID = businessID;
	}

	void setReviewID(String reviewID) {
		this.reviewID = reviewID;
	}

	void setText(String text) {
		this.text = text;
	}

	void setUserID(String userID) {
		this.userID = userID;
	}
	
	void setDate(String date) {
		this.date = date;
	}

	void setCoolVotes(int coolVotes) {
		this.coolVotes = coolVotes;
	}

	void setUsefulVotes(int usefulVotes) {
		this.usefulVotes = usefulVotes;
	}

	void setFunnyVotes(int funnyVotes) {
		this.funnyVotes = funnyVotes;
	}

	void setStars(int stars) {
		this.stars = stars;
	}
	
	String getType() {
		return type;
	}

	String getBusinessID() {
		return businessID;
	}

	String getReviewID() {
		return reviewID;
	}

	String getText() {
		return text;
	}

	public String getUserID() {
		return userID;
	}
	
	String getDate() {
		return date;
	}

	long getCoolVotes() {
		return coolVotes;
	}

	long getUsefulVotes() {
		return usefulVotes;
	}

	long getFunnyVotes() {
		return funnyVotes;
	}

	public long getStars() {
		return stars;
	}
	
	//New equals and hashCode methods
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Review))
			return false;
		
		Review other = (Review) o;
		
		if(other.getReviewID() == reviewID)
			return true;
		
		return false;
	}
	
	@Override
	public int hashCode(){
		int hash = ((int) reviewID.charAt(0)) << 16;
		hash += (int) reviewID.charAt(1);
		return hash;
	}
	
}
