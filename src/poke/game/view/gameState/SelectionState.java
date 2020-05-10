package poke.game.view.gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import poke.Controller;
import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.typ.Typen;
import poke.game.view.Entries.UnderlingEntry;
import poke.game.view.tileMap.Background;
/**
 * Diese Klasse zeichent den Combatscreen
 */
public class SelectionState extends GameState {		
	
	private Background bg;
	private ArrayList<Pokemon> underlings;
	private Pokemon testPokemon;
	/**
	 * Das ist ein Konstruktor
	 * @param gsm der GameStateManager
	 * @param c der Controller
	 */
	public SelectionState(GameStateManager gsm, Controller c) {
		this.gsm = gsm;
		init();
		
	
	}
	/**
	 * Diese Methode initialisiert notwendige 
	 * Ressourcen
	 */
	@Override 
	public void init() {
	}
	/**
	 * Diese Methode wird öfters pro Sekunde
	 * ausgeführt une aktualisiert bestimmte 
	 * Elemente
	 */
	@Override
	public void update() {
	}
	/**
	 * Diese Methode zeichnet den ganzen Screen
	 * @param g Das Graphics ding
	 */
	@Override
	public void draw(Graphics2D g) {
	
		g.setColor(Color.white);
		g.fillRect(0, 0, 400, 400);
		UnderlingEntry e = new UnderlingEntry(null,100,1);
		UnderlingEntry e2 = new UnderlingEntry(null,50,1);
		UnderlingEntry e3 = new UnderlingEntry(null,100,2);
		UnderlingEntry e4 = new UnderlingEntry(null,150,1);
		UnderlingEntry e1 = new UnderlingEntry(null,200,1);
		e.draw(g);
		e1.draw(g);
		e2.draw(g);
		e3.draw(g);
		e4.draw(g);
		
	}
	@Override
	public void keyPressed(int k) {
		switch(k) {
			case KeyEvent.VK_ESCAPE:
				this.gsm.setState(0);
				break;
		}
	}
	
	@Override
	public void keyReleased(int k) {
		
	}
}
