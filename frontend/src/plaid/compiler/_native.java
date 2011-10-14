package plaid.compiler;

import java.util.Collections;
import java.util.List;

public final class _native {
		
	public static List<?> loadClass(final String fqn) {
		final ClassLoader cl = _native.class.getClassLoader();
		try {
			final Class<?> klazz = cl.loadClass(fqn);
			return Collections.singletonList(klazz);
		} catch (ClassNotFoundException e) {
			return Collections.EMPTY_LIST;
		} 
	}
}