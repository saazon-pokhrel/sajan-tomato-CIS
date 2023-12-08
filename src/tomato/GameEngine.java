package tomato;



import java.awt.image.BufferedImage;

/**
 * Main class where the games are coming from.
 *
 */
public class GameEngine {
	String thePlayer = null;  // Player's name

	public GameEngine(String player) {
		thePlayer = player;
	}

	int counter = 0;  // Counter variable
	int score = 0;   // Score variable
	GameServer theGames = new GameServer(); // GameServer instance to manage games
	Game current = null; // Current game being played


	/**
	 * Retrieves a game. This basic version only has two games that alternate.
	 */
	public BufferedImage nextGame() { 
		current = theGames.getRandomGame(); // Get a random game from the GameServer
		return current.getImage(); // Return the image of the current game


	}

	
	public boolean checkSolution( int i) {
		if (i == current.getSolution()) {
			score++;  // Increment score for correct solution
			return true;
		} else {
			return false; // Incorrect solution
		}
	}

	
	public int getScore() {
		return score;
	}

}
