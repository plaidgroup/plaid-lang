package plaid.runtime.models.map;

import java.util.Collection;
import java.util.Map;

import plaid.runtime.PlaidException;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidTag;

//TODO : make into a singleton object?

public class PlaidAbstractValueMap extends PlaidObjectMap {
	@Override
	public void addMember(PlaidMemberDef member, PlaidObject obj) {
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}

	public void updateMember(String name, PlaidObject obj){
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}

	public void removeMember(String name) {
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}
	
	public Map<String, PlaidMemberDef> getMembers() {
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}
		
	public void addState(PlaidObject state) {
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}

	public void removeState(PlaidObject state) {
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}
	
	public Collection<PlaidObject> getStates() throws PlaidException {
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}
	
	public void addTag(PlaidTag tag,PlaidTag enclosingTag) {
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}
	
	public void addTopTag(PlaidTag tag) {
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}
	
	public void removeTag(PlaidTag tag) {
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}
	
	public boolean matchesTag(String tagName) {
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}
	
	public Map<PlaidTag,PlaidTag> getTags() throws PlaidException {
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}
	
	public Collection<PlaidTag> getTopTags() throws PlaidException {
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}
		
	public PlaidObject changeState(PlaidObject update) throws PlaidException {
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}
	
	public PlaidObject copy() {
		throw new PlaidRuntimeException("No operations possible on an abstract value.");
	}
}
