package poke.game.view.gameState;

public enum Stats {
	ANG("Angriff",0),
	VERT("Verteidigung", 1),
	SPANG("Spz.Angriff",2),
	SPVERT("Spz.Verteidigung",3),
	INIT("Initiative",4),
	GENAU("Genauigkeit",5),
	FLUCHT("Flucht",6);
	
	private String text;
	public String getText() {
		return text;
	}

	public int getIndex() {
		return index;
	}

	private int index;
	
	private Stats(String text, int index) {
		this.text = text;
		this.index = index;
	}
}
