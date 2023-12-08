package tomato;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
/**
 * GUI class for the Tomato Game.
 */

public class GameGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -107785653906635L;
	  // Action performed when a button is clicked
	public void actionPerformed(ActionEvent e) {
		  // Extract the solution from the clicked button
		int solution = Integer.parseInt(e.getActionCommand());
		   // Check if the solution is correct
		boolean correct = myGame.checkSolution(solution);
		// Get the current score
		int score = myGame.getScore(); 
		if (correct) {
			// Display correct solution message
			System.out.println("Correct solution entered!");
			// Get the next game and update the GUI
			currentGame = myGame.nextGame(); 			
			ImageIcon ii = new ImageIcon(currentGame);
			questArea.setIcon(ii);
			infoArea.setText("CORRECT!  Your New Score is: "+score);
		} else { 
			  // Display incorrect solution message
			infoArea.setText( "Not Correct!!! Try again...  Your Total Score is : "+score);			
		}
                    }   

	JLabel questArea = null;  // Label to display the game image
	GameEngine myGame = null; // Game engine instance
	BufferedImage currentGame = null; // Current game image
	JTextArea infoArea = null; // Text area to display game information

	public void initGame(String player) {
		setSize(700, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TOMATO GAME");
		JPanel panel = new JPanel();

		myGame = new GameEngine(player);
		currentGame = myGame.nextGame();

		infoArea = new JTextArea(3, 40);
		
		infoArea.setEditable(false);
		infoArea.setText("Guess Tomato Value?   Score: 0");
		
		JScrollPane infoPane = new JScrollPane(infoArea);
		panel.add(infoPane);

		ImageIcon ii = new ImageIcon(currentGame);
		questArea = new JLabel(ii);
	    questArea.setSize(330, 600);
	    
		JScrollPane questPane = new JScrollPane(questArea);
		panel.add(questPane);
		  // Create buttons for number input
		for (int i = 0; i <= 9; i++) {
			JButton btn = new JButton(String.valueOf(i));
			panel.add(btn);
			btn.addActionListener(this);
		}
		


    getContentPane().add(panel);
    panel.repaint();

	
	}
	// Constructor with player name
	public GameGUI() {
		super();
		initGame(null);
		setVisible(true);
	}

	
	public GameGUI(String player) {
		super();
		initGame(player);
		setVisible(true);
	}

	// Main method to launch the GUI
	public static void main(String[] args) {
		GameGUI myGUI = new GameGUI();
		myGUI.setVisible(true);

	}
}