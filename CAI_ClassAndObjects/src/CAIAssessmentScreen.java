import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/*
Zheng Pei

Description: this is the assessment frame, in the frame there's a return to main screen button, a label that shows the
answer the user have previously selected for the current question, a combo box that allows the user to jump to a specific question,
a back and next button that allows the user to go to the previous/next page, a textarea that shows user the question,
a submit button, and a JTable on the top that shows the user which question he has completed
 */
public class CAIAssessmentScreen implements ActionListener {
    // array that keeps the user's answer on each question
    private static String[] userAnswerArray;
    // the score the user got
    private static int score = 0;
    // the frame
    private final JFrame frame = new JFrame("Class and objects - by Zheng Pei and Elliott Lai");
    // title of the frame
    private final JLabel title = new JLabel("Assessment Screen");
    // button that will bring the user back to the main menu
    private final JButton backMainMenu = new JButton("<html>back to main screen<html>");
    // button that allow user to submit his answer
    private final JButton submitButton = new JButton("<html>Submit</html>");
    // button that allows the user to go to the next question
    private final JButton next = new JButton("continue");
    // button that allows the user to go to the previous question
    private final JButton back = new JButton("go back");
    // text area that will display the question
    private final JLabel question = new JLabel(DefaultText.assessmentQuestions[0]);
    // record the answer the user have selected for this question
    private final JLabel userAnswer = new JLabel("");
    // show the correct answer of this question
    private final JLabel correctAnswer = new JLabel("");
    // create the text field for user to enter the answer
    private final JTextField answerField = new JTextField();
    // create the text field that shows the user the explanation of a question
    private final JLabel explanationField = new JLabel();
    // the combo box for fast page switch
    private final String[] comboBoxString = {"question 1", "question 2", "question 3", "question 4", "question 5", "question 6", "question 7", "question 8", "question 9"};
    private final JComboBox<String> pageSwitch = new JComboBox<String>(comboBoxString);
    // page switch button
    private final JButton pageSwitchButton = new JButton("<html>jump to the selected question</html>");
    // set up a JTable with scroll pane to remind the user of the questions he has completed
    private Object[][] data = {{"❌", "❌", "❌", "❌", "❌", "❌", "❌", "❌", "❌"}};
    private JTable table = new JTable(data, comboBoxString);
    private final JScrollPane tableScroll = new JScrollPane(table);
    // record the current page number
    private int page = 0;
    // check if the user have submitted the result
    private boolean submitted = false;
    
    public CAIAssessmentScreen() {
        // reinitialize the user answer array
        userAnswerArray = new String[9];
        
        // set up the background image
        frame.setContentPane(new JLabel(new ImageIcon("images/assessmentScreen1.gif")));
        
        // set up the frame layout
        frame.getContentPane().setLayout(null);
        
        // set up title
        title.setBounds(650, 50, 500, 50);
        title.setFont(new Font("Segoe Script", Font.BOLD, 50));
        title.setForeground(Color.white);
        
        // set up main menu button
        backMainMenu.setBounds(50, 50, 400, 40);
        backMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
        backMainMenu.setBorder(BorderFactory.createRaisedBevelBorder());
        backMainMenu.addActionListener(this);
        
        
        // set up back button
        back.setBounds(550, 750, 200, 50);
        back.setFont(new Font("Arial", Font.BOLD, 30));
        back.setBorder(BorderFactory.createRaisedBevelBorder());
        back.addActionListener(this);
        
        
        // set up the next button
        next.setBounds(1200, 750, 200, 50);
        next.setFont(new Font("Arial", Font.BOLD, 30));
        next.setBorder(BorderFactory.createRaisedBevelBorder());
        next.addActionListener(this);
        
        // set up the submit button
        submitButton.setBounds(1300, 200, 200, 400);
        submitButton.setFont(new Font("Arial", Font.BOLD, 30));
        submitButton.setBorder(BorderFactory.createRaisedBevelBorder());
        submitButton.addActionListener(this);
        
        
        // set up question
        question.setBounds(550, 200, 750, 400);
        question.setFont(new Font("Arial", Font.BOLD, 20));
        question.setBorder(BorderFactory.createRaisedBevelBorder());
        question.setForeground(Color.white);
        
        // set up the explanation
        explanationField.setFont(new Font("Arial", Font.BOLD, 20));
        explanationField.setBorder(BorderFactory.createRaisedBevelBorder());
        explanationField.setForeground(Color.white);
        
        // set up the user answer
        userAnswer.setBounds(50, 350, 400, 100);
        userAnswer.setFont(new Font("Arial", Font.BOLD, 20));
        userAnswer.setBorder(BorderFactory.createRaisedBevelBorder());
        userAnswer.setOpaque(true);
        
        // set up correct answer
        correctAnswer.setFont(new Font("Arial", Font.BOLD, 20));
        correctAnswer.setBorder(BorderFactory.createRaisedBevelBorder());
        correctAnswer.setForeground(Color.white);
        
        // set up the combo box
        pageSwitch.setBounds(50, 500, 400, 50);
        pageSwitch.setFont(new Font("Arial", Font.BOLD, 16));
        
        // set up the jump button
        pageSwitchButton.setBounds(50, 550, 400, 75);
        pageSwitchButton.setFont(new Font("Arial", Font.BOLD, 30));
        pageSwitchButton.setBorder(BorderFactory.createRaisedBevelBorder());
        pageSwitchButton.addActionListener(this);
        
        // set up the JTable
        tableScroll.setBounds(550, 150, 750, 40);
        
        
        // add all the components to the JFrame
        changeBackground();
        addAllComponents();
        
        // get the basic information about the user's screen
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        // set up the size of the frame, based on the size of the screen
        frame.setSize((int) screen.getWidth(), (int) screen.getHeight());
        // disable screen resizing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // make the screen visible
        frame.setVisible(true);
        // disable screen resizing
        frame.setResizable(false);
    }
    
    
    
    // a method that changes the background
    private void changeBackground() {
        // update the background
        frame.setContentPane(new JLabel(new ImageIcon("images/assessmentScreen.gif")));
        
        if(!submitted){
            // based on the page number, load different components
            if(page >= 0 && page <= 6){
                multipleChoiceSetUp();
            }else{
                textFieldSetUp();
            }
        }

    }
    
    // set up options for multiple choice questions
    private void multipleChoiceSetUp() {
        // create option abcd
        JRadioButton A = new JRadioButton("A");
        JRadioButton B = new JRadioButton("B");
        JRadioButton C = new JRadioButton("C");
        JRadioButton D = new JRadioButton("D");
        
        // create button group
        ButtonGroup group = new ButtonGroup();
        
        // set up the buttons
        A.setBounds(700, 600, 100, 50);
        A.setForeground(Color.white);
        A.setContentAreaFilled(false);
        A.setFont(new Font("Arial", Font.BOLD, 40));
        A.addActionListener(this);
        
        B.setBounds(1100, 600, 100, 50);
        B.setForeground(Color.white);
        B.setContentAreaFilled(false);
        B.setFont(new Font("Arial", Font.BOLD, 40));
        B.addActionListener(this);
        
        C.setBounds(700, 700, 100, 50);
        C.setForeground(Color.white);
        C.setContentAreaFilled(false);
        C.setFont(new Font("Arial", Font.BOLD, 40));
        C.addActionListener(this);
        
        D.setBounds(1100, 700, 100, 50);
        D.setForeground(Color.white);
        D.setContentAreaFilled(false);
        D.setFont(new Font("Arial", Font.BOLD, 40));
        D.addActionListener(this);
        
        // add buttons to the group
        group.add(A);
        group.add(B);
        group.add(C);
        group.add(D);
        
        // add the buttons to the screen
        frame.add(A);
        frame.add(B);
        frame.add(C);
        frame.add(D);
    }
    
    // set up a text field for user to enter the answer
    public void textFieldSetUp() {
        
        // user prompt
        JLabel prompt = new JLabel("<html>enter your answer here:</html>");
        
        // set up the prompt
        prompt.setBounds(550, 600, 150, 75);
        prompt.setFont(new Font("Arial", Font.BOLD, 20));
        prompt.setForeground(Color.white);
        frame.add(prompt);
        
        // clear the text
        answerField.setText(userAnswerArray[page]);
        // position the textField
        answerField.setBounds(750, 600, 400, 75);
        // set font
        answerField.setFont(new Font("Arial", Font.BOLD, 30));
        // add the text field to the screen
        frame.add(answerField);
    }
    
    // calculate the score the user got
    private void calculateScore() {
        // first reset score to 0
        score = 0;
        
        // for the first seven multiple choice questions, check if the answer is the same
        for (int i = 0; i < 7; ++i) {
            if (Objects.equals(userAnswerArray[i], DefaultText.assessmentAnswers[i])) {
                // if the answer is correct, score + 1
                score++;
            }
        }
        
        // for the last two questions, convert user input to lower case and remove the white space, then check the answer
        if (userAnswerArray[7] != null && userAnswerArray[7].trim().toLowerCase().equals(DefaultText.assessmentAnswers[7])) {
            score++;
        }
        if (userAnswerArray[8] != null && userAnswerArray[8].trim().equals(DefaultText.assessmentAnswers[8])) {
            score++;
        }
        
        
        // if the user answer array[i] is null or "", then change it to N/A
        for (int i = 0; i < 9; ++i) {
            if (userAnswerArray[i] == null || Objects.equals(userAnswerArray[i].trim(), "")) {
                userAnswerArray[i] = "N/A";
            }
        }
    }
    
    // add all the components to the Jframe
    private void addAllComponents() {
        
        // add the components to the frame
        frame.add(backMainMenu);
        pageSwitch.setSelectedIndex(page);
        frame.add(pageSwitch);
        frame.add(pageSwitchButton);
        frame.add(question);
        
        // add back and next
        if (page != 0) {
            frame.add(back);
        }
        if (page != DefaultText.assessmentQuestions.length - 1) {
            frame.add(next);
        }
        
        // set the text of the userAswer and add it to the frame
        if (userAnswerArray[page] == null) {
            userAnswer.setText("Your answer is: ");
        } else {
            userAnswer.setText("Your answer is : " + userAnswerArray[page]);
        }
        frame.add(userAnswer);
        
        // only do the following command if the user haven't submitted the result(the screen components will be different
        // after the user hit submit)
        if (!submitted) {
            // add the components to the frame
            frame.add(title);
            
            // show the question as completed if the answer is not null
            for (int i = 0; i < 9; ++i) {
                if (userAnswerArray[i] != null && !userAnswerArray[i].equals("")) {
                    data[0][i] = "✓";
                }
            }
            
            // update the Jtable
            table = new JTable(data, comboBoxString);
            frame.add(tableScroll);
            
            // add the submit button
            frame.add(submitButton);
        }
        
        // only do the following command if the user have submitted the result(the screen components will be different
        // after the user hit submit)
        if (submitted) {
            // set up the label for explanation part
            JLabel explain = new JLabel("Explanation");
            explain.setFont(new Font("Segoe Script", Font.BOLD, 30));
            explain.setForeground(Color.white);
            explain.setOpaque(false);
            explain.setBounds(900, 350, 500, 100);
            
            
            // reset the properties of some components
            userAnswer.setBounds(900, 150, 500, 100);
            question.setBounds(400, 150, 500, 600);
            
            pageSwitch.setBounds(400, 50, 500, 50);
            pageSwitchButton.setBounds(900, 50, 500, 50);
            
            submitButton.setText("<html>result</html>");
            submitButton.setBounds(875, 750, 200, 50);
            
            backMainMenu.setBounds(50, 50, 200, 200);
    
            userAnswer.setForeground(Color.white);
            userAnswer.setOpaque(false);
            
            // add the new components to the frame
            explanationField.setBounds(900, 350, 500, 400);
            explanationField.setText(DefaultText.assessmentExplanation[page]);
            
            correctAnswer.setBounds(900, 250, 500, 100);
            correctAnswer.setText("The correct answer is: " + DefaultText.assessmentAnswers[page]);
            
            frame.add(submitButton);
            frame.add(explanationField);
            frame.add(correctAnswer);
            frame.add(explain);
        }
        
        
    }
    
    // override the actionPerformed method in the actionListener class so that we can monitor changes made in GUI
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // if the user clicked on the back to main menu button
        if (e.getSource() == backMainMenu) {
            // open the main menu
            new CAIMainMenu();
            // close the activity frame
            frame.dispose();
        }
        
        // if the user clicked on the continue button
        if (e.getSource() == next) {
            // record the answer in the text field
            if (!submitted && (page == 7 || page == 8)) {
                userAnswerArray[page] = answerField.getText();
            }
            
            // turn to next page
            page++;
            
            // set up the questions
            question.setText(DefaultText.assessmentQuestions[page]);
            
            // change the background and add all the components
            changeBackground();
            addAllComponents();
        }
        
        // if the user clicked on the go back button
        if (e.getSource() == back) {
            // record the answer in the text field
            if (!submitted && (page == 7 || page == 8)) {
                userAnswerArray[page] = answerField.getText();
            }
            
            // turn to the previous page
            page--;
            
            // set up the questions
            question.setText(DefaultText.assessmentQuestions[page]);
            
            // change the background and add all the components
            changeBackground();
            addAllComponents();
        }
        
        // if the user decided to switch the page with the combo box
        if (e.getSource() == pageSwitchButton) {
            // get the question the user has selected, and jump to that page
            int desiredPage = pageSwitch.getSelectedIndex();
            // if the user want to jump to the current page, ignore the request
            if (desiredPage == page) {
                return;
            }
            // set the page number to be desirecPage - 1
            page = desiredPage - 1;
            // click on the next button to go to the next screen
            next.doClick();
        }
        
        // record the option the user has selected when the user select a radio button
        if (Objects.equals(e.getActionCommand(), "A") || Objects.equals(e.getActionCommand(), "B") ||
                Objects.equals(e.getActionCommand(), "C") || Objects.equals(e.getActionCommand(), "D")) {
            // put the selected answer into the answer Array
            userAnswerArray[page] = e.getActionCommand();
        }
        
        // calculate the result then open the result frame when the user hit submit
        if (e.getSource() == submitButton) {
            // refresh the page to record down the answer
            if (page == 0) {
                next.doClick();
                back.doClick();
            } else {
                back.doClick();
                next.doClick();
            }
            
            // calculate the score and open the result frame
            calculateScore();
            new CAIResultScreen();
            
            // mark as submitted
            submitted = true;
            
            // refresh the page
            if (page == 0) {
                next.doClick();
                back.doClick();
            } else {
                back.doClick();
                next.doClick();
            }
            
            
        }
        
    }
    
    // getters for the user answer array and score
    public static String[] getUserAnswerArray() {
        return userAnswerArray;
    }
    
    public static int getScore() {
        return score;
    }
}
