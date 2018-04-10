import java.util.*;
import java.io.*;

/*
*HW 2 -> Countries Using Array and LinkedList
*Sarah Ferguson
*November/December 2017
*Program will read a list of countries and give choices for user to choice from to list data.
*/
public class HW2 {
    //PIV
    public Countries[] countriesArray;
    public Borders[] bordersArray;
    public myLL countryLL;
    public static boolean temp;

    //Constructor
    public HW2() {
        temp = true;
    }

    public static void main(String[] args) throws IOException {
        HW2 test = new HW2();
        test.greet();
        while (temp) {
            test.greetUser();
        }
    }

    public void greet() {
        //greets the user by giving description of program and asks for file input
        System.out.println("Pick an option to do: (Type the number choice)");
        System.out.println("1. Import the data");
        System.out.println("2. Display list of all countries that border Germany");
        System.out.println("3. Display list of all countries that have a population greater than 10 million.");
        System.out.println("4. Display list of countries that border Germany and have a population greater than 10 million.");
        System.out.println("5. Quit the program.");
    }

    public void greetUser() throws IOException {
        //user input
        Scanner keyboard = new Scanner(System.in);
        int input = keyboard.nextInt();
        if (input == 1) {
            readFile();
            temp = true;
        }
        if (input == 2) {
            borderGermany();
            temp = true;
        }
        if (input == 3) {
            popGreater();
            temp = true;
        }
        if (input == 4) {
            borderAndPop();
            temp = true;
        }
        if (input == 5) {
            temp = false;
            return;
        }
    }

    public void readFile() throws IOException {
        try {
            //reads the countries and borders file
            Scanner inputFile = new Scanner(new FileReader("Countries.txt"));
            Scanner inputFile2 = new Scanner(new FileReader("Borders.txt"));
            countriesArray = new Countries[9];
            countryLL = new myLL();
            int i = 0;
            while (inputFile.hasNext()) {
                Countries temp = new Countries();
                String name = inputFile.nextLine();
                //splits up each line into an array, uses comma as a delimiter
                String[] ar = name.split(",");
                //takes each value in the array and sets it to its corresponding part to country temp
                temp.setCountryName(ar[0]);
                temp.setLatitude(ar[1]);
                temp.setLongitude(ar[2]);
                temp.setCountryArea(Integer.parseInt(ar[3]));
                temp.setCountryPopulation(Integer.parseInt(ar[4]));
                temp.setCountryGDP(Double.parseDouble(ar[5]));
                temp.setCountryYear(Integer.parseInt(ar[6]));
                //adds newly defined Country temp into linked list and array
                countriesArray[i] = temp;
                i++;
                countryLL.addFront(temp);
            }
            bordersArray = new Borders[10];
            int j = 0;
            while (inputFile2.hasNext()) {
                Borders temp = new Borders();
                String name = inputFile2.nextLine();
                String[] ar = name.split(",");
                temp.setCountry1(ar[0]);
                temp.setCountry2(ar[1]);
                bordersArray[j] = temp;
                j++;
            }
            System.out.println("Data successfully imported.");
        } catch (FileNotFoundException exception) {
            System.out.println("File not found.");
        }
    }

    public void borderGermany() {
        if (bordersArray==null)
            //checks to make sure data is imported first
            System.out.println("Data hasn't been imported. Try again.");
        else {
            System.out.println("These countries border Germany: ");
            for (int i = 0; i < bordersArray.length; i++) {
                if (bordersArray[i].country1.equalsIgnoreCase("Germany"))
                    System.out.println(bordersArray[i].getBorder("Germany"));
            }
        }
    }

    public void popGreater() {
        if (countriesArray==null)
            System.out.println("Data hasn't been imported. Try again.");
        else {
            System.out.println("These countries have a population greater than 10 million: ");
            //for linked list
            for (myLL.Node temp = countryLL.myHead; temp!= null; temp = temp.getNext()) {
                if (temp.getMyValue().getCountryPopulation() > 10000000) {
                    System.out.println(temp.getMyValue().countryName);
                }
            }
        }
    }

    public void borderAndPop() {
        if (countriesArray==null)
            System.out.println("Data hasn't bee imported. Try again.");
        else {
            System.out.println("These countries border Germany and have a population greater than 10 million: ");
            //for arraylist
            for (int j = 0; j < bordersArray.length; j++) {
                String temp = bordersArray[j].getBorder("Germany");
                for (int i = 0; i < countriesArray.length; i++) {
                    Countries temp2 = countriesArray[i];
                    if (temp.equalsIgnoreCase(temp2.getCountryName()) && temp2.getCountryPopulation() > 10000000) {
                        System.out.println(temp2.getCountryName());
                    }
                }
            }
        }
    }
}
