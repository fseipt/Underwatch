package poke.game.view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import poke.Controller;
import poke.Launcher;

public class Frame extends JFrame{

	public Frame(String name, Controller c) {
		JFrame window = new JFrame(name);
		window.setContentPane(new Launcher(c));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		window.setBackground(Color.white);
		try {
			BufferedImage icon = ImageIO.read(getClass().getResourceAsStream("/Graphics/Logo.gif"));
			window.setIconImage(icon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
