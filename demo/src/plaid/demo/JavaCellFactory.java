package plaid.demo;

public class JavaCellFactory implements CellFactory<JavaCell> {

	@Override
	public JavaCell makeCell() {
		return new JavaCell(false);
	}
	
}
