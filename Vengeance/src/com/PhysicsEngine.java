package com;

import java.util.ArrayList;

import com.util.PointF;
import com.util.RectangleF;

class PhysicsEngine {
	
	private SoundEngine mSoundEngine = null;
	
	PhysicsEngine(SoundEngine se) {
		mSoundEngine = se;
	}
	
	void update(ArrayList<GameObject> objects, int fps, GameState gs) {
		for (GameObject object : objects) {
			object.update(fps, objects.get(LevelManager.PLAYER_INDEX).getTransform());
		}
		detectCollision(gs, objects);
	}
	
	private void detectCollision(GameState gs, ArrayList<GameObject> objects) {
		
		boolean collisionOccured = false;
		
		Transform playersTransform = objects.get(LevelManager.PLAYER_INDEX).getTransform();
		
		PlayerTransform playersPlayerTransform = (PlayerTransform) playersTransform;
		ArrayList<RectangleF> playerColliders = playersPlayerTransform.getColliders();
		PointF playersLocation = playersTransform.getLocation();
		
		for (GameObject go : objects) {
			if (go.checkActive()) {
				if (playersTransform.getCollider().overlaps(go.getTransform().getCollider())) {
					collisionOccured = true;
					
					Transform testedTransform = go.getTransform();
					PointF testedLocation = testedTransform.getLocation();
					
					if (objects.indexOf(go) != LevelManager.PLAYER_INDEX) {
						for (int i = 0; i < playerColliders.size(); i++) {
							
							if (testedTransform.getCollider().overlaps(playerColliders.get(i))) {
								switch (go.getTag() + " with " + i) {
								case "Coin with 2": // Collectible with Right
									go.setInactive();
									gs.coinCollected();
									
									mSoundEngine.setAudioFile("collect_coin.wav");
									mSoundEngine.stop();
									mSoundEngine.playEffect("collect_coin.wav");
									break;
								case "Coin with 3": // Collectible with Left
									go.setInactive();
									gs.coinCollected();
									
									mSoundEngine.setAudioFile("collect_coin.wav");
									mSoundEngine.stop();
									mSoundEngine.playEffect("collect_coin.wav");
									break;
								case "Inert tile with 1": // Head
									playersLocation.y = testedLocation.y + testedTransform.getSize().y;
									playersPlayerTransform.triggerBumpedHead();
									break;
								case "Inert tile with 2": // Right
									playersTransform.stopMovingRight();
									playersLocation.x = testedTransform.getLocation().x
											- playersTransform.getSize().x;
									break;
								case "Inert tile with 3":// Left
									playersTransform.stopMovingLeft();
									playersLocation.x = testedTransform.getLocation().x
											+ playersTransform.getSize().x;
									break;
								case "Inert tile with 0": // tile with feet
									playersPlayerTransform.grounded();
									playersLocation.y = (testedTransform.getLocation().y) 
											- (playersTransform.getSize().y);
									break;
								case "level completed with 2":
									gs.levelCompleted();
									break;
								case "level completed with 3":
									gs.levelCompleted();
									break;
								case "death with 0":
									gs.death();
									break;
								}
							}
						}
					}
				}
			}
		}
	}
}
