 /*
 Group Member: Zheng Pei, Elliott Lai
 Description: a class that simulates the rule of Mancala
 */
 
 /*
 Functionality of the class:
 1. this class can define the number of stone in each pit and the side that get to start the game
 2. this class can make a copy of itself
 3. method gameOverCheck() can check if the game is over based on the rule of Mancala
 4. method getResult() can get the result the game(i.e. wins, lose or tie), this method should only be used after gameOverCheck()
 5. method illegalMove(int selected) can check if a move is illegal or not based on the rule of Mancala
 6. method applyMove(int selected) can apply a move that the player has chosen and modify the value(which represents
 the number of stones) inside the array correspondingly - this method should only be used after illegalMove(int selected)
 7. method printBoard() this function will print the game board in text, it's the function we used to test the program
 before we have finished the MancalaGameScreen class, this function should only be used for testing purposes
 8. the variable named as control indicates the player that get to make next move, it's declared as public, 1 - player, -1 - AI
  */

public class MancalaGameState {
    
    /*
    game interface sketch
    ---------------------------------------------------------------------------------------------------------------------------
     |             | AIPit[5]      | AIPit[4]      | AIPit[3]      |  AIPit[2]     |  AIPit[1]     |  AIPit[0]    |          |
     |   AI base   |--------------------------------------------------------------------------------------------  |playerBase|
     |             |  playerPit[0] |  playerPit[1] |  playerPit[2] |  playerPit[3] |  playerPit[4] |  playerPit[5]|          |
     ---------------------------------------------------------------------------------------------------------------------------
     */
    
    // initialize the number of stones in player's and AI's base to 0
    public int playerBaseStoneCount = 0;
    public  int AIBaseStoneCount = 0;
    
    // initialize two arrays that will hold the number of stones in the pit on each side
    public  int[] playerPit = new int[6];
    public  int[] AIPit = new int[6];
    
    // indicates who gets to make next move 1 - player, -1 - AI
    public int control;
    
    // the constructor of the class, initialize the array with the amount of stone the user choose to have
    public MancalaGameState(int stoneAmount, int whoStart) {
        
        // initialize two arrays with the amount of stone user have chosen to have on each pit(except for
        for (int i = 0; i < 6; ++i) {
            playerPit[i] = stoneAmount;
            AIPit[i] = stoneAmount;
        }
        
        control = whoStart;
    }
    
    // second constructor, this constructor is used when we want to make a copy of the class
    public MancalaGameState(MancalaGameState state) {
        this.playerBaseStoneCount = state.playerBaseStoneCount;
        this.AIBaseStoneCount = state.AIBaseStoneCount;
        this.control = state.control;
        this.playerPit = state.playerPit.clone();
        this.AIPit = state.AIPit.clone();
    }
    
    
    // check if the game is over based on the rule, return true if the game is over, false otherwise
    public boolean gameOverCheck() {
        // count the stone left in the pit on both sides
        int stoneLeft = 0;
        for (int i = 0; i < 6; ++i) {
            stoneLeft += playerPit[i];
            stoneLeft += AIPit[i];
        }
        
        if (control == 1) {
            // if this is the turn that human player will play
            // check if the human player can still make next move
            boolean human_move = false;
            for (int i = 0; i < 6; ++i) {
                // there's at least one more move
                if (playerPit[i] != 0) {
                    human_move = true;
                    break;
                }
            }
            
            // if human player can't make any more moves
            if (!human_move) {
                // remove the stones in the pits on both sides
                for (int i = 0; i < 6; ++i) {
                    AIPit[i] = 0;
                    playerPit[i] = 0;
                }
                
                // the AI gets all the stone left
                AIBaseStoneCount += stoneLeft;
                
                // game over
                return true;
            }
        } else {
            // if this is the turn that AI will play
            // check if the AI can still make next move
            boolean AI_move = false;
            for (int i = 0; i < 6; ++i) {
                // there's at least one more move
                if (AIPit[i] != 0) {
                    AI_move = true;
                    break;
                }
            }
            
            // if AI can't make any more moves
            if (!AI_move) {
                // remove the stones in the pits on both sides
                for (int i = 0; i < 6; ++i) {
                    AIPit[i] = 0;
                    playerPit[i] = 0;
                }
                
                // the human player gets all the stone left
                playerBaseStoneCount += stoneLeft;
                
                // game over
                return true;
            }
        }
        
        
        // the game is not over yet
        return false;
    }
    
    // check which side wins, this method will only be called after the gameOverCheck() method returns true which means
    // that the game is over
    public int getResult() {
        // return -1 - the player lost, 0 - tie, 1 - player wins
        if (playerBaseStoneCount < AIBaseStoneCount) {
            return -1;
        } else if (playerBaseStoneCount == AIBaseStoneCount) {
            return 0;
        } else {
            // playerBaseStoneCount > AIBaseStoneCount
            return 1;
        }
    }
    
    
    // the user can not choose a pit with 0 stone inside
    // the user can only select pit on his own side in the MancalaGameScreen, so we don't need to check if he chooses the right side
    // return true if the move is illegal, false otherwise
    public boolean illegalMove(int selected) {
        // the player's turn
        if (control == 1 && playerPit[selected] == 0) {
            return true; // illegal
        }
        // the AI's turn
        return control == -1 && AIPit[selected] == 0; // illegal
    }
    
    // this method applies the move to the board
    // this method must be used after using illegalMove() to check if the move is legal or not
    public void applyMove(int selected) {
        // the amount of stone in the pit that the user have selected
        int amount;
        // this variable records the place where we should add one stone, start from the next place
        int index_add = selected + 1;
        
        // indicate which side of the pit the machine should add stone to, 1 - player, -1 - computer
        int side = control;
        
        // the player's turn
        if (control == 1) {
            amount = playerPit[selected];
            
            // empty the stone in this pit
            playerPit[selected] = 0;
            
        } else {
            // the computer's turn
            amount = AIPit[selected];
            
            // empty the stone in this pit
            AIPit[selected] = 0;
            
        }
        
        // a variable that will later be used in the while loop to check if the last stone is located at a special location
        // -1 - not special, 0 - inside the pit(another turn), 1 - in an empty pit and the pit is at the current player's side
        int special = -1;
        
        // continue the loop as long as there is more stone
        while (amount != 0) {
            // index out of array bound, which means the next stone should be placed in either player's base or
            // AI's base(avoid opponents base), then we should switch side to add the stone
            if (index_add == 6) {
                // add stone to player's base(only if this is the human player's turn)
                if (side == 1 && side == control) {
                    // record the info that the current stone is placed in the base
                    special = 0;
                    amount--;
                    playerBaseStoneCount++;
                }
                
                // add stone to player's base(only if this is the AI player's turn)
                if (side == -1 && side == control) {
                    // record the info that the current stone is placed in the base
                    special = 0;
                    amount--;
                    AIBaseStoneCount++;
                }
                
                index_add = 0; // index restart from 0
                side = -side; // change side
            } else {
                // add one to the next pit
                if (side == 1) {
                    // human player's side
                    // if the last stone is put in an empty pit and the pit is at the current player's side, and
                    // the opposite pit isn't empty
                    if (side == control && playerPit[index_add] == 0 && AIPit[5 - index_add] != 0) {
                        special = 1;
                    } else {
                        special = -1;
                    }
                    
                    playerPit[index_add]++;
                } else {
                    // AI's side
                    // if the last stone is put in an empty pit and the pit is at the current player's side, and
                    // the opposite pit isn't empty
                    if (side == control && AIPit[index_add] == 0 && playerPit[5 - index_add] != 0) {
                        special = 1;
                    } else {
                        special = -1;
                    }
                    AIPit[index_add]++;
                }
                index_add++; // move to the next place
                amount--; // stone amount -1
            }
            
        }
        
        
        if (special == 0) {
            // last stone was inside the pit(another turn)
            // return without switch the control
            return;
        } else if (special == 1) {
            // if the last stone is in an empty pit and the pit is at the current player's side
            if (control == 1) {
                // human's turn, take stone from AI side
                // the index for the opposite side can be calculated with formula 5-(index_add -1) which is 6- index_add
                playerBaseStoneCount += AIPit[6 - index_add] + 1;
                playerPit[index_add - 1] = 0;
                AIPit[6 - index_add] = 0;
            }
            
            if (control == -1) {
                // AI's turn, take stone from human side, the index need to -1 since we + 1 to index before exiting the loop
                AIBaseStoneCount += playerPit[6 - index_add] + 1;
                AIPit[index_add - 1] = 0;
                playerPit[6 - index_add] = 0;
            }
        }
        
        // switch the control
        control = -control;
    }
    
    // this method will output the board in text, it should only be used to test this class
    public void printBoard() {
        System.out.println("this is the current game board:");
        // print AIPit in reverse order
        for (int i = 5; i >= 0; --i) {
            System.out.print("\t" + AIPit[i] + ",");
        }
        
        System.out.println();
        // print the base of AI and the player
        System.out.println(AIBaseStoneCount + "\t\t\t\t\t\t\t" + playerBaseStoneCount);
        
        // print the playerPit in normal order
        for (int i = 0; i < 6; ++i) {
            System.out.print("\t" + playerPit[i] + ",");
        }
        
        System.out.println();
    }
    
    // return the number of stones in the given pit in the mancalaPlayer1's side
    public String getStonePlayerPit(int pos){
        return Integer.toString(playerPit[pos]);
    }
    
    // return the number of stones in the given pit in the AI's side
    public String getStoneAIPit(int pos){
        return Integer.toString(AIPit[pos]);
    }
    
}
