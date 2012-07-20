package plaid.fastruntime;

import java.util.Map;

import plaid.fastruntime.reference.UnmodifiableList;

public interface ObjectValue {
	
	public ObjectValue addParent(ObjectValue parent);
	
	public ObjectValue addTag(String tag);
	
	/**
	 * Name is strangely spelled to avoid collision with plaid keyword.
	 */
	public ObjectValue addMethod(String name, int numArgs, String fullyQualifiedClassName);
	
	public ObjectValue addDynMethod(String name, int numArgs);

	public ObjectValue addField(boolean settable, String name, String fullyQualifiedClassName);
	
	public ObjectValue addDynField(boolean settable, String name);
	
	public ObjectValue changeState(ObjectValue other);
	
	public UnmodifiableList<MethodInfo> getMethods();
	
	public UnmodifiableList<FieldInfo> getFields();

	/*
	 * Returns a new instance every time it is called.
	 */
	public PlaidObject[] getDefaultStorage(Map<String,PlaidLambda> fieldInitializers);
	
	/**
	 * @see java.lang.String#intern String interning
	 * @return Value is a canonical instance. The returned String instance should be the result of a call to String.intern() method.
	 */
	public String getCanonicalRep();
	
	public String getTopTag();

	public int getStorageIndex(String name);
	
	public int getStorageLength();
	
	public boolean matches(String tag);
	
	public ObjectValue remove(String member);
	
	public ObjectValue rename(String currentName, String newName);
	
	public ObjectValue with(ObjectValue other);

	public PlaidObject[] getPostChangeStorage(ObjectValue oldObjectValue,
			PlaidObject[] oldStorage);
	
	

}
