/*
*HW 4 -> Sorting
*Sarah Ferguson
*September 2017
*Program will sort and test the time difference between bubble sort and java's built in sorting method.
*/

import java.util.*;
import java.io.*;

public class HW4 {

    public String in;
    public static ArrayList list, bubblesort, javasort;

     public HW4() {
        in = "";
        list = new ArrayList<String>();
        bubblesort = new ArrayList<String>();
        javasort = new ArrayList<String>();
    }

    public static void main(String args[]) throws IOException {
        HW4 test = new HW4();
        test.readTextFile();
        test.toResult();
        test.bubbleSort(list);
        test.javaSort(list);
    }

    public ArrayList<String> readTextFile() throws IOException
    //reads text file and adds each word from file into arraylist
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter in the pathway to the file that is to be sorted:");
        in = keyboard.nextLine();
        //ex: my input would be /Users/sarahdee/IdeaProjects/HW4/src/test.txt

        //if entry is empty, process ends automatically
        if (in.equals(""))
            System.out.println("Entry is null.");

        //takes filepath input and copies everything from file into arraylist
        Scanner s = new Scanner(new File(in));
        //looks at each line from the input line
        while (s.hasNextLine()) {
            String x = s.nextLine();
            //looks at each line as a string sentence and then adds each word separately into list
            for (String word : x.split("\\s+")) {
                //looks at each word and then deletes any special characters before adding to the list
                list.add(word.replaceAll("[^a-zA-Z0-9]", ""));
            }
        }
        s.close();

        //reiterates through list and deletes any empty elements
        for (int i = list.size() - 1; i > -1; i--) {
            if (list.get(i).equals(""))
                list.remove(i);
        }

        //returns final version of arraylist; each word is in an indexed element in arraylist
        //and doesn't include any special characters
        return list;

        //for (int i = 0; i < list.size(); i++) {
            //String temp = "";
            //temp.equals(list.get(i));
            //String res = temp.replaceAll("[^a-zA-Z0-9]", "");
            //list.set(i, res);
        //}

        //for (int i = 0; i < list.size(); i++) {
            //String temp = "";
            //temp.equals(list.get(i));
            //for (int j = 0; j < temp.length(); j++) {
                //Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
                //Matcher match = pt.matcher(temp);
                //while (match.find()) {
                    //String x = match.group();
                    //temp = temp.replaceAll("\\s" + x, "");
                //}
                //list.get(i).equals(temp);
            //}
        //}
    }

    public void toResult()
    //another way of the toString java method
    {
         System.out.println("Filename: " + in);
         System.out.println("Number of words: " + list.size());
         System.out.println("Wall Clock: ");
         System.out.println("\tBubble sort: " + bubbleWallClock());
         System.out.println("\tInternal sort: " + javaWallClock());
         System.out.println("CPU Time: ");
         System.out.println("\tBubble sort: " + bubbleCpuTime());
         System.out.println("\tInternal sort: " + javaCpuTime());

         //System.out.println("This is the bubble sort:\n" + bubblesort);
         //System.out.println("This is the java sort:\n" + javasort);
    }

    public void bubbleSort(ArrayList<String> list) {
        //copies list to bubblesort list so original list stays the same
        bubblesort = list;
        for (int j = 0; j < list.size(); j++) {
            for (int i = j+1; i < list.size(); i++) {
                if ((list.get(i)).compareTo(list.get(j)) < 0) {
                    String t = list.get(j);
                    list.set(j, list.get(i));
                    list.set(i, t);
                }
            }
        }
        //System.out.println("This is the bubblesort result:\n" + bubblesort);
    }

    public long bubbleWallClock() {
         long startTime = System.currentTimeMillis();
         bubbleSort(list);
         long endTime = System.currentTimeMillis();
         return endTime - startTime;
    }

    public long bubbleCpuTime() {
         long startTime = System.nanoTime();
         bubbleSort(list);
         long endTime = System.nanoTime();
         return endTime - startTime;
    }

    public void javaSort(ArrayList<String> list) {
         //copies list to javasort list so original list stays the same
        javasort = list;
        Collections.sort(javasort);
        //System.out.println("This is javasort result:\n" + javasort);
    }

    public long javaWallClock() {
         long startTime = System.currentTimeMillis();
         javaSort(list);
         long endTime = System.currentTimeMillis();
         return endTime - startTime;
    }

    public long javaCpuTime() {
         long startTime = System.nanoTime();
         javaSort(list);
         long endTime = System.nanoTime();
         return endTime - startTime;
    }
}
