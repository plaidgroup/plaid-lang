package plaid.lang;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.DimensionValue;
import plaid.fastruntime.reference.SimplePlaidJavaObject;
import plaid.generated.Iampamp$plaid$1$plaid;
import plaid.generated.ItoString$0$plaid;

public class True extends AbstractPlaidState implements Iampamp$plaid$1$plaid,
														ItoString$0$plaid {
	
	public static final plaid.fastruntime.PlaidState theState$plaid;
	public static final PlaidJavaObject TRUE_VALUE;
	static {
	theState$plaid = new True(new DimensionValue("plaid/lang/True", null, new DimensionValue("plaid/lang/Boolean", null, null)));
	TRUE_VALUE = new SimplePlaidJavaObject(theState$plaid,null, java.lang.Boolean.TRUE);
	}
	
	private True(ObjectValue metadata) {
		super(metadata); 	
	}

	@Override
	public PlaidObject ampamp$plaid(PlaidObject receiver, PlaidObject arg) {
		return arg;
	}
	
	@Override
	public PlaidObject instantiate() {
		return TRUE_VALUE;
	}

	@Override
	public PlaidObject toString(PlaidObject x) {
		return Util.string("true");
	}
}