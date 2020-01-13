package poke.game.programmlogik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class PokemonReader {
	
	private Pokemon pokemon;
	private Pokemon[] pokemons;
	private int[] b;
	private String[] att;
	
	/**
	 * Konstruktor: erzeugt ein Move array und ruft die reader Methode auf
	 */
	public PokemonReader() {
		this.pokemons = new Pokemon[0]; // erzeugt ein Move Array
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
		try(Scanner sc = new Scanner(new BufferedReader(new FileReader("./resource/Moves")))) {
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
			if(s.charAt(x) == ',') {
				this.addB(x); // feugt den Index zum Array an dem ein Beistrich liegt
			}
		}
		this.att = new String[b.length +1]; // erstellt ein String array fuer die einzelnden
		for(int x = 0; x < b.length+1; x++) {
			if(x == 0) {
				this.att[x] = s.substring(x, b[x]); // setzt Namen auf den ersten Index
			} 
			if(x > 0 && x < b.length) {
				this.att[x] = s.substring(b[x -1], b[x]); // setzt Index 2 - 5
			}
			if(x == (b.length+1)) {
				this.att[x] = s.substring(b[x]); // setzt Zusatz effekt zum letzten Index
			}
		}
		pokemon = new Pokemon(); // Neues Move Object
		try {
			pokemon.setName(this.att[1]); // Namen setzen
			pokemon.setTyp(this.att[2]); // Typen setzen
			pokemon.setTyp(this.att[3]); // Staerke setzen
			pokemon.setForm(Integer.parseInt(this.att[4])); // Genauigkeit setzem
			pokemon.setGender(Integer.parseInt(this.att[5])); // AP setzen
			pokemon.setWeight(Integer.parseInt(this.att[6])); // Effekte Setzen
			pokemon.setHappiness(Integer.parseInt(this.att[7])); // Effekte Setzen
			Ability a = new Ability(this.att[8]);
			pokemon.setAbility(a);
			pokemon.setBilder(this.att[9]);
			pokemon.setBilder(this.att[10]);
			pokemon.setBilder(this.att[11]);
			Stats stats = new Stats(this.att[12]);
			pokemon.setStats(stats);
			this.addEintragA(this.pokemon); // fuegt den neu erstellten Move zu der Liste hinzu
		} catch(WrongArgumentException wae) {
			System.err.println("Fehler beim speichern eines Moves: " + wae.getMessage());
		}
	}
	
}
