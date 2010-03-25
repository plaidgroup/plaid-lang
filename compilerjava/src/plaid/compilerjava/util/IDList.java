package plaid.compilerjava.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import plaid.compilerjava.AST.ID;

public class IDList implements Iterable<ID> {

	private List<ID> elts;
	
	public IDList(List<ID> elts) {
		this.elts = elts;
	}
	
	public IDList() {
		this.elts = new ArrayList<ID>();
	}

	public IDList add(ID id) {
		List<ID> newElts = new ArrayList<ID>();
		newElts.addAll(elts);
		newElts.add(id);
		return new IDList(newElts);
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
