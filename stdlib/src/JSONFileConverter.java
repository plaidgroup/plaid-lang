import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import plaid.compilerjava.util.MemberRep;


/**
 * Just a small utility to help convert the JSON representations of the natively 
 * written stuff in stdlib (Integer and String for now) into Java-ready 
 * escaped Strings.
 * 
 * Remember to add quotes around the thing that is printed out!
 * 
 * @author mhahnenberg
 *
 */
public class JSONFileConverter {
	public static void main(String[] args) {
		File f = new File("misc/StringType.json");
		try {
			// read in the file
			BufferedReader br = new BufferedReader(new FileReader(f));
			StringBuilder sb = new StringBuilder();
			String currLine = null;
			while ((currLine = br.readLine()) != null) {
				sb.append(currLine.trim());
			}
			
			// run our escape function on it
			String result = MemberRep.escapeJSONString(sb.toString());
			
			// print the result
			System.out.println(result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
