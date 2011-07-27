package plaid.collections;

public class JavaArrayWrapper {
	
	private Object[] array;

	public JavaArrayWrapper(int size) {
		this.array = new Object[size];
	}

	public static JavaArrayWrapper create(int size) {
		return new JavaArrayWrapper(size);
	}

	public Object get(int index) {
		return array[index];
	}

	public Object set(int index, Object elem) {
		Object old = array[index];
		array[index] = elem;
		return old;
	}
}