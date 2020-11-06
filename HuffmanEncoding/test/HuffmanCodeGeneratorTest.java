import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import codingtrees.HuffmanCodeGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class to test the generation of huffman codes.
 */
public class HuffmanCodeGeneratorTest {
  private String plainMessage = "she sells sea shells at the sea shore";
  private HuffmanCodeGenerator huffmanCodeGenerator;

  @Before
  public void setUp() throws Exception {
    huffmanCodeGenerator = new HuffmanCodeGenerator();
  }

  @Test
  public void testGenerateHuffmanCodesBinarySymbols() {
    char[] symbols = {'0', '1'};
    Map<Character, String> huffmanCodeDictionary
            = huffmanCodeGenerator.generateHuffmanCodes(plainMessage, symbols);
    assertEquals("{ =110, a=1110, r=01101, s=10, t=0111, e=00, h=1111, l=010, o=01100}",
            huffmanCodeDictionary.toString());
  }


  @Test
  public void testGenerateHuffmanCodesHexadecimalSymbols() {
    char[] symbols = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };
    Map<Character, String> huffmanCodeDictionary
            = huffmanCodeGenerator.generateHuffmanCodes(plainMessage, symbols);
    assertEquals("{ =6, a=3, r=1, s=8, t=2, e=7, h=4, l=5, o=0}",
            huffmanCodeDictionary.toString());

  }

  @Test
  public void testGenerateHuffmanCodesSpecialSymbols() {
    char[] symbols = {';', '.', ',', '{'};
    Map<Character, String> huffmanCodeDictionary
            = huffmanCodeGenerator.generateHuffmanCodes(plainMessage, symbols);
    assertEquals("{ =,,, a=;{, r=;., s=., t=;,, e=,{, h=,;, l=,., o=;;}",
            huffmanCodeDictionary.toString());

  }

  @Test
  public void testGenerateHuffmanCodesWithSpaceSymbol() {
    char[] symbols = {'\t', '.', ',', '{'};
    Map<Character, String> huffmanCodeDictionary
            = huffmanCodeGenerator.generateHuffmanCodes(plainMessage, symbols);
    assertEquals("{ =,,, a=\t{, r=\t., s=., t=\t,, e=,{, h=,\t, l=,., o=\t\t}",
            huffmanCodeDictionary.toString());

  }

  @Test
  public void testGenerateHuffmanCodesWithEnterSymbol() {
    char[] symbols = {'\n', 'a', '5', '{'};
    Map<Character, String> huffmanCodeDictionary
            = huffmanCodeGenerator.generateHuffmanCodes(plainMessage, symbols);
    assertEquals("{ =55, a=\n{, r=\na, s=a, t=\n5, e=5{, h=5\n, l=5a, o=\n\n}",
            huffmanCodeDictionary.toString());

  }

  @Test
  public void testDuplicateSymbols() {
    char[] symbols = {'0', '0', '1', '2', '2'}; //same symbol

    try {
      Map<Character, String> huffmanCodeDictionary
              = huffmanCodeGenerator.generateHuffmanCodes(plainMessage, symbols);
      fail("This statement should throw an exception");
    } catch (IllegalArgumentException e) {
      //
    }
  }

  @Test
  public void testOnlyOneSymbolWhenMessageLengthMoreThanOne() {
    char[] symbols = {'0'};

    try {
      Map<Character, String> huffmanCodeDictionary
              = huffmanCodeGenerator.generateHuffmanCodes(plainMessage, symbols);
      fail("This statement should throw an exception");
    } catch (IllegalArgumentException e) {
      //
    }
  }

  @Test
  public void testOnlyOneSymbolWhenMessageLengthIsOne() {
    char[] symbols = {'0'};
    String plainMessage = "s";
    try {
      Map<Character, String> huffmanCodeDictionary
              = huffmanCodeGenerator.generateHuffmanCodes(plainMessage, symbols);
    } catch (IllegalArgumentException e) {
      fail("This statement should not throw an exception");
    }
  }

  @Test
  public void testMessageWithSpecialCharacters() {
    char[] symbols = {'0', '1'};
    String plainMessage = "Helloooo!!!!!!   This is a beauuutiful day ^^%%$! \n ^%$!^%!$%^!!";
    Map<Character, String> huffmanCodeDictionary
            = huffmanCodeGenerator.generateHuffmanCodes(plainMessage, symbols);
    assertEquals("{ =101, a=0010, !=111, b=011001, d=011010, $=0001, e=01110, %=1100, "
                         + "f=011011, H=010111, h=011110, i=0011, \n=010110, l=0100, o=1000, "
                         + "s=0000, T=011000, t=011111, u=1001, y=01010, ^=1101}",
            huffmanCodeDictionary.toString());

    plainMessage = "#$%^&*!@$%&!%^&@%^@%^%@&^%))<>:?{}?!&^@&^";
    huffmanCodeDictionary
            = huffmanCodeGenerator.generateHuffmanCodes(plainMessage, symbols);
    assertEquals("{@=100, !=0111, #=00010, $=0010, %=110, &=101, )=0011, *=00011, "
                         + ":=01000, {=01011, <=01001, }=0000, >=01010, ^=111, ?=0110}",
            huffmanCodeDictionary.toString());
  }

  @Test
  public void testMessageWithNumericCharacters() {
    char[] symbols = {'0', '1'};
    String plainMessage = "767868768746728102983555271893989282982928938376635524239273826478";
    Map<Character, String> huffmanCodeDictionary
            = huffmanCodeGenerator.generateHuffmanCodes(plainMessage, symbols);
    assertEquals("{0=111100, 1=111101, 2=110, 3=010, 4=11111, 5=1110, 6=011, 7=101, "
                         + "8=00, 9=100}",
            huffmanCodeDictionary.toString());
  }
}