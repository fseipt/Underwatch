package poke.game.view.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import poke.Controller;
import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.move.Move;
import poke.game.programmlogik.typ.Typen;
import poke.game.sound.Sound;
import poke.game.view.tileMap.Background;
import poke.game.view.tileMap.TileMap;

/**
 * Diese Klasse zeichent den Combatscreen
 */
public class TypesState extends GameState {		
	
	private Background bg;
	
	/**
	 * Das ist ein Konstruktor
	 * @param gsm der GameStateManager
	 * @param c der Controller
	 */
	public TypesState(GameStateManager gsm, Controller c) {
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
		int x = 100;
		int y = 10;
		for(Typen t: Typen.values()) {
			try {
				g.drawImage(ImageIO.read(getClass().getResourceAsStream("/Graphics/types/"+t.getText()+".gif")), x, y, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			y+=20;
			if(y == 210) {
				x = 200;
				y = 10;
			}
		}
	
	
		
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
