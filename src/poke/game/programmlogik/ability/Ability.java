package poke.game.programmlogik.ability;

import poke.game.programmlogik.WrongArgumentException;

public class Ability {

	private String ability;
	private Effekt effekt;
	private String bezeichnung;
	
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
		int z = 0;
		int c = 0;
		for(int x = 0; x < t.length(); x++) {
			if(t.charAt(x) == 44) {
				if(z == 0) {
					this.setAbility(t.substring(0,x));
					c = x;
					z++;
				}
				if(z == 1) {
					Effekt e = new Effekt(t.substring(c, x));
					this.setEffekt(e);
					this.setBezeichnung(t.substring(x+1));
				}
				
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
	
	public void setBezeichnung(String s) throws WrongArgumentException {
		if(s.length() < 2) {
			throw new WrongArgumentException();
		} else {
			this.bezeichnung = s;
		}
	}
	
	public String getAbility() {
		return this.ability;
	}
}
