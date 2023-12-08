package tomato;
//Declares the package where the class is located

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;
//Imports necessary classes for image handling and IOException

public class Game {
	// Define the Game class
	BufferedImage image = null; // Stores the game's image	
	int solution = -1; // Stores the solution to the game
	
	// Constructor to initialize the Game object
	public Game(BufferedImage image, int solution) {
		super();
		this.image = image; // Set the image for the game
		this.solution = solution;
	}
	// Getter method to retrieve the game's image
	public BufferedImage getImage() {
		return image;
	}
	// Getter method to retrieve the solution to the game
	public int getSolution() {
		return solution;
	}
	
}

