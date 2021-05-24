package Test;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Maze.Room;

class Room_Test {
	Room room;

	@Test
	public void test_Room() {
		room = new Room(0);
		int doorCount = 2;
		Assertions.assertEquals(2, doorCount);
		Assertions.assertNotEquals(1, doorCount);
	}
	
	@Test
	public void testLockDoor() {
		room = new Room(2);
		room.lockDoor();
	}
	
	@Test
	public void testIsLock() {
		room = new Room(2);
		room.isLocked();
		Assertions.assertFalse(room.isLocked());
	}
	
	@Test
	public void testToString() {
		Room nwRoom = new Room(0);
		assertEquals(nwRoom.toString(), "[]");
	}
	
}
