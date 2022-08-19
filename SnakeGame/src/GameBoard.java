import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class GameBoard {
    // frame and it's components
    private JFrame frame = new JFrame("Snake Game - Zheng Pei");
    private JPanel gamePanel;
    
    // snake
    private Snake snake = new Snake();
    
    // timer, the snake moves once based on given time interval
    private Timer timer;
    
    // button & text prompt , only show up after the snake is dead
    private JButton playAgain = new JButton("play again");
    private JLabel deadPrompt = new JLabel("game over");
    
    // timer interval of each timer event
    private int delay = 300;
    
    // when the constructor is called, initialize the game's UI and game panel
    public GameBoard() {
        
        initGamePanel();
        initFrame();
        initKeyListener();
        initTimer();
        
    }
    
    // initialize a timer that updates based on the current delay(time interval)
    private void initTimer() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // if the snake is dead, stop the timer
                if (!snake.isAlive()) {
                    timer.stop();
                    // updates the UI with gameEnd method
                    gameEnd();
                    
                    return;
                }
                
                // every time when the user managed to eat three food, stop the timer then reinitialize it with a
                // shorter time interval
                if(snake.increaseSpeed == 3){
                    snake.increaseSpeed = 0;
                    // increases the speed by 10 %
                    delay -= delay/10;
                    timer.stop();
                    initTimer();
                    return;
                }
                
                // otherwise move the snake based on the current direction and repaint the game board
                snake.move();
                gamePanel.repaint();
            }
        };
        
        // set and start the timer
        timer = new Timer(delay, listener);
        timer.setRepeats(true);
        timer.start();
    }
    
    
    // initialize a keylistener to detect the key being pressed
    private void initKeyListener() {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                    snake.setDirection(3);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                    snake.setDirection(2);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                    snake.setDirection(1);
                } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                    snake.setDirection(0);
                }
            }
            
            ;
            
            @Override
            public void keyReleased(KeyEvent e) {
            
            }
            
            ;
        });
    }
    
    
    // initialize the game frame
    private void initFrame() {
        // set the screen size based on the user's screen size
        frame.setSize(735, 755);
        
        // make the game appear in the center of the user's screen
        frame.setLocationRelativeTo(null);
        
        // set default close operation
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        // add the game panel
        frame.add(gamePanel);
        
        // frame cannot be resized
        frame.setResizable(false);
        
        // make the frame visible
        frame.setVisible(true);
        
    }
    
    // initialize the game panel
    private void initGamePanel() {
        
        gamePanel = new JPanel() {
            // paint an 18*18 grid, with each grid composed of 40*40 pixel
            @Override
            public void paint(Graphics g) {
                
                // clear old data
                g.clearRect(0, 0, 735, 755);
                
                
                // draw 19 vertical lines
                for (int i = 0; i < 19; ++i) {
                    g.drawLine(40 * i, 0, 40 * i, 720);
                }
                // draw 19 horizontal lines
                for (int i = 0; i < 19; ++i) {
                    g.drawLine(0, 40 * i, 720, 40 * i);
                }
                
                // draw the snake
                LinkedList<Node> snakeBody = snake.getSnakeBody();
                for (Node n : snakeBody) {
                    g.fillRect(40 * n.getX(), 40 * n.getY(), 40, 40);
                }
                
                // generate the food
                if (Food.eaten) {
                    // generate a set of coordinates for new food
                    Food.generateCoordinate();
                    
                    // keep generating coordinates for new food until the new coordinate is not overlapped with the snake's body
                    while (true) {
                        boolean noOverlap = true;
                        for (int i = 0; i < snakeBody.size(); ++i) {
                            if (snakeBody.get(i).getX() == Food.random_x && snakeBody.get(i).getY() == Food.random_y) {
                                noOverlap = false;
                                Food.generateCoordinate();
                                break;
                            }
                        }
                        if (noOverlap) {
                            break;
                        }
                    }
                }
                // draw the food on the board(make the food shown in blue)
                g.setColor(Color.blue);
                g.fillRect(40 * Food.random_x, 40 * Food.random_y, 40, 40);
                g.setColor(Color.black);
            }
            
        };
        
    }
    
    public void gameEnd() {
        // make a new frame with dead prompt and replay button
        JFrame resultFrame = new JFrame("Snake Game - Zheng Pei");
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setLayout(null);
        resultFrame.setSize(400, 300);
        
        deadPrompt.setBounds(50, 40, 350, 50);
        deadPrompt.setText("game over, score: " + snake.score);
        deadPrompt.setFont(new Font("Segoe Script", Font.BOLD, 30));
        
        playAgain.setBounds(100, 100, 200, 100);
        playAgain.setFont(new Font("Segoe Script", Font.BOLD, 30));
        playAgain.setBorder(BorderFactory.createRaisedBevelBorder());
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameBoard();
                resultFrame.dispose();
                frame.dispose();
                return;
            }
        });
        
        resultFrame.add(deadPrompt);
        resultFrame.add(playAgain);
        
        resultFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        resultFrame.setVisible(true);
        
        return;
    }
    
}
