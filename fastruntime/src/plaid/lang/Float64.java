package plaid.lang;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalArgumentException;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.DimensionValue;
import plaid.fastruntime.reference.SimplePlaidJavaObject;
import plaid.generated.Idiv$plaid$1$plaid;
import plaid.generated.Ieqeq$plaid$1$plaid;
import plaid.generated.Igteq$plaid$1$plaid;
import plaid.generated.Imult$plaid$1$plaid;
import plaid.generated.Iplus$plaid$1$plaid;
import plaid.generated.Isub$plaid$1$plaid;
import plaid.generated.ItoString$0$plaid;

public class Float64 extends AbstractPlaidState
					implements Iplus$plaid$1$plaid,
					Ieqeq$plaid$1$plaid,
					Imult$plaid$1$plaid,
					Isub$plaid$1$plaid,
					Igteq$plaid$1$plaid,
					Idiv$plaid$1$plaid, 
					ItoString$0$plaid,
					plaid.generated.InativeLessThan$1$plaid{

	public static final plaid.fastruntime.PlaidState theState$plaid;
	static {
	theState$plaid = new Float64(new DimensionValue("plaid/lang/Float64", null, null));
	}
	
	public static PlaidObject plaidFloat64(java.lang.Double d) { 
		return  ((Float64) theState$plaid).new Float64PlaidJavaObject(theState$plaid,d);
	}
	
	private Float64(ObjectValue metadata) {
		super(metadata);
	}

	@Override
	public PlaidObject instantiate() {
		throw new PlaidIllegalOperationException("Cannot instantiate Float64 state");
	}
	
	@Override
	public PlaidObject gteq$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.Double first = ((java.lang.Double) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Double second = ((java.lang.Double) ((PlaidJavaObject) arg).getJavaObject());
			boolean b = first.doubleValue() >= second.doubleValue();
			return Util.bool(b);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException(">= failed", e.getCause());
		}
	}

	@Override
	public PlaidObject sub$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.Double first = ((java.lang.Double) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Double second = ((java.lang.Double) ((PlaidJavaObject) arg).getJavaObject());
			double result = first.doubleValue() - second.doubleValue();
			return plaidFloat64(result);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("- failed", e.getCause());
		}
	}

	@Override
	public PlaidObject mult$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.Double first = ((java.lang.Double) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Double second = ((java.lang.Double) ((PlaidJavaObject) arg).getJavaObject());
			double result = first.doubleValue() * second.doubleValue();
			return plaidFloat64(result);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("* failed", e.getCause());
		}
	}

	@Override
	public PlaidObject eqeq$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.Double first = ((java.lang.Double) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Double second = ((java.lang.Double) ((PlaidJavaObject) arg).getJavaObject());
			boolean b = first.doubleValue() == second.doubleValue();
			return Util.bool(b);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("== failed", e.getCause());
		}
	}

	@Override
	public PlaidObject plus$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.Double first = ((java.lang.Double) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Double second = ((java.lang.Double) ((PlaidJavaObject) arg).getJavaObject());
			double result = first.doubleValue() + second.doubleValue();
			return plaidFloat64(result);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("+ failed", e.getCause());
		}
	}

	@Override
	public PlaidObject div$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.Double first = ((java.lang.Double) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Double second = ((java.lang.Double) ((PlaidJavaObject) arg).getJavaObject());
			double result = first.doubleValue() / second.doubleValue();
			return plaidFloat64(result);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("/ failed", e.getCause());
		}
	}
	
	@Override
	public PlaidObject nativeLessThan(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.Double first = ((java.lang.Double) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Double second = ((java.lang.Double) ((PlaidJavaObject) arg).getJavaObject());
			boolean result = first.doubleValue() < second.doubleValue();
			return Util.bool(result);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("nativeLesThan failed", e.getCause());
		}
	}
	
	private final class Float64PlaidJavaObject extends SimplePlaidJavaObject {
		
		public Float64PlaidJavaObject(PlaidState dispatch,java.lang.Double javaObject) {
			super(dispatch, null, javaObject);
			this.rep = javaObject;
		}
		
		private java.lang.Double rep;
		
		@Override
		public boolean canBePrimitive(JavaPrimitive p) {
			switch (p) {
			case DOUBLE: return true;
			default: return false;
			}
		}
		
		@Override
		public Object asPrimitive(JavaPrimitive p) {
			switch (p) {
			case DOUBLE: return rep;
			default: throw new PlaidIllegalOperationException("Floats cannot be used as " + p.name + " primitives.");
			}
		}
	}

	@Override
	public PlaidObject toString(PlaidObject x) {
		return plaid.fastruntime.Util.string(((PlaidJavaObject)x).getJavaObject().toString());
	}
}
