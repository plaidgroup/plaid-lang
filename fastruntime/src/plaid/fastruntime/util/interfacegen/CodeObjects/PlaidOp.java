package plaid.fastruntime.util.interfacegen.CodeObjects;

import java.util.HashMap;

import plaid.fastruntime.NamingConventions;

public class PlaidOp extends SimplePlaidMethod {

	private static final HashMap<String,Return> opReturns = new HashMap<String,Return>();
	static {
		opReturns.put("==",Return.Boolean);
		opReturns.put("<",Return.Boolean);
        opReturns.put(">",Return.Boolean);
		opReturns.put("<=",Return.Boolean);
        opReturns.put(">=",Return.Boolean);
        opReturns.put("!=",Return.Boolean);
        opReturns.put("!",Return.Boolean);
        opReturns.put("~",Return.Self);
        opReturns.put("&",Return.Self);
        opReturns.put("|",Return.Self);
        opReturns.put("+",Return.Self);
        opReturns.put("-",Return.Self);
        opReturns.put("*",Return.Self);
        opReturns.put("/",Return.Self);
        opReturns.put("^",Return.Self);
        opReturns.put("%",Return.Self);
	}
	
	boolean generate = true;

	public PlaidOp(String operator, Class javaObjectType, String plaidObjectType) {
		super("", (operator.equals("!") || operator.equals("~"))?0:1, 
				Return.Void, new Class[(operator.equals("!") || operator.equals("~"))?1:2], 
				"", plaidObjectType);
		
		
		if (operator == null || !opReturns.containsKey(operator)) { 
			generate = false; 
			return; 
		}
			
		this.name = NamingConventions.convertOpStringToMethodName(operator);
		parseFromReturn(opReturns.get(operator), plaidObjectType);
		
		operation = (operator.equals("!") || operator.equals("~"))?operator+"%s":"%s"+operator+"%s";
		methodBody = "";
		
		for (int i = 0; i < this.castTypes.length; i++)
			this.castTypes[i] = javaObjectType;
	}
	
	@Override
	public String getGeneratedCode(CodeGenerator g) {
		
		return super.getGeneratedCode(g);
	}

}
