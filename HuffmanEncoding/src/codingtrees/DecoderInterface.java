package codingtrees;

import java.util.Map;

/**
 * Interface for decoder that offers methods to decode a particular encoded message according to the
 * codeDictionary that is provided.
 */
public interface DecoderInterface {

  /**
   * Takes in the codeDictionary which has the prefix codes for all symbols along with the encoded
   * message and then returns the decoded message by using trees.
   *
   * @param codeDictionary dictionary having prefix codes for all symbols
   * @param encodedMessage encoded message which needs to be decoded
   * @return decoded message
   */
  String decodeMessage(Map<Character, String> codeDictionary, String encodedMessage);
}
