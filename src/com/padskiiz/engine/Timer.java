package com.padskiiz.engine;

public class Timer {

	private double lastLoopTime;
	
	public void init() {
		lastLoopTime = getTime();
	}

	public double getTime() {
		return System.nanoTime() / 1000_000_000.0;
	}
	
	public float getLastLoopTime() {
		double time = getTime();
        float elapsedTime = (float) (time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
	}
	
	public double getElapsedTime() {
		return lastLoopTime;
	}
}