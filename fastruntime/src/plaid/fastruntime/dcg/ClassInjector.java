package plaid.fastruntime.dcg;


// NEED TO SWITCH RESTRICTED ACCESS CHECK TO WARNINGS
// Java -> Compiler -> Errors/Warnings -> Deprecated and restricted API -> Forbidden reference (access rule)
public final class ClassInjector {
	private static sun.misc.Unsafe UNSAFE = getUnsafe();
	
	// prohibit instantiation
	private ClassInjector() {}
	
    /**
     * Returns a sun.misc.Unsafe.  Suitable for use in a 3rd party package.
     * Replace with a simple call to Unsafe.getUnsafe when integrating
     * into a jdk.
     *
     * @return a sun.misc.Unsafe
     */
    private static sun.misc.Unsafe getUnsafe() {
        try {
            return sun.misc.Unsafe.getUnsafe();
        } catch (SecurityException se) {
            try {
                return java.security.AccessController.doPrivileged
                    (new java.security
                     .PrivilegedExceptionAction<sun.misc.Unsafe>() {
                        public sun.misc.Unsafe run() throws Exception {
                            java.lang.reflect.Field f = sun.misc
                                .Unsafe.class.getDeclaredField("theUnsafe");
                            f.setAccessible(true);
                            return (sun.misc.Unsafe) f.get(null);
                        }});
            } catch (java.security.PrivilegedActionException e) {
                throw new RuntimeException("Could not initialize intrinsics",
                                           e.getCause());
            }
        }
    }
	
	public static Class<?> defineClass(String name, byte[] b, int off, int len) {
		return UNSAFE.defineClass(name, b, off, len);
	}
}
