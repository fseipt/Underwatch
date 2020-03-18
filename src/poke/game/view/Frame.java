package poke.game.view;

import java.awt.Color;
import javax.swing.JFrame;
import poke.*;

public class Frame extends JFrame{

	public Frame(String name, Controller c) {
		JFrame window = new JFrame(name);
		window.setContentPane(new Launcher(c));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		window.setBackground(Color.white);
	}
}
