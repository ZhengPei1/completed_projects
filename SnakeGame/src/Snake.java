import java.util.LinkedList;

public class Snake {
    // stores the body of the snake
    private LinkedList<Node> snakeBody = new LinkedList<Node>();
    
    // moving direction, 0 up, 1 down, 2 left, 3 right
    private int direction = 3;
    
    // indicate whether the snake is still alive
    private boolean alive = true;
    
    // the score the player gets so far, 1 food = 1 score
    public int score = 0;
    
    // check if it's time to increase the speed of the snake
    public int increaseSpeed = 0;
    
    // constructor
    public Snake(){
        // initialize a snake at the center of the game board
        snakeBody.addFirst(new Node(8, 9));
        snakeBody.addFirst(new Node(9, 9));
        snakeBody.addFirst(new Node(10, 9));
    }
    
    // based on the moving direction, append one node at the head of the snake while deleting one node from the end
    // of the snake
    public void move(){
        switch(direction){
            // moving rightwards
            case 3:{
                // when the snake's head hits a wall, the snake is dead
                if(snakeBody.getFirst().getX() + 1 >= 18 ){
                    alive = false;
                    return;
                }
                snakeBody.addFirst(new Node(snakeBody.getFirst().getX() + 1, snakeBody.getFirst().getY()));
                break;
            }
            // moving leftwards
            case 2:{
                // when the snake's head hits a wall, the snake is dead
                if(snakeBody.getFirst().getX() - 1 < 0 ){
                    alive = false;
                    return;
                }
                snakeBody.addFirst(new Node(snakeBody.getFirst().getX() - 1, snakeBody.getFirst().getY()));
                break;
            }
            
            // moving downwards
            case 1:{
                // when the snake's head hits a wall, the snake is dead
                if(snakeBody.getFirst().getY() + 1 >= 18 ){
                    alive = false;
                    return;
                }
                snakeBody.addFirst(new Node(snakeBody.getFirst().getX() , snakeBody.getFirst().getY() + 1));
                break;
            }

            // moving upwards
            case 0:{
                // when the snake's head hits a wall, the snake is dead
                if(snakeBody.getFirst().getY() - 1 < 0 ){
                    alive = false;
                    return;
                }
                snakeBody.addFirst(new Node(snakeBody.getFirst().getX() , snakeBody.getFirst().getY() - 1));
                break;
            }
        }
        
        // remove the last node
        snakeBody.removeLast();
        
        // when the snake's head hits its body, the snake is dead
        for(int i = 1; i < snakeBody.size(); ++i){
            if(snakeBody.getFirst().getX() == snakeBody.get(i).getX() && snakeBody.getFirst().getY() == snakeBody.get(i).getY()){
                alive = false;
                return;
            }
        }
        
        // check if the snake's head overlapped with food, if overlapped, add a block to the head of the snake
        if(snakeBody.getFirst().getX() == Food.random_x && snakeBody.getFirst().getY() == Food.random_y){
            // food has been eaten
            Food.eaten = true;
            
            // score + 1
            score ++;
            
            // increase speed ++
            increaseSpeed ++;
            
            // based on the moving direction, add one block to snake's body
            switch(direction){
                // moving rightwards
                case 3:{
                    snakeBody.addFirst(new Node(snakeBody.getFirst().getX() + 1, snakeBody.getFirst().getY()));
                    break;
                }
                // moving leftwards
                case 2:{
                    snakeBody.addFirst(new Node(snakeBody.getFirst().getX() - 1, snakeBody.getFirst().getY()));
                    break;
                }
                // moving downwards
                case 1:{
                    snakeBody.addFirst(new Node(snakeBody.getFirst().getX() , snakeBody.getFirst().getY() + 1));
                    break;
                }
        
                // moving upwards
                case 0:{
                    snakeBody.addFirst(new Node(snakeBody.getFirst().getX() , snakeBody.getFirst().getY() - 1));
                    break;
                }
            }
        }
        
        
    }
    
    // getter for snake body
    public LinkedList<Node> getSnakeBody() {
        return snakeBody;
    }
    
    // setter for direction
    public void setDirection(int direction) {
        // snake can't turn its head 180 degrees(can't go to the opposite moving direction)
        if((direction == 3 || direction == 2) && (this.direction == 3 || this.direction == 2)){
            return;
        }
        if((direction == 1 || direction == 0) && (this.direction == 1 || this.direction == 0)){
            return;
        }
        this.direction = direction;
    }
    
    // getter for variable:alive
    public boolean isAlive() {
        return alive;
    }
}
