/*
*HW 5 -> Sorting and Concordance
*Sarah Ferguson
*October 2017
*Program will create a concordance - a sorted list of words and the number of times each word occurs
*/

import java.util.Comparator;

public class Data {
    //class for the linked list to use
    public String word;
    public int freq;

    public Data() {
        word = "";
        freq = 0;
    }

    public Data(String word, int freq) {
        this.word = word;
        this.freq = freq;
    }

    public int getFreq() {
        return freq;
    }

    public String getWord() {
        return word;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String toString() {
        return word + " \t" + freq;
    }
}
