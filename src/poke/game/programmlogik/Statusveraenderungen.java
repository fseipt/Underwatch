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
	public Statusveraenderungen(String s) throws WrongArgumentException {
		int c = 0; // deklarieren und initialisieren einer int Variable
		for(int x = 0; x < s.length(); x++) {
			if(s.charAt(x) == 58) { // checkt ob
				c++;
			}
		}
		String[] stat = new String[c];
		multyplier = new int[c];
		double m = 0;
		for(int y = 0; y < c; y++) {
			for(int x = 0; x < s.length(); x++) {
				if(s.charAt(x) == 47) {
					stat[y] = s.substring(0,x);
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
}
