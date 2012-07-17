package plaid.fastruntime;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodes {

	public static final Map<String,String> ERROR_MESSAGES = new HashMap<String,String>();
	static {
		
		/* 
		 *  INTERNAL ERROR MESSAGES
		 */
		ERROR_MESSAGES.put("SYM_NOT_FOUND", 
			"(Implementation Error) Symbol not found.");
		ERROR_MESSAGES.put("UNEXPECTED_SYM",
			"(Implementation Error) unexpected symbol found: ");
		ERROR_MESSAGES.put("UNEXPECTED_SIG",
			"(Implementation Error) unexpected signature type found");
		ERROR_MESSAGES.put("STRUCT_NOT_FOUND", 
			"(Implementation Error) Structure not found");
		ERROR_MESSAGES.put("SCOPING_ERROR", 
			"(Implementation Error) Expected symbol already out of scope: ");
		ERROR_MESSAGES.put("T_WRONG_AST",
			"(Implementation Error) Bad AST structure: ");
		ERROR_MESSAGES.put("UNEXPECTED_AST",
			"(Implementation Error) Unexpected AST node: ");
		ERROR_MESSAGES.put("UNIMPLEMENTED_FEATURE",
			"(Unimplemented Feature) ");
		
		/*
		 *  TYPECHECKER ERROR MESSAGES
		 */
		ERROR_MESSAGES.put("T_NOT_IN_SCOPE",
		   "Identifier not in scope: ");
		ERROR_MESSAGES.put("T_ASSIGN_LOCAL_VAL",
			"Cannot assign to a 'val' variable or parameter: ");
		ERROR_MESSAGES.put("T_ASSIGN_WRONG_TYPE",
		   "Wrong type provided for assignment: ");
		ERROR_MESSAGES.put("T_ASSIGN_FIELD_METHOD",
		   "Cannot assign to a method: ");
		ERROR_MESSAGES.put("T_ASSIGN_FIELD_OTHER",
		   "(Implementation Error) unknown member signature: ");
		ERROR_MESSAGES.put("T_ASSIGN_FIELD_MISSING",
		   "Field does not exist: ");
		ERROR_MESSAGES.put("T_ASSIGN_FIELD_NON_OBJECT_STRUCTURE",
			"Target object of assigment is not an object: ");
		ERROR_MESSAGES.put("T_ASSIGN_FIELD_NOT_ENOUGH_PERMISSION",
			"Insufficient permission for field assignment: ");
		ERROR_MESSAGES.put("T_ASSIGN_FIELD_WRONG_TARGET",
			"Target of field assignment must be an identifier");
		ERROR_MESSAGES.put("T_ASSIGN_PERM",
			"Assignment have permission none: ");
		ERROR_MESSAGES.put("T_METHOD_FIRST_CLASS",
		   "Methods are not first class values.");
		ERROR_MESSAGES.put("T_DEREF_MISSING",
		   "Field does not exist: ");
		ERROR_MESSAGES.put("T_DOUBLE_PERM",
		   "Double literals are immutable: ");
		ERROR_MESSAGES.put("T_INT_PERM",
		   "Integer literals are immutable: ");
		ERROR_MESSAGES.put("T_STRING_PERM",
		   "String literals are immutable: ");
		ERROR_MESSAGES.put("CHAIN_DEREF",
		   "Cannot chain dereferences");
		ERROR_MESSAGES.put("T_CASE_NOT_SUBSTRUCT", 
		   "Case pattern must be a substructure of matched structure: ");
		ERROR_MESSAGES.put("T_CASE_NOT_STATE", 
		   "Case pattern must be a state");
		ERROR_MESSAGES.put("T_MATCH_NO_CASES", 
		   "No cases found for match");
		ERROR_MESSAGES.put("MATCH_NON_ID",
		   "Match target expression must be an ID");
		ERROR_MESSAGES.put("T_CONTEXT_MERGE_FAIL",
		   "Could not merge contexts");
		ERROR_MESSAGES.put("T_NO_RESULT_LUB",
		   "No consistent result type");
		ERROR_MESSAGES.put("T_PATTERN_INVALID",
		   "Pattern must be a state or Java class: ");
		ERROR_MESSAGES.put("T_CALL_NOT_METHOD",
		   "Cannot call a non-method: ");
		ERROR_MESSAGES.put("T_CALL_MISSING",
		   "Method not found: ");
		ERROR_MESSAGES.put("T_CALL_ARG_NUM",
		   "Wrong number of arguments provided: ");
		ERROR_MESSAGES.put("T_CALL_WRONG_ARG_STRUCT",
		   "Incompatible argument structure provided: ");
		ERROR_MESSAGES.put("T_CALL_NON_OBJECT_STRUCTURE",
			"Target object of method call is not an object: ");
		ERROR_MESSAGES.put("T_APP_NOT_FUNCTION",
		   "Cannot call a non-function: ");
		ERROR_MESSAGES.put("T_APP_NOT_FUNCTION_OR_METHOD",
		   "Target of call is not a function or a method: ");
		ERROR_MESSAGES.put("RESTORE_VAR", 
			"Cannot restore to a 'var': ");	
		ERROR_MESSAGES.put("T_VAR_ALREADY_DECLARED",
			"Local variable name already in scope: ");	
		ERROR_MESSAGES.put("T_VAR_WRONG_STRUCT",
			"Local variable initializer gives wrong structure: ");	
		ERROR_MESSAGES.put("NESTED_STATES", 
			"Nested states not supported.");	
		ERROR_MESSAGES.put("STATE_COMPOSITION", 
			"State composition not supported.");	
		ERROR_MESSAGES.put("STATE_REF_WRONG_AST", 
			"States rep not supported: ");
		ERROR_MESSAGES.put("T_NEW_NON_STATE", 
			"Target of 'new' not a state: ");
		ERROR_MESSAGES.put("T_SPEC_ABSTRACT", 
			"Cannot specialize with an abstract member.");
		ERROR_MESSAGES.put("SPECIALIZATIONS", 
			"Specialize if and only if abstract.");
		ERROR_MESSAGES.put("T_NEW_ABSTRACT_MEMBER", 
			"No definition given for abstract member: ");
		ERROR_MESSAGES.put("T_NEW_BAD_SPEC", 
			"Invalid implementation of abstract member: ");
		ERROR_MESSAGES.put("T_NEW_SPEC_WRONG_SYM", 
			"Unexpected symbol for specialization: ");
		ERROR_MESSAGES.put("T_FIELD_DECL_STRUCT", 
			"Field Initializer structure is not a substructure of declared structure: ");
		ERROR_MESSAGES.put("T_METHOD_DECL_RCVR_STRUCT_IN", 
			"Receiver structure not guaranteed by declaring state: ");
		ERROR_MESSAGES.put("T_METHOD_DECL_MULTIPLE_INPUTS", 
			"Multiple inputs defined with the same name: ");
		ERROR_MESSAGES.put("T_METHOD_DECL_ENV_CHANGE", 
			"Method environment variables cannot consume permissions or change out of starting state: ");
		ERROR_MESSAGES.put("T_METHOD_DECL_ENV_SCOPE", 
			"Method environment variable not in scope at declaration: ");
		ERROR_MESSAGES.put("T_METHOD_DECL_ENV_STRUCT", 
			"Method environment variable has wrong structure: ");
		ERROR_MESSAGES.put("T_METHOD_DECL_RET_STRUCT", 
			"Method body does not provide required return structure: ");
		ERROR_MESSAGES.put("T_METHOD_DECL_RCVR_TYPE_OUT", 
			"Method receiver did not end with the right type: ");
		ERROR_MESSAGES.put("T_METHOD_DECL_ARG_TYPE_OUT", 
			"Arg did not end with the right type: ");
		ERROR_MESSAGES.put("T_METHOD_DECL_ENV_TYPE_OUT", 
			"Environment variable did not end with the right type: ");
		ERROR_MESSAGES.put("T_CHNG_NOT_CHANGEABLE", 
			"Cannot change state of object with permission ");
		ERROR_MESSAGES.put("T_CHNG_PERM",
			"State changes have permission none: ");
		ERROR_MESSAGES.put("T_METHOD_DECL_TOP_RCVR",
			"Receiver of top level method must be void: ");
		ERROR_MESSAGES.put("T_CHANGE_NOT_THIS",
			"Target of state change must be 'this'.");
		ERROR_MESSAGES.put("NON_VAR_STATE_CHANGE",
			"State change of non-variables not yet supported.");
		ERROR_MESSAGES.put("T_OP_NOT_METHOD",
			"Operator not a method: ");
		ERROR_MESSAGES.put("T_TOP_FIELD_PERM",
			"Cannot remove permissions from a package field.");
		ERROR_MESSAGES.put("T_LAMBDA_DECL_ENV_CHANGE", 
			"Lambda environment variables cannot consume permissions or change out of starting state: ");
		ERROR_MESSAGES.put("T_LAMBDA_DECL_ENV_SCOPE", 
			"Lambda environment variable not in scope at declaration: ");
		ERROR_MESSAGES.put("T_LAMBDA_DECL_ENV_STRUCT", 
			"Lambda environment variable has wrong structure: ");
		ERROR_MESSAGES.put("T_LAMBDA_DECL_RET_STRUCT", 
			"Lambda body does not provide required return structure: ");
		ERROR_MESSAGES.put("T_LAMBDA_INVALID_PERM", 
			"Lambda literal cannot be given permission: ");
		
		/*
		 * Aeminium Error messages
		 */
		
		ERROR_MESSAGES.put("A_ASSIGN_SHARED_OBJECT_FIELD",
		   "Assignment to fields of shared objects require protection of an atomic block.");
		ERROR_MESSAGES.put("A_DEPENDENCY_MISSING",
		   "Cannot find dependency information: ");
		ERROR_MESSAGES.put("A_NESTED_ATOMIC",
		   "Nested Atomic blocks found");
		
		/*
		 *  PERMISSION ERROR MESSAGES
		 */
		ERROR_MESSAGES.put("P_RETURN_FAIL",
				   "(Implementation Error) Invalid Permission Return: ");
		ERROR_MESSAGES.put("P_SPLIT_FAIL",
				"Insufficient Permission ");
		
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
		ERROR_MESSAGES.put("S_MERGE_FAILED",
				"Structures cannot be merged: ");
		
		/*
		 *  NAME RESOLUTION ERROR MESSAGES
		 */
		ERROR_MESSAGES.put("N_PACKAGE",
				"Packages are not first class values: ");
		ERROR_MESSAGES.put("N_JAVA_CLASS",
				"Java Classes are not first class values: ");
		ERROR_MESSAGES.put("N_QI_NOT_FOUND",
				"Could not resolve qualified identifier: ");
		ERROR_MESSAGES.put("N_ID_NOT_FOUND",
				"Could not resolve identifier: ");
		ERROR_MESSAGES.put("N_DUPLICATE_VAR",
			"Variable already declared: ");
		ERROR_MESSAGES.put("N_ALREADY_DECLARED",
			"Declaration already exists for name ");
		
		ERROR_MESSAGES.put("PARSER_ERROR",
			"Parse Error: ");
	}
	
	public static final String getMessage(String errorCode) {
		if (ERROR_MESSAGES.containsKey(errorCode)) {
			return ERROR_MESSAGES.get(errorCode);
		 } else {
			return "Unknown Error Code: " + errorCode;
		 }
		
	}
	
}
