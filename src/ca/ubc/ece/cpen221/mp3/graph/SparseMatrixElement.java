package ca.ubc.ece.cpen221.mp3.graph;

public class SparseMatrixElement {
	
	private int offset;
	private boolean value;
	
	public SparseMatrixElement(int offset, boolean value){
		this.offset = offset;
		this.value = value;
	}
	
	public boolean getValue(){
		return value;
	}
	
	public void flipValue(){
		value = !value;
	}
	
	public void setValue(boolean newValue){
		value = newValue;
	}
	
	public int getOffset(){
		return offset;
	}
}
