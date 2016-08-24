package com.manib.elevator.impl;

import java.util.PriorityQueue;

import com.manib.elevator.impl.enums.ElevatorDirection;
import com.manib.elevator.impl.enums.ElevatorStatus;
import com.manib.elevator.interfaces.ElevatorTemplate;

public class Elevator implements ElevatorTemplate {

	private Integer currentFloor;
	private PriorityQueue<Integer> targetFloor;
	
	public Elevator(int currentFloor) {
		this.currentFloor = currentFloor;
		this.targetFloor = new PriorityQueue<Integer>(10);
	}
	
	public int nextDestination() {
		return this.targetFloor.peek();
	}
	
	public int currentFloor() {
		return this.currentFloor;
	}
	
	public void popDestination() {
		this.targetFloor.poll();
	}
	
	@Override
	public void moveUp() {
		++this.currentFloor;
	}

	@Override
	public void moveDown() {
		--this.currentFloor;
	}

	@Override
	public void addDestination(Integer destination) {
		this.targetFloor.add(destination);
	}

	@Override
	public ElevatorDirection direction() {
		if (this.targetFloor.size() > 0) {
			if (this.currentFloor < this.targetFloor.peek()) {
				return ElevatorDirection.ELEVATOR_UP;
			} else if (this.currentFloor > this.targetFloor.peek()) {
				return ElevatorDirection.ELEVATOR_DOWN;
			}
		}
		return ElevatorDirection.ELEVATOR_HOLD;
	}

	@Override
	public ElevatorStatus status() {
		return this.targetFloor.size() > 0 ? ElevatorStatus.ELEVATOR_BUSY: ElevatorStatus.ELEVATOR_FREE;
	}

}
