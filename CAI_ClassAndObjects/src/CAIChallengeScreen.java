import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;

/*
Zheng Pei
Description: this is the challenge screen, it is a screen that's included in the activity screen, it gives the user a challenge
related to the CAI program topic that the user could try out as an activity
 */
public class CAIChallengeScreen {
    // initialize the frame
    JFrame frame = new JFrame("Class and objects - by Zheng Pei and Elliott Lai");
    
    // the instruction on the frame
    JLabel instruction = new JLabel("<html>try your best to write a Dog class that meets the following requirements:<br><br>" +
            "1. The class should have variables that records the brand of dog, the age of dog, and whether the dog is happy or not<br><br>" +
            "2. There should be a constructor that initialize all these variables, don't forget to include getters/setters for all these variables<br><br>"+
            "3. write Method dogBark(), a dog should bark \"Woof Woof\" if it's happy, \"ruff ruff\" if it's unhappy<br><br>" +
            "4. write Method isMature(), if a dog is older than 2 years old, the method should print \" the dog is no longer a puppy!\" <br>" +
            "or if it's below 2 years old, then the method should print \" the dog is still a puppy\"<br><br> "+
            "5. write Method toString() that will print all the information of this dog in a logical way, for example: <br>" +
            "This dog is a 5 years old Labrador, it's angry/happy right now.</html>");
    
    // the constructor
    public CAIChallengeScreen(){
        // set up the instruction
        instruction.setFont(new Font("Arial", Font.BOLD, 20));
        instruction.setBounds(20, 20, 560, 760);
        instruction.setForeground(Color.white);
        
        // add background image and instruction
        frame.setContentPane(new JLabel(new ImageIcon("images/challengeScreen.jpg")));
        frame.add(instruction);
        
        // set the size of the frame then make it visible
        frame.setSize(600, 800);
        frame.setVisible(true);
    }
}
