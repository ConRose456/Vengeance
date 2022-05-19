package com;

final class GameState {
	
	private static volatile boolean mRunning = false;
	
	private static volatile boolean mPaused = true;
	private static volatile boolean mGameOver = true;
	private static volatile boolean mDrawing = false;
	private EngineController mEngineController;
	
	private static volatile boolean mLevelCompleted = false;
	private static volatile boolean mDead = false;
	
	private static volatile boolean mLevelMenu = false;
	private static volatile boolean mOptionsMenu = false;
	private static volatile boolean mCustomMenu = false;
	
	private SoundEngine mSoundEngine = null;
	
	private long mStartTimeInMillis;
	
	private int mCoinsCollected;
	private int mTotalCoins;
	
	private int mCurrentLevel = 1;
	private int mHighestReachedLevel = 1;
	
	GameState(EngineController ec, SoundEngine se) {
		mEngineController = ec;
		mSoundEngine = se;
	}
	
	void startNewGame() {
		mLevelCompleted = false;
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
	
	int getHighestReachedLevel() {
		return mHighestReachedLevel;
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
	
	void goHome() {
		mLevelCompleted = false;
		mGameOver = true;
	}
	
	void death() {
		mDead = true;
		mPaused = true;
	}
	
	void levelCompleted() {
		if (mCurrentLevel == mHighestReachedLevel) {
			mHighestReachedLevel++;
		}
		mLevelCompleted = true;
		mPaused = true;
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
	
	void  flipCustomMenu() {
		mCustomMenu = !mCustomMenu;
	}
	
	boolean getCustomMenu() {
		return mCustomMenu;
	}
	
	boolean getLevelCompleted() {
		return mLevelCompleted;
	}
	
	boolean isDead() {
		return mDead;
	}
}
