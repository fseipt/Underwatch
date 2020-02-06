package poke.game.view.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import poke.game.view.tileMap.Background;

public class MenuState extends GameState {
	
	private Background bg;
	private String[] options = {"New Game", "Load Game", "Quit"	};
	private int currentChoice = 0;
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		try {
			bg = new Background("/Backgrounds/menubg.gif",1);
			bg.setVector(-0.1, 0);
			titleColor = new Color(128,0,0);
			titleFont = new Font("Press Start 2P", 1,28);
			font = new Font("Press Start 2P", 1, 12);
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
		g.drawString("Underwatch", 80, 70 );
		
		// draw menun options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if(i== currentChoice) g.setColor(Color.black);
			else g.setColor(Color.LIGHT_GRAY);
			g.setFont(font);
			g.drawString(options[i],130,140 +i *15 );
		}
	}
	@Override
	public void update() {
		bg.update();
	}
	public void select() {
		switch(currentChoice) {
		case 0:
			gsm.setState(GameStateManager.MENUSTATE);
			break;
		case 1:
			break;
		case 2:
			System.exit(0);
			break;
		}
	}
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) select();
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) currentChoice = options.length -1;
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) currentChoice = 0;
		}
	}
	@Override
	public void keyReleased(int k) {
		
	}
}
