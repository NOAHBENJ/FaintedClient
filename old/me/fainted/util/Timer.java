package me.fainted.util;

public class Timer {
	public long lastMS;
	
	public void reset() {
		lastMS = System.currentTimeMillis();
	}
	
	public boolean hasTimeElapsed(long time, boolean reset) {
		if (System.currentTimeMillis()-lastMS > time) {
			if (reset) 
				reset();
			
			return true;
		}
		
		return false;
	}	
}
