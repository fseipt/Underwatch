package poke.game.view.Graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import poke.GraphicElement;

public class StatLine implements GraphicElement{
	private int[] stats;
	private int stat;
	private BufferedImage up,down,dot;
	private StatPoint[][] sP = new StatPoint[7][6];
	public StatLine(int[] stats) {
		this.stats = stats;
		try {
			this.up = ImageIO.read(getClass().getResourceAsStream("/Stats/up.gif"));
			this.down = ImageIO.read(getClass().getResourceAsStream("/Stats/down.gif"));
			this.dot = ImageIO.read(getClass().getResourceAsStream("/Stats/dot.gif"));
		}
		catch (Exception e) {
			System.err.println("IRrrrgendwas ist schieef gelaufen bei den sttaaats");
		}
		int x = 178;
		int y = 42;
		int ups = 0;
		for(int u = 0; u < 7; u++) {
			for (int i = 0; i < 6; i++) {
				
				sP[u][i] = new StatPoint((stats[u] > 0 ? (stats[u] > i ? 1 : 0) : 
											(stats[u] < 0 ? 
											(stats[u] < i*(-1) ? -1 : 0) 
											: 0)) , x,y);
			/*	g.drawImage((stats[u] > 0 ? (stats[u] > i ? up : dot) : 
							(stats[u] < 0 ? 
							(stats[u] < i*(-1) ? down : dot) 
							: dot))  , x, y, null); */
				x+=15;
			}
			y+=20;
		x = 178;
		}
	}
	public void draw(Graphics2D g) {
		for (int i = 0; i < sP.length; i++) {
			for (int j = 0; j < sP[i].length; j++) {
				sP[i][j].draw(g);
			}
		} 
	}
	
	public void update() {
		for (int i = 0; i < sP.length; i++) {
			for (int j = 0; j < sP[i].length; j++) {
				if(sP[i][j] != null) sP[i][j].update();
			}
		}
	}

}
