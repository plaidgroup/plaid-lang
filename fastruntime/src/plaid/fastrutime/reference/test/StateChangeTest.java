package plaid.fastrutime.reference.test;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.ObjectValue;
import plaid.fastrutime.reference.DimensionValue;
import plaid.fastrutime.reference.ListValue;
import plaid.fastrutime.reference.MethodValue;


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
			new MethodValue("foo", 0, null);
		ObjectValue result = target.changeState(update);
		ObjectValue goal = 
				new ListValue(new DimensionValue("A", null, null), new MethodValue("foo", 0, null));
		Assert.assertTrue(result.equals(goal));
	}
	
	@Test
	public void List() {
		ObjectValue target = 
					new DimensionValue("A", null, null);
		ObjectValue update = new ListValue(new MethodValue("foo", 0, null), new DimensionValue("B", null, null));
		ObjectValue result = target.changeState(update);
		ObjectValue goal = 
			new ListValue(new DimensionValue("A", null, null), new MethodValue("foo", 0, null), new DimensionValue("B", null, null));
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
			new DimensionValue("A", null, new DimensionValue("C", new MethodValue("foo", 0, null), null));
		ObjectValue update = 
				new DimensionValue("B", null, new DimensionValue("C", new MethodValue("bar", 0, null), null));
		ObjectValue result = target.changeState(update);
		ObjectValue goal = 
			new DimensionValue("B", null, new DimensionValue("C", new MethodValue("foo", 0, null), null));
		Assert.assertTrue(result.equals(goal));
	}

	@Test
	public void UniqueTags() {
		ObjectValue target = 
			new DimensionValue("A", null, new DimensionValue("C", new MethodValue("foo", 0, null), null));
		ObjectValue update = 
				new DimensionValue("B", new DimensionValue("B", null, null), new DimensionValue("C", null, null));
		ObjectValue result = target.changeState(update);
		ObjectValue goal = 
			new DimensionValue("B", null, new DimensionValue("C", new MethodValue("foo", 0, null), null));
		Assert.assertTrue(result.equals(goal));
	}
	
	@Test
	public void testListToString() {
		ListValue lv = new ListValue(new MethodValue("foo", 0, null), new MethodValue("bar", 0, null));
		Assert.assertTrue("<method foo,method bar>".equals(lv.toString()));
	}
	
	@Test
	public void testDimensionToString() {
		DimensionValue dv = new DimensionValue("B", 
				new MethodValue("foo", 0, null), 
				new DimensionValue("A", null, null));
		Assert.assertTrue("B{method foo}<:A".equals(dv.toString()));
		dv = new DimensionValue("B", 
				null, 
				new DimensionValue("A", null, null));
		Assert.assertTrue("B<:A".equals(dv.toString()));
		dv = new DimensionValue("B", 
				new MethodValue("foo", 0, null), 
				null);
		Assert.assertTrue("B{method foo}".equals(dv.toString()));
		dv = new DimensionValue("B", 
				null, 
				null);
		Assert.assertTrue("B".equals(dv.toString()));
	}
	
}
