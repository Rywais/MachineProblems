package ca.ubc.ece.cpen221.mp3.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.staff.*;

import java.util.*;

public class AlgorithmsTest {

	/**
	 * Tests that the commonUpstreamVertices Algorithm works properly
	 * based on known data
	 */
	@Test
	public void commonUpstreamVerticesTest() {
		Graph amg = new AdjacencyMatrixGraph();
		Graph alg = new AdjacencyListGraph();
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
		
		List<Vertex> amgList = Algorithms.commonUpstreamVertices(amg, v[0],v[3]);
		List<Vertex> algList = Algorithms.commonUpstreamVertices(amg,v[0],v[3]);
		
		assertTrue(amgList.size() == algList.size() && 
				amgList.size() == 2);
		
		for(Vertex i : amgList){
			assertTrue(i.getLabel().equals(v[1].getLabel()) ||
					i.getLabel().equals(v[5].getLabel()));
		}
		
		for(Vertex i : algList){
			assertTrue(i.getLabel().equals(v[1].getLabel()) ||
					i.getLabel().equals(v[5].getLabel()));
		}
	}
	
	/**
	 * Tests that the commonDownstreamVertices algorithm works properly
	 * based on known data
	 */
	@Test
	public void commonDownstreamVerticesTest() {
		Graph amg = new AdjacencyMatrixGraph();
		Graph alg = new AdjacencyListGraph();
		Vertex[] v = new Vertex[10];
		
		for(int i = 0; i < 10; i++){
			v[i] = new Vertex(Integer.toString(i+1));
			amg.addVertex(v[i]);
			alg.addVertex(v[i]);
		}
		
		alg.addEdge(v[0], v[1]);
		alg.addEdge(v[0], v[2]);
		alg.addEdge(v[0], v[4]);
		alg.addEdge(v[0], v[6]);
		
		amg.addEdge(v[0], v[1]);
		amg.addEdge(v[0], v[2]);
		amg.addEdge(v[0], v[4]);
		amg.addEdge(v[0], v[6]);
		
		alg.addEdge(v[3],v[1]);
		alg.addEdge(v[3],v[8]);
		alg.addEdge(v[3],v[4]);
		alg.addEdge(v[3],v[5]);
		
		amg.addEdge(v[3],v[1]);
		amg.addEdge(v[3],v[8]);
		amg.addEdge(v[3],v[4]);
		amg.addEdge(v[3],v[5]);
		
		List<Vertex> amgList = Algorithms.commonDownstreamVertices(amg, v[0],v[3]);
		List<Vertex> algList = Algorithms.commonDownstreamVertices(amg,v[0],v[3]);
		
		assertTrue(amgList.size() == algList.size() && 
				amgList.size() == 2);
		
		for(Vertex i : amgList){
			assertTrue(i.getLabel().equals(v[1].getLabel()) ||
					i.getLabel().equals(v[4].getLabel()));
		}
		
		for(Vertex i : algList){
			assertTrue(i.getLabel().equals(v[1].getLabel()) ||
					i.getLabel().equals(v[4].getLabel()));
		}
	}
	
	/**
	 * Tests shortest path algorithm with sample data from a known graph
	 */
	@Test
	public void shortestPathTest(){
		Graph amg = new AdjacencyMatrixGraph();
		Graph alg = new AdjacencyListGraph();
		Vertex[] v = new Vertex[10];
		
		for(int i = 0; i < 10; i++){
			v[i] = new Vertex(Integer.toString(i+1));
			amg.addVertex(v[i]);
			alg.addVertex(v[i]);
		}
		
		amg.addEdge(v[1], v[3]);
		amg.addEdge(v[1], v[2]);
		amg.addEdge(v[2], v[3]);
		amg.addEdge(v[2], v[4]);
		amg.addEdge(v[2], v[5]);
		amg.addEdge(v[3], v[2]);
		amg.addEdge(v[3], v[4]);
		amg.addEdge(v[4], v[5]);
		
		assertEquals(Algorithms.shortestDistance(amg, v[1], v[5]),2);
		
		alg.addEdge(v[1], v[3]);
		alg.addEdge(v[1], v[2]);
		alg.addEdge(v[2], v[3]);
		alg.addEdge(v[2], v[4]);
		alg.addEdge(v[2], v[5]);
		alg.addEdge(v[3], v[2]);
		alg.addEdge(v[3], v[4]);
		alg.addEdge(v[4], v[5]);
		alg.addEdge(v[6], v[5]);
		
		assertEquals(Algorithms.shortestDistance(alg, v[1], v[6]),-1);
	}
	
	/**
	 * Tests Breadth First Search algorithm with known data from a simple graph
	 */
	@Test
	public void testBFS(){
		Graph amg = new AdjacencyMatrixGraph();
		Graph alg = new AdjacencyListGraph();
		Vertex[] v = new Vertex[10];
		
		for(int i = 0; i < 10; i++){
			v[i] = new Vertex(Integer.toString(i+1));
			amg.addVertex(v[i]);
			alg.addVertex(v[i]);
		}
		
		amg.addEdge(v[1], v[3]);
		amg.addEdge(v[1], v[2]);
		amg.addEdge(v[2], v[3]);
		amg.addEdge(v[2], v[4]);
		amg.addEdge(v[2], v[5]);
		amg.addEdge(v[3], v[2]);
		amg.addEdge(v[3], v[4]);
		amg.addEdge(v[4], v[5]);
		
		assertEquals(Algorithms.breadthFirstSearch(amg, v[1], v[5]),true);
		
		alg.addEdge(v[1], v[3]);
		alg.addEdge(v[1], v[2]);
		alg.addEdge(v[2], v[3]);
		alg.addEdge(v[2], v[4]);
		alg.addEdge(v[2], v[5]);
		alg.addEdge(v[3], v[2]);
		alg.addEdge(v[3], v[4]);
		alg.addEdge(v[4], v[5]);
		alg.addEdge(v[6], v[5]);
		
		assertEquals(Algorithms.breadthFirstSearch(alg, v[1], v[6]), false);
	}

	/**
	 * Tests Depth First Search algorithm with known data from a simple graph
	 */
	@Test
	public void testDFS(){
		Graph amg = new AdjacencyMatrixGraph();
		Graph alg = new AdjacencyListGraph();
		Vertex[] v = new Vertex[10];
		
		for(int i = 0; i < 10; i++){
			v[i] = new Vertex(Integer.toString(i+1));
			amg.addVertex(v[i]);
			alg.addVertex(v[i]);
		}
		
		amg.addEdge(v[1], v[3]);
		amg.addEdge(v[1], v[2]);
		amg.addEdge(v[2], v[3]);
		amg.addEdge(v[2], v[4]);
		amg.addEdge(v[2], v[5]);
		amg.addEdge(v[3], v[2]);
		amg.addEdge(v[3], v[4]);
		amg.addEdge(v[4], v[5]);
		
		assertEquals(Algorithms.depthFirstSearch(amg, v[1], v[5]),true);
		
		alg.addEdge(v[1], v[3]);
		alg.addEdge(v[1], v[2]);
		alg.addEdge(v[2], v[3]);
		alg.addEdge(v[2], v[4]);
		alg.addEdge(v[2], v[5]);
		alg.addEdge(v[3], v[2]);
		alg.addEdge(v[3], v[4]);
		alg.addEdge(v[4], v[5]);
		alg.addEdge(v[6], v[5]);
		
		assertEquals(Algorithms.depthFirstSearch(alg, v[1], v[6]), false);
	}
	
}
