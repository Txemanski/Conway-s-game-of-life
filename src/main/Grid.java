package main;

import java.awt.Point;
import java.util.HashSet;

public class Grid {
	
	HashSet<Point> aliveCells = new HashSet<Point>();
	HashSet<Point> tempCells = new HashSet<Point>();
	HashSet<Point> checkedCells = new HashSet<Point>();
	
	
	public Grid () {
		aliveCells.add(new Point(2,1));
		aliveCells.add(new Point(3,2));
		aliveCells.add(new Point(3,3));
		aliveCells.add(new Point(2,3));
		aliveCells.add(new Point(1,3));
	}
	
	public void updateGrid() {
		
		aliveCells.forEach(e -> checkNeighbourhood(e.x, e.y));
		
		aliveCells.clear();
		tempCells.forEach(e -> aliveCells.add(e));
		tempCells.clear();
		checkedCells.clear();
	
	}
	
	private void aliveNextTurn(int x, int y) {
		
		int numberofAliveNeighbours = 0;
		
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++) 				
				if (aliveCells.contains(new Point(x + i, y + j)) && (i != 0 || j != 0))					
					numberofAliveNeighbours++;
			
		if (aliveCells.contains(new Point(x, y)) && numberofAliveNeighbours == 2 || numberofAliveNeighbours == 3 )
			tempCells.add(new Point(x, y));
		
	}
	
	private void checkNeighbourhood(int x, int y) {
		
		for (int i = -1; i < 2; i++) 
			for (int j = -1; j < 2; j++)
				if (!checkedCells.contains(new Point(x + i, y + j))) {
					checkedCells.add(new Point(x + i, y + j));
					aliveNextTurn(x + i, y + j);
				}		
	}

	public HashSet<Point> getAliveCells(){
		return aliveCells;
	}
	
	public void manualChangeStatusCell(Point p) {
		
		if (aliveCells.contains(p)) aliveCells.remove(p);
		else aliveCells.add(p);
		
	}
}
