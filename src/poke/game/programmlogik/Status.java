package poke.game.programmlogik;

public class Status implements Allgemein{

	private String status;
	
	
	public Status(String s) {
		this.setStatus(s);
	}
	
	public void setStatus(String s) {
		boolean checkR = false;
		for(int x = 0; x < Allgemein.statusse.length; x++) {
			if(s.equals(Allgemein.statusse[x])) {
				checkR = true;
			}
		}
		if(checkR == true) {
			this.status = s;
		}
	}
	
}
