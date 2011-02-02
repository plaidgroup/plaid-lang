package plaid.runtime.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class Configuration {
	protected static final Properties properties; 
	
	static {
		String filename = System.getenv("AEMINIUMRT_CONFIG");
		if ( filename == null ) {
			filename = "plaidrt.config";
		}
		File file = new File(filename);
		properties = new Properties();
		if ( file.exists()  && file.canRead()) {
			FileReader freader;
			try {
				freader = new FileReader(file);
				properties.load(freader);
				freader.close();
			}  catch (IOException e) {
			} 
		} 
	}
	
	protected Configuration() {}
	
	public final static String getProperty(Class<?> klazz, String key, String defaultValue) {
		String value = properties.getProperty(klazz.getSimpleName() + "." + key);
		if ( value != null ) {
			return value.trim();
		} else {
			return defaultValue;
		}
	}
	
	public final static int getProperty(Class<?> klazz, String key, int defaultValue) {
		String value = properties.getProperty(klazz.getSimpleName()+ "." + key);
		if ( value != null ) {
			return Integer.valueOf(value.trim());
		} else {
			return defaultValue;
		}
	}
	
	public final static boolean getProperty(Class<?> klazz, String key, boolean defaultValue) {
		String value = properties.getProperty(klazz.getSimpleName()+ "." + key);
		if ( value != null ) {
			return Boolean.valueOf(value.trim());
		} else {
			return defaultValue;
		}
	}

}