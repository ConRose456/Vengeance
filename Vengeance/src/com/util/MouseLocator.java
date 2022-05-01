package com.util;

import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MouseLocator {
	
	private static JFrame mFrameReference;
	
	public MouseLocator(JFrame fr) {
		MouseLocator.mFrameReference = fr;
	}
	
	public static Point getMouseLocation(Point location) {
		location = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(location, mFrameReference);
		return location;
	}
}
