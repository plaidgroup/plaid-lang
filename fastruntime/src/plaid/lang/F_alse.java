package plaid.lang;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.reference.AbstractPlaidDispatch;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.DimensionValue;
import plaid.fastruntime.reference.SimplePlaidJavaObject;
import plaid.generated.IXOR$1$plaid;
import plaid.generated.Iampamp$plaid$1$plaid;
import plaid.generated.Ibang$plaid$0$plaid;
import plaid.generated.Ieqeq$plaid$1$plaid;
import plaid.generated.Ipipepipe$plaid$1$plaid;
import plaid.generated.Isubsubgt$plaid$1$plaid;
import plaid.generated.ItoS_tring$0$plaid;

public final class F_alse extends AbstractPlaidDispatch 
implements Iampamp$plaid$1$plaid,
		   ItoS_tring$0$plaid,
		   Ieqeq$plaid$1$plaid,
		   Ibang$plaid$0$plaid,
		   Ipipepipe$plaid$1$plaid,
		   IXOR$1$plaid,
		   Isubsubgt$plaid$1$plaid {
	
	private static final java.lang.String FALSE_TAG = "plaid/lang/False".intern();
	private static final java.lang.String BOOLEAN_TAG = "plaid/lang/Boolean".intern();
	
	public static final plaid.fastruntime.PlaidState theState$plaid;
	public static final PlaidJavaObject FALSE_VALUE;
	static {
		F_alse falseDispatch = new F_alse(new DimensionValue(FALSE_TAG, null, new DimensionValue(BOOLEAN_TAG, null, null)));
		FALSE_VALUE = falseDispatch.new FalsePlaidJavaObject(falseDispatch);
		theState$plaid = new AbstractPlaidState(falseDispatch) {
			@Override
			public final PlaidObject instantiate() {
				return F_alse.FALSE_VALUE;
			}
			@Override
			public final java.lang.String getTopTag() {
				return FALSE_TAG;
			}
		};
	}
	
	private F_alse(ObjectValue metadata) {
		super(metadata); 	
	}

	@Override
	public PlaidObject ampamp$plaid(PlaidObject receiver, PlaidObject arg) {
		return Util.bool(false);
	}
	
	@Override
	public PlaidObject toS_tring(PlaidObject x) {
		return Util.string("false");
	}
	
	@Override
	public final boolean matches(java.lang.String tag) {
		return tag == FALSE_TAG || tag == BOOLEAN_TAG || tag.equals(FALSE_TAG) || tag.equals(BOOLEAN_TAG);
	}

	private final class FalsePlaidJavaObject extends SimplePlaidJavaObject {
		protected FalsePlaidJavaObject(PlaidDispatch dispatch) {
			super(dispatch,null,java.lang.Boolean.FALSE);
		}
		
		@Override
		public boolean canBePrimitive(JavaPrimitive p) {
			switch (p) {
			case BOOLEAN: return true;
			default: return false;
			}
		}
		
		@Override
		public Object asPrimitive(JavaPrimitive p) {
			switch (p) {
			case BOOLEAN: return java.lang.Boolean.FALSE;
			default: throw new PlaidIllegalOperationException("False cannot be used as a " + p.name + "primitive.");
			}
		}
	}

	@Override
	public PlaidObject eqeq$plaid(PlaidObject receiver, PlaidObject arg) {
		return Util.bool(arg.getDispatch() instanceof F_alse);
	}

	@Override
	public PlaidObject subsubgt$plaid(PlaidObject receiver, PlaidObject arg) {
		return Util.bool(true);
	}

	@Override
	public PlaidObject XOR(PlaidObject x, PlaidObject y) {
		return Util.bool(y.getDispatch() instanceof T_rue);
	}

	@Override
	public PlaidObject pipepipe$plaid(PlaidObject receiver, PlaidObject arg) {
		return Util.bool(arg.getDispatch() instanceof T_rue);
	}

	@Override
	public PlaidObject bang$plaid(PlaidObject receiver) {
		return Util.bool(true);
	}
	
}