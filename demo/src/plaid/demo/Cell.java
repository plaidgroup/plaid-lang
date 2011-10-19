package plaid.demo;

import java.util.List;

/*
 * Framework will always call explore before calling next.
 */
public interface Cell<T extends Cell<T>> {
	/*
	 * Toggles this cell.
	 */
	public void toggle();
	
	/*
	 * The cell explores its surroundings. In the default game of life the cell will determine
	 * if it is lonely, crowded, content, should spawn, or remain dead. 
	 * @param neighbors The a list of neighbor objects in the underlying representation.
	 */
	public void explore(List<T> neighbors);
	
	/*
	 * Mutates the cell based on its exploration. 
	 */
	public void change();
	
	/*
	 * @returns a boolean representing whether this cell is alive
	 */
	public boolean isAlive();
	
}
