package poke.game.controllerlogik;

import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.Status;
import poke.game.programmlogik.Statusveraenderungen;
import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.item.Item;
import poke.game.programmlogik.move.Move;

public class Spieler extends Team{

	private Pokemon[] spieler;
	private int z;
	
	public Spieler(Pokemon[] t) throws WrongArgumentException {
		this.setTeam(t);
	}
	@Override
	public void setTeam(Pokemon[] t) throws WrongArgumentException {
		if(t.length > 6 || t.length < 0) {
			throw new WrongArgumentException();
		} else{
			this.spieler = t;
		}
	}

	@Override
	public String[] getIcons() {
		String[] array = new String[6];
		for(int x = 0; x < this.spieler.length; x++) {
			array[x] = this.spieler[x].getIcon();
		}
		return array;
	}

	public void setZusatz(Item item, Move[] move, Status status, Statusveraenderungen statusveraenderungen) {
		try {
			this.spieler[z].setItem(item);
			this.spieler[z].setMove(move);
			this.spieler[z].setStatus(status);
			this.spieler[z].setStatusveraenderungen(statusveraenderungen);
			this.z++;
		} catch (WrongArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("unused")
	public void switchIn(Pokemon p) throws WrongArgumentException {
		int z = 10;
		for(int x = 0; x < this.spieler.length; x++) {
			if(this.spieler[x].equals(p)) {
				z = x;
			}
		}
		if(z > 6) {
			throw new WrongArgumentException();
		} else {
			this.spieler[z] = this.spieler[0];
			this.spieler[0] = p;
		}
	}
	
	public Pokemon[] getSpieler() {
		return this.spieler;
	}
}
