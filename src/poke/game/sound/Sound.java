package poke.game.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class Sound {
	
	String clickSound;
	Clip clip;
	public Sound(String file){
		this.clickSound = file;
	}
	public void play(){	
		try {
			File file = new File(clickSound);
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);	
			clip = AudioSystem.getClip();
			clip.open(sound);
			clip.setFramePosition(0);
			clip.start();
		} 
		catch(Exception e){	
			System.err.println(e.getMessage());
		}
	}
}
