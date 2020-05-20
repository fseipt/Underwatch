package poke.game.programmlogik;

import poke.game.programmlogik.ability.Ability;
import poke.game.programmlogik.item.Item;
import poke.game.programmlogik.move.Move;
import poke.game.programmlogik.typ.Typ;

public class Pokemon implements Allgemein{
	
	private String name;
	private Typ[] typ = new Typ[2];
	private int zahl1 = 0;
	private int form;
	static final int level = 100;
	private String gender;
	private int weight;
	private int happiness;
	private Ability ability;
	private String[] bilder = new String[3];
	private int zahl2 = 0;
	private Stats stat;
	private String basis;
	private Item item;
	private Move[] move = new Move[4];
	private Status status;
	private Statusveraenderungen statusveraenderung;
	private boolean zcheckR = false;
	private Move[] possible;
	private int chance;
	
	public Pokemon() {
		
	}
	/**
	 * Konstruktor mit Parametern
	 * @param name der Name
	 * @param t die Typen
	 * @param form die form
	 * @param gender das Geschlecht
	 * @param weight das Gewicht
	 * @param happiness die Gluecklichkeit
	 * @param ab die Faehigkeit
	 * @param fb das vordere Bild
	 * @param bb das hintere Bild
	 * @param i das Icon
	 * @param stats die Stats
	 * @param m Die Moves
	 * @throws WrongArgumentException
	 */
	public Pokemon(String name, Typ[] t, int form, int gender, int weight, int happiness, Ability ab,String fb, String bb, String i,Stats stats, Move[] m ) throws WrongArgumentException {
		this.setName(name);
		this.setTyp(t);
		this.setForm(form);
		this.setGender(gender);
		this.setWeight(weight);
		this.setHappiness(happiness);
		this.setAbility(ab);
		this.setBilder(fb);
		this.setBilder(bb);
		this.setBilder(i);
		this.setStats(stats);
		this.setPossible(m);
	}
	/**
	 * Setzt den Namen
	 * @param name der Name
	 */
	public void setName(String name) {
		this.name = name; // setzt den Namen
	}
	
	/**
	 * Setzt den Typ
	 * @param typ der Typ
	 */
	public void setTyp(Typ[] typ) {
		this.typ = typ;
	}
	public Typ[] getTyp() {
		return this.typ;
	}
	/**
	 * Setzt die Form des Pokeemons
	 * @param form die Form
	 */
	public void setForm(int form) {
		this.form = form;
	}
	public int getForm() {
		return this.form;
	}
	/**
	 * Setzt das Geschlecht des Pokemons
	 * @param gender das Geschlecht als int
	 * @throws WrongArgumentException
	 */
	public void setGender(int gender) throws WrongArgumentException {
		if(gender > 0 && gender < 4) {
			switch(gender) {
				case 1: this.gender = "Male"; // Setzt das Geschlecht auf Maennlich
					break;
				case 2: this.gender = "Female"; // Setzt das Geschlecht auf Weiblich
					break;
				case 3: this.gender = "Genderless"; // Setzt das Geschlecht auf kein Geschlecht
					break;
			}
		} else {
			throw new WrongArgumentException();
		}
	}
	public int getGender() {
			switch(this.gender) {
				case "Male": return 1; 
				case "Female": return 2;
				case "Genderless": return 3;
		}
		return 3;
	}
	
	public String getGenderS() {
		return this.gender;
	}
	/**
	 * Setzt das Gewicht des Pokemons
	 * @param weight Das Gewicht
	 * @throws WrongArgumentException
	 */
	public void setWeight(int weight) throws WrongArgumentException {
		if(weight >= 0) { // checkt ob das Gewicht positiv
			this.weight = weight; // Setzt das Gewicht
		}	else {
			throw new WrongArgumentException();
		}
	}
	public int getWeight() {
		return this.weight;
	}
	/**
	 * Setzt die Gluecklichkeit des Pokemons
	 * @param happiness Die Gluecklichkeit
	 * @throws WrongArgumentException
	 */
	public void setHappiness(int happiness) throws WrongArgumentException {
		if(happiness >= 0 && happiness <= 255) { // checkt ob die Gluecklichkeit "passend" ist
			this.happiness = happiness; // setzt die Gluecklichkeit
		}else {
			throw new WrongArgumentException();
		}
	}
	
	public int getHappiness() {
		return this.happiness;
	}
	/**
	 * Setzt die Faehigkeit
	 * @param ability die Faehigkeit
	 */
	public void setAbility(Ability ability) {
		this.ability = ability; // setzt die Faehigkeit
	}
	public Ability getAbility() {
		return this.ability;
	}
	/**
	 * Setzt alle Bilder
	 * @param bilder ein Bild
	 */
	public void setBilder(String bilder) {
		this.bilder[zahl2] = bilder; // setzt das Bild
		this.zahl2++; // erhoet diie Zaehlvariable um 1
	}
	
	/**
	 * Setzt die Stats des Pokemon
	 * @param stats die Stats
	 */
	public void setStats(Stats stats) {
		this.stat = stats; //setzt die Stats
	}
	public void setBasis(String b) {
		this.basis = b;
	}
	public String getBasis() {
		return this.basis;
	}
	/**
	 * Setzt das Item des Pokemon
	 * @param item das Item
	 * @throws WrongArgumentException
	 */
	public void setItem(Item item) throws WrongArgumentException {
		this.item = item; // setzt das item
		
	}
	/**
	 * gibt das Item zurück
	 * @return das Item
	 */
	public Item getItem() {
		return this.item;
	}
	/**
	 * Setzt ein Movearray auf die Moves des Pokemnons
	 * @param move
	 * @throws WrongArgumentException
	 */
	public void setMove(Move[] move) throws WrongArgumentException {
		boolean t = false; // deklarieren und initialisieren einer boolean Variable
		boolean t2 = true; // deklarieren und initialisieren einer boolean Variable
		for(int x = 0; x < move.length; x++) {
			t = false; // setzt die Varaible auf false;
			for(int y = 0; y < this.possible.length; y++) {
				if(move[x] == null) { // checkt ob das Move Object null ist
					t = true; // setzt die Variable auf true
					break;
				} else {
					if(move[x].equals(this.possible[y])) { // checkt ob das Pokemon den Move erlernen kann
						t = true; // setzt die Variable auf true
					}
				}
			}
			if(t == false) {
				t2 = false; // setzt die Variable auf false
				break; 
			}
		} if(t2 == true) {
			this.move = move; // setzt den Move
		} else {
			throw new WrongArgumentException();
		}
	}
	/**
	 * gibt die Moves dies das Pokemon hat zurueck
	 * @return die Moves
	 */
	public Move[] getMoves() {
		return this.move;
	}
	/**
	 * Setzt den Status
	 * @param status der Status
	 * @throws WrongArgumentException
	 */
	public void setStatus(Status status) throws WrongArgumentException {
		this.status = status;
		
	}
	/**
	 * Gibt den Status zurück
	 * @return der Status
	 */
	public Status getStatus() {
		return this.getStatus();
	}
	public void setStatusveraenderungen(Statusveraenderungen statusveraenderungen) {
		this.statusveraenderung = statusveraenderungen; // Setzt die Status veraenderung
	}
	
	/**
	 * Gibt das Bild von vorne zurueck
	 * @return 
	 */
	public String getVBild() {
		return this.bilder[0]; // gibt das Bild zurueck
	}
	/**
	 * Gibt das Bild von hinten zurueck
	 * @return 
	 */
	public String getBBild() {
		return this.bilder[1]; // gibt das Bild zurueck
	}
	/**
	 * Gibt das Icon zurueck
	 * @return 
	 */
	public String getIcon() {
		return this.bilder[2]; // gibt das Icon zurueck
	}
	
	/**
	 * Setzt die Moves die das jeweilige Pokemon erlernen kann
	 * @param m die Moves
	 */
	public void setPossible(Move[] m) {
		this.possible = m; // Setzt die Moves
	}
	
	/**
	 * Gibt alle Moves die das Pokemon erlernen kann zurueck
	 * @return
	 */
	public Move[] getPossible() {
		return this.possible; // gibt die Moves zurueck
	}
	/**
	 * gibt den Namen des Pokemons zurueck
	 * @return
	 */
	public String getName() {
		return this.name; // gibt den Namen zurueck
	}
	/**
	 * setzt die Chance einen Move zu hitten
	 * @throws WrongArgumentException 
	 */
	public void setChance(int x) throws WrongArgumentException {
		if(x < 0 && x > 100) {
			throw new WrongArgumentException();
		}
		this.chance = x;
	}
	/**
	 * Gibt zurueck ob der Move trifft
	 */
	public boolean getChance() {
		if((int) ((Math.random() * 101) + 1) -1 < this.chance) {
			return true;
		} else {
			return false;
		}
		
	}
	/**
	 * Gibt die ausgerechneten Stats in einem Array zurueck
	 * @return
	 */
	public int[] getStats() {
		return this.stat.getStats();
	}
	
	/**
	 * Gibt das Stats Objekt zurueck
	 * @return das Stats Objekt
	 */
	public Stats getStat() {
		return this.stat;
	}
	/**
	 * Setzt den HP Stat
	 * @param i die Hp
	 * @throws WrongArgumentException 
	 */
	public void setHP(int i) throws WrongArgumentException {
		this.stat.setHP(i);
	}
}
