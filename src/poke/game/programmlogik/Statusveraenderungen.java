package poke.game.programmlogik;

public class Statusveraenderungen {

	private double attack = 1;
	private double defense = 1;
	private double spezialattack = 1;
	private double spezialdefense = 1;
	private double speed = 1;
	private double genauigkeit = 1;
	private double fluchtwert = 1;
	private double critchance = 1;
	private int[] multyplier;
	
	/**
	 * Ein Konstruktor ohne Parameter
	 */
	public Statusveraenderungen() {
		
	}
	/**
	 * Ein Konstruktor mit Parameter der den uebergebenen String ueberprueft und dann 
	 * die Statusveraenderungen setzt
	 * @param s der String 
	 * @throws WrongArgumentException
	 */
	public void setStatusveraenderungen(String s) throws WrongArgumentException {
		switch(s) {
			case "Attack":
				break;
			case "Defense":
				break;
			case "Specialattack":
				break;
			case "Specialdefense":
				break;
			case "Speed":
				break;
			case "Genauigkeit":
				break;
			case "Fluchtwert":
				break;
			case "Crit-Chance":
				break;
		}
		String[] stat = new String[8];
		multyplier = new int[8];
		double m = 0;
		int a = 0;
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < s.length(); x++) {
				if(s.charAt(x) == 47) {
					stat[y] = s.substring(a,x);
					a = x+1;
					multyplier[y] = Integer.parseInt(s.substring((x+1)));
					break;
				}
			}
			if(multyplier[y] > 6 || multyplier[y] < -6 ) {
				throw new WrongArgumentException();
			}
			if(multyplier[y] < 0) {
				m = 2 / (multyplier[y]+2);
			} else {
				m = (multyplier[y]+2) / 2;
			}
			if(stat[y].equals(Allgemein.stausveraenderungen[1])) {
				this.attack = m;
			}
			if(stat[y].equals(Allgemein.stausveraenderungen[2])) {
				this.defense = m;
			}
			if(stat[y].equals(Allgemein.stausveraenderungen[3])) {
				this.spezialattack = m;
			}
			if(stat[y].equals(Allgemein.stausveraenderungen[4])) {
				this.spezialdefense = m;
			}
			if(stat[y].equals(Allgemein.stausveraenderungen[5])) {
				this.speed = m;
			}
			if(stat[y].equals(Allgemein.stausveraenderungen[6])) {
				this.genauigkeit = m;
			}
			if(stat[y].equals(Allgemein.stausveraenderungen[7])) {
				this.fluchtwert = m;
			}
			if(stat[y].equals(Allgemein.stausveraenderungen[8])) {
				this.critchance = m;
			}
		}
	}
	/**
	 * gibt den Multiplier zurueck
	 * @return
	 */
	public int[] getMultti() {
		return this.multyplier; //gibt den Multiplier zurueck
	}
}
