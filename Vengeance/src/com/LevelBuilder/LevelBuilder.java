package com.LevelBuilder;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

class LevelBuilder {

	private JFrame mFrame = null;
	private LevelBuilderPanel mPanel = null;
	
	private LevelBuilder() throws IOException {
		mFrame = new JFrame("Vengeance");
		mPanel = new LevelBuilderPanel();
		
		String path = System.getProperty("user.dir") + "/res/images/UI/PlayerIcon.png";
		Image playerIcon = ImageIO.read(new File(path));
		mFrame.setIconImage(playerIcon);
		
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setResizable(false);
		
		__init__();
	}
	
	private LevelBuilder(IOException e) {
		e.printStackTrace();
		
		mFrame = new JFrame("Vengeance");
		mPanel = new LevelBuilderPanel();

		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setResizable(false);
		
		__init__();
	}
	
	private void __init__() {
		mFrame.setLayout(new BorderLayout());
		
		mFrame.add(mPanel);
		mFrame.pack();
		
		mFrame.setLocationRelativeTo(null);
		mFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			new LevelBuilder();
		} catch (IOException e) {
			new LevelBuilder(e);
		}
	}
}
