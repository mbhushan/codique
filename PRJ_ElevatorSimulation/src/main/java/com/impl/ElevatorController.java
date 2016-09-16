package main.java.com.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/*
 * ElevatorController.java
 * Your names here
 * Quiz section ID here
 *
 * A controller for the Building/Elevator simulator.
 * This controller generates a sequence of people arriving and changes the
 * state of the system as directed by the ElevatorAlgorihm.
 * The controller also contains the state of the world (the people waiting
 * on floors and the people in the elevator). The responsibilities of the
 * controller include loading people on the elevator, unloading people, and
 * moving the elevator to a specific floor.
 */
public class ElevatorController {
  
  // These variables are for the class
  // DO NOT remove these class variables
  // in Java, the static final keywords mean that these variables are class-wide
  // and cannot be assigned after they are initialized to the values. Static
  // variables belong to the entire class instead of instances of the class.
  private static final int defaultHowManyFloors = 5;             //default number of floors
  private static final int defaultHowManyPeopleMax = 30;         //default number of maximum people
  private static final int defaultDelayBetweenStepsMilli = 600; //default delay in milliseconds
  private static final int randomSeed=100;                       //seed for generating the sequence
  private static final int peopleIncrementCount=3;               //max number of people added at each step
  private static final int incrementInterval=10;                 //clock ticks after which people are randomly generated
                                                                 //at various floors
  private static final int elevatorCapacity=8;                   //maximum capacity of the elevator
  
  // instance variables
  private int clockTicks;             //clock ticks passed till now (time expired during simulation)
  private int elevatorFloor;          //current floor of the elevator
  private int howManyFloors;          //number of floors in the building
  private int howManyPeopleMax;       //maximum number of people in simulation
  private int delayBetweenStepsMilli; //delay for clock ticks
  private ElevatorAlgorithm algorithm;//algorithm which controls this elevator
//  private Viewer viewer;              // the graphical window and stuff..
  private Random rgenerator;          //random number generator to assist in simulation
  private ArrayList allPeopleList;    //list of all the people entered in simulation till now 
  private ArrayList floors;    //an arraylist of arraylists - to keep an account of people
                               // waiting at each particular floor. ith element of this arraylist
                               // is an arraylist which is a list of people waiting at the (i+1) floor
  private ArrayList elevatorList;     //list of people currently in the elevator
  
  /** Create a new instance of ElevatorController,
   *           with a default number of floors, maxpeople and delay.
   */
  public ElevatorController(ElevatorAlgorithm ea) {
    // initialize instance variables for controller
    howManyFloors = defaultHowManyFloors;
    howManyPeopleMax = defaultHowManyPeopleMax;
    delayBetweenStepsMilli = defaultDelayBetweenStepsMilli;
    floors = new ArrayList(); 
    for(int i=0; i <= howManyFloors; i++) {  //make ArrayLists for each floor
      floors.add(new ArrayList());
    }
    elevatorList = new ArrayList();  //make ArrayList to store people in elevator
    allPeopleList = new ArrayList(); //make ArrayList to store all people in
                                     //simulation
    elevatorFloor = 1;           //start elevator at floor 1
    clockTicks = 0;              //start clock at 0
    rgenerator = new Random(randomSeed); 
    algorithm = ea;          // set the algorithm for the controller                  
    ea.setController(this);  // make sure algorithm has this object as the controller                                           
    //viewer = new Viewer(howManyFloors, 2 * howManyPeopleMax); //initialize viewer
  }
  
  /** get the current floor that the elevator is on */
  public int getCurrentFloor() {
    return elevatorFloor;
  }
  
  /** get the highest floor number */
  public int getMaxFloor() {
    return howManyFloors;
  }
  
  /** get the lowest floor number */
  public int getMinFloor() {
    return 1;
  }
  
  /** Get the current clockTicks */
  public int getClockTicks() {
    return clockTicks;
  }
  
  /** get the number of people still in the system 
   * This is the number of people in the elevator and number 
   * of people waiting in queues on different floors*/
  public int getRemainingPeopleCount() {
    int count = elevatorList.size();    // number of people in elevator
    for(int i=1; i <= howManyFloors; i++) {  
      ArrayList people = (ArrayList)(floors.get(i));
      count += people.size();    // number of people waiting on floors
    }  
    return count;
  } 
  
  /** returns a list of the people waiting at the specified floor f */
  public ArrayList getPeopleAtFloor(int f) {
    return (ArrayList)floors.get(f);
  } 
  
  /** returns a list of the all the people waiting at any floor in the building */
  public ArrayList getAllPeopleWaiting() {
    ArrayList waiting = new ArrayList();  //create a new list and put all
                                          //people waiting on the floors in list
    for(int i=1; i<=howManyFloors; i++) {
      Iterator itr = ((ArrayList)floors.get(i)).iterator();
      while(itr.hasNext()) {
        waiting.add((Person)itr.next());
      } 
    }
    return waiting;
  }  
  
  /** This method takes an arrayList of persons waiting for the elevator and 
   * returns the person who has had the longest waiting time for the elevator */
  public Person getLongestWaitingPerson(ArrayList waitingPeople) {
    Person mostWaiting = null;
    int time;
    int lowestEnteringTime = getClockTicks();
    Iterator itr = waitingPeople.iterator();
    // go through all people waiting and find person who entered the simulation
    // the longest time ago
    while(itr.hasNext()) {
      Person person = (Person)itr.next();
      time = person.getEnteringTime();
      if(time <= lowestEnteringTime) {
        lowestEnteringTime = time;
        mostWaiting = person;
      }   
    }
    return mostWaiting;
  }
  
  /** load person - takes a person object and loads it into the elevator if the 
   * person is waiting on the current floor -it returns true if the person was 
   * successfully loaded into the elevator, else returns false*/
  public boolean loadPerson(Person person) {
    ArrayList people = (ArrayList)floors.get(elevatorFloor);
    //this means that the person can not be loaded into the elevator as he/she is not in the waiting queue
    //of the current floor
    if(!people.contains(person)){
      return false;
    }
    people.remove(person);
    elevatorList.add(person);
    // loading a person takes time
    incrementClock();
    view("Person waiting since last " + (getClockTicks()-person.getEnteringTime()) +
         " units of time picked up for target floor:" + person.getTargetFloor());
    delay(1);
    return true;
  }
  
  /** take the elevator to the specified floor */
  public void moveToFloor(int f) {
    if(f > howManyFloors) { // if floor is not in range, force it to be in range
      f = howManyFloors;
    } 
    if(f <= 0) {
      f = 1; 
    }
    
 int direction;
 if(f > elevatorFloor) {
   direction = 1 ;
 } else {
   direction = -1;
 }
 
    //moving takes time and the clock is correspondingly incremented.
 //for going one floor up/down, the clok takes one unit of time
 while(elevatorFloor != f) {
  elevatorFloor = elevatorFloor + direction;  
  incrementClock();
     view("Elevator moved to floor:" + elevatorFloor);
     delay(1);
   }
}   
  
  /** take out the people who want to get out at the current floor */
  public void unloadElevator() {
    Iterator itr = elevatorList.iterator();
    int count = 0;
    
    while(itr.hasNext()) {
      Person next = (Person)itr.next();
      if(next.getTargetFloor()==elevatorFloor) {
        count++;
        itr.remove();
        next.setLeavingTime(getClockTicks()); 
      }
    }
    
    //stopping to unload people takes time
    incrementClock();
    view(count + " people dropped at floor :" + elevatorFloor);
    delay(1);
  }   
  
  /**This method returns the total waiting time for the persons in the system
   * who have reached their destinations 
   * Helpful for gathering statistics about the algorithm */
  public int getTotalWaitingTime() {
    int waitingTime = 0;
    Iterator itr = allPeopleList.iterator();
    while(itr.hasNext()) {
      waitingTime += ((Person)itr.next()).getWaitingTime();
    }
    return waitingTime;
  }
 
  ////////////////////////////////////////////////////////////////////////
  // Following methods used for simulation -- do not modify following code
  /////////////////////////////////////////////////////////////////////////
  /** increment the clock by specified amount - it does
   * so by calling the method which incements the clock by
   * one unit at time 
   * DO NOT MODIFY CODE */
  void incrementClock(int units) {
    for(int i=1; i<=units; i++) {
      incrementClock();
    }
  }    
  
  /** increments the clock by one unit. after incrementInterval clock ticks, the method for
   * generating the people is called. This ensures that people are added to the system at regular 
   * intervals of time irrespetive of how many clock ticks does one call to move method
   * of the ElevatorAlgorithm takes
   * DO NOT MODIFY CODE */
  void incrementClock() {
    clockTicks++;
    if(clockTicks%incrementInterval==0) {
      generatePeople();
    }
  }    
  
  /** run the simulator, calling the elevator algorithm, till there are any people left
   * in the system    
   * DO NOT MODIFY CODE */
  public void generatePeople() {
    
    if(allPeopleList.size() <= howManyPeopleMax) {    
      // A randomly chosen number of people are added to 
      // the the queues at one of the randomly chosen floors
      int newPeopleCount = rgenerator.nextInt(peopleIncrementCount)+1;
      //add the new people to appropriate floor queues
      for(int i=1; i<=newPeopleCount; i++) {   
        int floor = rgenerator.nextInt(howManyFloors)+1;
        int targetFloor = rgenerator.nextInt(howManyFloors)+1;
        //we discard this random generation if the waiting floor is
        //same as the targetfloor
        if(floor!=targetFloor) {  
          Person newArrival = new Person(floor,targetFloor,getClockTicks());
          ArrayList people = (ArrayList)(floors.get(floor));
          
          //add the current arrival to appropriate floor list 
          people.add(newArrival);
          allPeopleList.add(newArrival); 
        }
      }    
    } 
    view("People randomly added to the floors");
    delay(1);
  }
  
  /** starts the simulation
   * DO NOT MODIFY CODE */
  public void run() {        
    //moveToFloor(1);  //starting from floor 1
    generatePeople();
    while (getRemainingPeopleCount()>0 || allPeopleList.size() <= howManyPeopleMax) {  
      incrementClock();          
      view("Clock incremented by one unit - control back to elevator");
      delay(1);
      algorithm.move();  // this is where the move method for the algorithm gets called
    }
  }
  
  /** This method introduces i times a fixed amount of sleep 
   * DO NOT MODIFY CODE */
  public void delay(int i) {
    try {         // 142 students -- you may ignore the try/catch, but
                  // this is something you have to look forward to in 143
      Thread.sleep(i * this.delayBetweenStepsMilli);
    } catch (Throwable e) {
    } 
  }
  
  /** This fucntion calls the viewer with required parameters 
   * DO NOT MODIFY CODE */
  public void view(String message) {
   // viewer.redraw(getClockTicks(),elevatorFloor,floors,elevatorList,message);
  } 

}