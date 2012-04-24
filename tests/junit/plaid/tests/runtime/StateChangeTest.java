package plaid.tests.runtime;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.reference.DimensionValue;
import plaid.fastruntime.reference.ListValue;
import plaid.fastruntime.reference.MethodValue;


public class StateChangeTest {
	
	@Test
	public void addH() {
		ObjectValue result = new DimensionValue("A", null, null).changeState(new DimensionValue("B", null, null));
		ObjectValue goal = new ListValue(new DimensionValue("A", null, null), new DimensionValue("B", null, null));
		Assert.assertTrue(result.equals(goal));
	}
	
	@Test
	public void addChild() {
		ObjectValue result = new DimensionValue("A", null, null).changeState(new DimensionValue("B", null, new DimensionValue("A", null, null)));
		ObjectValue goal = new DimensionValue("B", null, new DimensionValue("A", null, null));
		Assert.assertTrue(result.equals(goal));
	}
	

	@Test
	public void matchSuperInner() {
		ObjectValue target = 
			new DimensionValue("B", null, 
					new DimensionValue("A", 
							new DimensionValue("C", null, new DimensionValue("D", null, null)), null));
		ObjectValue update = 
			new DimensionValue("E", null, new DimensionValue("D", null, null));
		ObjectValue result = target.changeState(update);
		ObjectValue goal = new DimensionValue("B", null, 
				new DimensionValue("A", 
						new DimensionValue("E", null, new DimensionValue("D", null, null)), null));
		Assert.assertTrue(result.equals(goal));
	}
	
	@Test
	public void matchInner() {
		ObjectValue target = 
			new DimensionValue("B", 
					new DimensionValue("C", null, new DimensionValue("D", null, null)), null);
		ObjectValue update = 
			new DimensionValue("E", null, new DimensionValue("D", null, null));
		ObjectValue result = target.changeState(update);
		ObjectValue goal = new DimensionValue("B", 
				new DimensionValue("E", null, new DimensionValue("D", null, null)), null);
		Assert.assertTrue(result.equals(goal));
	}
	
	@Test
	public void matchSuper() {
		ObjectValue target = 
					new DimensionValue("C", null, new DimensionValue("D", null, null));
		ObjectValue update = 
			new DimensionValue("E", null, new DimensionValue("D", null, null));
		ObjectValue result = target.changeState(update);
		ObjectValue goal = 
				new DimensionValue("E", null, new DimensionValue("D", null, null));
		Assert.assertTrue(result.equals(goal));
	}
	
	@Test
	public void MV() {
		ObjectValue target = 
					new DimensionValue("A", null, null);
		ObjectValue update = 
				MethodValue.createMethodWithStaticDefinition("foo", 0, null);
		ObjectValue result = target.changeState(update);
		ObjectValue goal = 
				new ListValue(new DimensionValue("A", null, null), MethodValue.createMethodWithStaticDefinition("foo", 0, null));
		Assert.assertTrue(result.equals(goal));
	}
	
	@Test
	public void List() {
		ObjectValue target = 
					new DimensionValue("A", null, null);
		ObjectValue update = new ListValue(MethodValue.createMethodWithStaticDefinition("foo", 0, null), new DimensionValue("B", null, null));
		ObjectValue result = target.changeState(update);
		ObjectValue goal = 
			new ListValue(new DimensionValue("A", null, null), MethodValue.createMethodWithStaticDefinition("foo", 0, null), new DimensionValue("B", null, null));
		Assert.assertTrue(result.equals(goal));
	}
	
	@Test
	public void MatchDim() {
		ObjectValue target = 
			new ListValue(new DimensionValue("A", null, new DimensionValue("C", null, null)), 
					new DimensionValue("D", null, new DimensionValue("F", null, null)));
		ObjectValue update = new ListValue(new DimensionValue("B", null, new DimensionValue("C", null, null)), 
				new DimensionValue("E", null, new DimensionValue("F", null, null)));
		ObjectValue result = target.changeState(update);
		ObjectValue goal = 
			new ListValue(new DimensionValue("B", null, new DimensionValue("C", null, null)), 
					new DimensionValue("E", null, new DimensionValue("F", null, null)));
		Assert.assertTrue(result.equals(goal));
	}
	
	@Test
	public void MatchDimMisorder() {
		ObjectValue target = 
			new ListValue(new DimensionValue("A", null, new DimensionValue("C", null, null)), 
					new DimensionValue("D", null, new DimensionValue("F", null, null)));
		ObjectValue update = new ListValue( 
				new DimensionValue("E", null, new DimensionValue("F", null, null)),
				new DimensionValue("B", null, new DimensionValue("C", null, null)));
		ObjectValue result = target.changeState(update);
		ObjectValue goal = 
			new ListValue(new DimensionValue("B", null, new DimensionValue("C", null, null)), 
					new DimensionValue("E", null, new DimensionValue("F", null, null)));
		Assert.assertTrue(result.equals(goal));
	}
	
	@Test
	public void MaintainOriginalParent() {
		ObjectValue target = 
			new DimensionValue("A", null, new DimensionValue("C", MethodValue.createMethodWithStaticDefinition("foo", 0, null), null));
		ObjectValue update = 
				new DimensionValue("B", null, new DimensionValue("C", MethodValue.createMethodWithStaticDefinition("bar", 0, null), null));
		ObjectValue result = target.changeState(update);
		ObjectValue goal = 
			new DimensionValue("B", null, new DimensionValue("C", MethodValue.createMethodWithStaticDefinition("foo", 0, null), null));
		Assert.assertTrue(result.equals(goal));
	}

	@Test
	public void UniqueTags() {
		ObjectValue target = 
			new DimensionValue("A", null, new DimensionValue("C", MethodValue.createMethodWithStaticDefinition("foo", 0, null), null));
		ObjectValue update = 
				new DimensionValue("B", new DimensionValue("B", null, null), new DimensionValue("C", null, null));
		ObjectValue result = target.changeState(update);
		ObjectValue goal = 
			new DimensionValue("B", new DimensionValue("B", null, null), new DimensionValue("C", MethodValue.createMethodWithStaticDefinition("foo", 0, null), null));
		Assert.assertEquals(goal, result);
	}
	
}
