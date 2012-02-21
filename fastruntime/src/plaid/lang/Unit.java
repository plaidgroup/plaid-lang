package plaid.lang;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.DimensionValue;
import plaid.fastruntime.reference.SimplePlaidJavaObject;

public class Unit extends AbstractPlaidState {

	public static final plaid.fastruntime.PlaidState theState$plaid;
	public static final PlaidJavaObject UNIT_VALUE;
	static {
	theState$plaid = new Unit(new DimensionValue("plaid/lang/Unit", null, null));
	UNIT_VALUE = new SimplePlaidJavaObject(theState$plaid, null, null);
	}
	
	private Unit(ObjectValue metadata) {
		super(metadata); 	
	}

	@Override
	public PlaidObject instantiate() {
		return UNIT_VALUE;
	}
	
}
