package poke.game.programmlogik;

public class Status implements Allgemein{

	private String status;
	private boolean checkR = false;
	
	
	public Status(String s) {
		this.setStatus(s);
	}
	
	public void setStatus(String s) {
		for(int x = 0; x < Allgemein.staten.length; x++) {
			if(s.equals(Allgemein.staten[x])) {
				checkR = true;
			}
		}
		if(checkR == true) {
			this.status = s;
		}
	}
	
}
