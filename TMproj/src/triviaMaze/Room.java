package triviaMaze;

public class Room {

private int myNumDoors;
private boolean myDoorAccess;
private boolean myRoomAccess;

	public Room(int theNumDoors, boolean theDoorAccess, boolean theRoomAccess){	
		myNumDoors   = theNumDoors;
		myDoorAccess = theDoorAccess;
		myRoomAccess = theRoomAccess;
	}
	public Room() {
		myNumDoors = 2;
		myDoorAccess = false;
		myRoomAccess = true;
	}
	public void lock(boolean theDoorAccess, int doorNumber) {
		for(int i = 0; i < getNumDoors(); i++) {
			if(doorNumber == i) theDoorAccess = false;
		}
	}
	
	public void unlock(boolean theDoorAccess, int doorNumber) {
		for(int i = 0; i < getNumDoors(); i++) {
			if(doorNumber == i) theDoorAccess = true;
		}
	}
	
	public boolean checkDoor(int theNumDoors, boolean theDoorAccess, boolean theRoomAccess) {
		int count = 0;
		for(int i = 0; i < theNumDoors; i++) {
			if(theDoorAccess == false || myRoomAccess == false) count++;
		}
		if(count == theNumDoors) {
			return false; //if the count is same as the amount of doors availabe than every door is lock, return false to end game
		}
		else {
			return true;//returns true if atleast one door is open, keeps game going
		}
	}
	public int getNumDoors() {
		return myNumDoors;
	}
	
	public void setNumDoor(int myNumDoors) {
		this.myNumDoors = myNumDoors;
	}
	public boolean getDoorAccess() {
		return myDoorAccess;
	}
	
	public void setDoorAccess(boolean myDoorAccess) {
		this.myDoorAccess = myDoorAccess;
	}
	public boolean getRoomAccess() {
		return myRoomAccess;
	}
	
	public void setRoomAccess(boolean myRoomAccess) {
		this.myRoomAccess = myRoomAccess;
	}
}
