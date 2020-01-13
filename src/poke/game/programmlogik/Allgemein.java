package poke.game.programmlogik;

public interface Allgemein {
	
	String[] typen = {"Normal","Kampf","Flug","Gift","Boden","Gestein",
					  "Kaefer","Geist","Stahl","Feuer","Wasser","Pflanze",
				      "Elektro","Psycho","Eis","Drache","Unlicht","Fee"};
	
	String[] staten = {"Alive", "Dead", "Poisened", "Freezed", "Paralysed", "Burned", "Sleep"};
	
	int[] ap = {5,10,15,20,25,30,35,40};
	
}
