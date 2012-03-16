package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MemberDefInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.ObjectValue;
import fj.data.List;

public final class FieldValue extends MemberValue implements FieldInfo {

	private final boolean settable;
	
	public static FieldValue createFieldWithStaticDefinition(String name, boolean settable, String classInternalName) {
		return new FieldValue(name, settable, classInternalName, false, null);
	}
	
	public static FieldValue createFieldWithDynamicDefinition(String name, boolean settable, String memberDefinitionName) {
		return new FieldValue(name, settable, null, true, memberDefinitionName);
	}
	
	private FieldValue(String name, boolean settable, String classInternalName,
			boolean staticallyDefined, String memberDefinitionName) {
		super(name, classInternalName, staticallyDefined,  memberDefinitionName);
		this.settable = settable;
		this.init();
	}
	

	@Override
	protected List<MethodInfo> constructMethods() {
		return List.nil();
	}

	@Override
	public List<FieldInfo> constructFields() {
		return List.single((FieldInfo) this);
	}
	
	@Override
	public List<MemberDefInfo> constructMemberDefs() {
		return List.nil();
	}

	@Override
	public int compareTo(FieldInfo o) {
		return  this.getName().compareTo(o.getName());
	}

	public boolean isSettable() {
		return settable;
	}
	
	@Override
	public ObjectValue rename(String currentName, String newName) {
		if(this.getName().equals(currentName)) {
			return new FieldValue(newName, this.settable, this.getStaticClassInternalName(),
								  this.isStaticallyDefined(), this.getMemberDefinitionName());
		} else {
			return this;
		}
	}

	@Override
	protected String constructCanonicalRep() {
		String settableString = settable ? "t" : "f";
		String result =  "field:" + settableString + this.getName()+ this.getStaticClassInternalName();
		return result.intern();
	}
}
