package com.LevelBuilder;

import java.awt.BorderLayout;

import javax.swing.JFrame;

class LevelBuilder {

	private JFrame frame = null;
	private LevelBuilderPanel panel = null;
	
	private LevelBuilder() {
		frame = new JFrame("Platformer Level Builder");
		panel = new LevelBuilderPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		__init__();
	}
	
	private void __init__() {
		frame.setLayout(new BorderLayout());
		
		frame.add(panel);
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String args[]) {
		new LevelBuilder();
	}
	
}
