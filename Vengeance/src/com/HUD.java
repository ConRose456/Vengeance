package com;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.util.RectangleF;

final class HUD {

	private int mScreenWidth;
	private int mScreenHeight;
	private int mTextFormatting;
	
	private Image mMenuBitmap;
	private Image mTitleBitmap;
	private Image mCurrencyBitmap;
	
	private int mMenuBuffer;
	
	private int mPauseMenuVerticalBuffer;
	private int mPauseMenuHorizontalBuffer;
	private int mPauseMenuBuffer;
	
	private int mDeathScreenHeightBuffer;
	
	private final int mCoinBufferHorizontal = 18;
	private final int mCoinBufferVertical = 33;
	
	private RectangleF mLevelMenuButton = new RectangleF();
	
	private int mMainMenu_LevelMenuButtonSize;
	private int mMainMenu_LevelMenuButton_x;
	private int mMainMenu_LevelMenuButton_y;
	
	private ArrayList<RectangleF> mLevelMenuButtons = new ArrayList<>();
	
	private int mLevelMenuButtonSize;
	private int mLevelMenuButtonVerticalBuffer_initial;
	private int mLevelMenuButtonHorizontalBuffer_initial;
	private int mLevelMenuButtonVerticalBuffer;
	private int mLevelMenuButtonHorizontalBuffer;
	
	private RectangleF mLevelMenuBackButton = new RectangleF();
	
	HUD(Point screenSize) {
		this.mScreenWidth = screenSize.x;
		this.mScreenHeight = screenSize.y;
		
		this.mTextFormatting = screenSize.x / 25;
		
		String path = System.getProperty("user.dir") + "/res/images";
		
		try {
			mMenuBitmap = ImageIO.read(new File(path + "/background.png"));
			//mTitleBitmap  = ImageIO.read(new File(path + "/title_image.png"));
			mCurrencyBitmap = ImageIO.read(new File(path + "/coin_icon.png"));
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		this.mPauseMenuVerticalBuffer = mScreenHeight / 5;
		this.mPauseMenuHorizontalBuffer = mScreenWidth / 15;
		this.mPauseMenuBuffer = mScreenWidth / 10;
		this.mMenuBuffer = mPauseMenuBuffer;
		
		this.mDeathScreenHeightBuffer = mScreenHeight / 20;
		
		this.mMainMenu_LevelMenuButtonSize = (int)(mScreenWidth * 0.05f);
		this.mMainMenu_LevelMenuButton_x = (int)(mScreenWidth * 0.04f);
		this.mMainMenu_LevelMenuButton_y = (int)(mScreenHeight * 0.04f);
		
		this.mLevelMenuButton = new RectangleF(
				mMainMenu_LevelMenuButton_x,
				mScreenHeight - mMainMenu_LevelMenuButton_y - mMainMenu_LevelMenuButtonSize,
				mMainMenu_LevelMenuButtonSize,
				mMainMenu_LevelMenuButtonSize);
		
		this.mLevelMenuBackButton = new RectangleF(
				mMainMenu_LevelMenuButton_x,
				mMainMenu_LevelMenuButton_y + (mMainMenu_LevelMenuButtonSize / 5),
				mMainMenu_LevelMenuButtonSize,
				mMainMenu_LevelMenuButtonSize);
		
		this.mLevelMenuButtonSize = (int)(mScreenWidth * 0.05f);
		this.mLevelMenuButtonVerticalBuffer_initial = (int)(mScreenHeight * .2f);
		this.mLevelMenuButtonHorizontalBuffer_initial = (int)(mScreenWidth * .15f);
		this.mLevelMenuButtonVerticalBuffer = (int)((mScreenHeight - (mLevelMenuButtonVerticalBuffer_initial * 2)) / 3);
		this.mLevelMenuButtonHorizontalBuffer = (int)((mScreenWidth - (mLevelMenuButtonHorizontalBuffer_initial * 2)) / 5);
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				this.mLevelMenuButtons.add(new RectangleF(
					(mLevelMenuButtonHorizontalBuffer_initial + (mLevelMenuButtonHorizontalBuffer * j) + (mLevelMenuButtonSize / 2) * j), 
					mLevelMenuButtonVerticalBuffer_initial + (mLevelMenuButtonVerticalBuffer * i - (mLevelMenuButtonSize / 2) * i),
					mLevelMenuButtonSize,
					mLevelMenuButtonSize));
			}
			
		}
				
	}
	
	void draw(Graphics g, GameState gs) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		if (gs.isGameOver() && !gs.isPaused()) {
			drawDeathScreen(g);
		}
		else if (gs.isGameOver() && gs.isPaused()) {
			drawMainMenu(g, gs);
		}
		else if (!gs.isGameOver()) {
			if (gs.isPaused()) {
				drawPauseMenu(g, g2d);
			}
			
			g.setColor(new Color(255, 255, 255, 255));
			g.setFont(new Font("Arial", Font.PLAIN, 18));
			g.drawImage(mCurrencyBitmap, mMenuBuffer * 9, mCoinBufferHorizontal, null);
			g.drawString("" + gs.getCoinsCollected(), 
					mMenuBuffer * 9 + (mCurrencyBitmap.getWidth(null) + 5), mCoinBufferVertical);
		}
		
		if (gs.getLevelMenu()) {
			drawLevelMenu(g, gs);
		}
	}	
	
	private void drawMainMenu(Graphics g, GameState gs) {
		g.drawImage(mMenuBitmap, 0, 0, mScreenWidth, mScreenHeight, null);
		
		g.setColor(new Color(255, 255, 255, 255));
		
		String titleText = "Vengeance";
		g.setFont(new Font("Arial", Font.PLAIN, 36));
		int titleTextWidth = g.getFontMetrics().stringWidth(titleText);
		
		g.drawString(titleText, (mScreenWidth / 2) - (titleTextWidth / 2), g.getFont().getSize());
		
		String startText = "Press Space to Play!";
		g.setFont(new Font("Arial", Font.PLAIN, 24));
		int startTextWidth = g.getFontMetrics().stringWidth(startText);
		
		g.drawString(startText, (mScreenWidth / 2) - (startTextWidth / 2), 
				mScreenHeight - g.getFont().getSize());
		
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		g.drawImage(mCurrencyBitmap, mMenuBuffer * 9, mCoinBufferHorizontal, null);
		g.drawString("" + gs.getCoinsCollected(), 
				mMenuBuffer * 9 + (mCurrencyBitmap.getWidth(null) + 5), mCoinBufferVertical);
		
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRect((int)mLevelMenuButton.x, (int)mLevelMenuButton.y, (int)mLevelMenuButton.width, (int)mLevelMenuButton.height);
	}
	
	private void drawPauseMenu(Graphics g, Graphics2D g2d) {
		GradientPaint graident = new GradientPaint(50, 50, new Color(0, 0, 0, 255),
				mScreenWidth, mScreenWidth, new Color(255, 255, 255, 10));
		g2d.setPaint(graident);
		g2d.fillRect(0, 0, mScreenWidth, mScreenHeight);				
		
		g.setColor(new Color(255, 255, 255, 255));
		
		String pauseTitleText = "Vengeance";
		g.setFont(new Font("Arial", Font.PLAIN, 36));
		g.drawString(pauseTitleText, mPauseMenuHorizontalBuffer, mPauseMenuVerticalBuffer);
		
		String resumeText = "Resume";
		String optionsText = "Options";
		String exitGameText = "Exit";
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString(resumeText, mPauseMenuHorizontalBuffer, 
				mPauseMenuVerticalBuffer + mPauseMenuBuffer + g.getFont().getSize());
		g.drawString(optionsText, mPauseMenuHorizontalBuffer, 
				(int)(mPauseMenuVerticalBuffer + mPauseMenuBuffer * 1.5f + g.getFont().getSize()));
		g.drawString(exitGameText, mPauseMenuHorizontalBuffer, 
				mPauseMenuVerticalBuffer + mPauseMenuBuffer * 2 + g.getFont().getSize());
	}
	
	private void drawDeathScreen(Graphics g) {
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRect(0, (mScreenHeight / 2) - mDeathScreenHeightBuffer, 
				mScreenWidth, mDeathScreenHeightBuffer * 2);
		
		String deathMessage = "Game Over";
		g.setFont(new Font("Arial", Font.PLAIN, 24));
		int deathTextWidth = g.getFontMetrics().stringWidth(deathMessage) / 2;
		int deathTextHeight = g.getFont().getSize() / 10;
		
		g.setColor(new Color(150, 25, 25, 255));
		g.drawString(deathMessage, mScreenWidth / 2 - deathTextWidth, mScreenHeight / 2 - deathTextHeight);
	}
	
	private void drawLevelMenu(Graphics g, GameState gs) {
		g.drawImage(mMenuBitmap, 0, 0, mScreenWidth, mScreenHeight, null);
		
		g.setColor(new Color(255, 255, 255, 255));
		String titleText = "Levels";
		g.setFont(new Font("Arial", Font.PLAIN, 36));
		int titleTextWidth = g.getFontMetrics().stringWidth(titleText);
		
		g.drawString(titleText, (mScreenWidth / 2) - (titleTextWidth / 2), g.getFont().getSize());
		
		g.setColor(new Color(0 ,0, 0, 100));
		for (RectangleF button : mLevelMenuButtons) {
			g.fillRect((int)button.x, (int)button.y, (int)button.width, (int)button.height);
		}
		
		g.fillRect(
				(int)mLevelMenuBackButton.x, 
				(int)mLevelMenuBackButton.y, 
				(int)mLevelMenuBackButton.width,
				(int)mLevelMenuBackButton.height);
		
		g.setColor(new Color(255, 255, 255, 255));
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		g.drawImage(mCurrencyBitmap, mMenuBuffer * 9, mCoinBufferHorizontal, null);
		g.drawString("" + gs.getCoinsCollected(), 
				mMenuBuffer * 9 + (mCurrencyBitmap.getWidth(null) + 5), mCoinBufferVertical);
	}
	
	public RectangleF getMainMenu_LevelButton() {
		return mLevelMenuButton;
	}
	
	public RectangleF getLevelMenuBackButton() {
		return mLevelMenuBackButton;
	}
	
	public ArrayList<RectangleF> getLevelButtons() {
		return mLevelMenuButtons;
	}
}
