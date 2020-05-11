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
import poke.game.view.Graphics.Stats;
import poke.game.view.Graphics.StatsEnum;

public class UnderlingEntry implements GraphicElement {
	private Pokemon p;
	private BufferedImage rahmen, bg,icon, typ1, typ2, typ3;
	private int y,i;
	private String[] stats;
	public UnderlingEntry(Pokemon p, int y) {
		this.p = p;
		this.y = y;
		try {
			this.rahmen = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/iconRahmen.gif"));
			this.icon = ImageIO.read(getClass().getResourceAsStream("/Underlings/icon2.gif"));
			this.bg = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/entryBg.gif"));
			this.typ1 = Typen.Elektro.getImage();
			this.typ2 = Typen.Stahl.getImage();
			this.typ3 = Typen.Wasser.getImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.stats = new String[7];
		for(StatsEnum s: StatsEnum.values()) stats[s.ordinal()] = s.getAbkuerzung();
		stats[5] = "BST";
		stats[6] = "";
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.drawImage(bg,0,y-23,null);
			
	
		g.drawImage(rahmen,10,y,null);
		g.drawImage(icon,11,y+1,null);
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
		
		g.drawImage(typ3,73,y+10,null);
		
	}
	
	@Override
	public void update() {
		
	}
}
