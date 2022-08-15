import java.io.*;

/*
Zheng Pei
Description: this class is being used to generate, write and execute java file witten by the user

Functionality:
    - there is no getter and setters for this class as the variables should not be changed externally
    - the constructor will accept a String that contains the code written by user
    - method generateJavaFile() this method will call the extractClassName method and writeFile method to generate a
    file end in .java
    - method compileFile(), this method will compile all the files in the C:\CAIGeneratedCode folder, so it can be
    called by any instance, you only need to call this method once, this method will return false if it weren't able to
    compile the files
    - method executeFiles() this method will run the code that's stored in the instance, so it should only be called by the
    instance of the application class
    - method extractClassName(), this is a private method as it should not be called outside the class, this class
    will extract the name of the class from the user's program, and use it as the file name, it returns false
    if it didn't find the name of the class
    - method writeFile(), this is a private method as it should not be called outside the class, this class will write
    code to the file

You should call these methods in the following order:
constructor - generateJavaFile() - compileFile() - executeFiles()

 */
public class CAICodeExecution {
    // the name of the class(without .java)
    private String className;
    // the code
    private String code;
    // the name of the file generated(with .java)
    private String fileName;
    
    // the constructor, it will accept the String code from the class that initialize it
    public CAICodeExecution(String code) {
        this.code = code;
    }
    
    // this method will call the extractClassName method and writeFile method to generate a file end in .java
    public void generateJavaFile() {
        // if can't find the name of the class
        if (!extractClassName()) {
            // end method call
            return;
        }
        writeFile();
    }
    
    // this method will compile all the files in the C:\CAIGeneratedCode folder, so it can be called by any instance,
    // you only need to call this method once, this method will return false if it weren't able to compile the files
    public boolean compileFile() {
        try {
            // whether files can be compiled or not
            boolean success = true;
            
            // run the compile command
            Process process = Runtime.getRuntime().exec("javac C:\\CAIGeneratedCode\\*.java");
            
            // get the error message from the command line(if there is any), and print all of them in the console on GUI
            String errorMessage = null;
            BufferedReader errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((errorMessage = errorStream.readLine()) != null) {
                // ignore the error command caused by system setting
                if(errorMessage.equals("Picked up JAVA_TOOL_OPTIONS: -Duser.language=en")){
                    continue;
                }
                CAIActivityScreen.consoleOutput.append(errorMessage +"\n");
                // files can't be compiled
                success = false;
            }
            return success;
        } catch (IOException e) {
            // if IOExeption occurred, tell the user that the file didn't compile
            CAIActivityScreen.consoleOutput.append("failed to compile files\n");
            return false;
        }
    }
    
    // this method will run the code that's stored in the instance, so it should only be called by the instance of the application class
    public void executeFiles() {
        try {
            // try to execute the current class
            Process process = Runtime.getRuntime().exec("java -cp C:\\CAIGeneratedCode\\ " + className);
            
            // get the outputStream from command line
            String output = null;
            BufferedReader consoleOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            // tell the user the following texts are program output
            CAIActivityScreen.consoleOutput.append("-----------------------\n the program output:\n");
            while ((output = consoleOutput.readLine()) != null) {
                // show the output on the console of GUI
                CAIActivityScreen.consoleOutput.append(output + "\n");
            }
            
            // get the error message from command line
            String errorMessage = null;
            BufferedReader errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
    
            // tell the user the following texts are error message
            CAIActivityScreen.consoleOutput.append("-----------------------\n the system/error message:\n");
            while ((errorMessage = errorStream.readLine()) != null) {
                // show the error message on the console of GUI
                CAIActivityScreen.consoleOutput.append(errorMessage + "\n");
            }
            
            // wait for the program to finish
            try{
                process.waitFor();
            }catch(InterruptedException e){
            
            }
            // print the program execution code
            CAIActivityScreen.consoleOutput.append("exit value(" + process.exitValue() + ")\n");
            CAIActivityScreen.consoleOutput.append("-------------------\n");
        } catch (IOException e) {
            // if we weren't able to execute the file, show the  error message on the console of GUI
            CAIActivityScreen.consoleOutput.append(e.getMessage());
        }
        
        // after execution, delete all the generated file
        File directory = new File("C:\\CAIGeneratedCode");
        String[] files = directory.list();
        // for all the files in the directory
        for (String file : files) {
            // delete them one by one
            File currentFile = new File("C:\\CAIGeneratedCode", file);
            currentFile.delete();
        }
        
    }
    
    // this class will extract the name of the class from the user's program, and use it as the file name
    // return false if it didn't find the name of the class
    private boolean extractClassName() {
        
        // find the index of the first curly bracket {
        int firstBracket = 0;
        for (int i = 0; i < code.length(); ++i) {
            // if we find the bracket, break the loop
            if (code.charAt(i) == '{') {
                firstBracket = i;
                break;
            }
        }
        
        // find the index for the last and the first character of the word before the curly bracket(this word is the name
        // of the class)
        int lastChar = 0;
        int firstChar = 0;
        for (int i = firstBracket - 1; i > 0; --i) {
            // first we find the last character of the word, skip all the white spaces and empty line
            if (lastChar == 0) {
                if (code.charAt(i) != ' ' || code.charAt(i) != '\n') {
                    lastChar = i + 1;
                }
            } else {
                // after we find the last character of the word, we loop backward to find the first character of the word
                // terminate when there's a white space
                if (code.charAt(i) == ' ') {
                    firstChar = i + 1;
                    break;
                }
            }
        }
        // generate the classNmae based on the information we have
        if (firstBracket != 0 && lastChar - firstChar >= 1) {
            className = code.substring(firstChar, lastChar);
            // remove bank space in the classNmae
            className = className.trim();
        }
        
        // check if we were able to extract the class name
        if (className == null) {
            // tell the user we didn't find the class name and vie him an example class
            CAIActivityScreen.consoleOutput.append("unable to extract class name, please make sure to write your class as:\n" +
                    "public class + className{\n" +
                    "   code\n" +
                    "}\n");
            return false;
        } else {
            // generate the file name
            CAIActivityScreen.consoleOutput.append("extracted class name: " + className + "\n");
            fileName = className + ".java";
            return true;
        }
        
    }
    
    // the method that will write code to the file
    private void writeFile() {
        // set up a file object that points to the source folder
        File createFile = new File("C:\\CAIGeneratedCode" + File.separator + fileName);
        
        try {
            // try to create the file
            if (createFile.createNewFile()) {
                CAIActivityScreen.consoleOutput.append("file created successfully\n");
            } else {
                CAIActivityScreen.consoleOutput.append("file with the same name already exist, override the old file\n");
            }
            ;
        } catch (IOException e) {
            // print error message if IOException occurred when creating the file
            CAIActivityScreen.consoleOutput.append("some error occurred when trying to create the file\n");
        }
        
        try {
            // create a Printwriter to write code to the file
            PrintWriter writer = new PrintWriter("C:\\CAIGeneratedCode" + File.separator + fileName);
            // write the code
            writer.print(code);
            // close the writer
            writer.close();
            CAIActivityScreen.consoleOutput.append("Successfully write to the file.\n");
        } catch (IOException e) {
            // print error message if IOException occurred when writing to the file
            CAIActivityScreen.consoleOutput.append("failed to write to the file\n");
        }
    }
    
    
}
