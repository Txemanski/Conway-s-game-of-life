package gui;

import javax.swing.Timer;

import calculator.Grid;

class UIManager {

	protected Grid gameBoard = new Grid();
	protected Canvas cPanel = new Canvas(this);
	protected ButtonPanel bPanel = new ButtonPanel(this);
	protected Timer timerGame;
	
	protected UIManager() {
		
		timerGame = new Timer(bPanel.getDelay(), e -> {
			gameBoard.updateGrid();
			cPanel.repaint();
			bPanel.updateCounter();
		});
		timerGame.setRepeats(true);
		
	}
	
}
