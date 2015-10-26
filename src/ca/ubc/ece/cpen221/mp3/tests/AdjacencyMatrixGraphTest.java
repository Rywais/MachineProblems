package ca.ubc.ece.cpen221.mp3.tests;

import java.util.*;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.staff.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdjacencyMatrixGraphTest {

	/**
	 * Tests that vertices are properly added and checked for.
	 */
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
	
	/**
	 * Tests that the getVertices method returns a proper list
	 */
	@Test
	public void test2() {
		AdjacencyMatrixGraph amg = new AdjacencyMatrixGraph();
		Vertex[] v = new Vertex[4];
		
		for(int i = 0; i < 4; i++){
			v[i] = new Vertex(Integer.toString(i+1));
			amg.addVertex(v[i]);
		}
		
		List<Vertex> vList = amg.getVertices();
		for(Vertex i : vList){
			assertTrue(i.getLabel().equals(v[0].getLabel()) || 
					i.getLabel().equals(v[1].getLabel()) ||
					i.getLabel().equals(v[2].getLabel()) ||
					i.getLabel().equals(v[3].getLabel()));
		}
		for(Vertex i : vList){
			assertFalse(i.getLabel().equals(new Vertex("5")));
		}
		
	}
	
	@Test
	public void copyTest(){
		
		AdjacencyMatrixGraph amg = new AdjacencyMatrixGraph();
		AdjacencyMatrixGraph copy;
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
		
		copy = new AdjacencyMatrixGraph(amg);
		
		assertTrue(copy.edgeExists(v[0], v[1]));
		assertTrue(copy.edgeExists(v[1], v[2]));
		assertTrue(copy.edgeExists(v[1], v[3]));
		assertTrue(copy.edgeExists(v[2], v[0]));
		assertTrue(copy.edgeExists(v[3], v[1]));
		
		assertFalse(copy.edgeExists(v[1],v[0]));
		
		List<Vertex> vList = copy.getVertices();
		for(Vertex i : vList){
			assertTrue(i.getLabel().equals(v[0].getLabel()) || 
					i.getLabel().equals(v[1].getLabel()) ||
					i.getLabel().equals(v[2].getLabel()) ||
					i.getLabel().equals(v[3].getLabel()));
		}
		for(Vertex i : vList){
			assertFalse(i.getLabel().equals(new Vertex("5")));
		}
	}

}
