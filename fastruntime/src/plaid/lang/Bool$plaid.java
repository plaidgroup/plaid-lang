package plaid.lang;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalArgumentException;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.generated.Iampamp$plaid$1$plaid;

public class Bool$plaid extends AbstractPlaidState 
						   implements Iampamp$plaid$1$plaid {
	
//	public static class PlaidBoolean { }
//	public static class True extends PlaidBoolean { }
//	public static class False extends PlaidBoolean { }
//	
//	public static final PlaidBoolean TRUE = new True();
//	public static final PlaidBoolean FALSE = new False();
//	
//	public static PlaidBoolean getPlaidBoolean(boolean b) {
//		if (b) return TRUE;
//		else return FALSE;
//	}
//	
//	public static class BooleanObjectValue extends SingleValue {
//
//		@Override
//		public List<MethodInfo> getMethods() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public PlaidStorage getDefaultStorage() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public Set<String> getTags() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public Set<String> getOuterTags() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public Set<String> getInnerTags() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		
//	}
	
	public Bool$plaid(ObjectValue metadata) {
		super(metadata);
	}

	@Override
	public PlaidObject ampamp$plaid(PlaidObject receiver, PlaidObject arg) {
		try {
			Boolean first = ((Boolean) ((PlaidJavaObject) receiver).getJavaObject());
			Boolean second = ((Boolean) ((PlaidJavaObject) arg).getJavaObject());
			boolean b = first.booleanValue() && second.booleanValue();
			return Util.JAVA_GEN.createPlaidJavaObject(b); //getPlaidBoolean(b));
			
		} catch (ClassCastException e) {
			throw new PlaidIllegalArgumentException("startsWith failed", e.getCause());
		}
	}
	


}
