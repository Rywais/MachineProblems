package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.*;

public class Edge {
	private Vertex vStart, vEnd; // Relevant vertices

	/**
	 * @param vStart
	 *            is the leading vertex for the edge
	 * @param vEnd
	 *            is the ending vertex for the edge
	 */
	public Edge(Vertex vStart, Vertex vEnd) throws IllegalArgumentException{
		if (vStart != vEnd) {
			this.vStart = vStart;
			this.vEnd = vEnd;
		}else{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns the ending vertex of the edge
	 * 
	 * @return the ending vertex
	 */
	public Vertex getEndVertex() {
		return vEnd;
	}

	/**
	 * Returns the leading vertex of the edge
	 * 
	 * @return the leading vertex
	 */
	public Vertex getStartVertex() {
		return vStart;
	}

}
