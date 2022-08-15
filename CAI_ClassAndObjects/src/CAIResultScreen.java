import javax.swing.*;
import java.awt.*;
/*
Zheng Pei
Description: this is the result frame, it shows the user the score he got in the assessment screen and a sheet that
contains 1. question number 2. the user's answer 3. the correct answer
 */
public class CAIResultScreen {
    // the frame
    private JFrame frame = new JFrame("Class and objects - by Zheng Pei and Elliott Lai");
    
    // a label that shows user the score he got
    private JLabel scoreLabel = new JLabel();
    
    // a label that prompts the user that he can go back and check the explanation
    private JLabel prompt = new JLabel("<html>you can now go back and check the explanation for each question</html>");
    
    // set up a JTable with scroll pane to that shows if the user got a question correct
    private String[] columnHeader = {"question #", "your answer", "the correct answer"};
    private Object[][] data = new Object[9][3];
    private JTable table;
    
    private JScrollPane tableScroll;
    
    public CAIResultScreen() {
        // set up the Jframe
        frame.setLayout(null);
        frame.setSize(470, 310);
        
        // get the score and set the text of the scoreLabel to the score
        scoreLabel.setText("Score: " + Integer.toString(CAIAssessmentScreen.getScore()) + "/9");
        scoreLabel.setFont(new Font(Font.SERIF, Font.ITALIC | Font.BOLD, 30));
        
        // update the data shown in Jtable
        for (int i = 0; i < 9; ++i) {
            data[i][0] = "question " + (i + 1);
            data[i][1] = CAIAssessmentScreen.getUserAnswerArray()[i];
            data[i][2] = DefaultText.assessmentAnswers[i];
        }
        
        // initialize the Jtable and scroll pane
        table = new JTable(data, columnHeader);
        tableScroll = new JScrollPane(table);
        
        // set up the basic property of the table and label
        tableScroll.setLocation(0, 100);
        tableScroll.setSize(table.getPreferredScrollableViewportSize());
        
        scoreLabel.setBounds(0, 0, 400, 50);
        
        prompt.setBounds(20, 50, 400, 50);
        prompt.setFont(new Font(Font.SERIF, Font.ITALIC | Font.BOLD, 18));
        
        // add the components to the frame
        frame.add(tableScroll);
        frame.add(scoreLabel);
        frame.add(prompt);
        
        // set frame to the centre of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        
        // make frame visible and make the frame determine its own size
        frame.setVisible(true);
    }
}
