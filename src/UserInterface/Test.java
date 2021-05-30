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
		
//		outer:while (!maze.isLastRoom()) {
//			displayMaze(maze.getDisplayMaze());
//			Room currRoom = maze.getCurrentRoom();
//			Map<Character, String> availableDoorChoices = getCurrentDoorChoices(maze);
//			char userChoice = getUserChoice(availableDoorChoices, maze);
//			if (userChoice == 'q') break;
//			
//			while (!currRoom.isLocked()) {
//				Question q = Question.createRandomQuestion();
//				System.out.println(q);
//				System.out.println(q.getMyAnswer());
//				String userAns = sc.next().toLowerCase();
//				if (userAns.equals(q.getMyAnswer().toLowerCase())) {
//					System.out.println("Correct answer!");
//					maze.move(userChoice);
//					break;
//				} else {
//					System.out.println("Wrong answer!");
//					currRoom.lockDoor();
//					availableDoorChoices.remove(userChoice);
//					if (!availableDoorChoices.isEmpty())
//						userChoice = getUserChoice(availableDoorChoices, maze);
//					if (userChoice == 'q') break outer;
//				}
//			}
//			System.out.println();
//			if (currRoom.isLocked()) break;
//		}
//		displayMaze(maze.getDisplayMaze());
//		if (maze.isLastRoom()) { 
//			System.out.println("You won");	
//		}else { 
//			System.out.println("you lost");
//		}
//		Question.close();
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
