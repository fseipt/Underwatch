package poke.game.controllerlogik;

import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.move.Move;

public class Kampf {
	private Pokemon spieler;
	private Pokemon gegner;
	
	public Kampf(Pokemon s, Pokemon g) {
		this.spieler = s;
		this.gegner = g;
	}
	
	public boolean turn() {
		boolean x = false;
		int s = 0;
		int g = 0;
		int[] t = spieler.getStats();
		s = t[5];
		t = gegner.getStats();
		g  = t[5];
		if(s > g) {
			x = true;
		}
		return x;
 	}
	
	public int angriff(Move m, int z) {
		int hp = 0;
		if(z == 0) {
			
		}
		if(z == 1) {
			
		} if(z == 2) {
			
		}
		return hp;
	}
}
