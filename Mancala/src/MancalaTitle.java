/*
Group Member: Zheng Pei, Elliott Lai
Description: Creates a GUI for the title frame of the Mancala game
Major Skills: Using Java Swing GUI objects, such as JLabels and JButtons

 */

// Import Java packages
import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

// Start of the MancalaTitle class
public class MancalaTitle extends JFrame implements ActionListener {
	
	// Declare field variables
	int stoneNumber = 4;
	int gameMode = 1;
	int animationSpeed = 250;
	
	// Declare GUI objects
	JLabel background = new JLabel(new ImageIcon("images/woodTexture.jpg"));
	
	// the labels that will be used as prompt
	JLabel title = new JLabel("Mancala Game Menu");
	JLabel stoneInitial = new JLabel("How many stones would you like to start with? (default to 4)");
	JLabel modePrompt = new JLabel("Which mode would you like to play? (default to human vs AI)");
	
	
	// the buttons for mode selection
	JRadioButton twoButton = new JRadioButton("2");
	JRadioButton threeButton = new JRadioButton("3");
	JRadioButton fourButton = new JRadioButton("4");
	JRadioButton fiveButton = new JRadioButton("5");
	JRadioButton twoPlayer = new JRadioButton("Two Player");
	JRadioButton playerAI = new JRadioButton("Player vs. AI");
	ButtonGroup group1 = new ButtonGroup();
	ButtonGroup group2 = new ButtonGroup();
	
	// the button that opens the game screen
	JButton openGameScreen = new JButton("Start Game");
	
	// prompt for the name of the player
	JLabel playerNamePrompt = new JLabel("<html>Enter player's name, you don't need to write Player2's name if you " +
			"didn't select two player mode</html>");
	
	// text field where the user can enter the name of the player
	JTextField player1Name = new JTextField("Player1");
	
	JTextField player2Name = new JTextField("Player2");
	
	// label and buttons for the user to select animation interval
	JLabel animationPrompt = new JLabel("<html>select desired animation speed, recommended speed is medium</html>");
	ButtonGroup speedButtonGroup = new ButtonGroup();
	JRadioButton fast = new JRadioButton("fast");
	JRadioButton medium = new JRadioButton("medium");
	JRadioButton slow = new JRadioButton("slow");
	
	// Constructor method
	public MancalaTitle() {
		
		// Sets the window size to 800 px by 600 px and the title
		setSize(800, 600);
		setTitle("Mancala Game Menu");
		
		// Clears the window background
		background.setLayout(null);
		setResizable(false);
		
		// Position the title label centered at the top of the GUI
		title.setBounds(350, 5, 200, 50);
		title.setForeground(Color.cyan);
		add(title);
		
		// Position the questions and buttons down the middle of the GUI
		stoneInitial.setBounds(50, 60, 400, 50);
		stoneInitial.setForeground(Color.cyan);
		add(stoneInitial);
		
		twoButton.setBounds(50, 115, 50, 50);
		twoButton.addActionListener(this);
		add(twoButton);
		group1.add(twoButton);
		
		threeButton.setBounds(150, 115, 50, 50);
		threeButton.addActionListener(this);
		add(threeButton);
		group1.add(threeButton);
		
		fourButton.setBounds(250, 115, 50, 50);
		fourButton.addActionListener(this);
		fourButton.setSelected(true);
		add(fourButton);
		group1.add(fourButton);
		
		fiveButton.setBounds(350, 115, 50, 50);
		fiveButton.addActionListener(this);
		add(fiveButton);
		group1.add(fiveButton);
		
		// set up the prompt for mode selection
		modePrompt.setBounds(50, 170, 400, 50);
		modePrompt.setForeground(Color.cyan);
		add(modePrompt);
		
		// set up the buttons for game mode to the screen
		twoPlayer.setBounds(50, 225, 100, 50);
		twoPlayer.addActionListener(this);
		add(twoPlayer);
		group2.add(twoPlayer);
		
		playerAI.setBounds(200, 225, 200, 50);
		playerAI.addActionListener(this);
		playerAI.setSelected(true);
		add(playerAI);
		group2.add(playerAI);
		
		// set up the button for gameScreen
		openGameScreen.setBounds(300, 500, 200, 50);
		openGameScreen.addActionListener(this);
		add(openGameScreen);
		
		playerNamePrompt.setBounds(420, 70, 350, 50);
		playerNamePrompt.setForeground(Color.cyan);
		add(playerNamePrompt);
		
		// set up the text field for user to choose the name of the player
		player1Name.setBounds(450, 120, 300, 25);
		add(player1Name);
		
		player2Name.setBounds(450, 150, 300, 25);
		add(player2Name);
		
		// set up the prompt for selecting animation speed
		animationPrompt.setBounds(50, 300, 400, 25);
		animationPrompt.setForeground(Color.cyan);
		add(animationPrompt);
		
		// set up the buttons for selecting animation speed
		speedButtonGroup.add(fast);
		speedButtonGroup.add(medium);
		speedButtonGroup.add(slow);
		
		fast.setBounds(50, 350, 100, 50);
		fast.addActionListener(this);
		medium.setBounds(200, 350, 100, 50);
		medium.setSelected(true);
		medium.addActionListener(this);
		slow.setBounds(350, 350, 100, 50);
		slow.addActionListener(this);
		
		add(fast);
		add(medium);
		add(slow);
		
		// Enables everything to be visible
		add(background);
		setVisible(true);
	}
	
	// Getters & Setters
	public int getStoneNumber() {
		return stoneNumber;
	}
	public void setStoneNumber(int stoneNumber) {
		this.stoneNumber = stoneNumber;
	}
	public int getGameMode() {
		return gameMode;
	}
	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}
	
	// Start of the method that checks which buttons have been clicked
	public void actionPerformed(ActionEvent e) {
		
		// Checks which stoneNumber button has been clicked, and then sets the appropriate
		// number of stones to start with in each pit
		if(e.getSource() == twoButton) {
			setStoneNumber(2);
		}
		else if(e.getSource() == threeButton) {
			setStoneNumber(3);
		}
		else if(e.getSource() == fourButton) {
			setStoneNumber(4);
		}
		else if(e.getSource() == fiveButton) {
			setStoneNumber(5);
		}
		
		// Checks which game mode has been selected
		if(e.getSource() == twoPlayer) {
			setGameMode(2);
		}
		else if(e.getSource() == playerAI) {
			setGameMode(1);
			
		}
		
		// check which animation speed the user have selected
		if(e.getSource() == fast){
			animationSpeed = 0;
		}else if(e.getSource() == medium){
			animationSpeed = 250;
		}else if(e.getSource() == slow){
			animationSpeed = 750;
		}
		
		// open(initialize) the game screen
		if(e.getActionCommand() == "Start Game"){
			// initialize the game board, default to player one start
			MancalaRunGame.state = new MancalaGameState(stoneNumber, 1);
			
			// based on the mode selected, run the game
			if (gameMode == 1) {
				// ask for the name of the mancalaPlayer1
				MancalaRunGame.mancalaPlayer1 = new MancalaPlayer(player1Name.getText());
				
				// initialize mancalaPlayer2 as AI - use empty constructor
				MancalaRunGame.mancalaPlayer2 = new MancalaPlayer();
				
			} else {
				// ask for the name of the players
				MancalaRunGame.mancalaPlayer1 = new MancalaPlayer(player1Name.getText());
				
				MancalaRunGame.mancalaPlayer2 = new MancalaPlayer(player2Name.getText());
			}
			
			// close the current window
			dispose();
			
			// open the game screen
			MancalaGameScreen.frame.setVisible(true);
			
			// run the game
			MancalaRunGame mancalaRunGame = new MancalaRunGame(animationSpeed);
		}
		
	}

} // End of the MancalaTitle class
