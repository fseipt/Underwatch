package poke.game.view.Entries;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import poke.GraphicElement;
import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.typ.Typen;

public class UnderlingEntry implements GraphicElement {
	private Pokemon p;
	private BufferedImage rahmen, bg,icon;
	private int y,i;
	
	public UnderlingEntry(Pokemon p, int y, int i) {
		this.p = p;
		this.y = y;
		this.i = i;
		try {
			rahmen = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/iconRahmen.gif"));
			icon = ImageIO.read(getClass().getResourceAsStream("/Underlings/icon"+i+".gif"));
			bg = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/entryBg.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(bg,0,y-5,null);
		g.drawImage(icon,10,y,null);
		
		Image tmp = rahmen.getScaledInstance(34, 34, Image.SCALE_AREA_AVERAGING);
	    BufferedImage dimg = new BufferedImage(34, 34, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    
	    
		g.drawImage(dimg,10,y,null);
		g.setFont(new Font("Press Start 2P", 1, 10));
		g.setColor(Color.black);
		g.drawString("Name",9,y+45);
		if(i == 1 ) {
			g.drawImage(Typen.valueOf("Elektro").getImage(),50,y+10,null);
			g.drawImage(Typen.valueOf("Stahl").getImage(),100,y+10,null);
			g.drawString("123 89 165 76 67 113 103", 165, y+22);
		}
		else {
			g.drawImage(Typen.valueOf("Wasser").getImage(),73,y+10,null);
			g.drawString("123 89 165 76 67 113 103", 165, y+22);
		}
	}
	
	@Override
	public void update() {
		
	}
}
