package com.util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class RectangleF {

	public float x;
	public float y;

	public float width;
	public float height;

	public RectangleF() {
		this(0, 0, 0, 0);
	}

	public RectangleF(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public RectangleF(PointF p, Dimension d) {
		this(p.x, p.y, d.width, d.height);
	}

	public RectangleF(RectangleF r) {
		this(r.x, r.y, r.width, r.height);
	}
	
	public RectangleF(Rectangle r) {
		this(r.x, r.y, r.width, r.height);
	}

	public RectangleF(float width, float height) {
		this(0, 0, width, height);
	}

	public RectangleF(PointF p) {
		this(p.x, p.y, 0, 0);
	}

	public RectangleF(Dimension d) {
		this(0, 0, d.width, d.height);
	}

	public RectangleF getBounds() {
		return new RectangleF(x, y, width, height);
	}

	public void setBounds(RectangleF r) {
		setBounds(r.x, r.y, r.width, r.height);
	}

	public void setBounds(float x, float y, float width, float height) {
		reshape(x, y, width, height);
	}

	public void reshape(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public PointF getLocation() {
		return new PointF(x, y);
	}
	
	public boolean contains(PointF p) {
        return contains(p.x, p.y);
    }
	
	public boolean contains(float x, float y) {
        return inside(x, y);
    }
	
	public boolean contains(RectangleF r) {
        return contains(r.x, r.y, r.width, r.height);
    }
	
	public boolean contains(float X, float Y, float W, float H) {
        float w = this.width;
        float h = this.height;
        if (((int)w | (int)h | (int)W | (int)H) < 0) {
            return false;
        }

        float x = this.x;
        float y = this.y;
        if (X < x || Y < y) {
            return false;
        }
        w += x;
        W += X;
        if (W <= X) {

            if (w >= x || W > w) return false;
        } else {
            if (w >= x && W > w) return false;
        }
        h += y;
        H += Y;
        if (H <= Y) {
            if (h >= y || H > h) return false;
        } else {
            if (h >= y && H > h) return false;
        }
        return true;
    }
	
	public boolean overlaps (RectangleF r) {
	    return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
	}
	
	public boolean inside(float X, float Y) {
        float w = this.width;
        float h = this.height;
        if (((int)w | (int)h) < 0) {
            // At least one of the dimensions is negative...
            return false;
        }
        // Note: if either dimension is zero, tests below must return false...
        float x = this.x;
        float y = this.y;
        if (X < x || Y < y) {
            return false;
        }
        w += x;
        h += y;
        //    overflow || intersect
        return ((w < x || w > X) &&
                (h < y || h > Y));
    }
	
	public boolean intersects(RectangleF r) {
        float tw = this.width;
        float th = this.height;
        float rw = r.width;
        float rh = r.height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        float tx = this.x;
        float ty = this.y;
        float rx = r.x;
        float ry = r.y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }
	
	public RectangleF intersection(RectangleF r) {
        float tx1 = this.x;
        float ty1 = this.y;
        float rx1 = r.x;
        float ry1 = r.y;
        float tx2 = tx1; tx2 += this.width;
        float ty2 = ty1; ty2 += this.height;
        float rx2 = rx1; rx2 += r.width;
        float ry2 = ry1; ry2 += r.height;
        if (tx1 < rx1) tx1 = rx1;
        if (ty1 < ry1) ty1 = ry1;
        if (tx2 > rx2) tx2 = rx2;
        if (ty2 > ry2) ty2 = ry2;
        tx2 -= tx1;
        ty2 -= ty1;
        // tx2,ty2 will never overflow (they will never be
        // larger than the smallest of the two source w,h)
        // they might underflow, though...
        if (tx2 < Float.MIN_VALUE) tx2 = Float.MIN_VALUE;
        if (ty2 < Float.MIN_VALUE) ty2 = Float.MIN_VALUE;
        return new RectangleF(tx1, ty1, tx2, ty2);
    }

}
