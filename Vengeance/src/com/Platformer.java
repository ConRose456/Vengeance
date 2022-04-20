package com;

import java.awt.BorderLayout;
import java.net.MalformedURLException;

import javax.swing.JFrame;

class Platformer {
	
	private JFrame mFrame = null;
	private GameEngine mGameEngine = null;
	
	private Platformer() {
		mFrame = new JFrame("Platformer");
		mGameEngine = new GameEngine();
		
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setResizable(false);
		
		__init__();
	}
	
	private void __init__() {
		mFrame.setLayout(new BorderLayout());
		
		mFrame.add(mGameEngine);
		mFrame.pack();
		
		mFrame.setLocationRelativeTo(null);
		mFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Platformer();
	}
	
}
