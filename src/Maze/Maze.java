package Maze;

import java.util.Arrays;

public class Maze {

	private Room [][]  myRooms;
	private int rowIndex;
	private int colIndex;
	private char [][] displayMaze;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Maze maze = new Maze(4,4);
		System.out.println(maze);

	}

	public Maze() {
		this (4,4);
	}

	public Maze(final int rows, final int cols) {
		if (rows < 0 || cols < 0 || rows != cols) {
			throw new IllegalArgumentException("The maze dimension have to be positive and equal");
		}
		myRooms = buildMaze(rows, cols);
		rowIndex = 0;
		colIndex = 0; 
		displayMaze = buildDisplayMaze(rows, cols);
	}

	private Room[][] buildMaze(int rows, int cols) {
		//Build the maze with with one or two rooms as required.
		int size = rows;
		Room [][] rooms = new Room[rows][cols];

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
		rooms[size-1][size-1] = new Room(new char[] {});
		return rooms;
	}
	
	private char [][] buildDisplayMaze(int rows, int cols){
		
		final char [][] displayArr = new char[rows][cols];
		
		for (char [] arr : displayArr) {
			Arrays.fill(arr,'X');
		}
		displayArr[0][0] = '*';
		return displayArr;		
	}
//	
//	public boolean canMoveSouth() {
//		return southIndex + 1 < myRooms.length;
//	}
//
//	public boolean canMoveEast() {
//		return eastIndex + 1 < myRooms.length;
//	}

	public void move(final char theDirection) {
		//set the previous room open --> 'O'
		displayMaze[rowIndex][colIndex] = 'O';
		
		char ch = Character.toUpperCase(theDirection);
		if (ch == 'S') {
			rowIndex++;
		} else if (ch == 'E') {
			colIndex++;
		} else if (ch == 'W') {
			colIndex--;
		} else if (ch == 'N'){
			rowIndex--;
		} else {
			throw new IllegalArgumentException();
		}
		//set the current room --> '*'
		displayMaze[rowIndex][colIndex] = '*';	
	}
	
	public boolean isNextRoomSolved(char theChar) {
		
		theChar = Character.toUpperCase(theChar);
		if(theChar == 'E') {
			return myRooms[rowIndex][colIndex + 1].isSolved();
		} else if (theChar == 'S') {
			return myRooms[rowIndex + 1][colIndex].isSolved();
		} else if (theChar == 'W') {
			return myRooms[rowIndex][colIndex - 1].isSolved();
		} else if (theChar == 'N') {
			return myRooms[rowIndex - 1][colIndex].isSolved();
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	public void openDoors(char theChar) {
		theChar = Character.toUpperCase(theChar);
		myRooms[rowIndex][colIndex].openDoor(theChar);
		
		if (theChar == 'W') {
			myRooms[rowIndex][colIndex - 1].openDoor('E');
		} else if (theChar == 'S') {
			myRooms[rowIndex + 1][colIndex].openDoor('N');
		} else if (theChar == 'E') {
			myRooms[rowIndex][colIndex + 1].openDoor('W');
		} else if (theChar == 'N') {
			myRooms[rowIndex - 1][colIndex].openDoor('S');
		}
	}
	
	public char[][] getDisplayMaze(){
		return displayMaze.clone();
	}
	
	public Room getCurrentRoom() {
		return myRooms[rowIndex][colIndex];
	}
	
	public boolean isLastRoom() {
		final int len = myRooms.length;
		return rowIndex == len - 1 && colIndex == len - 1;
	}
	
	public String toString() {
		return Arrays.deepToString(myRooms);
	}

	

	public int getRowIndex() {
		return rowIndex;
	}
	
	public int getColIndex() {
		return colIndex;
	}
	
}
