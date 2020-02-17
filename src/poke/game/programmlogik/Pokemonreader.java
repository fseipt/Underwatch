package poke.game.programmlogik;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import poke.game.programmlogik.ability.Ability;
import poke.game.programmlogik.ability.Abilityreader;
import poke.game.programmlogik.move.Move;
import poke.game.programmlogik.move.Movereader;
import poke.game.programmlogik.typ.Typ;
import poke.game.programmlogik.typ.Typreader;

public class Pokemonreader {
	
	private Pokemon pokemon;
	private Pokemon[] pokemons;
	private Move[] m;
	private Typ[] typ;
	private Ability[] a;
	private int[] b;
	private String[] att;
	
	/**
	 * Konstruktor: erzeugt ein Pokemon array und ruft die Reader Methode auf
	 */
	public Pokemonreader(Movereader m, Typreader t, Abilityreader a) {
		this.pokemons = new Pokemon[0]; // initialisiert das Pokemon Array
		this.typ = t.getTypen(); // initialisiert das Typ array
		this.a = a.getAbilities(); // initialisiert das Ability array
		this.m =m.getMoves(); // initialisiert das Move array
		this.reader(); // fuehrt die reader Methode aus 
		
	}
	
	/**
	 * veregoessert das int Array um 1 und fuegt den Parameter hinzu
	 * @param m der Move der in das Array hinzugefuegt wrid
	 */
	public void addEintragA(Pokemon p) {
		this.pokemons = Arrays.copyOf(pokemons, pokemons.length+ 1); // kopiert das Array und vergroessert es um 1
		this.pokemons[this.pokemons.length - 1] = p; // fuegt den Parameter zum Array hinzu
	}
	
	/**
	 * liesst ein CSV-File aus und fuellt ein Pokemon array
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
	 * veregoessert das int Array um 1 und fuegt den Parameter hinzu
	 * @param s der Index der Zeile an dem ein Beistrch ist
	 */
	public void addB(int s) {
		this.b = Arrays.copyOf(b, b.length + 1); // kopiert das Array unf vergroessert es um 1 
		this.b[this.b.length - 1] = s; // fuegt den Parameter zum Array hinzu
	}
	
	/**
	 * Zerlegt eine Zeile zu den einzelnden Attributen der Klasse Pokemon und erstellt ein Pokemon Objekt
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
		while(this.att[8].length()<3) {
			this.att[8] = "0"+this.att[8]; // setzt eine null vor den String
		}
		while(this.att[9].length()<3) {
			this.att[9] = "0"+this.att[9]; // setzt eine null vor den String
		}
		pokemon = new Pokemon(); // Neues Move Object
		try {
			pokemon.setName(this.att[0]); // Namen setzen
		 
			Typ[] t = new Typ[2]; // erzeugt ein Typ array
			for(int x = 1; x < 2; x++) {
				for(int y = 0; y < typ.length; y++) {
					if(this.att[x].equals(Allgemein.typen[y])) { // checkt ob der Typ existiret
						t[x-1] = typ[y]; // setzt den Typ in das gerade erstellte Typ array
					}
				}
			}
			pokemon.setTyp(t); // Typen setzen
			
			pokemon.setForm(Integer.parseInt(this.att[3])); // Form setzen
			pokemon.setGender(Integer.parseInt(this.att[4])); // Gender setzen
			pokemon.setWeight(Integer.parseInt(this.att[5])); // Weight Setzen
			pokemon.setHappiness(Integer.parseInt(this.att[6])); // Happiness Setzen
			
			Ability a = null; // Ability erzeugen
			for(int x = 0; x < this.a.length; x++) {
				if(this.att[7].equals(this.a[x].getAbility())) { // checkt ob die Ability exisitiert
					a = this.a[x]; // setzt die Ability in das Ability Objekt
				}
			}
			
			pokemon.setAbility(a); // Ability setzen
			pokemon.setBilder(this.att[8]); // Front Bild setzen
			pokemon.setBilder(this.att[9]); // Back BIld setzen
			pokemon.setBilder(this.att[10]); // Icon setzeen
			Stats stats = new Stats(this.att[11]); // Stats erzeugen
			pokemon.setStats(stats); // Stats setzen
			
			Move[] m = new Move[this.att.length-12];
			for(int x = 12; x < this.att.length; x++) {
				for(int y = 0; y < m .length; y++) {
					if(this.att[x].equals(this.m[y].getName())) {
						m[x-12] = this.m[y];
						break;
					}
				}
			}
			pokemon.setPossible(m); // setzt alle Moves die das Pokemon erlernen kann
			this.addEintragA(this.pokemon); // fuegt den neu erstellten Move zu der Liste hinzu
		} catch(WrongArgumentException wae) {
			System.err.println("Fehler beim speichern eines Moves: " + wae.getMessage());
		}
	}

	public Pokemon[] getPokemon() {
		return this.pokemons;
	}
}
