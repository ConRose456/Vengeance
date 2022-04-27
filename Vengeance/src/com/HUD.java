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
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.util.RectangleF;

final class HUD {

	private int mScreenWidth;
	private int mScreenHeight;
	
	// Start Screen Buttons
	private Image mMenuBitmap;
	private Image mTitleBitmap;
	private Image mVersionBitmap;
	private Image mCurrencyBitmap;
	private Image mStartButtonBitmap;
	private Image mOptionsButtonBitmap;
	private Image mExitButtonBitmap;
	private Image mCustomizationButtonBitmap;
	private Image mLevelMenuButtonBitmap;
	
	private RectangleF mLevelMenuButton;
	private RectangleF mCustomizationButton;
	private HashMap<String, RectangleF> mStartMenuButtonMap = new HashMap<>();
	private RectangleF mStartMenu_StartButton;
	private RectangleF mStartMenu_OptionsButton;
	private RectangleF mStartMenu_ExitButton;
	
	private int mStartMenuButtonWidth;
	private int mStartMenuButtonHeight;
	private int mStartMenuButtonVerticalButtonBuffer_initial;
	private int mStartMenuButtonHorizontalButtonBuffer_initial;
	private int mStartMenuButtonVerticalButtonBuffer;
	
	private int mMainMenu_LevelMenuButtonSize;
	private int mMainMenu_LevelMenuButton_x;
	private int mMainMenu_LevelMenuButton_y;
	
	private int mVersionTextWidth;
	private int mVersionTextHeight;
	private int mVersionTextX;
	private int mVersionTextY;
	

	// Pause Screen Buttons
	private Image mPauseButtonBitmap;
	private Image mResumeButtonBitmap;
	
	private HashMap<String, RectangleF> mPauseMenuButtonMap = new HashMap<>();
	private RectangleF mPauseMenu_ResumeButton;
	private RectangleF mPauseMenu_OptionsButton;
	private RectangleF mPauseMenu_ExitButton;
	
	
	// Level Menu Buttons
	private Image mLevelMenuBackgroundBitmap;
	private Image mLockedLevelBitmap;
	private ArrayList<Image> mLevelBitmapButtons = new ArrayList<>();
	
	private ArrayList<RectangleF> mLevelMenuButtons = new ArrayList<>();
	
	private int mLevelMenuButtonSize;
	private int mLevelMenuButtonVerticalBuffer_initial;
	private int mLevelMenuButtonHorizontalBuffer_initial;
	private int mLevelMenuButtonVerticalBuffer;
	private int mLevelMenuButtonHorizontalBuffer;
	
	
	// Back Buttons Stuff
	private Image mBackButtonBitmap;
	
	private RectangleF mLevelMenuBackButton;
	private RectangleF mOptionsMenuBackButton;
	private RectangleF mCustomMenuBackButton;
	
	
	// Other Variables
	private RectangleF mInGamePauseButton;
	
	private int mMenuBuffer;
	private int mDeathScreenHeightBuffer;
	private final int mCoinBufferHorizontal = 18;
	private final int mCoinBufferVertical = 33;

	HUD(Point screenSize) {
		this.mScreenWidth = screenSize.x;
		this.mScreenHeight = screenSize.y;
		
		this.mMenuBuffer = mScreenWidth / 10;
		
		String path = System.getProperty("user.dir") + "/res/images";
		
		try {
			// Title Bitmap
			mTitleBitmap = ImageIO.read(new File(path + "/UI/TitleBitmap.png"));
			mVersionBitmap = ImageIO.read(new File(path + "/UI/VersionText.png"));
			
			// UI Bitmaps
			mMenuBitmap = ImageIO.read(new File(path + "/backgroundSky.png"));
			mCurrencyBitmap = ImageIO.read(new File(path + "/coin_icon.png"));
			mBackButtonBitmap = ImageIO.read(new File(path + "/UI/BackButton.png"));
			mPauseButtonBitmap = ImageIO.read(new File(path + "/UI/PauseButton.png"));
			mLevelMenuButtonBitmap = ImageIO.read(new File(path + "/UI/LevelMenuButton.png"));
			mLevelMenuBackgroundBitmap = ImageIO.read(new File(path + "/UI/LevelMenuBackground.png"));
			mLockedLevelBitmap = ImageIO.read(new File(path + "/UI/LockedLevel.png"));
			
			// Start Screen Bitmaps
			mStartButtonBitmap = ImageIO.read(new File(path + "/UI/StartButton.png"));
			mOptionsButtonBitmap = ImageIO.read(new File(path + "/UI/OptionsButton.png"));
			mExitButtonBitmap = ImageIO.read(new File(path + "/UI/ExitButton.png"));
			mResumeButtonBitmap = ImageIO.read(new File(path + "/UI/ResumeButton.png"));
			mCustomizationButtonBitmap = ImageIO.read(new File(path + "/UI/CustomizationButton.png"));
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		this.mDeathScreenHeightBuffer = mScreenHeight / 20;
		
		this.mMainMenu_LevelMenuButtonSize = (int)(mScreenWidth * 0.05f);
		this.mMainMenu_LevelMenuButton_x = (int)(mScreenWidth * 0.04f);
		this.mMainMenu_LevelMenuButton_y = (int)(mScreenHeight * 0.04f);
		
		this.mLevelMenuButton = new RectangleF(
				mMainMenu_LevelMenuButton_x,
				mScreenHeight - mMainMenu_LevelMenuButton_y - mMainMenu_LevelMenuButtonSize,
				mMainMenu_LevelMenuButtonSize,
				mMainMenu_LevelMenuButtonSize);
		
		this.mCustomizationButton = new RectangleF(mScreenWidth - mMainMenu_LevelMenuButton_x - mMainMenu_LevelMenuButtonSize,
				mScreenHeight - mMainMenu_LevelMenuButton_y - mMainMenu_LevelMenuButtonSize,
				mMainMenu_LevelMenuButtonSize,
				mMainMenu_LevelMenuButtonSize);
		
		this.mLevelMenuBackButton = new RectangleF(
				mMainMenu_LevelMenuButton_x,
				mMainMenu_LevelMenuButton_y + (mMainMenu_LevelMenuButtonSize / 5),
				mMainMenu_LevelMenuButtonSize,
				mMainMenu_LevelMenuButtonSize);
		this.mOptionsMenuBackButton = new RectangleF(
				mMainMenu_LevelMenuButton_x,
				mMainMenu_LevelMenuButton_y + (mMainMenu_LevelMenuButtonSize / 5),
				mMainMenu_LevelMenuButtonSize,
				mMainMenu_LevelMenuButtonSize);
		this.mCustomMenuBackButton = new RectangleF(
				mMainMenu_LevelMenuButton_x,
				mMainMenu_LevelMenuButton_y + (mMainMenu_LevelMenuButtonSize / 5),
				mMainMenu_LevelMenuButtonSize,
				mMainMenu_LevelMenuButtonSize);
	
		this.mInGamePauseButton = new RectangleF(
				mMainMenu_LevelMenuButton_x,
				mMainMenu_LevelMenuButton_y + (mMainMenu_LevelMenuButtonSize / 5),
				mMainMenu_LevelMenuButtonSize,
				mMainMenu_LevelMenuButtonSize);
		
		this.mLevelMenuButtonSize = (int)(mScreenWidth * 0.05f);
		this.mLevelMenuButtonVerticalBuffer_initial = (int)(mScreenHeight * .25f);
		this.mLevelMenuButtonHorizontalBuffer_initial = (int)(mScreenWidth * .15f);
		this.mLevelMenuButtonVerticalBuffer = (int)((mScreenHeight - (mLevelMenuButtonVerticalBuffer_initial * 2)) / 3);
		this.mLevelMenuButtonHorizontalBuffer = (int)((mScreenWidth - (mLevelMenuButtonHorizontalBuffer_initial * 2)) / 5);
		
		try {
			mLevelBitmapButtons.add(ImageIO.read(new File(path + "/UI/LevelOneButton.png")));
			mLevelBitmapButtons.add(ImageIO.read(new File(path + "/UI/LevelTwoButton.png")));
			mLevelBitmapButtons.add(ImageIO.read(new File(path + "/UI/LevelThreeButton.png")));
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				this.mLevelMenuButtons.add(new RectangleF(
					(mLevelMenuButtonHorizontalBuffer_initial + (mLevelMenuButtonHorizontalBuffer * j) + (mLevelMenuButtonSize / 2) * j), 
					mLevelMenuButtonVerticalBuffer_initial + (mLevelMenuButtonVerticalBuffer * i) + (mLevelMenuButtonSize / 2),
					mLevelMenuButtonSize,
					mLevelMenuButtonSize));
			}
		}
		
		this.mVersionTextWidth = mVersionBitmap.getWidth(null) * 2;
		this.mVersionTextHeight = mVersionBitmap.getHeight(null) * 2;
		this.mVersionTextX = (int)((mScreenWidth / 2) - (mVersionTextWidth / 2));
		this.mVersionTextY = (int)(mLevelMenuButton.y + mLevelMenuButton.height - mVersionTextHeight);
		
		this.mStartMenuButtonWidth = (int)(mScreenWidth * 0.1125f);
		this.mStartMenuButtonHeight = (int)(mScreenHeight * 0.08f);
		this.mStartMenuButtonVerticalButtonBuffer_initial = (int)(mScreenHeight * 0.36f);
		this.mStartMenuButtonHorizontalButtonBuffer_initial = (int)((mScreenWidth * 0.5f) - (mStartMenuButtonWidth / 2));
		this.mStartMenuButtonVerticalButtonBuffer = (int)(mScreenHeight * 0.10f);
		
		this.mStartMenu_StartButton = new RectangleF(
				mStartMenuButtonHorizontalButtonBuffer_initial,
				mStartMenuButtonVerticalButtonBuffer_initial,
				mStartMenuButtonWidth,
				mStartMenuButtonHeight);
		this.mStartMenu_OptionsButton = new RectangleF(
				mStartMenuButtonHorizontalButtonBuffer_initial,
				mStartMenuButtonVerticalButtonBuffer_initial + mStartMenuButtonVerticalButtonBuffer,
				mStartMenuButtonWidth,
				mStartMenuButtonHeight);
		this.mStartMenu_ExitButton = new RectangleF(
				mStartMenuButtonHorizontalButtonBuffer_initial,
				mStartMenuButtonVerticalButtonBuffer_initial + (mStartMenuButtonVerticalButtonBuffer * 2),
				mStartMenuButtonWidth,
				mStartMenuButtonHeight);
		
		this.mPauseMenu_ResumeButton = mStartMenu_StartButton;
		this.mPauseMenu_OptionsButton = mStartMenu_OptionsButton;
		this.mPauseMenu_ExitButton = mStartMenu_ExitButton;
		
		this.mStartMenuButtonMap.put("Start", mStartMenu_StartButton);
		this.mStartMenuButtonMap.put("Options", mStartMenu_OptionsButton);
		this.mStartMenuButtonMap.put("Exit", mStartMenu_ExitButton);
		
		this.mPauseMenuButtonMap.put("Resume", mPauseMenu_ResumeButton);
		this.mPauseMenuButtonMap.put("Options", mPauseMenu_OptionsButton);
		this.mPauseMenuButtonMap.put("Exit", mPauseMenu_ExitButton);
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
			
			if (!gs.isPaused()) {
				g.drawImage(mPauseButtonBitmap, 
					(int)mInGamePauseButton.x, 
					(int)mInGamePauseButton.y, 
					(int)mInGamePauseButton.width, 
					(int)mInGamePauseButton.height, null);
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
		if (gs.getOptionsMenu()) {
			drawOptionsMenu(g, gs);
		}
		if (gs.getCustomMenu()) {
			drawCustomMenu(g, gs);
		}
	}	
	
	private void drawMainMenu(Graphics g, GameState gs) {
		
		g.drawImage(mMenuBitmap, 0, 0, mScreenWidth, mScreenHeight, null);
		g.drawImage(mTitleBitmap, 
				(int)((mScreenWidth * 0.5f) - ((mScreenWidth * 0.675f) / 2)), 
				(int)(mScreenHeight * 0.12f), 
				(int)(mScreenWidth * 0.675f), 
				(int)(mScreenHeight * 0.12f), null);
		g.drawImage(mVersionBitmap, 
				mVersionTextX, 
				mVersionTextY, 
				mVersionTextWidth, 
				mVersionTextHeight, null);
		
		g.setColor(new Color(255, 255, 255, 255));
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		g.drawImage(mCurrencyBitmap, mMenuBuffer * 9, mCoinBufferHorizontal, null);
		g.drawString("" + gs.getTotalCoins(), 
				mMenuBuffer * 9 + (mCurrencyBitmap.getWidth(null) + 5), mCoinBufferVertical);
		
		g.drawImage(mStartButtonBitmap, 
				(int)mStartMenu_StartButton.x, 
				(int)mStartMenu_StartButton.y, 
				(int)mStartMenu_StartButton.width, 
				(int)mStartMenu_StartButton.height, null);
		g.drawImage(mOptionsButtonBitmap, 
				(int)mStartMenu_OptionsButton.x, 
				(int)mStartMenu_OptionsButton.y, 
				(int)mStartMenu_OptionsButton.width, 
				(int)mStartMenu_OptionsButton.height, null);
		g.drawImage(mExitButtonBitmap, 
				(int)mStartMenu_ExitButton.x, 
				(int)mStartMenu_ExitButton.y, 
				(int)mStartMenu_ExitButton.width, 
				(int)mStartMenu_ExitButton.height, null);
		
		g.drawImage(mLevelMenuButtonBitmap,
				(int)mLevelMenuButton.x, 
				(int)mLevelMenuButton.y, 
				(int)mLevelMenuButton.width, 
				(int)mLevelMenuButton.height, null);
		g.drawImage(mCustomizationButtonBitmap, 
				(int)mCustomizationButton.x, 
				(int)mCustomizationButton.y, 
				(int)mCustomizationButton.width, 
				(int)mCustomizationButton.height, null);
	}
	
	private void drawPauseMenu(Graphics g, Graphics2D g2d) {
		GradientPaint graident = new GradientPaint(50, 50, new Color(0, 0, 0, 255),
				mScreenWidth, mScreenWidth, new Color(0, 0, 0, 100));
		g2d.setPaint(graident);
		g2d.fillRect(0, 0, mScreenWidth, mScreenHeight);
		
		g.drawImage(mTitleBitmap, 
				(int)((mScreenWidth * 0.5f) - ((mScreenWidth * 0.675f) / 2)), 
				(int)(mScreenHeight * 0.12f), 
				(int)(mScreenWidth * 0.675f), 
				(int)(mScreenHeight * 0.12f), null);
		
		g.drawImage(mResumeButtonBitmap, 
				(int)mPauseMenu_ResumeButton.x, 
				(int)mPauseMenu_ResumeButton.y, 
				(int)mPauseMenu_ResumeButton.width, 
				(int)mPauseMenu_ResumeButton.height, null);
		g.drawImage(mOptionsButtonBitmap, 
				(int)mPauseMenu_OptionsButton.x, 
				(int)mPauseMenu_OptionsButton.y, 
				(int)mPauseMenu_OptionsButton.width, 
				(int)mPauseMenu_OptionsButton.height, null);
		g.drawImage(mExitButtonBitmap, 
				(int)mPauseMenu_ExitButton.x, 
				(int)mPauseMenu_ExitButton.y, 
				(int)mPauseMenu_ExitButton.width, 
				(int)mPauseMenu_ExitButton.height, null);
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
		g.drawImage(mLevelMenuBackgroundBitmap, 
				(int)(mScreenWidth * 0.094f), (int)(mScreenHeight * 0.22f), 
				(int)(mScreenWidth * 0.81f), (int)(mScreenHeight * 0.72f), null);
		
		g.setColor(new Color(255, 255, 255, 255));
		String titleText = "Levels";
		g.setFont(new Font("Arial", Font.PLAIN, 36));
		int titleTextWidth = g.getFontMetrics().stringWidth(titleText);
		
		g.drawString(titleText, (mScreenWidth / 2) - (titleTextWidth / 2), g.getFont().getSize());
		
		g.setColor(new Color(0 ,0, 0, 100));
		int i = 0;
		for (RectangleF button : mLevelMenuButtons) {
			
			if (i < gs.getHighestReachedLevel()) {
				g.drawImage(mLevelBitmapButtons.get(i), (int)button.x, (int)button.y, (int)button.width, (int)button.height, null);
			} else {
				g.drawImage(mLockedLevelBitmap, (int)button.x, (int)button.y, (int)button.width, (int)button.height, null);
			}
			i++;
		}
	
		g.drawImage(mBackButtonBitmap, 
				(int)mLevelMenuBackButton.x, 
				(int)mLevelMenuBackButton.y, 
				(int)mLevelMenuBackButton.width,
				(int)mLevelMenuBackButton.height, null);
		
		g.setColor(new Color(255, 255, 255, 255));
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		g.drawImage(mCurrencyBitmap, mMenuBuffer * 9, mCoinBufferHorizontal, null);
		g.drawString("" + gs.getTotalCoins(), 
				mMenuBuffer * 9 + (mCurrencyBitmap.getWidth(null) + 5), mCoinBufferVertical);
	}
	
	private void drawOptionsMenu(Graphics g, GameState gs) {
		g.drawImage(mMenuBitmap, 0, 0, mScreenWidth, mScreenHeight, null);
		g.drawImage(mLevelMenuBackgroundBitmap, 
				(int)(mScreenWidth * 0.094f), (int)(mScreenHeight * 0.22f), 
				(int)(mScreenWidth * 0.81f), (int)(mScreenHeight * 0.72f), null);
		
		g.setColor(new Color(255, 255, 255, 255));
		String titleText = "Options";
		g.setFont(new Font("Arial", Font.PLAIN, 36));
		int titleTextWidth = g.getFontMetrics().stringWidth(titleText);
		
		g.drawString(titleText, (mScreenWidth / 2) - (titleTextWidth / 2), g.getFont().getSize());
		
		g.drawImage(mBackButtonBitmap, 
				(int)mLevelMenuBackButton.x, 
				(int)mLevelMenuBackButton.y, 
				(int)mLevelMenuBackButton.width,
				(int)mLevelMenuBackButton.height, null);
		
		g.setColor(new Color(255, 255, 255, 255));
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		g.drawImage(mCurrencyBitmap, mMenuBuffer * 9, mCoinBufferHorizontal, null);
		g.drawString("" + gs.getTotalCoins(), 
				mMenuBuffer * 9 + (mCurrencyBitmap.getWidth(null) + 5), mCoinBufferVertical);
	}
	
	public void drawCustomMenu(Graphics g, GameState gs) {
		g.drawImage(mMenuBitmap, 0, 0, mScreenWidth, mScreenHeight, null);
		g.drawImage(mLevelMenuBackgroundBitmap, 
				(int)(mScreenWidth * 0.094f), (int)(mScreenHeight * 0.22f), 
				(int)(mScreenWidth * 0.81f), (int)(mScreenHeight * 0.72f), null);
		
		g.setColor(new Color(255, 255, 255, 255));
		String titleText = "Character Customization";
		g.setFont(new Font("Arial", Font.PLAIN, 36));
		int titleTextWidth = g.getFontMetrics().stringWidth(titleText);
		
		g.drawString(titleText, (mScreenWidth / 2) - (titleTextWidth / 2), g.getFont().getSize());
		
		g.drawImage(mBackButtonBitmap, 
				(int)mLevelMenuBackButton.x, 
				(int)mLevelMenuBackButton.y, 
				(int)mLevelMenuBackButton.width,
				(int)mLevelMenuBackButton.height, null);
		
		g.setColor(new Color(255, 255, 255, 255));
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		g.drawImage(mCurrencyBitmap, mMenuBuffer * 9, mCoinBufferHorizontal, null);
		g.drawString("" + gs.getTotalCoins(), 
				mMenuBuffer * 9 + (mCurrencyBitmap.getWidth(null) + 5), mCoinBufferVertical);
	}
	
	public HashMap<String, RectangleF> getStartMenuButtons() {
		return mStartMenuButtonMap;
	}
	
	public HashMap<String, RectangleF> getPauseMenuButtons() {
		return mPauseMenuButtonMap;
	}
	
	public RectangleF getMainMenu_LevelButton() {
		return mLevelMenuButton;
	}
	
	public RectangleF getMainMenu_CustomizationButton() {
		return mCustomizationButton;
	}
	
	public RectangleF getLevelMenuBackButton() {
		return mLevelMenuBackButton;
	}
	
	public RectangleF getOptionsMenuBackButton() {
		return mOptionsMenuBackButton;
	}
	
	public RectangleF getCustomMenuBackButton() {
		return mCustomMenuBackButton;
	}
	
	public RectangleF getInGamePauseButton() {
		return mInGamePauseButton;
	}
	
	public ArrayList<RectangleF> getLevelButtons() {
		return mLevelMenuButtons;
	}
}
