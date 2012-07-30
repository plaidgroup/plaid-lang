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
import java.lang.String;
import java.lang.Integer;
import java.lang.CharSequence;
import java.lang.StringBuffer;
import java.nio.charset.Charset;
import java.util.Locale;
import java.lang.Object;
import java.lang.Long;
import java.lang.Boolean;
import java.lang.Character;
public class S_tring extends AbstractPlaidDispatch implements plaid.generated.IisE_mpty$0$plaid, plaid.generated.IsubS_equence$2$plaid, plaid.generated.IcompareT_o$1$plaid, plaid.generated.ItoC_harA_rray$0$plaid, plaid.generated.IoffsetB_yC_odeP_oints$2$plaid, plaid.generated.Icontains$1$plaid, plaid.generated.Isubstring$1$plaid, plaid.generated.IgetB_ytes$4$plaid, plaid.generated.Isubstring$2$plaid, plaid.generated.ItoS_tring$0$plaid, plaid.generated.IequalsI_gnoreC_ase$1$plaid, plaid.generated.Iintern$0$plaid, plaid.generated.IcontentE_quals$1$plaid, plaid.generated.Iplus$plaid$1$plaid, plaid.generated.IgetB_ytes$0$plaid, plaid.generated.IgetB_ytes$1$plaid, plaid.generated.IcodeP_ointC_ount$2$plaid, plaid.generated.ItoL_owerC_ase$0$plaid, plaid.generated.Ilength$0$plaid, plaid.generated.ItoL_owerC_ase$1$plaid, plaid.generated.Ibangeq$plaid$1$plaid, plaid.generated.Inotify$0$plaid, plaid.generated.Iconcat$1$plaid, plaid.generated.Isplit$2$plaid, plaid.generated.Isplit$1$plaid, plaid.generated.IcompareT_oI_gnoreC_ase$1$plaid, plaid.generated.IhashC_ode$0$plaid, plaid.generated.Iequals$1$plaid, plaid.generated.Iwait$0$plaid, plaid.generated.Iwait$2$plaid, plaid.generated.Iwait$1$plaid, plaid.generated.IendsW_ith$1$plaid, plaid.generated.IgetC_lass$0$plaid, plaid.generated.Itrim$0$plaid, plaid.generated.IlastI_ndexO_f$2$plaid, plaid.generated.IlastI_ndexO_f$1$plaid, plaid.generated.IregionM_atches$4$plaid, plaid.generated.IregionM_atches$5$plaid, plaid.generated.IcharA_t$1$plaid, plaid.generated.IcodeP_ointB_efore$1$plaid, plaid.generated.IcodeP_ointA_t$1$plaid, plaid.generated.InotifyA_ll$0$plaid, plaid.generated.IindexO_f$1$plaid, plaid.generated.IindexO_f$2$plaid, plaid.generated.IstartsW_ith$1$plaid, plaid.generated.IstartsW_ith$2$plaid, plaid.generated.Imatches$1$plaid, plaid.generated.ItoU_pperC_ase$1$plaid, plaid.generated.ItoU_pperC_ase$0$plaid, plaid.generated.IreplaceA_ll$2$plaid, plaid.generated.IgetC_hars$4$plaid, plaid.generated.Ieqeq$plaid$1$plaid, plaid.generated.Ireplace$2$plaid, plaid.generated.IreplaceF_irst$2$plaid { 
	public S_tring(ObjectValue metadata) {
		super(metadata);
	}
	public static PlaidObject plaidString(String val) {
		return new StringPlaidJavaObject(val);
	}
	public static final PlaidState theState$plaid;
	static {
		theState$plaid = new AbstractPlaidState(new S_tring(new DimensionValue("plaid/lang/String", null, null))) { 
			@Override
			public PlaidObject instantiate() {
				throw new PlaidIllegalOperationException("Cannot instantiate String state");
			}
		};
	}
	private static final class StringPlaidJavaObject implements PlaidJavaObject { 
		private final String value;
		private String javaObj;
		public StringPlaidJavaObject(String value) {
			this.value = value;
			this.javaObj = value;
		}
		@Override
		public PlaidDispatch getDispatch() {
			return S_tring.theState$plaid.getDispatch();
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
				this.javaObj =  String.valueOf(value);
			}
			return this.javaObj;
		}
		@Override
		public boolean canBePrimitive(PlaidJavaObject.JavaPrimitive p) {
			{
			return false;
			}
		}
		@Override
		public Object asPrimitive(PlaidJavaObject.JavaPrimitive p) {
			{
			 throw new PlaidIllegalOperationException("Non-primitive objects cannot be converted to primitives.");
			 }
			
		}
	}
	public PlaidObject isE_mpty(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.bool(((String)parsedReciever).isEmpty());
	}
	public PlaidObject subS_equence(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		return Util.javaToPlaid(((String)parsedReciever).subSequence(((Integer)parsedArg1), ((Integer)parsedArg2)));
	}
	public PlaidObject compareT_o(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.integer(((String)parsedReciever).compareTo(((String)parsedArg1)));
	}
	public PlaidObject toC_harA_rray(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.javaToPlaid(((String)parsedReciever).toCharArray());
	}
	public PlaidObject offsetB_yC_odeP_oints(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		return Util.integer(((String)parsedReciever).offsetByCodePoints(((Integer)parsedArg1), ((Integer)parsedArg2)));
	}
	public PlaidObject contains(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.bool(((String)parsedReciever).contains(((CharSequence)parsedArg1)));
	}
	public PlaidObject substring(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.string(((String)parsedReciever).substring(((Integer)parsedArg1)));
	}
	@Deprecated
	public PlaidObject getB_ytes(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1, PlaidObject arg2, PlaidObject arg3) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		Object parsedArg3 = ((PlaidJavaObject) arg2).getJavaObject();
		Object parsedArg4 = ((PlaidJavaObject) arg3).getJavaObject();
		((String)parsedReciever).getBytes(((Integer)parsedArg1), ((Integer)parsedArg2), ((byte[])parsedArg3), ((Integer)parsedArg4));
		return Util.unit();
	}
	public PlaidObject substring(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		return Util.string(((String)parsedReciever).substring(((Integer)parsedArg1), ((Integer)parsedArg2)));
	}
	public PlaidObject toS_tring(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.string(((String)parsedReciever).toString());
	}
	public PlaidObject equalsI_gnoreC_ase(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.bool(((String)parsedReciever).equalsIgnoreCase(((String)parsedArg1)));
	}
	public PlaidObject intern(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.string(((String)parsedReciever).intern());
	}
	public PlaidObject contentE_quals(PlaidObject receiver, PlaidObject arg0) {
		String parsedReciever = (String)((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		try {
			if (parsedArg1 instanceof CharSequence) {
				return Util.bool(parsedReciever.contentEquals((CharSequence)parsedArg1));
			}
			else if(parsedArg1 instanceof StringBuffer) {
				return Util.bool(parsedReciever.contentEquals((StringBuffer)parsedArg1));
			}
			else {
					throw new PlaidIllegalOperationException("Invalid arguments to function"); 
			}
		} catch (Exception e) { throw new PlaidIllegalOperationException("A java exception was thrown", e); }
	}
	public PlaidObject plus$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		try {
			return Util.string(((String)parsedReciever)+((String)parsedArg1));
	
		} catch (ClassCastException e) { 
			throw new PlaidIllegalOperationException("A invalid operation has been attempted.",e); 
		}
	}
	public PlaidObject getB_ytes(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.javaToPlaid(((String)parsedReciever).getBytes());
	}
	public PlaidObject getB_ytes(PlaidObject receiver, PlaidObject arg0) {
		String parsedReciever = (String)((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		try {
			if (parsedArg1 instanceof String) {
				return Util.javaToPlaid(parsedReciever.getBytes((String)parsedArg1));
			}
			else if(parsedArg1 instanceof Charset) {
				return Util.javaToPlaid(parsedReciever.getBytes((Charset)parsedArg1));
			}
			else {
					throw new PlaidIllegalOperationException("Invalid arguments to function"); 
			}
		} catch (Exception e) { throw new PlaidIllegalOperationException("A java exception was thrown", e); }
	}
	public PlaidObject codeP_ointC_ount(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		return Util.integer(((String)parsedReciever).codePointCount(((Integer)parsedArg1), ((Integer)parsedArg2)));
	}
	public PlaidObject toL_owerC_ase(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.string(((String)parsedReciever).toLowerCase());
	}
	public PlaidObject length(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.integer(((String)parsedReciever).length());
	}
	public PlaidObject toL_owerC_ase(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.string(((String)parsedReciever).toLowerCase(((Locale)parsedArg1)));
	}
	public PlaidObject bangeq$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		try {
			return Util.bool(((String)parsedReciever)!=((String)parsedArg1));
	
		} catch (ClassCastException e) { 
			throw new PlaidIllegalOperationException("A invalid operation has been attempted.",e); 
		}
	}
	public PlaidObject notify(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		((Object)parsedReciever).notify();
		return Util.unit();
	}
	public PlaidObject concat(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.string(((String)parsedReciever).concat(((String)parsedArg1)));
	}
	public PlaidObject split(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		return Util.javaToPlaid(((String)parsedReciever).split(((String)parsedArg1), ((Integer)parsedArg2)));
	}
	public PlaidObject split(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.javaToPlaid(((String)parsedReciever).split(((String)parsedArg1)));
	}
	public PlaidObject compareT_oI_gnoreC_ase(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.integer(((String)parsedReciever).compareToIgnoreCase(((String)parsedArg1)));
	}
	public PlaidObject hashC_ode(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.integer(((String)parsedReciever).hashCode());
	}
	public PlaidObject equals(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.bool(((String)parsedReciever).equals(((Object)parsedArg1)));
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
	public PlaidObject endsW_ith(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.bool(((String)parsedReciever).endsWith(((String)parsedArg1)));
	}
	public PlaidObject getC_lass(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.javaToPlaid(((Object)parsedReciever).getClass());
	}
	public PlaidObject trim(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.string(((String)parsedReciever).trim());
	}
	public PlaidObject lastI_ndexO_f(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1) {
		String parsedReciever = (String)((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		try {
			if (parsedArg1 instanceof Integer && parsedArg2 instanceof Integer) {
				return Util.integer(parsedReciever.lastIndexOf((Integer)parsedArg1, (Integer)parsedArg2));
			}
			else if(parsedArg1 instanceof String && parsedArg2 instanceof Integer) {
				return Util.integer(parsedReciever.lastIndexOf((String)parsedArg1, (Integer)parsedArg2));
			}
			else {
					throw new PlaidIllegalOperationException("Invalid arguments to function"); 
			}
		} catch (Exception e) { throw new PlaidIllegalOperationException("A java exception was thrown", e); }
	}
	public PlaidObject lastI_ndexO_f(PlaidObject receiver, PlaidObject arg0) {
		String parsedReciever = (String)((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		try {
			if (parsedArg1 instanceof String) {
				return Util.integer(parsedReciever.lastIndexOf((String)parsedArg1));
			}
			else if(parsedArg1 instanceof Integer) {
				return Util.integer(parsedReciever.lastIndexOf((Integer)parsedArg1));
			}
			else {
					throw new PlaidIllegalOperationException("Invalid arguments to function"); 
			}
		} catch (Exception e) { throw new PlaidIllegalOperationException("A java exception was thrown", e); }
	}
	public PlaidObject regionM_atches(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1, PlaidObject arg2, PlaidObject arg3) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		Object parsedArg3 = ((PlaidJavaObject) arg2).getJavaObject();
		Object parsedArg4 = ((PlaidJavaObject) arg3).getJavaObject();
		return Util.bool(((String)parsedReciever).regionMatches(((Integer)parsedArg1), ((String)parsedArg2), ((Integer)parsedArg3), ((Integer)parsedArg4)));
	}
	public PlaidObject regionM_atches(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1, PlaidObject arg2, PlaidObject arg3, PlaidObject arg4) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		Object parsedArg3 = ((PlaidJavaObject) arg2).getJavaObject();
		Object parsedArg4 = ((PlaidJavaObject) arg3).getJavaObject();
		Object parsedArg5 = ((PlaidJavaObject) arg4).getJavaObject();
		return Util.bool(((String)parsedReciever).regionMatches(((Boolean)parsedArg1), ((Integer)parsedArg2), ((String)parsedArg3), ((Integer)parsedArg4), ((Integer)parsedArg5)));
	}
	public PlaidObject charA_t(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.string("" + ((String)parsedReciever).charAt(((Integer)parsedArg1)));
	}
	public PlaidObject codeP_ointB_efore(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.integer(((String)parsedReciever).codePointBefore(((Integer)parsedArg1)));
	}
	public PlaidObject codeP_ointA_t(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.integer(((String)parsedReciever).codePointAt(((Integer)parsedArg1)));
	}
	public PlaidObject notifyA_ll(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		((Object)parsedReciever).notifyAll();
		return Util.unit();
	}
	public PlaidObject indexO_f(PlaidObject receiver, PlaidObject arg0) {
		String parsedReciever = (String)((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		try {
			if (parsedArg1 instanceof Integer) {
				return Util.integer(parsedReciever.indexOf((Integer)parsedArg1));
			}
			else if(parsedArg1 instanceof String) {
				return Util.integer(parsedReciever.indexOf((String)parsedArg1));
			}
			else {
					throw new PlaidIllegalOperationException("Invalid arguments to function"); 
			}
		} catch (Exception e) { throw new PlaidIllegalOperationException("A java exception was thrown", e); }
	}
	public PlaidObject indexO_f(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1) {
		String parsedReciever = (String)((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		try {
			if (parsedArg1 instanceof Integer && parsedArg2 instanceof Integer) {
				return Util.integer(parsedReciever.indexOf((Integer)parsedArg1, (Integer)parsedArg2));
			}
			else if(parsedArg1 instanceof String && parsedArg2 instanceof Integer) {
				return Util.integer(parsedReciever.indexOf((String)parsedArg1, (Integer)parsedArg2));
			}
			else {
					throw new PlaidIllegalOperationException("Invalid arguments to function"); 
			}
		} catch (Exception e) { throw new PlaidIllegalOperationException("A java exception was thrown", e); }
	}
	public PlaidObject startsW_ith(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.bool(((String)parsedReciever).startsWith(((String)parsedArg1)));
	}
	public PlaidObject startsW_ith(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		return Util.bool(((String)parsedReciever).startsWith(((String)parsedArg1), ((Integer)parsedArg2)));
	}
	public PlaidObject matches(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.bool(((String)parsedReciever).matches(((String)parsedArg1)));
	}
	public PlaidObject toU_pperC_ase(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		return Util.string(((String)parsedReciever).toUpperCase(((Locale)parsedArg1)));
	}
	public PlaidObject toU_pperC_ase(PlaidObject receiver) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		return Util.string(((String)parsedReciever).toUpperCase());
	}
	public PlaidObject replaceA_ll(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		return Util.string(((String)parsedReciever).replaceAll(((String)parsedArg1), ((String)parsedArg2)));
	}
	public PlaidObject getC_hars(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1, PlaidObject arg2, PlaidObject arg3) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		Object parsedArg3 = ((PlaidJavaObject) arg2).getJavaObject();
		Object parsedArg4 = ((PlaidJavaObject) arg3).getJavaObject();
		((String)parsedReciever).getChars(((Integer)parsedArg1), ((Integer)parsedArg2), ((char[])parsedArg3), ((Integer)parsedArg4));
		return Util.unit();
	}
	public PlaidObject eqeq$plaid(PlaidObject receiver, PlaidObject arg0) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		try {
			return Util.bool(((String)parsedReciever).equals(((String)parsedArg1)));
	
		} catch (ClassCastException e) { 
			throw new PlaidIllegalOperationException("A invalid operation has been attempted.",e); 
		}
	}
	public PlaidObject replace(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1) {
		String parsedReciever = (String)((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		try {
			if (parsedArg1 instanceof CharSequence && parsedArg2 instanceof CharSequence) {
				return Util.string(parsedReciever.replace((CharSequence)parsedArg1, (CharSequence)parsedArg2));
			}
			else if(parsedArg1 instanceof Character && parsedArg2 instanceof Character) {
				return Util.string(parsedReciever.replace((Character)parsedArg1, (Character)parsedArg2));
			}
			else {
					throw new PlaidIllegalOperationException("Invalid arguments to function"); 
			}
		} catch (Exception e) { throw new PlaidIllegalOperationException("A java exception was thrown", e); }
	}
	public PlaidObject replaceF_irst(PlaidObject receiver, PlaidObject arg0, PlaidObject arg1) {
		Object parsedReciever = ((PlaidJavaObject) receiver).getJavaObject();
		Object parsedArg1 = ((PlaidJavaObject) arg0).getJavaObject();
		Object parsedArg2 = ((PlaidJavaObject) arg1).getJavaObject();
		return Util.string(((String)parsedReciever).replaceFirst(((String)parsedArg1), ((String)parsedArg2)));
	}
}
