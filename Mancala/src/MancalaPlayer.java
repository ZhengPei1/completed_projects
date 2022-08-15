/*
 Group Member: Zheng Pei, Elliott Lai
Description: this class simulates the behaviour of a player

Functionality:
- method askForMove(MancalaGameState state) - if the player is human, then it will return the move the user have selected
                                            - if the player is AI, it will return the move the based on the result of AIMoveAlgorithm
- method getPlayerName() - get the name of the player
 */

public class MancalaPlayer {
    
    // the name of the player, default to AI
    private String playerName = "AI";
    
    // the move algorithm that will find the best move for AI
    AIMoveAlgorithm runSimulation = new AIMoveAlgorithm();
    
    // constructor that initialize the name of the human player
    public MancalaPlayer(String playerName){
        
        this.playerName = playerName;
    }
    
    // an empty constructor for the AI, since AI don't have a name
    public MancalaPlayer(){
    
    }
    
    // a method that asks the user for the input and return the integer value the user have chosen
    public int askForMove(MancalaGameState state){
        
        // if it's a human player
        if(playerName != "AI"){
    
            // return the input
            return MancalaGameScreen.getLastSelected();
        }else{
            // if it's an AI player
            // reset the value
            runSimulation.reset();
    
            // find the move
            runSimulation.findMoveForAI(state, 0, true);
            
            // get and return the move AI should do
            return runSimulation.getNextMove();
            
            
        }
        
    }
    
    
    // getter and setter for the user name
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String playerName) {
        if (this.playerName == "AI") {
            // AI should not be changed to any name other than AI
            return;
        }
        this.playerName = playerName;
    }
}
