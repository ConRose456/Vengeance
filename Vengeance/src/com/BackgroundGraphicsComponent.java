package com;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.GOSpecs.GameObjectSpec;
import com.util.PointF;

class BackgroundGraphicsComponent implements GraphicsComponent {

	private String mBitmapName;
	
	@Override
	public void init(GameObjectSpec spec, PointF objectSize, int pixelsPerMetre) {
		mBitmapName = spec.getBitmapName();
		BitmapStore.addBitmap(mBitmapName, objectSize, pixelsPerMetre, true);
	}

	@Override
	public void draw(Graphics g, Transform t, Camera cam) {
		BackgroundTransform bt = (BackgroundTransform)t;
		
		Image image = BitmapStore.getBitmap(mBitmapName);
		Image imageReversed = BitmapStore.getBitmapReversed(mBitmapName);
		
		int scaledxClip = (int)(bt.getXClip() * cam.getPixelsPerMetre());
		
		int width = image.getWidth(null);
        int height = image.getHeight(null);
        
		PointF position = t.getLocation();

        float floatstartY = ((cam.getYCentre() - ((cam.getCameraWorldCentre() - position.y) * cam.getPixelsPerMetre())));
        int startY = (int) floatstartY;
        float floatendY = ((cam.getYCentre() - ((cam.getCameraWorldCentre() - position.y - t.getSize().y) * cam.getPixelsPerMetre())));
        int endY = (int) floatendY;

        Rectangle fromRect1 = new Rectangle(0, 0, width - scaledxClip, height);
        Rectangle toRect1 = new Rectangle(scaledxClip, startY, width, endY);

        Rectangle fromRect2 = new Rectangle(width - scaledxClip, 0, width, height);
        Rectangle toRect2 = new Rectangle(0, startY, scaledxClip, endY);

        if (!bt.getReversedFirst()) {
            g.drawImage(image, fromRect1.x, fromRect1.y, fromRect1.width, fromRect1.height, 
            		toRect1.x, toRect1.y, toRect1.width, toRect1.height, null);
            g.drawImage(imageReversed, fromRect2.x, fromRect2.y, fromRect2.width, fromRect2.height, 
            		toRect2.x, toRect2.y, toRect2.width, toRect2.height, null);
        }
        else {
        	g.drawImage(imageReversed, fromRect1.x, fromRect1.y, fromRect1.width, fromRect1.height, 
            		toRect1.x, toRect1.y, toRect1.width, toRect1.height, null);
            g.drawImage(image, fromRect2.x, fromRect2.y, fromRect2.width, fromRect2.height, 
            		toRect2.x, toRect2.y, toRect2.width, toRect2.height, null);
        }
        
        g.drawImage(image, 0, 0, 800, 500, null);
		
	}

}
