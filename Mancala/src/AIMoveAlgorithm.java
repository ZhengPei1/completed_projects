/*
 Group Member: Zheng Pei, Elliott Lai
 Description: a class that help AI to decide the move it should pick, run the function findMoveForAI() first
 then use the function getNextMove() to get the next move for AI, the algorithm will maximize the number of stones
 AI get in this turn while try to minimize the number of stones human can get in the next turn
 */

/*
 Functionality of the class:
 1. There is no constructor for this class because we do not need to use external value to initialize any variable
 2. the method findMoveForAI(MancalaGameState state, int firstMove, boolean firstEnter) will calculate then store
 the best move into the variable called nextMove
 3. the method simulatesHumanTurn(MancalaGameState state) will calculate the maximum number of stones human can get,
 even though the function is declared as public for testing purpose, but it is only designed to work with
 the method findMoveForAI(), it is used to predict the best move human player can make and shouldn't be used individually
 4. the method reset() resets the value of the variable maximumScoreDifference and nextMove
 5. this class has getters and setters for all variables, after using findMoveForAI(), the getter method getNextMove()
 should be used to get the value stored in the nextMove
*/

/*
 Warning: Do not reuse an instance of this class that have been used before without calling the method reset()
 
 */

public class AIMoveAlgorithm {
    
    // maximumScoreDifference is the (maximum score AI can win in this turn - the maximum score human can win next turn)
    // the larger the difference is, the better the move
    private int maximumScoreDifference = -9999999;
    
    // nextMove - after using the function findMoveForAI, the best next move for AI will be stored in this variable
    private int nextMove = 0;
    
    // this function will calculate the best next move for AI, and it will store the move into the variable nextMove
    // Warning: the parameter int firstMove can equals to any integer value, boolean firstEnter must be equals to true
    public void findMoveForAI(MancalaGameState state, int firstMove, boolean firstEnter) {
        // if the game is over(there's no pit with stones left that the computer can choose from), this
        // function should calculate the maximumScoreDifference at this point and terminate itself
        if (state.gameOverCheck()) {
            // The more stones AI gets compared with the number of stones human player gets, the better the move
            if (state.AIBaseStoneCount - state.playerBaseStoneCount > maximumScoreDifference) {
                maximumScoreDifference = state.AIBaseStoneCount - state.playerBaseStoneCount;
                // only store the first move
                nextMove = firstMove;
            }
            return;
        }
        
        // for all six possible pits (instead of going from leftmost to rightmost hole - which is 0 to 5, we go from 5 to 0,
        // this is because a strategy in mancala is to empty the rightmost hole whenever possible)
        for (int i = 5; i >= 0; --i) {
            // we only record the first move that AI will do in a sequence of moves, because this function is called
            // recursively, so we need to check if this is the first level of the recursion
            if (firstEnter) {
                firstMove = i;
            }
            
            // make a copy of MancalaGameState instance so that we can modify it without changing the original value
            MancalaGameState copyState = new MancalaGameState(state);
            
            // if the move is not illegal(legal)
            if (!copyState.illegalMove(i)) {
                // apply the move
                copyState.applyMove(i);
                // if it's still AI's turn after the change, call this function again to calculate further possible gain
                if (copyState.control == -1) {
                    // recursively call the function until there's no more moves for AI
                    findMoveForAI(copyState, firstMove, false);
                } else {
                    // if AI's turn ends after this move(i.e. the last stone weren't place in AI's base)
                    // use simulatesHumanTurn() method to calculate the maximum number of stones human player can get
                    int maximumHumanGain = simulatesHumanTurn(copyState);
                    
                    // The more stones AI gets compared with the number of stones human player gets, the better the move
                    if (copyState.AIBaseStoneCount - maximumHumanGain > maximumScoreDifference) {
                        maximumScoreDifference = copyState.AIBaseStoneCount - maximumHumanGain;
                        // only store the first move
                        nextMove = firstMove;
                    }
                }
            }
        }
    }
    
    // simulates all the possible moves human can do in his turn, return the maximum number of stones he can get
    public int simulatesHumanTurn(MancalaGameState state) {
        // if the game is over(there's no pit with stones left that the human player can choose from), this
        // function should return the number of stones in the human player's base at this point and terminate itself
        if (state.gameOverCheck()) {
            return state.playerBaseStoneCount;
        }
        
        // the variable that stores the maximum number of stones the human player can get in his turn
        int maxGain = 0;
        
        // for all 6 pits on human's side
        for (int i = 5; i >= 0 ; --i) {
            // make a copy of the state so that we can modify it without changing the original value
            MancalaGameState copyState = new MancalaGameState(state);
            // if the move is not illegal(legal)
            if (!copyState.illegalMove(i)) {
                copyState.applyMove(i);
                // if it's still Human's turn after the move, call this function again to keep simulating
                if (copyState.control == 1) {
                    // recursively call this function to simulate the further moves human player can do
                    int newGain = simulatesHumanTurn(copyState);
                    
                    // only keep the largest number of stones the human player can get
                    maxGain = Math.max(newGain, maxGain);
                } else {
                    // if human's turn ends after the move
                    // only keep the largest number of stones human player can get
                    maxGain = Math.max(maxGain, copyState.playerBaseStoneCount);
                }
                
            }
        }
        // return the maximum number of stones human player can get
        return maxGain;
    }
    
    // reset the value for the variable maximumScoreDifference
    public void reset(){
        maximumScoreDifference =  -9999999;
        nextMove = 0;
    }
    
    // getters and setters for variables
    public int getMaximumScoreDifference() {
        return maximumScoreDifference;
    }
    
    public void setMaximumScoreDifference(int maximumScoreDifference) {
        this.maximumScoreDifference = maximumScoreDifference;
    }
    
    public int getNextMove() {
        return nextMove;
    }
    
    public void setNextMove(int nextMove) {
        this.nextMove = nextMove;
    }
}
