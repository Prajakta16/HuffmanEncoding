package codingtrees;

import java.util.LinkedList;
import java.util.Map;

/**
 * Class for decoder that offers methods to decode a particular encoded message according to the
 * codeDictionary that is provided.
 */
public class Decoder implements DecoderInterface {

  @Override
  public String decodeMessage(Map<Character, String> codeDictionary, String encodedMessage) {
    if (encodedMessage.isEmpty()) {
      return encodedMessage;
    }

    if (codeDictionary.size() == 0) {
      throw new IllegalArgumentException();
    }

    CustomTree decodeTree = generateTree(codeDictionary);
    //System.out.println(decodeTree.getAllPaths().toString());

    return decodeMessageHelper(decodeTree, encodedMessage);
  }

  private String decodeMessageHelper(CustomTree decodeTree, String encodedString) {
    String decodedMsg = "";
    LinkedList<Character> encodedMsg = new LinkedList<>();
    for (char character : encodedString.toCharArray()) {
      encodedMsg.add(character);
    }

    while (encodedMsg.size() > 0) {
      encodedMsg = decodeTree.findInPath(encodedMsg);

      decodedMsg += encodedMsg.getFirst(); // the first index has the encoded character
      //System.out.println(decodedMsg);
      encodedMsg.removeFirst();
    }

    return decodedMsg;
  }

  private CustomTree generateTree(Map<Character, String> codeDictionary) {
    CustomTree customTree = new CustomTreeImpl();

    for (Character c : codeDictionary.keySet()) {
      customTree.add(codeDictionary.get(c).toCharArray(), c);
    }
    return customTree;
  }
}
