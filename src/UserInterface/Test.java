package UserInterface;
import Question.Question;
import Maze.Maze;

import java.io.File;
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
	
	
	//test
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//DO some ground work before the game
		
		//User chooses to play the game
		 try
	        {
	            String filePath =  "Jeopardy-theme-song.wav";
	            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(filePath);
	              
	            audioPlayer.play();
	        }
	            catch (Exception ex) 
	            {
	                System.out.println("Error with playing sound.");
	                ex.printStackTrace();
	              
	              }
		startGame();
		
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
		

		while (userChoice != 'Q' && !availableChoices.contains(userChoice) && userChoice != '*' && userChoice != 'C') {
			System.out.println("Invalid Input, please try again or press 'Q' to Quit");
			System.out.println("Please select one of the given door(s):");
			System.out.println(availableChoices);
			userChoice = Character.toUpperCase(sc.next().charAt(0));
		}
		return userChoice;
	}

	public static void newGame(Maze maze) {
		//Maze maze = new Maze();
		System.out.println(maze);
		while (!maze.isLastRoom() && !maze.isCurrentRoomLocked()) {
			char userSelection = getUserChoice(maze);
			if (userSelection == 'Q') break;
			if(userSelection == '*' ) {
				if(menu(maze) == false) break;
				System.out.println(maze);
				userSelection = getUserChoice(maze);
			}
			
			if(userSelection == 'C') cheats(maze);
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
				}
				else {
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
			
			try
	        {
	            String filePath =  "Sad_Trombone-Joe_Lamb-665429450.wav";
	            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(filePath);
	              
	            audioPlayer.play();
	        }
	            catch (Exception ex) 
	            {
	                System.out.println("Error with playing sound.");
	                ex.printStackTrace();
	              
	              }
			System.out.println("You Lost!");
		}
	}

		public static void startGame() {
		System.out.println("Welcome User to the TRIVIA MAZE!");
		System.out.println("Press '*' for menu screen in Game");
		System.out.println();
		System.out.println("Please select an option: ");
		System.out.println(" New Game (select 1)      Load Game (select 2)      Help Screen (select 3)");
		String answer = sc.next();

		if(answer.equals("1")) {
			//start a new game
			Maze maze = new Maze();
			System.out.println("New Game has been selected");
			newGame(maze);
		}
		else if (answer.equals("2")) {
			//Load game
			System.out.println("Loading prevoius Game");
			newGame(loadGame());
		}
		else if(answer.equals("3")) {
			//open Help Screen
			help();
			startGame();
		}
		else {
			System.out.println("You have enter invalid input.");
		}
		}

		public static void saveGame(Maze maze) {
		System.out.println("Name for the file to be save: ");
		String filename = sc.next();
		  try {
		      FileOutputStream fileOut =
		      new FileOutputStream(filename+ ".ser");
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
		System.out.println("Name for the file to open: ");
		String filename = sc.next();
		Maze maze = null;
		  try {
		      FileInputStream fileIn = new FileInputStream(filename+ ".ser");
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
			String option;
			System.out.println("Menu Screen");
			System.out.println("Save Game (1)   return to Start Screen (2)   Exit Game(3)");
			option = sc.next();
			while(!option.equals("1") && !option.equals("2") && !option.equals("3")) {
				System.out.println("invalid input");
				System.out.println("Save Game (1)   return to Start Screen (2)   Exit Game(3)");
				option = sc.next();
			
			}	
			if(option.equals("1")) {
				saveGame(maze);
				return true;
			}
			else if(option.equals("2")) {
				startGame();
				return true;
			}
			else if(option.equals("3")) {
				return false;
			}
			return true;
		}
		
		public static Maze cheats(Maze maze) {
			System.out.println("Hmmm.... I hear you Something up yourselves?");
			String cheat = sc.next();
			if(cheat.equals("^<>^?")) {
				System.out.println("Congrats, you can move 2 doors");
				char selection = getUserChoice(maze);
				maze.openDoors(selection);
            	maze.move(selection);
            	maze.move(selection);
			}
			return maze;
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
		System.out.println("////////////////////////////How to play/////////////////////////////////////");
		System.out.println("select new game or load game, once selected follow the prompts inorder");
		System.out.println("to complete the maze. chose between South door or East door in each room");
		System.out.println("Every door has random question, continue or save the progress of the game");
		System.out.println("Make sure to save the game before leaving or your data wont be save :(");
		System.out.println("//////////////////////////   Cheats   /////////////////////////////////////");
		System.out.println(" Cheat 1 -------------------- PowerUp ---------------- moves 2 room");
		System.out.println(" Cheat 2 -------------------- CheatCode -------------- moves to end of row");
		System.out.println(" Cheat 3 -------------------- GoldenTicket ----------- moves to last room");

		}
}
