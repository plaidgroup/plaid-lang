package plaid.fastruntime;

import fj.data.List;

public interface ObjectValue {
	public ObjectValue changeState(ObjectValue other);
	
	public List<MethodInfo> getMethods();

	public PlaidStorage getDefaultStorage();
}
