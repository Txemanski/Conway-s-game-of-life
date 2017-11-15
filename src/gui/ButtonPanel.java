package gui;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 6980336047696920906L;
	private static final String BSTART = "Start", BSTOP = "Stop";
	private static final int MIN = 10, MAX = 10000;
	private static final FileNameExtensionFilter FILTER = new FileNameExtensionFilter("Saved States (*.sav)","sav");

	private UIManager masterUI;
	private int delayValue = 500;
	private JLabel stepCounter = new JLabel();
	private JButton saveButton;
	private JButton loadButton;

	public ButtonPanel(UIManager master) {

		masterUI = master;
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
			if (masterUI.timerGame.isRunning()) {
				masterUI.timerGame.stop();
				startButton.setText(BSTART);
				saveButton.setEnabled(true);
				loadButton.setEnabled(true);
			} else {
				masterUI.timerGame.start();
				startButton.setText(BSTOP);
				saveButton.setEnabled(false);
				loadButton.setEnabled(false);
			}
		});
		add(startButton);

	}

	private void configureSaveButton() {

		saveButton = new JButton("Save");
		saveButton.setSize(300, 100);
		saveButton.addActionListener(e -> {
			JFileChooser fileDialog = new JFileChooser();
			fileDialog.setFileFilter(FILTER);
			fileDialog.setAcceptAllFileFilterUsed(false);
			fileDialog.setCurrentDirectory(new File("./"));
			int result = fileDialog.showSaveDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) 
				
				try {
					File selectedFile = fileDialog.getSelectedFile();
					int processFile = JOptionPane.YES_OPTION;
					if (!Pattern.matches("([^\\s]+(\\.(?i)(sav))$)", selectedFile.getName())) selectedFile = new File(selectedFile.getCanonicalPath() + ".sav");
					if (selectedFile.exists()) processFile = JOptionPane.showConfirmDialog(this, "Do you want to overwrite the existing file?", "File already exists", JOptionPane.YES_NO_CANCEL_OPTION);
					if (processFile == JOptionPane.YES_OPTION)masterUI.gameBoard.saveStatustoFile(selectedFile);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "There was an error writing the file " + fileDialog.getSelectedFile().getName(), "Error!", JOptionPane.ERROR_MESSAGE, null);
					e1.printStackTrace();
				}
		});
		add(saveButton);

	}

	private void configureLoadButton() {

		loadButton = new JButton("Load");
		loadButton.setSize(300, 100);
		loadButton.addActionListener(e -> {
			JFileChooser fileDialog = new JFileChooser();
			fileDialog.setFileFilter(FILTER);
			fileDialog.setAcceptAllFileFilterUsed(false);
			fileDialog.setCurrentDirectory(new File("./"));
			int result = fileDialog.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION)
				try {
					masterUI.gameBoard.loadStatusfromFile(fileDialog.getSelectedFile());
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(this, "The file " + fileDialog.getSelectedFile().getName() + " seems to be corrupted", "Error!", JOptionPane.ERROR_MESSAGE, null);
					e1.printStackTrace();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "There was an error reading the file " + fileDialog.getSelectedFile().getName(), "Error!", JOptionPane.ERROR_MESSAGE, null);
					e1.printStackTrace();
				}
			masterUI.cPanel.repaint();
			updateCounter();
		});
		add(loadButton);

	}

	public void updateCounter() {
		stepCounter.setText("Turn: " + masterUI.gameBoard.getGeneration());
	}

	public int getDelay() {
		return delayValue;
	}

}
