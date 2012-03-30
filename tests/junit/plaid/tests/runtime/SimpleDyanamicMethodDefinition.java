package plaid.tests.runtime;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.Util;
import plaid.fastruntime.reference.ListValue;
import plaid.fastruntime.reference.MemberDefValue;
import plaid.fastruntime.reference.MethodValue;
import plaid.generated.Iadd$1$plaid;


public class SimpleDyanamicMethodDefinition {

	@Test
	public void test() {
		MethodValue mv = MethodValue.createMethodWithDynamicDefinition("add", 1, "A");
		MemberDefValue mdv = new MemberDefValue("A");
		ListValue lv = new ListValue(mv,mdv);
		PlaidState testState = Util.makeState(lv);
		PlaidObject testObject = testState.instantiate();
		
		Assert.assertEquals(((PlaidJavaObject)Util.string("hoozah!")).getJavaObject(), 
				((PlaidJavaObject)((Iadd$1$plaid)testObject.getDispatch()).add(testObject,null)).getJavaObject());
	}
	
	private class Adder implements Iadd$1$plaid{
		@Override
		public PlaidObject add(PlaidObject x, PlaidObject y) {
			return plaid.fastruntime.Util.string("hoozah!");
		}
	}
}
