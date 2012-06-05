package plaid.lang;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalArgumentException;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.reference.AbstractPlaidDispatch;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.DimensionValue;
import plaid.generated.Idiv$plaid$1$plaid;
import plaid.generated.Ieqeq$plaid$1$plaid;
import plaid.generated.Igteq$plaid$1$plaid;
import plaid.generated.Imod$plaid$1$plaid;
import plaid.generated.Imult$plaid$1$plaid;
import plaid.generated.Iplus$plaid$1$plaid;
import plaid.generated.Isub$plaid$1$plaid;
import plaid.generated.ItoString$0$plaid;

public final class Float64 extends AbstractPlaidDispatch
					implements Iplus$plaid$1$plaid,
					Ieqeq$plaid$1$plaid,
					Imult$plaid$1$plaid,
					Isub$plaid$1$plaid,
					Igteq$plaid$1$plaid,
					Idiv$plaid$1$plaid, 
					ItoString$0$plaid,
					Imod$plaid$1$plaid,
					plaid.generated.InativeLessThan$1$plaid{
	private static final PlaidDispatch FLOAT64_DISPATCH;
	public static final PlaidState theState$plaid;
	static {
		theState$plaid = new AbstractPlaidState(new Float64(new DimensionValue("plaid/lang/Float64", null, null))) {
			@Override
			public PlaidObject instantiate() {
				throw new PlaidIllegalOperationException("Cannot instantiate Float64 state");
			}
		};
		FLOAT64_DISPATCH = theState$plaid.getDispatch();
	}
	
	public static PlaidObject plaidFloat64(double d) { 
		return new Float64PlaidJavaObject(d);
	}
	
	private Float64(ObjectValue metadata) {
		super(metadata);
	}
	
	@Override
	public PlaidObject gteq$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			double first = ((Float64PlaidJavaObject) receiver).doubleValue;
			double second = ((Float64PlaidJavaObject) arg).doubleValue;
			boolean b = first >= second;
			return Util.bool(b);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException(">= failed", e.getCause());
		}
	}

	@Override
	public PlaidObject sub$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			double first = ((Float64PlaidJavaObject) receiver).doubleValue;
			double second = ((Float64PlaidJavaObject) arg).doubleValue;
			double result = first- second;
			return plaidFloat64(result);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("- failed", e.getCause());
		}
	}

	@Override
	public PlaidObject mult$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			double first = ((Float64PlaidJavaObject) receiver).doubleValue;
			double second = ((Float64PlaidJavaObject) arg).doubleValue;
			double result = first * second;
			return plaidFloat64(result);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("* failed", e.getCause());
		}
	}

	@Override
	public PlaidObject eqeq$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			double first = ((Float64PlaidJavaObject) receiver).doubleValue;
			double second = ((Float64PlaidJavaObject) arg).doubleValue;
			boolean b = first == second;
			return Util.bool(b);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("== failed", e.getCause());
		}
	}

	@Override
	public PlaidObject plus$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			double first = ((Float64PlaidJavaObject) receiver).doubleValue;
			double second = ((Float64PlaidJavaObject) arg).doubleValue;
			double result = first + second;
			return plaidFloat64(result);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("+ failed", e.getCause());
		}
	}

	@Override
	public PlaidObject div$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			double first = ((Float64PlaidJavaObject) receiver).doubleValue;
			double second = ((Float64PlaidJavaObject) arg).doubleValue;
			double result = first/second;
			return plaidFloat64(result);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("/ failed", e.getCause());
		}
	}
	
	@Override
	public PlaidObject mod$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			double first = ((Float64PlaidJavaObject) receiver).doubleValue;
			double second = ((Float64PlaidJavaObject) arg).doubleValue;
			double result = first%second;
			return plaidFloat64(result);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("/ failed", e.getCause());
		}
	}
	
	@Override
	public PlaidObject nativeLessThan(PlaidObject receiver, PlaidObject arg) {
		try {
			double first = ((Float64PlaidJavaObject) receiver).doubleValue;
			double second = ((Float64PlaidJavaObject) arg).doubleValue;
			boolean result = first < second;
			return Util.bool(result);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("nativeLesThan failed", e.getCause());
		}
	}
	
	@Override
	public PlaidObject toString(PlaidObject x) {
		return plaid.fastruntime.Util.string(((PlaidJavaObject)x).getJavaObject().toString());
	}
	
	private static final class Float64PlaidJavaObject implements PlaidJavaObject, ItoString$0$plaid {
		
		public Float64PlaidJavaObject(double float64) {
			this.doubleValue = float64;
		}
		
		private Double javaObject;
		final public double doubleValue;
		
		@Override
		public final Object getJavaObject() {
			if (this.javaObject == null) {
				this.javaObject =  Double.valueOf(doubleValue);
			}
			return this.javaObject;
		}
		
		@Override
		public final boolean canBePrimitive(JavaPrimitive p) {
			switch (p) {
			case DOUBLE: return true;
			default: return false;
			}
		}
		
		@Override
		public final Object asPrimitive(JavaPrimitive p) {
			switch (p) {
			case DOUBLE: return this.getJavaObject();
			default: throw new PlaidIllegalOperationException("Floats cannot be used as " + p.name + " primitives.");
			}
		}

		@Override
		public final PlaidDispatch getDispatch() {
			return FLOAT64_DISPATCH;
		}
		
		@Override
		public final void changeState(PlaidState s) {
			throw new PlaidIllegalOperationException("Cannot change the state of a Java Object.");
		}

		@Override
		public final PlaidObject[] getStorage() {
			throw new PlaidIllegalOperationException("No storage object for a Java Object.");
		}

		public java.lang.String toString() {
			return "" + this.javaObject;			
		}
		
		@Override
		public PlaidObject toString(PlaidObject pthis) {
			if ( pthis instanceof Float64PlaidJavaObject) {
			return Util.string(pthis.toString());
			} else {
				throw new PlaidIllegalArgumentException("Invalid receiver type : " + pthis.getClass().toString());
			}
		}
	}


}
