package poke.game.programmlogik;

public class Stats {

	private int healthpoint;
	private int attack;
	private int defense;
	private int spezialattack;
	private int spezialdefense;
	private int speed;
	private int total;
	private int[] statsAmine = new int[6];
	private int[] stats = new int[9];
	private int zb = 0, zp = 0;
	private boolean zhelp = false;
	
	/**
	 * Konstruktor mit Parameter, schaut ob der Status legitim ist und setzt ihn danach
	 * @param s der Status als String
	 */
	public Stats(String s) {
		for(int x = 0; x < s.length(); x++) {
			if(s.charAt(x) == 44) { // checkt ob an dem Index ein Beistrich ist
				zb++; // erhoeht die Zaehlvariable
				zhelp = true; // setzt die variable auf true
			}
			if(zhelp) {
				switch(zb) {
			
				case 1: healthpoint = Integer.parseInt(s.substring(0,x)); // Setzt den HP Teil des Parameters
					break;
				case 2: attack = Integer.parseInt(s.substring(zp,x)); // Setzt den Angriff Teil des Parameters
					break;
				case 3: defense = Integer.parseInt(s.substring(zp,x)); // Setzt den Verteidung Teil des Parameters
					break;
				case 4: spezialattack = Integer.parseInt(s.substring(zp,x)); // Setzt den Spezialangriff Teil des Parameters
					break;
				case 5: spezialdefense = Integer.parseInt(s.substring(zp,x)); // Setzt den Speezialdefensive Teil des Parameters
					break;
				case 6: speed = Integer.parseInt(s.substring(x)); // Setzt den Speed Teil des Parameters
					break;
				}
			}
			if(s.charAt(x) == 44) {
					zp = (x+1); // erhoehen der Zaehlvariable
			}
			zhelp = false; // setzt die Variable auf false
		}
		for(int x = 0; x < this.stats.length; x++) {
			switch(x) {
			
			case 0: this.stats[x] = (((2*this.healthpoint+94)*100)/100)+110; // Setzt den ausgerechneten hp Stat
				break;
			case 1: this.stats[x] = (((2*this.attack+94)*100)/100)+5; // Setzt den ausgerechneten Angriff Stat
				break;
			case 2: this.stats[x] = (((2*this.defense+94)*100)/100)+5; // Setzt den ausgerechneten Verteidungs Stat
				break;
			case 3: this.stats[x] = (((2*this.spezialattack+94)*100)/100)+5; // Setzt den ausgerechneten Spezialangriff Stat
				break;
			case 4: this.stats[x] = (((2*this.spezialdefense+94)*100)/100)+5; // Setzt den ausgerechneten Spezailverteidiguns Stat
				break;
			case 5: this.stats[x] = (((2*this.speed+94)*100)/100)+5; // Setzt den ausgerechneten Speed Stat
				break;
			case 6: this.stats[x] = 1; // Der Genauigkeits multiplikator
				break;
			case 7: this.stats[x] = 1; // Der Fluchtwertsmultiplikator
				break;
			case 8: this.stats[x] = 20; // Die Chance auf einen Crit
			}
		}
		this.setStatsAmine();
		this.settotal();
	}
	
	/**
	 * Gibt Alle Stats zurueck
	 * @return 
	 */
	public int[] getStats() {
		return this.stats; // gibt die Stats als Array zurueck
	}
	/**
	 * Multipliziert alle Stats mit der Veraenderung
	 * @param a Die Veraenderung
	 */
	public void setStats(int[] a) {
		for(int x = 0; x < this.stats.length; x++) { 
			this.stats[x] = this.stats[x] * a[x]; // Setzt die Stats mit Status veraenderungen
		}
	}
	public void setHP(int i) throws WrongArgumentException {
		if(i < this.stats[0]) {
			this.stats[0] = i; // Setzt die HP
		} else {
			throw new WrongArgumentException();
		}
	}
	public void setA(double a) {
		this.stats[1] = (int) (this.stats[1] * a); // Setzt den Angriff
	}
	public void setD(double a) {
		this.stats[2] = (int) (this.stats[2] * a); // Setzt die Verteidigung
	}
	public void setSA(double a) {
		this.stats[3] = (int) (this.stats[3] * a); // Setzt den Spezialangriff
	}
	public void setSD(double a) {
		this.stats[4] = (int) (this.stats[4] * a); // Setzt die Spezialverteidung
	}
	public void setS(double a) {
		this.stats[5] = (int) (this.stats[5] * a); // Setzt den Speed
	}
	public void setG(double a) {
		this.stats[6] = (int) (this.stats[6] * a); // Setzt die Genaugkeit
	}
	public void setF(double a) {
		this.stats[7] = (int) (this.stats[7] * a); // Setzt den Fuchtwert
	}
	public void setC(double a) {
		this.stats[8] = (int) (this.stats[8] * a); // Setzt die Crit-Chance
	}
	
	
	/**
	 * Setzt die Basis stats in ein Array
	 */
	public void setStatsAmine() {
		this.statsAmine[0] = this.healthpoint;
		this.statsAmine[1] = this.attack;
		this.statsAmine[2] = this.defense;
		this.statsAmine[3] = this.spezialattack;
		this.statsAmine[4] = this.spezialdefense;
		this.statsAmine[5] = this.defense;
	}
	
	/**
	 * gibt das Basisstats-Array zurück
	 * @return das Basisstats-Array
	 */
	public int[] getStatsAmine() {
		return this.statsAmine;
	}
	
	/**
	 * Setzt den gesamten-"Basewert" eines Underlings
	 */
	public void settotal() {
		for(int x = 0; x < this.statsAmine.length; x++) {
			this.total += this.statsAmine[x];
		}
	}
	
	/**
	 * Gibt den gesamten-"Basewert" eines Underlings zurück
	 * @return den gesamten-"Basewert"
	 */
	public int getTotal() {
		return this.total;
	}
}