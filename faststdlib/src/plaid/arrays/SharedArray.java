package plaid.arrays;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.aeminium.Datagroup;
import plaid.fastruntime.errors.PlaidIllegalArgumentException;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.reference.AbstractPlaidDispatch;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.DimensionValue;
import plaid.generated.IsetUnique$plaid$2$plaid;
import plaid.lang.Integer32;


public class SharedArray extends AbstractPlaidDispatch implements IsetUnique$plaid$2$plaid {
	public static final plaid.fastruntime.PlaidDispatch SHARED_ARRAY_DISPATCH;
	public static final plaid.fastruntime.PlaidState theState$plaid;
	static {
		theState$plaid = new AbstractPlaidState(new SharedArray(new DimensionValue("plaid/arrays/SharedArray", null, null))) {
			@Override
			public PlaidObject instantiate() {
				throw new PlaidIllegalOperationException("Cannot instantiate SharedArray state");
			}
		};
		SHARED_ARRAY_DISPATCH = theState$plaid.getDispatch();
	}
	
	public SharedArray(ObjectValue metadata) {
		super(metadata);
	}

	public static final int toInt(PlaidObject obj) {
		if ( obj instanceof Integer32.Integer32PlaidJavaObject ) {
			return ((Integer32.Integer32PlaidJavaObject)obj).integerValue;
		} else {
			throw new PlaidIllegalArgumentException("Cannot convert non Integer to int");
		}
	}
	
	@Override
	public PlaidObject setUnique(PlaidObject thisVar, PlaidObject indexVar, PlaidObject objVar) {
		if ( thisVar instanceof SharedArrayPlaidJavaObject ) {
			final SharedArrayPlaidJavaObject sa = (SharedArrayPlaidJavaObject)thisVar;
			final int index = toInt(indexVar);
			if ( sa.accessOk(index)) {
				sa.data[index] = objVar;
			} else {
				throw new PlaidIllegalArgumentException("Index out of bound: " + index);
			}
		} else {
			throw new PlaidIllegalArgumentException("Receiver to SharedArray is not a shard array.");
		}
		return null;
	}	
	
	public static final class SharedArrayPlaidJavaObject implements PlaidObject {
		public final PlaidObject data[];
		public final Datagroup groups[];
		
		public SharedArrayPlaidJavaObject(final int bucketCount) {
			data = new PlaidObject[bucketCount];
			groups = new plaid.fastruntime.aeminium.Datagroup[bucketCount];
			// initialize data groups
			for (int i = 0; i < groups.length; i++) {
				groups[i] = new Datagroup();
			}
		}
		
		public final boolean accessOk(int index) {
			return index > 0 && index < data.length ;
		}
		
		@Override
		public PlaidDispatch getDispatch() {
			return SHARED_ARRAY_DISPATCH;
		}

		@Override
		public PlaidObject[] getStorage() {
			throw new PlaidIllegalOperationException("SharedArrays do not allow access of internal data.");
		}

		@Override
		public void changeState(PlaidState s) {
			throw new PlaidIllegalOperationException("SharedArrays do not support state change.");
		}
		
	}


}
