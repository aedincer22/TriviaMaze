package UserInterface;

import java.util.*;

import Maze.Maze;

public class UserInterface {

	private static final Scanner SC = new Scanner(System.in);
	private static final char MAIN_MENU_OPTION = '+';
	private static final char CHEAT_ONE = '#';
	private static final char CHEAT_TWO = '?';
	private static final char QUIT_OPTION = 'Q';
<<<<<<< HEAD
	
=======

>>>>>>> 2a6505307658b53d431616beb351423eb9b7d583
	public static void main(String[] args) {
		// test
		// TODO Auto-generated method stub
		printMainMenu();
		String input = getInputFromHomePage();
		System.out.println(input);
		
	}

	/**
	 * Prints the home page for the Trivia Maze.
	 */
	public static void printHomePage() {
		System.out.println("Welcome User to the TRIVIA MAZE!");
		System.out.println("Remember during the game: ");
		System.out.println("Press '+' for menu screen in the game.");
		System.out.println("'O' represents the rooms you have unlocked.");
		System.out.println("'X' represents the room that are locked.");
		System.out.println("'*' represents the room you are in.");
		System.out.println();
		System.out.println("Please select an option: ");
		System.out.println(
				" New Game (select 1)      Load Game (select 2)      Help Screen (select 3)      Quit(select 4)");
	}

	/**
	 * Gets the valid user input when the user is on "Home Page." Valid inputs at
	 * home page are --> [1,2,3,4]
	 * 
	 * @return String: user input
	 */
	public static String getInputFromHomePage() {
		String homePageInput = SC.next();
		final Set<String> homePageChoices = new HashSet<>(Arrays.asList("1", "2", "3", "4"));

		while (!homePageChoices.contains(homePageInput)) {
			System.out.println("Invalid input, try again.");
			homePageInput = SC.next();
		}
		return homePageInput;
	}

	/**
	 * Print the main menu.
	 */
	public static void printMainMenu() {
		System.out.println("--------- Main Menu ---------");
		System.out.println("Save Game (select 1)");
		System.out.println("Go to Home Page (select 2)");
		System.out.println("Close Main Menu (select 3)");
		System.out.println("Help (select 4)");
		System.out.println("Quit Game (select 5)");
		System.out.println("-----------------------------");
	}

	/**
	 * Get a valid input from the main menu. Currently the valid inputs are -->
	 * [1,2,3,4,5]
	 * 
	 * @return String: user input
	 */
	public static String getInputFromMainMenu() {
		String mainMenuInput = SC.next();
		// System.out.println(mainMenuInput);
		final Set<String> mainMenuChoices = new HashSet<>(Arrays.asList("1", "2", "3", "4", "5"));

		while (!mainMenuChoices.contains(mainMenuInput)) {
			System.out.println("Invalid input, try again.");
			mainMenuInput = SC.next();
		}
		return mainMenuInput;
	}

	/**
	 * Prints the state of the maze and the current user.
	 * 
	 * @param maze
	 */
	public static void printTheCurrentMazeInfo(final Maze maze) {
		System.out.println(maze);
		System.out.println("You are curently in " + maze.getRowIndex() + " row and " + maze.getColIndex() + " column.");
		System.out.println("Please select one of the given door(s):");
		System.out.println(maze.getCurrentAvailableDoors());
	}

	/**
	 * Returns a valid input from the user. A user can enter either a given
	 * direction or can choose to quit the game or choose main menu or can use one
	 * of the valid cheats.
	 * 
	 * @param maze
	 * @return String: User input
	 */
	public static char getUserDirection(final Maze maze) {
		final Set<Character> availableChoices = new HashSet<>();
		for (char ch : maze.getCurrentAvailableDoors()) {
			// convert the character to string
			availableChoices.add(ch);
		}
		availableChoices.add(MAIN_MENU_OPTION);
		availableChoices.add(QUIT_OPTION);
		availableChoices.add(CHEAT_ONE);
		availableChoices.add(CHEAT_TWO);

		char directionInput = Character.toUpperCase(SC.next().charAt(0));
		while (!availableChoices.contains(directionInput)) {
			System.out.println("Invalid input, try again!");
			directionInput = Character.toUpperCase(SC.next().charAt(0));
		}
		return directionInput;
	}

	/**
	 * Returns an Answer from the user
	 * 
	 * @return String: Answer
	 */
	public static String getUserAnswer() {
		final String answer = SC.next();
		return answer;
	}

	/**
	 * Return the file name entered by the user
	 * 
	 * @return String: Filename
	 */
	public static String getFileName() {
		// System.out.print("Press Q to quit or ");
		System.out.println("Please enter the name of the file: ");
		final String filename = SC.next();
		return filename;
	}

	/**
	 * Prints the final verdict of the game.
	 * 
	 * @param result
	 */
	public static void printResult(final boolean result) {
		if (result) {
			System.out.println("You Won!!");
		} else {
			System.out.println("You Lost!!");
		}
	}

	/**
	 * Prints the help screen.
	 */
	public static void printHelpScreen() {
		System.out.println("Welcome to the Help Screen!");
		System.out.println("//////////////////////////Intro to Trivia MAZE//////////////////////////////");
		System.out.println("Hello new User, if you are reading this you selected the help option");
		System.out.println("In order to win the game, pass through each room and find the exit...");
		System.out.println("However each door is locked in order to open them you must answer each");
		System.out.println("question correctly. The question vary from multiple choice,true/false ");
		System.out.println("and fill in the blanks. you will have two chance in each room to answer");
		System.out.println("the question correctly, otherwise the room locks permanetly and game Over.");
		System.out.println();
		System.out.println("////////////////////////////How to play/////////////////////////////////////");
		System.out.println("select new game or load game, once selected follow the prompts inorder");
		System.out.println("to complete the maze. chose between South door or East door in each room");
		System.out.println("Every door has random question, continue or save the progress of the game");
		System.out.println("Make sure to save the game before leaving or your data wont be save :(");
		System.out.println();
		System.out.println("//////////////////////////   Cheats   /////////////////////////////////////");
		System.out.println(" Cheat -------------------- Lastroom --------- moves to last room");
		System.out.println(" Cheat -------------------- Extra Moves ------ 2 moves added");

		System.out.println();
	}
}
