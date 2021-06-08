/*
 * MazeTest
 * Version Spring 2021
 * 06/05/2021 
 */
package Maze;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for Maze class.
 * 
 * @author Ruchik Chaudhari, Shirwa Ahmed, and Yongzhao Ye
 * @version Spring 2021
 *
 */
public class MazeTest {

	/**
	 * Default 4 x 4 maze for testing.
	 */
	private Maze myDefaultMaze;

	/**
	 * Custom maze for testing.
	 */
	private Maze myCustomeMaze;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myDefaultMaze = new Maze();
		myCustomeMaze = new Maze(3, 3);
	}

	/**
	 * Test method for {@link maze.Maze#Maze()}.
	 */
	@Test
	public void testMaze() {
		final int expectedSize = 4;
		assertEquals(expectedSize, myDefaultMaze.size());
	}

	/**
	 * Test method for {@link maze.Maze#Maze(int, int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMazeIntIntException() {
		final int invalidRow = -1;
		final int invalidCol = 2;
		final Maze maze = new Maze(invalidRow, invalidCol);
	}

	/**
	 * Test method for {@link maze.Maze#Maze(int, int)}.
	 */
	@Test
	public void testMazeIntInt() {
		final int expectedSize = 3;
		assertEquals(expectedSize, myCustomeMaze.size());
	}

	/**
	 * Test method for {@link maze.Maze#setLocation(int, int, int, int)}.
	 */
	@Test
	public void testSetLocation() {
		final int row = 2;
		final int col = 3;
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		assertEquals(row, myDefaultMaze.getRowIndex());
		assertEquals(col, myDefaultMaze.getColIndex());
	}

	/**
	 * Test method for {@link maze.Maze#setLocation(int, int, int, int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetLocationExceptionNegative() {
		final int invalidRow = -1;
		final int col = 0;
		myDefaultMaze.setLocation(invalidRow, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
	}

	/**
	 * Test method for {@link maze.Maze#setLocation(int, int, int, int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetLocationExceptionOverSize() {
		myDefaultMaze.setLocation(myDefaultMaze.size(), myDefaultMaze.size(), myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
	}

	/**
	 * Test method for {@link maze.Maze#move(char)}.
	 */
	@Test
	public void testMoveSouth() {
		final char direction = 'S';
		final int row = 1;
		final int col = 0;
		myDefaultMaze.move(direction);
		assertEquals(row, myDefaultMaze.getRowIndex());
		assertEquals(col, myDefaultMaze.getColIndex());
	}

	/**
	 * Test method for {@link maze.Maze#move(char)}.
	 */
	@Test
	public void testMoveEast() {
		final char direction = 'E';
		final int row = 0;
		final int col = 1;
		myDefaultMaze.move(direction);
		assertEquals(row, myDefaultMaze.getRowIndex());
		assertEquals(col, myDefaultMaze.getColIndex());
	}

	/**
	 * Test method for {@link maze.Maze#move(char)}.
	 */
	@Test
	public void testMoveNorth() {
		final char direction = 'N';
		final int row = 2;
		final int col = 2;
		final int expectedRow = 1;
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		myDefaultMaze.move(direction);
		assertEquals(expectedRow, myDefaultMaze.getRowIndex());
		assertEquals(col, myDefaultMaze.getColIndex());
	}

	/**
	 * Test method for {@link maze.Maze#move(char)}.
	 */
	@Test
	public void testMoveWest() {
		final char direction = 'W';
		final int row = 2;
		final int col = 2;
		final int expectedCol = 1;
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		myDefaultMaze.move(direction);
		assertEquals(row, myDefaultMaze.getRowIndex());
		assertEquals(expectedCol, myDefaultMaze.getColIndex());
	}

	/**
	 * Test method for {@link maze.Maze#move(char)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMoveException() {
		final char invalidDirection = 'N';
		myDefaultMaze.move(invalidDirection);
	}

	/**
	 * Test method for {@link maze.Maze#canMoveNorth()}
	 */
	@Test
	public void testCanMoveNorthFalse() {
		assertFalse(myDefaultMaze.canMoveNorth());
	}

	/**
	 * Test method for {@link maze.Maze#canMoveNorth()}
	 */
	@Test
	public void testCanMoveNorthTrue() {
		final char row = 1;
		final char col = 0;
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		assertTrue(myDefaultMaze.canMoveNorth());
	}

	/**
	 * Test method for {@link maze.Maze#canMoveSouth}
	 */
	@Test
	public void testCanMoveSouthTrue() {
		assertTrue(myDefaultMaze.canMoveSouth());
	}

	/**
	 * Test method for {@link maze.Maze#canMoveSouth}
	 */
	@Test
	public void testCanMoveSouthFalse() {
		final int row = 3;
		final int col = 0;
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		assertFalse(myDefaultMaze.canMoveSouth());
	}

	/**
	 * Test method for {@link maze.Maze#canMoveEast}
	 */
	@Test
	public void testCanMoveEastFalse() {
		final int row = 3;
		final int col = 3;
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		assertFalse(myDefaultMaze.canMoveEast());
	}

	/**
	 * Test method for {@link maze.Maze#canMoveEast}
	 */
	@Test
	public void testCanMoveEastTrue() {
		final int row = 0;
		final int col = 2;
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		assertTrue(myDefaultMaze.canMoveEast());
	}

	/**
	 * Test method for {@link maze.Maze#canMoveWest}
	 */
	@Test
	public void testCanMoveWestFalse() {
		assertFalse(myDefaultMaze.canMoveWest());
	}

	/**
	 * Test method for {@link maze.Maze#canMoveWest}
	 */
	@Test
	public void testCanMoveWestTrue() {
		final int row = 2;
		final int col = 2;
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		assertTrue(myDefaultMaze.canMoveWest());
	}

	/**
	 * Test method for {@link maze.Maze#openDoors(char)}.
	 */
	@Test
	public void testOpenDoorsSouth() {
		/*
		 * Two door should open, when you open a door.
		 */
		final char direction1 = 'S';
		final char direction2 = 'N';
		myDefaultMaze.openDoors(direction1);
		assertTrue(myDefaultMaze.isCurrentRoomDoorOpen(direction1));
		myDefaultMaze.move(direction1);
		assertTrue(myDefaultMaze.isCurrentRoomDoorOpen(direction2));
	}

	/**
	 * Test method for {@link maze.Maze#openDoors(char)}.
	 */
	@Test
	public void testOpenDoorsNorth() {
		final int row = 2;
		final int col = 2;
		final char direction1 = 'N';
		final char direction2 = 'S';
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		myDefaultMaze.openDoors(direction1);
		assertTrue(myDefaultMaze.isCurrentRoomDoorOpen(direction1));
		myDefaultMaze.move(direction1);
		assertTrue(myDefaultMaze.isCurrentRoomDoorOpen(direction2));
	}

	/**
	 * Test method for {@link maze.Maze#openDoors(char)}.
	 */
	@Test
	public void testOpenDoorsEast() {
		final int row = 2;
		final int col = 2;
		final char d1 = 'E';
		final char d2 = 'W';
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		myDefaultMaze.openDoors(d1);
		assertTrue(myDefaultMaze.isCurrentRoomDoorOpen(d1));
		myDefaultMaze.move(d1);
		assertTrue(myDefaultMaze.isCurrentRoomDoorOpen(d2));
	}

	/**
	 * Test method for {@link maze.Maze#openDoors(char)}.
	 */
	@Test
	public void testOpenDoorsWest() {
		final int row = 2;
		final int col = 2;
		final char direction1 = 'W';
		final char direction2 = 'E';
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		myDefaultMaze.openDoors(direction1);
		assertTrue(myDefaultMaze.isCurrentRoomDoorOpen(direction1));
		myDefaultMaze.move(direction1);
		assertTrue(myDefaultMaze.isCurrentRoomDoorOpen(direction2));
	}

	/**
	 * Test method for {@link maze.Maze#isLastRoom()}.
	 */
	@Test
	public void testIsLastRoomTrue() {
		final int row = 3;
		final int col = 3;
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		assertTrue(myDefaultMaze.isLastRoom());
	}

	/**
	 * Test method for {@link maze.Maze#isLastRoom()}.
	 */
	@Test
	public void testIsLastRoomFalse() {
		final int row = 3;
		final int col = 1;
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		assertFalse(myDefaultMaze.isLastRoom());
	}

	/**
	 * Test method for {@link maze.Maze#isCurrentRoomLocked()}.
	 */
	@Test
	public void testIsCurrentRoomLockedTrue() {
		final Room[][] rooms = myDefaultMaze.getMaze();
		Room room = rooms[0][0];
		room.deleteDoor('S');
		room.deleteDoor('E');
		assertEquals(room.isLocked(), myDefaultMaze.isCurrentRoomLocked());
	}

	/**
	 * Test method for {@link maze.Maze#isCurrentRoomLocked()}.
	 */
	@Test
	public void testIsCurrentRoomLockedFalse() {
		assertFalse(myDefaultMaze.isCurrentRoomLocked());
	}

	/**
	 * Test method for {@link maze.Maze#isCurrentRoomDoorOpen(char)}.
	 */
	@Test
	public void testIsCurrentRoomDoorOpenTrue() {
		final char direction = 'S';
		myDefaultMaze.openDoors(direction);
		assertTrue(myDefaultMaze.isCurrentRoomDoorOpen(direction));
	}

	/**
	 * Test method for {@link maze.Maze#isCurrentRoomDoorOpen(char)}.
	 */
	@Test
	public void testIsCurrentRoomDoorOpenFalse() {
		final char direction = 'S';
		assertFalse(myDefaultMaze.isCurrentRoomDoorOpen(direction));
	}

	/**
	 * Test method for {@link maze.Maze#deleteCurrentRoomDoor(char)}.
	 */
	@Test
	public void testDeleteCurrentRoomDoorSouth() {
		final char direction = 'S';
		myDefaultMaze.deleteCurrentRoomDoor(direction);
		final Set<Character> expected = new HashSet<>();
		expected.add('E');
		assertEquals(expected, myDefaultMaze.getCurrentAvailableDoors());
	}

	/**
	 * Test method for {@link maze.Maze#deleteCurrentRoomDoor(char)}.
	 */
	@Test
	public void testDeleteCurrentRoomDoorEast() {
		final char direction = 'E';
		myDefaultMaze.deleteCurrentRoomDoor(direction);
		final Set<Character> expected = new HashSet<>();
		expected.add('S');
		assertEquals(expected, myDefaultMaze.getCurrentAvailableDoors());
	}

	/**
	 * Test method for {@link maze.Maze#deleteCurrentRoomDoor(char)}.
	 */
	@Test
	public void testDeleteCurrentRoomDoorNorth() {
		final int row = 2;
		final int col = 2;
		final char direction = 'N';
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		myDefaultMaze.deleteCurrentRoomDoor(direction);
		final Set<Character> expected = new HashSet<>();
		expected.add('E');
		expected.add('S');
		expected.add('W');
		assertEquals(expected, myDefaultMaze.getCurrentAvailableDoors());
	}

	/**
	 * Test method for {@link maze.Maze#deleteCurrentRoomDoor(char)}.
	 */
	@Test
	public void testDeleteCurrentRoomDoorWest() {
		final int row = 2;
		final int col = 2;
		final char direction = 'W';
		myDefaultMaze.setLocation(row, col, myDefaultMaze.getRowIndex(), myDefaultMaze.getColIndex());
		myDefaultMaze.deleteCurrentRoomDoor(direction);
		final Set<Character> expected = new HashSet<>();
		expected.add('E');
		expected.add('S');
		expected.add('N');
		assertEquals(expected, myDefaultMaze.getCurrentAvailableDoors());
	}

	/**
	 * Test method for {@link maze.Maze#getRowIndex()}.
	 */
	@Test
	public void testGetRowIndex() {
		final int row = 0;
		assertEquals(row, myDefaultMaze.getRowIndex());
	}

	/**
	 * Test method for {@link maze.Maze#getColIndex()}.
	 */
	@Test
	public void testGetColIndex() {
		final int col = 0;
		assertEquals(col, myDefaultMaze.getColIndex());
	}

	/**
	 * Test method for {@link maze.Maze#getCurrentAvailableDoors()}.
	 */
	@Test
	public void testGetCurrentAvailableDoors() {
		final Set<Character> expected = new HashSet<>(List.of('S', 'E'));
		assertEquals(expected, myDefaultMaze.getCurrentAvailableDoors());
	}

	/**
	 * Test method for {@link maze.Maze#getDisplayMaze()}
	 */
	@Test
	public void testGetDisplayMaze() {
		final char[][] expected = new char[][] { { '*', 'X', 'X' }, { 'X', 'X', 'X' }, { 'X', 'X', 'X' } };
		assertArrayEquals(expected, myCustomeMaze.getDisplayMaze());
	}

	/**
	 * Test method for {@link maze.Maze#toString()}.
	 */
	@Test
	public void testToString() {
		final char[][] expected = new char[][] { { '*', 'X', 'X' }, { 'X', 'X', 'X' }, { 'X', 'X', 'X' } };
		final StringBuilder str = new StringBuilder();
		for (final char[] ch : expected) {
			str.append(Arrays.toString(ch)).append("\n");
		}
		assertEquals(str.toString(), myCustomeMaze.toString());
	}

}
