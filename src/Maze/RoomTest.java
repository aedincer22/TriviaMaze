/*
 * RoomTest Class
 * Version Spring 2021 
 * 06/05/2021
 */
package Maze;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for Room class.
 * 
 * @author Ruchik Chaudhari, Shirwa Ahmed, and Yongzhao Ye
 * @version Spring 2021
 *
 */
public class RoomTest {

	/**
	 * The room instance to be tested.
	 */
	private Room testRoom;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		testRoom = new Room(new char[] { 'S', 'N', 'E', 'W' });
	}

	/**
	 * Test method for {@link maze.Room#Room(char[])}.
	 */
	@Test(expected = NullPointerException.class)
	public void testNullRoom() {
		final Room nullRoom = new Room(null);
	}

	/**
	 * Test method for {@link maze.Room#Room(char[])}.
	 */
	@Test
	public void testRoom() {

		testRoom.getDoors();
		assertFalse(testRoom.getDoors().equals(null));
	}

	/**
	 * Test method for {@link maze.Room#buildRoom(char[])}.
	 */
	@Test
	public void testBuildRoom() {
		final Map<Character, Boolean> expected = new HashMap<Character, Boolean>();
		expected.put('S', false);
		expected.put('N', false);
		expected.put('E', false);
		expected.put('W', false);
		assertTrue(testRoom.getDoors().equals(expected));
	}

	/**
	 * Test method for {@link maze.Room#deleteDoor(char)}.
	 */
	@Test
	public void testDeleteDoorTrue() {
		final char ch = 'S';
		final Map<Character, Boolean> expected = new HashMap<Character, Boolean>();
		expected.put('N', false);
		expected.put('E', false);
		expected.put('W', false);
		testRoom.deleteDoor(ch);
		assertTrue(testRoom.getDoors().equals(expected));
	}

	/**
	 * Test method for {@link maze.Room#deleteDoor(char)}.
	 */
	@Test
	public void testDeleteDoorFalse() {
		final char ch = 'N';
		final Map<Character, Boolean> expected = new HashMap<Character, Boolean>();
		expected.put('N', false);
		expected.put('E', false);
		expected.put('W', false);
		testRoom.deleteDoor(ch);
		assertFalse(testRoom.getDoors().equals(expected));
	}

	/**
	 * Test method for {@link maze.Room#isLocked()}.
	 */
	@Test
	public void testIsLockedTrue() {
		testRoom.deleteDoor('S');
		testRoom.deleteDoor('E');
		testRoom.deleteDoor('N');
		testRoom.deleteDoor('W');
		assertTrue(testRoom.isLocked());
	}

	/**
	 * Test method for {@link maze.Room#isLocked()}.
	 */
	@Test
	public void testIsLockedFalse() {
		assertFalse(testRoom.isLocked());
	}

	/**
	 * Test method for {@link maze.Room#openDoor(char)}.
	 */
	@Test
	public void testOpenDoorTrue() {
		final char ch = 'S';
		testRoom.openDoor(ch);
		assertTrue(testRoom.getDoors().get(ch));
	}

	/**
	 * Test method for {@link maze.Room#openDoor(char)}.
	 */
	@Test
	public void testOpenDoorFalse() {
		final char ch = 'S';
		assertFalse(testRoom.getDoors().get(ch));
	}

	/**
	 * Test method for {@link maze.Room#isSolved()}.
	 */
	@Test
	public void testIsSolvedTrue() {
		final char ch = 'S';
		testRoom.openDoor(ch);
		assertTrue(testRoom.isSolved());
	}

	/**
	 * Test method for {@link maze.Room#isSolved()}.
	 */
	@Test
	public void testIsSolvedFalse() {
		assertFalse(testRoom.isSolved());
	}

	/**
	 * Test method for {@link maze.Room#getAvailableDoors()}.
	 */
	@Test
	public void testGetAvailableDoorsTrue() {

		final Set<Character> expected = new HashSet<Character>();
		expected.add('N');
		expected.add('E');
		expected.add('W');
		expected.add('S');

		assertTrue(testRoom.getAvailableDoors().equals(expected));
	}

	/**
	 * Test method for {@link maze.Room#getAvailableDoors()}.
	 */
	@Test
	public void testGetAvailableDoorsFalse() {

		final Set<Character> expected = new HashSet<Character>();
		expected.add('N');
		expected.add('E');
		expected.add('W');

		assertFalse(testRoom.getAvailableDoors().equals(expected));
	}

	/**
	 * Test method for {@link maze.Room#isDoorOpen(char)}.
	 */
	@Test
	public void testIsDoorOpenTrue() {
		final char ch = 'W';
		testRoom.openDoor(ch);
		assertTrue(testRoom.isDoorOpen(ch));
	}

	/**
	 * Test method for {@link maze.Room#isDoorOpen(char)}.
	 */
	@Test
	public void testIsDoorOpenFalse() {
		assertFalse(testRoom.isDoorOpen('W'));
	}

	/**
	 * Test method for {@link maze.Room#toString()}.
	 */
	@Test
	public void testToString() {
		final Map<Character, Boolean> expected = new HashMap<Character, Boolean>();
		expected.put('S', false);
		expected.put('N', false);
		expected.put('E', false);
		expected.put('W', false);
		assertTrue(testRoom.toString().equals(expected.toString()));
	}

	/**
	 * Test method for {@link maze.Room#isDoorOpen(char)}.
	 */
	@Test
	public void testGetMyDoors() {
		final Map<Character, Boolean> expected = new HashMap<Character, Boolean>();
		expected.put('S', false);
		expected.put('N', false);
		expected.put('E', false);
		expected.put('W', false);
		assertTrue(testRoom.getDoors().equals(expected));
	}

}
