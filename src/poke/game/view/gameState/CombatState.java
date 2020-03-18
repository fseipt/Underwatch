package poke.game.view.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import poke.Controller;
import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.move.Move;
import poke.game.sound.Sound;
import poke.game.view.tileMap.Background;
import poke.game.view.tileMap.TileMap;

/**
 * Diese Klasse zeichent den Combatscreen
 * 
 * Fabis Aufgaben:
 * 	.) Initialisier skills mit dem Moves
 *  .) Initialisier ap mit den APs
 */
public class CombatState extends GameState {	
	private static final int ANGRIFF = 0;
	private static final int VERTEIDIGUNG = 1;
	private static final int SPANGRIFF = 2;
	private static final int SPVERTEIDIGUNG = 3;
	private static final int INITIATIVE = 4;
	private static final int GENAUIGKEIT = 5;
	private static final int FLUCHT = 6;
	
	private static final int MAIN = 0;
	private static final int SKILLS = 1;
	private static final int STATS = 2;
	
	
	
	
	private TileMap tileMap;
	private Background bg;
	private Font font;
	private Color color, health;
	private int currentChoice, currentMenu;
	private String[] options = {"FIGHT", "STATS", "POKEMON","RUN"};
	private Sound select2;
	private BufferedImage male, female, magnet, magnetBack;
	
	
	private StatPoint sP;
	
	
	
	// FABISSSS DRECK
	private int currentHp, maxHp, level;
	private int currentHpE, maxHpE, levelE;
	private String enemyName, ownName, message;
	// SKills
	private String[] skills; 
	private String[] types;
	private int[] ap;
	//Stats
	private int[] stats = {-6,2,3,0,-2,0,-1};
	private StatLine statLine = new StatLine(stats);
	
	
	
	public CombatState(GameStateManager gsm, Controller c) {
		
		
		this.currentHp = gsm.getSpieler().getSpieler()[0].
		
		//this.skills = c.getTeam()[0]
		
		
		
		
		this.skills = new String[] {"Skill 1", "Skill 2", "Skill 3", "Skill 4"};
		this.ap = new int[] {20,15,5,40};
		this.types = new String[] {"Fee","Drache","Gestein","Käfer"};
		
		
		
		
		
		try {
			this.magnet = ImageIO.read(getClass().getResourceAsStream("/magnet.gif"));
			this.magnetBack = ImageIO.read(getClass().getResourceAsStream("/magnetBack.gif"));
			this.female = ImageIO.read(getClass().getResourceAsStream("/female.gif"));
			this.male = ImageIO.read(getClass().getResourceAsStream("/male.gif"));
		}
		catch(Exception e) { System.err.println("Die geschlechter gehen nicht!!"); }
		
		
		
		
		this.gsm = gsm;
		this.currentMenu = 0;
		this.font = new Font("Press Start 2P", 1, 10);
		this.enemyName = "Enemy";
		this.ownName = "Self";
		this.health = Color.green;
		this.currentHp = 100;
		this.maxHp = 100;
		this.level = 100;
		this.color = Color.white;
		this.message = "What should\n "+ownName+" do?";
		init();
		this.currentChoice = 1;
		this.select2 = new Sound("res/Sound/MenuSelect.wav");
		
	}
	
	@Override 
	public void init() {
		sP = new StatPoint(+1, 100,100);
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/combat.gif");
		tileMap.loadMap("/Maps/combat.map");
		tileMap.setPosition(0, 0);
		bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
	}

	@Override
	public void draw(Graphics2D g) {
		
		bg.draw(g);
		
		g.drawImage(this.magnet,190,15,null);
		g.drawImage(this.magnetBack, -10, 70, 150, 150, null);
		// g.setColor(Color.white);
		// g.fillRect(0, 0, 320, 240);
		tileMap.draw(g);
		
		sP.draw(g);

		
		// Draw Title
		g.setColor(color);
		g.setFont(font);
		g.drawString(enemyName, 20, 28 );
		g.drawString(ownName, 170, 134 );
		
		
		// Gender
		g.drawImage(female,250,128,null); // self
		g.drawImage(male,100,20,null);    // enemy
		
		// HP self
		g.drawString(currentHp+"/"+maxHp,253,160);
		// Level self
		g.drawString("Lv"+level,264,137);
	
		// level enemy
		g.drawString("Lv"+level,114,29);
		
		// Healths
		g.setColor(health);
		g.fillRect(43, 33, 100, 5);
		g.fillRect(193, 141, 100, 5);
		
		// Healthbar
		g.setColor(color);
		g.drawRect(43, 33, 100, 5);
		g.drawRect(193, 141, 100, 5);
		
		// Self "HP"
		g.setFont(new Font("Press Start 2P", 1, 7));
		g.drawString("HP", 180, 146);
		// Enemy "HP"
		g.setFont(new Font("Press Start 2P", 1, 7));
		g.drawString("HP", 30, 38);

	
		
		
		
		switch(currentMenu) {
			case 0: 
				drawSelection(g);
				break;	
			case 1:
				drawSkills(g);
				break;
			case 2:
				drawSelection(g);
				drawStats(g);
				break;
		}
	}
	/**
	 * Diese Methode zeichnet das Stats-MEnu
	 */
	public void drawStats(Graphics2D g) {
		g.setColor(Color.white);
		TileMap statMap = new TileMap(30);
		statMap.loadTiles("/Tilesets/combat.gif");
		statMap.loadMap("/Maps/stats.map");
		statMap.setPosition(0, 0);
		statMap.draw(g);
		
		g.setFont(new Font("Press Start 2P", 1, 10));
		g.drawString("Angriff",84, 50);
		g.drawString("Verteidigung",84, 70);
		g.drawString("Spz.Angriff",84, 90);
		g.drawString("Spz.Verteidigung",84, 110);
		g.drawString("Initiative",84, 130);
		g.drawString("Genauigkeit",84, 150);
		g.drawString("Fluchtwert",84, 170);
		
		
		statLine.draw(g);
		// statLines[0] = new StatLine(stats);
	}
	
	/**
	 * Diese Methode zeichnet das Auswahlmenü
	 * für Skills
	 */
	public void drawSkills(Graphics2D g) {
		g.setColor(Color.GRAY);
		g.fillRoundRect(209,180, 110, 60, 10, 10);
		g.setFont(new Font("Press Start 2P", 1, 14));
		g.setColor(Color.white);
		
		g.drawString("AP",224,204);
		g.drawString(this.ap[currentChoice]+"/"+this.ap[currentChoice],265,204);
		
		g.drawString("Type",224,226);
		g.drawString(types[currentChoice], 265, 226);
		// FIGHT
		g.setColor((currentChoice == 0) ? Color.red : Color.white);
		g.drawString(skills[0], 20, 205);
		// STATS
		g.setColor((currentChoice == 1) ? Color.red : Color.white);
		g.drawString(skills[1], 115, 205);
		// POKEMON
		g.setColor((currentChoice == 3) ? Color.red : Color.white);
		g.drawString(skills[3],116, 227);
		// RUN
		g.setColor((currentChoice == 2) ? Color.red : Color.white);
		g.drawString(skills[2], 20, 227); 
	}
	
	/**
	 * Diese Methode zeichnet das Auswahlmenü
	 * zwischen FIGHT, STATS, POKEMON und RUN
	 * @param g ist das Graphics2D Objekt wo alles gezeichnet wird
	 */
	public void drawSelection(Graphics2D g) {
		g.setFont(new Font("Press Start 2P", 1, 10));
		g.drawString(message, 20, 210);
		g.setFont(new Font("Press Start 2P", 1, 14));
		g.setColor(Color.GRAY);
		g.fillRoundRect(159,180, 160, 60, 10, 10);
		// FIGHT
		g.setColor((currentChoice == 0) ? Color.red : Color.white);
		g.drawString(options[0], 170, 205);
		// STATS
		g.setColor((currentChoice == 1) ? Color.red : Color.white);
		g.drawString(options[1], 255, 205);
		// POKEMON
		g.setColor((currentChoice == 3) ? Color.red : Color.white);
		g.drawString(options[3],256, 227);
		// RUN
		g.setColor((currentChoice == 2) ? Color.red : Color.white);
		g.drawString(options[2], 170, 227); 
	}
	public void drawWerte(int index, int change) {
		
	}
	
	@Override
	public void update() {
		sP.update();
		statLine.update();
		tileMap.setPosition(0,0);
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			select2.play();
			if(currentChoice == 0 && currentMenu == this.MAIN) currentMenu = this.SKILLS;
			else if(currentChoice == 1 && currentMenu == this.MAIN) currentMenu = this.STATS;
		}
		if(k == KeyEvent.VK_RIGHT) {
			select2.play();
			if(currentChoice%2 == 0) currentChoice++;
			else currentChoice--;
		}
		if(k == KeyEvent.VK_LEFT) {
			select2.play();
			if(currentChoice%2 == 0) currentChoice++;
			else currentChoice--;
		}
		if(k == KeyEvent.VK_DOWN) {
			select2.play();
			if(currentChoice < 2) currentChoice+=2;
			else currentChoice-=2;
		}
		if(k == KeyEvent.VK_UP) {
			select2.play();
			if(currentChoice < 2) currentChoice+=2;
			else currentChoice-=2;
		}
		if(k == KeyEvent.VK_X) {
			currentMenu = 0;
		}
	}
	
	@Override
	public void keyReleased(int k) {
		
	}
}
