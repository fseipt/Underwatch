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
import poke.game.view.Graphics.StatLine;
import poke.game.view.Graphics.StatsEnum;
import poke.game.view.tileMap.Background;
import poke.game.view.tileMap.TileMap;

/**
 * Diese Klasse zeichent den Combatscreen
 */
public class CombatState extends GameState {		
	private static final int MAIN = 0;
	private static final int SKILLS = 1;
	private static final int STATS = 2;		
	
	private TileMap tileMap;
	private Background bg;
	private Font font;
	private Color color, health;
	private int currentChoice, currentMenu;
	private String[] options = {"FIGHT", "STATS", "POKEMON","RUN"};
	private Sound select2, heal;
	private BufferedImage male, female, magnet, magnetBack;
	private BufferedImage[] typeIcons;
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
	private int[] apMax, ap;
	//Stats
	private int[] stats = {-6,2,3,0,-2,0,-1};
	private StatLine statLine = new StatLine(stats);
	
	
	/**
	 * Das ist ein Konstruktor
	 * @param gsm der GameStateManager
	 * @param c der Controller
	 */
	public CombatState(GameStateManager gsm, Controller c) {
		init();
		Pokemon own = c.getSpieler().getSpieler()[0];
		this.gsm = gsm;
		this.currentMenu = 0;
		this.health = Color.green;
		this.font = new Font("Press Start 2P", 1, 10);
		this.dmg = 0;
		this.hpFac = 1;
		this.message = "What should\n "+ownName+" do?";
		this.color = Color.white;
		this.currentChoice = 1;
		this.select2 = new Sound("res/Sound/MenuSelect.wav");
		this.heal = new Sound("res/Sound/heal.wav");
		try {
			this.magnet = ImageIO.read(getClass().getResourceAsStream("/Underlings/003.gif"));
			this.magnetBack = ImageIO.read(getClass().getResourceAsStream("/Underlings/002B.gif"));
			
			this.female = ImageIO.read(getClass().getResourceAsStream("/Graphics/female.gif"));
			this.male = ImageIO.read(getClass().getResourceAsStream("/Graphics/male.gif"));
			
			this.typeIcons = new BufferedImage[18];
			for(Typen t: Typen.values())  typeIcons[t.ordinal()] = t.getImage();
		}
		catch(Exception e) { System.err.println("Die Bilder gehen nicht!!"); }
		
		
	
		/* ------ SELF ------ */

		// Name
		this.ownName = own.getName();
		
		// Hp
		this.currentHp = 100;
		this.maxHp = 100;
		
		// Lvl
		this.level = 100;
	
		// Moves
		this.moves = own.getMoves();
		
		// Aps
		this.apMax = new int[4];
		this.ap = new int[4];
		// Skills
		this.skills = new String[4];
		// Typen
		this.types = new String[4];
		for (int i = 0; i < this.moves.length; i++) {
			if(this.moves[i] != null)  {
				this.skills[i] = this.moves[i].getName();
				this.ap[i] = this.moves[i].getAngriffspunkte();
				this.apMax[i] = this.moves[i].getAngriffspunkte();
				this.types[i] = this.moves[i].getTyp().getTyp();
				
			}
			else {
				this.skills[i] = "-";
				this.apMax[i] = 0;
				this.ap[i] = 0;
				this.types[i] = "-";
			}
		}
		
		/* ------ ENEMY ------ */
		this.enemyName = "Enemy";
	
	}
	/**
	 * Diese Methode initialisiert notwendige 
	 * Ressourcen
	 */
	@Override 
	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/combat.gif");
		tileMap.loadMap("/Maps/combat.map");
		tileMap.setPosition(0, 0);
		bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
	}
	/**
	 * Diese Methode wird �fters pro Sekunde
	 * ausgef�hrt une aktualisiert bestimmte 
	 * Elemente
	 */
	@Override
	public void update() {
		statLine.update();
		tileMap.setPosition(0,0);
		damageH();
		healH();
		
	}
	/**
	 * Diese Methode zeichnet den ganzen Screen
	 * @param g Das Graphics ding
	 */
	@Override
	public void draw(Graphics2D g) {
		
		bg.draw(g);
		
		g.drawImage(this.magnet,190,15,null);
		g.drawImage(this.magnetBack, -10, 70,160,160, null);
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
	 * ausgef�hrten Attacke �bergeben
	 * @param dmg Schaden
	 */
	public void hpChange(double dmg) {
		this.dmg = -dmg;
		if(dmg > 0) heal.play();
		this.dmgP = this.currentHp + dmg;
		if(dmgP == 0) dmgP--;
	}
	/**
	 * Diese Methode wird jede Sekunde ausgef�hr
	 * und sorgt daf�r, dass die HP Anzeige in 
	 * einer Animation sich decreased
	 */
	public void damageH() {
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
		
		
		/* Da wird der aktuelle Faktor berechnet
		 * Je n�her das Zieldamage an der akutellen
		 * HP ist, desto kleiner (langsameer) ist der Faktor
		 * 
		 * Der Faktor ist standardm��ig 1
		 * und wird bei z.B 80% damageprozess mit 0,2 und NICHT 0,8
		 * subtrahiert und mit 10000 . 
		 */
		else {
			this.hpFac -= (1-dmgP/this.currentHp)/10000;
		}
		if(currentHp*hpFac < dmgP) this.currentHp = dmgP;
		else this.currentHp = currentHp*hpFac;
		
	}
	/**
	 * Diese Methode wird bei jedem update() ausgef�hrt
	 * und l�sst, falls gehealt wird, die healthbar aufladen
	 */
	public void healH() {
		
		/* ----- Voraussetzungen ------ */
		
		// Wenn die HP schon das Maximum erreicht
		if(this.currentHp > this.maxHp) {
			this.currentHp = this.maxHp;
			this.hpFac = 1; // 1 => keine �nderungen
			this.dmg = 0; // Nichts wird abgezogen
			this.dmgP = 0;
			return;
		}
		// Wenn nichts zu tun ist bzw. HP schon voll ist
		if(this.currentHp == this.maxHp || dmg > 0 || dmgP == 0) return;
		
		// Wenn der zu erreichende HP Wert gr��er als das Maximum
		// Ist wird er auf die MaxHp gesetzt.
		if(dmgP > this.maxHp) dmgP = this.maxHp;
		
		// Wenn jmd Tot ist, Kann nicht mehr geheilt werden
		if(currentHp <= 0) {
			this.hpFac = 1; 
			this.dmg = 0;
			this.dmgP = 0;
		}
		
		// Wenn fertig geheilt wurde
		if(dmgP <= this.currentHp ) {
			this.currentHp = dmgP;
			dmgP = 0;
			this.hpFac = 1;
		}
		
		
		/* ----- Eigentliche Animation ------ */
		
		
		
		/* Da wird der aktuelle Faktor berechnet
		 * Je n�her das Zielhiel an der akutellen
		 * HP ist, desto kleiner (langsameer) ist der Faktor
		 * 
		 * Der Faktor ist standardm��ig 1
		 * und wird bei z.B 80% healprozess mit 0,2 und NICHT 0,8
		 * subtrahiert und mit 10000 . 
		 */
		else {
			this.hpFac -= (1-dmgP/this.currentHp) /10000 ;
		}
		this.currentHp = currentHp*hpFac;
	}
	/**
	 * Diese Methode zeichnet die Healthbar
	 * @param g das Graphics ding
	 */
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
		g.drawString(StatsEnum.ANG.getText(),84, 50);
		g.drawString(StatsEnum.VERT.getText(),84, 70);
		g.drawString(StatsEnum.SPANG.getText(),84, 90);
		g.drawString(StatsEnum.SPVERT.getText(),84, 110);
		g.drawString(StatsEnum.INIT.getText(),84, 130);
		g.drawString(StatsEnum.GENAU.getText(),84, 150);
		g.drawString(StatsEnum.FLUCHT.getText(),84, 170);
		
		
		statLine.draw(g);
		// statLines[0] = new StatLine(stats);
	}
	
	/**
	 * Diese Methode zeichnet das Auswahlmen�
	 * f�r Skills
	 */
	public void drawSkills(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(209,180, 110, 60);
		g.setFont(new Font("Press Start 2P", 1, 11));
		g.setColor(Color.white);
		
		g.drawString("AP",224,204);
		g.drawString(this.apMax[currentChoice] == 0 ? "-" : this.ap[currentChoice]+"/"+this.ap[currentChoice],265,204);
		g.drawString("Type",224,226);
		

		g.drawImage(this.typeIcons[Typen.valueOf(this.types[currentChoice]).ordinal()],260,214,null);
		
		int xs;
		int ys;
		for (int i = 0; i < skills.length; i++) {
			xs = (i%2 == 0) ? 20 : 115;
			ys = (i <= 1) ? 205 : 227;
			g.setColor((currentChoice == i) ? Color.red : Color.white);
			g.drawString(this.skills[i], xs, ys);
		}
	}
	
	/**
	 * Diese Methode zeichnet das Auswahlmen�
	 * zwischen FIGHT, STATS, POKEMON und RUN
	 * @param g ist das Graphics2D Objekt wo alles gezeichnet wird
	 */
	public void drawSelection(Graphics2D g) {
		g.setFont(new Font("Press Start 2P", 1, 10));
		g.drawString(message, 20, 210);
		g.setFont(new Font("Press Start 2P", 1, 13));
		g.setColor(Color.DARK_GRAY);
		g.fillRect(160,180, 160, 60);
		int xo;
		int yo;
		for (int i = 0; i < options.length; i++) {
			xo = (i%2 == 0) ? 170 : 255;
			yo = (i <= 1) ? 205 : 227;
			g.setColor((currentChoice == i) ? Color.red : Color.white);
			g.drawString(this.options[i], xo, yo);
		}
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
				hpChange(-50);
				break;
			case KeyEvent.VK_V:
				hpChange(70);
				break;
			case KeyEvent.VK_F:
				this.currentHp = this.maxHp;
				break;
			case KeyEvent.VK_ESCAPE:
				this.gsm.setState(0);
				break;
		}
	}
	
	@Override
	public void keyReleased(int k) {
		
	}
}
