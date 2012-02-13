package plaid.lang;

import java.io.PrintStream;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalArgumentException;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.generated.Iprintln$1$plaid;

public class DPrintStream$plaid extends AbstractPlaidState implements Iprintln$1$plaid {

	public DPrintStream$plaid(ObjectValue metadata) {
		super(metadata);
	}

	@Override
	public PlaidObject println(PlaidObject thisVar, PlaidObject other) {
		try {
			PrintStream ps = ((PrintStream) ((PlaidJavaObject) thisVar).getJavaObject());
			String second = ((PlaidJavaObject) other).getJavaObject().toString();
			ps.println(second);
			return Util.unit();
			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("string printing failed", e.getCause());
		}
	}
	
	

}
