package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import calculator.Grid;

public class Window extends JFrame {

	private static final long serialVersionUID = 8698427445604449743L;
	
	private Grid gameBoard = new Grid();
	private Canvas cPanel = new Canvas(gameBoard);
	private ButtonPanel bPanel = new ButtonPanel(gameBoard);
	private TimeControl timer = new TimeControl(cPanel,bPanel);

	public Window(String string) {
		
		super(string);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(cPanel, BorderLayout.CENTER);
		add(bPanel, BorderLayout.SOUTH);
		setSize(Canvas.RESOLUTION, Canvas.RESOLUTION);	
		timer.start();
	}

}
