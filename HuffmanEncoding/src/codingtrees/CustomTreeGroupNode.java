package codingtrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Group node in the tree which carries the hash map of applicable paths which form the children of
 * the node.
 */
class CustomTreeGroupNode implements CustomTreeNode {

  private Map<Character, CustomTreeNode> childNodes = null; // the symbol and respective treenode

  /**
   * Constructor for CustomTreeGroupNode node. Assigns an empty hashmap which will be later filled
   * with paths.
   */
  public CustomTreeGroupNode() {
    this.childNodes = new HashMap<>();
  }

  @Override
  public CustomTreeNode add(char[] symbolCode, char data) {
    //System.out.println(Arrays.toString(symbolCode));
    if (symbolCode.length == 1) {
      if (childNodes.containsKey(symbolCode[0])) {
        throw new IllegalArgumentException(); // some other character is present
      }
      childNodes.put(symbolCode[0], new CustomTreeDataNode(data));
    } else { // add a branch to current leaf node and pass rest of the symbols to your child

      if (!childNodes.containsKey(symbolCode[0])) { // create a new group node at the new branch
        childNodes.put(symbolCode[0], new CustomTreeGroupNode());
      }

      char[] newSymbolCode = new char[symbolCode.length - 1];
      for (int i = 1; i < symbolCode.length; i++) {
        newSymbolCode[i - 1] = symbolCode[i];
      }

      // pass the rest of the symbol code path to your appropriate child node
      childNodes.get(symbolCode[0]).add(newSymbolCode, data);
    }
    return this;
  }

  @Override
  public List<String> getAllPaths(String currentPath) {
    List<String> paths = new ArrayList<>();

    for (Character c : childNodes.keySet()) {
      String tempPath = currentPath + c;
      paths.addAll(childNodes.get(c).getAllPaths(tempPath));
    }
    return paths;
  }

  @Override
  public LinkedList<Character> findInPath(LinkedList<Character> encodedMsg) {
    // capture the branch for first element in encoded message
    Character branch = encodedMsg.getFirst();
    encodedMsg.removeFirst();

    return childNodes.get(branch).findInPath(encodedMsg);
  }

}
