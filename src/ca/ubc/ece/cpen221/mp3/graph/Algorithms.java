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
	 * Finds the shortest distance between two vertices of a graph
	 * 
	 * @param graph is the graph to be traversed, cannot be null
	 * @param a is the starting vertex
	 * @param b is the vertex to be found through the shortest path
	 * @return -1 if there is no valid path, the shortest path length otherwise.
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
		int depth = 1;
		if(graph.getDownstreamNeighbors(a).contains(b))
			return depth;
		else{
			return continueTraversal(graph,graph.getDownstreamNeighbors(a),
					b,graph.getDownstreamNeighbors(a),depth, -1);
		}
		
	}
	
	/**
	 * A helper method for the shortest distance algorithm
	 * @param g is the graph to continue being traversed
	 * @param A is the list of vertices that may need to be traversed
	 * @param b is the vertex a path to is being searched for
	 * @param alreadyVisited is a list of previously visited vertices
	 * @param depth is the current depth of the search
	 * @param currentBest is the current shortest path length or -1
	 * if no path has yet been found
	 * @return the current "currentBest" value
	 */
	private static int continueTraversal(Graph g, List<Vertex> A, Vertex b,
			List<Vertex> alreadyVisited, int depth, int currentBest){
		boolean killFlag = true;
		
		depth++; //increase depth counter
		
		for(Vertex a : A){
			//if searched for vertex is found, return depth
			if(g.getDownstreamNeighbors(a).contains(b))
				return depth;
			
			//Add new already Viewed vertices to graph
			for(Vertex c : g.getDownstreamNeighbors(a)){
				if(!alreadyVisited.contains(c)){
					alreadyVisited.add(c);
					killFlag = false; //Don't stop if there's a new value
				}
			}
			
			if(killFlag)
				return currentBest;
			
			//Try to find a new best distance recursively
			try{
				int ans = continueTraversal(g,g.getDownstreamNeighbors(a),b,
						alreadyVisited,depth, currentBest);
				
				if(ans < currentBest || currentBest == -1)
					currentBest = ans;
				
			} catch(Exception E){
				//do nothing and carry on if the current best hasn't been updated
			}
			
		}
		
		return currentBest;
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

	public static boolean breadthFirstSearch(Graph g, Vertex a, Vertex b){
		if(g.getDownstreamNeighbors(a).contains(b))
			return true;
		return continueBFS(g,g.getDownstreamNeighbors(a),g.getDownstreamNeighbors(a),b);
	}
	
	private static boolean continueBFS(Graph g, List<Vertex> A, List<Vertex> alreadySearched, Vertex b){
		for(Vertex i : A){
			//skip iteration if it's already been searched
			if(alreadySearched.contains(i))
				continue;
			if(g.getDownstreamNeighbors(i).contains(b))
				return true;
			alreadySearched.add(i);
		}
		
		//List of vertices to be searched at the next depth level
		List<Vertex> pass = new ArrayList<Vertex>();
		
		//Add all un-searched vertices at next depth
		for(Vertex i : A){
			for(Vertex j : g.getDownstreamNeighbors(i)){
				if(!alreadySearched.contains(j))
					pass.add(j);
			}
		}
		
		if(pass.size() == 0)
			return false;
		
		return continueBFS(g,pass,alreadySearched,b);
		
	}
}
