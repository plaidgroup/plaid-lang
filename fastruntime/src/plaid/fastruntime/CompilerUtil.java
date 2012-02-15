package plaid.fastruntime;

import java.util.HashMap;
import java.util.Map;

/**
 * This utility class is used by the compiler. It should probably be moved out of the runtime.
 * @author jssunshi
 *
 */

public class CompilerUtil {
	public static String convertOpStringToMethodName(String op) {
		StringBuilder output = new StringBuilder();
		Map<String, String> opNames = new HashMap<String,String>();
        opNames.put("=","eq");
        opNames.put("<","lt");
        opNames.put(">","gt");
        opNames.put("!","bang");
        opNames.put("~","tilde");
        opNames.put("?","quest");
        opNames.put(":","colon");
        opNames.put("&","amp");
        opNames.put("|","pipe");
        opNames.put("+","plus");
        opNames.put("-","sub");
        opNames.put("*","mult");
        opNames.put("/","div");
        opNames.put("^","carat");
        opNames.put("%","mod");
		for (int i=0; i<op.length(); i++) {
			String opChar = op.substring(i, i+1);
			output.append(opNames.get(opChar));
		}
		output.append(NamingConventions.GENERATED_SUFFIX);
		return output.toString();
		
	}
}
