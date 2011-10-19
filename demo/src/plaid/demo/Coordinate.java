package plaid.demo;

import java.awt.Color;

public class Coordinate {
	private final int column;
	private final int row;
	private final Color color;
	
	public Coordinate(int column, int row, Color color) {
		super();
		this.column = column;
		this.row = row;
		this.color = color;
	}
	public int getColumn() {
		return column;
	}
	public int getRow() {
		return row;
	}
	public Color getColor() {
		return color;
	}
}
