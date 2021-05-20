package triviaMaze;

import java.util.Scanner;

public class example {

		public static void main(String [] args) {
			Scanner scan = new Scanner(System.in);
			Room room = new Room();
			int door = room.getNumDoors();
			boolean doorAccss = room.getDoorAccess();
			boolean roomAccss = room.getRoomAccess();
			
			boolean boolArray[][] = new boolean[4][4];
			while(room.checkDoor(door,doorAccss,roomAccss) == false) {
			for(int i = 0; i < boolArray.length; i++) {
				for(int j = 0; j < boolArray[i].length;j++) {
						System.out.println("you are at row "+i+" and column "+j+" of the maze");
						System.out.print("there are " + room.getNumDoors()+" number of doors: ");
						System.out.println("[You are here] ----------Door1["+room.getDoorAccess()+"]  Door2[ true]");
						System.out.println();
						System.out.println("please select a door: ");
						int selection = scan.nextInt();
							if(selection == 1) {
								System.out.println("you have selected door 1");
								//attach question to this door
								//if answer is wrong 
								//closed door and message 'door has been locked' appears
								//update the status of door access to false
							}
							else if(selection == 2) {
								System.out.println("you have selected door 1");
								//attach question to this door
								//if answer is wrong 
								//closed door and message 'door has been locked' appears
								//update the status of door access to false

							}
							else {
								System.out.println("invalid input");
							}
					}

				}
			}
			
		}
}
