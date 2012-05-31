package plaid.lang;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalArgumentException;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.reference.AbstractPlaidDispatch;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.DimensionValue;
import plaid.fastruntime.reference.SimplePlaidJavaObject;
import plaid.generated.IcharAt$1$plaid;
import plaid.generated.IendsWith$1$plaid;
import plaid.generated.Ieqeq$plaid$1$plaid;
import plaid.generated.IindexOf$1$plaid;
import plaid.generated.IlastIndexOf$1$plaid;
import plaid.generated.Ilength$0$plaid;
import plaid.generated.Iplus$plaid$1$plaid;
import plaid.generated.Ireplace$2$plaid;
import plaid.generated.IstartsWith$1$plaid;
import plaid.generated.Isubstring$2$plaid;
import plaid.generated.ItoLowerCase$0$plaid;
import plaid.generated.ItoString$0$plaid;

public class String extends AbstractPlaidDispatch
						  implements Iplus$plaid$1$plaid,
						  			 Ieqeq$plaid$1$plaid,
						  			 IstartsWith$1$plaid,
						  			 IendsWith$1$plaid,
						  			 ItoLowerCase$0$plaid,
						  			 Isubstring$2$plaid,
						  			 Ilength$0$plaid,
						  			 IcharAt$1$plaid,
						  			 IindexOf$1$plaid,
						  			 IlastIndexOf$1$plaid,
						  			 Ireplace$2$plaid,
						  			 ItoString$0$plaid
{

	public static final plaid.fastruntime.PlaidState theState$plaid;
	static {
		theState$plaid = new AbstractPlaidState(new String(new DimensionValue("plaid/lang/String", null, null))) {
			@Override
			public PlaidObject instantiate() {
				throw new PlaidIllegalOperationException("Cannot instantiate String state");
			}
		};
	}
	
	public static PlaidObject plaidString(java.lang.String s) { 
		return new SimplePlaidJavaObject(theState$plaid.getDispatch(),null, s); 
	}
	
	private String(ObjectValue metadata) {
		super(metadata);
	}


	public PlaidObject plus$plaid(PlaidObject receiver, PlaidObject other) {
		try {
			java.lang.String first = ((java.lang.String) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.String second = ((java.lang.String) ((PlaidJavaObject) other).getJavaObject());
			java.lang.String concat = first + second;
			return plaidString(concat);			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("String concatenation failed", e.getCause());
		}
	}

	@Override
	public PlaidObject substring(PlaidObject receiver, PlaidObject arg1,
			PlaidObject arg2) {
		try {
			java.lang.String first = ((java.lang.String) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Integer second = ((java.lang.Integer) ((PlaidJavaObject) arg1).getJavaObject());
			java.lang.Integer third = ((java.lang.Integer) ((PlaidJavaObject) arg2).getJavaObject());
			java.lang.String sub = first.substring(second,third);
			return plaidString(sub);
			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("substring failed", e.getCause());
		}
	}

	@Override
	public PlaidObject toLowerCase(PlaidObject receiver) {
		try {
			java.lang.String first = ((java.lang.String) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.String lower = first.toLowerCase();
			return plaidString(lower);
			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("toLowerCase failed", e.getCause());
		}
	}
	
	@Override
	public PlaidObject length(PlaidObject receiver) {
		try {
			java.lang.String first = ((java.lang.String) ((PlaidJavaObject) receiver).getJavaObject());
			int length = first.length();
			return Util.integer(length);
			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("toLowerCase failed", e.getCause());
		}
	}

	@Override
	public PlaidObject endsWith(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.String first = ((java.lang.String) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.String second = ((java.lang.String) ((PlaidJavaObject) arg).getJavaObject());
			boolean b = first.endsWith(second);
			return Util.bool(b);
			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("endsWith failed", e.getCause());
		}
	}

	@Override
	public PlaidObject startsWith(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.String first = ((java.lang.String) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.String second = ((java.lang.String) ((PlaidJavaObject) arg).getJavaObject());
			boolean b = first.startsWith(second);
			return Util.bool(b);
			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("startsWith failed", e.getCause());
		}
	}

	@Override
	public PlaidObject eqeq$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.String first = ((java.lang.String) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.String second = ((java.lang.String) ((PlaidJavaObject) arg).getJavaObject());
			boolean b = first.equals(second);
			return Util.bool(b);
			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("String equality failed", e.getCause());
		}
	}
	
	@Override
	public PlaidObject charAt(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.String first = ((java.lang.String) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Integer second = ((java.lang.Integer) ((PlaidJavaObject) arg).getJavaObject());
			char c = first.charAt(second);
			return Util.string("" + c);
			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("String charAt failed", e.getCause());
		}
	}

	@Override
	public PlaidObject indexOf(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.String first = ((java.lang.String) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.String second = ((java.lang.String) ((PlaidJavaObject) arg).getJavaObject());
			int result = first.indexOf(second);
			return Util.integer(result);
			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("String index of failed", e.getCause());
		}
	}
	
	@Override
	public PlaidObject replace(PlaidObject receiver, PlaidObject arg1,
			PlaidObject arg2) {
		try {
			java.lang.String first = ((java.lang.String) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.String second = ((java.lang.String) ((PlaidJavaObject) arg1).getJavaObject());
			java.lang.String third = ((java.lang.String) ((PlaidJavaObject) arg2).getJavaObject());
			java.lang.String result = first.replace(second,third);
			return Util.string(result);
			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("String equality failed", e.getCause());
		}
	}

	@Override
	public PlaidObject lastIndexOf(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.String first = ((java.lang.String) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.String second = ((java.lang.String) ((PlaidJavaObject) arg).getJavaObject());
			int result = first.lastIndexOf(second);
			return Util.integer(result);
			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("String last index of failed", e.getCause());
		}
	}

	@Override
	public PlaidObject toString(PlaidObject x) {
		return x;
	}
}
