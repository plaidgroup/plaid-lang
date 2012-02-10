package plaid.lang;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalArgumentException;
import plaid.fastruntime.errors.PlaidInternalException;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.generated.Iplus$plaid$1$plaid;
import plaid.generated.ItoString$0$plaid;

public class Integer32$plaid extends AbstractPlaidState implements Iplus$plaid$1$plaid, ItoString$0$plaid
{
	public Integer32$plaid() {
		super(null);
	}

	public PlaidObject plus$plaid(PlaidObject receiver, PlaidObject other) {
		try {
			Integer first = ((Integer) ((PlaidJavaObject) receiver).getJavaObject());
			Integer second = ((Integer) ((PlaidJavaObject) other).getJavaObject());
			Integer result = first.intValue() + second.intValue();
			return Util.integer(result);
			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("integer addition failed", e);
		}
	}

	@Override
	public PlaidObject toString(PlaidObject receiver) {
		try {
			Integer first = ((Integer) ((PlaidJavaObject) receiver).getJavaObject());
			return Util.string(first.toString());
		} catch (Exception e) {
			throw new PlaidInternalException("integer string conversion failed", e);
		}
	}
}
