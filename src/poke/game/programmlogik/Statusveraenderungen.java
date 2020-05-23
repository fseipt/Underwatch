package poke.game.programmlogik;

public class Statusveraenderungen {

	private int attack = 0;
	private int defense = 0;
	private int spezialattack = 0;
	private int spezialdefense = 0;
	private int speed = 0;
	private int genauigkeit = 0;
	private int fluchtwert = 0;
	private int critchance = 20;
	private double[] multyplier;
	
	/**
	 * Ein Konstruktor ohne Parameter
	 */
	public Statusveraenderungen() {
		this.multyplier = new double[8];
	}
	/**
	 * Ein Konstruktor mit Parameter der den uebergebenen String ueberprueft und dann 
	 * die Statusveraenderungen setzt
	 * @param s der String 
	 * @throws WrongArgumentException
	 */
	public void setPStatusveraenderungen(String s) throws WrongArgumentException {
		for(int x = 0; x < this.multyplier.length; x++) {
			this.multyplier[x] = 1;
		}
		switch(s) {
			case "Attack": this.attack++;
				break;
			case "Defense": this.defense++;
				break;
			case "Specialattack": this.spezialattack++;
				break;
			case "Specialdefense": this.spezialdefense++;
				break;
			case "Speed": this.speed++;
				break;
			case "Genauigkeit": this.genauigkeit++;
				break;
			case "Fluchtwert": this.fluchtwert++;
				break;
			case "Crit-Chance": this.critchance = this.critchance+20;
				break;
		}
		setMulti();
	}
	public void setMStatusveraenderungen(String s) throws WrongArgumentException {
		for(int x = 0; x < this.multyplier.length; x++) {
			this.multyplier[x] = 1;
		}
		switch(s) {
			case "_Attack": this.attack--;
				break;
			case "_Defense": this.defense--;
				break;
			case "_Specialattack": this.spezialattack--;
				break;
			case "_Specialdefense": this.spezialdefense--;
				break;
			case "_Speed": this.speed--;
				break;
			case "_Genauigkeit": this.genauigkeit--;
				break;
			case "_Fluchtwert": this.fluchtwert--;
				break;
			case "_Crit-Chance": this.critchance = this.critchance-20;
				break;
		}
		setMulti();
	}
	/**
	 * gibt den Multiplier zurueck
	 * @return
	 */
	public double[] getMultti() {
		return this.multyplier; //gibt den Multiplier zurueck
	}
	/**
	 * Packt alle Werte in ein Array
	 * @return
	 */
	public int[] pack() {
		int[] i = new int[8];
		i[0] = this.attack;
		i[1] = this.defense;
		i[2] = this.spezialattack;
		i[3] = this.spezialdefense;
		i[4] = this.speed;
		i[5] = this.genauigkeit;
		i[6] = this.fluchtwert;
		i[7] = this.critchance;
		return i;
		
	}
	
	public void setMulti() {
		checkOver();
		int[] i = pack();
		for(int x = 0; x < i.length-1; x++) {
			if(i[x] > 0) {
				this.multyplier[x] = (i[x]+2)/2;
			}
			if(i[x] < 0) {
				this.multyplier[x] = 2/((-1*i[x])+2);
			}
		}
		
	}
	/**
	 * Ruft die Check Methoden auf
	 */
	public void checkOver() {
		checkOverP();
		checkOverM();
	}

	/**
	 * Checkt ob ein Wert zu hoch verrstärkt ist 
	 * und schwächt inh zum Maximum sollte der Fall eintreten
	 */
	public void checkOverP() {
		if(this.attack > 6) {
			this.attack = 6;
		}
		if(this.defense > 6) {
			this.defense = 6;
		}
		if(this.spezialattack > 6) {
			this.spezialattack = 6;
		}
		if(this.spezialdefense > 6) {
			this.spezialdefense = 6;
		}
		if(this.speed > 6) {
			this.speed = 6;
		}
		if(this.genauigkeit > 6) {
			this.genauigkeit = 6;
		}
		if(this.fluchtwert > 6) {
			this.fluchtwert = 6;
		}
		if(this.critchance > 100) {
			this.critchance = 100;
		}	
	}
	/**
	 * Checkt ob ein Stat zu stark geschwächt wurde
	 * und stärkt ihn zum Minimun sollte der Fall eintreten
	 */
	public void checkOverM() {
		if(this.attack < -6) {
			this.attack = -6;
		}
		if(this.defense < -6) {
			this.defense = -6;
		}
		if(this.spezialattack < -6) {
			this.spezialattack = -6;
		}
		if(this.spezialdefense < -6) {
			this.spezialdefense = -6;
		}
		if(this.speed < -6) {
			this.speed = -6;
		}
		if(this.genauigkeit < -6) {
			this.genauigkeit = -6;
		}
		if(this.fluchtwert < -6) {
			this.fluchtwert = -6;
		}
		if(this.critchance < 0) {
			this.critchance = 0;
		}	
	}
}
