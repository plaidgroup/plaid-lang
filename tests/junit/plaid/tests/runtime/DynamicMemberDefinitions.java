package plaid.tests.runtime;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidLambda;
import plaid.fastruntime.PlaidLambda$0;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.Util;
import plaid.fastruntime.reference.AbstractPlaidDispatch;
import plaid.fastruntime.reference.ObjectValueFactory;
import plaid.fastruntime.reference.SimplePlaidState;

public class DynamicMemberDefinitions {

	@Test
	public void testObjectValueDefaultStorage() {
		ObjectValueFactory ovf = new ObjectValueFactory();
		ovf.dynMthd("foo", 0);
		PlaidDispatch fooD = new FooDispatch(ovf.value());
		Map<String, PlaidLambda> memberDefinitions = new HashMap<String,PlaidLambda>();
		memberDefinitions.put("foo", new PlaidLambda$0() {
			public PlaidObject invoke$plaid() {
				return Util.string("hello");
			}
		});
		PlaidState ps = SimplePlaidState.makeDynamicallyDefinedState(fooD, memberDefinitions);
		PlaidObject myFoo = ps.instantiate();
		PlaidObject actual = ((plaid.generated.Ifoo$0$plaid)myFoo.getDispatch()).foo(myFoo);
		Assert.assertEquals(Util.string("hello"), actual);
	}
	
	private final class FooDispatch extends AbstractPlaidDispatch implements plaid.generated.Ifoo$0$plaid {

		public FooDispatch(ObjectValue metadata) {
			super(metadata);
		}
		
		public PlaidObject getfoo(PlaidObject thisVar) {
			return thisVar.getStorage()[0];
		}
		
		public PlaidObject foo(PlaidObject thisVar) {
			return ((plaid.fastruntime.PlaidLambda$0)getfoo(thisVar)).invoke$plaid();
		}
	}
}
