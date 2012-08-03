package plaid.collections;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalArgumentException;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.reference.AbstractPlaidDispatch;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.DimensionValue;
import plaid.generated.Iget$1$plaid;
import plaid.generated.Iset$2$plaid;
import plaid.generated.Ilength$0$plaid;
import plaid.generated.Ishift$3$plaid;

public class A_rray extends AbstractPlaidDispatch implements Iset$2$plaid, Iget$1$plaid, Ilength$0$plaid, Ishift$3$plaid {
	
	private A_rray(ObjectValue metadata) {
		super(metadata); 	
	}
	
	public static final plaid.fastruntime.PlaidState theState$plaid;
	
	static {
		theState$plaid = new AbstractPlaidState(new A_rray(new DimensionValue("plaid/collections/Array", null, null))) {
			@Override
			public PlaidObject instantiate() {
				throw new PlaidIllegalOperationException("Cannot instantiate Array.");
			}
		};
	}
	
	public static PlaidObject plaidArray(int size) {
		return new ArrayPlaidJavaObject(size);
	}
	
	@Override
	public PlaidObject get(PlaidObject receiver, PlaidObject index) {
		try {
			ArrayPlaidJavaObject a = (ArrayPlaidJavaObject)receiver;
			int intIndex = ((Integer)((PlaidJavaObject)index).getJavaObject()).intValue();
			return a.javaArray[intIndex];
		} catch(ClassCastException e){
			throw new PlaidIllegalArgumentException("get failed", e.getCause());
		}
	}

	@Override
	public PlaidObject set(PlaidObject receiver, PlaidObject index,
			PlaidObject value) {
		try {
			ArrayPlaidJavaObject a = (ArrayPlaidJavaObject)receiver;
			int intIndex = ((Integer)((PlaidJavaObject)index).getJavaObject()).intValue();
			a.javaArray[intIndex] = value;
			return Util.unit();
		} catch(ClassCastException e){
			throw new PlaidIllegalArgumentException("set failed", e.getCause());
		}
	}
	
	@Override
	public PlaidObject length(PlaidObject receiver) {
		try {
			ArrayPlaidJavaObject a = (ArrayPlaidJavaObject)receiver;
			return Util.javaToPlaid(a.javaArray.length);
		} catch(ClassCastException e){
			throw new PlaidIllegalArgumentException("length failed", e.getCause());
		}
	}
	



	@Override
	public PlaidObject shift(PlaidObject receiver, PlaidObject start,
			PlaidObject end, PlaidObject length) {
		try {
			ArrayPlaidJavaObject a = (ArrayPlaidJavaObject)receiver;
			int startIndex = ((Integer)((PlaidJavaObject)start).getJavaObject()).intValue();
			int endIndex = ((Integer)((PlaidJavaObject)end).getJavaObject()).intValue();
			int ilength = ((Integer)((PlaidJavaObject)length).getJavaObject()).intValue();
			System.arraycopy(a.javaArray, startIndex, a.javaArray, endIndex, ilength);
			return Util.unit();
		} catch(ClassCastException e){
			throw new PlaidIllegalArgumentException("shift failed", e.getCause());
		}
	}


	
	private final static class ArrayPlaidJavaObject implements PlaidJavaObject {

		public final PlaidObject[] javaArray; 
		protected ArrayPlaidJavaObject(int size) {
			javaArray = new PlaidObject[size];
		}
		@Override
		public PlaidDispatch getDispatch() {
			return A_rray.theState$plaid.getDispatch();
		}
		
		@Override
		public void changeState(PlaidState s) {
			throw new PlaidIllegalOperationException("Cannot change the state of a Java Object.");
		}

		@Override
		public PlaidObject[] getStorage() {
			throw new PlaidIllegalOperationException("No storage object for a Java Object.");
		}

		@Override
		public Object getJavaObject() {
			return this.javaArray;
		}

		@Override
		public boolean canBePrimitive(JavaPrimitive p) {
			return false;
		}

		@Override
		public Object asPrimitive(JavaPrimitive p) {
			throw new PlaidIllegalOperationException("Cannot return primitive version of Array.");
		}
	}
}
