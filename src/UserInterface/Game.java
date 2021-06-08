/*
 * Game Class
 * Version Spring 2021
 * 06/08/2021 
 */
package UserInterface;

import Maze.Maze;
//import question.*;
import Question.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;
	static Scanner sc = new Scanner(System.in);
	static int moveCounter;

	/**
	 * Prints the location of the player in the maze, gets the player input
	 * direction to move in the maze or if player selected menu screen
	 * 
	 * @param Maze
	 * @return Char: players directions or menu options
	 */
	public static char getUserChoice(Maze maze) {
		// print the details
		UserInterface.printTheCurrentMazeInfo(maze);
		// get a valid input from the user
		char userChoice = UserInterface.getUserDirection(maze);
		return userChoice;
	}

	/**
	 * Creates a new game, prints the maze display, prints the question associated
	 * to each room tracks players movement, allows player to call menu screen,
	 * cheats, or answer question.
	 * 
	 * @param Maze
	 */
	public static void newGame(Maze maze) {
		moveCounter = 0;
		while (!maze.isLastRoom() && !maze.isCurrentRoomLocked()) {
			if (moveCounter == 15)
				break;
			if (moveCounter == 10) {
				System.out.println("!!!!!Warning!!!!!");
				System.out.println("   5 moves left  ");
				System.out.println();
			}
			int answer = 0;
			char userSelection = getUserChoice(maze);
			if (userSelection == 'Q')
				break;
			while (userSelection == '+' || userSelection == '#' || userSelection == '?') {

				if (userSelection == '+') {
					if (menu(maze) == false) {
						answer = 5;
						break;
					}
					userSelection = getUserChoice(maze);
				}
				if (userSelection == '#') {
					cheat1(maze);
					userSelection = getUserChoice(maze);
				}
				if (userSelection == '?') {
					cheat2();
					userSelection = getUserChoice(maze);
				}
			}
			// checks if user select menu or cheats, breaks if user enters quit game
			if (answer == 5)
				break;
			if (!maze.isCurrentRoomDoorOpen(userSelection)) {
				// ask a question and compare answers
				Question question = QuestionFactory.createRandomQuestion();
				System.out.println(question);
				// get the answer
				System.out.println(question.getAnswer());
				String userAnswer = UserInterface.getUserAnswer();
				// check to see if user answer menu or cheat as answer
				while (userAnswer.equals("+") || userAnswer.equals("?")) {

					if (userAnswer.equals("+")) {
						if (menu(maze) == false) {
							answer = 5;
							break;
						}
						System.out.println(maze);
						System.out.println(question);
						// get the answer
						System.out.println(question.getAnswer());
						userAnswer = UserInterface.getUserAnswer();
					}

					if (userAnswer.equals("?")) {
						cheat2();
						System.out.println(maze);
						System.out.println(question);
						// get the answer
						System.out.println(question.getAnswer());
						userAnswer = UserInterface.getUserAnswer();
					}
				}
				// check to see if user enters cheat as answer
				if (userAnswer.equals("#")) {
					cheat1(maze);
					userSelection = getUserChoice(maze);
					System.out.println(question);
					// get the answer
					System.out.println(question.getAnswer());
					userAnswer = UserInterface.getUserAnswer();
				}
				if (answer == 5)
					break;
				// if answer is true then move to the next room
				if (userAnswer.equals(question.getAnswer().toLowerCase())) {
					maze.openDoors(userSelection);
					maze.move(userSelection);
					moveCounter++;
				} else {
					maze.deleteCurrentRoomDoor(userSelection);
				}

			} else {
				maze.move(userSelection);
				moveCounter++;
			}
		}
		UserInterface.printResult(maze.isLastRoom());
	}

	/**
	 * Starts the game, prints the home page information Allows players to select
	 * the option of play a new game, load a previous game, help screen, or exit
	 */
	public static void startGame() {
		UserInterface.printHomePage();
		final String homePageInput = UserInterface.getInputFromHomePage();

		if (homePageInput.equals("1")) {
			// start a new game
			Maze maze = new Maze();
			System.out.println("New Game has been selected");
			newGame(maze);
		} else if (homePageInput.equals("2")) {
			// Load game
			System.out.println("Loading prevoius Game");
			newGame(loadGame());
		} else if (homePageInput.equals("3")) {
			// open Help Screen
			UserInterface.printHelpScreen();
			startGame();
		} else if (homePageInput.equals("4")) {
			// open Help Screen
		} else {
			System.out.println("You have enter invalid input.");
		}
	}

	/**
	 * Saves the state of the maze object to a file
	 * 
	 * @param Maze
	 */
	public static void saveGame(Maze maze) {
		try {
			FileOutputStream fileOut = new FileOutputStream(UserInterface.getFileName() + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(maze);
			out.close();
			fileOut.close();
			System.out.println("Game Was Saved");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * Loads the file which the maze object was saved
	 * 
	 * @return Maze: saved maze class
	 */
	public static Maze loadGame() {
		Maze maze = null;
		try {
			FileInputStream fileIn = new FileInputStream(UserInterface.getFileName() + ".ser");
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

	/**
	 * Opens a menu screen with option to save game, return to home page, help
	 * screen, or exit game
	 * 
	 * @param Maze
	 * @return Boolean: false if player wants to exit game
	 */
	public static boolean menu(Maze maze) {
		UserInterface.printMainMenu();
		final String mainMenuInput = UserInterface.getInputFromMainMenu();

		if (mainMenuInput.equals("1")) {
			saveGame(maze);
			return true;
		} else if (mainMenuInput.equals("2")) {
			startGame();
			return true;
		} else if (mainMenuInput.equals("3")) {
			return true;
		} else if (mainMenuInput.equals("4")) {
			UserInterface.printHelpScreen();
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Sets room location to one room away from last room
	 * 
	 * @param Maze
	 * @return Maze: updated maze location
	 */
	public static Maze cheat1(Maze maze) {
		System.out.println("Cheat has been activated, last room granted");
		maze.setLocation(2, 3, maze.getRowIndex(), maze.getColIndex());
		return maze;
	}

	/**
	 * removes 2 count from moveCounter, Allows player more chances
	 */
	public static void cheat2() {
		System.out.println("Cheat has been activated, +2 moves will be awarded");
		moveCounter--;
		moveCounter--;
	}

}
