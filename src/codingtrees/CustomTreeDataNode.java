package codingtrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Leaf node in the tree which carries the character data.
 */
class CustomTreeDataNode implements CustomTreeNode {

  private final char data;

  /**
   * Constructor for CustomTreeDataNode node. Assigns the data to the node.
   *
   * @param data data that needs to be assigned to the node.
   */
  public CustomTreeDataNode(char data) {
    //System.out.println(data);
    this.data = data;
  }

  @Override
  public CustomTreeNode add(char[] symbolCode, char data) {
    // do nothing
    return null;
  }

  @Override
  public List<String> getAllPaths(String currentPath) {
    List<String> paths = new ArrayList<>();
    paths.add(currentPath + "-" + data);
    return paths;
  }

  @Override
  public LinkedList<Character> findInPath(LinkedList<Character> encodedMsg) {
    encodedMsg.addFirst(data);
    return encodedMsg;
  }

}
