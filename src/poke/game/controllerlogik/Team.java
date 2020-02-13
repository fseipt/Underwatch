package poke.game.controllerlogik;

import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.WrongArgumentException;

public abstract class Team {

	public abstract void setTeam(Pokemon[] t) throws WrongArgumentException;
	
	public abstract String[] getIcons();
	
	
	
}
