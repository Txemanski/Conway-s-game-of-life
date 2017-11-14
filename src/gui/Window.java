package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 8698427445604449743L;
	
	private Canvas p = new Canvas();
	private ButtonPanel bPanel = new ButtonPanel();
	private TimeControl timer = new TimeControl(p,bPanel);

	public Window(String string) {
		
		super(string);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(p, BorderLayout.CENTER);
		add(bPanel, BorderLayout.SOUTH);
		setSize(Canvas.RESOLUTION, Canvas.RESOLUTION);	
		timer.start();
	}

}
