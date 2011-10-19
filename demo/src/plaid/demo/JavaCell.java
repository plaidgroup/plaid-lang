package plaid.demo;

import java.awt.Color;
import java.util.List;

public class JavaCell implements Cell<JavaCell>{

	private boolean alive;
	private boolean next;
	
	public JavaCell(boolean alive) {
		this.alive = alive;
	}
	
	@Override
	public void toggle() {
		alive = !alive;
	}

	@Override
	public void explore(List<JavaCell> neighbors) {
		int numAlive = 0;
		for(JavaCell neighbor : neighbors) {
			if(neighbor.alive) {
				numAlive++;
			}
		}
		if(alive) {
			if(numAlive < 2 || numAlive > 3) {
				next = false;
			}
			else {
				next = true;
			}
		} else if(numAlive > 2) {
			next = true;
		}
	}
	
	@Override
	public void change() {
		alive = next;
	}


	@Override
	public Color getColor() {
		if (alive) {
			return Color.BLUE;
		} else {
			return Color.GRAY;
		}
	}
	
}
