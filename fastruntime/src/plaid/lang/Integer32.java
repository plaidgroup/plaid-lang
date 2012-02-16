package plaid.lang;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalArgumentException;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.DimensionValue;
import plaid.fastruntime.reference.SimplePlaidJavaObject;
import plaid.generated.Ieqeq$plaid$1$plaid;
import plaid.generated.Igteq$plaid$1$plaid;
import plaid.generated.Imult$plaid$1$plaid;
import plaid.generated.Iplus$plaid$1$plaid;
import plaid.generated.Isub$plaid$1$plaid;
import plaid.generated.ItoString$0$plaid;

public class Integer32 extends AbstractPlaidState
						   implements Iplus$plaid$1$plaid,
						   			  Ieqeq$plaid$1$plaid,
						   			  Imult$plaid$1$plaid,
						   			  Isub$plaid$1$plaid,
						   			  Igteq$plaid$1$plaid,
						   			  ItoString$0$plaid {

	public static final plaid.fastruntime.PlaidState theState$plaid;
	static {
	theState$plaid = new Integer32(new DimensionValue("plaid/lang/Integer", null, null));
	}
	
	public static PlaidObject plaidInteger(java.lang.Integer i) { return new SimplePlaidJavaObject(theState$plaid,null, i); }
	
	private Integer32(ObjectValue metadata) {
		super(metadata);
	}

	@Override
	public PlaidObject gteq$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.Integer first = ((java.lang.Integer) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Integer second = ((java.lang.Integer) ((PlaidJavaObject) arg).getJavaObject());
			boolean b = first.intValue() >= second.intValue();
			return Util.bool(b);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException(">= failed", e.getCause());
		}
	}

	@Override
	public PlaidObject sub$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.Integer first = ((java.lang.Integer) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Integer second = ((java.lang.Integer) ((PlaidJavaObject) arg).getJavaObject());
			int result = first.intValue() - second.intValue();
			return plaidInteger(result);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("- failed", e.getCause());
		}
	}

	@Override
	public PlaidObject mult$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.Integer first = ((java.lang.Integer) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Integer second = ((java.lang.Integer) ((PlaidJavaObject) arg).getJavaObject());
			int result = first.intValue() * second.intValue();
			return plaidInteger(result);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("* failed", e.getCause());
		}
	}

	@Override
	public PlaidObject eqeq$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.Integer first = ((java.lang.Integer) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Integer second = ((java.lang.Integer) ((PlaidJavaObject) arg).getJavaObject());
			boolean b = first.intValue() == second.intValue();
			return Util.bool(b);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("== failed", e.getCause());
		}
	}

	@Override
	public PlaidObject plus$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.Integer first = ((java.lang.Integer) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Integer second = ((java.lang.Integer) ((PlaidJavaObject) arg).getJavaObject());
			int result = first.intValue() + second.intValue();
			return plaidInteger(result);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("+ failed", e.getCause());
		}
	}

	@Override
	public PlaidObject toString(PlaidObject receiver) {
		try {
			java.lang.Integer first = ((java.lang.Integer) ((PlaidJavaObject) receiver).getJavaObject());
			return Util.string(first.toString());
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("+ failed", e.getCause());
		}
	}

}