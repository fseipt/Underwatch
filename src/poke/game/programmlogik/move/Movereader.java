package poke.game.programmlogik.move;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.typ.Typ;
import poke.game.programmlogik.typ.Typreader;

public class Movereader {
	
	private Move move;
	private Move[] moves;
	private Typ[] t;
	private int[] b;
	private String[] att;
	
	/**
	 * Konstruktor: erzeugt ein Move array und ruft die reader Methode auf
	 */
	public Movereader(Typreader t) {
		this.moves = new Move[0]; // erzeugt ein Move Array
		this.t = t.getTypen();
		this.reader(); // fuehrt die reader Methode aus 
		
	}
	
	/**
	 * veregoessert das int Array um 1 und fuegt den Parameter hinzu
	 * @param m der Move der in das Array hinzugefuegt wrid
	 */
	public void addEintragA(Move m) {
		this.moves = Arrays.copyOf(moves, moves.length+ 1); // kopiert das Array unf vergroessert es um 1
		this.moves[this.moves.length - 1] = m; // fuegt den Parameter zum Array hinzu
	}
	
	/**
	 * liesst ein CSV-File aus und fuellt eine Move Array
	 */
	public void reader() {
		try(Scanner sc = new Scanner(new BufferedReader(new FileReader("res/Data/Move.csv")))) {
	      	  while(sc.hasNext()) {
	      		 addEintrag(sc.nextLine()); // gibt die naechste Zeile an die Methode weiter
	      	  }
	      	}catch(IOException e) {
				System.err.println("Fehler beim Schreiben: "+e.toString());
			} 
	}
	
	/**
	 * veregoessert das int Array um 1 und fuegt den Parameter hinzu
	 * @param s der Index der Zeile an dem ein Beistrch ist
	 */
	public void addB(int s) {
		this.b = Arrays.copyOf(b, b.length + 1); // kopiert das Array unf vergroessert es um 1 
		this.b[this.b.length - 1] = s; // fuegt den Parameter zum Array hinzu
	}
	
	/**
	 * Zerlegt eine Zeile zu den einzelnden Attributen der Klasse Move und erstellt ein Move Objekt
	 * @param s die Zeile
	 */
	public void addEintrag(String s) {
		b = new int[0];
		for(int x = 0; x < s.length(); x++) {
			if(s.charAt(x) == 59) {
				this.addB(x); // feugt den Index zum Array an dem ein Beistrich liegt
			}
		}
		this.att = new String[b.length +1]; // erstellt ein String array fuer die einzelnden
		for(int x = 0; x < b.length+1; x++) {
			if(x == 0) {
				this.att[x] = s.substring(x, b[x]); // setzt Namen auf den ersten Index
			} 
			if(x > 0 && x < b.length) {
				this.att[x] = s.substring(b[x -1]+1, b[x]); // setzt Index 2 - 5
			}
			if(x == (b.length)) {
				this.att[x] = s.substring(b[x-1]+1); // setzt Zusatz effekt zum letzten Index
			}
		}
		while(this.att[0].charAt(0) < 65 || this.att[0].charAt(0) > 90) {
			this.att[0] = this.att[0].substring(1);
		}
		move = new Move(); // Neues Move Object
		try {
			move.setMove(this.att[0]); // Namen setzen
			Typ t = new Typ();
			for(int x = 0; x < this.t.length; x++) {
				if(this.att[1].equals(this.t[x].getTyp())) {
					t = this.t[x];
					break;
				}
			}
			move.setTyp(t); // Typ setzen
			move.setStaerke(Integer.parseInt(this.att[2])); // Staerke setzen
			move.setArt(Integer.parseInt(this.att[3])); // Setzt die Art
			move.setGenauigkeit(Integer.parseInt(this.att[4])); // Genauigkeit setzem
			move.setAngriffspunkte(Integer.parseInt(this.att[5])); // AP setzen
			Zusatz z = new Zusatz(this.att[6]);
			move.setZusatz(z); // Effekte Setzen
			move.setPrio(Integer.parseInt(this.att[7]));
			this.addEintragA(this.move); // fuegt den neu erstellten Move zu der Liste hinzu
		} catch(WrongArgumentException wae) {
			System.err.println("Fehler beim speichern eines Moves: " + wae.getMessage());
		}
	}
	public Move[] getMoves() {
		return moves;
	}
}
