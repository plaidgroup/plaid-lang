package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.ObjectValue;
import fj.data.List;

public final class FieldValue extends MemberValue implements FieldInfo {

	private final boolean settable;
	
	public static FieldValue createFieldWithStaticDefinition(String name, boolean settable, String classInternalName) {
		return new FieldValue(name, settable, classInternalName, true);
	}
	
	public static FieldValue createFieldWithDynamicDefinition(String name, boolean settable) {
		return new FieldValue(name, settable, null, false);
	}
	
	private FieldValue(String name, boolean settable, String classInternalName,
			boolean staticallyDefined) {
		super(name, classInternalName, staticallyDefined);
		this.settable = settable;
		this.init();
	}
	

	@Override
	protected List<MethodInfo> constructMethods() {
		return NIL_METHOD_INFO;
	}

	@Override
	public List<FieldInfo> constructFields() {
		return List.single((FieldInfo) this);
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
								  this.isStaticallyDefined());
		} else {
			return this;
		}
	}

	@Override
	protected String constructCanonicalRep() {
		String settableString = settable ? "t" : "f";
		String fieldInitializer = this.isStaticallyDefined() ?
				this.getStaticClassInternalName() 
				: "";
		String result =  "field:" + settableString + this.getName() + this.isStaticallyDefined() +
				fieldInitializer;
		return result;//.intern();
	}
}
