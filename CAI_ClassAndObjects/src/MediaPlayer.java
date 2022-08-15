import java.awt.*;
import java.io.File;
import java.io.IOException;

/*
Zheng Pei
Description: this class is being used to play a video, it will print the stack trace when IO exception occurred

if you want to play a video by using this class, initialize an object of this class and pass in the videoFilePath as
a parameter

 */

public class MediaPlayer
{
    // the constructor
    public MediaPlayer(String videoFilePath)
    {
        try{
            // open the video file with the computer default media player
            Desktop.getDesktop().open(new File(videoFilePath));
        }
        catch (IOException e){
            // print the stack trace once IO exception occurred
            e.printStackTrace();
        };
    }
}
