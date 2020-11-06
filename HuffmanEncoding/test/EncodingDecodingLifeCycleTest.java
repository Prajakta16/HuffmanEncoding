import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import codingtrees.Decoder;
import codingtrees.Encoder;
import codingtrees.HuffmanCodeGenerator;

import static org.junit.Assert.assertEquals;

/**
 * Test class to test end to end life cycle of encoding and decoding.
 */
public class EncodingDecodingLifeCycleTest {
  private String plainMessage = "she sells sea shells at the sea shore";
  private HuffmanCodeGenerator huffmanCodeGenerator;
  private Encoder encoder;
  private Decoder decoder;

  @Before
  public void setUp() throws Exception {
    huffmanCodeGenerator = new HuffmanCodeGenerator();
    encoder = new Encoder();
    decoder = new Decoder();
  }


  @Test
  public void testEndToEndWithHexadecimalSymbols() {
    char[] symbols = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };
    Map<Character, String> huffmanCodeDictionary
            = huffmanCodeGenerator.generateHuffmanCodes(plainMessage, symbols);
    assertEquals("{ =6, a=3, r=1, s=8, t=2, e=7, h=4, l=5, o=0}",
            huffmanCodeDictionary.toString());

    String encodedMsg = encoder.encodeMessage(huffmanCodeDictionary, plainMessage);
    assertEquals("8476875586873684755863262476873684017",
            encodedMsg);

    String decodeMsg = decoder.decodeMessage(huffmanCodeDictionary, encodedMsg);
    assertEquals(plainMessage, decodeMsg);

  }

  @Test
  public void testEndToEndWithSpecialSymbols() {
    char[] symbols = {';', '.', ',', '{'};
    Map<Character, String> huffmanCodeDictionary
            = huffmanCodeGenerator.generateHuffmanCodes(plainMessage, symbols);
    assertEquals("{ =,,, a=;{, r=;., s=., t=;,, e=,{, h=,;, l=,., o=;;}",
            huffmanCodeDictionary.toString());

    String encodedMsg = encoder.encodeMessage(huffmanCodeDictionary, plainMessage);
    assertEquals(".,;,{,,.,{,.,..,,.,{;{,,.,;,{,.,..,,;{;,,,;,,;,{,,.,{;{,,.,;;;;.,{",
            encodedMsg);

    String decodeMsg = decoder.decodeMessage(huffmanCodeDictionary, encodedMsg);
    assertEquals(plainMessage, decodeMsg);

  }

  @Test
  public void testEndToEndForNumericMessage() {
    char[] symbols = {'0', '1'};
    String plainMessage = "01234567890002223456";
    Map<Character, String> huffmanCodeDictionary
            = huffmanCodeGenerator.generateHuffmanCodes(plainMessage, symbols);
    assertEquals("{0=110, 1=11100, 2=00, 3=1111, 4=010, 5=011, 6=100, 7=11101, "
                         + "8=1010, 9=1011}",
            huffmanCodeDictionary.toString());

    String encodedMsg = encoder.encodeMessage(huffmanCodeDictionary, plainMessage);
    assertEquals("1101110000111101001110011101101010111101101100000001111010011100",
            encodedMsg);

    String decodeMsg = decoder.decodeMessage(huffmanCodeDictionary, encodedMsg);
    assertEquals(plainMessage, decodeMsg);

  }
}