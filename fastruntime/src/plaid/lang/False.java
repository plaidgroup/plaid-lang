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

public class False extends AbstractPlaidState 
implements Iampamp$plaid$1$plaid,
		   ItoString$0$plaid {
	
	private static final java.lang.String FALSE_TAG = "plaid/lang/False";
	private static final java.lang.String BOOLEAN_TAG = "plaid/lang/Boolean";
	
	public static final plaid.fastruntime.PlaidState theState$plaid;
	public static final PlaidJavaObject FALSE_VALUE;
	static {
	theState$plaid = new False(new DimensionValue(FALSE_TAG, null, new DimensionValue(BOOLEAN_TAG, null, null)));
	FALSE_VALUE = ((False)theState$plaid).new FalsePlaidJavaObject(theState$plaid);
	}
	
	private False(ObjectValue metadata) {
		super(metadata); 	
	}

	@Override
	public PlaidObject ampamp$plaid(PlaidObject receiver, PlaidObject arg) {
		return receiver;
	}
	
	@Override
	public PlaidObject instantiate() {
		return FALSE_VALUE;
	}
	
	@Override
	public PlaidObject toString(PlaidObject x) {
		return Util.string("false");
	}
	
	
	@Override
	public boolean matches(java.lang.String tag) {
		return tag.equals(FALSE_TAG) || tag.equals(BOOLEAN_TAG);
	}
	
	@Override
	public java.lang.String getTopTag() {
		return FALSE_TAG;
	}

	private final class FalsePlaidJavaObject extends SimplePlaidJavaObject {
		protected FalsePlaidJavaObject(PlaidState dispatch) {
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
	
}