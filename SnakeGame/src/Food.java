import java.util.Random;

public class Food {
    // randomizer
    private static Random r = new Random();
    
    // indicate whether the food has been eated
    public static boolean eaten = true;
    
    // the x and y coordinate of the newly generated food
    public static int random_x;
    public static int random_y;
    
    // generate a new set of coordinate for food, also make the variable:eaten = false
    public static void generateCoordinate(){
        eaten = false;
        
        // generate from 1 - 16
        random_x = r.nextInt(15) + 1;
        random_y = r.nextInt(15) + 1;
    }
    
    
    
}
