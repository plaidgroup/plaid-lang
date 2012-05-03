package plaid.tests.runtime;

import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidStorage;

public class PlaidStorageExample implements PlaidStorage {
	private final PlaidObject x;
	private PlaidObject y;
	
	public PlaidStorageExample(PlaidObject x, PlaidObject y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	
	public PlaidObject getY() {
		return y;
	}
	public void setY(PlaidObject y) {
		this.y = y;
	}
	public PlaidObject getX() {
		return x;
	}
	
	
}
