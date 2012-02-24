package plaid.compilerjava.AST;

import java.util.List;

public class RewriteUtil {
	//Utility functions for the parser
	private static Expression getArg(ID pairID, int count) {
			return new Application(pairID.getToken(), new Dereference(getArg2(pairID, count), new ID("first")), new UnitLiteral());
	}

	private static Expression getArg2(ID pairID, int count)
	{
			if (count == 1)
			{
					return pairID;
			}
			else
			{
					return new Application(pairID.getToken(), new Dereference(getArg2(pairID, count - 1), new ID("second")), new UnitLiteral());
			}
	}

	public static Expression getBodyWithPairExtractions(List<ID> argList, ID argID, int count, Expression body)
	{
			if (argList.size() > 0)
			{
					return new LetBinding(argList.get(0).getToken(), argList.get(0), getArg(argID, count), getBodyWithPairExtractions(argList.subList(1, argList.size()), argID, count + 1, body), false);
			}
			else
			{
					return body;
			}
	}
}
