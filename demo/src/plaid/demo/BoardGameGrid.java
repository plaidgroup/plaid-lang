/**
 * Copyright 1996-2004 Edwin Martin <edwin@bitstorm.nl>
 * @author Edwin Martin
 */

package plaid.demo;

import java.awt.Dimension;
import java.util.ArrayList;

public class BoardGameGrid<T extends Cell<T>>{
	private int cellRows;
	private int cellCols;

	private ArrayList<ArrayList<T>> grid;
	
	private CellFactory<T> cellFactory;

	/**
	 * Contructs a GameOfLifeGrid.
	 * 
	 * @param cellCols number of columns
	 * @param cellRows number of rows
	 * @param factory an object that contains a factory method for constructing cell objects
	 */
	public BoardGameGrid(int cellCols, int cellRows, CellFactory<T> factory) {
		this.cellCols = cellCols;
		this.cellRows = cellRows;
		this.cellFactory = factory;

		clear();
	}

	/**
	 * Clears grid.
	 */
	public synchronized void clear() {
		grid = new ArrayList<ArrayList<T>>();
		for ( int c=0; c<cellCols; c++) {
			grid.add(new ArrayList<T>());
			for ( int r=0; r<cellRows; r++ ) {
				grid.get(c).add(cellFactory.makeCell());
			}
		}
	}

	/**
	 * Create next generation of shape.
	 */
	public synchronized void next() {
//		ArrayList<ArrayList<T>> clonedRepList = new ArrayList<ArrayList<T>>();
//		for ( int c=0; c<cellCols; c++) {
//			clonedRepList.add(new ArrayList<T>());
//			for ( int r=0; r<cellRows; r++ ) {
//				clonedRepList.get(c).add(grid.get(c).get(r).getValue());
//			}
//		}
//		for ( int c=0; c<cellCols; c++) {
//			for ( int r=0; r<cellRows; r++ ) {
//				ArrayList<S> neighbors = new ArrayList<T>();
//				neighbors.add(clonedRepList.get(colValue(c-1)).get(rowValue(r-1)));
//				neighbors.add(clonedRepList.get(colValue(c-1)).get(rowValue(r)));
//				neighbors.add(clonedRepList.get(colValue(c-1)).get(rowValue(r+1)));
//				neighbors.add(clonedRepList.get(colValue(c)).get(rowValue(r-1)));
//				neighbors.add(clonedRepList.get(colValue(c)).get(rowValue(r+1)));
//				neighbors.add(clonedRepList.get(colValue(c+1)).get(rowValue(r-1)));
//				neighbors.add(clonedRepList.get(colValue(c+1)).get(rowValue(r)));
//				neighbors.add(clonedRepList.get(colValue(c+1)).get(rowValue(r+1)));
//				grid.get(c).get(r).explore(neighbors);
//			}
//		}
		for ( int c=0; c<cellCols; c++) {
			for ( int r=0; r<cellRows; r++ ) {
				ArrayList<T> neighbors = new ArrayList<T>();
				neighbors.add(grid.get(colValue(c-1)).get(rowValue(r-1)));
				neighbors.add(grid.get(colValue(c-1)).get(rowValue(r)));
				neighbors.add(grid.get(colValue(c-1)).get(rowValue(r+1)));
				neighbors.add(grid.get(colValue(c)).get(rowValue(r-1)));
				neighbors.add(grid.get(colValue(c)).get(rowValue(r+1)));
				neighbors.add(grid.get(colValue(c+1)).get(rowValue(r-1)));
				neighbors.add(grid.get(colValue(c+1)).get(rowValue(r)));
				neighbors.add(grid.get(colValue(c+1)).get(rowValue(r+1)));
				grid.get(c).get(r).explore(neighbors);
			}
		}
		for ( int c=0; c<cellCols; c++) {
			for ( int r=0; r<cellRows; r++ ) {
				grid.get(c).get(r).change();
			}
		}
	}
	
	private int rowValue(int raw) {
		int toReturn = ((raw % cellRows) + cellRows )% cellRows;
		return toReturn;
	}
	
	private int colValue(int raw) {
		int toReturn = ((raw % cellCols) + cellCols )% cellCols;
		return toReturn;
	}
	
	/**
	 * Get color of each coordinate
	 */
	public Iterable<Coordinate> getCellCoordinates() {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		for ( int c=0; c<cellCols; c++) {
			for ( int r=0; r<cellRows; r++ ) {
				coordinates.add(new Coordinate(c,r,grid.get(c).get(r).getColor()));
			}
	}
		return coordinates;
	}


	/**
	 * Set value of cell.
	 * @param col x-coordinate of cell
	 * @param row y-coordinate of cell
	 * @param c value of cell
	 */
	public synchronized void setCell( int col, int row) {
		grid.get(col).get(row).toggle();
	}
	
	/**
	 * Get dimension of grid.
	 * @return dimension of grid
	 */
	public Dimension getDimension() {
		return new Dimension( cellCols, cellRows );
	}
}