import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import codingtrees.Decoder;
import codingtrees.HuffmanCodeGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class to test decoder methods.
 */
public class DecoderTest {
  private String encodedMessage = "101111001101000010010101101000111011010111100010010101"
                                          + "101110011111001111111001101000111011010111101100011"
                                          + "0100";
  private HuffmanCodeGenerator huffmanDictionary;
  private Decoder decoder;

  @org.junit.Before
  public void setUp() throws Exception {
    decoder = new Decoder();
  }

  @Test
  public void testValidDict() {
    Map<Character, String> dict = new HashMap<>();
    String text = " :110\na:1110\nr:01101\ns:10\nt:0111\ne:00\nh:1111\nl:010\no:01100";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    try {
      String decodedMsg = decoder.decodeMessage(dict, encodedMessage);
    } catch (Exception e) {
      fail("Exception should not be thrown");
    }
  }


  @Test
  public void testIncorrectPrefixDict() {
    String encodedMessage = "101111001101000010010101101000111011010111100010010101"
                                    + "101110011111001111111001101000111011010111101100011"
                                    + "0100";
    Map<Character, String> dict = new HashMap<>();
    String text = " :110\na:110\nr:110\ns:110\nt:110\ne:110\nh:110\nl:110\no:110";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    try {
      String decodedMsg = decoder.decodeMessage(dict, encodedMessage);
      fail("Exception should have been be thrown");
    } catch (Exception e) {
      //
    }
  }

  @Test
  public void testEmptyDict() {
    Map<Character, String> dict = new HashMap<>();
    try {
      String decodedMsg = decoder.decodeMessage(dict, encodedMessage);
      fail("Exception should be thrown as code dict is empty");
    } catch (Exception e) {
      //
    }
  }

  @Test
  public void verifyBinaryDecodeOutput() {
    Map<Character, String> dict = new HashMap<>();
    String text = " :110\na:1110\nr:01101\ns:10\nt:0111\ne:00\nh:1111\nl:010\no:01100";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    String encodedMessage = "101111001101000010010101101000111011010111100010010101"
                                    + "101110011111001111111001101000111011010111101100011"
                                    + "0100";
    try {
      String decodedMsg = decoder.decodeMessage(dict, encodedMessage);
      assertEquals(decodedMsg, "she sells sea shells at the sea shore");
    } catch (Exception e) {
      fail("Exception should not be thrown");
    }
  }

  @Test
  public void verifyBinaryDecodeOutput2() {
    Map<Character, String> dict = new HashMap<>();
    String text = " :110\na:1110\nr:01101\ns:10\nt:0111\ne:00\nh:1111\nl:010\no:01100";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    String encodedMessage = "10111100";
    try {
      String decodedMsg = decoder.decodeMessage(dict, encodedMessage);
      assertEquals(decodedMsg, "she");
    } catch (Exception e) {
      fail("Exception should not be thrown");
    }
  }

  @Test
  public void verifyDecodingForHexadecimalEncoding() {
    Map<Character, String> dict = new HashMap<>();
    String text = " :6\na:3\nr:1\ns:8\nt:2\ne:7\nh:4\nl:5\no:0";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    try {
      String encodedMessage = "31";
      String decodedMsg = decoder.decodeMessage(dict, encodedMessage);
      assertEquals("ar", decodedMsg);

      encodedMessage = "82";
      decodedMsg = decoder.decodeMessage(dict, encodedMessage);
      assertEquals("st", decodedMsg);

      encodedMessage = "054";
      decodedMsg = decoder.decodeMessage(dict, encodedMessage);
      assertEquals("olh", decodedMsg);

    } catch (Exception e) {
      fail("Exception should not be thrown");
    }
  }

  @Test
  public void verifyDecodingForSpecialCharacterEncoding() {
    Map<Character, String> dict = new HashMap<>();
    String text = "@:100\n!:0111\n#:00010\n$:0010\n%:110\n&:101\n):0011\n*:00011\n::01000\n{:01011"
                          + "\n<:01001\n}:0000\n>:01010\n^:111\n?:0110";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    try {
      String encodedMessage = "10000010";
      String decodedMsg = decoder.decodeMessage(dict, encodedMessage);
      assertEquals("@#", decodedMsg);

    } catch (Exception e) {
      fail("Exception should not be thrown");
    }
  }

  @Test
  public void testDecodingEmptyEncodingMsg() {
    encodedMessage = "";
    Map<Character, String> dict = new HashMap<>();
    String text = " :110\na:1110";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    try {
      String decodedMsg = decoder.decodeMessage(dict, encodedMessage);
      assertEquals(encodedMessage, decodedMsg);
    } catch (Exception e) {
      //
    }
  }

  @Test
  public void testMalformedEncodedMessage() {
    encodedMessage = "101";
    Map<Character, String> dict = new HashMap<>();
    String text = " :110\na:1110\nr:01101\ns:10\nt:0111\ne:00\nh:1111\nl:010\no:01100";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    try {
      String decodedMsg = decoder.decodeMessage(dict, encodedMessage);
      fail("Exception should have been be thrown");
    } catch (Exception e) {
      //
    }
  }

  @Test
  public void testPrefixNotMatching() {
    String encodedMessage = "101111001101000010010101101000111011010111100010010101"
                                    + "101110011111001111111001101000111011010111101100011"
                                    + "0100";
    Map<Character, String> dict = new HashMap<>();
    String text = " :110\na:1110";
    String[] lines = text.split("\\r?\\n");
    for (String line : lines) {
      dict.put(line.charAt(0), line.substring(2));
    }

    try {
      String decodedMsg = decoder.decodeMessage(dict, encodedMessage);
      fail("Exception should have been be thrown");
    } catch (Exception e) {
      //
    }
  }
}