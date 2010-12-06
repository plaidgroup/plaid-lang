package plaid.runtime;

import plaid.runtime.models.map.PlaidObjectMap;

public class PlaidTailCall extends PlaidObjectMap {
	private PlaidObject method;
	private PlaidObject args;
	
	public PlaidTailCall(PlaidObject method, PlaidObject args) {
		this.method = method;
		this.args = args;
	}

	public PlaidMethod getMethod() {
		return Util.toPlaidMethod(method);
	}
	
	public PlaidObject getArgs() {
		return args;
	}
}
