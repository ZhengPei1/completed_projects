import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
Zheng Pei

Description: this class will create an activity screen where the user can code/ run program, view challenge or
return to the main screen

 */
public class CAIActivityScreen implements ActionListener {
    
    // the textareas that are used to accept user input
    public static JTextArea applicationClass = new JTextArea(DefaultText.textForTheApplicationClass);
    public static JTextArea codeSpace = new JTextArea(DefaultText.textForUserCreatedClass);
    public static JTextArea consoleOutput = new JTextArea();
    
    // the frame of Activityscreen
    JFrame frame = new JFrame("Class and objects - by Zheng Pei and Elliott Lai");
    
    // title of the frame
    JLabel title = new JLabel("Activity Screen");
    
    // the main panel of the screen
    JPanel panel = new JPanel() {
        // override the paint component method to paint the panel's background with a image
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(getToolkit().getImage("images/activityScreenBackground.jpg"), 0, 0, getWidth(), getHeight(), this);
        }
    };
    
    // the label, text area and scroll pane that are used to set up a code space for the user to code the application class
    JLabel applicationLabel = new JLabel("<html>APPLICATION CLASS:</html>");
    JScrollPane applicationClassPanel = new JScrollPane(applicationClass, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    // the label, text area and scroll pane that are used to set up a code space for the user to code his own class
    JLabel codeSpaceLabel = new JLabel("SUBCLASS:");
    JScrollPane codeSpacePanel = new JScrollPane(codeSpace, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    // the label, text area and scroll pane that are used to set up a console that will display the error message or output of the written program
    JLabel consoleLabel = new JLabel("<html>CONSOLE:</html>");
    JScrollPane consoleOutputPanel = new JScrollPane(consoleOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    // button that allow the user to run the program he wrote
    JButton runProgram = new JButton("<html>run program</html>");
    
    // button that will bring the user back to the main menu
    JButton backMainMenu = new JButton("<html>go back to main menu<html>");
    
    // button that will open the challenge screen
    JButton challenge = new JButton("<html>challenge<html>");
    
    public CAIActivityScreen() {

        // set up the title label
        title.setFont(new Font("Arial", Font.BOLD, 70));
        title.setForeground(Color.white);
        title.setBounds(470, 20, 600, 80);
        
        // set up the application label, code space and scroll bar
        applicationLabel.setBounds(40, 375, 200, 100);
        applicationLabel.setForeground(new Color(173, 148, 77));
        applicationLabel.setFont(new Font(Font.SERIF, Font.ITALIC| Font.BOLD, 30));
        
        applicationClassPanel.setBounds(250, 150, 1000, 500);
        applicationClassPanel.getViewport().setOpaque(false);
        applicationClassPanel.setOpaque(false);
        
        applicationClass.setTabSize(2);
        applicationClass.setFont(new Font("Arial", Font.ITALIC, 20));
        applicationClass.setOpaque(false);
        applicationClass.setForeground(Color.white);
        applicationClass.setCaretColor(Color.WHITE);
        
        // set up the user created class label, code space and scroll bar
        codeSpaceLabel.setBounds(50, 950, 200, 100);
        codeSpaceLabel.setForeground(new Color(173, 148, 77));
        codeSpaceLabel.setFont(new Font(Font.SERIF, Font.ITALIC| Font.BOLD, 30));
        
        codeSpacePanel.setBounds(250, 700, 1000, 500);
        codeSpacePanel.getViewport().setOpaque(false);
        codeSpacePanel.setOpaque(false);
        
        codeSpace.setTabSize(2);
        codeSpace.setForeground(Color.white);
        codeSpace.setOpaque(false);
        codeSpace.setFont(new Font("Arial", Font.ITALIC, 20));
        codeSpace.setCaretColor(Color.WHITE);
        
        // set up the console label, and scroll bar
        consoleLabel.setBounds(50, 1250, 200, 100);
        consoleLabel.setForeground(new Color(173, 148, 77));
        consoleLabel.setFont(new Font(Font.SERIF, Font.ITALIC| Font.BOLD, 30));
        consoleOutputPanel.setBounds(250, 1250, 1000, 200);
        
        // set up the runProgram button
        runProgram.setBounds(1250, 150, 200, 250);
        runProgram.setForeground(Color.white);
        runProgram.addActionListener(this);
        runProgram.setFont(new Font("Arial", Font.BOLD, 30));
        runProgram.setContentAreaFilled(false);
        runProgram.setBorder(BorderFactory.createRaisedBevelBorder());
        
        // set up the return button
        backMainMenu.setBounds(10, 20, 450, 75);
        backMainMenu.setForeground(Color.white);
        backMainMenu.addActionListener(this);
        backMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
        backMainMenu.setContentAreaFilled(false);
        backMainMenu.setBorder(BorderFactory.createRaisedBevelBorder());
        
        // set up the challenge button
        challenge.setBounds(1250, 400, 200, 250);
        challenge.setForeground(Color.white);
        challenge.addActionListener(this);
        challenge.setFont(new Font("Arial", Font.BOLD, 30));
        challenge.setContentAreaFilled(false);
        challenge.setBorder(BorderFactory.createRaisedBevelBorder());
        
        
        
        // add all the components to the menu
        panel.add(title);
        panel.add(applicationLabel);
        panel.add(applicationClassPanel);
        panel.add(codeSpaceLabel);
        panel.add(codeSpacePanel);
        panel.add(consoleLabel);
        panel.add(consoleOutputPanel);
        panel.add(runProgram);
        panel.add(backMainMenu);
        panel.add(challenge);
        
        // get the basic information about the user's screen
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        
        // set up the size of the main panel and disable the layout
        panel.setPreferredSize(new Dimension(1080, 1700));
        panel.setLayout(null);
        
        // create a scroll pane and place the main panel inside so that we have a scrollable page
        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // speed up the scrolling speed
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        
        // set the scroll pane as the contentpane of the frame
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        // set up the size of the frame, based on the size of the screen
        frame.setSize((int) screen.getWidth(), (int) screen.getHeight());
        // disable screen resizing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // make the screen visible
        frame.setVisible(true);
        
        
    }
    
    // override the actionPerformed method in the actionListener class so that we can monitor changes made in GUI
    @Override
    public void actionPerformed(ActionEvent e) {
        // if the user clicked on the runProgram button
        if (e.getSource() == runProgram) {
            // create separation lines
            for(int i = 0; i < 5; ++i){
                CAIActivityScreen.consoleOutput.append("----------------------------------------------------------------------\n");
            }
            
            // generate .java file for the new class written by the user
            CAICodeExecution newClassExecution = new CAICodeExecution(codeSpace.getText());
            newClassExecution.generateJavaFile();
            
            // generate .java file for the application class written by the user
            CAICodeExecution applicationClassExecution = new CAICodeExecution(applicationClass.getText());
            applicationClassExecution.generateJavaFile();
            
            // compile both files, if file didn't compile, print error message and eng method call
            if(!applicationClassExecution.compileFile()){
                consoleOutput.append("file compilation failed, stop execution\n");
                return;
            };
            
            // execute the application file
            applicationClassExecution.executeFiles();
        }
        
        // if the user clicked on the back to main menu button
        if (e.getSource() == backMainMenu) {
            // open the main menu
            new CAIMainMenu();
            // close the activity frame
            frame.dispose();
        }
        
        // if the user choose to see the challenge
        if(e.getSource() == challenge){
            // open the challenge screen
            new CAIChallengeScreen();
        }
    }
}
