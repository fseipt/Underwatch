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
	private ArrayList<UnderlingEntry> e;
	private UnderlingEntry[] current;
	private BufferedImage add,searchbar, background;
	private int yEnt;
	private Sound scroll, blocked;
	private int stelle;
	private boolean scrollUpAble, scrollDownAble;
	/**
	 * Das ist ein Konstruktor
	 * @param gsm der GameStateManager
	 * @param c der Controller
	 */
	public SelectionState(GameStateManager gsm, Controller c) {
	
		this.e = new ArrayList<>();
	
		
		
		this.gsm = gsm;
		testPokemon = c.getSpieler().getSpieler()[0];
		
		this.yEnt = 70;
		for(int i = 0; i < 8; i++) {
			e.add(new UnderlingEntry(testPokemon, 10));
		}
		
		
		this.current = new UnderlingEntry[6];
		this.stelle = 0;
		
		// Wenn man sowieso NICHT runter oder raufscrollen kann
		if(e.size() < 4) {
			this.scrollUpAble = false;
			this.scrollDownAble = false;
			current[0] = null;
			for(int i = 0; i<e.size();i++) current[i] = e.get(i);
		}
		
		else {
			this.scrollDownAble = true;
			this.scrollUpAble = true;
		}
		
		
		for(int i = 0;i < current.length; i++)  current[i] = e.get(i+stelle); 
		
		init();
		try {
			this.scroll = new Sound("res/Sound/scroll.wav");
			this.blocked = new Sound("res/Sound/blocked.wav");
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
		this.stelle = 0;
		for(int i = 0;i < current.length; i++) {
			 current[i] = e.get(i);
		}
		yEnt = 10;
		for(UnderlingEntry en: e) {
			en.move(yEnt);
			yEnt += 60;
		}
	}
	/**
	 * Diese Methode wird öfters pro Sekunde
	 * ausgeführt und aktualisiert bestimmte 
	 * Elemente
	 */
	@Override
	public void update() {
		if(current[0] == null)  this.scrollDownAble = false;
		
		if(this.stelle < e.size()*-1) this.scrollUpAble = false;
	
		else if(this.stelle > e.size()) this.scrollDownAble = false;

		else if(this.stelle < e.size() && this.stelle >= 0) {
			this.scrollUpAble = true;
			this.scrollDownAble = true;
			for(int i = 0;i < current.length; i++) {
				
				if((stelle+i) < 8) current[i] = e.get(stelle+i);
				else current[i] = null;
			}
		}
		for(UnderlingEntry en: current) if(en != null) en.update();
	}
	/**
	 * Diese Methode zeichnet den ganzen Screen
	 * @param g Das Graphics ding
	 */
	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(new Color(201,201,201));
		g.fillRect(0, 0, 320, 240);
		
		
		for(UnderlingEntry en: current) if(en != null) en.draw(g);

		
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
				if(this.stelle < 0) {
					this.scrollDownAble = false;
					blocked.play();
				}
				else if(!scrollDownAble) blocked.play();
				else {
					this.stelle--;
					update();
					// for(UnderlingEntry en: current) if(en != null) en.scroll(-1);
					
					for(int i = 0; i < current.length; i++) if(current[i] != null) current[i].moveToStelle(i);
					// for(UnderlingEntry en: e) en.move();
					scroll.play();
				}
				break;
				
				
			case KeyEvent.VK_UP:
				if(this.stelle >= e.size()-4) {
					this.scrollUpAble = false;
					blocked.play();
				}
				
				else if(!scrollUpAble) blocked.play();
				
				else {
					this.stelle++;
					update();
					
					// for(UnderlingEntry en: current) if(en != null) en.scroll(1);
					for(int i = 0; i < current.length; i++) if(current[i] != null) current[i].moveToStelle(i);
					scroll.play();
				}
				break;
		}
	}
	
	@Override
	public void keyReleased(int k) {
		
	}
}
