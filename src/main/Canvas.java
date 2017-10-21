package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Canvas extends JPanel {

	private static final long serialVersionUID = -4810618286807932601L;
	public static final int RESOLUTION = 1000;
	private static final int CELLSIZE = 5;
	public static final int INITIAL_DELAY = 1000;
	private Timer timer = new Timer(INITIAL_DELAY, e -> updatePanel()) ;
	private Grid grella = new Grid();
	
	public Canvas() {
		timer.setRepeats(true);
	}
	
	
	public void paintComponent (Graphics g){
		
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		g.setColor(Color.GREEN);
		
		HashSet<Point> test = grella.getAliveCells();
		
		for (Point p : test) g.fillRect(p.x*CELLSIZE, p.y*CELLSIZE, CELLSIZE - 1, CELLSIZE - 1);		
		
	}

	public void updatePanel() {
		
		grella.updateGrid();
		repaint();
			
	}
	
	public void changeStatus() {
		if (timer.isRunning()) timer.stop();
		else timer.start();
		
	}
	
	public void setDelay(int delay) {
		timer.setDelay(delay);
	}

}
