package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class Canvas extends JPanel implements MouseInputListener, MouseWheelListener {

	private static final long serialVersionUID = -4810618286807932601L;
	public static final int RESOLUTION = 1000;
	private int cellSize = 10;
	private Point dragOrigin = new Point();
	private float xPitch = 0, yPitch = 0;
	private UIManager masterUI;

	public Canvas(UIManager master) {
		
		masterUI = master;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		g.setColor(Color.GREEN);
		
		masterUI.gameBoard.getGrid().forEach(p -> g.fillRect(Math.round(p.x + xPitch) * cellSize, Math.round(p.y + yPitch) * cellSize , cellSize - 1, cellSize - 1));

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!masterUI.timerGame.isRunning()) {
			masterUI.gameBoard.manualChangeStatusCell(new Point((int)(e.getX() / cellSize - xPitch), (int)(e.getY() / cellSize - yPitch)));
			repaint();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {dragOrigin = e.getPoint();}

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

		
	}

}
