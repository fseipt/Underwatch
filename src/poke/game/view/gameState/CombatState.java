package poke.game.view.gameState;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import poke.Controller;
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
	private double currentHp, maxHp, hpFac, dmg, dmgP;
	private int level;
	private double currentHpE, maxHpE;
	private int levelE;
	private String enemyName, ownName, message;
	// SKills
	private Move[] moves;
	private String[] skills; 
	private String[] types;
	private int[] ap;
	//Stats
	private int[] stats = {-6,2,3,0,-2,0,-1};
	private StatLine statLine = new StatLine(stats);
	
	
	
	public CombatState(GameStateManager gsm, Controller c) {
		moves = c.getSpieler().getSpieler()[0].getMoves();
		
		System.out.println(c.getSpieler().getSpieler()[0].getMoves()[1].getName());
		// WARUM WERDEN NUR 2 MOVES ERKANNT OBWOHL 4 IM CSV FILE SIND
		/*this.moves = c.getSpieler().getSpieler()[0].getMoves();
		
		this.skills = new String[] {moves[0].getName(), moves[1].getName(),
									moves[2].getName(), moves[3].getName()};
		this.ap = new int[] {moves[0].getAngriffspunkte(),moves[1].getAngriffspunkte()
							,moves[2].getAngriffspunkte(),moves[3].getAngriffspunkte()};
		
		this.types = new String[] {moves[0].getTyp().getTyp(),moves[1].getTyp().getTyp(),
								   moves[2].getTyp().getTyp(),moves[3].getTyp().getTyp()};
		
		*/
		
		
		
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
		this.dmg = 0;
		this.hpFac = 1;
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
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/combat.gif");
		tileMap.loadMap("/Maps/combat.map");
		tileMap.setPosition(0, 0);
		bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
	}
	@Override
	public void update() {
		statLine.update();
		tileMap.setPosition(0,0);
		damageH();
		healH();
		
	}
	@Override
	public void draw(Graphics2D g) {
		
		bg.draw(g);
		
		g.drawImage(this.magnet,190,15,null);
		g.drawImage(this.magnetBack, -10, 70, 150, 150, null);
		// g.setColor(Color.white);
		// g.fillRect(0, 0, 320, 240);
		tileMap.draw(g);
		

		
		// Draw Title
		g.setColor(color);
		g.setFont(font);
		g.drawString(enemyName, 20, 28 );
		g.drawString(ownName, 170, 134 );
		
		
		// Gender
		g.drawImage(female,250,128,null); // self
		g.drawImage(male,100,20,null);    // enemy
		
		// HP self
		g.drawString((int)currentHp+"/"+(int)maxHp,253,160);
		// Level self
		g.drawString("Lv"+level,264,137);
	
		// level enemy
		g.drawString("Lv"+level,114,29);
		
		
		drawHealth(g);
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
	 * Dieser Methode wird der Schaden einer
	 * ausgeführten Attacke übergeben
	 * @param dmg Schaden
	 */
	public void hpChange(double dmg) {
		this.dmg = -dmg;
		this.dmgP = this.currentHp + dmg;
		if(dmgP == 0) dmgP--;
	}
	/**
	 * Diese Methode wird jede Sekunde ausgeführ
	 * und sorgt dafür, dass die HP Anzeige in 
	 * einer Animation sich decreased
	 */
	public void damageH() {
		if(this.currentHp >= this.maxHp) {
			this.currentHp = this.maxHp;
			this.hpFac = 0;
			this.dmg = 0;
			this.dmgP = 0;
			return;
		}
		if(dmgP == 0 || dmg < 0) return;
		if(currentHp <= 0) {
			this.hpFac = 0;
			this.dmg = 0;
			this.dmgP = 0;
		}
		
		// Wenn schon alles abgezogen wurde
		if(dmgP >= this.currentHp ) {
			this.currentHp = dmgP;
			dmgP = 0;
			this.hpFac = 1;
		}
		else {
			this.hpFac -= (1-dmgP/this.currentHp)/1000;
		}
		this.currentHp = currentHp*hpFac;
		
	}
	public void healH() {
		if(this.currentHp == this.maxHp || dmg > 0 || dmgP == 0) return;
		
		
		if(dmgP > this.maxHp) dmgP = this.maxHp;
		
		
		if(currentHp <= 0) {
			this.hpFac = 0;
			this.dmg = 0;
			this.dmgP = 0;
		}
		
		
		// Wenn schon alles abgezogen wurde
		if(dmgP <= this.currentHp ) {
			this.currentHp = dmgP;
			dmgP = 0;
			this.hpFac = 1;
		}
		else {
			this.hpFac -= (1-dmgP/this.currentHp) /3000 ;
		}
		this.currentHp = currentHp*hpFac;
	}
	public void drawHealth(Graphics2D g) {
		
		double hpProz = (currentHp/maxHp) *100;
		
		this.health = (hpProz >= 50 ? Color.green : 
					  (hpProz >= 20 ? Color.yellow : Color.RED));
	
		if(this.currentHp >= 1 && hpProz > 0 && hpProz < 1) hpProz = 2;
		// Healths
		g.setColor(health);
		g.fillRect(43, 33, (int) hpProz, 5);
		g.fillRect(193, 141, (int) hpProz, 5);
				
		// Healthbar
		g.setColor(color);
		g.drawRect(43, 33, 100, 5);
		g.drawRect(193, 141, 100, 5);
	}
	/**
	 * Diese Methode zeichnet das Stats-MEnu
	 * @param g graphics ding
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
	public void keyPressed(int k) {
		switch(k) {
			case KeyEvent.VK_ENTER:
				select2.play();
				if(currentChoice == 0 && currentMenu == this.MAIN) currentMenu = this.SKILLS;
				else if(currentChoice == 1 && currentMenu == this.MAIN) currentMenu = this.STATS;
				break;
					
			case KeyEvent.VK_RIGHT:
				select2.play();
				if(currentChoice%2 == 0) currentChoice++;
				else currentChoice--;
				break;
					
			case KeyEvent.VK_LEFT:
				select2.play();
				if(currentChoice%2 == 0) currentChoice++;
				else currentChoice--;
				break;
			
			case KeyEvent.VK_DOWN:
				select2.play();
				if(currentChoice < 2) currentChoice+=2;
				else currentChoice-=2;
				break;
					
			case KeyEvent.VK_UP:
				select2.play();
				if(currentChoice < 2) currentChoice+=2;
				else currentChoice-=2;
				break;
					
			case KeyEvent.VK_X:
				currentMenu = 0;
				break;
					
			case KeyEvent.VK_H:
				this.currentHp--;
				break;
			
			case KeyEvent.VK_J:
				this.currentHp++;
				break;
			case KeyEvent.VK_G:
				hpChange(-30);
				break;
			case KeyEvent.VK_V:
				hpChange(70);
				break;
			case KeyEvent.VK_F:
				this.currentHp = this.maxHp;
				break;
		}
	}
	
	@Override
	public void keyReleased(int k) {
		
	}
}
