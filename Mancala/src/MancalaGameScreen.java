import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Objects;

/*
 Group Member: Zheng Pei, Elliott Lai
 Description: the main game screen of the game, this is where the user interact with the program
 
 Functionality:
 - method updateGameBoard() - should be called each time after a move has been made, update the game board with the current state
 - method buttonSetUp() - set up the basic properties of a button, avoid repetitive code
 - method getLastSelected() - return which button the user have selected
 */


public class MancalaGameScreen implements ActionListener {
    /*
    game interface sketch( buttons are invisible to the user )
    -----------------------------------------------------------------------------------------------
     |            | button8 | button9 | button10 |  button11|  button12 |  button13    |          |
     |   button14 |--------------------------------------------------------------------|  button7 |
     |            |  button1 |  button2 |  button3 |  button4 | button5 |  button6     |          |
     ----------------------------------------------------------------------------------------------
     */
    
    // variable for frame
    public static JFrame frame = new JFrame("Mancala game by Zheng Pei and Elliott Lai");
    
    // variable that records which pit the user have selected
    private static int lastSelected = -1;
    
    // these four variables will set a game log which helps the human player to keep track of what happens
    public static DefaultListModel<String> gameLogContent = new DefaultListModel<String>();
    
    private static JList gameLog;
    
    private static JScrollPane scrollPane;
    
    private static JLabel gameLogTitle = new JLabel("game log:");
    
    
    // all the invisible buttons on the screen
    private JButton button1 = new JButton();
    private JButton button2 = new JButton();
    private JButton button3 = new JButton();
    private JButton button4 = new JButton();
    private JButton button5 = new JButton();
    private JButton button6 = new JButton();
    private JButton button7 = new JButton();
    private JButton button8 = new JButton();
    private JButton button9 = new JButton();
    private JButton button10 = new JButton();
    private JButton button11 = new JButton();
    private JButton button12 = new JButton();
    private JButton button13 = new JButton();
    private JButton button14 = new JButton();
    
    // the two buttons for helpWindow and newGame
    private JButton helpWindow = new JButton("rules");
    
    private JButton startNewGame = new JButton("start new game");
    
    // two labels that help the user to find his own side
    private JLabel player1Side = new JLabel();
    
    private JLabel player2Side = new JLabel();
    
    // record the number of timer that is still running
    public int timerLeft = 0;
    
    public MancalaGameScreen(){
        // set up the basic properties of frame
        frame.setLayout(null);
        frame.setSize(1024, 600);
        frame.setContentPane(new JLabel(new ImageIcon("Images/MancalaGameBoard.png")));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        // set up the title of the game lop
        gameLogTitle.setBounds(200, 150, 600, 50);
        gameLogTitle.setFont(new Font("Arial", Font.BOLD, 20));
        
        // set up the game log and the scroll pane
        gameLog = new JList(gameLogContent);
        scrollPane = new JScrollPane(gameLog);
        scrollPane.setBounds(200, 200, 600, 150);
        gameLog.setOpaque(false);
        gameLog.setBackground(new Color(0, 0, 0, 0));
        frame.add(scrollPane);
        frame.add(gameLogTitle);
        
        // set up all the buttons
        buttonSetUp(button1, 110, 140,155,400, "1");
        buttonSetUp(button2, 110, 140,275,400, "2");
        buttonSetUp(button3, 110, 140,392,400, "3");
        buttonSetUp(button4, 110, 140,510,400, "4");
        buttonSetUp(button5, 110, 140,630,400, "5");
        buttonSetUp(button6, 110, 140,745,400, "6");
        buttonSetUp(button7, 120, 500,860,30, "player1Base");
        buttonSetUp(button8, 110, 140,155,25, "8");
        buttonSetUp(button9, 110, 140,275,25, "9");
        buttonSetUp(button10, 110, 140,392,25, "10");
        buttonSetUp(button11, 110, 140,510,25, "11");
        buttonSetUp(button12, 110, 140,630,25, "12");
        buttonSetUp(button13, 110, 140,745,25, "13");
        buttonSetUp(button14, 120, 500, 30, 30, "player2Base");
        
        
        // set up the button that opens the help window
        helpWindow.setBounds(200, 350, 300, 50);
        helpWindow.addActionListener(this);
        frame.add(helpWindow);
        
        // set up the button that reopens the title screen(restart the game)
        startNewGame.setBounds(500, 350, 300, 50);
        startNewGame.addActionListener(this);
        frame.add(startNewGame);
        
        // always keep the scroll bar of the scroll pane at the bottom so the user don't have to scroll down, also disable scroll bar
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });
        
        // set up the labels that helps the user to locate his pit
        player1Side.setBounds(440, 530, 200, 30);
        player1Side.setFont(new Font("Arial", Font.ITALIC, 20));
        frame.add(player1Side);
    
        player2Side.setBounds(460, 0, 200, 30);
        player2Side.setFont(new Font("Arial", Font.ITALIC, 20));
        frame.add(player2Side);
        
        // make the frame appears at the middle of the user's screen
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screen.width - 1024) / 2, (screen.height - 600) / 2);
        

    }
    
    // a method that set up the basic properties of a button, it simplifies the code
    private void buttonSetUp(JButton button, int sizeWidth, int sizeHeight, int locationX, int locationY, String actionCommand){
        button.setSize(sizeWidth, sizeHeight);
        button.setLocation(locationX, locationY);
        
        // make the button invisible
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        
        // set up the action command and actionListener
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        
        
        button.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(button);
    }
    
    // this method will update the game board with the newest value
    public void updateGameBoard(MancalaGameState state, int interval, int whoMove) {
        // update the labels with the name of the player
        player1Side.setText(MancalaRunGame.mancalaPlayer1.getPlayerName() + "'s side");
        player2Side.setText(MancalaRunGame.mancalaPlayer2.getPlayerName() + "'s side");
        
        // initialize an array that will hold text that will be displayed on all the buttons, from 1-14
        String[] updateText = new String[14];
        
        // initialize an array that hold all the buttons
        JButton[] buttonArray = {button1, button2, button3, button4, button5, button6, button7,
                button13, button12, button11, button10, button9, button8, button14};
        
        // initialize 14 timer object for animation
        Timer[] timerArray = new Timer[14];
        for(int i = 0; i < 14; ++i){
            timerArray[i] = new Timer(interval, null);
        }
        
        // fill the text array with value
        int index = 0;
        for(int i = 0; i <= 5; ++i){
            updateText[index] = state.getStonePlayerPit(i);
            index ++;
        }
        updateText[index] = Integer.toString(state.playerBaseStoneCount);
        index ++;
        for(int i = 0; i <= 5; ++i){
            updateText[index] = state.getStoneAIPit(i);
            index++;
        }
        updateText[index] = Integer.toString(state.AIBaseStoneCount);
        
        // if a move done by AI
        if(whoMove == -1){
            // start updating buttons from the AI's side
            for(int i = 7; i < 14; ++i) {
                // only set timers for the pits that needs updates(it saves time)
                if (Objects.equals(buttonArray[i].getText(), updateText[i])) {
                    continue;
                }
                // set up the timer with specific delay
                int finalI = i;
                timerLeft++;
                timerArray[i].setInitialDelay(timerLeft * interval);
                timerArray[i].setRepeats(false);
    
                timerArray[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // update text
                        buttonArray[finalI].setText(updateText[finalI]);
                        timerLeft--;
                    }
                });
                // start the timer
                timerArray[i].start();
            }
            for(int i = 0; i < 7; ++i) {
                // only set timers for the pits that needs updates(it saves time)
                if (Objects.equals(buttonArray[i].getText(), updateText[i])) {
                    continue;
                }
                // set up the timer with specific delay
                int finalI = i;
                timerLeft++;
                timerArray[i].setInitialDelay(timerLeft * interval);
                timerArray[i].setRepeats(false);
        
                timerArray[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // update text
                        buttonArray[finalI].setText(updateText[finalI]);
                        timerLeft--;
                    }
                });
                // start the timer
                timerArray[i].start();
            }
        }else{
            // start updating from human's side
            for(int i = 0; i < 14; ++i) {
                // only set timers for the pits that needs updates(it saves time)
                if (Objects.equals(buttonArray[i].getText(), updateText[i])) {
                    continue;
                }
                // set up the timer with specific delay
                int finalI = i;
                timerLeft++;
                timerArray[i].setInitialDelay(timerLeft * interval);
                timerArray[i].setRepeats(false);
        
                timerArray[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // update text
                        buttonArray[finalI].setText(updateText[finalI]);
                        timerLeft--;
                    }
                });
                // start the timer
                timerArray[i].start();
            }
            
        }
        

    }
    
    

    // override the actionPerformed method so that we can detect what the user have selected
    @Override
    public void actionPerformed(ActionEvent e) {
        // make sure the user have make moves when the animation is playing
        if(timerLeft != 0){
            gameLogContent.addElement("Please make your next move after the animation is finished");
            return;
        }
        
        // error checking, make sure the user can't select stones in the base or the stones in the opponent's pit
        if((e.getActionCommand().equals("13") ||e.getActionCommand().equals("12") ||e.getActionCommand().equals("11")
                ||e.getActionCommand().equals("10") ||e.getActionCommand().equals("9") ||e.getActionCommand().equals("8"))
                && MancalaRunGame.state.control == 1){
            gameLogContent.addElement("You can't move the stone in your opponents pit!");
            return;
        }else if((e.getActionCommand().equals("1") || e.getActionCommand().equals("2") || e.getActionCommand().equals("3") ||
                e.getActionCommand().equals("4") || e.getActionCommand().equals("5") || e.getActionCommand().equals("6"))
                && MancalaRunGame.state.control == -1){
            gameLogContent.addElement("You can't move the stone in your opponents pit!");
            return;
        }else if(e.getActionCommand().equals("player1Base") || e.getActionCommand().equals("player2Base")){
            gameLogContent.addElement("You can't move the stones in the base!");
            return;
        }
        
        // open the helpWindow if the user clicked on the rule button, start a new game if the user clicked on the start new game button
        if(e.getActionCommand().equals("rules")){
            MancalaHelpWindow helpScreen = new MancalaHelpWindow();
            return;
        }else if(e.getActionCommand().equals("start new game")){
            gameLogContent.clear();
            MancalaTitle titleScreen = new MancalaTitle();
            
            // close the current window
            frame.dispose();
            return;
        }
        
        // based on the pit the user have selected, change the value of last selected
        if(e.getActionCommand().equals("13") || e.getActionCommand().equals("1")){
            lastSelected = 0;
        }else if(e.getActionCommand().equals("12") || e.getActionCommand().equals("2")){
            lastSelected = 1;
        }else if(e.getActionCommand().equals("11") || e.getActionCommand().equals("3")){
            lastSelected = 2;
        }else if(e.getActionCommand().equals("10") || e.getActionCommand().equals("4")){
            lastSelected = 3;
        }else if(e.getActionCommand().equals("9") || e.getActionCommand().equals("5")){
            lastSelected = 4;
        }else if(e.getActionCommand().equals("8") || e.getActionCommand().equals("6")){
            lastSelected = 5;
        }
        
        // after we get the value of the last selected button, run one turn of the game
        MancalaRunGame.oneTurn();
    }
    
    // getters and setters
    public static int getLastSelected() {
        return lastSelected;
    }
    
    public static void setLastSelected(int lastSelected) {
        MancalaGameScreen.lastSelected = lastSelected;
    }
}
