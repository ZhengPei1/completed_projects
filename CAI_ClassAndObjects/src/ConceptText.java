/*
Zheng Pei
 Date: June 7th, 2022
 Teacher: Mr. Lee
 Course: ICS3U1
 Description: Text class for the JTextAreas in the ConceptScreen class
 */

// Start of the ConceptText class
public class ConceptText {
	
	// text for terminology
	public static final String terminologyText = "<html>Object - A member/instance of a Java class that has certain attributes <br><br>" +
	
	"Class - A template that allows objects to be created, along with object data types and methods to be defined <br><br>" +
	
	"Access Modifier - The public and private keywords are access modifiers which control the level of access to classes" +
			", attributes, methods, and constructors <br><br>" +
			
	"Static - The static keyword allows a variable/method to be shared across  " +
	"all instances of the class, so that any modifications to that variable/  " +
	"method will also be updated throughout the program.</html>";
	
	// text for step 1
	public static final String step1Text = "<html>What are field variables? " +
			"A non-static variable that is defined within a class, but outside of any method, constructor, or block<br><br>" +
			
			"In the following example, we will create a Book object class with the field variables for the book's title, number of pages, and it's price.<br><br>" +

			"General Syntax: public/private datatype variableName;" +
			
			"Variable Nomenclature:<br>" +
			"- No spaces or special characters, such as ! or @<br> " +
			"- Must begin with a letter  <br>" +
			"- Use camo-case if there are two words (first word is all lowercase, and first letter of second word is capitalized)</html>";
	
	// text for step 2
	public static final String step2Text = "<html> " +
	"What is a constructor? <br><br>" +

	"A method used to set initial values for object attributes  <br><br>" +

	"Next in the example, we will initialize a constructor using the Book object's field variables. <br><br>" +

	"*the keyword \"this\" refers to a class attribute, instead of a parameter with the same name.</html>";
	
	// text for step 3
	public static final String step3Text = "<html>" +
	"What do setters and getters do? <br><br>" +
	" " +
	"A set method is used to assign  " +
	"a parameter to a variable, while  " +
	"a get method is used to return  " +
	"the value of a variable <br><br>" +
	" " +
	"The setters in the example  " +
	"include valid input-checking  " +
	"for the numPages and cost variables.  " +
	"Please try creating the setter and  " +
	"getter methods for cost.</html>";
	
	// text for step4
	public static final String step4Text = "<html>" +
	"What is a toString method? <br><br> " +
	" " +
	"The toString method is used  " +
	"to display all of an object's  " +
	"variable values in a string  " +
	"format.</html>";
	
	// text for step 5
	public static final String step5Text = "<html>" +
	"Finally, we create an instance  " +
	"of the Book object class in a  " +
	"separate application class by  " +
	"assigning it parameters.  <br><br>" +
	" " +
	"From here, please try printing  " +
	"a toString of your new Book,  " +
	"and then try to access/modify  " +
	"it's field variables using the  " +
	"setter and getter methods. <br><br> " +
	" " +
	"General Syntax:  " +
	"objectClass objectName =  " +
	"new objectClass(parameters); </html>";

} // End of the ConceptText class
