package poke;

import poke.game.controllerlogik.Gegnerreader;
import poke.game.programmlogik.Pokemonreader;
import poke.game.programmlogik.ability.Abilityreader;
import poke.game.programmlogik.item.Itemreader;
import poke.game.programmlogik.move.Movereader;
import poke.game.programmlogik.typ.Typreader;

public class Main {

	public static void main(String[] args) {
		Itemreader i = new Itemreader();
		Typreader t =new Typreader();
		Abilityreader a = new Abilityreader();
		Movereader m = new Movereader(t);
		Pokemonreader p = new Pokemonreader(m);
		Gegnerreader g = new Gegnerreader(p,i);
		System.out.print("Bitte Funktioniere");
	}
}
