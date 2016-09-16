package main.java.com.impl;

import java.util.*;
/** Class ElevatorAlgorithm - implements the algorithm for the elevator for 
  * transporting people where they want to go by moving the elevator
  * up and down 
  * Your Names Here
  * Quiz Section ID Here
  */

// All elevator algorithm classes must implement ElevatorAlgorithm. 
// ElevatorAlgorithm is an interface for the classes that define elevator
// algorithms. All elevator algorithm classes must include the following
// methods:
// public void SetController(ElevatorController controller)
// public void move()
// To implement the interface, you must include the following after
// the class name and before the open brace:
// implements ElevatorAlgorithm
public class NaiveElevatorAlgorithm implements ElevatorAlgorithm {
  
  private ElevatorController controller; //controller for this elevator algorithm
  
  /** Constructor of the class -- does not take any parameters*/
  public NaiveElevatorAlgorithm() {
  }
  
  /** sets the elevator controller for the algorithm */
  // method called when the ElevatorController object is constructed
  public void setController(ElevatorController controller) {
    this.controller = controller;
  }
  
  /** Algorithm which decides the movement of the elevator.
   The following is a naive algorithm. It looks for the person waiting
   for the longest time on any of the floors. The elevator is moved to
   that floor and the person is picked up. He is dropped at his target
   floor. The same process is repeated each time the function move
   is called */
  public void move() {
    ArrayList people=controller.getAllPeopleWaiting();
    Person person=controller.getLongestWaitingPerson(people);
    
    //i.e. if at all there is a person in the system 
    if(person != null) {
      int startingFloor=person.getStartingFloor();
      int targetFloor=person.getTargetFloor();
      controller.moveToFloor(startingFloor);
      controller.loadPerson(person);
      controller.moveToFloor(targetFloor);
      controller.unloadElevator();
    }     
    //else -- the elevator does not move anywhere if no one is requesting
    //service
  }   
}
