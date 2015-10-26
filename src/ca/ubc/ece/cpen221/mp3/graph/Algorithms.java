package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
import java.util.*;

public class Algorithms {

	/**
	 * *********************** Algorithms ****************************
	 * 
	 * Please see the README for the machine problem for a more detailed
	 * specification of the behavior of each method that one should implement.
	 */

	/**
	 * This is provided as an example to indicate that this method and other
	 * methods should be implemented here.
	 * 
	 * You should write the specs for this and all other methods.
	 * 
	 * @param graph
	 * @param a
	 * @param b
	 * @return
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
		// TODO: Implement this method and others
		return 0;
	}
	
	
	public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b){
		List<Vertex> result, aList, bList;
		result = new ArrayList<Vertex>();
		aList = graph.getUpstreamNeighbors(a);
		bList = graph.getUpstreamNeighbors(b);
		
		//Add equal vertices to result list
		for(Vertex i : aList){
			for(Vertex j : bList){
				if(i.getLabel().equals(j.getLabel()))
					result.add(i);
			}
		}
		
		return result;
	}
	
	public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex a, Vertex b){
		List<Vertex> result, aList, bList;
		result = new ArrayList<Vertex>();
		aList = graph.getDownstreamNeighbors(a);
		bList = graph.getDownstreamNeighbors(b);
		
		//Add equal vertices to result list
		for(Vertex i : aList){
			for(Vertex j : bList){
				if(i.getLabel().equals(j.getLabel())){
					result.add(i);
					break;
				}
			}
		}
		
		return result;
	}

}
