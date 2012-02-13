package plaid.lang;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalArgumentException;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.generated.Ieqeq$plaid$1$plaid;
import plaid.generated.Igteq$plaid$1$plaid;
import plaid.generated.Imult$plaid$1$plaid;
import plaid.generated.Iplus$plaid$1$plaid;
import plaid.generated.Isub$plaid$1$plaid;

public class Integer$plaid extends AbstractPlaidState
						   implements Iplus$plaid$1$plaid,
						   			  Ieqeq$plaid$1$plaid,
						   			  Imult$plaid$1$plaid,
						   			  Isub$plaid$1$plaid,
						   			  Igteq$plaid$1$plaid {

	public Integer$plaid() {
		super(null);
	}

	@Override
	public PlaidObject gteq$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.Integer first = ((java.lang.Integer) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.Integer second = ((java.lang.Integer) ((PlaidJavaObject) arg).getJavaObject());
			boolean b = first.intValue() >= second.intValue();
			return Util.JAVA_GEN.createPlaidJavaObject(b);
			
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
			return Util.JAVA_GEN.createPlaidJavaObject(result);
			
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
			return Util.JAVA_GEN.createPlaidJavaObject(result);
			
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
			return Util.JAVA_GEN.createPlaidJavaObject(b);
			
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
			return Util.JAVA_GEN.createPlaidJavaObject(result);
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("+ failed", e.getCause());
		}
	}

}
