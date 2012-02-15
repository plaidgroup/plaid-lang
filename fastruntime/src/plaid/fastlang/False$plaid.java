package plaid.fastlang;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.DimensionValue;
import plaid.fastruntime.reference.SimplePlaidJavaObject;
import plaid.generated.Iampamp$plaid$1$plaid;

public class False$plaid extends AbstractPlaidState 
implements Iampamp$plaid$1$plaid {
	
	public static final PlaidJavaObject FALSE_VALUE = 
			new SimplePlaidJavaObject(new False$plaid(new DimensionValue("plaid/lang/False", null, new DimensionValue("plaid/lang/Boolean", null, null))),
					null, Boolean.FALSE);
	
	private False$plaid(ObjectValue metadata) {
		super(metadata);
	}
	
	@Override
	public PlaidObject ampamp$plaid(PlaidObject receiver, PlaidObject arg) {
		return receiver;
	}
}