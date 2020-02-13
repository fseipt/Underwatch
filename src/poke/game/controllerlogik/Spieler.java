package poke.game.controllerlogik;

import java.util.Arrays;

import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.Status;
import poke.game.programmlogik.Statusveraenderungen;
import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.item.Item;
import poke.game.programmlogik.move.Move;

public class Spieler extends Team{

	private Pokemon[] spieler;
	private Pokemon[] building;
	private Pokemon p;
	private int z;
	
	public Spieler() throws WrongArgumentException {
		building = new Pokemon[0];
		spieler = new Pokemon[6];
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

	public void setZusatz(Item item, Move[] move) {
		try {
			this.spieler[z].setItem(item);
			this.spieler[z].setMove(move);
			this.spieler[z].setStatus(new Status("Alive"));
			this.spieler[z].setStatusveraenderungen(new Statusveraenderungen());
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
	
	public void setPokemon(Pokemon p) {
		this.p = p;
		this.building = Arrays.copyOf(this.building, this.building.length+1);
		this.building[this.building.length-1] = p;
		
	}
	
	public void remPokemon(int i) {
		if(building[i] != null) {
			this.building[i] = null;
		}
	}
	public Pokemon[] getSpieler() {
		return this.spieler;
	}
}
