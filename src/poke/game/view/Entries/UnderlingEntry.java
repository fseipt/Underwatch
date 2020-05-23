package poke.game.view.Entries;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import poke.GraphicElement;
import poke.game.programmlogik.Pokemon;
import poke.game.programmlogik.typ.Typen;
import poke.game.view.Graphics.StatsEnum;

/**
 * Diese Methoede erzeugt ein Underling Entry GraphicElement
 * @author Amine Boutahar
 */
public class UnderlingEntry implements GraphicElement {
	private Pokemon p;
	private BufferedImage rahmen, bg,icon, typen[], statsFelder;
	private int y,yO;
	
	private String[] stats;
	private double scrollFac, yD;
	private int scroll, scrollZ, scrollDir, stelle;
	private boolean scrolling;
	private ArrayList<Integer> possible;
	private int speed, speedFac;
	private String name;
	private int[] statsV;
	
	public UnderlingEntry(Pokemon p, int y) {
		this.speedFac = 1;
		this.speed = 0;
		this.stelle = 0;
		this.possible = new ArrayList<>();
		
		for(int i = 10; i <= 310; i+=60) possible.add(i);
		
		this.scroll = 0;
		this.scrollFac = 1;
		this.scrollZ = 0;
		this.scrollDir = 0;
		
		this.typen = new BufferedImage[2];
		this.p = p;
		this.y = y;
		this.yD = y;
		this.yO = y;
		
	
		String icon;
		if(p.getIcon() == null)  icon = "icon.gif";
		// System.out.println(Typen.valueOf(p.getTyp()[1].getTyp()));
		try {
			this.rahmen = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/iconRahmen.gif"));
			this.icon = ImageIO.read(getClass().getResourceAsStream("/Underlings/"+p.getIcon()+".gif"));
			this.bg = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/entryBg.gif"));
			this.typen[0] = Typen.valueOf(p.getTyp()[0].getTyp()).getImage();
			if(p.getTyp()[1] != null) this.typen[1] = Typen.valueOf(p.getTyp()[1].getTyp()).getImage();
		}
		catch (IOException e) { e.printStackTrace(); }
		
		this.stats = new String[7];
		stats[0] = "HP";
		for(StatsEnum s: StatsEnum.values()) if(s.ordinal()<6)stats[s.ordinal()+1] = s.getAbkuerzung(); 
		stats[6] = "BST";
		
	}
	
	
	public void setY(int y) {
		this.y = y;
		this.yD = y;
	}
	/*
	 * 0=10,  NICHT SICHTBAR
	 * 1=70,
	 * 2= 130
	 * 3= 190
	 * 4= 250 
	 * 5 = 310  NICHT SICHTBAR
	 */
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void moveToStelle(int stelle) {
	
		int zStelle = possible.get(stelle);
		
		speed++;	
		
		
		
		
		// System.out.println(possible.get(stelle));
		
		// int y = possible.get(stelle);
		if(this.y < zStelle) {
			this.scrollDir = -1;
			this.scroll = this.y + zStelle;
		}
		else if(this.y > zStelle) {
			this.scrollDir = 1;
			this.scroll = this.y - zStelle;
		}
		
		
		
		this.scrollZ = zStelle;
		this.scrolling = true;
		if(this.stelle < 5) this.stelle++;
		else this.stelle = 0;
	}
	
	public void scrollRunterUpdate() {
		if(this.scrollDir != -1) return;
		
		
	
		/* ----- Voraussetzungen ------ */
		if(this.scrollZ >= 310) this.scrollZ = 310;
		// if(this.scrollZ > 250) 
		
		// Wenn schon das Maximum erreicht wurde
		if(this.yD >= this.scrollZ && this.scroll > 0) {
			this.yD = this.scrollZ;
			this.yO = this.scrollZ;
			this.scrollFac = 1; // 1 => keine Änderungen
			this.scroll = 0; // Nichts wird abgezogen
			this.scrolling = false;
			return;
		}
		
		// Wenn nichts zu tun ist bzw. HP schon voll ist
		if(this.yD == this.scrollZ || scroll == 0) {
			this.scrolling = false;
			return;
		}
	
		
		this.scrollFac -= (1-this.scrollZ/this.yD) /(1000/speedFac) ;
		this.yD =  this.yD*this.scrollFac;
	}
	
	public void scrollRaufUpdate() {
		if(this.scrollDir != 1) return;
		/* ----- Voraussetzungen ------ */
		if(this.scrollZ <= 10) this.scrollZ = 10;
		
		// Wenn schon das Maximum erreicht wurde
		if(this.yD <= this.scrollZ && this.scroll > 0) {
			this.yD = this.scrollZ;
			this.yO = this.scrollZ;
			this.scrollFac = 1; // 1 => keine Änderungen
			this.scroll = 0; // Nichts wird abgezogen
			this.scrolling = false;
			return;
		}
		
		
		if(this.yD == this.scrollZ || scroll == 0) {
			this.scrolling = false;
			return;
		}
		
		else this.scrollFac -= (1-this.scrollZ/this.yD) /(1000/speedFac) ;
		this.yD =  this.yD*this.scrollFac;
	}
	
	@Override
	public void draw(Graphics2D g) { 
		this.y = (int) yD;
		g.setColor(Color.black);
		g.drawImage(bg,0,y-23,null);
		// Icon
		g.drawImage(rahmen,5,y,null);
		g.drawImage(icon,7,y+2,null);
		g.setFont(new Font("Press Start 2P", 1, 8));
		
		
		g.setFont(new Font("8-bit fortress",0, 7));
		int xS = 147;
		
		g.setColor(Color.DARK_GRAY);
		for(String s: stats) {
			g.drawString(s,xS,y+10);
			xS+=24;
		}
		
		xS = 147;
		
		this.statsV = p.getStatsAmine();
		
		g.setColor(Color.white);
		for(int s :statsV) {
			g.drawString(""+s,xS,y+23);
			xS+=24;
		}
		
		
		/*for(int i: p.getStats()) {
			if(i != 1) g.drawString(""+i,xS,y+23);
			else g.drawString(""+p.getStats()[9],xS,y+23);
			xS+=24;
		}*/
	
		
		g.drawString(p.getName(),10,y-5);
		
		
		if(p.getTyp()[1] != null) {
			g.drawImage(typen[0],45,y+11,null);
			g.drawImage(typen[1],93,y+11,null);
		}
		else g.drawImage(typen[0],69,y+11,null);
		
	}
	
	@Override
	public void update() {
		if(speed > 4) speedFac = 2;
		else speedFac = 1;
		scrollRunterUpdate();
		scrollRaufUpdate();
	}
	
	
	
	
	public Pokemon getP() {
		return p;
	}

	public boolean scrolling() { return this.scrolling; }
}