/*
*HW 6 -> Sorting Different Data Types
*Sarah Ferguson
*October 2017
*Program will mergesort int,double, and string arrays and time it
*/

import java.util.*;

public class HW6 {
    public int numSize, numBig, realNumSize, wordSize;
    public double realNumBig;
    public ArrayList <Integer> javanum;
    public ArrayList <Double> javadouble;
    public ArrayList <String> javastring;
    //these PIV are used to measure the wall clock and the cpu time
    public long javawallStartTime, javawallEndTime, javacpuStartTime, javacpuEndTime;

    public HW6()
    //default constructor, sets all PIV to default values
    {
        numSize = 0;
        numBig = 0;
        realNumSize = 0;
        realNumBig = 0;
        wordSize = 0;
        javanum = new ArrayList<Integer>();
        javadouble = new ArrayList<Double>();
        javastring = new ArrayList<String>();
    }

    //getter methods used to get the arraylist
    public ArrayList<Integer> getJavanum() {
        return javanum;
    }

    public ArrayList<Double> getJavadouble() {
        return javadouble;
    }

    public ArrayList<String> getJavastring() {
        return javastring;
    }

    //public HW6(int numSize, int numBig) {
        //this.numSize = numSize;
        //this.numBig = numBig;
    //}

    //public HW6(int realNumSize, double realNumBig) {
        //this.realNumSize = realNumSize;
        //this.realNumBig = realNumBig;
    //}

    //public HW6(int wordSize) {
        //this.wordSize = wordSize;
    //}

    public void greetUser() {
        System.out.println("This program will create a random assorted of [int, double, strings] based off user input.");
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Choose what to be sorted: Integer, Double, String (Type int, double, or string)");
        String input = keyboard.nextLine();
        if (input.equalsIgnoreCase("Int"))
            optionInt();
        if (input.equalsIgnoreCase("Double"))
          optionDouble();
        if (input.equalsIgnoreCase("String"))
            optionString();
    }

    public void optionInt() {
        System.out.println("Type in an integer to be considered the SIZE (# of integers in the array to sort)");
        Scanner keyboard = new Scanner(System.in);
        numSize = keyboard.nextInt();
        System.out.println("Type in an integer to be considered the BIG (what will be the biggest number that the random numbers come from)");
        numBig = keyboard.nextInt();

        //creates a mergesort obj in order for the mergesort methods to be used
        MergeSort test = new MergeSort();

        if (numBig < numSize)
            System.out.println("Inputted values are invalid. Size > Big not allowed.");
        else {
            if (numSize < 11) {
                generateInt(test.num, numSize);
                System.out.println("This is the unsorted list: " + test.getNum());
                System.out.println("This is the sorted list: " + test.intMergesort(test.num, 0, numSize - 1));
            }
            else {
                generateInt(test.getNum(), numSize);
                test.intMergesort(test.num, 0, numSize - 1);
                //uses wallclock and cpu clock from the mergesort class
                System.out.println(test.wallClock());
                System.out.println(test.cpuTime());

                //creates a HW6 object to be able to use the java sort in HW6
                HW6 test2 = new HW6();
                generateInt(test2.getJavanum(), numSize);
                test2.javaIntSort();
                System.out.println(test2.wallClock());
                System.out.println(test2.cpuTime());
            }
        }
    }

    private void generateInt(ArrayList<Integer> a, int size) {
        for (int i = 0; i < size; i++) {
            a.add(i, (int) ((numBig) * Math.random()));
        }
    }

    public void optionDouble() {
        System.out.println("Type in an integer to be considered the SIZE (# of integers in the array to sort)");
        Scanner keyboard = new Scanner(System.in);
        realNumSize = keyboard.nextInt();
        System.out.println("Type in a double to be considered the BIG (what will be the biggest number that the random numbers come from)");
        realNumBig = keyboard.nextDouble();

        MergeSort test = new MergeSort();

        if (realNumBig < realNumSize)
            System.out.println("Inputted values are invalid. Size > Big not allowed.");
        else {
            if (realNumSize <= 10) {
                generateDouble(test.getRealNum(), realNumSize);
                System.out.println("This is the unsorted list: " + test.getRealNum());
                System.out.println("This is the sorted list: " + test.doubleMergesort(test.getRealNum(), 0, realNumSize - 1));
            }
            else {
                generateDouble(test.getRealNum(), realNumSize);
                test.doubleMergesort(test.realNum, 0, realNumSize - 1);
                System.out.println(test.wallClock());
                System.out.println(test.cpuTime());

                HW6 test2 = new HW6();
                generateDouble(test2.getJavadouble(), realNumSize);
                test2.javaDoubleSort();
                System.out.println(test2.wallClock());
                System.out.println(test2.cpuTime());
            }
        }
    }

    private void generateDouble(ArrayList<Double> a, int size) {
        for (int i = 0; i < size; i ++) {
            a.add(i, (double) ((realNumBig) * Math.random()));
        }
    }

    public void optionString() {
        System.out.println("Type in an integer to be considered the SIZE (# of integers in the array to sort)");
        Scanner keyboard = new Scanner(System.in);
        wordSize = keyboard.nextInt();

        MergeSort test = new MergeSort();

        if (wordSize < 0)
            System.out.println("Inputted values are invalid. Size < 0 not allowed.");
        else {
            if (wordSize <= 10) {
                generateWord(test.getWord(), wordSize);
                System.out.println("This is the unsorted list: " + test.getWord());
                System.out.println("This is the sorted list: " + test.stringMergesort(test.word, 0, wordSize - 1));
            } else {
                generateWord(test.getWord(), wordSize);
                test.doubleMergesort(test.realNum, 0, realNumSize - 1);
                System.out.println(test.wallClock());
                System.out.println(test.cpuTime());

                HW6 test2 = new HW6();
                generateWord(test2.getJavastring(), wordSize);
                test2.javaStringSort();
                System.out.println(test2.wallClock());
                System.out.println(test2.cpuTime());
            }
        }
    }

    private void generateWord(ArrayList<String> a, int size) {
        for (int i = 0; i < size; i++) {
            a.add(i, randomString());
        }
    }

    private String randomString() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder temp = new StringBuilder();
        Random rnd = new Random();
        while (temp.length() < 7) {
            int index = (int) (rnd.nextFloat() * chars.length());
            temp.append(chars.charAt(index));
        }
        String test = temp.toString();
        return test;
    }

    //uses built in java sort for int
    public ArrayList<Integer> javaIntSort() {
        javawallStartTime = System.currentTimeMillis();
        javacpuStartTime = System.nanoTime();
        Collections.sort(javanum);
        javawallEndTime = System.currentTimeMillis();
        javacpuEndTime = System.nanoTime();
        return javanum;
    }
    //uses built in java sort for double
    public ArrayList<Double> javaDoubleSort() {
        javawallStartTime = System.currentTimeMillis();
        javacpuStartTime = System.nanoTime();
        Collections.sort(javadouble);
        javawallEndTime = System.currentTimeMillis();
        javacpuEndTime = System.nanoTime();
        return javadouble;
    }
    //uses built in java sort for string
    public ArrayList<String> javaStringSort() {
        javawallStartTime = System.currentTimeMillis();
        javacpuStartTime = System.nanoTime();
        Collections.sort(javastring);
        javawallEndTime = System.currentTimeMillis();
        javacpuEndTime = System.nanoTime();
        return javastring;
    }

    public long wallClock() {
        System.out.println("This is the wall clock time for java sort: " );
        return javawallEndTime - javawallStartTime;
    }

    public long cpuTime() {
        System.out.println("This is the CPU time for java sort: ");
        return javacpuEndTime - javacpuStartTime;
    }

    public static void main(String args[]) {
        //creates new HW6 obj so all methods don't have to be static
        HW6 test = new HW6();
        test.greetUser();
    }
}
