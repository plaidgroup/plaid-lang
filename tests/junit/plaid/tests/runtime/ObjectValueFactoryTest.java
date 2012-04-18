package plaid.tests.runtime;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.reference.*;
public class ObjectValueFactoryTest {
	@Test
	public void addTwoMethodsToTaggedState() {
		ObjectValueFactory ovf = new ObjectValueFactory();
		ovf.tag("testTag");
		ovf.mthd("foo", 0, "plaid/lang/Foo");
		ovf.mthd("bar", 0, "plaid/lang/Baz");
		ObjectValue expected = ovf.value();
		ObjectValue actual = 
				new DimensionValue("testTag", 
						new ListValue(
								MethodValue.createMethodWithStaticDefinition("foo", 0, "plaid/lang/Foo"),
								MethodValue.createMethodWithStaticDefinition("bar", 0, "plaid/lang/Baz")), 
						null);
		Assert.assertEquals(expected, actual);
		
	}
}
