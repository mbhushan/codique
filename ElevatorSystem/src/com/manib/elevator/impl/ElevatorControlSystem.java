package com.manib.elevator.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.manib.elevator.impl.exceptions.InvalidNumberInput;
import com.manib.elevator.interfaces.ElevatorControl;

public class ElevatorControlSystem implements ElevatorControl {

	public static final int MAX_ELEVATORS = 4;
	private int numElevators = 0;
	private int numFloors = 0;
	List<Elevator> elevators;
	PriorityQueue<Integer> pickupFloors;
	
	public ElevatorControlSystem(int numElevators, int numFloors) throws InvalidNumberInput {
		if (numElevators < 1) {
			throw new InvalidNumberInput("number of elevators has to be positive!");
		}
		if (numFloors < 1) {
			throw new InvalidNumberInput("number of floors has to be positive!");
		}

		this.numElevators = (numElevators > MAX_ELEVATORS) ? MAX_ELEVATORS: numElevators;
		this.numFloors = numFloors;
		
		initElevators();
		pickupFloors = new PriorityQueue<Integer>(10);
	}
	
	private void initElevators() {
		this.elevators = new ArrayList<Elevator>();
		for (int id=0; id<this.numElevators; id++) {
			this.elevators.add(new Elevator(1));
		}
	}
	
	
	@Override
	public void pickUp(Integer pickUpFloor) {
		this.pickupFloors.add(pickUpFloor);
	}

	@Override
	public void destination(Integer elevatorId, Integer destinationFloor) {
		this.elevators.get(elevatorId).addDestination(destinationFloor);
	}

	@Override
	public void step() {
		for (Elevator elv: this.elevators) {
			switch(elv.status()) {
			case ELEVATOR_FREE:
				if (!pickupFloors.isEmpty()) {
					elv.addDestination(pickupFloors.poll());
				}
				break;
			case ELEVATOR_BUSY:
				switch (elv.direction()){
	            case ELEVATOR_UP:
	              elv.moveUp();
	              break;
	            case ELEVATOR_DOWN:
	              elv.moveDown();
	              break;
	            case ELEVATOR_HOLD:
	              // TODO: can be more sophisticated, emergency, alert, maintenance. 
	              elv.popDestination();
	              break;
	          }
			}
		}
	}

}
