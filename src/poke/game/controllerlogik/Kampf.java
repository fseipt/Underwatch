package poke.game.controllerlogik;

import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.Status;
import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.ability.Ability;
import poke.game.programmlogik.item.Item;
import poke.game.programmlogik.move.Move;
import poke.game.programmlogik.typ.Typ;

public class Kampf {
	private Pokemon spieler;
	private int speedS;
	private Pokemon gegner;
	private int speedG;
	private Spieler s;
	private Gegner g;
	private String spi, geg;
	private boolean spielerTurn, gegnerTurn, faster;
	private int turn;
	private int poison;
	
	/**
	 * default Konstruktor
	 * @param s setzt das Spieler Pokemon
	 * @param g setzt das Gegner Pokemon
	 */
	public Kampf(Spieler s, Gegner g) {
		this.spieler = s.getFirst();
		this.gegner = g.getFirst();
		this.s = s;
		this.speedS = spieler.getStats()[5];
		this.g = g;
		this.speedG = gegner.getStats()[5];
		this.faster();
		
	}
	
	/**
	 * Evaluiert die Aktion die beide Spieler, wenn beide "Switchen" begintt das Underling 
	 * das einen höheren momentanen "Speedwert" hat. Wenn beide "Angreifen" greift das 
	 * Underling zu erst an mit einem höheren momentanen "Speedwert". Sonstig ist switchen 
	 * bevorzugt
	 * @param spieler Aktion des Spieler
	 * @param gegner Aktion des Gegners
	 */
	public void Auswahl(Object spieler, Object gegner) {
		if(spieler.getClass() == gegner.getClass()) {
			if(spieler.getClass() == this.spieler.getClass()) {
				if(faster) {
					this.switchS((Pokemon) spieler);
				} else {
					this.switchG((Pokemon) gegner);
				}
			}
			else {
				if(this.turn((Move) spieler, (Move) gegner)) {
					angriffS((Move) spieler);
					angriffG((Move) gegner);
				} else {
					angriffG((Move) gegner);
					angriffS((Move) spieler);
				}
			}
		}
		else {
			if(spieler.getClass() == this.spieler.getMoves()[0].getClass()) {
				switchG((Pokemon) gegner);
				angriffS((Move) spieler);
			} else {
				switchS((Pokemon) spieler);
				angriffG((Move) gegner);
			}
		}
	}
	
	/**
	 * Die Berechnungen für den Angriff des Usersq
	 * @param m
	 * @return
	 */
	public boolean angriffS(Move m)  {
		if(spieler.getStats()[0] == 0) {
			return false;
		}
		int hp = 0;
		int jz = gegner.getStats()[0];
		if(m.getArt() == 0) {
			
		}
		
		if(m.getArt() == 1) {
			double att = spieler.getStats()[2];
			double def = gegner.getStats()[3];
			double stab = 1;
			double item = 1;
			double burn = 1;
			double ability = 1;
			double sability = 1;
			double effe = 1;
			if(checkStab(spieler, m.getTyp())) {
				stab = 1.5;
				if(spieler.getAbility().getAbility().equals("Passend")) {
					stab = 2.0;
				}
			}
			item = checkItem(spieler.getItem());
			if(spieler.getStatus().getStatus().equals("Burned")) {
				burn = 0.5;
			}
			ability = checkAbility(spieler.getAbility());
			sability = checkAAbility(gegner.getAbility()); 
			effe = checkEffe(gegner, m.getTyp());
			hp = (int) ((int) m.getStaerke() * (att/def) * stab * item * burn * ability * sability * effe); 
		}
		
		if(m.getArt() == 2) {
			double satt = spieler.getStats()[4];
			double sdef = gegner.getStats()[5];
			double stab = 1;
			double item = 1;
			double ability = 1;
			double sability = 1;
			double effe = 1;
			if(checkStab(spieler, m.getTyp())) {
				stab = 1.5;
				if(spieler.getAbility().getAbility().equals("Passend")) {
					stab = 2.0;
				}
			}
			item = checkItem(spieler.getItem());
			ability = checkAbility(spieler.getAbility());
			sability = checkAAbility(gegner.getAbility()); 
			effe = checkEffe(gegner, m.getTyp());
			hp = (int) ((int) m.getStaerke() * (satt/sdef) *stab * item * ability * sability * effe); 
			
		}
		gegner.getStats()[9] -= hp;
		if(gegner.getStats()[9] < 1) {
			gegner.getStats()[9] = 0;
			try {
				gegner.setStatus(new Status("Dead"));
			} catch (WrongArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}	
	
	
	
	
	
	
	public boolean angriffG(Move m)  {
		if(gegner.getStats()[0] == 0) {
			return false;
		}
		int hp = 0;
		int jz = spieler.getStats()[0];
		if(m.getArt() == 0) {
			
		}
		
		if(m.getArt() == 1) {
			double att = gegner.getStats()[2];
			double def = spieler.getStats()[3];
			double stab = 1;
			double item = 1;
			double burn = 1;
			double ability = 1;
			double sability = 1;
			double effe = 1;
			if(checkStab(gegner, m.getTyp())) {
				stab = 1.5;
				if(gegner.getAbility().getAbility().equals("Passend")) {
					stab = 2.0;
				}
			}
			item = checkItem(gegner.getItem());
			if(gegner.getStatus().getStatus().equals("Burned")) {
				burn = 0.5;
			}
			ability = checkAbility(gegner.getAbility());
			sability = checkAAbility(spieler.getAbility()); 
			effe = checkEffe(spieler, m.getTyp());
			hp = (int) ((int) m.getStaerke() * (att/def) * stab * item * burn * ability * sability * effe); 
		}
		
		if(m.getArt() == 2) {
			double satt = gegner.getStats()[4];
			double sdef = spieler.getStats()[5];
			double stab = 1;
			double item = 1;
			double ability = 1;
			double sability = 1;
			double effe = 1;
			if(checkStab(gegner, m.getTyp())) {
				stab = 1.5;
				if(gegner.getAbility().getAbility().equals("Passend")) {
					stab = 2.0;
				}
			}
			item = checkItem(gegner.getItem());
			ability = checkAbility(gegner.getAbility());
			sability = checkAAbility(spieler.getAbility()); 
			effe = checkEffe(spieler, m.getTyp());
			hp = (int) ((int) m.getStaerke() * (satt/sdef) *stab * item * ability * sability * effe); 
			
		}
		spieler.getStats()[9] -= hp;
		if(spieler.getStats()[9] < 1) {
			spieler.getStats()[9] = 0;
			try {
				spieler.setStatus(new Status("Dead"));
			} catch (WrongArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	/**
	 * Checkt welches Pokemon zuerst Angreifft
	 * @param s Der Move des Spielers
	 * @param g Der Move des Gegners
	 * @return
	 */
	public boolean turn(Move s, Move g) {
		int sp = s.getPrioritaet();
		int ge = g.getPrioritaet();
		if(sp > ge) {
			return true;
		}
		if(sp == ge) {
			return this.faster;
		}
		if(sp < ge) {
			return false;
		}
		return false;
	}

	/**
	 * Checkt welche der Beiden Pokemon schneller ist
	 * @return true/false
	 */
	public void faster() {
		if(this.speedS > this.speedG) {
			this.faster = true;
		} else {
			this.faster = false;
		}
		
 	}
	public void increase() {
		if(gegnerTurn == true && spielerTurn == true) {
			turn++;
			spielerTurn = false;
			gegnerTurn = false;
		}
	}
	public int getTurn() {
		return turn;
	}
	public void switchG(Pokemon p) {
		try {
			g.switchIn(p);
			resetGegner();
			gegnerTurn = true;
		} catch (WrongArgumentException e) {
			// TODO Auto-generated catch block
			System.err.println("Fehler: ");
			e.printStackTrace();
		}
	}
	public void switchS(Pokemon p) {
		try {
			s.switchIn(p);
			resetSpieler();
			spielerTurn = true;
		} catch (WrongArgumentException e) {
			// TODO Auto-generated catch block
			System.err.println("Fehler: ");
			e.printStackTrace();
		}
	}
	public void resetSpieler() {
		this.spieler = this.s.getFirst();
		this.speedS = this.spieler.getStats()[5];
		this.faster();
	}
	public void resetGegner() {
		this.gegner = this.g.getFirst();
		this.speedG = this.spieler.getStats()[5];
		this.faster();
	}
	
	public boolean checkStab(Pokemon p, Typ t) {
		boolean multi = false;
		Typ[] types = p.getTyp();
		for(int x = 0; x < types.length; x++) {
			if(types[x].equals(t)) {
				multi = true;
			}
		}
		return multi;
	}
	
	public double checkItem(Item i) {
		double multi = 1;
		
		return multi;
	}
	
	public double checkAbility(Ability a) {
		double multi = 1;
		
		return multi;
	}
	
	public double checkAAbility(Ability a) {
		double multi = 1;
		
		return multi;
	}
	
	public double checkEffe(Pokemon p, Typ t) {
		double multi = 1;
		boolean immu = false;
		Typ[] types = p.getTyp();
		for(int x = 0; x < types.length && immu == false; x++) {
			for(int y = 0; y < t.getImm().length; y++) {
				if(t.getImm()[y].equals(types[x])) {
					multi = 0;
					immu = true;
					break;
				}
			}
			for(int y = 0; y < t.getZweiRes().length; y++) {
				if(t.getZweiRes()[y].equals(types[x])) {
					multi = multi *0.5;
					break;
				}
			}
			for(int y = 0; y < t.getZweiEff().length; y++) {
				if(t.getZweiEff()[y].equals(types[x])) {
					multi = multi  * 2;
					break;
				}
			}
			
		}
		return multi;
	}
}
