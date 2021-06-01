package Maze;

import java.io.Serializable;
import java.util.*;

public class Maze implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Room array which holds all the rooms in the maze.
	 */
	private final Room [][]  myRooms;
	
	/**
	 * The row index, which keeps track of the current row.
	 */
	private int myRowIndex;
	
	/**
	 * The column index,  which keeps track of the current column.
	 */
	private int myColIndex;
	
	/**
	 * Displays the current state of the maze.
	 * '*' is for the current room the user is in
	 * 'X' is for the rooms not yet visited
	 * 'O' is for the rooms visited
	 */
	private char [][] myDisplayMaze;
	
	/**
	 * The default rows, if not mentioned is 4.
	 */
	private final static int  DEFAULT_ROWS = 4;
	
	/**
	 * The default column, if not mentioned is 4.
	 */
	private final static int  DEFAULT_COLS = 4;
	

	/**
	 * Parameterless constructor which makes a default 4 x 4 maze.
	 */
	public Maze() {
		this (DEFAULT_ROWS, DEFAULT_COLS);
	}
	
	/**
	 * Parameterized constructor which makes a maze  
	 * based on the rows and columns passed.  
	 * @param rows: Number of rows in the maze
	 * @param cols: Number of Columns in the maze
	 * @exception throws IllegalArgumentException if row and col are not equal or if they are negative.
	 */
	public Maze(final int rows, final int cols) {
		
		if (rows < 0 || cols < 0 || rows != cols) {
			throw new IllegalArgumentException("The maze dimension have to be positive and equal");
		}
		myRooms = buildMaze(rows, cols);
		myRowIndex = 0;
		myColIndex = 0; 
		myDisplayMaze = buildDisplayMaze(rows, cols);
	}

	/**
	 * Fill maze with rooms and the amount of doors
	 * needed in each room based on the room location
	 * @param rows: Number of rows in the maze.
	 * @param cols: Number of columns in the maze.
	 * @return 2-D array which holds rooms
	 */
	private Room[][] buildMaze(final int rows, final int cols) {
		
		final int size = rows;
		final Room [][] rooms = new Room[rows][cols];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				//first row
				if (i == 0) {
					if (j == 0) {
						rooms[i][j] = new Room (new char[] {'S', 'E'});
					} else if (j < size-1) {
						rooms[i][j] = new Room(new char[] {'W','S','E'});
					} else {
						rooms[i][j] = new Room(new char[] {'W','S'});
					}
				} else if (j == 0 && i > 0) {//first column
					if(i < size-1) {
						rooms[i][j] = new Room(new char[] {'N','E','S'});
					} else {
						rooms[i][j] = new Room(new char[] {'N','E'});
					}
				} else if (j > 0 && i == size - 1) {//Last row
					if (j < size - 1) {
					rooms[i][j] = new Room(new char[] {'W','N','E'});
					} else {
						rooms[i][j] = new Room (new char[] {'N','W'});
					}
				} else if (j == size - 1 && (i > 0 && i < size - 1)) {//Last column
					rooms[i][j] = new Room (new char[] {'N','S','W'});
				} else {
					rooms[i][j] = new Room(new char[] {'N','S','E','W'});
				}
			}
		}
		//The last room does not have question doors 
		rooms[size-1][size-1] = new Room(new char[] {});
		return rooms;
	}
	
	/**
	 * Build the display maze for the actual maze. 
	 * @param rows
	 * @param cols
	 * @return 2-D array
	 */
	private char [][] buildDisplayMaze(int rows, int cols){
		
		final char [][] displayArr = new char[rows][cols];
		
		for (char [] arr : displayArr) {
			Arrays.fill(arr,'X');
		}
		displayArr[0][0] = '*';
		return displayArr;		
	}

	/**
	 * In the maze move towards the direction
	 * passed in the argument.
	 * @param theDirection
	 */
	public void move(final char theDirection) {
		//set the previous room open --> 'O'
		myDisplayMaze[myRowIndex][myColIndex] = 'O';
		
		char ch = Character.toUpperCase(theDirection);
		if (ch == 'S') {
			myRowIndex++;
		} else if (ch == 'E') {
			myColIndex++;
		} else if (ch == 'W') {
			myColIndex--;
		} else if (ch == 'N'){
			myRowIndex--;
		} else {
			throw new IllegalArgumentException("Can not move in the direction provided.");
		}
		//set the current room --> '*'
		myDisplayMaze[myRowIndex][myColIndex] = '*';	
	}
		
	/**
	 * When the user answers something right, the door selected by the
	 * user needs to be opened. There are two doors which get affected
	 * by this. The door of the current room and the door of the room,
	 * which user is moving to. 
	 * Example: The user chooses door 'S' and answers correctly, then the 
	 * user moves South (i.e. open door 'S') and we also open the 'N' of the
	 * next room. This allows user to move back and forth.
	 * @param theChar
	 */
	public void openDoors(char theChar) {
		theChar = Character.toUpperCase(theChar);
		myRooms[myRowIndex][myColIndex].openDoor(theChar);
		
		if (theChar == 'W') {
			myRooms[myRowIndex][myColIndex - 1].openDoor('E');
		} else if (theChar == 'S') {
			myRooms[myRowIndex + 1][myColIndex].openDoor('N');
		} else if (theChar == 'E') {
			myRooms[myRowIndex][myColIndex + 1].openDoor('W');
		} else if (theChar == 'N') {
			myRooms[myRowIndex - 1][myColIndex].openDoor('S');
		}
	}
	
	/**
	 * Check is the user is in the last room.
	 * @return boolean
	 */
	public boolean isLastRoom() {
		final int len = myRooms.length;
		return myRowIndex == len - 1 && myColIndex == len - 1;
	}
	
	/**
	 * Check if the current room is locked or not.
	 * @return boolean
	 */
	public boolean isCurrentRoomLocked() {
		return myRooms[myRowIndex][myColIndex].isLocked();
	}

	/**
	 * Checks if the given door in the current room
	 * is open or closed.
	 * @param ch: Door name
	 * @return boolean 
	 */
	public boolean isCurrentRoomDoorOpen(char ch) {
		return myRooms[myRowIndex][myColIndex].isDoorOpen(ch);
	}
	
	/**
	 * Delete the given door in the current room.
	 * @param ch: Door name
	 */
	public void deleteCurrentRoomDoor(char ch) {
		 myRooms[myRowIndex][myColIndex].deleteDoor(ch);
	}
	
	/**
	 * Returns the current row index.
	 * @return int: row index
	 */
	public int getRowIndex() {
		return myRowIndex;
	}
	
	/**
	 * Returns the current column index.
	 * @return int: column index
	 */
	public int getColIndex() {
		return myColIndex;
	}
	
	/**
	 * Returns the set of available doors in the current room
	 * @return Set <Character>: Available Doors
	 */
	public Set<Character> getCurrentAvailableDoors(){
		return myRooms[myRowIndex][myColIndex].getAvailableDoors();
	}
	
	/**
	 * To string for the maze class.
	 */
	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder();
		for (char [] ch : myDisplayMaze) {
			str.append(Arrays.toString(ch)).append("\n");
		}
		return str.toString();
	}
	
//	public boolean isNextRoomSolved(char theChar) {
//	
//	theChar = Character.toUpperCase(theChar);
//	if(theChar == 'E') {
//		return myRooms[myRowIndex][myColIndex + 1].isSolved();
//	} else if (theChar == 'S') {
//		return myRooms[myRowIndex + 1][myColIndex].isSolved();
//	} else if (theChar == 'W') {
//		return myRooms[myRowIndex][myColIndex - 1].isSolved();
//	} else if (theChar == 'N') {
//		return myRooms[myRowIndex - 1][myColIndex].isSolved();
//	} else {
//		throw new IllegalArgumentException();
//	}
//	
//}
}
