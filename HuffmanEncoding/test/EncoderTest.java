import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import codingtrees.Encoder;
import codingtrees.HuffmanCodeGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class to test Encoder methods.
 */
public class EncoderTest {
  private String plainMessage = "she sells sea shells at the sea shore";
  private HuffmanCodeGenerator huffmanDictionary;
  private Encoder encoder;

  @Before
  public void setUp() throws Exception {
    encoder = new Encoder();
  }

  @Test
  public void testValidDict() {
    Map<Character, String> dict = new HashMap<>();
    String dictText = " :110\na:1110\nr:01101\ns:10\nt:0111\ne:00\nh:1111\nl:010\no:01100";
    String[] lines = dictText.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    try {
      encoder.encodeMessage(dict, plainMessage);
    } catch (Exception e) {
      fail("Exception should not be thrown");
    }
  }

  @Test
  public void testIncorrectPrefixDict() {
    Map<Character, String> dict = new HashMap<>();
    String text = " :110\na:110\nr:110\ns:110\nt:110\ne:110\nh:110\nl:110\no:110";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    try {
      encoder.encodeMessage(dict, plainMessage);
      fail("Exception should have been be thrown");
    } catch (Exception e) {
      //
    }
  }

  @Test
  public void verifyEncodingStringMsg() {
    Map<Character, String> dict = new HashMap<>();
    String text = " :110\na:1110\nr:01101\ns:10\nt:0111\ne:00\nh:1111\nl:010\no:01100";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    try {
      for (Character c : dict.keySet()) {
        assertEquals(dict.get(c), encoder.encodeMessage(dict, String.valueOf(c)));
      }

      String encodedMsg = encoder.encodeMessage(dict, plainMessage);
      assertEquals(encodedMsg, "101111001101000010010101101000111011010111100010010101"
                                       + "1011100111110011111110011010001110110101111011000110100");

      encodedMsg = encoder.encodeMessage(dict, "s");
      assertEquals(encodedMsg, "10");

      encodedMsg = encoder.encodeMessage(dict, "sh");
      assertEquals(encodedMsg, "101111");
    } catch (Exception e) {
      fail("Exception should not be thrown");
    }
  }

  @Test
  public void verifyEncodingNumericMsg() {
    String plainMessage = "123456789";

    Map<Character, String> dict = new HashMap<>();
    String text = "0:111100\n1:111101\n2:110\n3:010\n4:11111\n5:1110\n6:011\n7:101\n8:00\n9:100";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    try {
      for (Character c : dict.keySet()) {
        assertEquals(dict.get(c), encoder.encodeMessage(dict, String.valueOf(c)));
      }

      String encodedMsg = encoder.encodeMessage(dict, plainMessage);
      assertEquals("11110111001011111111001110100100", encodedMsg);
    } catch (Exception e) {
      fail("Exception should not be thrown");
    }
  }

  @Test
  public void verifyEncodingMsgWithSpecialChars() {
    String plainMessage = "#$%^&*!@$%&!%^&@%^@%^%@&^%))<>:?{}?!&^@&^";

    Map<Character, String> dict = new HashMap<>();
    String text = "@:100\n!:0111\n#:00010\n$:0010\n%:110\n&:101\n):0011\n*:00011\n::01000\n"
                          + "{:01011\n<:01001\n}:0000\n>:01010\n^:111\n?:0110";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    try {
      for (Character c : dict.keySet()) {
        assertEquals(dict.get(c), encoder.encodeMessage(dict, String.valueOf(c)));
      }

      String encodedMsg = encoder.encodeMessage(dict, plainMessage);
      assertEquals("0001000101101111010001101111000010110101011111011110110011011"
                           + "1100110111110100101111110001100110100101010010000110010110000"
                           + "01100111101111100101111", encodedMsg);
    } catch (Exception e) {
      fail("Exception should not be thrown");
    }
  }

  @Test
  public void testEmptyMessage() {
    plainMessage = "";
    Map<Character, String> dict = new HashMap<>();
    String text = " :110\na:1110\nr:01101\ns:10\nt:0111\ne:00\nh:1111\nl:010\no:01100";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    try {
      String encodedMsg = encoder.encodeMessage(dict, plainMessage);
      assertEquals(plainMessage, encodedMsg);
    } catch (Exception e) {
      fail("Exception should not be thrown");
    }
  }

  @Test
  public void testInsufficientDict() {
    Map<Character, String> dict = new HashMap<>();
    String text = " :110\na:1110";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    try {
      encoder.encodeMessage(dict, "bad");
      fail("Exception should have been be thrown");
    } catch (Exception e) {
      //
    }
  }
}