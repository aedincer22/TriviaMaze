package triviaMaze;

import java.util.Scanner;

public class roomExample {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Room room = new Room() ;
		int door = 2;
		boolean doorAccss = false;
		boolean roomAccss = true;
		
		System.out.println("what door do you want to select? 1 or 2 ");
		int selection = scan.nextInt();
			if(doorAccss ==  false) {
				room.unlock(doorAccss, selection); 
				System.out.println("the door is now unlocked");
				System.out.println("what is the capital of washington? ");
				String answer = scan.next();
				if(answer.equals("Olympia") ||answer.equals("olympia")) {
					System.out.println("correct!! now to next stage");
				}
				else {
					System.out.println("incorrect, sorry game over");
					room.lock(doorAccss, selection);
					roomAccss = false;
				}
				//maybe have a while loop that says if the room access and all door access are false than close the game
			}
		
	}
}
