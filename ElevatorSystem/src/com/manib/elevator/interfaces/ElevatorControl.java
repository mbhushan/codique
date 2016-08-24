package com.manib.elevator.interfaces;

public interface ElevatorControl {

	public void pickUp(Integer pickUpFloor);

	public void destination(Integer elevatorId, Integer destinationFloor);

	public void step();
}
