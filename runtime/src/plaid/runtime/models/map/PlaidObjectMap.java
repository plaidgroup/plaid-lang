/**
 * Copyright (c) 2010 The Plaid Group (see AUTHORS file)
 * 
 * This file is part of Plaid Programming Language.
 *
 * Plaid Programming Language is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 *  Plaid Programming Language is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Plaid Programming Language.  If not, see <http://www.gnu.org/licenses/>.
 */
 
package plaid.runtime.models.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;

import plaid.runtime.PlaidException;
import plaid.runtime.PlaidIllegalAccessException;
import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidState;
import plaid.runtime.PlaidTag;
import plaid.runtime.Util;
import plaid.runtime.types.PlaidPermType;
import plaid.runtime.types.PlaidPermission;
import plaid.runtime.types.PlaidPermissionTable;
import plaid.runtime.types.PlaidPermissionTableMap;
import plaid.runtime.types.PlaidUniquePermission;
import plaid.runtime.utils.Delegate;

public class PlaidObjectMap implements PlaidObject {
	@SuppressWarnings("unchecked")
	public static Collection<PlaidObject> EMPTY_STATES = Collections.unmodifiableCollection(Collections.EMPTY_LIST);
	@SuppressWarnings("unchecked")
	public static Map<PlaidTag,PlaidTag> EMPTY_TAGS      = Collections.unmodifiableMap(Collections.EMPTY_MAP);
	protected PlaidPermissionTable permTable;
	protected Collection<PlaidObject> states;
	
	//members fixed is an immutable copy of members which is lazily updated after
	//member updates and returned whenever getMembers() is called
	protected Map<String, PlaidMemberDef> members;
	protected Map<String, PlaidMemberDef> membersFixed;
	protected boolean membersChanged = true;
	
	//some tags are nested and some are at the top level
	protected Map<PlaidTag,PlaidTag> tags;  //map between plaid tags and their enclosing tag
	protected List<PlaidTag> topTags = new ArrayList<PlaidTag>(); //top level tags for easy access
	
	// map from scopes to sets of bound variable names for this object
	protected boolean readonly = false;
	
	public PlaidObjectMap() {
		this(new PlaidPermissionTableMap(),
			 PlaidUniquePermission.unique());
	}
	
	public PlaidObjectMap(PlaidPermission initPerm) {
		this(new PlaidPermissionTableMap(),
			 initPerm);
	}
	
	private PlaidObjectMap(PlaidPermissionTable permTable, 
			               PlaidPermission initPerm) {
		this.permTable = permTable;
		initPerm.addPermission(this.permTable);
	}

	protected final Map<String, PlaidMemberDef> membersFixed() {
		if ( membersChanged ) {
			membersFixed = Collections.unmodifiableMap(members());
		}
		return membersFixed;
	}
	
	protected final Collection<PlaidObject> states() {
		if ( states == null ) {
			synchronized (this) {
				if ( states == null ) {
					states = new ArrayList<PlaidObject>();
				}
			}
		}
		return states;
	}
	
	protected final Map<PlaidTag,PlaidTag> tags() {
		if ( tags == null ) {
			synchronized (this) {
				if ( tags == null ) {
					tags = new HashMap<PlaidTag,PlaidTag>();
				}
			}
		}
		return tags;
	}
	
	protected final Map<String, PlaidMemberDef> members() {
		if ( members == null ) {
			synchronized (this) {
				if ( members == null ) {
					members = new HashMap<String, PlaidMemberDef>();
				}
			}
		}
		return members;
	}
	
	public void setReadOnly(boolean ro) {
		readonly = ro;
	}
	
	public boolean isReadOnly() {
		return readonly;
	}
	
	@Override
	public void addMember(PlaidMemberDef memberDef, PlaidObject obj) {
		
		if (this.isReadOnly())
			throw new PlaidIllegalAccessException("Cannot change readonly object.");

		boolean addedIsMutable = memberDef.isMutable();
		String name = memberDef.getMemberName();
		PlaidMemberDef existingDef = null;
		
		
		memberDef.setValue(obj);
		//Look for existing member definition
		existingDef = members().get(name);//findExisting(name, members().keySet());

		//if existing - update the existing member if allowed
		if (existingDef != null ) {
				
			//if existing is abstract, remove it and add the new one (do not update defined in)
			if (existingDef.getValue() instanceof PlaidAbstractValueMap) {
				//members().remove(name);
				members().put(name, memberDef);
			} else {
				
				//check that old/new match in terms of mutability
				boolean existingIsMutable = existingDef.isMutable();
				if (existingIsMutable && !addedIsMutable)
					throw new PlaidRuntimeException("Member \"" + name + "\" already exists as a mutable member.");
				else if (!existingIsMutable && addedIsMutable)
					throw new PlaidRuntimeException("Member \"" + name + "\" already exists as an immutable member.");
	
				//check that the member is not already defined or that it is being overriden
				if (!(members().get(existingDef.getMemberName()).getValue() instanceof PlaidAbstractValueMap) && !memberDef.overrides())  //undefined means having a unit value right now
					throw new PlaidRuntimeException("Member \"" + name + "\" already defined.");
			
				//find the correct tag to keep around
				if (memberDef.overrides()) {
					if (!memberDef.overrideIsBound()) {  //TODO:will we ever want to re-bind an override?
						if (existingDef.isAnonymous()) {
							memberDef.bindOverride("<Anonymous>"); // TODO: don't use a string like this
						} else 
							memberDef.bindOverride(existingDef.definedIn().getPath());
					}
					//members().remove(existingDef.getMemberName());
					members().put(existingDef.getMemberName(), memberDef);
				} else if (!existingDef.isAnonymous()) {
					//cannot both be defined in a state
					if (!memberDef.isAnonymous()) {
						throw new PlaidRuntimeException("Member \"" + name + "\" defined in states \"" + existingDef.definedIn() + 
							"\" and \"" + memberDef.definedIn() + "\".");
					} else { //take existing tag: just update the member
						existingDef.setValue(obj);
						//members().put(existingDef,obj);
					}
				} else { //remove existing def and use the added def
					//members().remove(existingDef.getMemberName());
					members().put(existingDef.getMemberName(), memberDef);
				}
			}
		} else { // new member - just add it
			members().put(name, memberDef);
		}
		membersChanged = true;
	}
	
	@Override
	public void updateMember(String name, PlaidObject obj) {
		if ( obj instanceof PlaidLookupMap ) {
			throw new PlaidRuntimeException("Cannot store PlaidLookupMap in PlaidObjects.");
		}
		
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		PlaidMemberDef existingDef = members().get(name);//findExisting(name, getMembers().keySet());
		if (existingDef == null) { //Cannot update a member that is not present
			throw new PlaidRuntimeException("Member \"" + name + "\" is undefined.");
		} else {
			if (!existingDef.isMutable()) { //Cannot update an immutable member
				throw new PlaidRuntimeException("Cannot update immutable member \"" + name + "\".");
			} else  { //update the definition
				//members().put(existingDef, obj);
				existingDef.setValue(obj);
			}
		}
	}

	@Override
	public void removeMember(String name) {
		if (this.isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		//remove the member if it exists else throw exception
		PlaidMemberDef existingDef = members().get(name);//findExisting(name, members().keySet());
		if (existingDef != null) members().remove(existingDef.getMemberName());
		else throw new PlaidRuntimeException("Cannot remove undefined member \"" + name + "\".");
		membersChanged = true;
	}

	@Override
	public Map<String, PlaidMemberDef> getMembers() {
		return membersFixed();
	}
	
	@Override
	public PlaidMemberDef getMember(String name) {
		return members().get(name);
	}
	
	@Override
	public void addState(PlaidObject state) throws PlaidException {
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (!states().contains(state)) {
			states().add(state);
		}
	}

	@Override
	public void removeState(PlaidObject state) {
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (states().contains(state)) {
			states().remove(state);
		}		
	}

	@Override
	public Collection<PlaidObject> getStates() {
		if ( states == null ) {
			return EMPTY_STATES;
		}
		return Collections.unmodifiableCollection(states());
	}
	
	@Override
	public void addTag(PlaidTag tag, PlaidTag enclosingTag) throws PlaidException {
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (this.existingHierarchy(tag)) 
			throw new PlaidRuntimeException("Hierarchy " + tag.rootTag().getName() + " already exists in the object");
		else {
			tags().put(tag,enclosingTag);
			if (enclosingTag == null)
				topTags.add(tag);
		}
	}

	@Override
	public void removeTag(PlaidTag tag) {
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (!this.existingHierarchy(tag)) 
			throw new PlaidRuntimeException("Hierarchy " + tag.rootTag().getName() + " does not exist in the object");
		else {
			PlaidTag enclosingTag = tags().remove(tag);
			if (enclosingTag == null) topTags.remove(tag);
		}		
	}

	@Override
	public Map<PlaidTag,PlaidTag> getTags() {
		if ( tags == null ) {
			return EMPTY_TAGS;
		}
		return tags();
	}
	
	@Override
	public boolean matchesTag(PlaidTag toMatch) {
		for (PlaidTag tag : getTags().keySet()) {
			if (toMatch.matches(tag)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void addTopTag(PlaidTag topTag) {
		if (!tags().containsKey(topTag)) {
			tags().put(topTag,null);
			topTags.add(topTag);
		}
	}

	@Override
	public Collection<PlaidTag> getTopTags() throws PlaidException {
		return Collections.unmodifiableList(this.topTags);
	}

	private boolean existingHierarchy(PlaidTag incoming) {
		for (PlaidTag t : tags().keySet()) {
			if (t.rootTag() == incoming.rootTag()) return true;
		}
		return false;
	}
	
	@Override
	public PlaidObject changeState(PlaidState update, boolean wipe) throws PlaidException {
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (wipe) changeByWipe(update);
		else restrictedUpdate(update);
		
		return Util.unit();
	}
	
	private void restrictedUpdate(PlaidState update) {		
		
		//Need to figure out which states from the old object to remove members from
		//and which states from the new object to add members from
		//1) first find matching hierarchies to incoming top tags in existing object	
		Map<PlaidTag,PlaidTag> outIn = new HashMap<PlaidTag,PlaidTag>();
		Map<PlaidTag,PlaidTag> preserveEnclosing = new HashMap<PlaidTag,PlaidTag>();  //keep track of enclosing tag of incoming states
		Set<PlaidTag> incoming = new HashSet<PlaidTag>(); // for tags without matches that will be added.
		Set<PlaidTag> removeHierarchies = new HashSet<PlaidTag>(); // optimized collection of tag hierarchies for removal
		//Turns out this one is not needed because incoming tags might include sub-hierarchies nested in tags that won't be added.
		//Set<PlaidTag> noAddHierarchies = new HashSet<PlaidTag>(); // optimized collection of tag hierarchies that remain in the object;
		for (PlaidTag newTag : update.getPrototype().getTopTags()) {
			boolean added = false;
			if (this.getTags().containsKey(newTag)) {
				outIn.put(newTag,newTag);
				added = true;  //already in this state - doesn't need to be added to the object
			} else {
				for (Entry<PlaidTag,PlaidTag> existingTag : this.getTags().entrySet()) {
					if (newTag.rootTag().equals(existingTag.getKey().rootTag())) {
						
						//TODO: figure out what these restrictions should be - they are not clear and the second one is definitely wrong (too conservative)
						//They will be checked below when the tags are actually added, so perhaps they are not needed
						//if (outIn.keySet().contains(existingTag.getKey())) throw new PlaidRuntimeException(existingTag + " already a member of this object");
						//if (outIn.values().contains(existingTag.getKey())) throw new PlaidRuntimeException(newTag + " already a member of the new object");
						outIn.put(existingTag.getKey(), newTag);
						preserveEnclosing.put(newTag,existingTag.getValue());  //where this tag was 
						removeHierarchies.add(existingTag.getKey());
						added = true;
					}
				}
			}
			if (!added) {
				incoming.add(newTag);
			}
		}

		//2) next find the roots of each pair and determine which states are "do not add" tags
		//   and which are "do not remove" tags
		Set<PlaidTag> noAdd = new HashSet<PlaidTag>();
		Set<PlaidTag> toRemove = new HashSet<PlaidTag>();
		//List<String> removedOverrides = new ArrayList<String>();
		for (PlaidTag existingTag : outIn.keySet()) {
			
			if (existingTag.equals(outIn.get(existingTag))) { //don't add any of these tags - no state change in this dimension
				noAdd.addAll(existingTag.getHierarchy());
				continue;
			}
			
			List<PlaidTag> existingHierarchy = existingTag.getHierarchy();
			int existingCount = existingHierarchy.size()-1;
			List<PlaidTag> newHierarchy = outIn.get(existingTag).getHierarchy();
			int newCount = newHierarchy.size()-1;
			
			//know the hierarchies overlap - now find the place where they diverge
			while ( existingCount >= 0 && newCount >= 0 &&
					existingHierarchy.get(existingCount).equals(newHierarchy.get(newCount))) {
				noAdd.add(existingHierarchy.get(existingCount)); // will not need to 
				newCount--;
				existingCount--;
			}
			
			//we are transitioning down - already added stuff above to noAdd list
			if ( newCount < 0 && existingCount >= 0 ) { //we are transitioning up
				// remove members here and below
				while ( existingCount >= 0 ) toRemove.add(existingHierarchy.get(existingCount--));
			} else if ( existingCount >= 0 ) { //we are transitioning across
				// remove members here and below
				while ( existingCount >= 0 ) toRemove.add(existingHierarchy.get(existingCount--));
			} //else transitioning down and nothing else to do since higher up states already added to the noAdd list
		}
		
		//2a) any tags nested in toRemove tags will be removed too
		this.tagDependencyClosure(toRemove,removeHierarchies);
		
		//2b) any tags nested in tags from noAdd should also not be added
		this.tagDependencyClosure(noAdd,new HashSet<PlaidTag>()); //hierarchy set not used
		
		//3) initialize members defined in tags not on the noAdd list
		Map<PlaidMemberDef,PlaidObject> willAdd = new HashMap<PlaidMemberDef,PlaidObject>();
		Map<String, PlaidMemberDef> newMembers = update.getPrototype().getMembers();
		for (PlaidMemberDef incomingMember : newMembers.values()) {
			if (!noAdd.contains(incomingMember.definedIn()) || //not in the noAdd list, or
					(members().get(members().get(incomingMember.getMemberName())) != null && // exists in this object, but is abstract
							members().get(members().get(incomingMember.getMemberName()).getValue()) instanceof PlaidAbstractValueMap)) //abstract
			{ 
				PlaidMemberDef newMemberDef = Util.memberDef(incomingMember);
				PlaidObject incomingMemberValue = newMembers.get(newMemberDef.getMemberName()).getValue();
				willAdd.put(newMemberDef, PlaidStateMap.initializeMember(incomingMemberValue, this));
			}
		}
		
		//4) loop over existing members removing those defined in states from toRemove List
		List<PlaidMemberDef> removeDefs = new ArrayList<PlaidMemberDef>();
		for (PlaidMemberDef member : members().values()) {
			if (toRemove.contains(member.definedIn())) {
				removeDefs.add(member);
				//if (member.overrides()) removedOverrides.add(member.getMemberName());
			}
		}
		for (PlaidMemberDef rem : removeDefs) removeMember(rem.getMemberName());
		
		//5) remove outgoing hierarchies
		for (PlaidTag outTag : removeHierarchies) removeTag(outTag);
		
		//6) add new members
		for (Entry<PlaidMemberDef,PlaidObject> add : willAdd.entrySet()) {
			addMember(add.getKey(),add.getValue());
		}
		
		//7) add incoming hiearchies
		for (Entry<PlaidTag,PlaidTag> inTag : update.getPrototype().getTags().entrySet()) {
			if (!noAdd.contains(inTag.getKey())) {
				if (inTag.getValue() != null ) //keep nesting from incoming state
					addTag(inTag.getKey(),inTag.getValue()); //TODO: fix change by transition
				else //keep nesting in existing object
					addTag(inTag.getKey(),preserveEnclosing.get(inTag.getKey()));
			}
		}
		for (PlaidTag inTag : incoming) { //add tags that 
			addTag(inTag,null);
		}
	}
	
	//TODO: KBN - This is kind of ugly, but I don't see any way around it - I wonder if there is a better scheme
	//Note that the toRemove set contains ALL individual tags that are being removed while the tag() collection
	//of this PlaidObject is optimized to group all tags in the same hierarchy together.  This is why we addAll
	//parts of the hierarchy when adding tags to toRemove
	//KBN 2011-4-20 - update turns out we need the hiearchies too since tags are added/removed from the object
	// on a hierarchy basis unlike members which are added/removed on an individual tag basis
	private void tagDependencyClosure(Set<PlaidTag> individualTags, Set<PlaidTag> hierarchies) {
		Map<PlaidTag,List<PlaidTag>> dependencies = new HashMap<PlaidTag,List<PlaidTag>>(); //hierarchies
		for (PlaidTag tag : tags().keySet()) {						//for each hierarchy associated with this object
			if (!individualTags.contains(tag)) {					//do nothing if it is already included in the set (already added)
				PlaidTag dependsOn = tags().get(tag);				//otherwise, get its enclosing tag
				if (individualTags.contains(dependsOn)) { 			//if its enclosing tag is outgoing, it needs to be added
					hierarchies.add(tag);							//add the hierarchy as a whole to the closure
					Stack<PlaidTag> worklist = new Stack<PlaidTag>(); 
					worklist.addAll(tag.getHierarchy());			//remove each tag in the hierarchy along with any dependent hierarchies
					while (!worklist.empty()) {
						PlaidTag checkDependencies = worklist.pop();
						individualTags.add(checkDependencies);  		   //add to list of individual tags in the closure
						if (dependencies.containsKey(checkDependencies)) { //recursively add dependent hieararchies
							for (PlaidTag removeDependency : dependencies.get(checkDependencies)) {
								hierarchies.add(removeDependency);  				//add whole hierarchy to the closure
								worklist.addAll(removeDependency.getHierarchy());  //each tag will also be checked for dependencies
							}
						}
					}
				} else {
					List<PlaidTag> deps = dependencies.get(dependsOn);   //otherwise, cache this dependency
					if (deps == null) {  //add the dependent list to the map if it is not already there
						deps = new ArrayList<PlaidTag>();
						dependencies.put(dependsOn,deps);
					}
					deps.add(tag);  //add the whole hierarchy as a dependency
				}
			}
		}
	}
	
	private void changeByWipe(PlaidState update) {

		// cleanup current information
		members().clear(); //TODO: fix this (KBN - how?)
		states().clear();
		tags().clear();
		topTags.clear();
		
		PlaidObject instantiated  = update.instantiate();
		
		// add in the members from the updating object
		for (Map.Entry<String, PlaidMemberDef> e : instantiated.getMembers().entrySet()) {
			if (e.getValue().getValue() instanceof PlaidMethodMap) {
				PlaidMethodMap pmm = (PlaidMethodMap)e.getValue().getValue();
				addMember(Util.memberDef(e.getValue()), new PlaidMethodMap(pmm.getFullyQualifiedName(), this, pmm.delegate));
			}
			else {
				addMember(Util.memberDef(e.getValue()), e.getValue().getValue());
			}
		}
		
		// add other states
		states().addAll(instantiated.getStates());
		for (PlaidTag t : instantiated.getTags().keySet()) this.addTag(t,instantiated.getTags().get(t)); 
		
		membersChanged = true;
	}
	
	@Override
	public PlaidObject copy() {
		PlaidObjectMap newObj = new PlaidObjectMap();
		// add members
		newObj.members().putAll(this.members());
		// add states
		newObj.states().addAll(this.states());
		// add tags
		newObj.tags().putAll(this.tags());
		newObj.topTags.addAll(this.topTags);
		return newObj;
	}

	public String toString() {
		
		String objectString= "";
		if (members != null && members.containsKey("toString")) {
			PlaidObject toStringMethod = members.get("toString").getValue();
			if (toStringMethod instanceof PlaidMethodMap) {
				PlaidObject result = Util.call(toStringMethod,Util.unit());
				
				if (result instanceof PlaidJavaObjectMap) {
					objectString = ((PlaidJavaObjectMap) result).getJavaObject().toString() + "\n\n";
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		//members
		sb.append("\nPlaidObject(\n\tmembers={");
		for (PlaidMemberDef f : members().values()) {
			if (members().get(f) instanceof PlaidAbstractValueMap) sb.append("Abstract ");
			sb.append(f + ", ");
		}
		if (sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
	
		// states
		sb.append("}, \n\tstates={");
		for( Object s : states().toArray() ) {
			PlaidStateMap sm = (PlaidStateMap)s;
			sb.append(sm + ",");
		}
		if (sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		
		// tags
		sb.append("}, \n\ttags={");
		for (Object t : tags().keySet().toArray()) {
			PlaidTag tm = (PlaidTagMap)t;
			sb.append(tm + ",");
		}	
		if (sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		
		sb.append("})");
		return objectString + sb.toString();
	}

	@Override
	public int hashCode() {
		PlaidMemberDef hashmember = members().get("hashCode");
		if (hashmember == null)
			return super.hashCode();
		PlaidObject hashmethobj = hashmember.getValue();
		if (hashmethobj == null)
			return super.hashCode();
		if (!(hashmethobj instanceof PlaidMethodMap))
			return super.hashCode();
		
		PlaidMethodMap hashmeth = (PlaidMethodMap) hashmethobj;
		//PlaidObject retval = hashmeth.invoke(Util.unit());
		PlaidObject retval = plaid.runtime.Util.call(hashmeth, Util.unit()); //for tco

		// We need to find out if retval is a (Plaid) integer and return its value
		// TODO: This is a hack!
		if (retval instanceof PlaidJavaObject) {
			return ((PlaidJavaObject) retval).getJavaObject().hashCode();			
		} else if ( retval.matchesTag(Util.getTag("plaid.lang.Int32")) ) {
			PlaidMemberDef nativeValMemberDef = retval.getMember("nativeVal");
			if (nativeValMemberDef != null && nativeValMemberDef.getValue() instanceof PlaidJavaObject) {
				return ((PlaidJavaObject) nativeValMemberDef.getValue()).getJavaObject().hashCode();
			} else 
				return super.hashCode();
			
		} else 
			return super.hashCode();

	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (this == other)
			return true;
		if (!(other instanceof PlaidObjectMap))
			return false;
		
		PlaidObjectMap po = (PlaidObjectMap) other;
		PlaidMemberDef eqmember = members().get("eqeq$plaid");
		if (eqmember == null)
			return false;
		PlaidObject eqmethobj = eqmember.getValue();
		if (eqmethobj == null)
			return false;
		if (!(eqmethobj instanceof PlaidMethodMap))
			return false;
		
		PlaidMethodMap eqmeth = (PlaidMethodMap) eqmethobj;
		//PlaidObject retval = eqmeth.invoke(po);
		PlaidObject retval = plaid.runtime.Util.call(eqmeth, po);	//for tco

		// We need to find out if retval is a (Plaid) true
		return retval.matchesTag(Util.getTag("plaid.lang.True"));
	}

	@Override
	public void cast(PlaidPermType oldType, PlaidPermType newType) {
		// TODO: lambda/method types
		// TODO: make sure the actual type part is compatible
		// make sure permission being cast to is compatible with all other existing aliases
		PlaidPermission oldPerm = oldType.getPermission();
		PlaidPermission newPerm = newType.getPermission();
		permTable.execPermCast(oldPerm, newPerm);
	}

	@Override
	public void split(PlaidPermType initialType, PlaidPermType neededType, PlaidPermType residueType) {
		PlaidPermission initialPerm = initialType.getPermission();
		PlaidPermission neededPerm = neededType.getPermission();
		PlaidPermission residuePerm = residueType.getPermission();
		permTable.execPermSplit(initialPerm, neededPerm, residuePerm);
	}

	@Override
	public void join(PlaidPermType toJoinType1, PlaidPermType toJoinType2, PlaidPermType resultType) {
		PlaidPermission toJoinPerm1 = toJoinType1.getPermission();
		PlaidPermission toJoinPerm2 = toJoinType2.getPermission();
		PlaidPermission resultPerm = resultType.getPermission();
		permTable.execPermSplit(toJoinPerm1, toJoinPerm2, resultPerm);
	}
	
	@Override
	public PlaidState freeze() {
		
		// add members from object to the state as proto members
		PlaidState frozenState = new PlaidStateMap();
		for ( Map.Entry<String, PlaidMemberDef> member : this.getMembers().entrySet() ) {
			final PlaidObject memberValue = member.getValue().getValue();
			PlaidMemberDef memberKey = member.getValue().copy();
			if (memberValue instanceof PlaidMethodMap) {
				PlaidMethodMap pmm = (PlaidMethodMap) memberValue;
				PlaidProtoMethodMap protoMethod = new PlaidProtoMethodMap(pmm.getFullyQualifiedName(), pmm.getDelegate());
				frozenState.addMember(Util.memberDef(memberKey), protoMethod);
			} else if (memberValue instanceof PlaidAbstractValueMap) {
				frozenState.addMember(Util.memberDef(memberKey), memberValue);
			} else {	
				PlaidProtoFieldMap protoField = new PlaidProtoFieldMap(new Delegate() {
					public PlaidObject invoke(PlaidObject thisVar, PlaidObject args) throws PlaidException {	
						return memberValue;
					}
				});
				frozenState.addMember(memberKey, protoField);
			}
		}
		// add states from this object to frozen state
		if ( this.getStates() != PlaidObjectMap.EMPTY_STATES ) {
			for (PlaidObject ps : this.getStates() ) {
				frozenState.getPrototype().addState(ps);
			}
		}
		
		// add tags from this object to frozen state
		if ( this.getTags() != PlaidObjectMap.EMPTY_TAGS ) {
			Map<PlaidTag,PlaidTag> tags = this.getTags();
			for (PlaidTag t : tags.keySet()) {
				frozenState.getPrototype().addTag(t,tags.get(t));
			}
		}
		
//		for (PlaidTag t : this.getTopTags()) {
//			frozenState.getPrototype().addTopTag(t)
//		}
		
		return frozenState;
	}
	

}
