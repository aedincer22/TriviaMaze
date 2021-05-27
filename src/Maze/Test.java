package Maze;
import Question.Question;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Test {
	static Scanner sc  = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//DO some ground work before the game
		
		//User chooses to play the game
		startGame();
		Maze maze = new Maze();
		
		outer:while (!maze.isLastRoom()) {
			
			Room currRoom = maze.getCurrentRoom();
			Map<Character, String> availableDoorChoices = getCurrentDoorChoices(maze);
			char userChoice = getUserChoice(availableDoorChoices, maze);
			if (userChoice == 'q') break;
			
			while (!currRoom.isLocked()) {
				Question q = Question.createRandomQuestion();
				System.out.println(q);
				System.out.println(q.getMyAnswer());
				String userAns = sc.next().toLowerCase();
				if (userAns.equals(q.getMyAnswer().toLowerCase())) {
					System.out.println("Correct answer!");
					maze.move(userChoice);
					System.out.println("You have made progress. Would you like to save the game?");
					String  saveGame = sc.next().toLowerCase();
					if(saveGame.equals("yes")) {
						saveGame(currRoom);
						System.out.println("saved the game");
					}
					break;
				} else {
					System.out.println("Wrong answer!");
					currRoom.lockDoor();
					availableDoorChoices.remove(userChoice);
					if (!availableDoorChoices.isEmpty())
						userChoice = getUserChoice(availableDoorChoices, maze);
					if (userChoice == 'q') break outer;
				}
			}
			if (currRoom.isLocked()) break;
		}
		
		if (maze.isLastRoom()) { 
			System.out.println("You won");	
		}else { 
			System.out.println("you lost");
		}
		Question.close();
	}
	
	public static char getUserChoice(Map<Character, String> availableChoices, Maze maze) {
				
		System.out.println("You are curently in " + maze.getSouthIndex() + " row and "
				+ maze.getEastIndex()+" column." );
		
		System.out.println("Please select one of the given door(s):");
		
		
		for (char ch : availableChoices.keySet()) {
			System.out.println(availableChoices.get(ch));
		}
		//setting user choice as an invalid choice initially
		char userChoice = Character.toLowerCase(sc.next().charAt(0));
		
		while (userChoice != 'q'&&!availableChoices.containsKey(userChoice)) {
			System.out.println("Invalid Input, please try again or press 'Q' to Quit");
			System.out.println("Please select one of the given door(s):");
			for (char ch : availableChoices.keySet()) {
				System.out.println(availableChoices.get(ch));
			}
			userChoice = Character.toLowerCase(sc.next().charAt(0));
		}
		return userChoice;
		
	}
	
	public static Map<Character, String> getCurrentDoorChoices(Maze maze) {
		Map<Character, String> availableChoices = new HashMap<>();
		if (maze.canMoveEast()) availableChoices.put('e', "Press 'E' for Door-East");
		if (maze.canMoveSouth()) availableChoices.put('s', "Press 'S' for Door-South");
		return availableChoices;
	}

	public static void startGame() {
		System.out.println("Welcome User to the TRIVIA MAZE!");
		System.out.println();
		System.out.println("Please select an option: ");
		System.out.println(" New Game (select 1)      Load Game (select 2)      Help Screen (select 3)");
		int answer = sc.nextInt();
		
		if(answer == 1) {
			//start a new game
			System.out.println("New Game has been selected");
		}
		else if (answer == 2) {
			//Load game
			System.out.println("Loading prevoius Game");
			loadGame();
		}
		else if(answer == 3) {
			//open Help Screen
			help();
			startGame();
		}
		else {
			System.out.println("You have enter invalid input.");
		}
	}
	
	public static void saveGame(Room currRoom) {
	      try {
	          FileOutputStream fileOut =
	          new FileOutputStream("C:\\classes\\tcss360\\TriviaMaze\\src\\Maze.ser");
	          ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          out.writeObject(currRoom);
	          out.close();
	          fileOut.close();
	          System.out.printf("Serialized data is saved in Maze.ser");
	       } catch (IOException i) {
	          i.printStackTrace();
	       }
	}
	
	public static void loadGame() {
		Room e = null;
	      try {
	          FileInputStream fileIn = new FileInputStream("C:\\classes\\tcss360\\TriviaMaze\\src\\Maze.ser");
	          ObjectInputStream in = new ObjectInputStream(fileIn);
	          e = (Room) in.readObject();
	          in.close();
	          fileIn.close();
	       } catch (IOException i) {
	          i.printStackTrace();
	          return;
	       } catch (ClassNotFoundException c) {
	          System.out.println("Room class not found");
	          c.printStackTrace();
	          return;
	       }
	}
	
	public static void help() {
		System.out.println("Welcome to the Help Screen!");
		System.out.println("//////////////////////////Intro to Trivia MAZE//////////////////////////////");
		System.out.println("Hello new User, if you are reading this you selected the help option");
		System.out.println("In order to win the game, pass through each room and find the exit...");
		System.out.println("However each door is locked in order to open them you must answer each");
		System.out.println("question correctly. The question vary from multiple choice,true/false ");
		System.out.println("and fill in the blanks. you will have two chance in each room to answer");
		System.out.println("the question correctly, otherwise the room locks permanetly and game Over.");
		System.out.println();
		System.out.println("//////////////////////////How to play//////////////////////////////");
		System.out.println("select new game or load game, once selected follow the prompts inorder");
		System.out.println("to complete the maze. chose between South door or East door in each room");
		System.out.println("Every door has random question, continue or save the progress of the game");
		System.out.println("Make sure to save the game before leaving or your data wont be save :(");
		System.out.println("//////////////////////////   Cheats   //////////////////////////////");
	}
}
