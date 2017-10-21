package main;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 8698427445604449743L;
	
	private Canvas p = new Canvas();
	private ButtonPanel bPanel = new ButtonPanel(p);

	public Window(String string) {
		// TODO Auto-generated constructor stub
		super(string);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(p, BorderLayout.CENTER);
		add(bPanel, BorderLayout.SOUTH);
		setSize(Canvas.RESOLUTION, Canvas.RESOLUTION);		
	}

}
