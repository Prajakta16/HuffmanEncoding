package codingtrees;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * The Huffman encoding algorithm to generate a binary prefix code, given the source message M is as
 * follows: Create a frequency table (a,f(a)) where a is a symbol in M and f(a) is the number of
 * times a occurs in M. Initialize a coding table (a,g(a)) where a is a symbol in M and g(a) is the
 * desired code for a. All codes are initially empty. Add all symbols into a priority queue Q, using
 * their frequencies as priorities. When the frequencies are the same, the item that is
 * lexicographically earlier has the lower priority. In * general Q contains items with one or more
 * symbols in them. Pop 2 items x and y from the queue * with the lowest frequencies f(x) and f(y)
 * respectively. For each symbol xi in x, add a prefix of * 0 to g(xi). Similarly, for each symbol
 * yi in y, add a prefix of 1 to g(yi). Add a new entry (x.y, * f(x)+f(y)) to Q, where x.y is the
 * concatenation of x and y. If there is more than one item in Q, * go to step 4. Otherwise, report
 * the resulting coding table.
 */
public class HuffmanCodeGenerator implements HuffmanCodeGeneratorInterface {

  class CharSequences implements Comparable<CharSequences> {
    private String charCombinations;
    private int value;

    CharSequences(String charCombinations, int value) {
      if (value < 0 || charCombinations.isEmpty()) {
        throw new IllegalArgumentException();
      }
      this.charCombinations = charCombinations;
      this.value = value;
    }

    private CharSequences() {
    }

    @Override
    public int compareTo(CharSequences other) {
      if (this.value < other.value) {
        return -1;
      } else if (this.value > other.value) {
        return 1;
      } else {
        return this.charCombinations.compareTo(other.charCombinations);
      }
    }

    @Override
    public String toString() {
      return this.charCombinations;
    }

    private String getCharCombinations() {
      return charCombinations;
    }

    private int getValue() {
      return value;
    }
  }

  @Override
  public Map<Character, String> generateHuffmanCodes(String plainMessage, char[] symbols) {
    if (symbols.length == 1 && plainMessage.length() > 1) {
      throw new IllegalArgumentException();
    }

    Set<Character> uniqueSymbols = new HashSet<>();
    for (char symbol : symbols) {
      if (uniqueSymbols.contains(symbol)) {
        throw new IllegalArgumentException();
      }
      uniqueSymbols.add(symbol);
    }

    // get frequency of all characters in the plain message
    Map<Character, Integer> frequency = getFrequency(plainMessage);
    //System.out.println(frequency.toString());

    // initialize priority queue with all characters and prioritize them according to frequency
    PriorityQueue<CharSequences> priorityQueue = initializePriorityQueue(frequency);


    Map<Character, String> codeDictionary = new HashMap<>(); // Stores the code for every character
    int numReqdSymbols;
    String lowestPriorityCharSequence;
    int valueLowestPriorityCharSequence;

    while (priorityQueue.size() > 1) { // until we are nor left with one complete string

      numReqdSymbols = Math.min(priorityQueue.size(), symbols.length);
      int newValue = 0;
      StringBuilder newCharSequences = new StringBuilder();
      for (int i = 0; i < numReqdSymbols; i++) {

        //get the lowest priority string from priorityQueue and assign the symbol_i at the start of
        // every character
        CharSequences charSequences = priorityQueue.poll();

        lowestPriorityCharSequence = charSequences.getCharCombinations();
        valueLowestPriorityCharSequence = charSequences.getValue();

        newCharSequences.append(lowestPriorityCharSequence);
        newValue += valueLowestPriorityCharSequence;

        for (Character character : lowestPriorityCharSequence.toCharArray()) {
          if (!codeDictionary.containsKey(character)) {
            codeDictionary.put(character, "");
          }
          codeDictionary.put(character, symbols[i] + codeDictionary.get(character));
        }
      }
      // adding the new string with new total value to the priority queue
      priorityQueue.add(new CharSequences(newCharSequences.toString(), newValue));
    }

    return codeDictionary;
  }

  private PriorityQueue<CharSequences> initializePriorityQueue(Map<Character, Integer> frequency) {
    PriorityQueue<CharSequences> priorityQueue = new PriorityQueue();

    for (Character characterInPlainMessage : frequency.keySet()) {
      CharSequences seq = new CharSequences(String.valueOf(characterInPlainMessage),
              frequency.get(characterInPlainMessage));
      priorityQueue.add(seq);
    }
    return priorityQueue;
  }

  private Map<Character, Integer> getFrequency(String plainMessage) {
    Map<Character, Integer> frequency = new HashMap<>();
    for (char charac : plainMessage.toCharArray()) {
      if (!frequency.containsKey(charac)) {
        frequency.put(charac, 1);
      } else {
        frequency.put(charac, frequency.get(charac) + 1);
      }
    }
    return frequency;
  }
}
