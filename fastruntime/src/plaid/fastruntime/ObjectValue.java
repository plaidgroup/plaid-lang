package plaid.fastruntime;

import fj.data.List;

public interface ObjectValue {
	public ObjectValue changeState(ObjectValue other);
	
	public List<MethodInfo> getMethods();
	
	public List<FieldInfo> getFields();

	/*
	 * Returns a new instance every time it is called.
	 */
	public PlaidObject[] getDefaultStorage();
	
	public String getTopTag();
	
	public boolean matches(String tag);

	int getFieldIndex(String fieldName);
	
	public ObjectValue specialize(ObjectValue newMembers);
	
	public ObjectValue remove(String member);
	
	public ObjectValue rename(String currentName, String newName);
}
