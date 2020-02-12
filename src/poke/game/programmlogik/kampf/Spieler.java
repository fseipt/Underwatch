package poke.game.programmlogik.kampf;

import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.Status;
import poke.game.programmlogik.Statusveraenderungen;
import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.item.Item;
import poke.game.programmlogik.move.Move;

public class Spieler extends Team{

	private Pokemon[] spieler;
	private int z;
	public Spieler(Pokemon[] t) {
		this.setTeam(t);
	}
	@Override
	public void setTeam(Pokemon[] t) {
		this.spieler = t;
	}

	@Override
	public String getIcons() {
		
		return null;
	}

	@Override
	public String getBild() {
		
		return null;
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
}
