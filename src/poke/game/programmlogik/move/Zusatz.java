package poke.game.programmlogik.move;

import poke.game.programmlogik.Allgemein;
import poke.game.programmlogik.Status;
import poke.game.programmlogik.Statusveraenderungen;
import poke.game.programmlogik.WrongArgumentException;

public class Zusatz {

	private Status status;
	private String sv;
	private int chance;
	private String[] s = new String[2];

	public Zusatz(String s) throws NumberFormatException, WrongArgumentException {
		for(int x = 0; x < s.length(); x++) {
			if(s.charAt(x) == 44) {
				this.s[0] = s.substring(0,x);
				this.s[1] = s.substring((x+1));
				break;
			}
		}
		this.setChance(Integer.parseInt(this.s[1]));
		this.setEffekte(this.s[0]);
	}

	public void setChance(int x) throws WrongArgumentException {
		if(x > 0 ) {
			this.chance = x;
		}
		else {
			throw new WrongArgumentException();
		}		
	}
	public void setEffekte(String s) throws WrongArgumentException {
		boolean test = false;;
		for(int x = 0; x < Allgemein.statusse.length; x++) {
			if(s.equals(Allgemein.statusse[x])) {
				test = true;
				Status status = new Status(s);
				this.status = status;
			}
		}
		if(test == false) {
			for(int x = 0; x < Allgemein.stausveraenderungen.length; x++) {
				if(s.equals(Allgemein.stausveraenderungen[x])) {
					test = true;
					this.sv = s;
				}
			}
		}
		if(test == false) {
			throw new WrongArgumentException();
		}
	}
}
	
	
