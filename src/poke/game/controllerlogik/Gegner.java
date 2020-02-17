package poke.game.controllerlogik;

import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.Status;
import poke.game.programmlogik.Statusveraenderungen;
import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.item.Item;
import poke.game.programmlogik.move.Move;

public class Gegner extends Team{

	private Pokemon[] gegner; // Pokemon Array | Das Team
	private int z; // Zaehl Variable
	
	/**
	 * Konstruktor mit Parameter | setzt das Team mit 6 Random Pokemon
	 * @param p Gegnerreader Objekt
	 * @throws WrongArgumentException
	 */
	public Gegner(Gegnerreader p) throws WrongArgumentException {
		Pokemon[] team = new Pokemon[6];
		for(int x = 0; x < team.length; x++) {
			team[x] = p.random();
		}
		setTeam(team);
	}
	/**
	 * Setzt das Team mit dem Parameter
	 * @param p das Team
	 * @throws WrongArgumentException
	 */
	@Override
	public void setTeam(Pokemon[] p) throws WrongArgumentException {
		if(p.length > 6 || p.length < 0) {
			throw new WrongArgumentException();
		} else{
			this.gegner = p;
		}
	}

	/**
	 * Gibt alle 6 Icon von dem Team zurueck
	 * @return die 6 Icons, als String Array
	 */
	@Override
	public String[] getIcons() {
		String[] array = new String[6];
		for(int x = 0; x < this.gegner.length; x++) {
			array[x] = this.gegner[x].getIcon();
		}
		return array;
	}
	/**
	 * Wenn ein Pokemon wechselt, wird der Platz im Team zwischen den zwei getauscht
	 * @param p Das Pokemon das an erster Stelle kommt
	 * @throws WrongArgumentException
	 */
	@SuppressWarnings("unused")
	public void switchIn(Pokemon p) throws WrongArgumentException {
		int z = 10;
		for(int x = 0; x < this.gegner.length; x++) {
			if(this.gegner[x].equals(p)) {
				z = x;
			}
		}
		if(z > 6) {
			throw new WrongArgumentException();
		} else {
			this.gegner[z] = this.gegner[0];
			this.gegner[0] = p;
		}
	}
	
	/**
	 * gibt das ganze Team zurueck
	 * @return das Team
	 */
	public Pokemon[] getGegner() {
		return this.gegner;
	}
}
