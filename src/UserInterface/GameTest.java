//package UserInterface;
//
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import Maze.Maze;
//
//class GameTest {
//	private Maze myDefaultMaze;
//	
//	private Maze myCustomeMaze;
//
//	public void setUp() throws Exception {
//		myDefaultMaze = new Maze();
//		myCustomeMaze = new Maze(3, 3);
//	}
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	public void testGetUserChoice() {
//		char expected = 'S';
//		Assertions.assertEquals(expected, Game.getUserChoice(myDefaultMaze));
//	}
//	
//	@Test
//	public void testNewGame() {
//		
//		Assertions.assertTrue( Game.newGame(myDefaultMaze));
//	}
//
//}
