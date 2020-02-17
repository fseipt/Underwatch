package poke.game.programmlogik.typ;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import poke.game.programmlogik.WrongArgumentException;

public class Typreader {
	
	private Typ typ;
	private Typ[] typen;
	private int[] b;
	private String[] att;
	
	/**
	 * Konstruktor: erzeugt ein Move array und ruft die reader Methode auf
	 */
	public Typreader() {
		this.typen = new Typ[0]; // erzeugt ein Move Array
		this.reader(); // fuehrt die reader Methode aus 
		
	}
	
	/**
	 * veregoessert das int Array um 1 und fuegt den Parameter hinzu
	 * @param m der Move der in das Array hinzugefuegt wrid
	 */
	public void addEintragA(Typ t) {
		this.typen = Arrays.copyOf(typen, typen.length+ 1); // kopiert das Array unf vergroessert es um 1
		this.typen[this.typen.length - 1] = t; // fuegt den Parameter zum Array hinzu
	}
	
	/**
	 * liesst ein CSV-File aus und fuellt eine Move Array
	 */
	public void reader() {
		try(Scanner sc = new Scanner(new BufferedReader(new FileReader("res/Data/Typ.csv")))) {
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
		for(int x = 0; x <= b.length+1; x++) {
			if(x == 0) {
				this.att[x] = s.substring(x, b[x]); // setzt Namen auf den ersten Index
			} 
			if(x > 0 && x < b.length) {
				this.att[x] = s.substring((b[x -1]+1), b[x]); // setzt Index 2 - 5
			}
			if(x == (b.length)) {
				this.att[x] = s.substring((b[x-1]+1)); // setzt Zusatz effekt zum letzten Index
			}
		}
		
		Typ t = new Typ(); // Neues Move Object
		try {
			if(!(this.att[0].equals("null"))) {
				t.setTyp(this.att[0]);
			}
			if(!(this.att[1].equals("null"))) {
				t.setImm(this.att[1]);
			}
			if(!(this.att[2].equals("null"))) {
				t.setZweiRes(this.att[2]);
			}
			if(!(this.att[3].equals("null"))) {
				t.setZweiEff(this.att[3]);
			}
			this.addEintragA(t); // fuegt den neu erstellten Move zu der Liste hinzu
		} catch(WrongArgumentException wae) {
			System.err.println("Fehler beim speichern eines Gegners: " + wae.getMessage());
		}
	}

	
	public Typ[] getTypen() {
		return this.typen;
	}
}
