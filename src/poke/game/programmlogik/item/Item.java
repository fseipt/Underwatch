package poke.game.programmlogik.item;

import poke.game.programmlogik.WrongArgumentException;

public class Item {

	private String name;
	private Effekt effekt;
	private String bezeichnung;
	
	public Item() {
		
	}
	public Item(String item) throws WrongArgumentException {
		int z = 0;
		int c = 0;
		for(int x = 0; x < item.length(); x++) {
			if(item.charAt(x) == 44) {
				if(z == 0) {
					this.setName(item.substring(0,x));
					c = x;
					z++;
				}
				if(z == 1) {
					Effekt e = new Effekt(item.substring(c, x));
					this.setEffekt(e);
					this.setBezeichnung(item.substring(x+1));
				}
				
			}
		}
	}
	public void setName(String n) throws WrongArgumentException {
		if(n.length() < 2) {
			throw new WrongArgumentException();
		} else {
			this.name = n;
		}
	}
	
	public void setEffekt(Effekt e) throws WrongArgumentException {
		if(e == null) {
			throw new WrongArgumentException();
		} else {
			this.effekt = e;
		}
	}
	
	public void setBezeichnung(String s) throws WrongArgumentException {
		if(s.length() < 2) {
			throw new WrongArgumentException();
		} else {
			this.bezeichnung = s;
		}
	}
}
