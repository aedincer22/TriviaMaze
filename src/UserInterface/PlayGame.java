package UserInterface;

public class PlayGame {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// DO some ground work before the game

		// User chooses to play the game
		try {
			String filePath = "Jeopardy-theme-song.wav";
			SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(filePath);

			audioPlayer.play();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
		Game.startGame();
	}
}
