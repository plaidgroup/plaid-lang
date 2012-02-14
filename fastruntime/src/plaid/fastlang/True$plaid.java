package plaid.fastlang;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.DimensionValue;
import plaid.fastruntime.reference.SimplePlaidJavaObject;
import plaid.generated.Iampamp$plaid$1$plaid;

public class True$plaid extends AbstractPlaidState implements Iampamp$plaid$1$plaid {
	
	public static final PlaidJavaObject TRUE_VALUE = 
			new SimplePlaidJavaObject(new True$plaid(new DimensionValue("plaid/lang/True", null, new DimensionValue("plaid/lang/Boolean", null, null))),
					null, Boolean.TRUE);
	
	private True$plaid(ObjectValue metadata) {
		super(metadata); 
		
	}

	@Override
	public PlaidObject ampamp$plaid(PlaidObject receiver, PlaidObject arg) {
		return arg;
	}

}
