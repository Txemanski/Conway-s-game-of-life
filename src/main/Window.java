package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Window extends JFrame implements KeyListener{

	private static final long serialVersionUID = 8698427445604449743L;
	
	private Canvas p = new Canvas();

	public Window(String string) {
		// TODO Auto-generated constructor stub
		super(string);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(p);
		setSize(Canvas.RESOLUTION, Canvas.RESOLUTION);
		addKeyListener(this);
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {if (arg0.getKeyCode() == KeyEvent.VK_SPACE)	p.updatePanel();}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
