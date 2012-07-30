package plaid.fastruntime.util.interfacegen.CodeObjects;

import java.util.HashMap;

import plaid.fastruntime.NamingConventions;
import plaid.fastruntime.util.interfacegen.CodeObjects.SimplePlaidMethod.Return;

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
			
		this.name = NamingConventions.getGeneratedIdentifier(operator);

		switch (opReturns.get(operator)) {
		case Boolean:
			this.returnType = "Util.bool(%s)";
			break;
		case Self:
			getReturn(javaObjectType, plaidObjectType, javaObjectType);
		}
		
		
		if (operator.equals("%")) operator = "%%";
		
		operation = (operator.equals("!") || operator.equals("~"))?operator+"%s":"%s"+operator+"%s";
		
		if (operator.equals("==")) {
			operation = "%s.equals(%s)";
		}
		
		methodBody = "";
		
		for (int i = 0; i < this.castTypes.length; i++)
			this.castTypes[i] = javaObjectType;
		
	}
	
	@Override
	protected String processMethodBody(String body, String indent) {
			StringBuilder outputBuilder = new StringBuilder();
			outputBuilder.append(indent+"try {\n");
			outputBuilder.append("\t"+body.replace("\n","\n\t")+"\n");
			outputBuilder.append(indent+"} catch (ClassCastException e) { \n"+indent+"\tthrow new PlaidIllegalOperationException(\"A invalid operation has been attempted.\",e); \n"+indent+"}\n");
			return outputBuilder.toString();
	}
	
	@Override
	public String getGeneratedCode(CodeGenerator g) {
		
		return super.getGeneratedCode(g);
	}

}
