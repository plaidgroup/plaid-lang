package plaid.fastruntime.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileGen {
	private static SourceFormater FORMATER = new SourceFormater(4);
	
	private static class SourceFormater {
		private final int tabSize;
		private final String[] indents = new String[64];

		public SourceFormater(int tabSize) {
			super();
			this.tabSize = tabSize;

			indents [0] = "";
			indents [1] = "";
			for ( int i  = 0 ; i < this.tabSize; i++ ) indents[1] += " ";

			for ( int i = 2 ; i < indents.length ; i++ ) {
				indents[i] = indents[i-1] + indents[1];
			}

		}

		protected String getIndent(int level) {
			if ( level > indents.length - 1) return indents[indents.length-1];
			if ( level < 0 ) return "";
			return indents[level]; 
		}
		
		public String format(String source) {
			final StringBuilder sb = new StringBuilder();
			int currentIndent = 0;
			boolean stringMode = false;
			boolean escapeMode = false;
			boolean newLine    = false;

			for ( char c : source.toCharArray() ) {
				//System.out.println("['"+c+"', "+currentIndent+", string="+stringMode+", escape="+escapeMode+"]");
				
				if ( c == '}' && !stringMode ) currentIndent--;
				if ( newLine ){
					newLine = false;
					sb.append(getIndent(currentIndent));
				}
				if ( c == '{' && !stringMode) currentIndent++;
				
				switch (c) {
				case  '"': {
					if ( !stringMode ) {
						stringMode = true;
					} else {
						if ( !escapeMode ) {
							stringMode = false;
						} else {
							escapeMode = false; 
						}
					}
					sb.append(c);
					break;
				}
				case '\\': {
					if ( stringMode ) {
						if ( escapeMode ) {
							escapeMode = false;
						} else {
							escapeMode = true;
						}
					}

					sb.append(c);
					break;
				}
				case '\n': {
					newLine = true;
					sb.append(c);
					break;
				}
				default: {
					escapeMode = false;	
					sb.append(c);
				}
				}
			}
			return sb.toString();
		}
	}


	/**
	 * @param packageDir in directory format, e.g.  java.lang.System should be java/lang
	 */
	public static File createOutputFile(String name, String outputDirectory, String code, String packageDir) {
		String directory, file;

		try {
			directory = outputDirectory + System.getProperty("file.separator")+ packageDir;
			file = name + ".java";
			File outputDir = new File(directory);
			outputDir.mkdirs();
			File output = new File(directory + System.getProperty("file.separator") + file);
			output.createNewFile();
			PrintWriter printOut = new PrintWriter(output);
			printOut.print(FORMATER.format(code));
			printOut.flush();

			return output;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}