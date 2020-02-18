package poke.game.programmlogik;

public interface Allgemein {
	
	// Alle Typen
	String[] typen = {"Normal","Kampf","Flug","Gift","Boden","Gestein",
					  "Kaefer","Geist","Stahl","Feuer","Wasser","Pflanze",
				      "Elektro","Psycho","Eis","Drache","Unlicht","Fee"};
	
	// Alle Stati
	String[] statusse = {"Alive", "Dead", "WPoisened", "HPoisened", "Freezed", "Paralysed", "Burned", "Sleep", "Flinched", "Attracted", "Confused"};
	
	// Alle Statusveraenderungen
	String[] stausveraenderungen = {"Attack", "Defense", "Spezialattack", "Spezialdefense", "Speed", "Genauigkeit", "Fluchtwert", "Crit-Chance"}; 
	
	// Alle moeglichen Angriffspunkte
	int[] ap = {5,10,15,20,25,30,35,40};
	
}
