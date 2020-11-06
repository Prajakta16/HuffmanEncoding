package codingtrees;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation for the custom tree.
 */
class CustomTreeImpl implements CustomTree {

  private CustomTreeNode root;

  /**
   * Constructor.
   */
  public CustomTreeImpl() {
    root = new CustomTreeGroupNode();
  }


  @Override
  public void add(char[] symbolCode, char data) {
    root.add(symbolCode, data);
  }

  @Override
  public List<String> getAllPaths() {
    return root.getAllPaths("");
  }

  @Override
  public LinkedList<Character> findInPath(LinkedList<Character> encodedMsg) {
    return root.findInPath(encodedMsg);
  }
}
