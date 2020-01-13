package poke.game.programmlogik;

public class Stats {

	private int healthpoint;
	private int attack;
	private int defense;
	private int spezialattack;
	private int spezialdefense;
	private int speed;
	private int b = 0, p = 0;
	private boolean help = false;
	
	public Stats(String s) {
		for(int x = 0; x < s.length(); x++) {
			if(s.charAt(x) == 44) {
				b++;
				help = true;
			}
			if(help) {
				switch(b) {
			
				case 1: healthpoint = Integer.parseInt(s.substring(0,x));
					break;
				case 2: attack = Integer.parseInt(s.substring(p,x));
					break;
				case 3: defense = Integer.parseInt(s.substring(p,x));
					break;
				case 4: spezialattack = Integer.parseInt(s.substring(p,x));
					break;
				case 5: spezialdefense = Integer.parseInt(s.substring(p,x));
					break;
				case 6: speed = Integer.parseInt(s.substring(x));
					break;
				}
			}
			if(s.charAt(x) == 44) {
					p = (x+1);
			}
			help = false;
		}
	}
}