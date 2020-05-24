package poke.game.view.Entries;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import poke.GraphicElement;
import poke.game.programmlogik.item.Item;
import poke.game.programmlogik.move.Move;
import poke.game.programmlogik.typ.Typen;
import poke.game.view.Graphics.StatsEnum;

/**
 * Diese Methoede erzeugt ein Underling Entry GraphicElement
 * @author Amine Boutahar
 */
public class MoveEntry implements GraphicElement {
	private Move p;
	private BufferedImage bg, typ, statsFelder, cat;
	private int y,yO;
	
	private String[] stats;
	private double scrollFac, yD;
	private int scroll, scrollZ, scrollDir, stelle;
	private boolean scrolling;
	private ArrayList<Integer> possible;
	private int speed, speedFac;
	private String name;
	private int[] statsV;
	
	public MoveEntry(Move p, int y) {
		this.speedFac = 1;
		this.speed = 0;
		this.stelle = 0;
		this.possible = new ArrayList<>();
		
		for(int i = 0; i <= 510; i+=85) possible.add(i);
		
		this.scroll = 0;
		this.scrollFac = 1;
		this.scrollZ = 0;
		this.scrollDir = 0;
		
		this.p = p;
		this.y = y;
		this.yD = y;
		this.yO = y;
		this.stats = new String[] {"Pwr","Acc","AP"};
	
		// System.out.println(Typen.valueOf(p.getTyp()[1].getTyp()));
		try {
			this.bg = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/entryBgM.gif"));
			this.cat = ImageIO.read(getClass().getResourceAsStream("/Graphics/types/"+p.getSArt()+".gif"));
			
			this.typ = Typen.valueOf(p.getTyp().getTyp()).getImage();
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
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

		
		g.setFont(new Font("8-bit fortress",0, 7));
		int xS = 65;
		
		for(String s: this.stats) {
			g.drawString(s, xS, y+11);
			xS+=23;
		}
	
		g.drawImage(this.cat,12,y+5,null);
		g.drawImage(this.typ,10,y+22,null);
		g.setColor(Color.white);
		
		g.drawString(""+p.getStaerke(),66,y+22);
		g.drawString(""+p.getGenauigkeit(),65+23,y+22);
		g.drawString(""+p.getAngriffspunkte(),65+45,y+22);
		

		drawString(g,p.getBeschreibung(),138, y);

		
		g.drawString(p.getName(),10,y-11);
	}
	
	@Override
	public void update() {
		if(speed > 4) speedFac = 2;
		else speedFac = 1;
		scrollRunterUpdate();
		scrollRaufUpdate();
	}
	
	
	
	 private void drawString(Graphics g, String text, int x, int y) {
	        for (String line : text.split("§"))
	            g.drawString(line, x, y += g.getFontMetrics().getHeight());
	    }

	public Move getP() {
		return p;
	}

	public boolean scrolling() { return this.scrolling; }
}