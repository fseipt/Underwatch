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
	private int b;
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
		try(Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Herr Seipt\\git\\Underwatch\\resource\\Pokemon.csv")))) {
	      	  while(sc.hasNext()) {
	      		 addEintrag(sc.nextLine()); // gibt die naechste Zeile an die Methode weiter
	      	  }
	      	}catch(IOException e) {
				System.err.println("Fehler beim Schreiben: "+e.toString());
			}
	}
	
	/**
	 * Zerlegt eine Zeile zu den einzelnden Attributen der Klasse Move und erstellt ein Move Objekt
	 * @param s die Zeile
	 */
	public void addEintrag(String s) {
		for(int x = 0; x < s.length(); x++) {
			if(s.charAt(x) == 59) {
				b = x; // feugt den Index zum Array an dem ein Beistrich liegt
			}
		}
		this.att = new String[2]; // erstellt ein String array fuer die einzelnden
		this.att[0] = s.substring(0,b);
		this.att[1] = s.substring(b+1);
		
		ability = new Ability(); // Neues Move Object
		try {
			ability.setAbility(this.att[0]); // Namen setzen
			Effekt e = new Effekt(this.att[1]);
			ability.setEffekt(e); // Typen setzen
			this.addEintragA(this.ability); // fuegt den neu erstellten Move zu der Liste hinzu
		} catch(WrongArgumentException wae) {
			System.err.println("Fehler beim speichern eines Moves: " + wae.getMessage());
		}
	}
	
}
