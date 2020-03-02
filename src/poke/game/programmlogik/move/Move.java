package poke.game.programmlogik.move;

import poke.game.programmlogik.Allgemein;
import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.typ.Typ;

public class Move implements Allgemein {

	private String name;


	private Typ typ;
	private int staerke;
	private int art;
	private String artS;
	private int genauigkeit;
	private int angriffspunkte;
	private Zusatz zusatz;
	private int prioritaet;
	private boolean zcheckR = false;
	
	public Move() {
		
	}
	
	
	public void setMove(String n) {
		name=n;
	}
	
	/**
	 * Setzt den Typne
	 * @param t der Typ
	 * @throws WrongArgumentException
	 */
	public void setTyp(Typ t) throws WrongArgumentException {
		this.typ = t;
	}
	
	/**
	 * Setzt die Staerke des Moves, diese darf nicht kleiner als 0 sein
	 * @param s die Staerke
	 * @throws WrongArgumentException
	 */
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
	
	/**
	 * Setzt die Genauigkeit, diese darf nicht groesser als 100 oder kleiner als 0 sein
	 * @param g die Genauigkeit
	 * @throws WrongArgumentException
	 */
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
	
	/**
	 * Setzt die Anzahl wie oft ein Movee eingesetzt werden kann, dieser ist nur valide 
	 * wenn er nicht groesser als 40 ist und durch 5 teilbar ist
	 * @param a die Anzahl
	 * @throws WrongArgumentException
	 */
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
	
	/**
	 * Setzt den Zusatz
	 * @param z
	 */
	public void setZusatz(Zusatz z) {
		this.zusatz = z;
	}
	
	/**
	 * Gibt den Namen des Moves zurueck
	 * @return der Name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gibt den Typen des Angriffes zurueck
	 * @return der Typ
	 */
	public Typ getTyp() {
		return null;
	}
	/**
	 * Gibt die Staerke des Moves zurueck 
	 * @return die staerke
	 */
	public int getStaerke() {
		return staerke;
	}
	/**
	 * Gibt die Genauigkeit des Moves zurueck
	 * @return die Genauigkeit
	 */
	public int getGenauigkeit() {
		return genauigkeit;
	}

	/**
	 * Gibt die Anzahl zurueck wie oft der Move eingesetzt werden kann
	 * @return die Anzahl
	 */
	public int getAngriffspunkte() {
		return angriffspunkte;
	}

	/**
	 * Gibt den Zusatz des Moves zurueck
	 * @return der Zusatz 
	 */
	public Zusatz getZusatz() {
		return zusatz;
	}
	/**
	 * Setzt die Prioritaet des moves
	 * @param i der prio wert
	 * @throws WrongArgumentException
	 */
	public void setPrio(int i) throws WrongArgumentException {
		if(i > 10 || i < -10) {
			throw new WrongArgumentException();
		} else {
			this.prioritaet = i;
		}
	}
}
