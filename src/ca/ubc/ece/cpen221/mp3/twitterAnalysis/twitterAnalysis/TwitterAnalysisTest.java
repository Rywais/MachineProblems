package ca.ubc.ece.cpen221.mp3.twitterAnalysis.twitterAnalysis;

import static org.junit.Assert.*;

import org.junit.Test;
import ca.ubc.ece.cpen221.mp3.twitterAnalysis.twitterAnalysis.TwitterAnalysis;
import ca.ubc.ece.cpen221.mp3.staff.*;
import ca.ubc.ece.cpen221.mp3.graph.*;

public class TwitterAnalysisTest {

	/**
	 * Tests a query for commonInfluencers, should output 2 & 6 
	 * into the specified file (on separate lines)
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
		
		String a = "commonInfluencers 1 4 ?";
		
		String[] s = a.split(" ");
		
		//Change the string here to the desired output file
		TwitterAnalysis.printQueryResult(s, "C:/Workspaces/OUTPUT.txt");
		
	}

}
