package plaid.demo;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;

public class GameBoardPanel extends Panel implements GameBoardControlsListener, Runnable {
	private static final long serialVersionUID = 329664493362382895L;

	protected CellGridCanvas canvas;
	protected BoardGameGrid<?> grid;
	//protected org.bitstorm.util.CellGridCanvas canvas;
	//protected org.bitstorm.util.GameOfLifeGrid grid;
	

	protected final int genTime = 200;

	private GameBoardControls controls;

	protected static Thread gameThread = null;

	/**
	 * Initialize UI.
	 * 
	 * @see java.applet.Applet#init()
	 */
	public GameBoardPanel(BoardGameGrid<?> gameGrid, int cellSize) {
		// set background colour
		setBackground(new Color(0x999999));

		// create gameOfLifeGrid
		this.grid = gameGrid;
		grid.clear();

		// create GameOfLifeCanvas
		canvas = new CellGridCanvas(grid, cellSize);

		// create GameOfLifeControls
		controls = new GameBoardControls();
		controls.addListener( this );
		
		// put it all together
		GridBagLayout gridbag = new GridBagLayout();
		setLayout(gridbag);
		GridBagConstraints canvasContraints = new GridBagConstraints();

		canvasContraints.fill = GridBagConstraints.BOTH;
		canvasContraints.gridx = GridBagConstraints.REMAINDER;
		canvasContraints.gridy = 0;
		canvasContraints.weightx = 1;
		canvasContraints.weighty = 1;
		canvasContraints.anchor = GridBagConstraints.CENTER;
		gridbag.setConstraints(canvas, canvasContraints);
		add(canvas);
		
		GridBagConstraints controlsContraints = new GridBagConstraints();
        canvasContraints.gridy = 1;
        canvasContraints.gridx = 0;
        controlsContraints.gridx = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(controls, controlsContraints);
        add(controls);

        reset();
		setVisible(true);
		validate();
	}

	@Override
	public void startStopButtonClicked() {
		if ( isRunning() ) {
			stop();
		} else {
			start2();
		}
	}

	@Override
	public void nextButtonClicked() {
		nextGeneration();	
	}
	
	@Override
	public void resetButtonClicked() {
		reset();
	}
	
	public void nextGeneration() {
		grid.next();
		canvas.repaint();
	}
	
	/**
	 * Starts creating new generations.
	 * No start() to prevent starting immediately.
	 */
	public synchronized void start2() {
		controls.start();
		if (gameThread == null) {
			gameThread = new Thread(this);
			gameThread.start();
		}
	}

	/**
	 * @see java.applet.Applet#stop()
	 */
	public void stop() {
		controls.stop();
		gameThread = null;
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	public synchronized void run() {
		while (gameThread != null) {
			nextGeneration();
			try {
				Thread.sleep(genTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Is the applet running?
	 * @return true: applet is running
	 */
	public boolean isRunning() {
		return gameThread != null;
	}
	
	/**
	 * Resets screen
	 */
	public void reset() {
		stop(); // might otherwise confuse user
		grid.clear();  
		canvas.repaint();
	}
}
