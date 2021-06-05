package UserInterface;
import Question.*;
import Maze.Maze;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Test implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Scanner sc  = new Scanner(System.in);
	static int moveCounter;
	//static UserInterface user = new UserInterface();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//DO some ground work before the game
		
		//User chooses to play the game
//		 try
//	        {
//	            String filePath =  "Jeopardy-theme-song.wav";
//	            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(filePath);
//	              
//	            audioPlayer.play();
//	        }
//	            catch (Exception ex) 
//	            {
//	                System.out.println("Error with playing sound.");
//	                ex.printStackTrace();
//	              
//	              }
		startGame();
		
	}
	public static char getUserChoice(Maze maze) {
		//print the details
		UserInterface.printTheCurrentMazeInfo(maze);		
		//get a valid input from the user
		char userChoice = UserInterface.getUserDirection(maze);
			
		return userChoice;
	}

	public static void newGame(Maze maze) {
		moveCounter = 0;
		while (!maze.isLastRoom() && !maze.isCurrentRoomLocked()) {
			if(moveCounter == 15) break;
			if(moveCounter == 10) {
				System.out.println("!!!!!Warning!!!!!");
				System.out.println("5 moves left");
				System.out.println();
			}
			char userSelection = getUserChoice(maze);
			if (userSelection == 'Q') break;
			if(userSelection == '+' ) {
				if(menu(maze) == false) break;
					userSelection = getUserChoice(maze);
			}		
			if(userSelection == '#') {
				cheat1(maze);
				userSelection = getUserChoice(maze);
			}
			if(userSelection == '?') {
				cheat2();
				userSelection = getUserChoice(maze);
			}
			if (!maze.isCurrentRoomDoorOpen(userSelection)) {
				//ask a question and compare answers
				Question question = QuestionFactory.createRandomQuestion();
				if (userSelection == 'Q') break;
				if(userSelection == '+' ) {
					if(menu(maze) == false) break;
						userSelection = getUserChoice(maze);
				}	
				if(userSelection == '#') {
					cheat1(maze);
					userSelection = getUserChoice(maze);
				}
				System.out.println(question);
				//get the answer
				System.out.println(question.getAnswer());
				String userAnswer = sc.next().toLowerCase();
				//if answer is true then move to the next room           
				if (userAnswer.equals(question.getAnswer().toLowerCase())) {
					maze.openDoors(userSelection);
					maze.move(userSelection);
					moveCounter++;
				}
				else {
					maze.deleteCurrentRoomDoor(userSelection);
			     }
				
			} else {
				maze.move(userSelection);
				moveCounter++;
			}
			//System.out.println(maze);
		}
		UserInterface.printResult(maze.isLastRoom());
	}

	public static void startGame() {
		UserInterface.printHomePage();

		if(UserInterface.getInputFromHomePage().equals("1")) {
			//start a new game
			Maze maze = new Maze();
			System.out.println("New Game has been selected");
			newGame(maze);
		}
		else if (UserInterface.getInputFromHomePage().contentEquals("2")) {
			//Load game
			System.out.println("Loading prevoius Game");
			newGame(loadGame());
		}
		else if(UserInterface.getInputFromHomePage().equals("3")) {
			//open Help Screen
			UserInterface.printHelpScreen();
			startGame();
		}
		else if(UserInterface.getInputFromHomePage().equals("4")) {
			//open Help Screen
		}
		else {
			System.out.println("You have enter invalid input.");
		}
	}

	public static void saveGame(Maze maze) {
		try {
			FileOutputStream fileOut =	new FileOutputStream(UserInterface.getFileName() + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(maze);
			out.close();
			fileOut.close();
			System.out.println("Game Was Saved");
		} catch (IOException i) {
			i.printStackTrace();
			}
	}	

	public static Maze loadGame() {
		Maze maze = null;
		try {
			FileInputStream fileIn = new FileInputStream(UserInterface.getFileName()+ ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			maze = (Maze) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return maze;
		} catch (ClassNotFoundException c) {
			System.out.println("Room class not found");
			c.printStackTrace();
			return maze;
		}
		return maze;
	}
		
	public static boolean menu(Maze maze) {
		UserInterface.printMainMenu();
		
		if(UserInterface.getInputFromMainMenu().equals("1")) {
			saveGame(maze);
			return true;
		}
		else if(UserInterface.getInputFromMainMenu().equals("2")) {
			startGame();
			return true;
		}
		else if(UserInterface.getInputFromMainMenu().equals("3")) {
			return true;
		}
		else if(UserInterface.getInputFromMainMenu().equals("4")) {
			UserInterface.printHelpScreen();
		}
		else {
			return false;
		}
		return true;
	}
		
	public static Maze cheat1(Maze maze) {
		System.out.println("Cheat has been activated, last room granted");
		maze.setLocation(2, 3,maze.getRowIndex(), maze.getColIndex());
		return maze;
	}
	public static void cheat2() {
		System.out.println("Cheat has been activated, +2 moves will be awarded");
		moveCounter--;
		moveCounter--;
	}

}
