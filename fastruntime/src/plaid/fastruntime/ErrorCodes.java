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
		
		
		/*
		 *  PERMISSION ERROR MESSAGES
		 */
		ERROR_MESSAGES.put("P_RETURN_FAIL",
				   "(Implementation Error) Invalid Permission Return: ");
		
		/*
		 *  STRUCTURE ERROR MESSAGES
		 */
		ERROR_MESSAGES.put("S_UNPACK_METHOD",
						"(Implementation Error) cannot unpack method: ");	
		ERROR_MESSAGES.put("S_UNPACK_OTHER",
				"(Implementation Error) cannot unpack non-field member: ");	
		ERROR_MESSAGES.put("S_UNPACK_MISSING",
				"(Implementation Error) cannot unpack missing member: ");	
		ERROR_MESSAGES.put("S_ASSIGN_VAL",
				"Cannot assign to 'val' field: ");	
		ERROR_MESSAGES.put("S_ASSIGN_METHOD",
				"(Implementation Error) Cannot assign to method: ");	
		ERROR_MESSAGES.put("S_ASSIGN_OTHER",
				"(Implementation Error) Cannot assign to non-field member: ");	
		ERROR_MESSAGES.put("S_ASSIGN_MISSING",
				"(Implementation Error) Cannot assign to missing member: ");	
	
	}
	
	public static final String getMessage(String errorCode) {
		if (ERROR_MESSAGES.containsKey(errorCode)) {
			return ERROR_MESSAGES.get(errorCode);
		 } else {
			return "Unknown Error Code: " + errorCode;
		 }
		
	}
	
}
