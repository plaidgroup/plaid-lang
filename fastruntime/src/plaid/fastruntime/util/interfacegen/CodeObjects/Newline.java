package plaid.fastruntime.util.interfacegen.CodeObjects;

public class Newline implements CodeObject {

	@Override
	public String getGeneratedCode(CodeGenerator generator) {
		return "\n";
	}

}
