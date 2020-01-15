package poke.game.programmlogik;

public class Stats {

	private int healthpoint;
	private int attack;
	private int defense;
	private int spezialattack;
	private int spezialdefense;
	private int speed;
	private int[] stats = new int[6];
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
		for(int x = 0; x < this.stats.length; x++) {
			switch(x) {
			
			case 0: this.stats[x] = (((2*this.healthpoint+94)*100)/100)+110;
				break;
			case 1: this.stats[x] = (((2*this.attack+94)*100)/100)+5;
				break;
			case 2: this.stats[x] = (((2*this.defense+94)*100)/100)+5;
				break;
			case 3: this.stats[x] = (((2*this.spezialattack+94)*100)/100)+5;
				break;
			case 4: this.stats[x] = (((2*this.spezialdefense+94)*100)/100)+5;
				break;
			case 5: this.stats[x] = (((2*this.speed+94)*100)/100)+5;
				break;
			}
		}
	}
	public int[] getStats() {
		return this.stats;
	}
	public void setStats(int[] a) {
		for(int x = 0; x < this.stats.length; x++) {
			this.stats[x] = this.stats[x] * a[x];
		}
	}
	public void setA(int a) {
		this.stats[1] = this.stats[1] * a;
	}
	public void setD(int a) {
		this.stats[2] = this.stats[2] * a;
	}
	public void setSA(int a) {
		this.stats[3] = this.stats[3] * a;
	}
	public void setSD(int a) {
		this.stats[4] = this.stats[4] * a;
	}
	public void setS(int a) {
		this.stats[5] = this.stats[5] * a;
	}
}