package poke.game.programmlogik.move;

import poke.game.programmlogik.Allgemein;
import poke.game.programmlogik.Status;
import poke.game.programmlogik.Statusveraenderungen;
import poke.game.programmlogik.WrongArgumentException;

public class Zusatz {

	private Status status;
	private String sv;
	private String heal;
	private boolean target;
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
	/**
	 * Setzt den Effekt
	 * Entweder ein Status
	 * eine Statusveränderung
	 * oder das Underling wird gehealt
	 * @param s
	 * @throws WrongArgumentException
	 */
	public void setEffekte(String s) throws WrongArgumentException {
		for(int x = 0; x < s.length(); x++) {
			if(s.charAt(x) == 47) {
				if(s.substring(0,x).equals("S")) {
					target = true;
					s=s.substring(x+1);
					break;
				}
			}
		}
		String[] string = new String[6];
		for(int x = 0; x < s.length(); x++) {
			if(s.charAt(x) == 47) {
				string = new String[2];
				string[0] = s.substring(0,x);
				string[1] = s.substring(x+1);
			}
		}
		if(string[0] == null) {
			string = new String[1];
			string[0] = s;
		}
		for(int y = 0; y < string.length; y++) {
			boolean test = false;;
			for(int x = 0; x < Allgemein.statusse.length; x++) {
				if(string[y].equals(Allgemein.statusse[x])) {
					test = true;
					Status status = new Status(string[y]);
					this.status = status;
					break;
				}
			}
			if(test == false) {
				for(int x = 0; x < Allgemein.stausveraenderungen.length; x++) {
					if(string[y].equals(Allgemein.stausveraenderungen[x])) {
						test = true;
						this.sv += string[y];
						break;
					}
				}
			}
			if(test == false) {
				if(s.equals("Heal")) {
					this.heal = "50";
				}
			}
		}
	}
	public Status getStatus() {
		return this.status;
	}
}
	
	
