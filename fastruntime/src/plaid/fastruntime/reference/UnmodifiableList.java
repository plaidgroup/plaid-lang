package plaid.fastruntime.reference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnmodifiableList <T extends Comparable<? super T>> implements Iterable<T>{
	@SuppressWarnings("rawtypes")
	private static final UnmodifiableList<?> EMPTY = new UnmodifiableList();
	
	private final ArrayList<T> repList;
	
	@SuppressWarnings("unchecked")
	public static final <S extends Comparable<? super S>> UnmodifiableList<S> makeEmpty() {
		return (UnmodifiableList<S>) EMPTY;
	}
	
	public static final <S extends Comparable<? super S>> UnmodifiableList<S> makeSingle(S s) {
		return new UnmodifiableList<S> (s);
	}
	
	public static final <S extends Comparable<? super S>> UnmodifiableList<S> makeList(S... sList) {
		ArrayList<S> newRepList = new ArrayList<S>();
		for (S s : sList) {
			newRepList.add(s);
		}
		return new UnmodifiableList<S>(newRepList);
	}
	
	private UnmodifiableList() {
		repList = null;
	}
	
	private UnmodifiableList (T t) {
		repList = new ArrayList<T>();
		repList.add(t);
	}
	
	private UnmodifiableList (ArrayList<T> repList) {
		this.repList = repList;
	}
	
	public UnmodifiableList<T> append(UnmodifiableList<T> tList) {
		if(this == EMPTY) {
			return tList;
		} else if(tList == EMPTY) {
			return this;
		} else {
			ArrayList<T> newRepList = new ArrayList<T>(this.repList);
			newRepList.addAll(tList.repList);
			return new UnmodifiableList<T>(newRepList);
		}
	}
	
	/*
	 * Precondition T is not null
	 */
	public UnmodifiableList<T> cons(T t) {
		if(this == EMPTY) {
			return makeSingle(t);
		} else {
			ArrayList<T> newRepList = new ArrayList<T>(this.repList);
			newRepList.add(t);
			return new UnmodifiableList<T>(newRepList);
		}
	}
	
	public int length() {
		if (this == EMPTY) {
			return 0;
		} else{
			return this.repList.size();
		}
	}
	
	public T head() {
		return repList.get(0);
	}
	
	public T get(int index) {
		return repList.get(index);
	}
	
	public UnmodifiableList<T> tail() {
		assert this.length() > 1;
		ArrayList<T> newList = new ArrayList<T>();
		for(int i=1;  i<this.repList.size(); i++) {
			newList.add(this.repList.get(i));
		}
		return new UnmodifiableList<T>(newList);
	}
	
	
	public UnmodifiableList<T> sort() {
		if (this == EMPTY) {
			return this;
		} else {
			@SuppressWarnings("unchecked")
			ArrayList<T> newRep = (ArrayList<T>)this.repList.clone();
			Collections.sort(newRep);
			return new UnmodifiableList<T>(newRep);
		}
	}

	@Override
	public Iterator<T> iterator() {
		if (this == EMPTY) {
			return new Iterator<T>() {
				public boolean hasNext() {
					return false;
				}

				@Override
				public T next() {
					throw new NoSuchElementException("List is empty");
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}
			};
		} else {
			return repList.iterator();
		}
	}
}