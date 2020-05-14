package poke.game.view.Entries;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
	private BufferedImage rahmen, bg,icon, typen[];
	private int y,i;
	private String[] stats;
	private double scrollFac, yD;
	private int scroll, scrollZ, scrollDir;
	
	public UnderlingEntry(Pokemon p, int y) {
		this.scroll = 0;
		this.scrollFac = 1;
		this.scrollZ = 0;
		this.scrollDir = 0;
		
		this.typen = new BufferedImage[2];
		this.p = p;
		this.y = y;
		this.yD = y;
		// System.out.println(Typen.valueOf(p.getTyp()[1].getTyp()));
		try {
			this.rahmen = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/iconRahmen.gif"));
			this.icon = ImageIO.read(getClass().getResourceAsStream("/Underlings/icon3.gif"));
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
	
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void scroll(int dir) {
		this.scrollFac = 1;
		this.scroll = 60;
		if(dir == -1) this.scrollZ = this.y + this.scroll;
		else if(dir == 1) this.scrollZ = this.y - this.scroll; 
		this.scrollDir = dir;
	}
	
	public void scrollRunterUpdate() {
		if(this.scrollDir != -1) return;
		
		/* ----- Voraussetzungen ------ */
		
		
		// Wenn schon das Maximum erreicht wurde
		if(this.yD >= this.scrollZ && this.scroll > 0) {
			this.yD = this.scrollZ;
			this.scrollFac = 1; // 1 => keine �nderungen
			this.scroll = 0; // Nichts wird abgezogen
			this.scrollZ = 0;
			return;
		}
		
		// Wenn nichts zu tun ist bzw. HP schon voll ist
		if(this.yD == this.scrollZ || scroll == 0) return;
		
		// Wenn der zu erreichende HP Wert gr��er als das Maximum
		// Ist, wird er auf die MaxHp gesetzt.
		//if(dmgP > this.maxHp) dmgP = this.maxHp;
		
		// Wenn jmd Tot ist, Kann nicht mehr geheilt werden
		/*if(currentHp <= 0) {
			this.hpFac = 1; 
			this.dmg = 0;
			this.dmgP = 0;
		}*/
		/*
		// Wenn fertig geheilt wurde
		if(this.scrollZ <= this.y ) {
			this.y = scrollZ;
			this.scrollFac = 1;
			this.scrollZ = 0;
			this.scroll = 0;
		} */
		
		
		/* ----- Eigentliche Animation ------ */
		
		
		
		/* Da wird der aktuelle Faktor berechnet.
		 * Je n�her das Scrollziel an der akutellen
		 * Y-Koordinate ist, desto kleiner (langsameer) ist der Faktor.
		 * 
		 * Der Faktor ist standardm��ig 1
		 * und wird bei z.B 80% healprozess mit 0,2 und NICHT 0,8
		 * subtrahiert und mit 10000 . 
		 */
		this.scrollFac -= (1-this.scrollZ/this.yD) /3000 ;
		this.yD =  this.yD*this.scrollFac;
	}
	
	public void scrollRaufUpdate() {
		if(this.scrollDir != 1) return;
		/* ----- Voraussetzungen ------ */
		
		
		// Wenn schon das Maximum erreicht wurde
		if(this.yD <= this.scrollZ && this.scroll > 0) {
			this.yD = this.scrollZ;
			this.scrollFac = 1; // 1 => keine �nderungen
			this.scroll = 0; // Nichts wird abgezogen
			this.scrollZ = 0;
			return;
		}
		
		// Wenn nichts zu tun ist bzw. HP schon voll ist
		if(this.yD == this.scrollZ || scroll == 0) return;
		
		
		/* ----- Eigentliche Animation ------ */
		
		
		
		/* Da wird der aktuelle Faktor berechnet.
		 * Je n�her das Scrollziel an der akutellen
		 * Y-Koordinate ist, desto kleiner (langsameer) ist der Faktor.
		 * 
		 * Der Faktor ist standardm��ig 1
		 * und wird bei z.B 80% healprozess mit 0,2 und NICHT 0,8
		 * subtrahiert und mit 10000 . 
		 */
		else this.scrollFac -= (1-this.scrollZ/this.yD) /3000 ;
		this.yD =  this.yD*this.scrollFac;
	}
	
	@Override
	public void draw(Graphics2D g) { 
		this.y = (int) yD;
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
		scrollRunterUpdate();
		scrollRaufUpdate();
	}
	
}
