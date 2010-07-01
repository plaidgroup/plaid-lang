package plaid.compilerjava.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import plaid.compilerjava.AST.ID;

public class IDList implements Iterable<ID> {

	private final List<ID> elts;
	
	public IDList(Collection<ID> elts) {
		this.elts = new ArrayList<ID>();
		this.elts.addAll(elts);
	}
	
	private IDList(Collection<ID> elts, ID newElt) {
		this(elts);
		this.elts.add(newElt);
	}
	
	public IDList() {
		this.elts = new ArrayList<ID>();
	}

	public IDList add(ID id) {
		return new IDList(elts, id);
	}
	
	public boolean contains(ID e) {
		return contains(e.getName());
	}
	
	public boolean contains(String s) {
		for (ID id : elts) {
			if (id.getName().equals(s)) return true;
		}
		return false;
	}
	
	@Override
	public Iterator<ID> iterator() {
		return elts.iterator();
	}

}
