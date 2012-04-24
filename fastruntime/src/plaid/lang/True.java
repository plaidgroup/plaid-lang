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
import plaid.generated.ItoString$0$plaid;

public final class True extends AbstractPlaidDispatch implements Iampamp$plaid$1$plaid,
ItoString$0$plaid,
Ieqeq$plaid$1$plaid,
Ibang$plaid$0$plaid,
Ipipepipe$plaid$1$plaid,
IXOR$1$plaid,
Isubsubgt$plaid$1$plaid {
	
	private static final java.lang.String TRUE_TAG = "plaid/lang/True".intern();
	private static final java.lang.String BOOLEAN_TAG = "plaid/lang/Boolean".intern();
	
	public static final plaid.fastruntime.PlaidState theState$plaid;
	public static final PlaidJavaObject TRUE_VALUE;
	
	static {
		True trueDispatch = new True(new DimensionValue(TRUE_TAG, null, new DimensionValue(BOOLEAN_TAG, null, null)));
		TRUE_VALUE = trueDispatch.new TruePlaidJavaObject(trueDispatch);
		theState$plaid = new AbstractPlaidState(trueDispatch) {
			@Override
			public final PlaidObject instantiate() {
				return TRUE_VALUE;
			}
			@Override
			public final java.lang.String getTopTag() {
				return TRUE_TAG;
			}
		};
	}
	
	private True(ObjectValue metadata) {
		super(metadata); 	
	}

	@Override
	public PlaidObject ampamp$plaid(PlaidObject receiver, PlaidObject arg) {
		return Util.bool(arg.getDispatch() instanceof True);
	}
	
	@Override
	public PlaidObject toString(PlaidObject x) {
		return Util.string("true");
	}
	
	@Override
	public boolean matches(java.lang.String tag) {
		return  tag == TRUE_TAG || tag == BOOLEAN_TAG || tag.equals(TRUE_TAG) || tag.equals(BOOLEAN_TAG);
	}
	
	private final class TruePlaidJavaObject extends SimplePlaidJavaObject {
		protected TruePlaidJavaObject(PlaidDispatch dispatch) {
			super(dispatch,null,java.lang.Boolean.TRUE);
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
			case BOOLEAN: return java.lang.Boolean.TRUE;
			default: throw new PlaidIllegalOperationException("True cannot be used as a " + p.name + "primitive.");
			}
		}
	}
	
	@Override
	public PlaidObject eqeq$plaid(PlaidObject receiver, PlaidObject arg) {
		return Util.bool(arg.getDispatch() instanceof True);
	}

	@Override
	public PlaidObject subsubgt$plaid(PlaidObject receiver, PlaidObject arg) {
		return Util.bool(arg.getDispatch() instanceof True);
	}

	@Override
	public PlaidObject XOR(PlaidObject x, PlaidObject y) {
		return Util.bool(y.getDispatch() instanceof False);
	}

	@Override
	public PlaidObject pipepipe$plaid(PlaidObject receiver, PlaidObject arg) {
		return Util.bool(true);
	}

	@Override
	public PlaidObject bang$plaid(PlaidObject receiver) {
		return Util.bool(false);
	}
}
