package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
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
		BufferedReader in = new BufferedReader(new FileReader(restaurants));
		String inputLine;
		//int i = 0;
		JSONParser parser = new JSONParser();
		while((inputLine = in.readLine()) != null) {
			Restaurant restaurant = new Restaurant();
			//System.out.println(inputLine);
			JSONObject line = (JSONObject)(parser.parse(inputLine));
			//System.out.println(line);
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
		//System.out.println(setRest.size());
		//parse("in(\"Telegraph Ave\") && (category(\"Chinese\") || category(\"Italian\")) && price(1..2)");
	}

	public Set<Restaurant> query(String queryString) {
		// TODO: Implement this method
		// Write specs, etc.

		/*parse(queryString);
		
		CharStream stream = new ANTLRInputStream(queryString);
		queryLexer lexer = new queryLexer(stream);
		TokenStream tokens = new CommonTokenStream(lexer);
		
		queryParser qParser = new queryParser(tokens);
		
		ParseTree tree = qParser.root();
		ParseTreeWalker walker = new ParseTreeWalker();*/
		
		
		return null;
	}
	
	public static void parse(String queryString) {
		CharStream stream = new ANTLRInputStream(queryString);
		queryLexer lexer = new queryLexer(stream);
		TokenStream tokens = new CommonTokenStream(lexer);
		
		queryParser parser = new queryParser(tokens);
		
		ParseTree tree = parser.root();
		System.err.println(tree.toStringTree(parser));
		ParseTreeWalker walker = new ParseTreeWalker();
		//queryListener_queryCreator listener = new queryListener_queryCreator();
		queryListener listener = new queryListenerPrintEverything();
        walker.walk(listener, tree);
        
		//return listener.getQuery();
	}
	
	private static class queryListenerPrintEverything extends queryBaseListener {
	    public void enterRoot(queryParser.RootContext ctx) {
	        System.err.println("entering root");
	    }
	    public void exitRoot(queryParser.RootContext ctx) {
	        System.err.println("exiting root");
	    }
	    public void enterOrExpr(queryParser.OrExprContext ctx) {
	        System.err.println("entering orExpr");
	    }
	    public void exitOrExpr(queryParser.OrExprContext ctx) {
	        System.err.println("exiting orExpr");
	    }
	    public void enterAndExpr(queryParser.AndExprContext ctx) {
	        System.err.println("entering andExpr");
	    }
	    public void exitAndExpr(queryParser.AndExprContext ctx) {
	        System.err.println("exiting andExpr");
	    }
	    public void enterAtom(queryParser.AtomContext ctx) {
	        System.err.println("entering atom");
	    }
	    public void exitAtom(queryParser.AtomContext ctx) {
	        System.err.println("exiting atom");
	    }
	    public void enterString(queryParser.StringContext ctx) {
	        System.err.println("entering string");
	    }
	    public void exitString(queryParser.StringContext ctx) {
	        System.err.println("exiting string");
	    }
	    public void enterIn(queryParser.InContext ctx) {
	        System.err.println("entering in");
	    }
	    public void exitIn(queryParser.InContext ctx) {
	        System.err.println("exiting in");
	    }
	    public void enterCategory(queryParser.CategoryContext ctx) {
	        System.err.println("entering category");
	    }
	    public void exitCategory(queryParser.CategoryContext ctx) {
	        System.err.println("exiting category");
	    }
	    public void enterName(queryParser.NameContext ctx) {
	        System.err.println("entering name");
	    }
	    public void exitName(queryParser.NameContext ctx) {
	        System.err.println("exiting name");
	    }
	    public void enterRating(queryParser.RatingContext ctx) {
	        System.err.println("entering rating");
	    }
	    public void exitRating(queryParser.RatingContext ctx) {
	        System.err.println("exiting rating");
	    }
	    public void enterPrice(queryParser.PriceContext ctx) {
	        System.err.println("entering price");
	    }
	    public void exitPrice(queryParser.PriceContext ctx) {
	        System.err.println("exiting price");
	    }
	    public void enterRange(queryParser.RangeContext ctx) {
	        System.err.println("entering range");
	    }
	    public void exitRange(queryParser.RangeContext ctx) {
	        System.err.println("exiting range");
	    }
	    
	}
	
	
	/*private static class queryListener_queryCreator extends queryBaseListener {
	    private Stack<query> stack = new Stack<query>();
	    
	    @Override
	    public void exitLiteral(queryParser.LiteralContext ctx) {
	        query literal = new BooleanLiteral(ctx.start.getType() == queryLexer.TRUE);
	        stack.push(literal);
	    }
	    
	    @Override
	    public void exitConjunction(FormulaParser.ConjunctionContext ctx) {
	        if (ctx.AND() != null) {
	            // we matched the AND rule
	            Formula right = stack.pop();
	            Formula left = stack.pop();
	            Formula and = new And(left, right);
	            stack.push(and);
	        } else {
	            // do nothing, because we just matched a literal and its BooleanLiteral is already on the stack
	        }
	    }
	    
	    @Override
	    public void exitQuery(queryParser.queryContext ctx) {
	        // do nothing, because the top of the stack should have the node already in it
	        assert stack.size() == 1;
	    }
	    
	    public query getQuery() {
	        return stack.get(0);
	    }
	}

	
	public void richQueries(String queryString) {
		
	}*/
}
