package codingtrees;

import java.util.Map;

/**
 * Interface representing an Encoder which offers methods to encode a plain text using a dictionary.
 */
public interface EncoderInterface {

  /**
   * Encodes a plain text using a dictionary.
   *
   * @param codeDictionary that has code symbols for every character
   * @param plainMessage message that needs to be encoded
   * @return encoded message
   */
  String encodeMessage(Map<Character, String> codeDictionary, String plainMessage);
}
