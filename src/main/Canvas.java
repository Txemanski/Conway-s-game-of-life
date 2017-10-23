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

public class Canvas extends JPanel implements MouseInputListener, MouseWheelListener {

	private static final long serialVersionUID = -4810618286807932601L;
	public static final int RESOLUTION = 1000;
	private int cellSize = 100;
	public static final int INITIAL_DELAY = 1000;
	private Point dragOrigin = new Point();
	private float xPitch = 0, yPitch = 0;
	private Grid gameBoard = new Grid();
	private Timer timer = new Timer(INITIAL_DELAY, e -> {
		gameBoard.updateGrid();
		repaint();
	});

	public Canvas() {
		timer.setRepeats(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		g.setColor(Color.GREEN);

		HashSet<Point> test = gameBoard.getAliveCells();

		for (Point p : test) 
			g.fillRect(Math.round(p.x + xPitch) * cellSize, Math.round(p.y + yPitch) * cellSize , cellSize - 1, cellSize - 1);

	}

	public void changeStatus() {
		if (timer.isRunning())
			timer.stop();
		else
			timer.start();

	}

	public void setDelay(int delay) {
		timer.setDelay(delay);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!timer.isRunning()) {
			gameBoard.manualChangeStatusCell(new Point((int)(e.getX() / cellSize - xPitch), (int)(e.getY() / cellSize - yPitch)));
			repaint();
		}

	}

	public void mousePressed(MouseEvent e) {

		dragOrigin = e.getPoint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		Point currentPoint = e.getPoint();
		
		int dragDiffX = currentPoint.x - dragOrigin.x;
		int dragDiffY = currentPoint.y - dragOrigin.y;
		
		if (dragDiffX > cellSize || dragDiffX < -1 * cellSize) {
			xPitch += dragDiffX / cellSize;
			dragOrigin.x = currentPoint.x;
			repaint();
		}
		
		if (dragDiffY > cellSize || dragDiffY < -1 * cellSize) {
			yPitch += dragDiffY / cellSize;
			dragOrigin.y = currentPoint.y;
			repaint();
		}

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		int rotation = e.getWheelRotation();
		
		
		//xPitch -= rotation * 500 / (cellSize * cellSize);
		//yPitch -= rotation * 500 / (cellSize * cellSize);
		cellSize -= rotation;
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

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println(e.getX() + " | " + e.getY());
		
	}

}
