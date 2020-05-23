package poke.game.programmlogik;

public interface Allgemein {
	
	// Alle Typen
	String[] typen = {"Normal","Feuer","Wasser","Pflanze","Kampf","Boden",
					  "Gestein","Gift","Elektro","Flug","Unlicht","Psycho",
				      "Geist","Kaefer","Eis","Stahl" ,"Drache","Fee"};
	
	// Alle Stati
	String[] statusse = {"Alive", "Dead", "WPoisened", "HPoisened", "Freezed", "Paralysed", "Burned", "Sleep", "Flinched", "Attracted", "Confused"};
	
	// Alle Statusveraenderungen
	String[] statusveraenderungen = {"Attack", "Defense", "Specialattack", "Specialdefense", "Speed", "Genauigkeit", "Fluchtwert", "Crit-Chance"}; 
	// Alle negativen Statusveraenderungen
	String[] mstatusveraenderungen = {"_Attack", "_Defense", "_Specialattack", "_Specialdefense", "_Speed", "_Genauigkeit", "_Fluchtwert", "_Crit-Chance"};
	// Alle moeglichen Angriffspunkte
	int[] ap = {5,10,15,20,25,30,35,40};
	
}
