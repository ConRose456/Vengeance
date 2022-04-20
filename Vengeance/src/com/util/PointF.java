package com.util;

public class PointF {

	public float x;
	public float y;
	
	public PointF() {
		this(0, 0);
	}
	
	public PointF(PointF p) {
		this(p.x, p.y);
	}

	public PointF(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setLocation(PointF p) {
		setLocation(p.x, p.y);
	}
	
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
}
