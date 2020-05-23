package poke.game.view.Entries;

import java.awt.Graphics2D;
import java.util.ArrayList;

import poke.GraphicElement;

public class Selection implements GraphicElement {
	
	private int speed, speedFac;
	private int[] possible = new int[6];
	private Entry[] entries;
	

	/*
	 * 0=10,  NICHT SICHTBAR
	 * 1=70,
	 * 2= 130
	 * 3= 190
	 * 4= 250 
	 * 5 = 310  NICHT SICHTBAR
	 */
	public Selection(Entry[] entries) {
		// Entries
		this.entries = entries;
		
		// Mögliche Stellen
		for(int i = 0; i < 6; i++) possible[i] = 10+(i*60);
		
		// Speed
		// this.speedFac = 1;
		// this.speed = 0;
	}
	
	public void setSpeed(int speed) { this.speed = speed; }
	
	public void moveToStelle(int dir) {
		// speed++;	
		Entry e;
		int i = 0;
		int ende = this.entries.length;
		
		if(dir > 0) ende -= dir;
		else i+= dir;
		
		for(; i < ende; i++) {
			e = this.entries[i];
			if(e == null) continue; 
			e.setScrolling(true);
		
			int zStelle = possible[i+dir];
			if(e.getY() < zStelle) {
				e.setScrollDir(-1);
				e.setScroll(e.getY()+zStelle);
			}
			else if(e.getY()  > zStelle) {
				e.setScrollDir(1);
				e.setScroll(e.getY()-zStelle);
			}
			e.setScrollZ(zStelle);
		}
	}
	
	
	public void scrollRunterUpdate() {
		Entry e;
		for(int i = 0; i < this.entries.length; i++) {
			e = this.entries[i]; 
			if(e == null) continue;
			if(e.getScrollDir() != -1) return;
			
			/* ----- Voraussetzungen ------ */
			// Wenn das Scrollziel zu weit ist
			if(e.getScrollZ() >= 310) e.setScrollZ(310);
			
			// Wenn schon das Maximum erreicht wurde
			if(e.getYD() >= e.getScrollZ()) {
				e.setYD(e.getScrollZ());
				e.setYO(e.getScrollZ());
				e.setScrollFac(1); // 1 => keine Änderungen
				e.setScroll(0);
				e.setScrolling(false);
				return;
			} 
			/* if(e.getScroll() == 0) {
				e.setScrolling(false);
				return;
			} */
			
			e.calculateYD(speedFac);
		}
	}
	
	public void scrollRaufUpdate() {
		Entry e;
		for(int i = 0; i < this.entries.length; i++) {
			e = this.entries[i]; 
			if(e == null) continue;
			if(e.getScrollDir() != 1) return;
			/* ----- Voraussetzungen ------ */
			// Wenn das Scrollziel zu weit ist
			if(e.getScrollZ() <= 10) e.setScrollZ(10);
			// Wenn schon das Maximum erreicht wurde
			if(e.getYD() <= e.getScrollZ()) {
				if(i == 2) System.out.println("IXHHH HABEEEE GESTOPPTTTT");
				e.setYD(e.getScrollZ());
				e.setYO(e.getScrollZ());
				e.setScrollFac(1); // 1 => keine Änderungen
				e.setScroll(0); // Nichts wird abgezogen
				
				e.setScrolling(false);
				return;
			}
			/*if(e.getScroll() == 0 ) {
				if(i == 2) System.out.println("IXHHH HABEEEE GESTOPPTTTT");
				e.setScrolling(false);
				return;
			} */
			else e.calculateYD(speedFac);
		}
	}
	public void neueRenderbare(Entry[] entries) {
		this.entries = entries;
	}
	
	@Override
	public void draw(Graphics2D g) {
		for(Entry e: entries) if(e != null) e.draw(g);
	}
	@Override
	public void update() {
		System.out.println(this.entries[2].getYD()+"\nYYY"+entries[2].getScrollZ());
		scrollRunterUpdate();
		scrollRaufUpdate();
//		if(speed > 5) speedFac = 5;
		// else speedFac = 1;
	}
	public boolean scrolling() {
		return this.entries[1].scrolling();
	}
	
}
