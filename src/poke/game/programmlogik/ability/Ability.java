package poke.game.programmlogik.ability;

import poke.game.programmlogik.WrongArgumentException;

public class Ability {

	private String ability;
	private Effekt effekt;
	
	/**
	 * Konstruktor ohne Parameter / für Abilityreader
	 */
	public Ability() {
		
	}
	
	/**
	 * Konstruktor mit Parameter / für Pokemonreader
	 * @param t Text mit Name + Effekt
	 * @throws WrongArgumentException
	 */
	public Ability(String t) throws WrongArgumentException {
		for(int x = 0; x < t.length(); x++) {
			if(t.charAt(x) == 44) {
				this.setAbility(t.substring(0,x));
				Effekt e = new Effekt(t.substring(x+1));
				this.setEffekt(e);
			}
		}
	}
	
	/**
	 * Setzt den Namen der Ability
	 * @param name der Name
	 * @throws WrongArgumentException
	 */
	public void setAbility(String name) throws WrongArgumentException {
		if(name.length() < 2) {
			throw new WrongArgumentException();
		} else{
			this.ability = name;
		}
	}
	
	/**
	 * Setzt den Effekt der Ability
	 * @param e der Effekt
	 * @throws WrongArgumentException
	 */
	public void setEffekt(Effekt e) throws WrongArgumentException {
		if(e == null) {
			throw new WrongArgumentException();
		} else{
			this.effekt = e;
		}
		
	}
}
