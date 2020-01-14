package poke.game.programmlogik;

import poke.game.programmlogik.ability.Ability;
import poke.game.programmlogik.move.Move;

public class Pokemon implements Allgemein{
	
	private String name;
	private String[] typ = new String[2];
	private int zahl1 = 0;
	private int form;
	static final int level = 100;
	private int gender;
	private int weight;
	private int happiness;
	private Ability ability;
	private String[] bilder = new String[3];
	private int zahl2;
	private Stats stat;
	private String item;
	private Move[] move = new Move[4];
	private int zahl3;
	private Status status;
	private Statusveraenderungen statusveraenderung;
	private boolean zcheckR = false;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setTyp(String typ) throws WrongArgumentException {
		this.zcheckR = false;
		for(int x = 0; x < Allgemein.typen.length; x++) {
			if(typ.equals(Allgemein.typen[x])) {
				zcheckR = true;
				break;
			}
		}
		if(zcheckR == true) {
			this.typ[zahl1] = typ;
			zahl1++;
		}
		else {
			throw new WrongArgumentException();
		}
	}
	public void setForm(int form) {
		this.form = form;
	}
	public void setGender(int gender) throws WrongArgumentException {
		if(gender >= 0 && gender <= 3) {
			this.gender = gender;
		} else {
			throw new WrongArgumentException();
		}
	}
	public void setWeight(int weight) throws WrongArgumentException {
		if(weight >= 0) {
			this.weight = weight;
		}	else {
			throw new WrongArgumentException();
		}
	}
	public void setHappiness(int happiness) throws WrongArgumentException {
		if(happiness >= 0 && happiness <= 255) {
			this.happiness = happiness;
		}else {
			throw new WrongArgumentException();
		}
	}
	public void setAbility(Ability ability) {
		
	}
	public void setBilder(String bilder) {
		this.bilder[zahl2] = bilder;
		this.zahl2++;
	}
	public void setStats(Stats stats) {
		this.stat = stats;
	}
	public void setItem(String item) throws WrongArgumentException {
		this.zcheckR = false;
		for(int x = 0; x < Allgemein.typen.length; x++) { // ÄNDERN!!!
			if(item.equals(Allgemein.typen[x])) { // ÄNDERN!!!
				zcheckR = true;
			}
		}
		if(zcheckR == true) {
			this.item = item;
		}
		else {
			throw new WrongArgumentException();
		}
	}
	public void setMove(Move move) {
		this.move[zahl3] = move;
		this.zahl3++;
	}
	public void setStatus(Status status) throws WrongArgumentException {
		this.zcheckR = false;
		for(int x = 0; x < Allgemein.statusse.length; x++) { // ÄNDERN!!!
			if(status.equals(Allgemein.statusse[x])) { // ÄNDERN!!!
				zcheckR = true;
			}
		}
		if(zcheckR == true) {
			this.status = status;
		}
		else {
			throw new WrongArgumentException();
		}
		
	}
	public void setStatusveraenderungen(Statusveraenderungen statusveraenderungen) {
		this.statusveraenderung = statusveraenderungen;
}
	
	
}
