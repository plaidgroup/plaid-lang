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
import plaid.generated.IcompareT_o$plaid$1$plaid;
import plaid.generated.Idiv$plaid$1$plaid;
import plaid.generated.Isub$plaid$1$plaid;
import plaid.generated.IgetC_lass$plaid$0$plaid;
import plaid.generated.IbyteV_alue$plaid$0$plaid;
import plaid.generated.Inotify$plaid$0$plaid;
import plaid.generated.IfloatV_alue$plaid$0$plaid;
import plaid.generated.IhashC_ode$plaid$0$plaid;
import plaid.generated.IlongV_alue$plaid$0$plaid;
import plaid.generated.Iwait$plaid$0$plaid;
import plaid.generated.InotifyA_ll$plaid$0$plaid;
import plaid.generated.Iwait$plaid$1$plaid;
import plaid.generated.IshortV_alue$plaid$0$plaid;
import plaid.generated.Iwait$plaid$2$plaid;
import plaid.generated.Iplus$plaid$1$plaid;
import plaid.generated.Imult$plaid$1$plaid;
import plaid.generated.Iequals$plaid$1$plaid;
import plaid.generated.IintV_alue$plaid$0$plaid;
import plaid.generated.ItoS_tring$plaid$0$plaid;
import plaid.generated.IdoubleV_alue$plaid$0$plaid;
class I_nt64 extends AbstractPlaidDispatch implements plaid.generated.IcompareT_o$plaid$1$plaid, plaid.generated.Idiv$plaid$1$plaid, plaid.generated.Isub$plaid$1$plaid, plaid.generated.IgetC_lass$plaid$0$plaid, plaid.generated.IbyteV_alue$plaid$0$plaid, plaid.generated.Inotify$plaid$0$plaid, plaid.generated.IfloatV_alue$plaid$0$plaid, plaid.generated.IhashC_ode$plaid$0$plaid, plaid.generated.IlongV_alue$plaid$0$plaid, plaid.generated.Iwait$plaid$0$plaid, plaid.generated.InotifyA_ll$plaid$0$plaid, plaid.generated.Iwait$plaid$1$plaid, plaid.generated.IshortV_alue$plaid$0$plaid, plaid.generated.Iwait$plaid$2$plaid, plaid.generated.Iplus$plaid$1$plaid, plaid.generated.Imult$plaid$1$plaid, plaid.generated.Iequals$plaid$1$plaid, plaid.generated.IintV_alue$plaid$0$plaid, plaid.generated.ItoS_tring$plaid$0$plaid, plaid.generated.IdoubleV_alue$plaid$0$plaid { 
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
	public PlaidObject compareT_o$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.integer(((Long)parsedReciever).compareTo(((Long)parsedArg1)));
	}
	public PlaidObject div$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return new LongPlaidJavaObject(((Long)parsedReciever)/((Long)parsedArg1));
	}
	public PlaidObject sub$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return new LongPlaidJavaObject(((Long)parsedReciever)-((Long)parsedArg1));
	}
	public PlaidObject getC_lass$plaid(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.javaToPlaid(((Object)parsedReciever).getClass());
	}
	public PlaidObject byteV_alue$plaid(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.javaToPlaid(((Long)parsedReciever).byteValue());
	}
	public PlaidObject notify$plaid(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		((Object)parsedReciever).notify();
		return Util.unit();
	}
	public PlaidObject floatV_alue$plaid(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.javaToPlaid(((Long)parsedReciever).floatValue());
	}
	public PlaidObject hashC_ode$plaid(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.integer(((Long)parsedReciever).hashCode());
	}
	public PlaidObject longV_alue$plaid(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.javaToPlaid(((Long)parsedReciever).longValue());
	}
	public PlaidObject wait$plaid(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		try {
			((Object)parsedReciever).wait();
			return Util.unit();
	
		} catch (Exception e) { 
			throw new PlaidIllegalOperationException("A java error has occured.",e); 
		}
	}
	public PlaidObject notifyA_ll$plaid(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		((Object)parsedReciever).notifyAll();
		return Util.unit();
	}
	public PlaidObject wait$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		try {
			((Object)parsedReciever).wait(((Long)parsedArg1));
			return Util.unit();
	
		} catch (Exception e) { 
			throw new PlaidIllegalOperationException("A java error has occured.",e); 
		}
	}
	public PlaidObject shortV_alue$plaid(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.javaToPlaid(((Long)parsedReciever).shortValue());
	}
	public PlaidObject wait$plaid(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1) {
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
	public PlaidObject plus$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return new LongPlaidJavaObject(((Long)parsedReciever)+((Long)parsedArg1));
	}
	public PlaidObject mult$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return new LongPlaidJavaObject(((Long)parsedReciever)*((Long)parsedArg1));
	}
	public PlaidObject equals$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.bool(((Long)parsedReciever).equals(((Object)parsedArg1)));
	}
	public PlaidObject intV_alue$plaid(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.integer(((Long)parsedReciever).intValue());
	}
	public PlaidObject toS_tring$plaid(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.string(((Long)parsedReciever).toString());
	}
	public PlaidObject doubleV_alue$plaid(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.float64(((Long)parsedReciever).doubleValue());
	}
}
