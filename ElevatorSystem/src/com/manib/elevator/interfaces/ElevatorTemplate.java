package com.manib.elevator.interfaces;

import com.manib.elevator.impl.enums.ElevatorDirection;
import com.manib.elevator.impl.enums.ElevatorStatus;

public interface ElevatorTemplate {

	public void moveUp();

	public void moveDown();

	public void addDestination(Integer destination);

	public ElevatorDirection direction();

	public ElevatorStatus status();

}
