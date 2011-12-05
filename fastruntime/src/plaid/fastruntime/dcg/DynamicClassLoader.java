package plaid.fastruntime.dcg;

import java.util.HashMap;


public class DynamicClassLoader extends ClassLoader {
	
	private final HashMap<String,Class<?>> GENERATED_CLASSES = 
		new HashMap<String,Class<?>>();
	
	private DynamicClassLoader() {
		super();
	}
	
	
	public static final DynamicClassLoader DYNAMIC_CLASS_LOADER = new DynamicClassLoader();
	
	public Class<?> createClass(String name, byte[] b) {
		Class<?> myClass = defineClass(name.replace("/", "."), b, 0, b.length);
		GENERATED_CLASSES.put(name, myClass);
		return myClass;
	}
	
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> foundClass = GENERATED_CLASSES.get(name);
		if (foundClass != null) {
			return foundClass;
		} else {
			return super.findClass(name);
		}
	}
}
