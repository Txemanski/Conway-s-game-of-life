package calculator;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import javax.swing.JOptionPane;

import persistence.StatePersistence;

public class Grid {

	GridState aliveCells = new GridState();
	HashSet<Point> tempCells = new HashSet<Point>();
	HashSet<Point> checkedCells = new HashSet<Point>();
	StatePersistence fileManager = new StatePersistence();

	public void updateGrid() {

		aliveCells.forEach(e -> checkNeighbourhood(e.x, e.y));

		aliveCells.clear();
		tempCells.forEach(e -> aliveCells.add(e));
		tempCells.clear();
		checkedCells.clear();
		aliveCells.setGeneration(aliveCells.getGeneration() + 1);
	}

	public HashSet<Point> getGrid() {
		return aliveCells;
	}

	private void aliveNextTurn(int x, int y) {

		int numberofAliveNeighbours = 0;

		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++)
				if (aliveCells.contains(new Point(x + i, y + j)) && (i != 0 || j != 0))
					numberofAliveNeighbours++;

		if (aliveCells.contains(new Point(x, y)) && numberofAliveNeighbours == 2 || numberofAliveNeighbours == 3)
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

	public void manualChangeStatusCell(Point p) {

		if (aliveCells.contains(p))
			aliveCells.remove(p);
		else
			aliveCells.add(p);

	}

	public int getGeneration() {
		return aliveCells.getGeneration();
	}

	public void saveStatustoFile(File f) throws IOException {

		fileManager.saveState(f, aliveCells);

	}

	public void loadStatusfromFile(File f) throws ClassNotFoundException, IOException {
		aliveCells = fileManager.loadState(f);

	}
}
