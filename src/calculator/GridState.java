package calculator;

import java.awt.Point;
import java.util.HashSet;

public class GridState extends HashSet<Point>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2030886502985433161L;
	private int generation;
	
	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}
	
}
