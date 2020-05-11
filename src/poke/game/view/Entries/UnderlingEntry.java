package poke.game.view.Entries;

import java.awt.Color;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import poke.GraphicElement;
import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.typ.Typen;
import poke.game.view.Graphics.StatsEnum;

public class UnderlingEntry implements GraphicElement {
	private Pokemon p;
	private BufferedImage rahmen, bg,icon, typen[];
	private int y,i;
	private String[] stats;
	public UnderlingEntry(Pokemon p, int y) {
		this.typen = new BufferedImage[2];
		this.p = p;
		this.y = y;
		// System.out.println(Typen.valueOf(p.getTyp()[1].getTyp()));
		try {
			this.rahmen = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/iconRahmen.gif"));
			this.icon = ImageIO.read(getClass().getResourceAsStream("/Underlings/icon2.gif"));
			this.bg = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/entryBg.gif"));
			this.typen[0] = Typen.valueOf(p.getTyp()[0].getTyp()).getImage();
			if(p.getTyp()[1] != null) this.typen[1] = Typen.valueOf(p.getTyp()[1].getTyp()).getImage();
		}
		catch (IOException e) { e.printStackTrace(); }
		
		this.stats = new String[7];
		
		for(StatsEnum s: StatsEnum.values()) stats[s.ordinal()] = s.getAbkuerzung();
		stats[5] = "BST";
		stats[6] = "";
	}
	
	
	@Override
	public void draw(Graphics2D g) { //f
		g.setColor(Color.black);
		g.drawImage(bg,0,y-23,null);
			
		// Icon
		g.drawImage(rahmen,7,y,null);
		g.drawImage(icon,9,y+2,null);
		g.setFont(new Font("Press Start 2P", 1, 8));
		
		
	
		
		g.setFont(new Font("8-bit fortress",0, 7));
		int xS = 171;
		
		g.setColor(Color.DARK_GRAY);
		for(String s: stats) {
			g.drawString(s,xS,y+10);
			xS+=24;
		}
		xS = 171;
		
		g.setColor(Color.white);
		for(int i: p.getStats()) {
			g.drawString(""+i,xS,y+23);
			xS+=24;
		}
	
		
		g.drawString("Wasserding",10,y-5);
		
		
		if(p.getTyp()[1] != null) {
			g.drawImage(typen[0],45,y+11,null);
			g.drawImage(typen[1],93,y+11,null);
		}
		else g.drawImage(typen[0],69,y+11,null);
		
	}
	
	@Override
	public void update() {
		
	}
}
