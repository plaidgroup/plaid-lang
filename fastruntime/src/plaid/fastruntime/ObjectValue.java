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

	int getFieldIndex(String fieldName);
}
