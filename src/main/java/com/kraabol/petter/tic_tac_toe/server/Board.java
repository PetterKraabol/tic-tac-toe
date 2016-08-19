/**
 * 
 */
package com.kraabol.petter.tic_tac_toe.server;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Petter
 * 
 * 	0 1 2
 *  3 4 5
 *  6 7 8
 *
 */
public class Board {
	private int size = 9;
	private List<Player> map = new ArrayList<Player>();
	
	public Board() {
		this.generateEmptyMap(this.size);
	}
	
	private void generateEmptyMap(int size) {
		while (this.map.size() <= size) {
			this.map.add(null);
		}
	}
	
	public void reset() {
		this.generateEmptyMap(this.size);
	}
	
	public boolean mark(int position, Player player) {
		
		if (this.map.get(position) == null) {
			this.map.set(position, player);
			return true;
		}
		
		return false;
	}
	
	public Player winner() {
		
		// Check diagonal top left to bottom right
		if (this.map.get(0) == this.map.get(4)
		 && this.map.get(4) == this.map.get(8)
		 && this.map.get(0) != null)
			return map.get(0);
		
		// Check diagonal bottom left to top right
		if (this.map.get(2) == this.map.get(4)
		 && this.map.get(4) == this.map.get(6)
		 && this.map.get(0) != null)
			return this.map.get(0);
		
		// Check horizontal Y and vertical X
		for (int x = 0, y = 0; y <= 6 && x <= 2; y += 3, x++){
			
			// Horizontal
			if (this.map.get(y) == this.map.get(y + 1)
			 && this.map.get(y) == this.map.get(y + 2)
			 && this.map.get(y) != null)
				return map.get(y);
		
			// Vertical
			if (this.map.get(x) == this. map.get(x + 3)
			 && this.map.get(x) == this.map.get(x + 6)
			 && this.map.get(x) != null)
				return this.map.get(x);
		
		}
		
		// No winner
		return null;
	}
	
	public boolean draw() {
		
		for (int i = 0; i < this.map.size(); i++) {
			if (this.map.get(i) == null) return false;
		}
		
		return true;
		
	}
	
}
