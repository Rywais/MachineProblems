package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.tree.pattern.ParseTreeMatch;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.json.simple.*;
import org.json.simple.parser.*;

// TODO: This class represents the Restaurant Database.
// Define the internal representation and 
// state the rep invariant and the abstraction function.

public class RestaurantDB {
	
	private static String restaurantJSONfilename;
	private static String reviewsJSONfilename;
	private static String usersJSONfilename;
	private static File restaurants = new File("data/restaurants.json");
	private static File reviews = new File("data/reviews.json");
	private static File users = new File("data/users.json");
	private static Set<Restaurant> setRest = new HashSet<Restaurant>();
	private static Set<Review> setReview = new HashSet<Review>();
	private static Set<User> setUser = new HashSet<User>();
	//private static Map<String, List<String>> queryMap = new HashMap<String, List<String>>();
	private static Map<String, String> queryMap = new HashMap<String, String>();
	private static List<String> list = new ArrayList<String>();
	private static List<String> copy = new ArrayList<String>();
	private static Stack<String> stack = new Stack<String>();
	
	
	/**
	 * Create a database from the Yelp dataset given the names of three files:
	 * <ul>
	 * <li>One that contains data about the restaurants;</li>
	 * <li>One that contains reviews of the restaurants;</li>
	 * <li>One that contains information about the users that submitted reviews.
	 * </li>
	 * </ul>
	 * The files contain data in JSON format.
	 * 
	 * @param restaurantJSONfilename
	 *            the filename for the restaurant data
	 * @param reviewsJSONfilename
	 *            the filename for the reviews
	 * @param usersJSONfilename
	 *            the filename for the users
	 */
	public RestaurantDB(String restaurantJSONfilename, String reviewsJSONfilename, String usersJSONfilename) {
		// TODO: Implement this method
		restaurants = new File(restaurantJSONfilename);
		reviews = new File(reviewsJSONfilename);
		users = new File(usersJSONfilename);
	}
	
	public static void main(String[] args) 
			throws ParseException, IOException {
		BufferedReader inRest = new BufferedReader(new FileReader(restaurants));
		BufferedReader inReview = new BufferedReader(new FileReader(reviews));
		BufferedReader inUser = new BufferedReader(new FileReader(users));
		String inputLine;
		JSONParser parser = new JSONParser();
		while((inputLine = inRest.readLine()) != null) {
			JSONObject lineRest = (JSONObject)(parser.parse(inputLine));
			createRestaurantDB(lineRest);
		}
		while((inputLine = inReview.readLine()) != null) {
			JSONObject lineReview = (JSONObject)(parser.parse(inputLine));
			createReviewDB(lineReview);
		}
		while((inputLine = inUser.readLine()) != null) {
			JSONObject lineUser = (JSONObject)(parser.parse(inputLine));
			createUserDB(lineUser);
		}

		//Set<Restaurant> result = query("in(\"Telegraph Ave\") && (category(\"Chinese\") || category(\"Italian\")) && price(1..2)");
		//toJSON(result);

	}

	public static Set<Restaurant> query(String queryString) {
		parse(queryString);
		//Set<Restaurant> database = this.getRestaurantSet();
		Set<Restaurant> database = setRest;
		Set<Restaurant> result = new HashSet<Restaurant>();
		Set<String> keySet = queryMap.keySet();
		Iterator itr = database.iterator();
		while(itr.hasNext()) {
			Restaurant rest = (Restaurant) itr.next();
			Iterator keyItr = keySet.iterator();
			while(keyItr.hasNext()) {
				String key = (String) keyItr.next();
				if ((rest.getNeighborhoods().contains(queryMap.get(key))) ||
					(rest.getCategories().contains(queryMap.get(key))) ||
					(rest.getName().matches(queryMap.get(key)))) {
					result.add(rest);
				}
				else if ((key.contains("price from")) && (rest.getPrice() <= Long.parseLong(queryMap.get(key))) ||
						(key.contains("price to")) && (rest.getPrice() >= Long.parseLong(queryMap.get(key)) )) {
					result.add(rest);
				}
				else if ((key.contains("rating from")) && (rest.getStars() <= Double.parseDouble(queryMap.get(key))) ||
						(key.contains("rating to")) && (rest.getStars() >= Double.parseDouble(queryMap.get(key)) )) {
					result.add(rest);
				}
			}
		}
		
		System.out.println(result);
		return result;
	}
	
	public static JSONArray toJSON(Set<Restaurant> result) {
		JSONArray restaurantsJSONArray = new JSONArray();
		JSONObject restaurantsJSONObject = new JSONObject();
		for (Restaurant rest : result) {
			 restaurantsJSONObject.put("open", rest.isOpen());
			 restaurantsJSONObject.put("url", rest.getURL());
			 restaurantsJSONObject.put("longitude", rest.getLongitude());
			 restaurantsJSONObject.put("neighborhoods", rest.getNeighborhoods());
			 restaurantsJSONObject.put("business_id", rest.getBusinessID());
			 restaurantsJSONObject.put("name", rest.getName());
			 restaurantsJSONObject.put("categories", rest.getCategories());
			 restaurantsJSONObject.put("state", rest.getState());
			 restaurantsJSONObject.put("type", rest.getType());
			 restaurantsJSONObject.put("stars", rest.getStars());
			 restaurantsJSONObject.put("city", rest.getCity());
			 restaurantsJSONObject.put("full_address", rest.getFullAddress());
			 restaurantsJSONObject.put("review_count", rest.getReviewCount());
			 restaurantsJSONObject.put("photo_url", rest.getphotoURL());
			 restaurantsJSONObject.put("schools", rest.getSchools());
			 restaurantsJSONObject.put("latitude", rest.getLatitude());
			 restaurantsJSONObject.put("price", rest.getPrice());
			 restaurantsJSONArray.add(restaurantsJSONObject.toJSONString());	 
		 }
		 
		 return restaurantsJSONArray;
	}
	
	
	
	public static void createRestaurantDB(JSONObject line) {
		Restaurant restaurant = new Restaurant();
		JSONArray categoryJSON = (JSONArray) line.get("categories");
		JSONArray neighborhoodJSON = (JSONArray) line.get("neighborhoods");
		JSONArray schoolJSON = (JSONArray) line.get("schools");
		Set<String> categories = new HashSet<String>();
		Set<String> neighborhoods = new HashSet<String>();
		Set<String> schools = new HashSet<String>();
		if(categoryJSON != null) {
			for(int i = 0; i < categoryJSON.size(); i++) {
				categories.add(categoryJSON.get(i).toString());
			}
		}	
		if(neighborhoodJSON != null) {
			for(int i = 0; i < neighborhoodJSON.size(); i++) {
				neighborhoods.add(neighborhoodJSON.get(i).toString());
			}
		}
		if(schoolJSON != null) {
			for(int i = 0; i < schoolJSON.size(); i++) {
				schools.add(schoolJSON.get(i).toString());
			}
		}
		restaurant.setBusinessID((String)line.get("business_id"));
		restaurant.setCategories(categories);
		restaurant.setCity((String)line.get("city"));
		restaurant.setFullAddress((String)line.get("full_address"));
		restaurant.setLatitude((double)line.get("latitude"));
		restaurant.setLongitude((double)line.get("longitude"));
		restaurant.setName((String)line.get("name"));
		restaurant.setNeighborhoods(neighborhoods);
		restaurant.setOpen((boolean)line.get("open"));
		restaurant.setphotoURL((String)line.get("photo_url"));
		restaurant.setPrice((long)line.get("price"));
		restaurant.setReviewCount((long)line.get("review_count"));
		restaurant.setSchools(schools);
		restaurant.setStars((double)line.get("stars"));
		restaurant.setState((String)line.get("state"));
		restaurant.setType((String)line.get("type"));
		restaurant.setURL((String)line.get("url"));
		setRest.add(restaurant);
	}
	
	public static void createReviewDB(JSONObject line) {
		//System.out.println(line);
		Map<String, Long> votes = (Map<String, Long>) line.get("votes");
		Review review = new Review((String) line.get("type"), (String) line.get("business_id"), (String) line.get("review_id"), 
									(String) line.get("text"), (String) line.get("user_id"), (String) line.get("date"), 
									votes.get("cool"), votes.get("useful"), votes.get("funny"), (long) line.get("stars"));
		setReview.add(review);
	}
	
	public static void createUserDB(JSONObject line) {
		//System.out.println(line);
		Map<String, Long> votes = (Map<String, Long>) line.get("votes");
		User user = new User((String) line.get("url"), (String) line.get("type"), (String) line.get("user_id"),
							(String) line.get("name"), votes.get("funny"), votes.get("useful"), votes.get("cool"), 
							(long) line.get("review_count"), (double) line.get("average_stars"));
		setUser.add(user);
	}
	
	public static Map<String, String> parse(String queryString) {
		CharStream stream = new ANTLRInputStream(queryString);
		QueryLexer lexer = new QueryLexer(stream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		//tokens.setTokenSource(lexer);
		QueryParser parser = new QueryParser(tokens);
		parser.reportErrorsAsExceptions();
		ParseTree tree = parser.root();
		//System.err.println(tree.toStringTree(parser));
		ParseTreeWalker walker = new ParseTreeWalker();
		QueryListener_QueryCreator listener = new QueryListener_QueryCreator();
		QueryListener listen = new QueryListenerPrintEverything();
        walker.walk(listener, tree);
        //walker.walk(listen, tree);
        
		return queryMap;
	}
	
	private static class QueryListenerPrintEverything extends QueryBaseListener {
	    public void enterRoot(QueryParser.RootContext ctx) {
	        System.err.println("entering root");
	    }
	    public void exitRoot(QueryParser.RootContext ctx) {
	        System.err.println("exiting root");
	    }
	    public void enterOrExpr(QueryParser.OrExprContext ctx) {
	        System.err.println("entering orExpr");
	    }
	    public void exitOrExpr(QueryParser.OrExprContext ctx) {
	        System.err.println("exiting orExpr");
	    }
	    public void enterAndExpr(QueryParser.AndExprContext ctx) {
	        System.err.println("entering andExpr");
	    }
	    public void exitAndExpr(QueryParser.AndExprContext ctx) {
	        System.err.println("exiting andExpr");
	    }
	    public void enterAtom(QueryParser.AtomContext ctx) {
	        System.err.println("entering atom");
	    }
	    public void exitAtom(QueryParser.AtomContext ctx) {
	        System.err.println("exiting atom");
	    }
	    public void enterString(QueryParser.StringContext ctx) {
	    	System.err.println("entering string");
	    }
	    public void exitString(QueryParser.StringContext ctx) {
	        System.err.println("exiting string");
	    }
	    public void enterIn(QueryParser.InContext ctx) {
	        System.err.println("entering in");
	    }
	    public void exitIn(QueryParser.InContext ctx) {
	        System.err.println("exiting in");
	    }
	    public void enterCategory(QueryParser.CategoryContext ctx) {
	        System.err.println("entering category");
	    }
	    public void exitCategory(QueryParser.CategoryContext ctx) {
	        System.err.println("exiting category");
	    }
	    public void enterName(QueryParser.NameContext ctx) {
	        System.err.println("entering name");
	    }
	    public void exitName(QueryParser.NameContext ctx) {
	        System.err.println("exiting name");
	    }
	    public void enterRating(QueryParser.RatingContext ctx) {
	        System.err.println("entering rating");
	    }
	    public void exitRating(QueryParser.RatingContext ctx) {
	        System.err.println("exiting rating");
	    }
	    public void enterPrice(QueryParser.PriceContext ctx) {
	        System.err.println("entering price");
	    }
	    public void exitPrice(QueryParser.PriceContext ctx) {
	        System.err.println("exiting price");
	    }

	}
	
	private static class QueryListener_QueryCreator extends QueryBaseListener {
	   
		@Override
	    public void exitRange(QueryParser.RangeContext ctx) {
	        if (ctx.RANGE() != null) {
	        	String string = ctx.getText();
	        	stack.push(string);

	            //System.out.println(string);
	        } else {
	            // do nothing
	        }
	    }
		
		@Override
	    public void exitDotprice(QueryParser.DotpriceContext ctx) {
	        if (ctx.DOTS() != null) {
	        	if(!queryMap.containsKey("price from")) {
        			queryMap.put("price from", stack.pop());
        		}
	        } 
	    }
		
		@Override
	    public void exitDotrating(QueryParser.DotratingContext ctx) {
	        if (ctx.DOTS() != null) {
	        	if(!queryMap.containsKey("rating from")) {
        			queryMap.put("rating from", stack.pop());
        		}
	        } 
	    }
		
		@Override
	    public void exitString(QueryParser.StringContext ctx) {
	        if (ctx.TEXT() != null) {
	        	String string = ctx.getText();
	        	stack.push(string);

	            //System.out.println(string);
	        } else {
	            // do nothing
	        }
	    }
	    
	    @Override
	    public void exitIn(QueryParser.InContext ctx) {
	    	int i = 1;
	    	if (ctx.IN() != null) {
	        	if(!stack.isEmpty()) {
	        		if(!queryMap.containsKey("neighborhood")) {
	        			queryMap.put("neighborhood", stack.pop());  
	        		}
	        		else {
	        			queryMap.put("neighborhood" + i, stack.pop());
	        			i++;
	        		}
	        	}
	        	
	        	//System.out.println(queryMap);
	        } else {
	            // do nothing
	        }
	     }
	    
	    @Override
	    public void exitCategory(QueryParser.CategoryContext ctx) {
	    	int i = 1;
	    	if (ctx.CATEGORY() != null) {
	        	if(!stack.isEmpty()) {
	        		if(!queryMap.containsKey("category")) {
	        			queryMap.put("category", stack.pop());
	        		}
	        		else {
	        			queryMap.put("category" + i, stack.pop());
	        			i++;
	        		}
	        	}
	        	//System.out.println(queryMap);
	        } else {
	            // do nothing
	        }
	    }
	    
	    @Override
	    public void exitName(QueryParser.NameContext ctx) {
	    	int i = 1;
	    	if (ctx.NAME() != null) {
	        	if(!stack.isEmpty()) {
	        		if(!queryMap.containsKey("name")) {
	        			queryMap.put("name", stack.pop());
	        		}
	        		else {
	        			queryMap.put("name" + i, stack.pop());
	        			i++;
	        		}
	        	}
	        	//System.out.println(queryMap);
	        } else {
	            // do nothing
	        }
	    }
	    
	    @Override
	    public void exitPrice(QueryParser.PriceContext ctx) {
	    	if (ctx.PRICE() != null) {
	        	if(!stack.isEmpty()) {
	        		if(!queryMap.containsKey("price to")) {
	        			queryMap.put("price to", stack.pop());
	        		}
	           	}
	        	//System.out.println(queryMap);
	        } else {
	            // do nothing
	        }
	    }
	    
	    @Override
	    public void exitRating(QueryParser.RatingContext ctx) {
	    	if (ctx.RATING() != null) {
	        	if(!stack.isEmpty()) {
	        		if(!queryMap.containsKey("rating to")) {
	        			queryMap.put("rating to", stack.pop());
	        		}
	        	}
	        	//System.out.println(queryMap);
	        } else {
	            // do nothing
	        }
	    }
	    
	    @Override
	    public void exitRoot(QueryParser.RootContext ctx) {
	        // do nothing, because the top of the stack should have the node already in it
	        assert stack.size() == 1;
	    }
	    
	  
	}

	
	public void richQueries(String queryString) {
		
	}
	
	public Set<Restaurant> getRestaurantSet(){
		return new HashSet<Restaurant>(setRest);
	}
	
	public Set<User> getUserSet(){
		return new HashSet<User>(setUser);
	}
	
	public Set<Review> getReviewSet(){
		return new HashSet<Review>(setReview);
	}
	
}
