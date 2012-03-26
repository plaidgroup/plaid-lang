package plaid.fastruntime;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodes {

	public static final Map<String,String> ERROR_MESSAGES = new HashMap<String,String>();
	static {
		
		/*
		 *  TYPECHECKER ERROR MESSAGES
		 */
		ERROR_MESSAGES.put("T_ASSIGN_LOCAL_VAL",
						   "Cannot assign to a \"val\" variable or parameter");
		
	
	}
	
}
