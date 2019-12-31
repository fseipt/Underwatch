package poke.game.programmlogik;

public abstract class Pokemon implements Typ{
	
	private String name;
	private String[] typ;
	private int form;
	static final int level = 100;
	private boolean gender;
	private int weight;
	private int happiness;
	private String item;
	private String ability;
	private Move[] move;
	private Stats[] stat;
	private Status status;
	private Statusveraenderungen[] statusveraenderung;
	
}
