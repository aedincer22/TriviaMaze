package Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Maze.Maze;
import Maze.Room;

class Maze_Test {
	
	Maze maze = new Maze();
	Room [][] myRoom;

	@Test
	public void testMaze() {
		int rows = 4;
		int coloumn = 4;
		Assertions.assertEquals(4, rows);
		Assertions.assertEquals(4, coloumn);
		Assertions.assertEquals(rows, coloumn);
	}

	@Test
	public void testBuildMaze() {
		int rows = 4;
		int coloumn = 4;
		maze = new Maze(rows,coloumn);
	}
	
	@Test
	public void testIsLastRoom() {
		int rows = 4;
		int coloumn = 4;
		maze = new Maze(rows,coloumn);
		Assertions.assertEquals(false , maze.isLastRoom());
	}
	
	@Test
	public void testToString() {
		Maze  nwMaze = new Maze(1,1);
		assertEquals("[[[]]]", nwMaze.toString());
	}
	
	@Test
	public void testMove() {
		char theDirection = 's';
		 maze.move(theDirection);
	}
	
	@Test
	public void testCanMoveSouth() {
		assertEquals(true , maze.canMoveSouth());
	}
	
	@Test
	public void testCanMoveEast() {
		assertEquals(true , maze.canMoveEast());
	}
	
	@Test
	public void testGetSouthIndex() {
		assertEquals(0, maze.getSouthIndex());
	}
	
	@Test
	public void testGetEastIndex() {
		assertEquals(0, maze.getEastIndex());
	}
//	@Test
//	public void testGetCurrentRoom() {
//		int rows = 2;
//		int coloumn = 2;
//		maze = new Maze(rows,coloumn);
//		myRoom = new Room[true];
//		Assertions.assertEquals(myRoom , maze.getCurrentRoom());
//	}
}
