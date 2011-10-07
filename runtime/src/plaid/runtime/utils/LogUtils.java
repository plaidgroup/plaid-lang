package plaid.runtime.utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LogUtils {
	public static class SimpleFormatter extends Formatter {

		@Override
		public String format(LogRecord record) {
			return String.format("[%7s] %s %s", record.getLevel().toString(), record.getMessage(), System.getProperty("line.separator"));
		}
	}
	
	public static class SimpleConsoleHandler extends ConsoleHandler {

		public SimpleConsoleHandler() {
			super();
			setLevel(Level.ALL);
			setFormatter(new SimpleFormatter());
		}
	}

	public static class SimpleLogger extends Logger {

		protected SimpleLogger(String name) {
			super(name, null);
			setUseParentHandlers(false);
			addHandler(new SimpleConsoleHandler());
			setLevel(Level.ALL);
		}
		
	}

	public static Logger getSimpleLogger(String name) {
		return new SimpleLogger(name);
	}
}