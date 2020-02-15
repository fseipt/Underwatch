package poke.game.controllerlogik;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.Pokemonreader;
import poke.game.programmlogik.Stats;
import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.ability.Ability;
import poke.game.programmlogik.item.Item;
import poke.game.programmlogik.item.Itemreader;
import poke.game.programmlogik.move.Move;

public class Gegnerreader {
	
	private Pokemon pokemon;
	private Pokemon[] pokemons;
	private Pokemon[] p;
	private Item[] i;
	private int[] b;
	private String[] att;
	
	/**
	 * Konstruktor: erzeugt ein Move array und ruft die reader Methode auf
	 */
	public Gegnerreader(Pokemonreader p, Itemreader i) {
		this.pokemons = new Pokemon[0]; // erzeugt ein Move Array
		this.p = p.getPokemon();
		this.i = i.getItems();
		this.reader(); // fuehrt die reader Methode aus 
		
	}
	
	/**
	 * veregoessert das int Array um 1 und fuegt den Parameter hinzu
	 * @param m der Move der in das Array hinzugefuegt wrid
	 */
	public void addEintragA(Pokemon p) {
		this.pokemons = Arrays.copyOf(pokemons, pokemons.length+ 1); // kopiert das Array unf vergroessert es um 1
		this.pokemons[this.pokemons.length - 1] = p; // fuegt den Parameter zum Array hinzu
	}
	
	/**
	 * liesst ein CSV-File aus und fuellt eine Move Array
	 */
	public void reader() {
		try(Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Herr Seipt\\git\\Underwatch\\resource\\Gegner.csv")))) {
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
		pokemon = new Pokemon(); // Neues Move Object
		try {
			
			for(int x = 0; x < this.p.length; x++) {
				if(this.att[0].equals(p[x].getName())) {
					pokemon = p[x];
				}
			}
			
			Item item = null;
			for(int x = 0; x < i.length; x++) {
				if(this.att[1].equals(i[x].getName())) {
					item = i[x];
				}
			}
			pokemon.setItem(item);
			
			Move[] m = new Move[4];
			Move[] po = pokemon.getPossible();
			for(int x = 2; x < this.att.length; x++) {
				for(int y = 0; y < po.length; y++) {
					if(this.att[x].equals(po[y].getName())) {
						m[x-2] = po[y];
					}
				}
			}
			pokemon.setMove(m);
			this.addEintragA(this.pokemon); // fuegt den neu erstellten Move zu der Liste hinzu
		} catch(WrongArgumentException wae) {
			System.err.println("Fehler beim speichern eines Gegners: " + wae.getMessage());
		}
	}

	
	public Pokemon random() {
		 return pokemons[(int) (Math.random() * this.pokemons.length) - 1];
	}
}
