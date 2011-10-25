package plaid.demo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CellGridCanvas extends Canvas {
	
	private static final long serialVersionUID = 8814434513714886629L;

	/**
	 * Image for double buffering, to prevent flickering.
	 */
	private Image offScreenImage;
	private Graphics offScreenGraphics;
	private Image offScreenImageDrawed;
	/**
	 * Image, containing the drawn grid.
	 */
	private Graphics offScreenGraphicsDrawed;
	private int cellSize;
	private BoardGameGrid<?> cellGrid;

	/**
	 * Constructs a CellGridCanvas.
	 * @param cellGrid the GoL cellgrid
	 * @param cellSize size of cell in pixels
	 */
	public CellGridCanvas(BoardGameGrid<?> cellGrid, int cellSize) {
		this.cellGrid = cellGrid;
		this.cellSize = cellSize;

		setBackground(new Color(0x999999));
		
		addMouseListener(
			new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					draw(e.getX(), e.getY());
				}
			});
	}

	/**
	 * Draw in this cell.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public void draw(int x, int y) {
		try {
			cellGrid.setCell(x / cellSize, y / cellSize);
			repaint();
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			// ignore
		}
	}

	/** 
	 * Use double buffering.
	 * @see java.awt.Component#update(java.awt.Graphics)
	 */
	public void update(Graphics g) {
		Dimension d = getSize();
		if ((offScreenImage == null)) {
			offScreenImage = createImage(d.width, d.height);
			offScreenGraphics = offScreenImage.getGraphics();
		}
		paint(offScreenGraphics);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	/**
	 * Draw this generation.
	 * @see java.awt.Component#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		// Draw grid on background image, which is faster
		if (offScreenImageDrawed == null) {
			Dimension dim = cellGrid.getDimension();
			Dimension d = getSize();
			offScreenImageDrawed = createImage(d.width, d.height);
			offScreenGraphicsDrawed = offScreenImageDrawed.getGraphics();
			// draw background (MSIE doesn't do that)
			offScreenGraphicsDrawed.setColor(getBackground());
			offScreenGraphicsDrawed.fillRect(0, 0, d.width, d.height);
			offScreenGraphicsDrawed.setColor(Color.gray);
			offScreenGraphicsDrawed.fillRect(0, 0, cellSize * dim.width - 1, cellSize * dim.height - 1);
			offScreenGraphicsDrawed.setColor(getBackground());
			for (int x = 1; x < dim.width; x++) {
				offScreenGraphicsDrawed.drawLine(x * cellSize - 1, 0, x * cellSize - 1, cellSize * dim.height - 1);
			}
			for (int y = 1; y < dim.height; y++) {
				offScreenGraphicsDrawed.drawLine( 0, y * cellSize - 1, cellSize * dim.width - 1, y * cellSize - 1);
			}
		}
		g.drawImage(offScreenImageDrawed, 0, 0, null);
		// draw populated cells
		g.setColor(Color.red);
		Iterable<Coordinate> coordinates = cellGrid.getCellCoordinates();
		for(Coordinate coordinate : coordinates) {
			g.setColor(coordinate.getColor());
			g.fillRect(coordinate.getColumn() * cellSize, 
					coordinate.getRow() * cellSize, cellSize - 1, cellSize - 1);
		}
	}
	
	/**
	 * This is the preferred size.
	 * @see java.awt.Component#getPreferredSize()
	 */
	public Dimension getPreferredSize() {
		Dimension dim = cellGrid.getDimension();
		return new Dimension( cellSize * dim.width,	cellSize * dim.height );
	}

}