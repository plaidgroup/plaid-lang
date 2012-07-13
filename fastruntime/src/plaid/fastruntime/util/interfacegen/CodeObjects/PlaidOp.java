package plaid.fastruntime.util.interfacegen.CodeObjects;

import java.util.HashMap;

import plaid.fastruntime.NamingConventions;

public class PlaidOp extends SimplePlaidMethod {
	private static final HashMap<String,String> opNames = new HashMap<String,String>();
	static {
        opNames.put("=","eq");
        opNames.put("<","lt");
        opNames.put(">","gt");
        opNames.put("<=","lteq");
        opNames.put(">=","gteq");
        opNames.put("!=","bangeq");
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
	}
	
	private static final HashMap<String,Return> opReturns = new HashMap<String,Return>();
	static {
		opReturns.put("=",Return.Boolean);
		opReturns.put("<",Return.Boolean);
        opReturns.put(">",Return.Boolean);
		opReturns.put("<=",Return.Boolean);
        opReturns.put(">=",Return.Boolean);
        opReturns.put("!=",Return.Boolean);
        opReturns.put("!",Return.Boolean);
        opReturns.put("~",Return.Integer);
        opReturns.put("&",Return.Integer);
        opReturns.put("|",Return.Integer);
        opReturns.put("+",Return.Self);
        opReturns.put("-",Return.Self);
        opReturns.put("*",Return.Self);
        opReturns.put("/",Return.Self);
        opReturns.put("^",Return.Integer);
        opReturns.put("%",Return.Integer);
	}
	
	boolean generate = true;

	public PlaidOp(String operator, String javaObjectType, String plaidObjectType) {
		super("", (operator.equals("!") || operator.equals("~"))?0:1, 
				Return.Void, new String[(operator.equals("!") || operator.equals("~"))?1:2], 
				(operator.equals("!") || operator.equals("~"))?operator+"%s":"%s"+operator+"%s",
						plaidObjectType);
		if (!opNames.containsKey(operator) || !opReturns.containsKey(operator)) { 
			generate = false; 
			return; 
		}
			
		this.name = opNames.get(operator);
		parseFromReturn(opReturns.get(operator), plaidObjectType);
		
		for (int i = 0; i < this.castTypes.length; i++)
			this.castTypes[i] = javaObjectType;
	}
	
	@Override
	public String getGeneratedCode(CodeGenerator g) {
		
		return super.getGeneratedCode(g);
	}

}
