package Maze;

import java.util.Arrays;

public class Room {

	final private boolean myDoors [];
	private boolean isLocked;
	
	public Room(final int theDoorCount) {
		
		if (theDoorCount > 2 || theDoorCount < 0) {
			throw new IllegalArgumentException("The room can have 0 to 2 doors.");
		}
		myDoors = new boolean [theDoorCount];
		Arrays.fill(myDoors, true);
		isLocked = false;
	}
	
	public void lockDoor() {
		//Shut down one door in the room
		int index = 0;
		for (boolean door : myDoors) {
			if (door) {
				myDoors[index] = false;
				break;
			}
			index++;
		}
	}
	public boolean isLocked() {
		//if all doors are false (meaning they are not open) then room is locked
		for (boolean door : myDoors) {
			if (door) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		return Arrays.toString(myDoors);
	}
	

}
