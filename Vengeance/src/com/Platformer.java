package com;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

class Platformer {
	
	private JFrame mFrame = null;
	private GameEngine mGameEngine = null;
	
	private Platformer() throws IOException {
		mFrame = new JFrame("Vengeance");
		mGameEngine = new GameEngine(mFrame);
		
		String path = System.getProperty("user.dir") + "/res/images/UI/FrameIcon.png";
		Image playerIcon = ImageIO.read(new File(path));
		mFrame.setIconImage(playerIcon);
		
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setResizable(false);
		
		__init__();
	}
	
	private Platformer(IOException e) {
		e.printStackTrace();
		
		mFrame = new JFrame("Vengeance");
		mGameEngine = new GameEngine(mFrame);

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
		try {
			new Platformer();
		} catch (IOException e) {
			new Platformer(e);
		}
	}
	
}
