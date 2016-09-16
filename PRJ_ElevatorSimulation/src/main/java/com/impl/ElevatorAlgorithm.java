package main.java.com.impl;

public interface ElevatorAlgorithm {
	// methods that all elevator algorithm classes must implement
	public void setController(ElevatorController controller);

	public void move();
}