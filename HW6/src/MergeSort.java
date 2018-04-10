/*
*HW 6 -> Sorting Different Data Types
*Sarah Ferguson
*October 2017
*Program will mergesort int,double, and string arrays and time it
*/

import java.util.*;

public class MergeSort {
    //private instance variables
    public ArrayList<Integer> num;
    public ArrayList<Double> realNum;
    public ArrayList<String> word;
    //used to measure wall lock and cpu time
    public long wallStartTime, wallEndTime, cpuStartTime, cpuEndTime;

    //default constructor, sets all PIV to a value
    public MergeSort() {
        num = new ArrayList<Integer>();
        realNum = new ArrayList<Double>();
        word = new ArrayList<String>();
    }

    //public MergeSort(ArrayList<Integer> num) {
        //this.num = num;
    //}

    //public MergeSort(ArrayList<Double> realNum) {
        //this.realNum = realNum;
    //}

    //public MergeSort(ArrayList<String> word) {
        //this.word = word;
    //}

    //these methods will be used to return unsorted and sorted list
    public ArrayList<Integer> getNum() {
        return num;
    }

    public ArrayList<Double> getRealNum() {
        return realNum;
    }

    public ArrayList<String> getWord() {
        return word;
    }

    private void intMerge(ArrayList<Integer> a, int first, int mid, int last)
    //takes in entire vector, but will merge these sections together:
    //left sublist from a[first]..a[mid]
    //right sublist from a[mid+1]..a[last]
    //precondition: each sublist is already in ascending order
    {
        //a -> reference to array to be sorted
        //first -> starting index of range of values to be sorted
        //mid -> midpoint index of values to be sorted
        //last -> last index of range of values to be sorted

        //copy array into temp array
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i = 0; i < a.size(); i++) {
            temp.add(a.get(i));
        }

        int i = first;
        int j = mid + 1;
        int k = first;

        //copies smallest values from left/right back to original array
        while (i <= mid && j <= last) {
            if (temp.get(i)<=(temp.get(j))) {
                a.set(k, temp.get(i));
                i++;
            }
            else {
                a.set(k, temp.get(j));
                j++;
            }
            k++;
        }
        //copies rest of left side of array into org. array
        while (i<= mid) {
            a.set(k, temp.get(i));
            k++;
            i++;
        }
    }

    public ArrayList<Integer> intMergesort(ArrayList<Integer> a, int first, int last) {
        //recursive mergesort of the array
        //a -> reference to array to be sorted
        //first -> starting index of range of values to be sorted
        //last -> ending index of range of values to be sorted

        wallStartTime = System.currentTimeMillis();
        cpuStartTime = System.nanoTime();

        if (first<last) {
            int mid = first + (last-first)/2;
            intMergesort(a, first, mid);
            intMergesort(a, mid+1, last);
            intMerge(a, first, mid, last);
        }

        wallEndTime = System.currentTimeMillis();
        cpuEndTime = System.nanoTime();

        return a;
    }

    private void doubleMerge(ArrayList<Double> a, int first, int mid, int last)
    {
        ArrayList<Double> temp = new ArrayList<Double>();
        for (int i = 0; i < a.size(); i++) {
            temp.add(a.get(i));
        }
        int i = first;
        int j = mid + 1;
        int k = first;
        while (i <= mid && j <= last) {
            if (temp.get(i)<=(temp.get(j))) {
                a.set(k, temp.get(i));
                i++;
            }
            else {
                a.set(k, temp.get(j));
                j++;
            }
            k++;
        }
        while (i<= mid) {
            a.set(k, temp.get(i));
            k++;
            i++;
        }
    }

    public ArrayList<Double> doubleMergesort(ArrayList<Double> a, int first, int last) {
        wallStartTime = System.currentTimeMillis();
        cpuStartTime = System.nanoTime();
        if (first<last) {
            int mid = first + (last-first)/2;
            doubleMergesort(a, first, mid);
            doubleMergesort(a, mid+1, last);
            doubleMerge(a, first, mid, last);
        }
        wallEndTime = System.currentTimeMillis();
        cpuEndTime = System.nanoTime();

        return a;
    }

    private void stringMerge(ArrayList<String> a, int first, int mid, int last)
    {
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < a.size(); i++) {
            temp.add(a.get(i));
        }
        int i = first;
        int j = mid + 1;
        int k = first;
        while (i <= mid && j <= last) {
            if (temp.get(i).compareTo(temp.get(j))<=0) {
                a.set(k, temp.get(i));
                i++;
            }
            else {
                a.set(k, temp.get(j));
                j++;
            }
            k++;
        }
        while (i<= mid) {
            a.set(k, temp.get(i));
            k++;
            i++;
        }
    }

    public ArrayList<String> stringMergesort(ArrayList<String> a, int first, int last) {
        wallStartTime = System.currentTimeMillis();
        cpuStartTime = System.nanoTime();
        if (first<last) {
            int mid = first + (last-first)/2;
            stringMergesort(a, first, mid);
            stringMergesort(a, mid+1, last);
            stringMerge(a, first, mid, last);
        }
        wallEndTime = System.currentTimeMillis();
        cpuEndTime = System.nanoTime();
        return a;
    }

    public long wallClock() {
        System.out.println("This is the wall clock time for merge sort: " );
        return wallEndTime - wallStartTime;
    }

    public long cpuTime() {
        System.out.println("This is the CPU time for merge sort: ");
        return cpuEndTime - cpuStartTime;
    }
}
