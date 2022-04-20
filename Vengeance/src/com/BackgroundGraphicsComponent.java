package com;

import java.awt.Graphics;
import java.awt.Image;

import com.GOSpecs.GameObjectSpec;
import com.util.PointF;

class BackgroundGraphicsComponent implements GraphicsComponent {

	private String mBitmapName;
	
	@Override
	public void init(GameObjectSpec spec, PointF objectSize, int pixelsPerMetre) {
		mBitmapName = spec.getBitmapName();
		BitmapStore.addBitmap(mBitmapName, objectSize, pixelsPerMetre, false);
	}

	@Override
	public void draw(Graphics g, Transform t, Camera cam) {
		BackgroundTransform bt = (BackgroundTransform)t;
		Image image = BitmapStore.getBitmap(mBitmapName);
		
		int width = 800;
		int height = 500;
		PointF location = new PointF(0, 0);
		
		g.drawImage(image, 0, 0, width, height, null);
		
	}

}
