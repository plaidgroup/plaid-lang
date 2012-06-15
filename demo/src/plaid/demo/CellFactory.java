package plaid.demo;

public interface CellFactory<T extends Cell<T>>{
	T makeCell();
}
