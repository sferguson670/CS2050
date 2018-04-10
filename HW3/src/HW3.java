import java.util.*;
import java.io.*;

/*
*HW 3 -> Countries Using Built-In ArrayList and LinkedList
*Sarah Ferguson
*November/December 2017
*Program will read a list of countries and give choices for user to choice from to list data.
*/
public class HW3 {
    //PIV
    public ArrayList<Countries> countriesAL;
    public LinkedList<Countries> countriesLL;
    public ArrayList<Borders> borders;
    public static boolean temp;

    //Constructor
    public HW3() {
        countriesAL = new ArrayList<Countries>();
        countriesLL = new LinkedList<Countries>();
        borders = new ArrayList<Borders>();
        temp = true;
    }
    public static void main(String[] args) throws IOException {
        HW3 test = new HW3();
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
        if (input==1) {
            readFile();
            temp=true;
        }
        if (input==2) {
            borderGermany();
            temp=true;
        }
        if (input==3) {
            popGreater();
            temp=true;
        }
        if (input==4) {
            borderAndPop();
            temp=true;
        }
        if (input==5) {
            temp=false;
            return;
        }
    }

    public void readFile() throws IOException {
        try {
            //reads the countries and borders file
            Scanner inputFile = new Scanner(new FileReader("Countries.txt"));
            Scanner inputFile2 = new Scanner(new FileReader("Borders.txt"));
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
                //adds newly defined Country temp into linked list and array list
                countriesAL.add(temp);
                countriesLL.add(temp);
            }
            while (inputFile2.hasNext()) {
                Borders temp = new Borders();
                String name = inputFile2.nextLine();
                String[] ar = name.split(",");
                temp.setCountry1(ar[0]);
                temp.setCountry2(ar[1]);
                borders.add(temp);
            }
            System.out.println("Data successfully imported.");
        }
        catch(FileNotFoundException exception) {
            System.out.println("File not found.");
        }
    }

    public void borderGermany() {
        if (borders.size()<1)
            //checks to make sure data is imported first
            System.out.println("Data hasn't been imported. Try again.");
        else {
            System.out.println("These countries border Germany: ");
            for (int i = 0; i < borders.size(); i++) {
                if (borders.get(i).country1.equalsIgnoreCase("Germany"))
                    System.out.println(borders.get(i).getBorder("Germany"));
            }
        }
    }

    public void popGreater() {
        if (countriesLL.size()<1)
            System.out.println("Data hasn't been imported. Try again.");
        else {
            System.out.println("These countries have a population greater than 10 million: ");
            //for linked list
            for (int i = 0; i < countriesLL.size(); i++) {
                if (countriesLL.get(i).getCountryPopulation() > 10000000) {
                    System.out.println(countriesLL.get(i).getCountryName());
                }
            }
        }
    }

    public void borderAndPop() {
        if (countriesAL.size()<1)
            System.out.println("Data hasn't bee imported. Try again.");
        else {
            System.out.println("These countries border Germany and have a population greater than 10 million: ");
            //for arraylist
            for (int j = 0; j < borders.size(); j++) {
                String temp = borders.get(j).getBorder("Germany");
                for (int i = 0; i < countriesAL.size(); i++) {
                    Countries temp2 = countriesAL.get(i);
                    if (temp.equalsIgnoreCase(temp2.getCountryName()) && temp2.getCountryPopulation()>10000000) {
                        System.out.println(temp2.getCountryName());
                    }
                }
            }
        }
    }
}
