/*HW 1 -> User Login
 *Sarah Ferguson
 * September 2017
 * Evaluates inputted userlogins and then prints out the result along with error log (if invalid)
 */

//FINAL CODE!!

import java.util.Scanner;
import java.util.regex.*;

public class UserLogin {

    //edited: removed boolean stringValidity
    //edited: added numLength, numChar, numInvalidChar
    //edited: added boolean for each case check
    public String login;
    public boolean checkUpperCase, checkLowerCase, checkSpace, checkDigits, checkInvalidChar, checkChar, checkLength;
    public int numLength, numChar, numInvalidChar;
    public StringBuffer report = new StringBuffer();

    public static void main(String[] args)
    //edited: added scanner newKeyboard to keep yes/no and login seperated
    {
        UserLogin test = new UserLogin();
        System.out.print(test.greetUser());

        boolean loop = true;

        while (loop) {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("\nWould you like to test some data? (Type yes or no)");
            if (keyboard.nextLine().equalsIgnoreCase("Yes")) {
                Scanner newKeyboard = new Scanner(System.in);
                System.out.println("\nEnter in a UserLogin");
                test.readUser(newKeyboard.nextLine());
                test.addToReport();
            }
            else {
                System.out.print(test.printReport());
                loop = false;
            }
        }
    }

    public UserLogin()
    //default constructor
    {
        login = "";
        checkUpperCase = false;
        checkLowerCase = false;
        checkSpace = false;
        checkDigits = false;
        checkInvalidChar = true;
        checkChar = false;
        checkLength = false;
        numLength = 0;
        numChar = 0;
        numInvalidChar = 0;
    }

    public String greetUser()
    //this method will return a brief description of the program
    {
        return "This program will evaluate inputted UserLogins and will determine whether or not they are valid on specific requirements";
    }

    public String getUser()
    //edited: wasn't sure at first what to do with this method, but decided to use it as a "getter"
    {
        return login;
    }

    public void readUser(String loginFromUser)
    //this method will read the user input from scanner
    //edited: added statements to set user back to "default", so userlogins on looped would return to a false state rather than the same as the one before it
    {
        login = loginFromUser;
        checkUpperCase = false;
        checkLowerCase = false;
        checkSpace = false;
        checkDigits = false;
        checkInvalidChar = false;
        checkChar = false;
        checkLength = false;
        numLength = 0;
        numChar = 0;
        numInvalidChar = 0;
    }

    public void checkUpperCase()
    //edited: used a for loop to check for each individual uppercase letter
    //edited: used a for while loop with char and ASCII values to check
    //edited: the loops had used a variable numUpperCase
    {
        String string = login;
        Pattern p = Pattern.compile("[A-Z]");
        Matcher m = p.matcher(string);

        if (m.find())
            checkUpperCase = true;
        else
            checkUpperCase = false;
    }

    public void checkLowerCase()
    //edited: used a for loop to check for each individual lowercase letter
    //edited: used a for while loop with char and ASCII values to check
    //edited: the loops had used a variable numLowerCase
    {
        String string = login;
        Pattern p = Pattern.compile("[a-z]");
        Matcher m = p.matcher(string);

        if (m.find())
            checkLowerCase = true;
        else
            checkLowerCase = false;
    }

    public void checkSpace()
    //edited: used a for loop to check for each inidividual blank space
    //edited: in said for loop, replaced the actual keys with backspace characters "\t"
    //edited: the loops had used a variable numSpaces
    {
        String string = login;
        Pattern p = Pattern.compile("[\\s]");
        Matcher m = p.matcher(string);

        if (m.find())
            checkSpace = true;
        else
            checkSpace = false;
    }

    public void checkLength() {
        //edited: the for loop also accounted for blank spaces
        //edited: the if statement had if (numLength>= 5 && (!checkSpaces())
        for (int i = 0; i < login.length(); i++) {
            numLength++;
        }

        if (numLength >= 5)
            checkLength = true;
        else
            checkLength = false;
    }

    public void checkDigits()
    //edited: used a for loop to check for each digit
    //edited: used a for while loop with char and ASCII values to check
    //edited: the loops had used a variable numDigits
    //edited: pattern compiled "[0-9]"
    {
        checkDigits = false;

        String string = login;
        Pattern p = Pattern.compile("[\\d]");
        Matcher m = p.matcher(string);

        if (m.find())
            checkDigits = true;
        else
            checkDigits = false;
    }

    public void checkChar()
    //edited: used a for loop to check for each char
    //edited: the loops had used a variable numChar
    //edited: removed regular expression
    {
        for (int i = 0; i < login.length(); i++) {
            if (login.substring(i,i+1).equals("!") || login.substring(i, i+1).equals("@") || login.substring(i, i+1).equals("#") || login.substring(i, i+1).equals("$"))
                numChar++;
        }

        if (numChar > 0)
            checkChar = true;
        else
            checkChar = false;

        //String string = login;
        //Pattern p = Pattern.compile("^[!@#$]+$");
        //Matcher m = p.matcher(string);

        //if (m.find())
        //checkChar = true;
        //else
        //checkChar = false;
    }

    public void checkInvalidChar()
    //edited: used a for loop to check for each invalid char
    //edited: the loops had used a variable numInvalidChar
    //edited: removed regular expression
    {
        for (int i = 0; i < login.length(); i++) {
            if (login.substring(i, i+1).equals("%") || login.substring(i, i+1).equals("^") || login.substring(i, i+1).equals("&") || login.substring(i, i+1).equals("*") || login.substring(i, i+1).equals("(") || login.substring(i, i+1).equals(")") || login.substring(i, i+1).equals("-") || login.substring(i, i+1).equals("_") || login.substring(i, i+1).equals("+") || login.substring(i, i+1).equals("=") || login.substring(i, i+1).equals("[") || login.substring(i, i+1).equals("]") || login.substring(i, i+1).equals("{") || login.substring(i, i+1).equals("}") || login.substring(i, i+1).equals("|") )
                numInvalidChar++;
            else if (login.substring(i, i+1).equals(":") || login.substring(i, i+1).equals(";") || login.substring(i, i+1).equals("'") || login.substring(i, i+1).equals(",") || login.substring(i, i+1).equals(".") || login.substring(i, i+1).equals("<") || login.substring(i, i+1).equals(">") || login.substring(i, i+1).equals("/") || login.substring(i, i+1).equals("?"))
                numInvalidChar++;
            else
                numInvalidChar+=0;
        }

        if (numInvalidChar > 0)
            checkInvalidChar = true;
        else
            checkInvalidChar = false;

        //String string = login;
        //Pattern p = Pattern.compile("[^a-zA-Z!@#$]");
        //Matcher m = p.matcher(string);

        //if (m.find())
        //checkInvalidChar = true;
        //else
        //checkInvalidChar = false;
    }

    public boolean checkValidity()
    //edited: originally was a void method
    //edited: removed the if statements with report.append
    //edited: added the methods to make sure the program went through it and updated values
    {
        checkUpperCase();
        checkLowerCase();
        checkSpace();
        checkChar();
        checkInvalidChar();
        checkLength();
        checkDigits();

        if (checkChar && checkDigits && checkLowerCase && checkUpperCase && checkLength && (checkInvalidChar == false) && (checkSpace == false))
            return true;
        else
            return false;
    }

    public String printUser()
    //edited: added the if statements with report.append
    //edited: added result and concanenated it
    //edited: removed report.append all the results
    {
        String result = "";
        if (checkValidity())
            result+= "\t(valid)";
        else {
            result+= "\t(invalid)";

            if (checkUpperCase==false)
                result+= "\n--no uppercase letter";
            if (checkLowerCase == false)
                result+= "\n--no lowercase letter";
            if (checkDigits == false)
                result+= "\n--no digits";
            if (checkLength == false)
                result+= "\n--too short (minimum of 5 characters)";
            if (checkSpace == true)
                result+= "\n--no spaces allowed (space, tab, return))";
            if (checkChar == false || checkInvalidChar == true)
                result+= "\n--no special character or invalid special character";
        }
        return result;
    }

    public void addToReport()
    //edited: added the user login beginning part
    {
        report.append("\nUserLogin: " + login + printUser());
    }

    public String printReport() {
        return report.toString();
    }
}
