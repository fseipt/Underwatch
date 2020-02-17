package poke.game.programmlogik.ability;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.ability.Ability;

public class Abilityreader {
	
	private Ability ability;
	private Ability[] abilities;
	private int[] b;
	private String[] att;
	
	/**
	 * Konstruktor: erzeugt ein Move array und ruft die reader Methode auf
	 */
	public Abilityreader() {
		this.abilities = new Ability[0]; // erzeugt ein Move Array
		this.reader(); // fuehrt die reader Methode aus 
		
	}
	
	/**
	 * veregoessert das int Array um 1 und fuegt den Parameter hinzu
	 * @param m der Move der in das Array hinzugefuegt wrid
	 */
	public void addEintragA(Ability a) {
		this.abilities = Arrays.copyOf(abilities, abilities.length+ 1); // kopiert das Array unf vergroessert es um 1
		this.abilities[this.abilities.length - 1] = a; // fuegt den Parameter zum Array hinzu
	}
	
	/**
	 * liesst ein CSV-File aus und fuellt eine Move Array
	 */
	public void reader() {
		try(Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Herr Seipt\\git\\Underwatch\\resource\\Ability.csv")))) {
	      	  while(sc.hasNext()) {
	      		 addEintrag(sc.nextLine()); // gibt die naechste Zeile an die Methode weiter
	      	  }
	      	}catch(IOException e) {
				System.err.println("Fehler beim Schreiben: "+e.toString());
			}
	}
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
		this.att = new String[3]; // erstellt ein String array fuer die einzelnden
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
		
		ability = new Ability(); // Neues Move Object
		try {
			ability.setAbility(this.att[0]); // Namen setzen
			Effekt e = new Effekt(this.att[1]);
			ability.setEffekt(e); // Typen setzen
			ability.setBezeichnung(this.att[2]);
			this.addEintragA(this.ability); // fuegt den neu erstellten Move zu der Liste hinzu
		} catch(WrongArgumentException wae) {
			System.err.println("Fehler beim speichern eines Moves: " + wae.getMessage());
		}
	}
	
	public Ability[] getAbilities() {
		return this.abilities;
	}
}
