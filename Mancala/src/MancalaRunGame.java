import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 Group Member: Zheng Pei, Elliott Lai
 Description: this is the most important class in the whole program, it initializes all the variables we need throughout
 the game, it can simulate one turn in the game by combining the methods that have been written in the GameState class,
 it will also print the result of the game after the game is finished
*/
/*
 Functionality:
 1. method oneTurn() will simulate on turn in the game of Mancala
 2. method showResult will display the result after the game has finished
 */
public class MancalaRunGame {
    
    // initialize two player instances
    public static MancalaPlayer mancalaPlayer1;
    public static MancalaPlayer mancalaPlayer2;
    
    // initialize the game state
    public static MancalaGameState state;
    
    // initialize the game screen
    public static MancalaGameScreen screen = new MancalaGameScreen();
    
    // the variable for animationInterval
    public static int animationInterval = 0;
    
    
    // the constructor, it will initialize the game screen by updating the game board, also it will set up the
    // animation interval for timers used in GUI
    public MancalaRunGame(int animationInterval) {
        screen.updateGameBoard(MancalaRunGame.state, 0, 1);
        this.animationInterval = animationInterval;
    }
    
    
    
    // simulate one turn in the game
    public static void oneTurn() {
        // the variable that represents the position of the pit the user have selected
        int selected;
        
        // the variable that records who makes this move 1-player, -1 -AI
        final int whoMove = state.control;
        
        if (state.control == 1) {
            // if it's the mancalaPlayer1's turn
            
            // get the move from player 1
            selected = mancalaPlayer1.askForMove(state);

            MancalaGameScreen.gameLogContent.addElement(mancalaPlayer1.getPlayerName() + " chose " + (selected + 1));

            
        } else {
            // if it's the mancalaPlayer2's turn
            
            // get the move from player 2 or AI
            selected = mancalaPlayer2.askForMove(state);
            
            MancalaGameScreen.gameLogContent.addElement(mancalaPlayer2.getPlayerName() + " chose " + (6-selected));
            
        }
    
        // if the move is illegal then print error prompt to user and end this method call
        if (state.illegalMove(selected)) {
            MancalaGameScreen.gameLogContent.addElement("the move is illegal, choose another move");
            return;
        }
        
        // apply the move
        state.applyMove(selected);
        
        // update the game board after the move
        screen.updateGameBoard(state, animationInterval, whoMove);

    
        // if the game is over after applying the move
        if(state.gameOverCheck()) {
            showResult(state, mancalaPlayer1, mancalaPlayer2);
            screen.updateGameBoard(state, animationInterval, whoMove);
            return;
        }
        
        // detect who should move next(update text after all the previous timers are finished)
        if(state.control == 1){
            // set up the waiting timer based on the timer left
            Timer waiting = new Timer(animationInterval * screen.timerLeft, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // tell the user whose turn is it now
                    MancalaGameScreen.gameLogContent.addElement("It's " + mancalaPlayer1.getPlayerName() + "'s turn now");
                }
            });
            // start the timer
            waiting.setRepeats(false);
            waiting.start();
        }else{
            // set up the waiting timer based on the timer left
            Timer waiting = new Timer(animationInterval * screen.timerLeft, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // tell the user whose turn is it now
                    MancalaGameScreen.gameLogContent.addElement("It's " + mancalaPlayer2.getPlayerName() + "'s turn now");
                }
            });
            // start the timer
            waiting.setRepeats(false);
            waiting.start();
        }
        
        // if next turn is AI's turn, run another turn automatically(wait until all the previous timers have exited)
        if(state.control == -1 && mancalaPlayer2.getPlayerName() == "AI"){
            // timerLeft interval + 2 so that AI will respond slower
            Timer waiting = new Timer(animationInterval * (screen.timerLeft+2), new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    oneTurn();
                }
            });
            waiting.setRepeats(false);
            waiting.start();
        }

    }
    
    // display the result of the game
    public static void showResult(MancalaGameState state, MancalaPlayer mancalaPlayer1, MancalaPlayer mancalaPlayer2){
        // get the result from the game state
        int result = state.getResult();
        
        // print corresponding messages based on the value returned from the game state class
        if (result == -1) {
            MancalaGameScreen.gameLogContent.addElement(mancalaPlayer2.getPlayerName() + " wins!!!!!");
        } else if (result == 0) {
            MancalaGameScreen.gameLogContent.addElement("tie");
        } else {
            MancalaGameScreen.gameLogContent.addElement(mancalaPlayer1.getPlayerName() + " wins!!!!!");
        }
    }
}
