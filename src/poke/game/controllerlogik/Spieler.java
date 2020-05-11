package poke.game.controllerlogik;

import java.io.Serializable;
import java.util.Arrays;

import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.Status;
import poke.game.programmlogik.Statusveraenderungen;
import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.item.Item;
import poke.game.programmlogik.move.Move;

public class Spieler extends Team implements Serializable {

	private static final long serialVersionUID = -5173021698010366589L;
	private Pokemon[] spieler; // Das Team des Spielers
	private Pokemon[] building; // Eine temporaere Variable, für diese Klasse
	private Move[] moves;
	private Item item;
	private Pokemon p; // ein Pokemon
	private int z; // zaehl Variable
	
	/**
	 * Konstruktor ohne Parameter, setzt nur die Arrays auf die passende groesse
	 * @throws WrongArgumentException
	 */
	public Spieler() {
		building = new Pokemon[0]; // setzt das building Array auf die groesse 0
		spieler = new Pokemon[6]; // setzt das Team auf die groesse, maximale groesse eines Teams
	}
	/**
	 * Setzt das Team mit dem Parameter
	 * @param p das Team
	 * @throws WrongArgumentException
	 */
	@Override
	public void setTeam(Pokemon[] t) throws WrongArgumentException {
		if(t.length > 6 || t.length < 0) { // checkt ob das Team die richtige groesse hat
			throw new WrongArgumentException();
		} else{
			this.spieler = t; // setzt das Team
		}
	}
	
	/**
	 * Gibt alle 6 Icon von dem Team zurueck
	 * @return die 6 Icons, als String Array
	 */
	@Override
	public String[] getIcons() {
		String[] array = new String[6]; // erstellt ein String Array, das so gross ist, wie das Team maximl gross sein kan
		for(int x = 0; x < this.spieler.length; x++) {
			array[x] = this.spieler[x].getIcon(); // speichert das Icon des Pokemons
		}
		return array;
	}
	
	/**
	 * Setzt alle zusaetzlichen Variablen fuer das Pokemon
	 * @param item das Item
	 * @param move die Moves
	 */
	public void setZusatz(Item item, Move[] move) {
		try {
			this.spieler[z].setItem(item); // Setzt das Item
			this.spieler[z].setMove(move); // Setzt die Moves
			this.spieler[z].setStatus(new Status("Alive")); // Setzt den Status
			this.spieler[z].setStatusveraenderungen(new Statusveraenderungen()); // Setzt die Statusveraenderung
			this.z++; // erhoet die Zaehlvariable
		} catch (WrongArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Wenn ein Pokemon wechselt, wird der Platz im Team zwischen den zwei getauscht
	 * @param p Das Pokemon das an erster Stelle kommt
	 * @throws WrongArgumentException
	 */
	@SuppressWarnings("unused")
	public void switchIn(Pokemon p) throws WrongArgumentException {
		int z = 10;
		for(int x = 0; x < this.spieler.length; x++) {
			if(this.spieler[x].equals(p)) {
				z = x;
			}
		}
		if(z > 6) {
			throw new WrongArgumentException();
		} else {
			this.spieler[z] = this.spieler[0];
			this.spieler[0] = p;
		}
	}
	
	/**
	 * Setzt das Pokemon 
	 * @param p
	 */
	public void setPokemon(Pokemon p) {
		this.p = p;
		this.building = Arrays.copyOf(this.building, this.building.length+1);
		this.building[this.building.length-1] = p;
		
	}
	
	/**
	 * loescht ein Pokemon aus dem Team
	 * @param i Index, welches geloescht wird
	 */
	public void remPokemon(int i) {
		if(building[i] != null) {
			this.building[i] = null;
		}
	}
	
	/**
	 * Gibt das Team des Spielers zurueck;
	 * @return 
	 */
	public Pokemon[] getSpieler() {
		return this.spieler;
	}
	
	/**
	 * gibt das Buildingteam zurueck
	 * @return
	 */
	public Pokemon[] getBuilding() {
		return this.building;
	}
	@Override
	public Pokemon getFirst() {
		return spieler[0];
	}
	public void setMoves() {
		this.moves = new Move[4]; 
	}
	public boolean checkMove(Move m) {
		for(int x = 0; x < this.moves.length; x++) {
			if(this.moves[x].equals(m)) {
				return false;
			}
		}
		return true;
	}
	public boolean setMove(Move m) {
		boolean check = true;
		check = this.checkMove(m);
		for(int x = 0; x < this.moves.length && check == true; x++) {
			if(this.moves[x] == null) {
				this.moves[x ]= m;
				break;
			}
		}
		return check;
	}
	/**
	 * gibt die Moves zurück
	 * @return
	 */
	public Move[] getMoves() {
		return this.moves;
	}
	/**
	 * gibt das Item zurück
	 * @return
	 */
	public Item getItems() {
		return this.item;
	}
}
