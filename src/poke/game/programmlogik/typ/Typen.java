package poke.game.programmlogik.typ;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Typen {
	Normal("Normal"),
	Feuer("Feuer"),
	Wasser("Wasser"),
	Pflanze("Pflanze"),
	Kampf("Kampf"),
	Boden("Boden"),
	Gestein("Gestein"),
	Gift("Gift"),
	Elektro("Elektro"),
	Flug("Flug"),
	Unlicht("Unlicht"),
	Psycho("Psycho"),
	Geist("Geist"),
	Kaefer("Kaefer"),
	Eis("Eis"),
	Stahl("Stahl"),
	Drache("Drache"),
	Fee("Fee");
	
	
	private final String alsText; // Der Typ als Text mit Umlauten
	
	Typen( String alsText) {
		this.alsText = alsText;
	}
	
	public String getText() {
		return alsText;
	}
	public BufferedImage getImage() {
		
		try {
			return ImageIO.read(getClass().getResourceAsStream("/Graphics/types/"+this.alsText+".gif"));
		} 
		catch (IOException e) { e.printStackTrace();  return null; 
		}

	}
	public int getIndex() {
		return ordinal();
	}
}