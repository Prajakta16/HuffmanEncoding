package codingtrees;

import java.util.LinkedList;
import java.util.List;

/**
 * Interface for n-children tree aliased as custom tree.
 */
interface CustomTree {

  /**
   * Adds a new object to the custom tree.
   *
   * @param symbolCode symbol path at which the data needs to be added
   * @param data to be added
   */
  void add(char[] symbolCode, char data);


  /**
   * Returns the character path for the custom tree.
   *
   * @return the character path for the custom tree
   */
  List<String> getAllPaths();

  /**
   * Find the path with first element of encodedMsg and pass the rest of the msg to children.
   *
   * @param encodedMsg message that needs to be decoded
   * @return linkedList with leftover encoded message and prefixed with discovered character
   */
  LinkedList<Character> findInPath(LinkedList<Character> encodedMsg);
}


