package poke.game.programmlogik;

public class Pokemon implements Allgemein{
	
	private String name;
	private String[] typ;
	private int typi = 0;
	private int form;
	static final int level = 100;
	private int gender;
	private int weight;
	private int happiness;
	private Ability ability;
	private String[] bilder;
	private Stats stat;
	private String item;
	private Move[] move;
	private Status status;
	private Statusveraenderungen[] statusveraenderung;
	private boolean checkR = false;
	
	public void setName(String name) {
		
	}
	public void setTyp(String typ) throws WrongArgumentException {
		this.checkR = false;
		for(int x = 0; x < Allgemein.typen.length; x++) {
			if(typ.equals(Allgemein.typen[x])) {
				checkR = true;
			}
		}
		if(checkR == true) {
			this.typ[typi] = typ;
			typi++;
		}
		else {
			throw new WrongArgumentException();
		}
	}
	public void setForm(int form) {
	
	}
	public void setGender(int gender) {
	
	}
	public void setWeight(int weight) {
	
	}
	public void setHappiness(int happiness) {
	
	}
	public void setAbility(Ability ability) {
	
	}
	public void setBilder(String bilder) {
	
	}
	public void setStats(Stats stats) {
	
	}
	public void setItem(String item) {
	
	}
	public void setMove(Move[] move) {
	
	}
	public void setStatus(Status status) {
	
	}
	public void setStatusveraenderungen(Statusveraenderungen[] statusveraenderungen) {
}
	
	
}
