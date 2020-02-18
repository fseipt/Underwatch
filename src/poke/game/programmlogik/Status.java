package poke.game.programmlogik;

public class Status implements Allgemein{

	private String status;
	
	/**
	 * Konstruktur mit Paramter uebergibt den Parameter an die Setter-Methode
	 * @param s der Status
	 */
	public Status(String s) {
		this.setStatus(s);
	}
	
	/**
	 * Check ob der Status legitim ist und setzt ihn danach
	 * @param s der Status
	 */
	public void setStatus(String s) {
		boolean checkR = false; // deklarieren und initialisieren einer boolean Variable
		for(int x = 0; x < Allgemein.statusse.length; x++) {
			if(s.equals(Allgemein.statusse[x])) { // checkt ob der Status existiert
				checkR = true; // setzt die Variable auf true
			}
		}
		if(checkR == true) {
			this.status = s; // setzt den Status
		}
	}
	
}
