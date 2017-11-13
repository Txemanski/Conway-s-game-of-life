package main;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 6980336047696920906L;
	private static final String BSTART = "Start", BSTOP = "Stop";
	private static final int MIN = 10, MAX = 10000;
	
	private int delayValue = 500, generation = 0;	
	private JLabel stepCounter = new JLabel("Turn: " + generation);
	private boolean run = false;
	
	public ButtonPanel() {
		
		configureSpinner();
		configureButton();
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

	private void configureButton() {

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
	
	public void incrementCounter() {
		stepCounter.setText("Turn: " + ++generation);
	}
	
	public int getDelay () {
		return delayValue;
	}
	
	public boolean mustRun() {
		return run;
	}

}
