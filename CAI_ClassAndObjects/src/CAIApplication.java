import java.io.File;

/*
Zheng Pei

Course Code: ICS3U1-01 - Mr.Lee

Title: Class and objects

Description: this program will create a computer-assisted instruction GUI that teaches the user about the fundamentals
of objects & classes through detailed concepts, interactive activities, and challenging assessments.

Major Skills: GUI creation, input, output, shutdown hook to execute codes when user choose to exit the program
, command line file execution for the code written by user(this skill is used in the activity screen), create & destroy
folder and files in the program

Added Features:
the user can code in the activity screen like how they code in eclipse(but obviously not as convenience), it's more like
coding in the notepad

Areas of Concern: None
 */
public class CAIApplication {
    // the main method
    public static void main(String[] args) {
        // open the main menu
        new CAIMainMenu();
        
        // create the source folder for the code user write in the activity screen
        new File("C:\\CAIGeneratedCode").mkdirs();
        
        // create a shutdown hook, this part of the code will be executed when the program exits
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                File directory = new File("C:\\CAIGeneratedCode");
                
                // delete the directory
                directory.delete();
                
                // prompt
                System.out.println("source folder deleted");
            }
        });
    }
}
