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
import plaid.generated.IasInt32$0$plaid;
import plaid.generated.Idiv$plaid$1$plaid;
import plaid.generated.Ieqeq$plaid$1$plaid;
import plaid.generated.Igt$plaid$1$plaid;
import plaid.generated.Igteq$plaid$1$plaid;
import plaid.generated.Ilt$plaid$1$plaid;
import plaid.generated.Ilteq$plaid$1$plaid;
import plaid.generated.Imod$plaid$1$plaid;
import plaid.generated.Imult$plaid$1$plaid;
import plaid.generated.Iplus$plaid$1$plaid;
import plaid.generated.Isub$plaid$1$plaid;
import plaid.generated.ItoS_tring$0$plaid;

public final class I_nteger32 extends AbstractPlaidDispatch
						   implements Iplus$plaid$1$plaid,
						   			  Ieqeq$plaid$1$plaid,
						   			  Imult$plaid$1$plaid,
						   			  Isub$plaid$1$plaid,
						   			  Idiv$plaid$1$plaid,
						   			  Imod$plaid$1$plaid,
						   			  plaid.generated.InativeLessThan$1$plaid,
						   			  Igteq$plaid$1$plaid,
						   			  Igt$plaid$1$plaid,
						   			  Ilt$plaid$1$plaid,
						   			  Ilteq$plaid$1$plaid,
						   			  ItoS_tring$0$plaid,
						   			  IasInt32$0$plaid{

	private final static int CACHE_LOWER_BOUND = -127;
	private final static int CACHE_UPPER_BOUND =  128;
	private final static Integer32PlaidJavaObject[] INTEGER32_CACHE = new Integer32PlaidJavaObject[CACHE_UPPER_BOUND - CACHE_LOWER_BOUND];
	static {
		for (int i = 0 ; i < INTEGER32_CACHE.length ; i++ ) {
			INTEGER32_CACHE[i] = new Integer32PlaidJavaObject(CACHE_LOWER_BOUND + i); 			
		}
	}
	
	public static final plaid.fastruntime.PlaidState theState$plaid;
	static {
		theState$plaid = new AbstractPlaidState(new I_nteger32(new DimensionValue("plaid/lang/Integer", null, null))) {
			@Override
			public PlaidObject instantiate() {
				throw new PlaidIllegalOperationException("Cannot instantiate Integer32 state");
			}
		};
	}
	
	public static PlaidObject plaidInteger(int i) {
		if ( CACHE_LOWER_BOUND < i  && i < CACHE_UPPER_BOUND) {
			return INTEGER32_CACHE[i - CACHE_LOWER_BOUND];
		} else {
			return new Integer32PlaidJavaObject(i);
		}
	}
	
	private I_nteger32(ObjectValue metadata) {
		super(metadata);
	}

	@Override
	public PlaidObject gteq$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			int first = ((Integer32PlaidJavaObject) receiver).integerValue;
			int second = ((Integer32PlaidJavaObject) arg).integerValue;
			boolean b = first >= second;
			return Util.bool(b);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException(">= failed", e.getCause());
		}
	}
	
	@Override
	public PlaidObject gt$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			int first = ((Integer32PlaidJavaObject) receiver).integerValue;
			int second = ((Integer32PlaidJavaObject) arg).integerValue;
			boolean b = first > second;
			return Util.bool(b);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException(">= failed", e.getCause());
		}
	}

	@Override
	public PlaidObject sub$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			int first = ((Integer32PlaidJavaObject) receiver).integerValue; 
			int second =  ((Integer32PlaidJavaObject) arg).integerValue;
			int result = first - second;
			return plaidInteger(result);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("- failed", e.getCause());
		}
	}

	@Override
	public PlaidObject mult$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			int first = ((Integer32PlaidJavaObject) receiver).integerValue; 
			int second =  ((Integer32PlaidJavaObject) arg).integerValue;
			int result = first * second;
			return plaidInteger(result);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("* failed", e.getCause());
		}
	}

	@Override
	public PlaidObject eqeq$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			int first = ((Integer32PlaidJavaObject) receiver).integerValue; 
			int second = ((Integer32PlaidJavaObject) arg).integerValue; 
			boolean b = first == second;
			return Util.bool(b);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("== failed", e.getCause());
		}
	}

	@Override
	public PlaidObject plus$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			int first = ((Integer32PlaidJavaObject) receiver).integerValue; 
			int second = ((Integer32PlaidJavaObject) arg).integerValue; 
			int result = first + second;
			return plaidInteger(result);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("+ failed", e.getCause());
		}
	}
	
	@Override
	public PlaidObject lt$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			int first = ((Integer32PlaidJavaObject) receiver).integerValue;
			int second = ((Integer32PlaidJavaObject) arg).integerValue;
			boolean b = first < second;
			return Util.bool(b);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("<= failed", e.getCause());
		}
	}
	
	@Override
	public PlaidObject lteq$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			int first = ((Integer32PlaidJavaObject) receiver).integerValue;
			int second = ((Integer32PlaidJavaObject) arg).integerValue;
			boolean b = first <= second;
			return Util.bool(b);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("<= failed", e.getCause());
		}
	}

	@Override
	public PlaidObject nativeLessThan(PlaidObject x, PlaidObject y) {
		try {
			double first = ((Integer32PlaidJavaObject) x).integerValue;
			double second = ((Integer32PlaidJavaObject) y).integerValue;
			boolean result = first < second;
			return Util.bool(result);

		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("nativeLessThan failed", e.getCause());
		}
	}

	@Override
	public PlaidObject mod$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			int first = ((Integer32PlaidJavaObject) receiver).integerValue; 
			int second = ((Integer32PlaidJavaObject) arg).integerValue; 
			int result = first % second;
			return plaidInteger(result);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("% failed", e.getCause());
		}
	}

	@Override
	public PlaidObject div$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			int first = ((Integer32PlaidJavaObject) receiver).integerValue; 
			int second = ((Integer32PlaidJavaObject) arg).integerValue; 
			int result = first / second;
			return plaidInteger(result);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("/ failed", e.getCause());
		}
	}
	
	@Override
	public PlaidObject asInt32(PlaidObject receiver) {
		return receiver;
	}

	@Override
	public PlaidObject toS_tring(PlaidObject receiver) {
		try {
			int first = ((Integer32PlaidJavaObject) receiver).integerValue; 
			return Util.string(""+first);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("+ failed", e.getCause());
		}
	}
	
	private final static class Integer32PlaidJavaObject implements PlaidJavaObject {
		public final int integerValue;
		private Integer javaObject;
		
		protected Integer32PlaidJavaObject(int integerValue) {
			this.integerValue = integerValue;
		}
		
		@Override
		public Object getJavaObject() {
			if (this.javaObject == null) {
				this.javaObject =  Integer.valueOf(integerValue);
			}
			return this.javaObject;
		}

		
		@Override
		public boolean canBePrimitive(JavaPrimitive p) {
			switch (p) {
			case INT: 
			case DOUBLE:
			case LONG: return true;
			default: return false;
			}
		}
		
		@Override
		public Object asPrimitive(JavaPrimitive p) {
			switch (p) {
			case INT: return integerValue;
			case DOUBLE: return (double) integerValue;
			case LONG: return (long) integerValue;
			default: throw new PlaidIllegalOperationException("Integers cannot be used as " + p.name + "primitives.");
			}
		}
		
		@Override
		public PlaidDispatch getDispatch() {
			return I_nteger32.theState$plaid.getDispatch();
		}
		
		@Override
		public void changeState(PlaidState s) {
			throw new PlaidIllegalOperationException("Cannot change the state of a Java Object.");
		}

		@Override
		public PlaidObject[] getStorage() {
			throw new PlaidIllegalOperationException("No storage object for a Java Object.");
		}
	}
}
