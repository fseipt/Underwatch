package poke.game.view.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import poke.game.sound.Sound;
import poke.game.view.tileMap.Background;

public class MenuState extends GameState {
	
	private Background bg;
	private String[] options = {"New Game", "Selection", "Combat", "Items","Moves","Load", "Team", "Quit"	};
	private int currentChoice = 0;
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private Sound select2;
	private BufferedImage logo;
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		try { // heyy
			this.select2 = new Sound("res/Sound/MenuSelect.wav");
			bg = new Background("/Backgrounds/menubg.gif",1);
			bg.setVector(-0.1, 0);
			titleColor = new Color(128,0,0);
			titleFont = new Font("Press Start 2P", 1,28);
			font = new Font("Press Start 2P", 1, 12);
			this.logo = ImageIO.read(getClass().getResourceAsStream("/Graphics/FullLogo.gif"));
		}
 		catch(Exception e) {
 			e.printStackTrace();
 		}
	}
	
	@Override
	public void init() {
	}
	@Override
	public void draw(Graphics2D g) {
		// draw background
		bg.draw(g);
		
		// Draw Title
		g.setColor(titleColor);
		g.setFont(titleFont);
		
		//g.drawImage(logo,30,10, null);
		// g.drawString("Underwatch", 80, 70 );
		
		// draw menun options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if(i== currentChoice) g.setColor(Color.red);
			else g.setColor(Color.black);
			g.setFont(font);
			g.drawString(options[i],130,40 +i *15 );
		}
	}
	@Override
	public void update() {
		bg.update();
	}
	public void select() { // Servus
		switch(currentChoice) {
		case 0:
			gsm.setState(6); // Servus
			break;
		case 1: 
			gsm.setState(GameStateManager.SELECTION);
			break;
		case 2:
			gsm.setState(GameStateManager.COMBAT); // seruss
			break;
		case 3: 
			gsm.setState(GameStateManager.SELECTIONITEMS);
			break;
		case 4:
			gsm.setState(GameStateManager.SELECTIONMOVES);
			break;
		case 5:
			gsm.setState(GameStateManager.LOAD);
			break;
		case 6:
			gsm.setState(GameStateManager.TEAM);
			break;
		case 7:
			System.exit(0);// Servusservuss
			break;
			
		}
	}
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) select();
		if(k == KeyEvent.VK_UP) {
			select2.play();
			currentChoice--;
			if(currentChoice == -1) currentChoice = options.length -1;
		}
		if(k == KeyEvent.VK_DOWN) {
			select2.play();
			currentChoice++;
			if(currentChoice == options.length) currentChoice = 0;
		}
	}
	@Override
	public void keyReleased(int k) {
		
	}
}
