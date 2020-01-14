package poke.game.programmlogik;

public class Stats {

	private int healthpoint;
	private int attack;
	private int defense;
	private int spezialattack;
	private int spezialdefense;
	private int speed;
	private int zb = 0, zp = 0;
	private boolean zhelp = false;
	
	public Stats(String s) {
		for(int x = 0; x < s.length(); x++) {
			if(s.charAt(x) == 44) {
				zb++;
				zhelp = true;
			}
			if(zhelp) {
				switch(zb) {
			
				case 1: healthpoint = Integer.parseInt(s.substring(0,x));
					break;
				case 2: attack = Integer.parseInt(s.substring(zp,x));
					break;
				case 3: defense = Integer.parseInt(s.substring(zp,x));
					break;
				case 4: spezialattack = Integer.parseInt(s.substring(zp,x));
					break;
				case 5: spezialdefense = Integer.parseInt(s.substring(zp,x));
					break;
				case 6: speed = Integer.parseInt(s.substring(x));
					break;
				}
			}
			if(s.charAt(x) == 44) {
					zp = (x+1);
			}
			zhelp = false;
		}
	}
}