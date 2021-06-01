package UserInterface;
import Question.Question;
import java.util.HashSet;

import Maze.Maze;

import java.util.*;

public class Test {
	static Scanner sc  = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//DO some ground work before the game
		
		//User chooses to play the game
		Maze maze = new Maze();
		System.out.println(maze);
		while (!maze.isLastRoom() && !maze.isCurrentRoomLocked()) {
			//Room currRoom = maze.getCurrentRoom();
			char userSelection = getUserChoice(maze);
			if (userSelection == 'Q') break;
			if (!maze.isCurrentRoomDoorOpen(userSelection)) {
				//ask a question and compare answers
				Question question = Question.createRandomQuestion();
				System.out.println(question);
				//get the answer
				System.out.println(question.getMyAnswer());
				String userAnswer = sc.next().toLowerCase();
				//if answer is true then move to the next room
				if (userAnswer.equals(question.getMyAnswer().toLowerCase())) {
					maze.openDoors(userSelection);
					maze.move(userSelection);
				} else {
					maze.deleteCurrentRoomDoor(userSelection);
				}
				
			} else {
				maze.move(userSelection);
			}
			System.out.println(maze);
		}
		Question.close();
		if(maze.isLastRoom()) {
			System.out.println("You Won!");
		} else {
			System.out.println("You Lost!");
		}
		
	}
	
	public static char getUserChoice(Maze maze) {
		
		//print the details
		System.out.println("You are curently in " + maze.getRowIndex() + " row and "
				+ maze.getColIndex()+" column." );
		
		System.out.println("Please select one of the given door(s):");
		Set<Character> availableChoices = maze.getCurrentAvailableDoors();
		System.out.println(availableChoices);
		
		//get a valid input from the user
		char userChoice = Character.toUpperCase(sc.next().charAt(0));
		while (userChoice != 'Q' && !availableChoices.contains(userChoice)) {
			System.out.println("Invalid Input, please try again or press 'Q' to Quit");
			System.out.println("Please select one of the given door(s):");
			System.out.println(availableChoices);
			userChoice = Character.toUpperCase(sc.next().charAt(0));
		}
		return userChoice;
	}
//	
//	public static Map<Character, String> getCurrentDoorChoices(Maze maze) {
//		Map<Character, String> availableChoices = new HashMap<>();
//		if (maze.canMoveEast()) availableChoices.put('e', "Press 'E' for Door-East");
//		if (maze.canMoveSouth()) availableChoices.put('s', "Press 'S' for Door-South");
//		return availableChoices;
//	}
//	
//	public static void displayMaze(char [][] displayMaze) {
//		
//		for (char [] ch : displayMaze) {
//			System.out.println(Arrays.toString(ch));
//		}
//	}
}
