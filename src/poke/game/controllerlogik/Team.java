package poke.game.controllerlogik;

import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.WrongArgumentException;

public abstract class Team {


	/**
	 * Setzt das Team mit dem Parameter
	 * @param p das Team
	 * @throws WrongArgumentException
	 */
	public abstract void setTeam(Pokemon[] t) throws WrongArgumentException;
	
	/**
	 * Gibt alle 6 Icon von dem Team zurueck
	 * @return die 6 Icons, als String Array
	 */
	public abstract String[] getIcons();
	
	public abstract Pokemon getFirst();
	
}
