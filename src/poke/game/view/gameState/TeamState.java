package poke.game.view.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import poke.Controller;
import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.typ.Typen;
import poke.game.sound.Sound;
import poke.game.view.tileMap.Background;

public class TeamState extends GameState {
	
	private Background bg;
	private String[] options = {"Spielstand 1", "Spielstand 2", "Spielstand 3"};
	private int currentChoice = 0;
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private Sound select2;
	private BufferedImage entryBg, typen[][],icon[], rahmen;
	public TeamState(GameStateManager gsm, Controller c) {
		Pokemon[] p = new Pokemon[6];
		for(int i = 0; i<6;i++) p[i] = c.getPoke().getPokemon()[i];
		
		this.typen = new BufferedImage[6][2];
		this.icon = new BufferedImage[6];
		
		this.gsm = gsm;
		try { // heyy
			this.select2 = new Sound("res/Sound/MenuSelect.wav");
			bg = new Background("/Backgrounds/menubg.gif",1);
			bg.setVector(-0.1, 0);
			
			this.entryBg = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/entrySBg.gif"));
			
			for(int i = 0; i<6;i++) {
				this.icon[i] = ImageIO.read(getClass().getResourceAsStream("/Underlings/"+p[i].getIcon()+".gif"));
				this.typen[i][0] = Typen.valueOf(p[i].getTyp()[0].getTyp()).getImage();
				if(p[i].getTyp()[1] != null) this.typen[i][1] = Typen.valueOf(p[i].getTyp()[1].getTyp()).getImage();
			}
				this.rahmen = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/iconRahmen.gif"));
			
			// 
			
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
		
		for(int i = 0; i < 3; i++) {
			g.drawImage(this.entryBg,10,60+(i*41),null);
			g.drawImage(this.rahmen,7,62+(i*41),null);
			g.drawImage(this.icon[i],9,64+(i*41),null);
		}
		
		for(int i = 0; i < 3; i++) {
			
			g.drawImage(this.entryBg,165,60+(i*41),null);
			g.drawImage(this.rahmen,165,62+(i*41),null);
			g.drawImage(this.icon[i+3],168,64+(i*41),null);
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
