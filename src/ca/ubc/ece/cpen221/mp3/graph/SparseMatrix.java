package ca.ubc.ece.cpen221.mp3.graph;

import java.util.*;

public class SparseMatrix {

	// Exterior hashmap takes row as key, interior takes column
	HashMap<Integer, HashMap<Integer, Integer>> rows;

	// Exterior hashmap takes column as key, interior takes row
	HashMap<Integer, HashMap<Integer, Integer>> columns;

	public SparseMatrix() {
		rows = new HashMap<Integer, HashMap<Integer, Integer>>();
		columns = new HashMap<Integer, HashMap<Integer, Integer>>();
	}

	/**
	 * Adds a specified value to a location in the array.
	 * @param row is the row where the value should be added
	 * @param column is the column where the value should be added
	 * @param value is the value to be added
	 */
	public void add(int row, int column, int value) {
		// If current row does not exist, add it
		if (!rows.containsKey(row)) {
			rows.put(row, new HashMap<Integer, Integer>());
			rows.get(row).put(column, value);
		}
		
		else{
			try{
			rows.get(row).put(column, value + rows.get(row).get(column));
			} catch(Exception E){
				rows.get(row).put(column, value);
			}
		}

		// If current column does not exist, add it
		if (!columns.containsKey(column)) {
			columns.put(column, new HashMap<Integer, Integer>());
			columns.get(column).put(row, value);
		}

		else {
			try{
				columns.get(column).put(row, value + rows.get(column).get(row));
			} catch(Exception E){
				columns.get(column).put(row, value);
			}
		}
	}

	/**
	 * Return the value at the specified row and column.
	 * 
	 * @param row
	 *            is the matrix row to be searched
	 * @param column
	 *            is the matrix column to be searched
	 * @return The value stored at the specified location.
	 */
	public int get(int row, int column) {
		if (!rows.get(row).containsKey(column))
			return 0;

		return (int) rows.get(row).get(column);
	}

	/**
	 * Returns a hash map representing a given row of a matrix where
	 * the key represents the column of the matrix
	 * @param key is the row of the matrix to take
	 * @return The hash map for the requested row
	 */
	public HashMap<Integer, Integer> getRow(int key) {
		return new HashMap<Integer, Integer>(rows.get(key));
	}

	/**
	 * Returns a hash map representing a given column of a matrix where
	 * the key represents the row of the matrix
	 * @param key is the column of the matrix to take
	 * @return The hash map for the requested column
	 */
	public HashMap<Integer, Integer> getColumn(int key) {
		return new HashMap<Integer, Integer>(columns.get(key));
	}

	/**
	 * Returns an array of row values that exist in the matrix (i.e.
	 * the matrix has non-zero elements in that row)
	 * @return An array of valid rows
	 */
	public Integer[] getRowIndices() {
		Integer[] result = new Integer[rows.keySet().toArray().length];
		rows.keySet().toArray(result);
		return result;
	}

	/**
	 * Returns an array of column values that exist in the matrix
	 * (i.e. The matrix has non-zero elements in that column)
	 * @return An array of valid columns
	 */
	public int[] getColumnIndices() {
		int[] result = new int[columns.keySet().toArray().length];
		for(int i =0; i < columns.keySet().toArray().length; i++){
		result[i] = (int) columns.keySet().toArray()[i];	
		}
		
		return result;
	}
	
	/**
	 * Carries out multiplication in the form A*B.
	 * @param a is the first matrix in the multiplication
	 * @param b is the second matrix in the multiplication
	 * @return A new matrix, being the result of the multiplication.
	 */
	public static SparseMatrix multiply(SparseMatrix a, SparseMatrix b) {

		SparseMatrix result = new SparseMatrix();
		
		// for all rows in A
		for (int i : a.getRowIndices()) {
			// for all columns in B
			for (int  j: b.getColumnIndices()) { // for all
				// for each value in the current row of A
				for (int k : a.getRow(i).keySet()) {

					result.add(i, j, a.get(i, k) * b.get(k, j));
				}
			}
		}
		
		
		return result;
	}
	
	/**
	 * Raises a matrix to a given integer power
	 * @param a is the matrix to be exponentiated
	 * @param exponent is the power the matrix should be raised to which
	 * must be greater than 1
	 * @return A new matrix, being the result of the exponentiation.
	 */
	public static SparseMatrix pow(SparseMatrix a, int exponent) {
		
		if(exponent < 2)
			throw new IllegalArgumentException();
		SparseMatrix result = SparseMatrix.multiply(a, a);
		for (int i = 0; i < exponent - 2; i++) {
			result = SparseMatrix.multiply(result, a);
		}
		return result;
	}

}
