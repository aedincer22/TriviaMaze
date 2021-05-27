package Maze;

import java.util.Arrays;

public class Maze{

	public Room [][]  myRooms;
	private int southIndex;
	private int eastIndex;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Maze maze = new Maze(2,2);
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
		southIndex = 0;
		eastIndex = 0; 
	}

	private Room[][] buildMaze(int rows, int cols) {
		//Build the maze with with one or two rooms as required.

		Room [][] rooms = new Room[rows][cols];

		for (int i = 0; i < rows; i++) {
			Room[] row = new Room[cols];
			for (int j = 0; j < cols; j++) {
				if (j == cols-1 || i == rows-1) {
					row[j] = new Room(1);
				} else {
					row [j] = new Room(2);
				}
			}
			rooms[i] = row;
		}
		rooms[rows-1][cols-1] = new Room(0);
		return rooms;
	}

	public boolean canMoveSouth() {
		return southIndex + 1 < myRooms.length;
	}

	public boolean canMoveEast() {
		return eastIndex + 1 < myRooms.length;
	}

	public void move(final char theDirection) {
		char ch = Character.toLowerCase(theDirection);
		if (ch == 's' && canMoveSouth()) {
			this.southIndex++;
		}
		if (ch == 'e' && canMoveEast()) {
			this.eastIndex++;
		}
	}
	
	public Room getCurrentRoom() {
		return myRooms[southIndex][eastIndex];
	}
	
	public boolean isLastRoom() {
		final int len = myRooms.length;
		return southIndex == len - 1 && eastIndex == len - 1;
	}
	
	public String toString() {
		return Arrays.deepToString(myRooms);
	}


	public int getSouthIndex() {
		return southIndex;
	}
	
	public int getEastIndex() {
		return eastIndex;
	}
}
