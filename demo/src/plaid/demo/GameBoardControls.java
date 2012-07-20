package plaid.demo;

import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class GameBoardControls extends Panel {
	private static final long serialVersionUID = -7640238177869138427L;
	
	private final String nextLabelText = "Next";
	private final String startLabelText = "Start";
	private final String stopLabelText = "Stop";
	private final String resetLabelText = "Reset";
	private Button startstopButton;
	private Button nextButton;
	private Button resetButton;
	private Vector<GameBoardControlsListener> listeners;

	/**
	 * Contructs the controls.
	 */
	public GameBoardControls() {
		listeners = new Vector<GameBoardControlsListener>();

		// start and stop buttom
		startstopButton = new Button(startLabelText);

		// when start/stop button is clicked
		startstopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startStopButtonClicked();
			}
		});

		// next generation button
		nextButton = new Button(nextLabelText);

		// when next button is clicked
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextButtonClicked();
			}
		});
		
		// next generation button
		resetButton = new Button(resetLabelText);

		// when next button is clicked
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButtonClicked();
			}
		});

		this.add(nextButton);
		this.add(startstopButton);
		this.add(resetButton);
		this.validate();
	}

	/**
	 * Add listener for this control
	 * 
	 * @param listener
	 *            Listener object
	 */
	public void addListener(GameBoardControlsListener listener) {
		listeners.addElement(listener);
	}

	/**
	 * Start-button is activated.
	 */
	public void start() {
		startstopButton.setLabel(stopLabelText);
		nextButton.setEnabled(false);
	}

	/**
	 * Stop-button is activated.
	 */
	public void stop() {
		startstopButton.setLabel(startLabelText);
		nextButton.setEnabled(true);
	}

	/**
	 * Called when the start/stop-button is clicked. Notify event-listeners.
	 */
	public void startStopButtonClicked() {
		for (GameBoardControlsListener l : listeners) {
			l.startStopButtonClicked();
		}
	}

	/**
	 * Called when the next-button is clicked. Notify event-listeners.
	 */
	public void nextButtonClicked() {
		for (GameBoardControlsListener l : listeners) {
			l.nextButtonClicked();
		}
	}
	
	/**
	 * Called when the next-button is clicked. Notify event-listeners.
	 */
	public void resetButtonClicked() {
		for (GameBoardControlsListener l : listeners) {
			l.resetButtonClicked();
		}
	}

}
