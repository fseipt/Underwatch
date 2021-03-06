
package poke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import poke.game.view.gameState.GameStateManager;

/**
 * Diese Klasse f�hrt das Spiel in Dauerschleife aus.
 * Damit ein Objekt dieser Klasse dem Frame geaddet werden
 * kann, erbt sie von Canvasss
 * @author Amine Boutahar
 * @verion 21.01.2020
 */
public class Launcher extends JPanel implements Runnable, KeyListener{
	// dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 3;
	
	
	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000/FPS;
	
	// image
	private BufferedImage image;
	private Graphics2D g;
	
	// game state manager
	private GameStateManager gsm;
	private Controller c;
	
	
	public Launcher(Controller c) {
		this.c = c;
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setFocusable(true);
		requestFocus(); 
	}
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	@Override
	public void run() {
		init();
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			start = System.nanoTime();
			update();
			draw();
			drawToScreen(); 
			
			elapsed = System.nanoTime() -start;
			wait = targetTime - elapsed / 100000;
			if(wait < 0) wait = 1;
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	private void update()  {
		gsm.update();
	}
	private void draw() { gsm.draw(g);}
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image,0,0, WIDTH*SCALE,HEIGHT*SCALE,null);
		g2.dispose();
		
	}
	
	
	public void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
		
		gsm = new GameStateManager(c);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0)  { if(gsm != null) gsm.keyPressed(arg0.getKeyCode()); }
	@Override
	public void keyReleased(KeyEvent arg0) { if(gsm != null) gsm.keyReleased(arg0.getKeyCode());}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}


