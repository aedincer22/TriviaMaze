package Maze;

import java.util.Arrays;
import java.util.*;

public class Room {
	

	final private Map<Character, Boolean> myDoors;
	//private boolean isLocked; not using islocked
	
	/**
	  *This method initializes room, and creates 
	  *doors corresponding to the doorCount and sets
	  *the isLocked value to false
	  * @param theDoorCount is an integer value that used to setup rooms
	  */
	public Room(final char [] theDoors) {
		Objects.requireNonNull(theDoors);
		myDoors = buildRoom(theDoors);
	}
	
	private Map<Character, Boolean> buildRoom(final char[] theDoors){
		
		//set all the doors to false  
		Map<Character, Boolean> doors = new HashMap<>();
		for (final char ch : theDoors) {
			doors.put(ch, false);
		}
		return doors;
	}
	
	/**
	 * Delete the door from the room.
	 */	
	public void deleteDoor(final char ch) {
		myDoors.remove(ch);
	}
	
	/**
	 * 
	 */
	public boolean isLocked() {
		//if no door are available then the room is locked
		return myDoors.size() == 0;
	}
	
	/**
	 * 
	 * @param ch
	 */
	public void openDoor(final char ch) {
		
		if (myDoors.containsKey(ch)) {
			myDoors.put(ch, true);
		} 
	}
	
	public boolean isSolved() {
		for (char ch : myDoors.keySet()) {
			if (myDoors.get(ch) == true) {
				return true;
			}
		}
		return false;
	}
	
	public Set<Character> getAvailableDoors() {
		return myDoors.keySet();
	}
	
	public boolean isDoorOpen(char ch) {
		if (myDoors.containsKey(ch)) {
			return myDoors.get(ch);
		} else {
			return false;
		}
		
	}
	
	/**
	  *This method creates a string of rooms to be display
	  * @return String of array display of rooms
	  */
	public String toString() {
		return myDoors.toString();
	}
	

}
