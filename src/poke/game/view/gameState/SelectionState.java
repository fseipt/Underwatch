package poke.game.view.gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import poke.Controller;
import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.typ.Typ;
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
	private int yEnt, underlingAnzahl;
	private Sound scroll, blocked;
	private int stelle;
	private boolean scrollUpAble, scrollDownAble;
	private Typ[][] se;
	private Typ[] test;
	
	/**
	 * Das ist ein Konstruktor
	 * @param gsm der GameStateManager
	 * @param c der Controller
	 */
	public SelectionState(GameStateManager gsm, Controller c) {
	
		this.e = new ArrayList<>();
		this.current = new UnderlingEntry[6];
		
		
		this.gsm = gsm;
		testPokemon = c.getSpieler().getSpieler()[0];
		this.underlingAnzahl = 50;
		
		
		// Das mit den Typen und so
		testshit();
		
		
		testPokemon.setTyp(test);
		// Alle Underlings werden initialisiert
		// Sie sind alle zuerst auf Y-Position 0!!!
		int typI = 0;
		for(int i = 0; i < this.underlingAnzahl;i++) {
			
			testPokemon.setTyp(se[typI]);
			e.add(new UnderlingEntry(testPokemon,10));
			if(typI < 7) typI++;
			else typI = 0;
		}
		
		
		
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
		// Stelle 0 heisst dass das erste Grad noch sichtbar ist
		this.stelle = 0;
		
		// Die Renderbaren werden initialisiert
		// Am Anfang nur die ersten 
		// DAS HEISST die erste Stelle ist NULL weil das erste sichtbar sein muss
		current[0] = null;
		if(this.underlingAnzahl > 4) {
			
			for(int i = 1; i< current.length; i++) {
				current[i] = e.get(i-1);
				// Hier werden sie auf die jeweiligen Positionen gebracht
				if(current[i] != null) current[i].moveToStelle(i); 
			}
			// Da werden alle unteren Einträge runtergeschoben
			for(int i = current.length-1; i < e.size(); i++) {
				if(e.get(i) != null) {
					e.get(i).setY(300); 
				}
			
			}
		}
		else {
			for(int i = 1; i <= underlingAnzahl; i++) {
				current[i] = e.get(i-1);
				// Hier werden sie auf die jeweiligen Positionen gebracht
				if(current[i] != null) current[i].moveToStelle(i); 
			}
		}
			
		
	}
	/**
	 * Diese Methode wird öfters pro Sekunde
	 * ausgeführt und aktualisiert bestimmte 
	 * Elemente
	 */
	@Override
	public void update() {
		// Updaten
		for(int i = 0; i<current.length; i++) if(current[i] != null) current[i].update();
		
	}
	/**
	 * Diese Methode zeichnet den ganzen Screen
	 * @param g Das Graphics ding
	 */
	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(new Color(201,201,201));
		g.fillRect(0, 0, 320, 310);
		
				
	
		
		for(int i = 0; i<current.length; i++) if(current[i] != null) current[i].draw(g);
		
		g.drawImage(background,0,0,null);
		g.drawImage(add,150,10,null);
		g.drawImage(searchbar,5,10,null);
		
		
		/*g.setColor(Color.red);
		g.drawLine(0, 55, 320, 55);
		g.drawLine(0, 235, 320, 235); */
	}
	/**
	 * Diese Methode speichert
	 * die Renderbaren Einträge in das
	 * current Array.
	 */
	public void neueRenderbare() {
		
		if(this.stelle == 0) {
			init();
		}
		else {
			for(int i = 0; i < current.length && this.stelle+i-1 < e.size(); i++) {
				current[i] = e.get(this.stelle+i-1);
				current[i].moveToStelle(i); 
			}
		}
	}
	
	
	@Override
	public void keyPressed(int k) {
		switch(k) {
			case KeyEvent.VK_ESCAPE:
				this.gsm.setState(0);
				break;
			case KeyEvent.VK_DOWN:
				if(this.stelle > 0 && !current[1].scrolling()) {
					this.stelle--;
					neueRenderbare();
					scroll.play();
				}
				else if(this.stelle <= 0) blocked.play();
				break;
				
				
			case KeyEvent.VK_UP:
				if(this.stelle < e.size()-3 && !current[1].scrolling()) {
					this.stelle++;
					neueRenderbare();
					scroll.play();
				}
				else if(this.stelle >= e.size()-3) blocked.play();
				
				
				break;
		}
	}
	
	@Override
	public void keyReleased(int k) {
		
		switch(k) {
		case KeyEvent.VK_UP:
			for(UnderlingEntry ein: current) if(ein != null) ein.setSpeed(0);
			break;
		case KeyEvent.VK_DOWN:
			for(UnderlingEntry ein: current) if(ein != null) ein.setSpeed(0);
			break;
		}
	}
	
	
	
	
	
	public void testshit() {
		se = new Typ[8][2];
		
		test = new Typ[2];
		test[0] = new Typ();
		test[0].setTyp("Gift");
		se[0] = test;
		
		Typ[] test1 = new Typ[2];
		test1[0] = new Typ();
		test1[0].setTyp("Fee");
		se[1] = test1; 
		
		Typ[] test2 = new Typ[2];
		test2[0] = new Typ();
		test2[0].setTyp("Psycho");
		se[2] = test2;
		
		
		Typ[] test3 = new Typ[2];
		test3[0] = new Typ();
		test3[0].setTyp("Boden");
		se[3] = test3;
		
		Typ[] test4 = new Typ[2];
		test4[0] = new Typ();
		test4[0].setTyp("Wasser");
		se[4] = test4; 
		
		Typ[] test5 = new Typ[2];
		test5[0] = new Typ();
		test5[0].setTyp("Feuer");
		se[5] = test5;
		
		Typ[] test6 = new Typ[2];
		test6[0] = new Typ();
		test6[0].setTyp("Flug");
		se[6] = test6;
		
		Typ[] test7 = new Typ[2];
		test7[0] = new Typ();
		test7[0].setTyp("Unlicht");
		se[7] = test7;
	}
}
