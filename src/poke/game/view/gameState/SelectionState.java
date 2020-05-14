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
import poke.game.sound.Sound;
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
	private Sound scroll;
	/**
	 * Das ist ein Konstruktor
	 * @param gsm der GameStateManager
	 * @param c der Controller
	 */
	public SelectionState(GameStateManager gsm, Controller c) {
		this.scroll = new Sound("res/Sound/scroll.wav");
		this.gsm = gsm;
		this.yEnt = 0;
		testPokemon = c.getSpieler().getSpieler()[0];
		init();
		try {
			this.add =  ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/add.gif"));
			this.searchbar = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/searchbar.gif"));
			this.background = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/background.gif"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		
		
	}
	
	/**
	 * Diese Methode initialisiert notwendiges
	 * und wird immer ausgeführt wenn man zu diesem State WECHSELT
	 */
	@Override 
	public void init() {
		this.yEnt = 0;
		
		e = new UnderlingEntry(testPokemon,70);
		e2 = new UnderlingEntry(testPokemon,130);
		e3 = new UnderlingEntry(testPokemon,190);
		e4 = new UnderlingEntry(testPokemon,260);
		e1 = new UnderlingEntry(testPokemon,320);
	
	}
	/**
	 * Diese Methode wird öfters pro Sekunde
	 * ausgeführt und aktualisiert bestimmte 
	 * Elemente
	 */
	@Override
	public void update() {
		e.update();
	}
	/**
	 * Diese Methode zeichnet den ganzen Screen
	 * @param g Das Graphics ding
	 */
	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(new Color(201,201,201));
		g.fillRect(0, 0, 320, 240);
		
		
		e.draw(g);
		// e2.draw(g);
		// e1.draw(g);
		// e2.draw(g);
		// e3.draw(g);
		// e4.draw(g);
		

		
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
			case KeyEvent.VK_DOWN:
				this.e.scroll(-1);
				scroll.play();
				break;
			case KeyEvent.VK_UP:
				this.e.scroll(1);
				scroll.play();
				break;
		}
	}
	
	@Override
	public void keyReleased(int k) {
		
	}
}
