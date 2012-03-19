package plaid.lang;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.DimensionValue;
import plaid.fastruntime.reference.SimplePlaidJavaObject;
import plaid.generated.Iampamp$plaid$1$plaid;
import plaid.generated.ItoString$0$plaid;

public class True extends AbstractPlaidState implements Iampamp$plaid$1$plaid,
														ItoString$0$plaid {
	
	private static final java.lang.String TRUE_TAG = "plaid/lang/True";
	private static final java.lang.String BOOLEAN_TAG = "plaid/lang/Boolean";
	
	public static final plaid.fastruntime.PlaidState theState$plaid;
	public static final PlaidJavaObject TRUE_VALUE;
	static {
	theState$plaid = new True(new DimensionValue("plaid/lang/True", null, new DimensionValue("plaid/lang/Boolean", null, null)));
	TRUE_VALUE = ((True)theState$plaid).new TruePlaidJavaObject(theState$plaid);
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
	
	@Override
	public boolean matches(java.lang.String tag) {
		return tag.equals(TRUE_TAG) || tag.equals(BOOLEAN_TAG);
	}
	
	@Override
	public java.lang.String getTopTag() {
		return TRUE_TAG;
	}
	
	private final class TruePlaidJavaObject extends SimplePlaidJavaObject {
		protected TruePlaidJavaObject(PlaidState dispatch) {
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
}
