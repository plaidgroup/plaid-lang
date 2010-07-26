package plaid.compilerjava.util;

public abstract class MemberRep {
	
	private String name;
	private boolean recompiling = false;
	
	public MemberRep(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void startRecompilation() {
		recompiling = true;
	}
	
	public boolean isRecompiling() {
		return recompiling;
	}
	
}
