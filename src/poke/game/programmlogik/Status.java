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
	public String getStatus() {
		return this.status;
	}
	
	public Pokemon setStati(Pokemon p, Status s) throws WrongArgumentException {
		int z = 0;
		for(int x = 0; x < Allgemein.statusse.length; x++) {
			if(s.getStatus().equals(Allgemein.statusse[x])) {
				z = x;
			}
		}
		switch(z) {
			case 1: p.setStatus(new Status("Dead"));
					p.setHP(0);
				break;
			case 2: p.setStatus(new Status("WPoisened")); 
					p.setChance(100);
				break;
			case 3: p.setStatus(new Status("HPoisened"));
					p.setChance(100);
				break;
			case 4: p.setStatus(new Status("Freezed"));
					p.setChance(20);
				break;
			case 5: p.setStatus(new Status("Paralysed"));
					p.setChance(75);
				break;
			case 6: p.setStatus(new Status("Burned"));
					p.setChance(100);
				break;
			case 7: p.setStatus(new Status("Sleep"));
					p.setChance(0);
				break;
			case 8: p.setStatus(new Status("Flinched"));
					p.setChance(0);
				break;
			case 9: p.setStatus(new Status("Attracted"));
					p.setChance(50);
				break;
			case 10:p.setStatus(new Status("Confused"));
					p.setChance(500); 
				break;
		}
		return p;
	}
}
