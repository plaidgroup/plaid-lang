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
import plaid.generated.IdoExclusive$2$plaid;
import plaid.generated.IdoExclusiveData1$3$plaid;
import plaid.generated.IdoExclusiveData2$4$plaid;
import plaid.generated.IdoLocalShared$2$plaid;
import plaid.generated.IdoLocalSharedData1$3$plaid;
import plaid.generated.IdoLocalSharedData2$4$plaid;
import plaid.generated.IdoShared$2$plaid;
import plaid.generated.IdoSharedData1$3$plaid;
import plaid.generated.IdoSharedData2$4$plaid;
import plaid.generated.IdoUnique$2$plaid;
import plaid.generated.IdoUniqueData1$3$plaid;
import plaid.generated.IdoUniqueData2$4$plaid;
import plaid.generated.Iinitialize$1$plaid;
import plaid.generated.Iinitialize$2$plaid;
import plaid.generated.IsetLocalShared$2$plaid;
import plaid.generated.IsetShared$2$plaid;
import plaid.generated.IsetUnique$2$plaid;
import plaid.lang.Integer32;


public class SharedArray extends AbstractPlaidDispatch implements Iinitialize$1$plaid,
															      IsetUnique$2$plaid,
                                                                  IsetShared$2$plaid,
                                                                  IsetLocalShared$2$plaid,
                                                                  IdoUnique$2$plaid,
                                                                  IdoShared$2$plaid, 
                                                                  IdoLocalShared$2$plaid, 
                                                                  IdoUniqueData1$3$plaid,
                                                                  IdoSharedData1$3$plaid, 
                                                                  IdoLocalSharedData1$3$plaid,
                                                                  IdoUniqueData2$4$plaid,
                                                                  IdoSharedData2$4$plaid, 
                                                                  IdoLocalSharedData2$4$plaid {
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
	
	private SharedArray(ObjectValue metadata) {
		super(metadata);
	}
	
	public static PlaidObject create(int order) {
		int bucketCount = (int)Math.pow(2, order);
 	   	if ( bucketCount <= 0  || bucketCount >= Math.pow(2, 24)) {
 	   		throw new PlaidIllegalArgumentException("Cannot allocate hashtable with " + bucketCount + " buckets.");
 	   	}
 	   	return new SharedArrayPlaidJavaObject(bucketCount);
	}
	
	public static PlaidObject create(PlaidObject order) {
		return create(toInt(order));
	}

	public static final int toInt(PlaidObject obj) {
		if ( obj instanceof Integer32.Integer32PlaidJavaObject ) {
			return ((Integer32.Integer32PlaidJavaObject)obj).integerValue;
		} else {
			throw new PlaidIllegalArgumentException("Cannot convert non Integer to int");
		}
	}
	
	@Override
	public PlaidObject initialize(PlaidObject thisVar, PlaidObject opsVar) {
		if ( thisVar instanceof SharedArrayPlaidJavaObject ) {
			final SharedArrayPlaidJavaObject sa = (SharedArrayPlaidJavaObject)thisVar;
			PlaidDispatch opsDispatch = opsVar.getDispatch();
			if ( opsDispatch instanceof Iinitialize$2$plaid ) {
				for (int i = 0; i < sa.data.length; i++) {
					Datagroup group = sa.groups[i];
					sa.data[i] = ((Iinitialize$2$plaid)opsDispatch).initialize(opsVar, plaid.fastruntime.Util.integer(i), group);
				}
			}
		} else {
			throw new PlaidIllegalArgumentException("Receiver to SharedArray is not a shard array.");
		}
		return plaid.fastruntime.Util.unit();
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
		return plaid.fastruntime.Util.unit();
	}
	
	@Override
	public PlaidObject setShared(PlaidObject thisVar, PlaidObject indexVar, PlaidObject objVar) {
		if ( thisVar instanceof SharedArrayPlaidJavaObject ) {
			final SharedArrayPlaidJavaObject sa = (SharedArrayPlaidJavaObject)thisVar;
			final int index = toInt(indexVar);
			if ( sa.accessOk(index)) {
				sa.groups[index].enterAtomic();
				sa.data[index] = objVar;
				sa.groups[index].leaveAtomic();
			} else {
				throw new PlaidIllegalArgumentException("Index out of bound: " + index);
			}
		} else {
			throw new PlaidIllegalArgumentException("Receiver to SharedArray is not a shard array.");
		}
		return plaid.fastruntime.Util.unit();
	}	
	
	@Override
	public PlaidObject setLocalShared(PlaidObject thisVar, PlaidObject indexVar, PlaidObject objVar) {
		if ( thisVar instanceof SharedArrayPlaidJavaObject ) {
			final SharedArrayPlaidJavaObject sa = (SharedArrayPlaidJavaObject)thisVar;
			final int index = toInt(indexVar);
			if ( sa.accessOk(index)) {
				sa.groups[index].enterAtomic();
				sa.data[index] = objVar;
				sa.groups[index].leaveAtomic();
			} else {
				throw new PlaidIllegalArgumentException("Index out of bound: " + index);
			}
		} else {
			throw new PlaidIllegalArgumentException("Receiver to SharedArray is not a shard array.");
		}
		return plaid.fastruntime.Util.unit();
	}
	
	@Override
	public PlaidObject doUnique(PlaidObject thisVar, PlaidObject indexVar, PlaidObject opsVar) {
		if ( thisVar instanceof SharedArrayPlaidJavaObject ) {
			final SharedArrayPlaidJavaObject sa = (SharedArrayPlaidJavaObject)thisVar;
			final int index = toInt(indexVar);
			if ( sa.accessOk(index)) {
				PlaidDispatch opsDispatch = opsVar.getDispatch();
				if ( opsDispatch instanceof IdoExclusive$2$plaid ) {
					PlaidObject obj = sa.data[index];
					Datagroup group = sa.groups[index];
					return ((IdoExclusive$2$plaid)opsDispatch).doExclusive(opsVar, obj, group);
				} else {
					throw new PlaidIllegalArgumentException("Illegal operations object");
				}
			} else {
				throw new PlaidIllegalArgumentException("Index out of bound: " + index);
			}
		} else {
			throw new PlaidIllegalArgumentException("Receiver to SharedArray is not a shard array.");
		}
	}
	
	@Override
	public PlaidObject doShared(PlaidObject thisVar, PlaidObject indexVar, PlaidObject opsVar) {
		if ( thisVar instanceof SharedArrayPlaidJavaObject ) {
			final SharedArrayPlaidJavaObject sa = (SharedArrayPlaidJavaObject)thisVar;
			final int index = toInt(indexVar);
			if ( sa.accessOk(index)) {
				PlaidDispatch opsDispatch = opsVar.getDispatch();
				if ( opsDispatch instanceof IdoShared$2$plaid ) {
					PlaidObject obj = sa.data[index];
					Datagroup group = sa.groups[index];
					return ((IdoShared$2$plaid)opsDispatch).doShared(opsVar, obj, group);
				} else {
					throw new PlaidIllegalArgumentException("Illegal operations object");
				}
			} else {
				throw new PlaidIllegalArgumentException("Index out of bound: " + index);
			}
		} else {
			throw new PlaidIllegalArgumentException("Receiver to SharedArray is not a shard array.");
		}
	}
	
	@Override
	public PlaidObject doLocalShared(PlaidObject thisVar, PlaidObject indexVar, PlaidObject opsVar) {
		if ( thisVar instanceof SharedArrayPlaidJavaObject ) {
			final SharedArrayPlaidJavaObject sa = (SharedArrayPlaidJavaObject)thisVar;
			final int index = toInt(indexVar);
			if ( sa.accessOk(index)) {
				PlaidDispatch opsDispatch = opsVar.getDispatch();
				if ( opsDispatch instanceof IdoShared$2$plaid ) {
					PlaidObject obj = sa.data[index];
					Datagroup group = sa.groups[index];
					return ((IdoShared$2$plaid)opsDispatch).doShared(opsVar, obj, group);
				} else {
					throw new PlaidIllegalArgumentException("Illegal operations object");
				}
			} else {
				throw new PlaidIllegalArgumentException("Index out of bound: " + index);
			}
		} else {
			throw new PlaidIllegalArgumentException("Receiver to SharedArray is not a shard array.");
		}
	}	

	@Override
	public PlaidObject doUniqueData1(PlaidObject thisVar, PlaidObject indexVar, PlaidObject opsVar, PlaidObject data) {
		if ( thisVar instanceof SharedArrayPlaidJavaObject ) {
			final SharedArrayPlaidJavaObject sa = (SharedArrayPlaidJavaObject)thisVar;
			final int index = toInt(indexVar);
			if ( sa.accessOk(index)) {
				PlaidDispatch opsDispatch = opsVar.getDispatch();
				if ( opsDispatch instanceof IdoExclusiveData1$3$plaid ) {
					PlaidObject obj = sa.data[index];
					Datagroup group = sa.groups[index];
					return ((IdoExclusiveData1$3$plaid)opsDispatch).doExclusiveData1(opsVar, obj, data, group);
				} else {
					throw new PlaidIllegalArgumentException("Illegal operations object");
				}
			} else {
				throw new PlaidIllegalArgumentException("Index out of bound: " + index);
			}
		} else {
			throw new PlaidIllegalArgumentException("Receiver to SharedArray is not a shard array.");
		}
	}
	
	@Override
	public PlaidObject doSharedData1(PlaidObject thisVar, PlaidObject indexVar, PlaidObject opsVar, PlaidObject data) {
		if ( thisVar instanceof SharedArrayPlaidJavaObject ) {
			final SharedArrayPlaidJavaObject sa = (SharedArrayPlaidJavaObject)thisVar;
			final int index = toInt(indexVar);
			if ( sa.accessOk(index)) {
				PlaidDispatch opsDispatch = opsVar.getDispatch();
				if ( opsDispatch instanceof IdoSharedData1$3$plaid ) {
					PlaidObject obj = sa.data[index];
					Datagroup group = sa.groups[index];
					return ((IdoSharedData1$3$plaid)opsDispatch).doSharedData1(opsVar, obj, data, group);
				} else {
					throw new PlaidIllegalArgumentException("Illegal operations object");
				}
			} else {
				throw new PlaidIllegalArgumentException("Index out of bound: " + index);
			}
		} else {
			throw new PlaidIllegalArgumentException("Receiver to SharedArray is not a shard array.");
		}
	}
	
	@Override
	public PlaidObject doLocalSharedData1(PlaidObject thisVar, PlaidObject indexVar, PlaidObject opsVar, PlaidObject data) {
		if ( thisVar instanceof SharedArrayPlaidJavaObject ) {
			final SharedArrayPlaidJavaObject sa = (SharedArrayPlaidJavaObject)thisVar;
			final int index = toInt(indexVar);
			if ( sa.accessOk(index)) {
				PlaidDispatch opsDispatch = opsVar.getDispatch();
				if ( opsDispatch instanceof IdoSharedData1$3$plaid ) {
					PlaidObject obj = sa.data[index];
					Datagroup group = sa.groups[index];
					return ((IdoSharedData1$3$plaid)opsDispatch).doSharedData1(opsVar, obj, data, group);
				} else {
					throw new PlaidIllegalArgumentException("Illegal operations object");
				}
			} else {
				throw new PlaidIllegalArgumentException("Index out of bound: " + index);
			}
		} else {
			throw new PlaidIllegalArgumentException("Receiver to SharedArray is not a shard array.");
		}
	}		

	@Override
	public PlaidObject doUniqueData2(PlaidObject thisVar, PlaidObject indexVar, PlaidObject opsVar, PlaidObject d1, PlaidObject d2) {
		if ( thisVar instanceof SharedArrayPlaidJavaObject ) {
			final SharedArrayPlaidJavaObject sa = (SharedArrayPlaidJavaObject)thisVar;
			final int index = toInt(indexVar);
			if ( sa.accessOk(index)) {
				PlaidDispatch opsDispatch = opsVar.getDispatch();
				if ( opsDispatch instanceof IdoExclusiveData2$4$plaid ) {
					PlaidObject obj = sa.data[index];
					Datagroup group = sa.groups[index];
					return ((IdoExclusiveData2$4$plaid)opsDispatch).doExclusiveData2(opsVar, obj, d1, d2, group);
				} else {
					throw new PlaidIllegalArgumentException("Illegal operations object");
				}
			} else {
				throw new PlaidIllegalArgumentException("Index out of bound: " + index);
			}
		} else {
			throw new PlaidIllegalArgumentException("Receiver to SharedArray is not a shard array.");
		}
	}
	
	@Override
	public PlaidObject doSharedData2(PlaidObject thisVar, PlaidObject indexVar, PlaidObject opsVar, PlaidObject d1, PlaidObject d2) {
		if ( thisVar instanceof SharedArrayPlaidJavaObject ) {
			final SharedArrayPlaidJavaObject sa = (SharedArrayPlaidJavaObject)thisVar;
			final int index = toInt(indexVar);
			if ( sa.accessOk(index)) {
				PlaidDispatch opsDispatch = opsVar.getDispatch();
				if ( opsDispatch instanceof IdoSharedData2$4$plaid ) {
					PlaidObject obj = sa.data[index];
					Datagroup group = sa.groups[index];
					return ((IdoSharedData2$4$plaid)opsDispatch).doSharedData2(opsVar, obj, d1, d2, group);
				} else {
					throw new PlaidIllegalArgumentException("Illegal operations object");
				}
			} else {
				throw new PlaidIllegalArgumentException("Index out of bound: " + index);
			}
		} else {
			throw new PlaidIllegalArgumentException("Receiver to SharedArray is not a shard array.");
		}
	}
	
	@Override
	public PlaidObject doLocalSharedData2(PlaidObject thisVar, PlaidObject indexVar, PlaidObject opsVar, PlaidObject d1, PlaidObject d2) {
		if ( thisVar instanceof SharedArrayPlaidJavaObject ) {
			final SharedArrayPlaidJavaObject sa = (SharedArrayPlaidJavaObject)thisVar;
			final int index = toInt(indexVar);
			if ( sa.accessOk(index)) {
				PlaidDispatch opsDispatch = opsVar.getDispatch();
				if ( opsDispatch instanceof IdoSharedData2$4$plaid ) {
					PlaidObject obj = sa.data[index];
					Datagroup group = sa.groups[index];
					return ((IdoSharedData2$4$plaid)opsDispatch).doSharedData2(opsVar, obj, d1, d2, group);
				} else {
					throw new PlaidIllegalArgumentException("Illegal operations object");
				}
			} else {
				throw new PlaidIllegalArgumentException("Index out of bound: " + index);
			}
		} else {
			throw new PlaidIllegalArgumentException("Receiver to SharedArray is not a shard array.");
		}
	}	
	
	public static final class SharedArrayPlaidJavaObject implements PlaidObject {
		public final PlaidObject data[];
		public final Datagroup groups[];
		
		public SharedArrayPlaidJavaObject(final int bucketCount) {
			data = new PlaidObject[bucketCount];
			groups = new plaid.fastruntime.aeminium.Datagroup[bucketCount];
			// initialize data groups
			for (int i = 0; i < groups.length; i++) {
				data[i] = plaid.lang.Unit.UNIT_VALUE;
				groups[i] = new Datagroup();
			}
		}
		
		public final boolean accessOk(int index) {
			return index >= 0 && index < data.length ;
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
