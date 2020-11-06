package codingtrees;

import java.util.Map;

/**
 * Interface to depict an huffman code generator.
 */
public interface HuffmanCodeGeneratorInterface {

  /**
   * Takes in a plain text message and a set of symbols, and produce a Huffman dictionary.
   *
   * @param plainMessage plain message
   * @param symbols      symbols to be used to generate huffman codes
   * @return code dictionary representing huffman codes for each distinct character in plain message
   */
  public Map<Character, String> generateHuffmanCodes(String plainMessage, char[] symbols);
}
