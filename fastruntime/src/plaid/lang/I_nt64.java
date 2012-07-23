package plaid.lang;
import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.DimensionValue;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.Util;
import plaid.fastruntime.reference.AbstractPlaidDispatch;
import java.lang.Long;
import java.lang.Object;
import java.lang.Integer;
public class I_nt64 extends AbstractPlaidDispatch implements plaid.generated.IintV_alue$0$plaid, plaid.generated.IshortV_alue$0$plaid, plaid.generated.IcompareT_o$1$plaid, plaid.generated.IgetC_lass$0$plaid, plaid.generated.Ilt$plaid$1$plaid, plaid.generated.ItoS_tring$0$plaid, plaid.generated.Iamp$plaid$1$plaid, plaid.generated.Iplus$plaid$1$plaid, plaid.generated.Imult$plaid$1$plaid, plaid.generated.Imod$plaid$1$plaid, plaid.generated.Ipipe$plaid$1$plaid, plaid.generated.InotifyA_ll$0$plaid, plaid.generated.Igteq$plaid$1$plaid, plaid.generated.IbyteV_alue$0$plaid, plaid.generated.Igt$plaid$1$plaid, plaid.generated.Itilde$plaid$0$plaid, plaid.generated.Ibangeq$plaid$1$plaid, plaid.generated.Idiv$plaid$1$plaid, plaid.generated.IdoubleV_alue$0$plaid, plaid.generated.Inotify$0$plaid, plaid.generated.Isub$plaid$1$plaid, plaid.generated.IlongV_alue$0$plaid, plaid.generated.Ieqeq$plaid$1$plaid, plaid.generated.IfloatV_alue$0$plaid, plaid.generated.IhashC_ode$0$plaid, plaid.generated.Iequals$1$plaid, plaid.generated.Iwait$0$plaid, plaid.generated.Iwait$2$plaid, plaid.generated.Iwait$1$plaid, plaid.generated.Ilteq$plaid$1$plaid { 
	public I_nt64(ObjectValue metadata) {
		super(metadata);
	}
	public static PlaidObject plaidInt64(long val) {
		return new LongPlaidJavaObject(val);
	}
	public static final PlaidState theState$plaid;
	static {
		theState$plaid = new AbstractPlaidState(new I_nt64(new DimensionValue("plaid/lang/Int64", null, null))) { 
			@Override
			public PlaidObject instantiate() {
				throw new PlaidIllegalOperationException("Cannot instantiate Int64 state");
			}
		};
	}
	private static final class LongPlaidJavaObject implements PlaidJavaObject { 
		private final long value;
		private Long javaObj;
		public LongPlaidJavaObject(long value) {
			this.value = value;
			this.javaObj = Long.valueOf(value);
		}
		@Override
		public PlaidDispatch getDispatch() {
			return I_nt64.theState$plaid.getDispatch();
		}
		@Override
		public PlaidObject[] getStorage() {
			throw new PlaidIllegalOperationException("No storage object for a Java Object.");
		}
		@Override
		public void changeState(PlaidState s) {
			throw new PlaidIllegalOperationException("Cannot change the state of a Java Object.");
		}
		@Override
		public Object getJavaObject() {
			if (this.javaObj == null) {
				this.javaObj =  Long.valueOf(value);
			}
			return this.javaObj;
		}
		@Override
		public boolean canBePrimitive(PlaidJavaObject.JavaPrimitive p) {
			switch (p) {
			case LONG: return true;
			default: return false;
			}
		}
		@Override
		public Object asPrimitive(PlaidJavaObject.JavaPrimitive p) {
			switch (p) {
			case LONG: return this.getJavaObject();
			default: throw new PlaidIllegalOperationException("longs cannot be used as " + p.name + " primitives.");
			}
		}
	}
	public PlaidObject intV_alue(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.integer(((Long)parsedReciever).intValue());
	}
	public PlaidObject shortV_alue(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.javaToPlaid(((Long)parsedReciever).shortValue());
	}
	public PlaidObject compareT_o(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.integer(((Long)parsedReciever).compareTo(((Long)parsedArg1)));
	}
	public PlaidObject getC_lass(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.javaToPlaid(((Object)parsedReciever).getClass());
	}
	public PlaidObject lt$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.bool(((Long)parsedReciever)<((Long)parsedArg1));
	}
	public PlaidObject toS_tring(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.string(((Long)parsedReciever).toString());
	}
	public PlaidObject amp$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return new LongPlaidJavaObject(((Long)parsedReciever)&((Number)parsedArg1).longValue());
	}
	public PlaidObject plus$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return new LongPlaidJavaObject(((Long)parsedReciever)+((Number)parsedArg1).longValue());
	}
	public PlaidObject mult$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return new LongPlaidJavaObject(((Long)parsedReciever)*((Number)parsedArg1).longValue());
	}
	public PlaidObject mod$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return new LongPlaidJavaObject(((Long)parsedReciever)%((Number)parsedArg1).longValue());
	}
	public PlaidObject pipe$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return new LongPlaidJavaObject(((Long)parsedReciever)|((Number)parsedArg1).longValue());
	}
	public PlaidObject notifyA_ll(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		((Object)parsedReciever).notifyAll();
		return Util.unit();
	}
	public PlaidObject gteq$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.bool(((Long)parsedReciever)>=((Number)parsedArg1).longValue());
	}
	public PlaidObject byteV_alue(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.javaToPlaid(((Long)parsedReciever).byteValue());
	}
	public PlaidObject gt$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.bool(((Long)parsedReciever)>((Number)parsedArg1).longValue());
	}
	public PlaidObject tilde$plaid(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return new LongPlaidJavaObject(~((Long)parsedReciever));
	}
	public PlaidObject bangeq$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.bool(!((Long)parsedReciever).equals(((Number)parsedArg1).longValue()));
	}
	public PlaidObject div$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return new LongPlaidJavaObject(((Long)parsedReciever)/((Number)parsedArg1).longValue());
	}
	public PlaidObject doubleV_alue(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.float64(((Long)parsedReciever).doubleValue());
	}
	public PlaidObject notify(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		((Object)parsedReciever).notify();
		return Util.unit();
	}
	public PlaidObject sub$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return new LongPlaidJavaObject(((Long)parsedReciever)-((Number)parsedArg1).longValue());
	}
	public PlaidObject longV_alue(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.javaToPlaid(((Long)parsedReciever).longValue());
	}
	public PlaidObject eqeq$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.bool(((Long)parsedReciever).equals(((Number)parsedArg1).longValue()));
	}
	public PlaidObject floatV_alue(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.javaToPlaid(((Long)parsedReciever).floatValue());
	}
	public PlaidObject hashC_ode(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.integer(((Long)parsedReciever).hashCode());
	}
	public PlaidObject equals(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.bool(((Long)parsedReciever).equals(((Object)parsedArg1)));
	}
	public PlaidObject wait(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		try {
			((Object)parsedReciever).wait();
			return Util.unit();
	
		} catch (Exception e) { 
			throw new PlaidIllegalOperationException("A java error has occured.",e); 
		}
	}
	public PlaidObject wait(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		try {
			((Object)parsedReciever).wait(((Long)parsedArg1), ((Integer)parsedArg2));
			return Util.unit();
	
		} catch (Exception e) { 
			throw new PlaidIllegalOperationException("A java error has occured.",e); 
		}
	}
	public PlaidObject wait(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		try {
			((Object)parsedReciever).wait(((Long)parsedArg1));
			return Util.unit();
	
		} catch (Exception e) { 
			throw new PlaidIllegalOperationException("A java error has occured.",e); 
		}
	}
	public PlaidObject lteq$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.bool(((Long)parsedReciever)<=((Long)parsedArg1));
	}
}
