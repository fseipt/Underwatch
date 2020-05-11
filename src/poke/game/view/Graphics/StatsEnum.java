package poke.game.view.Graphics;

public enum StatsEnum {
	ANG("Angriff",0,"Atk"),
	VERT("Verteidigung", 1,"Def"),
	SPANG("Spz.Angriff",2,"SpA"),
	SPVERT("Spz.Verteidigung",3,"SpD"),
	INIT("Initiative",4,"Spd"),
	GENAU("Genauigkeit",5,"Acc"),
	FLUCHT("Flucht",6,"Flee");
	
	private String text;
	private int index;
	private String abkuerzung;
	public String getText() {
		return text;
	}

	public int getIndex() {
		return index;
	}

	
	StatsEnum(String text, int index, String abkuerzung) {
		this.text = text;
		this.index = index;
		this.abkuerzung = abkuerzung;
	}
	
	public String getAbkuerzung() {
		return this.abkuerzung;
	}
}
