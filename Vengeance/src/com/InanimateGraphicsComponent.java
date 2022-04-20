package com;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.GOSpecs.GameObjectSpec;
import com.util.PointF;

class InanimateGraphicsComponent implements GraphicsComponent {

	private String mBitmapName;
	
	@Override
	public void init(GameObjectSpec spec, PointF objectSize, int pixelsPerMetre) {
		mBitmapName = spec.getBitmapName();
		BitmapStore.addBitmap(mBitmapName, objectSize, pixelsPerMetre, false);
	}

	@Override
	public void draw(Graphics g, Transform t, Camera cam) {
		Image image = BitmapStore.getBitmap(mBitmapName);
		Rectangle screenCoordinates = cam.worldToScreen(
				t.getLocation().x,
				t.getLocation().y, 
				t.getSize().x,
				t.getSize().y
		);
		g.drawImage(image, screenCoordinates.x, screenCoordinates.y, 
				screenCoordinates.width, screenCoordinates.height, null);
	}

}
