/*
Zheng Pei

Description: this class included text that will be used in the other classes, specifically
1. the text for the pre-written application class in the activity screen
2. the text for the pre-written cat class in the activity screen
3. the questions in the assessment screen
4. the answer for each question in the assessment screen
5. the explanation for each question in the assessment screen
 */
public class DefaultText {
    // this is the text for the pre-written cat class in the activity screen
    public static final String textForUserCreatedClass = "\n" +
            "// the cat class that simulates the behaviour of a cat \n"+
            "public class Cat {\n" +
            "\t // field variables\n"+
            "\tpublic int age = 1;\n" +
            "\tprivate boolean happy = true;\n" +
            "\n" +
            "\t// the constructor that initialize the age of the cat\n"+
            "\tpublic Cat(int age){\n" +
            "\tthis.age = age;\n" +
            "\t}\n" +
            "    \n" +
            "\t// a method that will print different message based on whether the cat is happy or not\n"+
            "\tpublic void patTheCat(){\n" +
            "\t\tif(happy){\n" +
            "\t\t\t System.out.println(\"meow\");\n" +
            "\t\t}else{\n" +
            "\t\t\tSystem.out.println(\"hiiiiiiiiiss\");\n" +
            "\t\t}\n" +
            "\t}\n" +
            "    \n" +
            "\t// getters and setters\n" +
            "\tpublic int getAge() {\n" +
            "\t\treturn age;\n" +
            "\t}\n" +
            "    \n" +
            "\tpublic void setAge(int age) {\n" +
            "\t\tthis.age = age;\n" +
            "\t}\n" +
            "    \n" +
            "\tpublic boolean isHappy() {\n" +
            "\t\t return happy;\n" +
            "\t}\n" +
            "    \n" +
            "\tpublic void setHappy(boolean happy) {\n" +
            "\t\tthis.happy = happy;\n" +
            "\t}\n" +
            "\n" +
            "\t// the toString method\n" +
            "\tpublic String toString(){\n" +
            "\t\tif(happy){\n" +
            "\t\t\treturn \"the cat is \" + age + \" years old, it is happy right now\" ;\n" +
            "\t\t}else{\n" +
            "\t\t\treturn \"the cat is \" + age + \" years old, it is unhappy right now\" ;\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}\n";
    
    // this is the text for the pre-written application class in the activity screen
    public static final String textForTheApplicationClass = "\n" +
            "// do not use the Scanner class or any other input class as the CAI program does not support input\n" +
            "\n"+
            "// the application class\n"+
            "public class Application {\n" +
            "\t// the main method \n"+
            "\tpublic static void main(String[] args) {\n" +
            "\t\t// create an object of the cat class that has age of 5\n"+
            "\t\tCat cat1 = new Cat(5);\n" +
            "\t\t// print of age of the cat \n"+
            "\t\tSystem.out.println(cat1.getAge());\n" +
            "\t\t// pat the cat(a cat is default to be happy)\n"+
            "\t\tcat1.patTheCat();\n" +
            "        \n" +
            "\t\t// create another object of the cat class that has age of 1\n"+
            "\t\tCat cat2 = new Cat(1);\n" +
            "\t\t// print the age of the cat 2(now he is one year old)\n"+
            "\t\tSystem.out.println(cat2.getAge());\n" +
            "\t\t// change the age of the cat 2 to 7 \n"+
            "\t\tcat2.setAge(7);\n" +
            "\t\t// print the age(now he is 7 years old)\n"+
            "\t\tSystem.out.println(cat2.getAge());\n" +
            "        \n" +
            "\t\t// change the cat2 to unhappy before pat the cat\n"+
            "\t\tcat2.setHappy(false);\n" +
            "\t\tcat2.patTheCat();\n" +
            "\n" +
            "\t\t// use toString method to check the information\n"+
            "\t\tSystem.out.println(cat2.toString());\n" +
            "\t}\n" +
            "}\n";
    
    // this is an array contains the texts for each question in the assessment screen
    public static final String[] assessmentQuestions = {
            "<html>question1: how to hide your class variables and class functions from other classes and objects<br><br>" +
                    "A: use keyword static <br>" +
                    "B: use private access modifier<br>" +
                    "C: write a function to block these requests<br> " +
                    "D: use class java.util.protect" +
                    "</html>",
            
            "<html>question2: How do you create the constructor for class Car<br><br>" +
                    "A: public void Car(){}<br>" +
                    "B: private Car(){}<br>" +
                    "C: public Tesla(){}<br>" +
                    "D: none of the above" +
                    "</html>",
            
            "<html>question3: If you want to get the value of a private variable called age inside of a well-written class, " +
                    "what would you do<br><br>" +
                    "A: className.age<br>" +
                    "B: className.getAge()<br>C: className->age<br>" +
                    "D: className::age()" +
                    "</html>",
            
            "<html>question4: What will be the output of the following program when an object of this class is initialized " +
                    "and the method makeChange is being called through that object<br><br>" +
                    "class Book{<br>" +
                    "&emsp;public static int count = 100;<br>" +
                    "&emsp;public Book(){};<br>" +
                    "&emsp;public static void makeChange(){<br>" +
                    "&emsp;&emsp;Book b1 = new Book();<br>" +
                    "&emsp;&emsp;b1.count++;<br>" +
                    "&emsp;&emsp;Book b2 = new Book();<br>" +
                    "&emsp;&emsp;System.out.println(b2.count); <br>" +
                    "&emsp;}<br>" +
                    "}<br>" +
                    "A: 100, B: 101, C: 102, D: error</html>",
            
            "<html>question5: Which statement does not create an object of class Book{}<br><br>" +
                    "A: Book B1 = new Book()<br>" +
                    "B: new Book();<br>" +
                    "C: Book B1;<br>" +
                    "D: Book rqugfyvh = new Book()<br>" +
                    "</html>",
            
            "<html>question6: What will be the output of the following program when an Object of this class is being " +
                    "created?<br><br>" +
                    "class Test<{<br>" +
                    "&emsp;int x = 10; <br>" +
                    "&emsp;public void assign(int x){<br>" +
                    "&emsp;&emsp;x = x;  <br>" +
                    "&emsp;&emsp;System.out.println(this.x);<br>" +
                    "&emsp;}<br>" +
                    "&emsp; public Test(){<br>" +
                    "&emsp;&emsp; assign(100);<br>" +
                    "&emsp;}<br>" +
                    "}<br>" +
                    "A: 10, B: 100, C: 110, D:error</html>",
            
            "<html>question7: You are creating a class that simulates a bank card, it has two variables: String cardType and " +
                    "String password, what access modifier would you use for these two variables<br><br>" +
                    "A: cardType - private, password - private<br>" +
                    "B: cardType - public, password - public<br>" +
                    "C: cardType - private password - public<br>" +
                    "D: cardType - public, password - private" +
                    "</html>",
            
            "<html>question8: type the name of the method that will be called when a class is initialized</html>",
            
            "<html>question9: type the name of the method that returns all the information of an object in a string format</html>",
    };
    
    // this is an array contains the answers for each question in the assessment screen
    public static final String[] assessmentAnswers = {"B", "D", "B", "B", "C", "A", "D", "constructor", "toString"};
    
    // this is an array contains the explanations for each question in the assessment screen
    public static final String[] assessmentExplanation = {
            "<html>the private access modifier will make a variable inaccessible form other classes</html>",
            
            "<html>the correct way of doing it should be public Car(){}</html>",
            
            "<html>use the getter of a class to get its private variable</html>",
            
            "<html>static variable share the same storage among different classes, so when b1.count++ is called, the variable count" +
                    "in b1 and b2 were all increased by 1</html>",
            
            "<html>Book B1 only created a variable that has the type of Book, it's not an object of the class</html>",
            
            "<html>Local variable within a method always have higher priority over a field(or instance) variable when " +
                    "there happens to be two variables with same names, unless the keyword this is used, in the code x = x, " +
                    "the local variable x assign the value 100 to itself, and the instance variable x = 10 is unchanged," +
                    "so when we print this.x(this refers to the instance variable x), the value we printed out is 10</html>",
            
            "<html>you can tell the other people about the type of your bank card, but the password should be kept as a secret</html>",
            
            "<html>constructor is called when a class is initialized through creating an instance of that class</html>",
            
            "<html>toString method normally will return all the information of an object in String format</html>",
    };
}
