package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import ca.ubc.ece.cpen221.mp3.twitterAnalysis.twitterAnalysis.TwitterAnalysis;
import ca.ubc.ece.cpen221.mp3.staff.*;
import ca.ubc.ece.cpen221.mp3.graph.*;

public class TwitterAnalysisTest {

	/**
	 * Tests a query for commonInfluencers, should output 2 & 6 
	 * into the specified file, then that users 1 & 5 have no 
	 * common influencers
	 * 
	 * **NOTE**: The Output file should be specified and checked by the tester
	 * It's location is marked with a comment 
	 * 
	 * Verified to work by Ryan Watt for the sample provided data
	 */
	@Test
	public void commonInfluencersQuery() {
		TwitterAnalysis.alg = new AdjacencyListGraph();
		TwitterAnalysis.amg = new AdjacencyMatrixGraph();
		
		Graph alg = TwitterAnalysis.alg;
		Graph amg = TwitterAnalysis.amg;
		
		Vertex[] v = new Vertex[10];
		
		for(int i = 0; i < 10; i++){
			v[i] = new Vertex(Integer.toString(i+1));
			amg.addVertex(v[i]);
			alg.addVertex(v[i]);
		}
		
		alg.addEdge(v[6], v[0]);
		alg.addEdge(v[1], v[0]);
		alg.addEdge(v[3], v[0]);
		alg.addEdge(v[5], v[0]);
		
		amg.addEdge(v[6], v[0]);
		amg.addEdge(v[1], v[0]);
		amg.addEdge(v[3], v[0]);
		amg.addEdge(v[5], v[0]);
		
		alg.addEdge(v[1],v[3]);
		alg.addEdge(v[5],v[3]);
		alg.addEdge(v[9],v[3]);
		
		amg.addEdge(v[1],v[3]);
		amg.addEdge(v[5],v[3]);
		amg.addEdge(v[9],v[3]);
		
		amg.addEdge(v[0], v[4]);
		alg.addEdge(v[0], v[4]);
		
		String a = "commonInfluencers 1 4 ?";
		String a2 = "commonInfluencers 1 5 ?";
		
		String[] s = a.split(" ");
		String[] s2 = a2.split(" ");
		
		//Change the string here to the desired output file
		TwitterAnalysis.printQueryResult(s, "C:/Workspaces/OUTPUT.txt");
		TwitterAnalysis.printQueryResult(s2, "C:/Workspaces/OUTPUT.txt");
		
	}
	
	/**
	 * Tests a query for numRetweets, should output 2 
	 * into the specified file, then that user 2's tweets cannot reach user 7
	 * 
	 * **NOTE**: The Output file should be specified and checked by the tester
	 * It's location is marked with a comment 
	 * 
	 * Verified to work by Ryan Watt for the sample provided data
	 */
	@Test
	public void numRetweetsQuery() {
		TwitterAnalysis.alg = new AdjacencyListGraph();
		TwitterAnalysis.amg = new AdjacencyMatrixGraph();
		
		Graph alg = TwitterAnalysis.alg;
		Graph amg = TwitterAnalysis.amg;
		
		Vertex[] v = new Vertex[10];
		
		for(int i = 0; i < 10; i++){
			v[i] = new Vertex(Integer.toString(i+1));
			amg.addVertex(v[i]);
			alg.addVertex(v[i]);
		}
		
		alg.addEdge(v[1], v[2]);
		alg.addEdge(v[1], v[3]);
		alg.addEdge(v[2], v[3]);
		alg.addEdge(v[2], v[4]);
		alg.addEdge(v[2], v[5]);
		alg.addEdge(v[3], v[2]);
		alg.addEdge(v[3], v[4]);
		alg.addEdge(v[4], v[5]);
		alg.addEdge(v[6], v[5]);
		
		amg.addEdge(v[1], v[2]);
		amg.addEdge(v[1], v[3]);
		amg.addEdge(v[2], v[3]);
		amg.addEdge(v[2], v[4]);
		amg.addEdge(v[2], v[5]);
		amg.addEdge(v[3], v[2]);
		amg.addEdge(v[3], v[4]);
		amg.addEdge(v[4], v[5]);
		amg.addEdge(v[6], v[5]);
		
		String a = "numRetweets 2 6 ?";
		String a2 = "numRetweets 2 7 ?";
		
		String[] s = a.split(" ");
		String[] s2 = a2.split(" ");
		
		//Change the string here to the desired output file
		TwitterAnalysis.printQueryResult(s, "C:/Workspaces/OUTPUT2.txt");
		TwitterAnalysis.printQueryResult(s2, "C:/Workspaces/OUTPUT2.txt");
		
	}

}
