package Maze;
import Question.Question;
import java.util.HashSet;
import java.util.*;

public class Test {
	static Scanner sc  = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//DO some ground work before the game
		
		//User chooses to play the game
		
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

}
