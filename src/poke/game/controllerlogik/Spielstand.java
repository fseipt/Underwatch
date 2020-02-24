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

public class Spielstand implements Serializable {

	private String name;
	private int score, index;
	private Spieler team;
	
	public Spielstand(String name, int score, int index, Spieler team) {
		this.name = name;
		this.score = score;
		this.index = index;
		this.team = team;
	}
	
	public void setTeam(Spieler team) {
		this.team = team;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void incScore() {
		this.score++;
	}

	public Spielstand laden(int file)  {
	    String name = "res/Spielstand/"+file+".txt";
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
	
	public void speichern(int file) {
		String name = "res/Spielstand/"+file+".txt";
		File temp;
		try (ObjectOutputStream outputStream = new ObjectOutputStream(
												new FileOutputStream(name))) {
			outputStream.writeObject(new Spielstand(this.name, this.score, this.index, this.team));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
