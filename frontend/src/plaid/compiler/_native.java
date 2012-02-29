package plaid.compiler;

import java.util.Collections;
import java.util.List;

public final class _native {
	public static class PlaidCompilerAbortException extends Exception {
		private static final long serialVersionUID = -6723450739366050606L;
	}
	
	public static void throwAbortException() throws PlaidCompilerAbortException {
		throw new PlaidCompilerAbortException();
	}
	
	public static List<?> loadClass(final String fqn) {
		final ClassLoader cl = _native.class.getClassLoader();
		try {
			final Class<?> klazz = cl.loadClass(fqn);
			return Collections.singletonList(klazz);
		} catch (ClassNotFoundException e) {
			return Collections.EMPTY_LIST;
		}  catch (Throwable e ){
			return Collections.EMPTY_LIST;
		}
	}
}