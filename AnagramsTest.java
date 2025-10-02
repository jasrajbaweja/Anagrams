/** author: Jasraj Baweja
 * I pledge my honor that I have abided by the Stevens Honor System.
 * CS 284 A
 * Homework 5
 */

package homework5;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class AnagramsTest {

    @Test
    public void testBuildLetterTable() {
        Anagrams testAnagrams = new Anagrams();
        testAnagrams.buildLetterTable();
        assertEquals(testAnagrams.letterTable.get('j'), 29);
        assertEquals(testAnagrams.letterTable.get('a'), 2);
        assertEquals(testAnagrams.letterTable.get('s'), 67);
        assertEquals(testAnagrams.letterTable.get('b'), 3);
    }

    @Test
    public void testMyHashCode(){
        Anagrams testAnagrams = new Anagrams();
        assertEquals(3872198, testAnagrams.myHashCode("steal"));
        assertEquals(3872198, testAnagrams.myHashCode("tales"));
        assertEquals(3872198, testAnagrams.myHashCode("least"));
    }

    @Test
    public void testAddWord(){
        Anagrams testAnagrams = new Anagrams();
        testAnagrams.addWord("sunny");
        testAnagrams.addWord("cloudy");
        assertEquals(2,testAnagrams.anagramTable.size());
    }

    @Test
    public void testGetMaxEntries() throws IOException {
        Anagrams testAnagrams = new Anagrams();
        testAnagrams.buildLetterTable();
        testAnagrams.processFile("words_alpha.txt");
        ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = testAnagrams.getMaxEntries();
        assertEquals(maxEntries.get(0).getValue().size(),15);
    }

    @Test
    void testHashLetter() {
        Anagrams a = new Anagrams();
        long out = a.myHashCode("b");
        assertEquals(3L, out);
    }

    @Test
    void testHashWord() {
        Anagrams a = new Anagrams();
        long out = a.myHashCode("be");
        assertEquals(33L, out);
    }

    @Test
    void testAnagramInit() {
        Anagrams a = new Anagrams();
        assertEquals(0, a.anagramTable.size());
    }

    @Test
    void testLetterTable() {
        Anagrams a = new Anagrams();
        assertEquals(26, a.letterTable.size());
    }

    @Test
    void testKey() {
        Anagrams a = new Anagrams();
        a.addWord("lamp");
        long lampHash = a.myHashCode("lamp");
        assertEquals("lamp", a.anagramTable.get(lampHash).get(0));
    }

    @Test
    void testMaxWord() {
        Anagrams a = new Anagrams();
        a.addWord("earth");
        a.addWord("heart");
        a.addWord("hater");
        a.addWord("frog");
        a.addWord("book");

        ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
        assertEquals(1, maxEntries.size());
        assertEquals(3, maxEntries.get(0).getValue().size());
        assertTrue(maxEntries.get(0).getValue().contains("earth"));
        assertTrue(maxEntries.get(0).getValue().contains("heart"));
        assertTrue(maxEntries.get(0).getValue().contains("hater"));
    }
}
