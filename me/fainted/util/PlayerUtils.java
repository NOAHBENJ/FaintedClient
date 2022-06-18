package me.fainted.util;

public class PlayerUtils {

	public float yaw, pitch;
	
	public PlayerUtils() {
		this.yaw = 69420;
		this.pitch = 69420;
	}
	
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	public float getYaw() {
		return yaw;
	}
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	public float getPitch() {
		return pitch;
	}

	public void setDefaults() {
		this.pitch = 69420;
		this.yaw = 69420;
		
	}
	
	
}
