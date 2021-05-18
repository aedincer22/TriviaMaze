package triviaMaze;

public class Room {

private int myNumDoors;
private boolean myDoorAccess;
private boolean myRoomAccess;

	public Room(){
		
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
