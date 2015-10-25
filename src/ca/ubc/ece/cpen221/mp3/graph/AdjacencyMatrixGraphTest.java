package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdjacencyMatrixGraphTest {

	@Test
	public void test() {
		AdjacencyMatrixGraph amg = new AdjacencyMatrixGraph();
		Vertex[] v = new Vertex[4];
		
		for(int i = 0; i < 4; i++){
			v[i] = new Vertex(Integer.toString(i+1));
			amg.addVertex(v[i]);
		}
		
		amg.addEdge(v[0], v[1]);
		amg.addEdge(v[1], v[2]);
		amg.addEdge(v[1], v[3]);
		amg.addEdge(v[2], v[0]);
		amg.addEdge(v[3], v[1]);
		
		assertTrue(amg.edgeExists(v[0], v[1]));
		assertTrue(amg.edgeExists(v[1], v[2]));
		assertTrue(amg.edgeExists(v[1], v[3]));
		assertTrue(amg.edgeExists(v[2], v[0]));
		assertTrue(amg.edgeExists(v[3], v[1]));
		
		assertFalse(amg.edgeExists(v[1],v[0]));
	}
	

}
