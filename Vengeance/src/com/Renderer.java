package com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import com.util.RectangleF;

class Renderer {
	
	private Camera mCamera;
	private Point mScreenSize;
	
	private boolean debug = false;
	
	Renderer(Point screenSize) {
		mCamera = new Camera(screenSize.x, screenSize.y);
		mScreenSize = screenSize;
	}
	
	int getPixelsPerMetre() {
		return mCamera.getPixelsPerMetre();
	}
	
	void draw(ArrayList<GameObject> objects, HUD hud, Graphics g, GameState gs) {
		g.setColor(new Color(100, 100, 100, 255));
		g.fillRect(0, 0, mScreenSize.x, mScreenSize.y);
		
		if (gs.isDrawing()) {
			if (objects.get(LevelManager.PLAYER_INDEX).getTransform().getLocation().x > 9
					&& objects.get(LevelManager.PLAYER_INDEX).getTransform().getLocation().x < 75) {
				mCamera.setWorldXCentre(objects.get(LevelManager.PLAYER_INDEX).getTransform().getLocation());
			}
			if (objects.get(LevelManager.PLAYER_INDEX).getTransform().getLocation().y < 25) {
				mCamera.setWorldYCentre(objects.get(LevelManager.PLAYER_INDEX).getTransform().getLocation());
			}
			
			for (GameObject object : objects) {
				if (object.checkActive()) {
					if (object.getTag() != "Player") {
						object.draw(g, mCamera);
					}
				}
			}
			objects.get(LevelManager.PLAYER_INDEX).draw(g, mCamera);
			Transform tran = objects.get(LevelManager.PLAYER_INDEX).getTransform();
			PlayerTransform pTran = (PlayerTransform) tran;
			
			if (debug) {
				drawPlayerColliders(g, pTran);
			}

		}
		
		hud.draw(g, gs);
	}
	
	private void drawPlayerColliders(Graphics g, PlayerTransform pTran) {
		g.setColor(new Color(255, 0, 0, 255));
		RectangleF rect = new RectangleF(mCamera.worldToScreen(pTran.getColliders().get(2).x, pTran.getColliders().get(2).y, pTran.getColliders().get(2).width, pTran.getColliders().get(2).height));
		RectangleF rect_2 = new RectangleF(mCamera.worldToScreen(pTran.getColliders().get(3).x, pTran.getColliders().get(3).y, pTran.getColliders().get(3).width, pTran.getColliders().get(3).height));

		g.drawRect((int)rect.x, (int)rect.y, (int)rect.width, (int)rect.height);
		g.drawRect((int)rect_2.x, (int)rect_2.y, (int)rect_2.width, (int)rect_2.height);
		
		RectangleF rect_3 = new RectangleF(mCamera.worldToScreen(pTran.getColliders().get(0).x, pTran.getColliders().get(0).y, pTran.getColliders().get(0).width, pTran.getColliders().get(0).height));
		RectangleF rect_4 = new RectangleF(mCamera.worldToScreen(pTran.getColliders().get(1).x, pTran.getColliders().get(1).y, pTran.getColliders().get(1).width, pTran.getColliders().get(1).height));

		g.drawRect((int)rect_3.x, (int)rect_3.y, (int)rect_3.width, (int)rect_3.height);
		g.drawRect((int)rect_4.x, (int)rect_4.y, (int)rect_4.width, (int)rect_4.height);
		
		g.setColor(new Color(255, 255, 255, 255));
		RectangleF rect_5 = new RectangleF(mCamera.worldToScreen(pTran.getCollider().x, pTran.getCollider().y, pTran.getCollider().width, pTran.getCollider().height));

		g.drawRect((int)rect_5.x, (int)rect_5.y, (int)rect_5.width, (int)rect_5.height);
	}
}
