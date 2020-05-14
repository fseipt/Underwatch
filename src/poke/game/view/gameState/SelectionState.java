package poke.game.view.gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import poke.Controller;
import poke.game.programmlogik.Pokemon;
import poke.game.view.Entries.UnderlingEntry;
import poke.game.view.tileMap.Background;
/**
 * Diese Klasse zeichent den Combatscreen
 */
public class SelectionState extends GameState {		
	
	private Background bg;
	private ArrayList<Pokemon> underlings;
	private Pokemon testPokemon;
	private UnderlingEntry e1,e2,e3,e4,e;
	private BufferedImage add,searchbar, background;
	private int yEnt;
	/**
	 * Das ist ein Konstruktor
	 * @param gsm der GameStateManager
	 * @param c der Controller
	 */
	public SelectionState(GameStateManager gsm, Controller c) {
		this.gsm = gsm;
		this.yEnt = 0;
		init();
		testPokemon = c.getSpieler().getSpieler()[0];
		e = new UnderlingEntry(testPokemon,yEnt+70);
		e2 = new UnderlingEntry(testPokemon,240-yEnt);
		e3 = new UnderlingEntry(testPokemon,yEnt+190);
		e4 = new UnderlingEntry(testPokemon,yEnt+260);
		e1 = new UnderlingEntry(testPokemon,yEnt+320);
		
		
	}
	/**
	 * Diese Methode initialisiert notwendige 
	 * und wird immer ausgeführt wenn man zu diesem State WECHSELT
	 * Ressourcen
	 */
	@Override 
	public void init() {
		this.yEnt = 0;
		try {
			this.add =  ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/add.gif"));
			this.searchbar = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/searchbar.gif"));
			this.background = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/background.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Diese Methode wird öfters pro Sekunde
	 * ausgeführt und aktualisiert bestimmte 
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
		yEnt+=1;
		e.setY(yEnt);
		e2.setY(yEnt);
		g.setColor(new Color(201,201,201));
		g.fillRect(0, 0, 320, 240);
		
		
		e.draw(g);
		e1.draw(g);
		e2.draw(g);
		e3.draw(g);
		e4.draw(g);
		

		
		g.drawImage(background,0,0,null);

		g.drawImage(add,150,10,null);
		g.drawImage(searchbar,5,10,null);
		
		
		
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
