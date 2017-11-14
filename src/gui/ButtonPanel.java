package gui;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileFilter;

import calculator.Grid;

public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 6980336047696920906L;
	private static final String BSTART = "Start", BSTOP = "Stop";
	private static final int MIN = 10, MAX = 10000;
	
	private Grid gameBoard;
	private int delayValue = 500;	
	private JLabel stepCounter = new JLabel();
	private boolean run = false;
	
	public ButtonPanel(Grid g) {
		
		gameBoard = g;
		configureSpinner();
		configureStartButton();
		configureSaveButton();
		configureLoadButton();
		updateCounter();
		add(stepCounter);
	}

	private void configureSpinner() {
		
		JSpinner delay;
		SpinnerNumberModel mSpinner = new SpinnerNumberModel(delayValue, MIN, MAX, MIN);
		delay = new JSpinner(mSpinner);
		delay.addChangeListener(e -> {
			delayValue = (Integer) delay.getValue();
			if (delayValue < MIN)
				delayValue = MIN;
			if (delayValue > MAX)
				delayValue = MAX;
			delay.setValue(delayValue);
		});
		add(new JLabel("Delay in ms"));
		add(delay);

	}

	private void configureStartButton() {

		JButton startButton = new JButton(BSTART);
		startButton.setSize(300, 100);
		startButton.addActionListener(e -> {
			run = !run;
			if (run)
				startButton.setText(BSTOP);
			else
				startButton.setText(BSTART);
		});
		add(startButton);

	}
	
	private void configureSaveButton() {

		JButton saveButton = new JButton("Save");
		saveButton.setSize(300, 100);
		saveButton.addActionListener(e -> {
			JFileChooser fileDialog = new JFileChooser();
			fileDialog.setCurrentDirectory(new File("./"));
			int result = fileDialog.showSaveDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) gameBoard.saveStatustoFile(fileDialog.getSelectedFile());
		});
		add(saveButton);

	}
	
	private void configureLoadButton() {

		JButton loadButton = new JButton("Load");
		loadButton.setSize(300, 100);
		loadButton.addActionListener(e -> {
			JFileChooser fileDialog = new JFileChooser();
			fileDialog.setCurrentDirectory(new File("./"));
			int result = fileDialog.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) gameBoard.loadStatusfromFile(fileDialog.getSelectedFile());
		});
		add(loadButton);

	}
	
	public void updateCounter() {
		stepCounter.setText("Turn: " + gameBoard.getGeneration());
	}
	
	public int getDelay () {
		return delayValue;
	}
	
	public boolean mustRun() {
		return run;
	}

}
