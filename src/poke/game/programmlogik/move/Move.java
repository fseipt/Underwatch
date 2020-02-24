package poke.game.programmlogik.move;

import poke.game.programmlogik.Allgemein;
import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.typ.Typ;

public class Move implements Allgemein {

	private String name;


	private String typ;
	private int staerke;
	private int art;
	private String artS;
	private int genauigkeit;
	private int angriffspunkte;
	private Zusatz zusatz;
	private boolean zcheckR = false;
	
	public Move() {
		
	}
	
	
	public void setMove(String n) {
		name=n;
	}
	public void setTyp(String t) throws WrongArgumentException {
		this.zcheckR = false;
		for(int x = 0; x < Allgemein.typen.length; x++) {
			if(t.equals(Allgemein.typen[x])) {
				zcheckR = true;
			}
		}
		if(zcheckR == true) {
			this.typ = t;
		}
		else {
			throw new WrongArgumentException();
		}
	}
	public void setStaerke(int s) throws WrongArgumentException {
		if(s >= 0) {
			this.staerke = s;
		}
		else {
			throw new WrongArgumentException();
		}
	}
	/**
	 * Setzt die Art des Moves
	 * @param a die Art als int
	 * @throws WrongArgumentException
	 */
	public void setArt(int a) throws WrongArgumentException {
		if(a < 0 || a > 2 ) {
			throw new WrongArgumentException();
		} else {
			switch (a) {
				case 0: 
					this.art = a;
					this.artS = "Status";
					break;
				case 1:
					this.art = a;
					this.artS = "Physisch";
				case 2:
					this.art = a;
					this.artS = "Speziell";
			}
		}
	}
	public void setGenauigkeit(int g) throws WrongArgumentException {
		if(g >= 0 && g <= 100) {
			this.genauigkeit = g;
		}
		if(g > 100) {
			this.genauigkeit = 111;
		}
		if(g < 0) {
			throw new WrongArgumentException();
		}
	}
	public void setAngriffspunkte(int a) throws WrongArgumentException {
		this.zcheckR = false;
		for(int x = 0; x < Allgemein.ap.length; x++) {
			if(a == Allgemein.ap[x]) {
				zcheckR = true;
			}
		}
		if(zcheckR == true) {
			this.angriffspunkte = a;
		}
		else {
			throw new WrongArgumentException();
		}
	}
	public void setZusatz(Zusatz z) {
		this.zusatz = z;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Typ getTyp() {
		return null;
	}
	public int getStaerke() {
		return staerke;
	}


	public int getGenauigkeit() {
		return genauigkeit;
	}


	public int getAngriffspunkte() {
		return angriffspunkte;
	}


	public Zusatz getZusatz() {
		return zusatz;
	}
}
