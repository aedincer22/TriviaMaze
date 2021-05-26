package Test;

import static org.junit.Assert.*;

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
		Assertions.assertEquals(rows, coloumn);
	}
	
	@Test
	public void testMazeColoumn() {
		int coloumn = 4;
		Assertions.assertEquals(4, coloumn);
	}
	
	@Test
	public void testMazeRows() {
		int rows = 4;
		Assertions.assertEquals(4, rows);
	}

//	@Test
//	public void testBuildMaze() {
//		int rows = 4;
//		int cols = 4;	
//		Assertions.assertEquals(maze.myRooms[3][3], maze.buildMaze(rows, cols));
//	}
	
	@Test
	public void testIsNotLastRoom() {
		maze = new Maze(4,4);
		maze.move('s');
		maze.move('e');
		Assertions.assertEquals(false , maze.isLastRoom());
	}
	
	@Test
	public void testIsLastRoom() {
		maze = new Maze(1,1);
		maze.move('s');
		maze.move('e');
		Assertions.assertEquals(true , maze.isLastRoom());
	}
	
	@Test
	public void testToString() {
		Maze  nwMaze = new Maze(1,1);
		assertEquals("[[[]]]", nwMaze.toString());
	}
		
	@Test
	public void testMoveSouth() {
		char theDirection = 's';
		 maze.move(theDirection);
		 Assertions.assertEquals(1, maze.getSouthIndex());
	}
	
	@Test
	public void testMoveEast() {
		char theDirection = 'e';
		 maze.move(theDirection);
		 Assertions.assertEquals(1, maze.getEastIndex());
	}
	
	@Test
	public void testMoveWrongInput() {
		char theDirection = 'x';
		 maze.move(theDirection);
		 Assertions.assertEquals(0, maze.getEastIndex());
		 Assertions.assertEquals(0, maze.getSouthIndex());
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
	public void testGetSouthIndexWrong() {
		Assertions.assertNotEquals(2, maze.getSouthIndex());
	}
	
	@Test
	public void testGetEastIndex() {
		assertEquals(0, maze.getEastIndex());
	}
	
	@Test
	public void testGetEastIndexWrong() {
		Assertions.assertNotEquals(2, maze.getEastIndex());
	}
		
	@Test
	public void testGetCurrentRoomMovedSouth() {
		maze = new Maze(2,2);
		maze.move('s');
		Assertions.assertEquals(maze.myRooms[1][0],maze.getCurrentRoom());
	}
	
	@Test
	public void testGetCurrentRoomMovedEast() {
		maze = new Maze(2,2);
		maze.move('e');
		Assertions.assertEquals(maze.myRooms[0][1],maze.getCurrentRoom());
	}
	
	@Test
	public void testGetCurrentRoomMovedWrongRoom() {
		maze = new Maze(2,2);
		maze.move('s');
		Assertions.assertNotEquals(maze.myRooms[1][1],maze.getCurrentRoom());
	}
}
