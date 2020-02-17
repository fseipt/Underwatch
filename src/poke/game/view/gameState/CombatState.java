package poke.game.view.gameState;

import java.awt.Color;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;

import poke.game.view.tileMap.Background;
import poke.game.view.tileMap.TileMap;

public class CombatState extends GameState {
	private TileMap tileMap;
	private Background bg;
	private Font font;
	private Color color;
	
	private String enemyName, ownName;
	
	
	public CombatState(GameStateManager gsm) {
		this.gsm = gsm;
		
		this.font = new Font("Press Start 2P", 1, 10);
		this.enemyName = "Enemy";
		this.ownName = "Self";
		
		this.color = Color.white;
		init();
	}
	
	@Override 
	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/combat.gif");
		tileMap.loadMap("/Maps/combat.map");
		tileMap.setPosition(0, 0);
		bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		// g.setColor(Color.white);
		// g.fillRect(0, 0, 320, 240);
		tileMap.draw(g);
		// Draw Title
		g.setColor(color);
		g.setFont(font);
		g.drawString(enemyName, 20, 28 );
		g.drawString(ownName, 168, 133 );
		g.drawRect(43, 34, 100, 5);
		g.drawRect(170, 154, 100, 5);
	}

	@Override
	public void update() {
		tileMap.setPosition(0,0);
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}
}
