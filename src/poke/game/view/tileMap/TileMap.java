package poke.game.view.tileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import poke.Launcher;

public class TileMap {
	
	
	// position
	private double x, y;
	
	// bounds
	private int xmin, ymin, xmax, ymax;
	
	private double tween;

	// map
	private int[][] map;
	private int tileSize, numRows, numCols, width, height;
	
	// tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	// drawing bounds
	private int rowOffset,colOffset, numRowsToDraw, numColsToDraw;
	
	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = Launcher.HEIGHT / tileSize +2; // Wie vielem tiles gezeichnet werden sollen -> Performance
		numColsToDraw = Launcher.WIDTH / tileSize +2;
		tween = 0.07; 
	}
	
	public void loadTiles(String s) {
		try {
			tileset = ImageIO.read(getClass().getResourceAsStream(s));
			numTilesAcross =  tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];
			
			BufferedImage subimage;
			for (int col = 0; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage(col*tileSize,0,tileSize,tileSize);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				subimage = tileset.getSubimage(col*tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED); // Sowas wie Boden
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String s) {
		
		try {
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			numCols = Integer.parseInt(br.readLine()); // Wie viele Spalten die Map hat
			numRows = Integer.parseInt(br.readLine()); // Wie viele Zeilen die Map hat
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize; 
			
			xmin = Launcher.WIDTH - width;
			xmax = 0;
			ymin = Launcher.HEIGHT - height;
			ymax = 0;
			
			String delims = "\\s+";
			
			for (int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for (int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
		}
		catch(Exception e) { e.printStackTrace();}
	}

	public int getX() { return (int) x; }
	public int getY() { return (int) y; }
	public int getTileSize() { return tileSize; }
	public int getWidth() { return width;	}
	public int getHeight() { return height;	}
	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}
	
	public void setPosition(double x, double y) {
		this.x  += (x-this.x)*tween;
		this.y += (y-this.y)* tween;
		
		fixBounds();
		
		colOffset = (int)-this.x/tileSize;
		rowOffset = (int)-this.y/tileSize;
	}
	
	public void fixBounds() {
		if(x < xmin)  x = xmin;
		if(y < ymin) y  = ymin;
		if(x > xmax)  x = xmax;
		if(y > ymax) y  = ymax;
	}
	
	public void draw(Graphics2D g) {
		for (int row = rowOffset; row < rowOffset+numRowsToDraw; row++) {
			if(row >= numRows) break;
			for(int col = colOffset; col < colOffset+numColsToDraw;col++) {
				 if(col >= numCols) break;
				 if(map[row][col]== 0) continue;
				 int rc = map[row][col];
				 int r = rc / numTilesAcross;
				 int c = rc % numTilesAcross;
				 
				 g.drawImage(tiles[r][c].getImage(),(int)x+ col*tileSize, (int)y+row*tileSize,null);
			}
		}
	}
}
