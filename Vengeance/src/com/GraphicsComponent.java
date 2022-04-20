package com;

import java.awt.Graphics;

import com.GOSpecs.GameObjectSpec;
import com.util.PointF;

interface GraphicsComponent {
	void init(GameObjectSpec spec, PointF objectSize, int pixelsPerMetre);
	void draw(Graphics g, Transform t, Camera cam);
}
