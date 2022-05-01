package com;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.util.JSONReader;
import com.util.LevelReader;

class GameEngine extends JPanel implements Runnable, GameEngineBroadcaster, EngineController,
												KeyListener, MouseListener {

	/**
	 * Author: Connor Dermot Rosewell
	 */
	private static final long serialVersionUID = -1108675188278649941L;
	
	private final int WIDTH = 800;
	private final int HEIGHT = 500;
	
	private GameState mGameState = null;
	private Renderer mRenderer = null;
	private PhysicsEngine mPhysicsEngine = null;
	
	private HUD mHud = null;
	private LevelManager mLevelManager = null;
	private UIController mUIController = null;
	
	private CopyOnWriteArrayList<InputObserver> mInputObservers = new CopyOnWriteArrayList<InputObserver>();
	
	private Thread mThread = null;
	private int mFps = 0;
	
	private BitmapStore bs = BitmapStore.getInstance();
	private SoundEngine se = SoundEngine.getInstance();
	
	GameEngine(JFrame frame) {
		
		JSONReader jsonReader = JSONReader.getInstance();
		LevelReader levelReader = LevelReader.getInstance();
		
		this.mGameState = new GameState(this, se);
		this.mRenderer = new Renderer(new Point(WIDTH, HEIGHT));
		this.mPhysicsEngine = new PhysicsEngine(se);
		
		this.mHud = new HUD(new Point(WIDTH, HEIGHT));
		this.mLevelManager = new LevelManager(this, mRenderer.getPixelsPerMetre());
		this.mUIController = new UIController(this, mHud, new Point(WIDTH, HEIGHT));
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);
		
		SoundEngine.setAudioFile("background_music.wav");
		SoundEngine.loop();
		
		startThread();
	}
	
	@Override
	public void startNewLevel() {
		
		BitmapStore.clearStore();
		SoundEngine.stop();
		SoundEngine.setAudioFile("background_music.wav");
		SoundEngine.loop();
		
		mInputObservers.clear();
		mUIController.addObserver(this);
		mLevelManager.setCurrentLevel(mGameState.getCurrentLevel());
		mLevelManager.buildGameObjects(mGameState);
	}
	
	@Override
	public void addObserver(InputObserver input) {
		mInputObservers.add(input);
	}
	
	private void startThread() {
		mGameState.startThread();
		mThread = new Thread(this, "Game Loop");
		mThread.start();
	}
	
	@SuppressWarnings("unused")
	private void stopThread() {
		mGameState.stopEverything();
		mGameState.stopThread();
		try {
			mThread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (mGameState.isRunning()) {
			
			long startTime = System.currentTimeMillis();
			
			if (!mGameState.isPaused()) {
				mPhysicsEngine.update(mLevelManager.getGameObjects(), mFps, mGameState);
			}
			repaint();
			
			long endTime = System.currentTimeMillis() - startTime;
			
			if (endTime < 16.67f) {
				try {
					Thread.sleep((long)(41.6f - endTime));
					endTime = System.currentTimeMillis() - startTime;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if (endTime > 0) {
				final int MILLIS_PER_SECOND = 1000;
				mFps = (int)(MILLIS_PER_SECOND / endTime);
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		mRenderer.draw(mLevelManager.getGameObjects(), mHud, g, mGameState);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (InputObserver input : mInputObservers) {
			input.handleKeyPressedInput(key, mGameState);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (InputObserver input : mInputObservers) {
			input.handleKeyReleasedInput(key, mGameState);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point location = new Point(e.getX(), e.getY());
		for (InputObserver input : mInputObservers) {
			input.handleMouseInput(location, mGameState);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
