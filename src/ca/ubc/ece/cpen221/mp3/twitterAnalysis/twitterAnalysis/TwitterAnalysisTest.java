package ca.ubc.ece.cpen221.mp3.twitterAnalysis.twitterAnalysis;

import static org.junit.Assert.*;

import org.junit.Test;
import ca.ubc.ece.cpen221.mp3.twitterAnalysis.twitterAnalysis.TwitterAnalysis;

public class TwitterAnalysisTest {

	@Test
	public void test() {
		TwitterAnalysis x = new TwitterAnalysis();
		try{
			x.createGraphs();
		}catch(Exception E){
			System.out.println("File not found!");
			fail();
		}
		
	}

}
