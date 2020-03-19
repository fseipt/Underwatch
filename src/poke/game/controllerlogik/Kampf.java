package poke.game.controllerlogik;

import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.move.Move;

public class Kampf {
	private Pokemon spieler;
	private Pokemon gegner;
	private Spieler s;
	private Gegner g;
	private boolean spielerTurn, gegnerTurn;
	private int turn;
	
	/**
	 * default Konstruktor
	 * @param s setzt das Spieler Pokemon
	 * @param g setzt das Gegner Pokemon
	 */
	public Kampf(Spieler s, Gegner g) {
		this.spieler = s.getFirst();
		this.gegner = g.getFirst();
		this.s = s;
		this.g = g;
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
	/**
	 * Checkt welches Pokemon zuerst Angreifft
	 * @param s Der Move des Spielers
	 * @param g Der Move des Gegners
	 * @return
	 */
	public boolean turn(Move s, Move g) {
		int sp = s.getPrioritaet();
		int ge = g.getPrioritaet();
		if(sp > ge) {
			return true;
		}
		if(sp == ge) {
			return this.turn();
		}
		if(sp < ge) {
			return false;
		}
		return false;
	}

	/**
	 * Checkt welche der Beiden Pokemon schneller ist
	 * @return true/false
	 */
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
	public void increase() {
		if(gegnerTurn == true && spielerTurn == true) {
			turn++;
			spielerTurn = false;
			gegnerTurn = false;
		}
	}
	public int getTurn() {
		return turn;
	}
	public void switchG(Pokemon p) {
		try {
			g.switchIn(p);
			gegnerTurn = true;
		} catch (WrongArgumentException e) {
			// TODO Auto-generated catch block
			System.err.println("Fehler: ");
			e.printStackTrace();
		}
	}
	public void switchS(Pokemon p) {
		try {
			s.switchIn(p);
			spielerTurn = true;
		} catch (WrongArgumentException e) {
			// TODO Auto-generated catch block
			System.err.println("Fehler: ");
			e.printStackTrace();
		}
	}
	
}
