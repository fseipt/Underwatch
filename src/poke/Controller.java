package poke;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import poke.game.controllerlogik.Gegner;
import poke.game.controllerlogik.Gegnerreader;
import poke.game.controllerlogik.Spieler;
import poke.game.controllerlogik.Spielstand;
import poke.game.controllerlogik.Team;
import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.Pokemonreader;
import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.ability.Abilityreader;
import poke.game.programmlogik.item.Itemreader;
import poke.game.programmlogik.move.Move;
import poke.game.programmlogik.move.Movereader;
import poke.game.programmlogik.typ.Typreader;
import poke.game.view.Frame;
import poke.game.view.gameState.GameStateManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.event.*;

public class Controller implements Serializable{
	
	private Typreader type;
	private Itemreader item;
	private Abilityreader ability;
	private Movereader move;
	private Pokemonreader poke;
	private Gegnerreader gegner;
	private Team[] team = new Team[2];
	private Frame frame;
	private Launcher launcher;
	private GameStateManager gsm;
	private Spielstand spielstand;
	private Spielstand[] spielstaende;
	
	private Spieler spieler;
	
	public Controller() {
		
		this.type = new Typreader();
		this.item = new Itemreader();
		this.ability = new Abilityreader();
		this.move = new Movereader(type);
		this.poke = new Pokemonreader(move,type,ability);
		this.gegner = new Gegnerreader(poke,item);
		this.team[0] = new Spieler();
		this.team[1] = new Gegner(gegner);
		
		this.spieler = new Spieler();
		
		try {
			this.spieler.setTeam(poke.getPokemon());
		} catch (WrongArgumentException e) {
			throw new RuntimeException("Spieler ding hat verkackt");
		}
		
		this.frame = new Frame("Underwatch", this);
		
		this.spielstaende = new Spielstand[3];
		this.spielstand = new Spielstand("Neu",0,0,null);
		this.spielstand();
		System.out.println("Ja moin");
	}
	public Spieler getSpieler() {
		return spieler;
	}
	/**
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) { 
		
	}
	public void spielstand() {
		int[] i = this.spielstand.checkStand();
		for(int x = 0; x < i.length; x++) {
			if(i[x] == 1) {
				spielstaende[x] = tempFull();
				spielstaende[x] = spielstaende[x].laden(x+1);
			}
		}
	}
	public Typreader getType() {
		return type;
	}
	public Itemreader getItem() {
		return item;
	}
	public Abilityreader getAbility() {
		return ability;
	}
	public Movereader getMove() {
		return move;
	}
	public Pokemonreader getPoke() {
		return poke;
	}
	public Gegnerreader getGegner() {
		return gegner;
	}
	public Team[] getTeam() {
		return team;
	}
	public Frame getFrame() {
		return frame;
	}
	public Launcher getLauncher() {
		return launcher;
	}
	public GameStateManager getGsm() {
		return gsm;
	}
	public Spielstand getSpielstand() {
		return spielstand;
	}
	public Spielstand[] getSpielstaende() {
		return spielstaende;
	}
	public Spielstand tempFull() {
		return new Spielstand("Test",0,0,null);
	}
	
	
	public static void main(String[] args)  {
		new Controller();
	}
	
	
	
}
