package poke;

import javax.swing.JFrame;

import poke.game.controllerlogik.Gegner;
import poke.game.controllerlogik.Gegnerreader;
import poke.game.controllerlogik.Spieler;
import poke.game.controllerlogik.Team;
import poke.game.programmlogik.Pokemonreader;
import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.ability.Abilityreader;
import poke.game.programmlogik.item.Itemreader;
import poke.game.programmlogik.move.Movereader;
import poke.game.programmlogik.typ.Typreader;

public class Controller {
	
	private Typreader t;
	private Itemreader i;
	private Abilityreader a;
	private Movereader m;
	private Pokemonreader p;
	private Gegnerreader g;
	private Team[] team = new Team[2];
	private Launcher l;
	
	
	public Controller() {
		this.t = new Typreader();
		this.i = new Itemreader();
		this.a = new Abilityreader();
		this.m = new Movereader(t);
		this.p = new Pokemonreader(m,t,a);
		this.g = new Gegnerreader(p,i);
		this.team[0] = new Spieler();
		this.team[1] = new Gegner(g);
		this.l = new Launcher(new JFrame("Underwatch"));
	}
	
	
	
	public static void main(String[] args)  {
		new Controller();
	}
}
