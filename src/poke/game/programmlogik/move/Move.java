package poke.game.programmlogik.move;

import poke.game.programmlogik.Allgemein;
import poke.game.programmlogik.WrongArgumentException;

public class Move implements Allgemein {

	private String name;
	private String typ;
	private int staerke;
	private int genauigkeit;
	private int angriffspunkte;
	private String zusatz;
	private boolean zcheckR = false;

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
	public void setZusatz(String z) {
		
	}
	
}
