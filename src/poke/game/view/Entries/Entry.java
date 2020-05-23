package poke.game.view.Entries;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import poke.GraphicElement;

public abstract class Entry implements GraphicElement {
	
	protected int y, yO;
	protected double yD, scrollFac; 
	private boolean scrolling;
	protected int scrollZ, stelle, scroll, scrollDir;
	protected BufferedImage rahmen, nullIcon, bg;
	public final int DIVIDEND = 5000;
	
	public Entry() {
		// Scrollen
		this.scroll = 0;
		this.scrollDir = 0;
		this.scrollFac = 1;
		
		this.stelle = 0;
		this.yD = 10;
		this.yO = 10;
		this.y = 10;
		this.scrollZ = 10;
		try {
			this.rahmen = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/iconRahmen.gif"));
			this.nullIcon = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/nullIcon.gif"));
			this.bg = ImageIO.read(getClass().getResourceAsStream("/Graphics/UI/entryBg.gif"));
		}
		catch (IOException e) { e.printStackTrace(); }
	}
	
	public void setY(int y) { this.y = y; }
	public void setYD(double yD) { this.yD = yD; }
	public void setYO(int yO) { this.yO = yO; }
	public void setScrollZ(int scrollZ) { this.scrollZ = scrollZ; }	
	public void setScrollFac(double scrollFac) { this.scrollFac = scrollFac; }
	public void setScroll(int scroll) { this.scroll = scroll; }
	public void setScrolling(boolean scrolling) { this.scrolling = scrolling; }
	public void setScrollDir(int scrollDir) { this.scrollDir = scrollDir; }

	public int getScroll() {return scroll; }
	public int getScrollDir() { return scrollDir; }
	public double getScrollFac() { return this.scrollFac; }
	public int getScrollZ() { return this.scrollZ; 	}
	public int getY() { return this.y; }
	public int getYO() { return this.yO;}
	public double getYD() { return this.yD; }

	public boolean scrolling() { return this.scrolling; }
	
	public void calculateYD(double speedFac) {
		double abzug = (1-this.scrollZ/this.yD) /(DIVIDEND/1);
		this.scrollFac -= abzug;
		this.yD =  this.yD*this.scrollFac;
	
	}
}
