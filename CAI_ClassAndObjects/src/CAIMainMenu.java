import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Zheng Pei
Description: this is the main menu of the program, it is linked to conceptScreen, activityScreen and the assessmentScreen,
the user can move to different screen by click on the buttons provided
 */
public class CAIMainMenu implements ActionListener {
    // the frame of the main menu
    JFrame frame = new JFrame("Class and objects - by Zheng Pei and Elliott Lai");
    
    // three buttons for conceptScreen, activityScreen and the assessmentScreen
    JButton conceptScreen = new JButton("learn concepts");
    JButton activityScreen = new JButton("start coding!");
    JButton assessmentScreen = new JButton("take test");
    
    // the constructor, it initializes the frame and all the components
    public CAIMainMenu(){
        // set the size of the frame
        frame.setSize(1024, 760);
        // disable default layout
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        // set background image
        frame.setContentPane(new JLabel(new ImageIcon("images/mainScreenBackground.gif")));
        
        // set the position font, and size for all three buttons
        conceptScreen.setBounds(162, 200, 300, 100);
        conceptScreen.setFont(new Font("Segoe Script", Font.BOLD, 30));
        conceptScreen.setForeground(Color.white);
        conceptScreen.setBackground(Color.CYAN);
        conceptScreen.setContentAreaFilled(false);
        conceptScreen.setBorder(BorderFactory.createRaisedBevelBorder());
        conceptScreen.addActionListener(this);
        
        activityScreen.setBounds(562, 200, 300, 100);
        activityScreen.setFont(new Font("Segoe Script", Font.BOLD, 30));
        activityScreen.setForeground(Color.white);
        activityScreen.setBackground(Color.CYAN);
        activityScreen.setContentAreaFilled(false);
        activityScreen.setBorder(BorderFactory.createRaisedBevelBorder());
        activityScreen.addActionListener(this);
        
        assessmentScreen.setBounds(362, 500, 300, 100);
        assessmentScreen.setFont(new Font("Segoe Script", Font.BOLD, 30));
        assessmentScreen.setForeground(Color.white);
        assessmentScreen.setBackground(Color.CYAN);
        assessmentScreen.setContentAreaFilled(false);
        assessmentScreen.setBorder(BorderFactory.createRaisedBevelBorder());
        assessmentScreen.addActionListener(this);
        
        // add the buttons onto the screen
        frame.add(conceptScreen);
        
        frame.add(activityScreen);
        frame.add(assessmentScreen);
        
        // make the frame visible
        frame.setVisible(true);
    }
    
    // override the actionPerformed method in the actionListener class so that we can monitor changes made in GUI
    @Override
    public void actionPerformed(ActionEvent e) {
        // open the respective screen based on the button the user have clicked
        if(e.getSource() == conceptScreen){
            new CAIConceptScreen();
        }else if(e.getSource() == activityScreen){
            new CAIActivityScreen();
        }else if(e.getSource() == assessmentScreen){
            new CAIAssessmentScreen();
        }
        
        // after initializing the new screen, close the frame of the main menu
        frame.dispose();
    }
}
