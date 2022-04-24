package com;

final class GameState {
	
	private static volatile boolean mRunning = false;
	
	private static volatile boolean mPaused = true;
	private static volatile boolean mGameOver = true;
	private static volatile boolean mDrawing = false;
	private EngineController mEngineController;
	
	private static volatile boolean mLevelMenu = false;
	private static volatile boolean mOptionsMenu = false;
	
	private SoundEngine mSoundEngine = null;
	
	private long mStartTimeInMillis;
	
	private int mCoinsCollected;
	private int mTotalCoins;
	
	private int mCurrentLevel = 2;
	
	GameState(EngineController ec, SoundEngine se) {
		mEngineController = ec;
		mSoundEngine = se;
	}
	
	void startNewGame() {
		stopEverything();
		mEngineController.startNewLevel();
		startEverything();
		mStartTimeInMillis = System.currentTimeMillis();
	}
	
	int getCurrentTime() {
		final long MILLIS_PER_SEC = 1000;
		return (int)((System.currentTimeMillis() - mStartTimeInMillis) / MILLIS_PER_SEC);
	}
	
	int getCurrentLevel() {
		return mCurrentLevel;
	}
	
	void setCurrentLevel(int level) {
		mCurrentLevel = level;
	}
	
	void resetCoins() {
		mCoinsCollected = 0;
	}
	
	int getCoinsCollected() {
		return mCoinsCollected;
	}
	
	int getTotalCoins() {
		return mTotalCoins;
	}
	
	void coinCollected() {
		mCoinsCollected++;
		mTotalCoins++;
	}
	
	void startEverything() {
		mPaused = false;
		mGameOver = false;
		mDrawing = true;
	}
	
	void stopEverything() {
		mPaused = true;
		mGameOver = true;
		mDrawing = false;
	}
	
	boolean isRunning() {
		return mRunning;
	}
	
	void startThread() {
		mRunning = true;
	}
	
	void stopThread() {
		mRunning = false;
	}
	
	boolean isPaused() {
		return mPaused;
	}
	
	void flipPaused() {
		mPaused = !mPaused;
		
		if (mPaused) {
			mSoundEngine.stop();
			mSoundEngine.setAudioFile("background_music.wav");
			mSoundEngine.loop();
		} else {
			mSoundEngine.stop();
			mSoundEngine.setAudioFile("background_music.wav");
			mSoundEngine.loop();
		}
	}
	
	boolean isGameOver() {
		return mGameOver;
	}
	
	void death() {
		mGameOver = true;
	}
	
	boolean isDrawing() {
		return mDrawing;
	}
	
	void startDrawing() {
		mDrawing = true;
	}
	
	void stopDrawing() {
		mDrawing = false;
	}
	
	void flipLevelMenu() {
		mLevelMenu = !mLevelMenu;
	}
	
	boolean getLevelMenu() {
		return mLevelMenu;
	}
	
	void flipOptionsMenu() {
		mOptionsMenu = !mOptionsMenu;
	}
	
	boolean getOptionsMenu() {
		return mOptionsMenu;
	}
}
