/*
Zheng Pei
 Date: June 6th, 2022
 Teacher: Mr. Lee
 Course: ICS3U1
 Description: Concept screen of the object & class CAI application that teaches the user how
 to code an object and test class
 Major Skills: Declaring field variables, initializing a constructor, generating setter & getter
 methods, generating a toString method, and creating an instance of an object class
 */

// Import Java packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Start of the ConceptScreen class
public class CAIConceptScreen implements ActionListener {
	
	// Declare GUI objects
	JFrame frame = new JFrame("Zheng and Elliott - Object & Class CAI Concept Screen");
	JPanel panel = new JPanel() {
		// override the paint component method to paint the panel's background with an image
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(getToolkit().getImage("images/conceptScreenBackground.jpg"), 0, 0, getWidth(), getHeight(), this);
		}
	};
	
	// headers and images
	JLabel title = new JLabel("Concept Screen");
	JLabel terminology = new JLabel("Terminology:");
	JLabel step1 = new JLabel("Step 1 - Create a new object class and declare field variables:");
	JLabel step1Image = new JLabel();
	JLabel step2 = new JLabel("Step 2 - Initialize a constructor with field variables:");
	JLabel step2Image = new JLabel();
	JLabel step3 = new JLabel("Step 3 - Generate setters & getters:");
	JLabel step3Image = new JLabel();
	JLabel step4 = new JLabel("Step 4 - Generate a toString method:");
	JLabel step4Image = new JLabel();
	JLabel step5 = new JLabel("Step 5 - Create an instance of the object class:");
	JLabel step5Image = new JLabel();
	
	// the instructional text
	JLabel terminologyText = new JLabel(ConceptText.terminologyText);
	JLabel step1Text = new JLabel(ConceptText.step1Text);
	JLabel step2Text = new JLabel(ConceptText.step2Text);
	JLabel step3Text = new JLabel(ConceptText.step3Text);
	JLabel step4Text = new JLabel(ConceptText.step4Text);
	JLabel step5Text = new JLabel(ConceptText.step5Text);
	
	// user prompt
	JLabel videoTutorial = new JLabel("Video Tutorial:");
	
	// panels
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	
	// return button
	JButton backButton = new JButton("Back to Main Menu");
	
	// buttons for video
	JButton video1 = new JButton("object class video");
	JButton video2 = new JButton("application class video");
	
	// Constructor method
	public CAIConceptScreen() {
		
		// set up the title
		title.setBounds(600, 15, 500, 50);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setForeground(Color.white);
		panel.add(title);
		
		// set up user prompt
		videoTutorial.setBounds(930, 15, 900, 50);
		videoTutorial.setFont(new Font("Arial", Font.BOLD, 20));
		videoTutorial.setForeground(Color.white);
		panel1.add(videoTutorial);
		
		// set up terminology
		terminology.setBounds(15, 15, 900, 50);
		terminology.setFont(new Font("Arial", Font.BOLD, 20));
		terminology.setForeground(Color.white);
		panel1.add(terminology);
		
		terminologyText.setBounds(15, 65, 900, 250);
		terminologyText.setFont(new Font("Arial", Font.BOLD, 16));
		terminologyText.setForeground(Color.white);
		panel1.add(terminologyText);
		
		// set up the video buttons
		video1.setBounds(930, 60, 500, 100);
		video1.addActionListener(this);
		video1.setOpaque(false);
		video1.setContentAreaFilled(false);
		video1.setForeground(Color.white);
		video1.setBorder(BorderFactory.createRaisedBevelBorder());
		video1.setFont(new Font("Arial", Font.BOLD, 30));
		
		video2.setBounds(930, 180, 500, 100);
		video2.addActionListener(this);
		video2.setOpaque(false);
		video2.setContentAreaFilled(false);
		video2.setForeground(Color.white);
		video2.setBorder(BorderFactory.createRaisedBevelBorder());
		video2.setFont(new Font("Arial", Font.BOLD, 30));
		
		panel1.add(video1);
		panel1.add(video2);
		
		// set up step 1
		step1.setBounds(15, 15, 900, 50);
		step1.setFont(new Font("Arial", Font.BOLD, 20));
		step1.setForeground(Color.white);
		panel2.add(step1);
		
		step1Text.setBounds(15, 65, 800, 250);
		step1Text.setFont(new Font("Arial", Font.BOLD, 16));
		step1Text.setForeground(Color.white);
		panel2.add(step1Text);
		
		step1Image.setIcon(new ImageIcon("images/step1.png"));
		step1Image.setBounds(815, 5, (int) step1Image.getPreferredSize().getWidth(), (int) step1Image.getPreferredSize().getHeight());
		panel2.add(step1Image);
		
		// set up step 2
		step2.setBounds(15, 15, 900, 50);
		step2.setFont(new Font("Arial", Font.BOLD, 20));
		step2.setForeground(Color.white);
		panel3.add(step2);
		
		step2Text.setBounds(15, 55, 800, 250);
		step2Text.setFont(new Font("Arial", Font.BOLD, 16));
		step2Text.setForeground(Color.white);
		panel3.add(step2Text);
		
		step2Image.setIcon(new ImageIcon("images/step2.png"));
		step2Image.setBounds(815, 5, (int) step2Image.getPreferredSize().getWidth(), (int) step2Image.getPreferredSize().getHeight());
		panel3.add(step2Image);
		
		// set up step 3
		step3.setBounds(15, 15, 900, 50);
		step3.setFont(new Font("Arial", Font.BOLD, 20));
		step3.setForeground(Color.white);
		panel4.add(step3);
		
		step3Text.setBounds(15, 65, 800, 150);
		step3Text.setFont(new Font("Arial", Font.BOLD, 16));
		step3Text.setForeground(Color.white);
		panel4.add(step3Text);
		
		step3Image.setIcon(new ImageIcon("images/step3.PNG"));
		step3Image.setBounds(815, 5, (int) step3Image.getPreferredSize().getWidth(), (int) step3Image.getPreferredSize().getHeight());
		panel4.add(step3Image);
		
		// set up step 4
		step4.setBounds(15, 15, 900, 50);
		step4.setFont(new Font("Arial", Font.BOLD, 20));
		step4.setForeground(Color.white);
		panel5.add(step4);
		
		step4Text.setBounds(15, 65, 800, 150);
		step4Text.setFont(new Font("Arial", Font.BOLD, 16));
		step4Text.setForeground(Color.white);
		panel5.add(step4Text);
		
		step4Image.setIcon(new ImageIcon("images/step4.png"));
		step4Image.setBounds(815, 5, (int) step4Image.getPreferredSize().getWidth(), (int) step4Image.getPreferredSize().getHeight());
		panel5.add(step4Image);
		
		// set up step 5
		step5.setBounds(15, 15, 900, 50);
		step5.setFont(new Font("Arial", Font.BOLD, 20));
		step5.setForeground(Color.white);
		panel6.add(step5);
		
		step5Text.setBounds(15, 65, 800, 150);
		step5Text.setFont(new Font("Arial", Font.BOLD, 16));
		step5Text.setForeground(Color.white);
		panel6.add(step5Text);
		
		step5Image.setIcon(new ImageIcon("images/step5.png"));
		step5Image.setBounds(815, 5, (int) step5Image.getPreferredSize().getWidth(), (int) step5Image.getPreferredSize().getHeight());
		panel6.add(step5Image);
		
		// set up the position and background of Jpanels
		panel1.setBounds(15, 80, 1480, 300);
		panel1.setOpaque(false);
		panel1.setBorder(BorderFactory.createRaisedBevelBorder());
		panel.add(panel1);
		
		panel2.setBounds(15, 390, 1480, 300);
		panel2.setOpaque(false);
		panel2.setBorder(BorderFactory.createRaisedBevelBorder());
		panel.add(panel2);
		
		panel3.setBounds(15, 700, 1480, 300);
		panel3.setOpaque(false);
		panel3.setBorder(BorderFactory.createRaisedBevelBorder());
		panel.add(panel3);
		
		panel4.setBounds(15, 1010, 1480, 500);
		panel4.setOpaque(false);
		panel4.setBorder(BorderFactory.createRaisedBevelBorder());
		panel.add(panel4);
		
		panel5.setBounds(15, 1520, 1480, 300);
		panel5.setOpaque(false);
		panel5.setBorder(BorderFactory.createRaisedBevelBorder());
		panel.add(panel5);
		
		panel6.setBounds(15, 1830, 1480, 350);
		panel6.setOpaque(false);
		panel6.setBorder(BorderFactory.createRaisedBevelBorder());
		panel.add(panel6);
		
		// set up the back button
		backButton.setBounds(15, 10, 500, 50);
		backButton.addActionListener(this);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setForeground(Color.white);
		backButton.setBorder(BorderFactory.createRaisedBevelBorder());
		backButton.setFont(new Font("Arial", Font.BOLD, 30));
		panel.add(backButton);
		
		// Determine the user's screen size
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Set up the size of the main panel, and disable screen layout
		panel.setPreferredSize(new Dimension(1080, 2300));
        panel.setLayout(null);
        panel1.setLayout(null);
        panel2.setLayout(null);
        panel3.setLayout(null);
        panel4.setLayout(null);
        panel5.setLayout(null);
        panel6.setLayout(null);
        
        // Add scrolling to the screen
        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        
		// set up the basic properties of Jframe
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setSize((int) screen.getWidth(), (int) screen.getHeight());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
	} // End of the constructor method
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// If backButton is clicked, go back to main menu
		if(e.getSource() == backButton) {
			new CAIMainMenu();
			frame.dispose();
		}
		
		// if video1 button is clicked, open part 1 video
		if(e.getSource() == video1){
			new MediaPlayer("videos/Concept Screen Example Pt. 1.webm");
		}
		
		// if video2 button is clicked, open part 2 video
		if(e.getSource() == video2){
			new MediaPlayer("videos/Concept Screen Example Pt. 2.webm");
		}
	}

} // End of the ConceptScreen class
