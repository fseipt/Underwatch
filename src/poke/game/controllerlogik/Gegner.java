package poke.game.controllerlogik;

import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.Status;
import poke.game.programmlogik.Statusveraenderungen;
import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.item.Item;
import poke.game.programmlogik.move.Move;

public class Gegner extends Team{

	private Pokemon[] gegner;
	private int z;
	
	public Gegner(Gegnerreader p) throws WrongArgumentException {
		Pokemon[] team = new Pokemon[6];
		for(int x = 0; x < team.length; x++) {
			team[x] = p.random();
		}
		setTeam(team);
	}
	@Override
	public void setTeam(Pokemon[] p) throws WrongArgumentException {
		if(p.length > 6 || p.length < 0) {
			throw new WrongArgumentException();
		} else{
			this.gegner = p;
		}
	}

	@Override
	public String[] getIcons() {
		String[] array = new String[6];
		for(int x = 0; x < this.gegner.length; x++) {
			array[x] = this.gegner[x].getIcon();
		}
		return array;
	}

	public void setZusatz(Item item, Move[] move, Status status, Statusveraenderungen statusveraenderungen) {
		try {
			this.gegner[z].setItem(item);
			this.gegner[z].setMove(move);
			this.gegner[z].setStatus(status);
			this.gegner[z].setStatusveraenderungen(statusveraenderungen);
			this.z++;
		} catch (WrongArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("unused")
	public void switchIn(Pokemon p) throws WrongArgumentException {
		int z = 10;
		for(int x = 0; x < this.gegner.length; x++) {
			if(this.gegner[x].equals(p)) {
				z = x;
			}
		}
		if(z > 6) {
			throw new WrongArgumentException();
		} else {
			this.gegner[z] = this.gegner[0];
			this.gegner[0] = p;
		}
	}
	
	public Pokemon[] getSpieler() {
		return this.gegner;
	}
}
