package poke.game.programmlogik;

import poke.game.programmlogik.ability.Ability;
import poke.game.programmlogik.move.Move;

public class Pokemon implements Allgemein{
	
	private String name;
	private String[] typ = new String[2];
	private int typi = 0;
	private int form;
	static final int level = 100;
	private int gender;
	private int weight;
	private int happiness;
	private Ability ability;
	private String[] bilder = new String[3];
	private int bildi;
	private Stats stat;
	private String item;
	private Move[] move = new Move[4];
	private int movi;
	private Status status;
	private Statusveraenderungen statusveraenderung;
	private boolean checkR = false;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setTyp(String typ) throws WrongArgumentException {
		this.checkR = false;
		for(int x = 0; x < Allgemein.typen.length; x++) {
			if(typ.equals(Allgemein.typen[x])) {
				checkR = true;
				break;
			}
		}
		if(checkR == true) {
			this.typ[typi] = typ;
			typi++;
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
		this.bilder[bildi] = bilder;
		this.bildi++;
	}
	public void setStats(Stats stats) {
		this.stat = stats;
	}
	public void setItem(String item) throws WrongArgumentException {
		this.checkR = false;
		for(int x = 0; x < Allgemein.typen.length; x++) { // ÄNDERN!!!
			if(item.equals(Allgemein.typen[x])) { // ÄNDERN!!!
				checkR = true;
			}
		}
		if(checkR == true) {
			this.item = item;
		}
		else {
			throw new WrongArgumentException();
		}
	}
	public void setMove(Move move) {
		this.move[movi] = move;
		this.movi++;
	}
	public void setStatus(Status status) throws WrongArgumentException {
		this.checkR = false;
		for(int x = 0; x < Allgemein.staten.length; x++) { // ÄNDERN!!!
			if(status.equals(Allgemein.staten[x])) { // ÄNDERN!!!
				checkR = true;
			}
		}
		if(checkR == true) {
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
