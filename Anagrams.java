/** author: Jasraj Baweja
 * I pledge my honor that I have abided by the Stevens Honor System.
 * CS 284 A
 * Homework 5
 */

package homework5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Anagrams {

    /**
     * This is an array of the first 26 prime numbers.
     * It is used to set up the letterTable.
     * The instance variable anagramTable will hold the main working hash table.
     */
    final Integer[] primes =
            {2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                    31, 37, 41, 43, 47, 53, 59, 61, 67,
                    71, 73, 79, 83, 89, 97, 101};
    Map<Character,Integer> letterTable;
    Map<Long,ArrayList<String>> anagramTable;

    /**
     * This method is invoked by the constructor for the class Anagrams and should build the hash table letterTable.
     */
    public void buildLetterTable() {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        letterTable = new HashMap<>();
        for (int i = 0; i < letters.length(); i++) {
            letterTable.put(letters.charAt(i), primes[i]);
        }
    }

    Anagrams() {
        buildLetterTable();
        anagramTable = new HashMap<Long, ArrayList<String>>();
    }

    /**
     * This method computes the hash code of the string s passed as argument and adds this word to the hash table anagramTable.
     * @param s is the word that is added to the anagramTable
     */
    public void addWord(String s) {
        long hashCode = myHashCode(s);
        ArrayList<String> anagram = anagramTable.getOrDefault(hashCode, new ArrayList<>());
        if (anagram.contains(s)) {
            throw new IllegalArgumentException("Not implemented");
        }
        anagram.add(s);
        anagramTable.put(hashCode, anagram);
    }

    /**
     * This method computes the hash code of the string. All the anagrams of a word should receive the same hash code.
     * @param s is the word to compute the hashcode for
     * @return the hashcode for the word
     */
    public long myHashCode(String s) {
        if(s.equals("")) {
            throw new IllegalArgumentException("Not implemented");
        }
        else if((s.substring(1,s.length())).equals("")) {
            return (long) letterTable.get(s.charAt(0));
        }
        return ((long) letterTable.get(s.charAt(0))) * this.myHashCode(s.substring(1,s.length()));
    }

    /**
     * The main method receives the name of a text file containing words, one per line, and builds the hash table anagramTable. For that it uses the addWord method.
     * @param s is the name of file that is processed
     * @throws IOException occurs if there is an error while reading the file
     */
    public void processFile(String s) throws IOException {
        FileInputStream fstream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            this.addWord(strLine);
        }
        br.close();
    }

    /**
     * This method returns the entries in the anagramTable that have the largest number of anagrams.
     * It returns a list of them since there may be more than one list of anagrams with a maximal size.
      * @return a list of map entries that represent the maximum number of anagrams found in the given anagramTable.
     */
    public ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
        ArrayList<Map.Entry<Long,ArrayList<String>>> output = new ArrayList<>();
        int max = 0;
        for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
            int size = entry.getValue().size();
            if (size == max) {
                output.add(entry);
            }
            else if (size > max) {
                max = size;
                output.clear();
                output.add(entry);
            }
        }
        return output;
    }

    /**
     * This is the main method that reads all the strings in a file and places them in the hash table of anagrams and
     * then iterate over the hash table to report which words have the largest number of anagrams.
     * @param args is the command line arguments and the first argument is expected to be the name of the input file
     */
    public static void main(String[] args) {
        Anagrams a = new Anagrams();

        final long startTime = System.nanoTime();
        try {
            a.processFile("words_alpha.txt");
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
        final long estimatedTime = System.nanoTime() - startTime;
        final double seconds = ((double) estimatedTime/1000000000);
        System.out.println("Time: "+ seconds);
        System.out.println("List of max anagrams: "+ maxEntries);
    }
}
