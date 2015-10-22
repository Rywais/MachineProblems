package ca.ubc.ece.cpen221.mp3.graph;
import java.util.List;
import java.util.ArrayList;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListGraph implements Graph {
	
	List<Vertex> vList = new ArrayList<Vertex>();
	List<Edge> eList = new ArrayList<Edge>();
	
	
	@Override
	public void addVertex(Vertex v) {
		vList.add(v);
	}

	@Override
	public void addEdge(Vertex v1, Vertex v2) throws IllegalArgumentException{
		if(vList.contains(v1) && vList.contains(v2)){
			eList.add(new Edge(v1,v2));
		} else{
			throw new IllegalArgumentException();
		}
		
	}

	@Override
	public boolean edgeExists(Vertex v1, Vertex v2) {
		Edge test = new Edge(v1,v2);
		for(Edge i : eList){
			if(i.equals(test))
				return true;
		}
		return false;
	}

	@Override
	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		List<Vertex> downstreamNeighbors = new ArrayList<Vertex>();
		for(Edge i: eList){
			if(i.getStartVertex().equals(v))
				downstreamNeighbors.add(i.getEndVertex());
		}
		return downstreamNeighbors;
	}

	@Override
	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		List<Vertex> upstreamNeighbors = new ArrayList<Vertex>();
		for(Edge i: eList){
			if(i.getEndVertex().equals(v))
				upstreamNeighbors.add(i.getStartVertex());
		}
		return upstreamNeighbors;
	}

	@Override
	public List<Vertex> getVertices() {
		List<Vertex> copy = new ArrayList<Vertex>();
		for(Vertex i : vList)
			copy.add(i);
		
		return copy;
	}
// TODO: Implement this class
}
