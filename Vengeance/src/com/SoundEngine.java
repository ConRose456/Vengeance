package com;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class SoundEngine {

	private static SoundEngine mOurInstance;
	
	static SoundEngine getInstance() {
		if (mOurInstance == null) {
			mOurInstance = new SoundEngine();
		}
		return mOurInstance;
	}
	
	private Clip clip = null;
	private AudioInputStream ais = null;
	
	private SoundEngine() {
	}
	
	void setAudioFile(String fileName) {
		try {
			ais = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/res/audio/" + fileName));
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	void playEffect(String fileName) {
		setAudioFile(fileName);
		play();
	}
	
	private void play() {
		clip.start();
	}
	
	void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	void stop() {
		clip.stop();
	}
}
