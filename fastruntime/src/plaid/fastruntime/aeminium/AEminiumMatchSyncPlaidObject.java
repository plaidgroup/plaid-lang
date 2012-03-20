package plaid.fastruntime.aeminium;

import java.util.Collection;
import java.util.HashSet;

import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.errors.PlaidIllegalArgumentException;
import plaid.fastruntime.errors.PlaidIllegalOperationException;

public class AEminiumMatchSyncPlaidObject implements PlaidObject {
	
	@SuppressWarnings("rawtypes")
	private final Collection deps = new HashSet();
	private int matchCount;

	public AEminiumMatchSyncPlaidObject(int matchCount) {
		this.matchCount = matchCount;
		if ( matchCount <  1) throw new PlaidIllegalArgumentException("MatchCount must be bigger than zero,");
	}
	
	/**
	 * Waits until all match blocks have been schedule.
	 * @return Null if there are outstanding matches or a collection of tasks to wait for 
	 */
	@SuppressWarnings("rawtypes")
	public synchronized Collection waitForMatches() {
		if ( 0 < matchCount ) {
			return null;
		} else {
			return deps;
		}
	}
	
	/**
	 * Registers a match with this synchronization object.
	 * @param deps The tasks the corresponding match leave has to wait for.
	 * @return If this is the last remaing match block it will return the list of tasks the corresponding match leave task should wait for. Null in the other cases.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized Collection registerMatch(Collection deps) {
		this.matchCount--;
		this.deps.addAll(deps);
		if ( this.matchCount == 0 ) {
			this.notifyAll();
			return deps;
		} else {
			return null;
		}
	}
	
	@Override
	public PlaidState getDispatch() {
		throw new PlaidIllegalOperationException("AEminiumMatchSyncPlaidObjects are not proper PlaidObjects.");
	}

	@Override
	public PlaidObject[] getStorage() {
		throw new PlaidIllegalOperationException("AEminiumMatchSyncPlaidObjects are not proper PlaidObjects.");
	}

	@Override
	public void changeState(PlaidState s) {
		throw new PlaidIllegalOperationException("AEminiumMatchSyncPlaidObjects are not proper PlaidObjects.");
	}

}
