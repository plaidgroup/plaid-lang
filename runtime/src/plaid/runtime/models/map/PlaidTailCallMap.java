package plaid.runtime.models.map;

import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidTailCall;
import plaid.runtime.Util;

public final class PlaidTailCallMap extends PlaidObjectMap implements PlaidTailCall {
	final private PlaidObject method;
	final private PlaidObject args;
	
	public PlaidTailCallMap(PlaidObject method, PlaidObject args) {
		this.method = method;
		this.args = args;
	}

	final public PlaidMethod getMethod() {
		return Util.toPlaidMethod(method);
	}
	
	final public PlaidObject getArgs() {
		return args;
	}
}
