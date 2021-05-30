package Maze;

import java.util.*;

class Room {
	
	/**
	 * Doors in the room, where each door is labeled
	 * with it's direction and a boolean value attached
	 * to it, which represents if the door is open or close.
	 * Example: S --> false, N --> True
	 */
	final private Map<Character, Boolean> myDoors;
	
	/**
	  * The parameterized constructor initializes room, and creates 
	  * doors corresponding to the door arrays and sets all the 
	  * the door's value to false.
	  * Example: [W,S,E] --> {W -> false, S -> false, E -> false}
	  * @param theDoors array
	  */
	public Room(final char [] theDoors) {
		Objects.requireNonNull(theDoors);
		myDoors = buildRoom(theDoors);
	}
	
	/**
	 * A private utility method to help create the map 
	 * from the doors array.
	 * @param theDoors
	 * @return Map<Character, Boolean>
	 */
	private Map<Character, Boolean> buildRoom(final char[] theDoors){
		
		//set initially all the doors to false  
		final Map<Character, Boolean> doors = new HashMap<>();
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
	 * Check if there are available doors in the room.
	 * If no doors available then the room is locked else
	 * it is not locked
	 * @return boolean: whether the room is locked or not
	 */
	public boolean isLocked() {
		//if no doors are available then the room is locked
		return myDoors.size() == 0;
	}
	
	/**
	 * Open the given door
	 * @param ch: Door name 
	 */
	public void openDoor(final char ch) {
		
		if (myDoors.containsKey(ch)) {
			myDoors.put(ch, true);
		} 
	}
	
	/**
	 * Check if the room has any open doors.
	 * @return boolean
	 */
	public boolean isSolved() {
		for (char ch : myDoors.keySet()) {
			if (myDoors.get(ch) == true) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get all the available doors in the room
	 * @return Set<Character>: list of available doors
	 */
	public Set<Character> getAvailableDoors() {
		final Set<Character>copy = new HashSet<>(myDoors.keySet());
		return copy;
	}
	
	/**
	 * Check if the given door open
	 * @param ch: Door name
	 * @return boolean
	 */
	public boolean isDoorOpen(final char ch) {
		if (myDoors.containsKey(ch)) {
			return myDoors.get(ch);
		} else {
			return false;
		}	
	}
	
	/**
	 * To String of the Room class
	 */
	@Override
	public String toString() {
		return myDoors.toString();
	}
	

}
