package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashSet;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class Canvas extends JPanel implements MouseInputListener, MouseWheelListener{

	private static final long serialVersionUID = -4810618286807932601L;
	public static final int RESOLUTION = 1000;
	private int cellSize = 10;
	public static final int INITIAL_DELAY = 1000;
	private Point dragOrigin = new Point();
	private int xPitch= 0, yPitch = 0, scaleFactor = 0;
	private Timer timer = new Timer(INITIAL_DELAY, e -> updatePanel()) ;
	private Grid gameBoard = new Grid();
	
	public Canvas() {
		timer.setRepeats(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
	}
	
	
	public void paintComponent (Graphics g){
		
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		g.setColor(Color.GREEN);
		
		HashSet<Point> test = gameBoard.getAliveCells();
		
		for (Point p : test) g.fillRect(p.x*cellSize + xPitch, p.y*cellSize + yPitch, cellSize - 1, cellSize - 1);		
		
	}

	public void updatePanel() {
		
		gameBoard.updateGrid();
		repaint();
			
	}
	
	public void changeStatus() {
		if (timer.isRunning()) timer.stop();
		else timer.start();
		
	}
	
	public void setDelay(int delay) {
		timer.setDelay(delay);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (!timer.isRunning()) {
			gameBoard.manualChangeStatusCell(new Point((e.getX() - xPitch) /cellSize, (e.getY() - yPitch) / cellSize));
			repaint();
		}
		
	}
	
	public void mousePressed(MouseEvent e) {
		
		dragOrigin = e.getPoint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		Point currentPoint = e.getPoint();
		xPitch += currentPoint.x - dragOrigin.x;
		yPitch += currentPoint.y - dragOrigin.y;
		dragOrigin = currentPoint;
		
		repaint();
		
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		int rotation = e.getWheelRotation();
		cellSize += rotation;
		scaleFactor += rotation;
		xPitch -= rotation * scaleFactor;
		yPitch -= rotation * scaleFactor;
		System.out.println(e.getWheelRotation());
		
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
