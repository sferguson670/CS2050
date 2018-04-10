/*
*HW 5 -> Sorting and Concordance
*Sarah Ferguson
*October 2017
*Program will create a concordance - a sorted list of words and the number of times each word occurs
*/

import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class HW5 {

    public String in;
    public static ArrayList<Data> list;
    public static LinkedList<Data> linkedlist;
    //public HashMap mMap;

    public HW5() {
        in = "";
        list = new ArrayList<Data>();
        linkedlist = new LinkedList<Data>();
        //mMap = new HashMap();
    }

    public static void main(String args[]) throws IOException {
        HW5 test = new HW5();
        test.readTextFile();
        test.toResult();
        //System.out.println(list.toString());
        //System.out.println(linkedlist.toString());
        test.txtOutput();
    }

    public void readTextFile() throws IOException
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
            //looks at each line as a string sentence and then adds each word separately into lists
            for (String word : x.split("\\s+")) {
                //converts each word and converts to all lowercase and replaces all special characters
                list.add(new Data(word.toLowerCase().replaceAll("[^a-zA-Z0-9']", ""), 1));
                linkedlist.add(new Data(word.toLowerCase().replaceAll("[^a-zA-Z0-9']", ""), 1));
            }
        }
        s.close();

        //after adding words, updates the words, and then sorts the lists
        updateData(linkedlist, list);
        sorts(linkedlist, list);
    }

    public void updateData(LinkedList<Data> linkedlist, ArrayList<Data> list) {
        for (int i = list.size() - 1; i > -1; i--) {
            Data obj = list.get(i);
            //if any of the elements of the list are blank, they will be deleted
            if (obj.word.equals(""))
                list.remove(i);
            else {
                //otherwise, a test to check for repeated elements will occur
                for (int j = 0; j < i; j++) {
                    Data obj2 = list.get(j);
                    //if there is a repeated obj, freq of org will go up and the repeated element will be deleted
                    if (obj.word.equals(obj2.getWord())) {
                        obj2.setFreq(obj2.getFreq() + 1);
                        list.remove(obj);
                    }
                }
            }
        }
        //same as above will apply to linked list
        for (int a = linkedlist.size() - 1; a > -1; a--) {
            Data obj = linkedlist.get(a);
            if (obj.word.equals(""))
                linkedlist.remove(a);
            else {
                for (int b = 0; b < a; b++) {
                    Data obj2 = linkedlist.get(b);
                    if (obj.word.equals(obj2.getWord())) {
                        obj2.setFreq(obj2.getFreq() + 1);
                        linkedlist.remove(obj);
                    }
                }
            }
        }
    }

    //public void sort(LinkedList<Data> linkedlist, ArrayList<Data> list) {
        //insertion sort for arraylist
        //for (int i = 0; i <list.size(); i++) {
            //for (int j = i; j > 0; j--) {
                //if (list.get(j).getWord().compareTo(list.get(j-1).getWord()) < 0) {
                    //int index1 = j;
                    //int index2 = j-1;
                    //swapAL(list, index1, index2);
                //}
            //}
        //}

        //insertion sort for linked list
        //for (int a = 0; a < linkedlist.size(); a++) {
            //for (int b = a; b > 0; b--) {
                //if (linkedlist.get(b).getWord().compareTo(linkedlist.get(b-1).getWord()) < 0) {
                    //int index1 = b;
                    //int index2 = b-1;
                    //swapLL(linkedlist, index1, index2);
                //}
            //}
        //}
    //}

    public void sorts (LinkedList<Data> linkedlist, ArrayList<Data> list) {
        //uses java's built in sort but uses a comparator bc its sorting a data class
        Collections.sort(list, new NameComparator());
        Collections.sort(linkedlist, new NameComparator());
    }

    class NameComparator implements Comparator<Data> {
        //created a comparator class that implements java's comparator
        public int compare(Data obj, Data obj2) {
            //redefines the compare method to fit data objects
            return obj.getWord().compareTo(obj2.getWord());
        }
    }

    //public void swapLL(LinkedList<Data> linkedlist, int a, int b) {
        //swaps element for LinkedList
        //Data temp = linkedlist.get(a);
        //linkedlist.set(a, linkedlist.get(b));
        //linkedlist.set(b, temp);
    //}

    //public void swapAL(ArrayList<Data> list, int a, int b) {
        //swaps element for arraylist
        //Data temp = list.get(a);
        //list.set(a, list.get(b));
        //list.set(b, temp);
    //}

    public void toResult()
    //another way of the toString java method
    {
        System.out.println("Filename: " + in);
        System.out.println("Number of words: " + list.size());
    }

    public void txtOutput() throws FileNotFoundException, UnsupportedEncodingException {
        //prints out array result to txt file
        PrintWriter writer = new PrintWriter("HW5.txt", "UTF-8");
        //iterates through arraylist and prints out each word and number of occurences
        for (int i = 0; i < list.size(); i++) {
            writer.println(list.get(i).toString());
        }
        writer.close();
    }
}