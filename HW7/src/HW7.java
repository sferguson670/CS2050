/*
*HW 7 -> Do It Yourself Hashtables
*Sarah Ferguson
* November 2017
* Program will create a hashtable in accordance of the words from input file
*/

//first import is for the try/catch blocks
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class HW7 {
    //private instance variables

    //unique arraylist is gonna be the complete list of all the unique (all lowercase, no special char) words from the text file
    public ArrayList<String> unique;
    //uniqueNum arraylist is gonna be the complete list of all the unique words converted to ASCII
    public ArrayList<Integer> uniqueNum;
    //creates the base array to be used for hash table
    LinkedList<String> table[];
    int arraySize;
    int hashNum;

    public HW7() {
        //default constructor

        unique = new ArrayList<>();
        uniqueNum = new ArrayList<>();
        arraySize = 0;
        hashNum = 0;
    }

    public static void main (String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //main method

        //bottom method evaluates to make sure the prime number method is working
        /*Scanner keyboard = new Scanner(System.in);
        int num = keyboard.nextInt();
        HW7 test = new HW7();
        if (test.isPrime(num)) {
            System.out.println("It is prime.");
        }
        else {
            System.out.println("It is not prime.");
            test.getPrime(num);
        }*/

        HW7 test = new HW7();
        test.greetUser();
        if (test.readFile()) {
            test.createHashTable();
            test.findBigBucket();
            test.txtOutput();
            test.javaHashTable();
        }
        else
            return;
    }

    public void javaHashTable() throws FileNotFoundException, UnsupportedEncodingException {
        //uses Java's built in hash table
        //write out keys and values for first 5 keys to plain test file called "HW7.hash.txt"
        //default load factor is 75%
        Hashtable<Integer, String> numbers = new Hashtable(arraySize);
        for (int i = 0; i < unique.size(); i++) {
            numbers.put(i, unique.get(i));
        }
        PrintWriter writer = new PrintWriter("HW7.hash.txt", "UTF-8");
        writer.println("This is the first 5 keys from Java's built in hash table:");
        Set set = numbers.entrySet();
        Iterator itr = set.iterator();
        int count = 0;
        while (count < 6) {
            //prints out the first 5 key - int pairs
            Map.Entry entry = (Map.Entry) itr.next();
            writer.println(entry.getKey() + " : " + entry.getValue());
            count++;
        }
        writer.close();
    }

    public void greetUser() {
        //greets the user by explaining the program
        //EC: asks the user for a prime number
        //EC: run method that checks if its a prime number
        //EC: then evaluate whether or not it is bigger than the n

        Scanner keyboard = new Scanner(System.in);
        System.out.println("This program will create a hash table in accordance with a text file");
        System.out.println("A prime number is needed to create the array size for the hash table.");
        System.out.println("Enter in a prime number. This number will be tested.");
        //for HW5.txt, I will be using 13901 as the inputted prime number
        int num = keyboard.nextInt();
        if (num<=0) {
            System.out.println("Number entered is less than 0. Prime number / array size can't be equal to 0.");
        }
        else if (isPrime(num))
        //this is if the number entered in from the user is a prime number
        {
            System.out.println("Correct! This number was prime. This will be used for the array size of the hash table.");
            arraySize = num;
            hashNum = num;
        }
        else
        //this is if the number entered in from the user is NOT a prime number
        {
            System.out.println("Incorrect. The next highest prime number will be evaluated for you. This will be used for the array size of the hash table.");
            int newPrime = getPrime(num);
            arraySize = newPrime;
            hashNum = newPrime;
        }
    }

    public boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n%2==0)
            return false;
        //if it isn't, check the odds
        for (int i = 3; i*i<=n; i+=2) {
            if (n%i==0)
                return false;
        }
        return true;
    }

    public int getPrime(int n) {
        //int n is a starting point of where to start checking for next (higher) prime number
        boolean temp = false;
        while (temp==false) {
            //checks to see if n is prime, if prime, then returns current n value
            if (isPrime(n)==true)
                temp = true;
                //if n isn't prime, n is incremented by 1, and then goes through the loop again
            else
                n++;
        }
        System.out.println("This is the next highest prime number: " + n);
        return n;
    }

    public boolean readFile() {
        //reads file and gets only the unique words in file

        Scanner keyboard = new Scanner(System.in);
        System.out.println("The program needs a file to be evaluated. Only the unique words with no special characters will be used for the hash table.");
        System.out.println("Enter in the pathway of the file:");
        //System.out.println("Example: /Users/sarahdee/CS2050/HW5.txt");
        String input = keyboard.nextLine();
        try {
            Scanner s = new Scanner(new File(input));
            //looks at each line from the inputl ine
            while (s.hasNextLine()) {
                String x = s.nextLine();
                for (String word : x.split("\\s+")) {
                    //converts each word in all lowercase and removes all special character
                    unique.add(word.toLowerCase().replaceAll("[^a-z0-9]", ""));
                }
            }
            s.close();
            //reiterates through the entire list again
            for (int i = unique.size()-1; i > -1; i--) {
                String temp = unique.get(i);
                //removes any blank elements
                if (temp.equals(""))
                    unique.remove(i);
                else {
                    //removes any duplicates
                    if (unique.indexOf(temp) != unique.lastIndexOf(temp)) {
                        unique.remove(unique.lastIndexOf(temp));
                    }
                }
            }
            //goes through finalized list and converts to ASCII
            convertToAscii();
            return true;
        }
        catch(FileNotFoundException exception) {
            System.out.println("The file was not found.");
            return false;
        }
    }

    public void convertToAscii() {
        //converts a word from string to an int value of ASCII value and puts in new uniqueNum list
        for (int i = 0; i < unique.size(); i++) {
            //after going through inner loop and adding to uniqueNum list, it will reset int and string value
            int result = 0;
            String num = "";
            for (int j = 0; j < unique.get(i).length(); j++) {
                char a = unique.get(i).charAt(j);
                int ascii = (int) a;
                //result+=ascii;
                num+=ascii;
            }
            //since longer words ASCII converted to int would be greater than java's int max value
            //it will take the last 8 digits
            if (num.length()>9) {
                int length = num.length();
                num = num.substring(length-8);
                result = Integer.parseInt(num);
            }
            else
                result = Integer.parseInt(num);
            uniqueNum.add(i, result);
        }

    }

    /*public int concat(int a, int b) {
        //this method concatenates two ints
        //int length = (int) (Math.log10(a)+1);
        int length = String.valueOf(b).length();
        int c = a * 10^length + b;
        return c;
    }*/

    public void createHashTable() {
        //creates a hash table using only the unique words from the file

        //int[] hashtable = new int[arraySize];

        //creates the base array to be used for hash table
        table = new LinkedList[arraySize];
        for (int i = 0; i < arraySize; i++) {
            table[i] = new LinkedList<>();
        }
        for (int i = 0; i < uniqueNum.size(); i++) {
            //uses the ASCII defined from uniqueNum to figure out index
            //but actual uses the unique list in the hash table
            int temp = uniqueNum.get(i);
            int index = hash(temp);
            String word = unique.get(i);
            if (table[index].equals(null))
                //checks to see whether or not the index is empty or not
                table[index].equals(word);
            else {
                //when there is a pre-existing element in the index, the element is added to the linked list
                table[index].add(word);
            }
        }

    }

    public int hash(int num) {
        //this will be the new array index
        //uses mod function
        return num % hashNum;
    }

    public void findBigBucket() {
        //EC: find the biggest bucket aka the longest linked list in the array
        int big = 0;
        int index = 0;
        for (int i = 0; i < table.length; i++) {
            int count = 0;
            for (int j = 0; j < table[i].size(); j++) {
                count++;
            }
            if (count>big) {
                big = count;
                index = i;
            }
        }
        System.out.println("The biggest bucket in the hashtable is index " + index);
        System.out.println("The number of elements in the linked list at that index is " + big);
    }

    public void txtOutput() throws FileNotFoundException, UnsupportedEncodingException {
        //creates a txt file output that lists the index and contents of the linked list for the index values 0 to 5
        //name of file will be : "HW7.out.txt"

        PrintWriter writer = new PrintWriter("HW7.out.txt", "UTF-8");
        //prints out the first 5 filled elements in hashtable
        int count = 0;
        int i = 0;
        while (count<6) {
            if (i >= table.length)
                break;
            else {
                if (table[i].size() > 1) {
                    writer.println(table[i]);
                    count++;
                    i++;
                }
                else
                    i++;
            }
        }
        /*
        for (int i = 0; i < 6; i++) {
            writer.println(hashTable[i]);
         }
         */
        writer.close();
    }
}