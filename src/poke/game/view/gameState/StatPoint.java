package poke.game.view.gameState;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import poke.game.view.tileMap.Animation;

import poke.game.view.tileMap.Animation;

public class StatPoint {
	// animations
	private ArrayList<BufferedImage[]> sprites;
	
	// Wie viele frames eine Animation hat
	private final int numFrames = 2;
	private int width = 9;
	private int height = 9;
	private Animation animation;
	private int x,y;

	private BufferedImage spriteSheet; // Sheets braucht man für animationen
	public StatPoint(int val, int x, int y) {
		this.x = x;
		this.y = y;
		// load sprites
		try {
			String s = "/Stats/";
			s += (val < 0 ? "down2.gif" : (val > 0 ? "up2.gif" : "dot.gif"));
			
			this.spriteSheet = ImageIO.read(getClass().getResourceAsStream(s));
			
			sprites = new ArrayList<BufferedImage[]>();
			
			if(s == "/Stats/dot.gif") {
				BufferedImage[] bi = new BufferedImage[1];
				bi[0] = spriteSheet;
				sprites.add(bi);
			}
			else {
				for (int i = 0; i < 2; i++) {
					BufferedImage[] bi = new BufferedImage[numFrames];
						for(int j = 0; j < numFrames; j++ ) {
							bi[j] = spriteSheet.getSubimage(i*width, 0, width, height);
						}
					sprites.add(bi); 
				}
			}
		}
		catch(Exception e) { e.printStackTrace(); }
		animation = new Animation();
		animation.setFrames(sprites.get(0));
		animation.setDelay(150);
		
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(animation.getImage(), x, y, null);
	}
public void update() {
		
		animation.update();
		
	}
}

