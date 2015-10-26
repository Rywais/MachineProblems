package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.SparseMatrix;

public class SparseMatrixTests {

	@Test
	public void matrixMultiplicationTest() {
		SparseMatrix myMatrix = new SparseMatrix();
		SparseMatrix holdingMatrix;
		
		myMatrix.add(1,1,7);
		myMatrix.add(1,2,17);
		myMatrix.add(1,3,-8);
		
		myMatrix.add(2,1,4);
		myMatrix.add(2,3,11);
		
		myMatrix.add(3,1,-13);
		myMatrix.add(3,2,91);
		myMatrix.add(3,3,32);
		
		holdingMatrix = SparseMatrix.pow(myMatrix, 2);
		
		//Test for proper matrix squaring
		assertEquals(holdingMatrix.get(1,1),221);
		assertEquals(holdingMatrix.get(1,2),-609);
		assertEquals(holdingMatrix.get(1,3),-125);
		
		assertEquals(holdingMatrix.get(2,1),-115);
		assertEquals(holdingMatrix.get(2,2),1069);
		assertEquals(holdingMatrix.get(2,3),320);
		
		assertEquals(holdingMatrix.get(3,1),-143);
		assertEquals(holdingMatrix.get(3,2),2691);
		assertEquals(holdingMatrix.get(3,3),2129);
		
		//test for proper matrix multiplication
		for(int i = 1; i < 4; i++){
			for(int j = 1; j < 4; j++){
				assertEquals(holdingMatrix.get(i, j),
						SparseMatrix.multiply(myMatrix, myMatrix).get(i, j));
			}
		}
		
		//Test for higher power of matrix
		holdingMatrix = SparseMatrix.pow(myMatrix, 5);
		
		assertEquals(holdingMatrix.get(1,1),2821507);
		assertEquals(holdingMatrix.get(1,2),-42140563);
		assertEquals(holdingMatrix.get(1,3),-29072003);
		
		assertEquals(holdingMatrix.get(2,1),-6553661);
		assertEquals(holdingMatrix.get(2,2),91134015);
		assertEquals(holdingMatrix.get(2,3),57573476);
		
		assertEquals(holdingMatrix.get(3,1),-40098253);
		assertEquals(holdingMatrix.get(3,2),481485121);
		assertEquals(holdingMatrix.get(3,3),273958427);		
	}//End matrixMultiplicationTest()
	
	@Test
	public void copyConstructorTest(){
		
		SparseMatrix myMatrix = new SparseMatrix();
		SparseMatrix holdingMatrix;
		
		myMatrix.add(1,1,7);
		myMatrix.add(1,2,17);
		myMatrix.add(1,3,-8);
		
		myMatrix.add(2,1,4);
		myMatrix.add(2,3,11);
		
		myMatrix.add(3,1,-13);
		myMatrix.add(3,2,91);
		myMatrix.add(3,3,32);
		
		holdingMatrix = new SparseMatrix(myMatrix);
		
		for(int i : myMatrix.getRowIndices()){
			for(int j : myMatrix.getColumnIndices()){
				assertTrue(myMatrix.get(i, j) == holdingMatrix.get(i, j));
			}
		}
	}

}
