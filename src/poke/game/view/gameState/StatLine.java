package poke.game.view.gameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class StatLine {
	private int[] stats;
	private int stat;
	private BufferedImage up,down,dot;
	
	public StatLine(int[] stats) {
		this.stats = stats;
		try {
			this.up = ImageIO.read(getClass().getResourceAsStream("/Stats/dot.gif"));
			this.down = ImageIO.read(getClass().getResourceAsStream("/Stats/up.gif"));
			this.dot = ImageIO.read(getClass().getResourceAsStream("/Stats/down.gif"));
		}
		catch (Exception e) {
			System.err.println("IRrrrgendwas ist schieef gelaufen bei den sttaaats");
		}
	}
	public void draw(Graphics2D g) {
		g.drawImage(down, 100, 100, null);
	}

}
