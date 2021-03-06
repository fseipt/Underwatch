package poke.game.view.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import poke.Controller;
import poke.game.sound.Sound;
import poke.game.view.tileMap.Background;

public class LoadGame extends GameState {
	
	private Background bg;
	private String[] options = {"Spielstand 1", "Spielstand 2", "Spielstand 3"};
	private int currentChoice = 0;
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private Sound select2;
	private BufferedImage iconBg;
	public LoadGame(GameStateManager gsm, Controller c) {
		
		this.gsm = gsm;
		try { // heyy
			this.select2 = new Sound("res/Sound/MenuSelect.wav");
			bg = new Background("/Backgrounds/menubg.gif",1);
			bg.setVector(-0.1, 0);
			titleColor = new Color(128,0,0);
			titleFont = new Font("Press Start 2P", 1,28);
			font = new Font("Press Start 2P", 1, 12);
			this.iconBg = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/speicherIconBg.gif"));
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
		
		
		// draw menun options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if(i== currentChoice) g.setColor(Color.black);
			else g.setColor(Color.LIGHT_GRAY);
			g.setFont(font);
			g.drawString(options[i],40+(i *90),177);
			g.drawImage(iconBg,45+i*89,85,null);
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
