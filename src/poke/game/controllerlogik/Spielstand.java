package poke.game.controllerlogik;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

import poke.game.programmlogik.WrongArgumentException;

public class Spielstand implements Serializable {

	private String name;
	private int score, index;
	private Spieler team;
	
	/**
	 * Konstruktor fuer einen Spielstand
	 * @param name der Name
	 * @param score der Score
	 * @param index der Index des Iconsarrays
	 * @param team das Team
	 */
	public Spielstand(String name, int score, int index, Spieler team) {
		this.name = name;
		this.score = score;
		this.index = index;
		this.team = team;
	}
	
	/**
	 * Setzt das Icon
	 * @param i der Index des Icons array
	 */
	public void setIcon(int i) {
		this.index = i;
	}
	/**
	 * Setzt das Team
	 * @param team das Team
	 */
	public void setTeam(Spieler team) {
		this.team = team;
	}
	/**
	 * Setzt den Score
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * erhoet den Score um 1
	 */
	public void incScore() {
		this.score++;
	}
	/**
	 * laedt einen Spielstand
	 * @param file der Spielstand Dateiname
	 * @return
	 */
	public Spielstand laden(int file)  {
	    String name = "res/Spielstand/SP"+file+".txt";
	    Spielstand s = null;
		try (ObjectInputStream inputStream = new ObjectInputStream(
							new FileInputStream(name))) {
			s = (Spielstand) inputStream.readObject();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return s;
	}
	
	/**
	 * Speichert einen Spielstand
	 * @param file der Spielstand Dateiname
	 */
	public void speichern(int file) {
		try {
			if(file > 3 || file < 0) {
				throw new WrongArgumentException();
			}
			String name = "res/Spielstand/SP"+file+".txt";
			File temp;
			try (ObjectOutputStream outputStream = new ObjectOutputStream(
													new FileOutputStream(name))) {
			outputStream.writeObject(new Spielstand(this.name, this.score, this.index, this.team));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (WrongArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * checkt ob es Spielstände gibt
	 * @return gibt ein int Array zurueck 1 fuer vorhanden 0 fuer nicht vorhanden
	 */
	public int[] checkStand() {
		boolean f1 = false ,f2 = false,f3 = false;
		int[] c = new int[3];
		File temp;
		for(int x = 0; x < c.length; x++) {
			c[x] = 0;
		}
		try {
			temp = File.createTempFile("SP1", ".txt");
		    f1 = temp.exists();
		    temp = File.createTempFile("SP2", ".txt");
		    f2 = temp.exists();
		    temp = File.createTempFile("SP3", ".txt");
		    f3 = temp.exists();
		} catch (IOException e) {
		   e.printStackTrace();
		}
		if(f1) {
			c[0] = 1;
		}
		if(f2) {
			c[1] = 1;
		}
		if(f3) {
			c[2] = 1;
 		}
		return c;
	}
	
}
