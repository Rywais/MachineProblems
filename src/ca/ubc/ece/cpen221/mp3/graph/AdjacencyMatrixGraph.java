package ca.ubc.ece.cpen221.mp3.graph;
import java.util.*;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {
	
	private SparseMatrix am = new SparseMatrix();
	private ArrayList<Integer> vertices = new ArrayList<Integer>(); 
	
	/**
	 * Adds a vertex to the list of vertices
	 * @param v is the vertex to be added which must have an
	 * integer based string for a label
	 */
    @Override
    public void addVertex(Vertex v) {
        if(!vertices.contains(Integer.parseInt(v.getLabel())))
        	vertices.add(Integer.parseInt(v.getLabel()));
    }

    /**
     * Adds a directed edge to the graph
     * @param v1 is a vertex that must already be in the graph
     * that will be the upstream vertex
     * @param v2 is a vertex that must already be in the graph
     * that will be the downstream vertex
     */
    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        int upstream = Integer.parseInt(v1.getLabel());
        int downstream = Integer.parseInt(v2.getLabel());
    	
    	if(vertices.contains(upstream) &&
        		vertices.contains(downstream))
        	am.add(upstream, downstream, 1);
        
    }

    /**
     * Returns true if a specified edge exists
     * @param v1 is the upstream vertex
     * @param v2 is the downstream vertex
     */
    @Override
    public boolean edgeExists(Vertex v1, Vertex v2) {
    	int upstream = Integer.parseInt(v1.getLabel());
        int downstream = Integer.parseInt(v2.getLabel());
    	if(am.get(upstream, downstream) != 0)
    		return true;
    	return false;
    }

    
    @Override
    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        List<Vertex> result = new ArrayList<Vertex>();
    	for(Integer i: am.getRow(Integer.parseInt(v.getLabel())).keySet()){
        	if(am.getRow(Integer.parseInt(v.getLabel())).get(i).intValue() != 0)
        		result.add(new Vertex(i.toString()));
        }
    	return result;
    }

    @Override
    public List<Vertex> getUpstreamNeighbors(Vertex v) {
    	List<Vertex> result = new ArrayList<Vertex>();
    	for(Integer i: am.getColumn(Integer.parseInt(v.getLabel())).keySet()){
        	if(am.getColumn(Integer.parseInt(v.getLabel())).get(i).intValue() != 0)
        		result.add(new Vertex(i.toString()));
        }
    	return result;
    }

    @Override
    public List<Vertex> getVertices() {
        List<Vertex> result = new ArrayList<Vertex>();
        for(Integer i : vertices){
        	result.add(new Vertex(Integer.toString(i.intValue())));
        }
        
        return result;
    }
	
	
	
}
