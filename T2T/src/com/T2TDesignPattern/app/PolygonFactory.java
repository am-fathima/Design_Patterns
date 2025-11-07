package com.T2TDesignPattern.app;

public class PolygonFactory {
	public Polygon getPolygon(int numOfSides) {
		
		if (numOfSides == 3) {
			return new Triangle();
		}
		
		
		return null;
		
	}
	
}
