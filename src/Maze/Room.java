package Maze;

import java.util.Arrays;

public class Room{

	final public boolean myDoors [];
	//private boolean isLocked; not using islocked
	//
	/**
	  *This method initializes room, and creates 
	  *doors corresponding to the doorCount and sets
	  *the isLocked value to false
	  * @param theDoorCount is an integer value that used to setup rooms
	  */
	public Room(final int theDoorCount) {
		
		if (theDoorCount > 2 || theDoorCount < 0) {
			throw new IllegalArgumentException("The room can have 0 to 2 doors.");
		}
		myDoors = new boolean [theDoorCount];
		Arrays.fill(myDoors, true);
	//	isLocked = false;is locked 
	}
	/**
	  *This method sets the door value 
	  *from true(open) to false(locked)
	  *and updates the location of the current door
	  */	
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
	
	/**
	  *This method check to see if 
	  *any doors are still open if not
	  *returns a boolean value and used to end game.
	  * @return boolean true if there is still door opens or false if no doors are open
	  */
	public boolean isLocked() {
		//if all doors are false (meaning they are not open) then room is locked
		for (boolean door : myDoors) {
			if (door) {
				return false;
			}
		}
		return true;
	}
	
	/**
	  *This method creates a string for the array
	  *of rooms to be display
	  * @return String of array display of rooms
	  */
	public String toString() {
		return Arrays.toString(myDoors);
	}
	

}
