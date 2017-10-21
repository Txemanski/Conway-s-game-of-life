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

	JButton startButton = new JButton(BSTART);
	JSpinner delay;
	Canvas canvas;

	public ButtonPanel(Canvas c) {

		canvas = c;
		configureButton();
		configureSpinner();
	}

	private void configureSpinner() {
		SpinnerNumberModel mSpinner = new SpinnerNumberModel (Canvas.INITIAL_DELAY,MIN,MAX,MIN);
		delay = new JSpinner(mSpinner);
		delay.addChangeListener(e -> {
			Integer value = (Integer) delay.getValue();
			if (value < MIN) delay.setValue(MIN);
			if (value > MAX) delay.setValue(MAX);
			value = (Integer) delay.getValue();
			canvas.setDelay(value);
		});
		add(new JLabel("Delay in ms"));
		add(delay);
		
	}

	private void configureButton() {

		startButton.setSize(300, 100);
		startButton.addActionListener(e -> {
			changeButtonText();
			canvas.changeStatus();
		});
		this.add(startButton);

	}

	private void changeButtonText() {
		
		if (startButton.getText().equals(BSTART)) startButton.setText(BSTOP);
		else startButton.setText(BSTART);
		
	}

}
