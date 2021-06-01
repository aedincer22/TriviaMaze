/**
 * 
 */
package Maze;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Ruchik Chaudhari
 *
 */
class RoomTest {

	Room room;

	@Test
	public void test_Room() {
		int doorCount = 2;
		Assertions.assertEquals(2, doorCount);
	}
	
	@Test
	public void test_RoomDoorCountZero(){
		int doorCount = 0;
		Assertions.assertNotEquals(2, doorCount);
	}
	
//	@Test(expected = IllegalArgumentException.class)
//	public void testIllegalArgumentException() {
//	    int doorCount = -1;
//	    ;
//	}
	
	
	@Test
	public void testLockDoor() {
		boolean [] expectedArray = new boolean[] {false,true};
		room = new Room(2);
		room.lockDoor();
		assertArrayEquals(expectedArray, room.myDoors);
	}
	
	@Test
	public void testIsLockAllDoorsOpen() {
		room = new Room(2);
		room.isLocked();
		Assertions.assertFalse(room.isLocked());
	}
	
	@Test
	public void testIsLockOneDoorIsOpen() {
		room = new Room(2);
		room.lockDoor();
		room.isLocked();
		Assertions.assertFalse(room.isLocked());
	}
	@Test
	public void testIsLockNoOPenDoor() {
		room = new Room(2);
		room.lockDoor();
		room.lockDoor();
		room.isLocked();
		Assertions.assertTrue(room.isLocked());
	}
	@Test
	public void testToStringEmptyArray() {
		Room nwRoom = new Room(0);
		assertEquals("[]", nwRoom.toString());
	}


}
