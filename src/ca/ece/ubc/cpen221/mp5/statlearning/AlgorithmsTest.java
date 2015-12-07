package ca.ece.ubc.cpen221.mp5.statlearning;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.*;

public class AlgorithmsTest {

	@Test
	public void restaurantStringJSONTest() {
		Restaurant r = new Restaurant();
		r.setName("McDonald's");
		r.setLatitude(69.69);
		r.setLongitude(96.96);
		String testVal = Algorithms.restaurantStringJSON(1,2.0,r);
		assertEquals(testVal, "{\"x\": 69.69, \"y\": 96.96, \"name\": \"McDonald's\", \"cluster\": 1, \"weight\": 2.0}");
	}
	
	@Test
	public void convertClustersToJSONTest(){
		Set<Restaurant> s1, s2;
		s1 = new HashSet<Restaurant>();
		s2 = new HashSet<Restaurant>();
		
		Restaurant r1 = new Restaurant();
		r1.setName("McDonald's");
		r1.setLatitude(69.69);
		r1.setLongitude(96.96);
		
		Restaurant r2 = new Restaurant();
		r2.setName("A&W");
		r2.setLatitude(70.0);
		r2.setLongitude(97.0);
		
		Restaurant r3 = new Restaurant();
		r3.setName("Burger King");
		r3.setLatitude(69.0);
		r3.setLongitude(96.0);
		
		s1.add(r1);
		s2.add(r2);
		s2.add(r3);
		
		List<Set<Restaurant>> testList = new ArrayList<Set<Restaurant>>();
		testList.add(s1);
		testList.add(s2);
		
		//Test against proper output.
		assertEquals(Algorithms.convertClustersToJSON(testList), "[{\"x\": 69.69, \"y\": 96.96, \"name\": \"McDonald's\", \"cluster\": 1, \"weight\": 1.0}, {\"x\": 69.0, \"y\": 96.0, \"name\": \"Burger King\", \"cluster\": 2, \"weight\": 1.0}, {\"x\": 70.0, \"y\": 97.0, \"name\": \"A&W\", \"cluster\": 2, \"weight\": 1.0}]");
	}

}
