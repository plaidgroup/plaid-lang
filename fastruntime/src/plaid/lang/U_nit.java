package plaid.lang;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.reference.AbstractPlaidDispatch;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.DimensionValue;
import plaid.fastruntime.reference.SimplePlaidJavaObject;
import plaid.generated.Ibangeq$plaid$1$plaid;
import plaid.generated.Ieqeq$plaid$1$plaid;
import plaid.generated.ItoS_tring$0$plaid;

public class U_nit extends AbstractPlaidDispatch implements
	Ieqeq$plaid$1$plaid, Ibangeq$plaid$1$plaid, ItoS_tring$0$plaid {

	public static final plaid.fastruntime.PlaidState theState$plaid;
	public static final PlaidJavaObject UNIT_VALUE;
	static {
		PlaidDispatch unitDispatch = new U_nit(new DimensionValue("plaid/lang/Unit", null, null));
		UNIT_VALUE = new SimplePlaidJavaObject(unitDispatch, null, null);
		theState$plaid = new AbstractPlaidState(unitDispatch) {
			@Override
			public PlaidObject instantiate() {
				return UNIT_VALUE;
			}
		};
	
	}
	
	private U_nit(ObjectValue metadata) {
		super(metadata); 	
	}

	@Override
	public PlaidObject bangeq$plaid(PlaidObject receiver, PlaidObject arg) {
		if (arg == UNIT_VALUE) return F_alse.FALSE_VALUE;
		else return T_rue.TRUE_VALUE;
	}

	@Override
	public PlaidObject eqeq$plaid(PlaidObject receiver, PlaidObject arg) {
		if (arg == UNIT_VALUE) return T_rue.TRUE_VALUE;
		else return F_alse.FALSE_VALUE;
	}

	@Override
	public PlaidObject toS_tring(PlaidObject x) {
		return plaid.fastruntime.Util.string("unit");
	}
	
}
