package codingtrees;

import java.util.LinkedList;
import java.util.List;

/**
 * Custom tree node representing of a n-children tree data is stored only at the leaves.
 */
interface CustomTreeNode {

  /**
   * Adds a new object to the BST tree.
   *
   * @param data object to be added into the tree
   * @param symbolCode path at which data needs to be added
   * @return the new tree
   */
  CustomTreeNode add(char[] symbolCode, char data);



  /**
   * Returns the path for all characters for the custom tree.
   *
   * @return the path for all characters for the custom tree
   */
  List<String> getAllPaths(String currentPath);

  /**
   * Find the path with first element of encodedMsg and pass the rest of the msg to children.
   *
   * @param encodedMsg message that needs to be decoded
   * @return linkedList with leftover encoded message and prefixed with discovered character
   */
  LinkedList<Character> findInPath(LinkedList<Character> encodedMsg);
}
