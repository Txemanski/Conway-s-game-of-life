package main;

import javax.swing.Timer;

public class TimeControl {
	
	private static final int REFRESH = 100;
	
	private Canvas visualBoard;
	private ButtonPanel buttons;
	
	private Timer timerGame, timerChecker;
	
	public TimeControl(Canvas c, ButtonPanel b) {
		
		buttons = b;
		visualBoard = c;
		
		timerGame = new Timer(buttons.getDelay(), e -> {
			visualBoard.updateGrid();
			visualBoard.repaint();
			buttons.incrementCounter();
		});
		timerGame.setRepeats(true);
		
		timerChecker = new Timer(REFRESH, e -> {
			if (buttons.mustRun()) timerGame.start();
			else timerGame.stop();
			visualBoard.setGameStopped(!buttons.mustRun());
			timerGame.setDelay(buttons.getDelay());
		});
		timerChecker.setRepeats(true);
		
	}
	
	public void start() {
		timerChecker.start();
	}

}
