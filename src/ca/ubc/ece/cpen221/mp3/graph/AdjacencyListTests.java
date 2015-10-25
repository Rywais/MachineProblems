package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdjacencyListTests {

	/**
	 * Tests that ten added edges are added properly by
	 * checking that they exist.
	 */
	@Test
	public void addingEdgesTest() {
		AdjacencyListGraph g = new AdjacencyListGraph();
		for(int i = 0; i < 10; i++)
			g.addVertex(new Vertex(Integer.toString(i)));
		
		for(int i = 0; i < 10; i++)
			g.addEdge(new Vertex(Integer.toString(i)),
					new Vertex(Integer.toString((i+1)%10)));
		
		for(int i = 0; i < 10; i++){
			assertTrue(g.edgeExists(new Vertex(Integer.toString( i )),
					new Vertex(Integer.toString( (i+1)%10) )));
		}
	}
	
	/**
	 * Tests that a single added edge exists
	 */
	@Test
	public void edgeExistsTest(){
		AdjacencyListGraph g = new AdjacencyListGraph();
		g.addVertex(new Vertex("a"));
		g.addVertex(new Vertex("b"));
		g.addEdge(new Vertex("a"), new Vertex("b"));
		
		assertTrue(g.edgeExists(new Vertex("a"), new Vertex("b")));
		assertFalse(g.edgeExists(new Vertex("a"), new Vertex("c")));
	}

}
