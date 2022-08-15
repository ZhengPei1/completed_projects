import javax.swing.*;
import java.awt.*;
/*
 Group Member: Zheng Pei, Elliott Lai
`Description: this screen will teach the user about all the rules of Mancala
 */

public class MancalaHelpWindow {
    public JFrame frame = new JFrame("Mancala game by Zheng Pei and Elliott Lai - help window");
    public JLabel rules = new JLabel("<html>Basic Rules:<br>" +
            "* Play always moves around the board in a counter-clockwise circle (to the right)<br>" +
            "* The store on your right belongs to you. That is where you keep the stones you win.<br>" +
            "* The six pits near you are your pits.<br>" +
            "* Only put stones in your own store, not your opponent’s store.<br><br>" +
            "Starting the Game:<br>" +
            "On a turn, a player picks up all the stones in one pit and “sows” them to the right, placing one " +
            "stone in each of the pits along the way. If you come to your store, then add a stone to your store " +
            "and continue. You may end up putting stones in your opponent’s pits along the way. " +
            "Play alternates back and forth, with opponents picking up the stones in one of their pits and " +
            "distributing them one at a time into the pits on the right, beginning in the pit immediately to the " +
            "right.<br><br>" +
            "Special Rules:<br>" +
            "*When the last stone in your hand lands in your store, take another turn.<br>" +
            "*When the last stone in your hand lands in one of your own pits, if that pit had been empty you " +
            "get to keep all of the stones in your opponents pit on the opposite side. Put those captured stones," +
            "as well as the last stone that you just played on your side, into the store.<br><br>" +
            "Ending the Game:<br>" +
            "The game is over when one player’s pits are completely empty. The other player takes the stones " +
            "remaining in her pits and puts those stones in her store. Count up the stones. Whoever has the most " +
            "stones wins.</html> ");
    
    public MancalaHelpWindow(){
        // set up the frame
        frame.setSize(800, 680);
        frame.setResizable(false);
        // put the frame to the middle of the screen based on the size of user's screen
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screen.width - 800) / 2, (screen.height - 680) / 2);
    
        // set up the font
        rules.setFont(new Font("verdana", Font.PLAIN, 16));
        
        // add the label to the frame, then make the screen visible
        frame.add(rules);
        frame.setVisible(true);
    }
}
