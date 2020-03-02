package poke;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import poke.game.controllerlogik.Gegner;
import poke.game.controllerlogik.Gegnerreader;
import poke.game.controllerlogik.Spieler;
import poke.game.controllerlogik.Spielstand;
import poke.game.controllerlogik.Team;
import poke.game.programmlogik.Pokemonreader;
import poke.game.programmlogik.WrongArgumentException;
import poke.game.programmlogik.ability.Abilityreader;
import poke.game.programmlogik.item.Itemreader;
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
	
	private Typreader t;
	private Itemreader i;
	private Abilityreader a;
	private Movereader m;
	private Pokemonreader p;
	private Gegnerreader g;
	private Team[] team = new Team[2];
	private Frame f;
	private Launcher l;
	private GameStateManager gsm;
	private Spielstand s;
	private Spielstand[] sp;
	
	
	public Controller() {
		this.t = new Typreader();
		this.i = new Itemreader();
		this.a = new Abilityreader();
		this.m = new Movereader(t);
		this.p = new Pokemonreader(m,t,a);
		this.g = new Gegnerreader(p,i);
		this.team[0] = new Spieler();
		this.team[1] = new Gegner(g);
		this.f = new Frame("Underwatch");
		this.sp = new Spielstand[3];
		this.s = new Spielstand("Neu",0,0,null);
		this.spielstand();
		// 	System.out.println("Ja moin");
	}
	/**
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) { 
		
	}
	public void spielstand() {
		int[] i = this.s.checkStand();
		for(int x = 0; x < i.length; x++) {
			if(i[x] == 1) {
				sp[x] = tempFull();
				sp[x] = sp[x].laden(x+1);
			}
		}
	}
	public Spielstand tempFull() {
		return new Spielstand("Test",0,0,null);
	}
	
	
	public static void main(String[] args)  {
		new Controller();
	}
}
