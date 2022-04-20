package com;

import com.GOSpecs.GameObjectSpec;
import com.util.PointF;

class GameObjectFactory {

	private GameEngine mGameEngineReference;
	private int mPixelsPerMetre;
	
	GameObjectFactory(GameEngine ge, int pixelsPerMetre) {
		this.mGameEngineReference = ge;
		this.mPixelsPerMetre = pixelsPerMetre;
	}
	
	GameObject create(GameObjectSpec spec, PointF location) {
		GameObject object = new GameObject();
		
		int numComponents = spec.getComponents().length;
		object.setTag(spec.getTag());
		
		switch (object.getTag()) {
		case "Background":
			object.setTransform(new BackgroundTransform(
					spec.getSpeed(),
					spec.getSize().x,
					spec.getSize().y,
					location));
			break;
		case "Player":
			object.setTransform(new PlayerTransform(
					spec.getSpeed(), 
					spec.getSize().x, 
					spec.getSize().y,
					location));
			break;
		default:
			object.setTransform(new Transform(
					spec.getSpeed(),
					spec.getSize().x,
					spec.getSize().y,
					location));
			break;
		}
		
		for (int i = 0; i < numComponents; i++) {
			switch (spec.getComponents()[i]) {
			case "InanimateGraphicsComponent":
				object.setGraphicsComponent(new InanimateGraphicsComponent(), 
						spec, spec.getSize(), mPixelsPerMetre);
				break;
			case "InanimateUpdateComponent":
				object.setMovement(new InanimateUpdateComponent());
				break;
			case "PlayerInputComponent":
				object.setPlayerInputTransform(new PlayerInputComponent(mGameEngineReference));
				break;
			case "BackgroundGraphicsComponent":
				object.setGraphicsComponent(new BackgroundGraphicsComponent(), 
						spec, spec.getSize(), mPixelsPerMetre);
				break;
			case "BackgroundUpdateComponent":
				object.setMovement(new BackgroundUpdateComponent());
				break;
			case "PlayerUpdateComponent":
				object.setMovement(new PlayerUpdateComponent());
				break;
			case "AnimatedGraphicsComponent":
				object.setGraphicsComponent(new AnimatedGraphicsComponent(), 
						spec, spec.getSize(), mPixelsPerMetre);
				break;
			}
		}
		
		return object;
	}
	
}
