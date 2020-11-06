package codingtrees;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class representing an Encoder which offers methods to encode a plain text using a dictionary.
 */
public class Encoder implements EncoderInterface {

  /**
   * Takes in the codeDictionary which has the prefix codes for all symbols along with the plain
   * message and then returns the encoded message by assigning appropriate codes to the symbols.
   *
   * @param codeDictionary dictionary having prefix codes for all symbols
   * @param plainMessage   encoded message which needs to be decoded
   * @return encoded message
   */
  @Override
  public String encodeMessage(Map<Character, String> codeDictionary, String plainMessage) {
    if (plainMessage.isEmpty()) {
      return plainMessage;
    }

    char[] plainMessageChars = plainMessage.toCharArray();

    for (int i = 0; i < plainMessageChars.length; i++) {
      if (!codeDictionary.containsKey(plainMessageChars[i])) {
        throw new IllegalArgumentException();
      }
    }

    Set<String> uniqueCodes = new HashSet<>();
    for (Character c : codeDictionary.keySet()) {
      if (uniqueCodes.contains(codeDictionary.get(c))) {
        throw new IllegalArgumentException();
      }
      uniqueCodes.add(codeDictionary.get(c));
    }

    StringBuilder encodedMessage = new StringBuilder();

    for (int i = 0; i < plainMessageChars.length; i++) {
      encodedMessage.append(codeDictionary.get(plainMessageChars[i]));
    }

    return encodedMessage.toString();
  }
}
