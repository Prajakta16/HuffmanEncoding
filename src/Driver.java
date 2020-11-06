import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import codingtrees.Decoder;
import codingtrees.Encoder;
import codingtrees.HuffmanCodeGenerator;

/**
 * Driver class to provide a sample run. It reads messages from either the keyboard or a file.
 * Writes messages to the screen or to a file writes a binary encoding to a file using either binary
 * or hexadecimal. For example, the binary encoding: 110000010110110110101101011011010110101101111
 * 1101011
 * could be written in hexadecimal as: C16DAD6D6B7EB read and write an prefix encoding to a
 * human-readable text file. One of the runs to show the reading of the message from the keyboard
 * One of the runs to show the writing of the message to the screen One of the runs to show the
 * reading of a message from a file One of the runs to show the writing of a message to a file One
 * of the runs to show the reading of the prefix encoding from a file and show that it can
 * successfully be used to decode a message One of the runs to show the writing of a prefix encoding
 * to a file
 */
public class Driver {

  /**
   * Main class.
   *
   * @param args arguments.
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (true) {

      System.out.println("Select operations: \n 1. Generate huffman code and get code dictionary\n "
                                 + "2. Generate binary huffman encoding\n "
                                 + "3. Generate hexadecimal huffman encoding\n "
                                 + "4. Provide any custom code dictionary to encode plain text\n "
                                 + "5. Use any custom code dictionary to decode the "
                                 + "encoded message\n Enter appropriate option");
      int option = sc.nextInt();

      switch (option) {
        case 1:
          generateHuffmanCodeDictionary(sc);
          break;

        case 2:
          generateSpecificHuffmanCodeDictionary("binary", sc);
          break;

        case 3:
          generateSpecificHuffmanCodeDictionary("hexadecimal", sc);
          break;

        case 4:
          encodePlainText(sc);
          break;

        case 5:
          decodeMessage(sc);
          break;

        default:
          System.out.println("Incorrect option");
      }
      System.out.println("Press q to quit, else press any other key");
      if (sc.next().equalsIgnoreCase("q")) {
        System.out.println("Successfully completed the program run");
        System.exit(0);
      }
    }

  }


  private static void decodeMessage(Scanner sc) {
    String encodedMessage = "101111001101000010010101101000111011010111100010010101101110011111"
                                    + "0011111110011010001110110101111011000110100";
    encodedMessage = readText(encodedMessage, sc);

    Map<Character, String> codeDictionary = readCodeDictionary(sc);
    Decoder decoder = new Decoder();
    String decodedMsg = decoder.decodeMessage(codeDictionary, encodedMessage);

    System.out.println("Do you want to write the decoded text to a file? Enter F, otherwise "
                               + "enter any other key to print on console");

    if (sc.next().equalsIgnoreCase("f")) {

      String decodedMessageOutputFilePath = "src/codingtrees/files/outputDecoded";
      writeToFile(decodedMsg, decodedMessageOutputFilePath, sc);
    } else {
      System.out.println(decodedMsg);
    }
  }

  private static void encodePlainText(Scanner sc) {
    String plainMessage = "she sells sea shells at the sea shore";
    plainMessage = readText(plainMessage, sc);

    Map<Character, String> codeDictionary = readCodeDictionary(sc);

    Encoder encoder = new Encoder();
    String encodedMessage = encoder.encodeMessage(codeDictionary, plainMessage);

    System.out.println("Do you want to write the encoded text to a file? Enter F, otherwise "
                               + "enter any other key to print on console");

    if (sc.next().equalsIgnoreCase("f")) {

      String encodedMessageOutputFilePath = "src/codingtrees/files/outputEncoded";
      writeToFile(encodedMessage, encodedMessageOutputFilePath, sc);
    } else {
      System.out.println(encodedMessage);
    }
  }

  private static Map<Character, String> readCodeDictionary(Scanner sc) {
    Map<Character, String> dict = new HashMap<>();
    System.out.println("If you want to read dictionary from file, type F");
    System.out.println("If you want to provide dictionary through keyboard, type K");

    String inputFormat = sc.next();
    if (inputFormat.equalsIgnoreCase("f")) {

      String filePath = "src/codingtrees/files/inputPrefixCodeDictionary";
      String text = readFromFile(filePath, sc);
      String[] lines = text.split("\\r?\\n");
      for (String line : lines) {
        dict.put(line.charAt(0), line.substring(2));
      }

    } else if (inputFormat.equalsIgnoreCase("k")) {
      System.out.println("Enter number of keys in the code dictionary");
      int countDistinctChar = sc.nextInt();
      System.out.println("Enter dictionary as a key value pair as char:code eg. a:01");
      String keyValuePair;
      for (int i = 0; i < countDistinctChar; i++) {
        keyValuePair = sc.next();
        dict.put(keyValuePair.charAt(0), keyValuePair.substring(2));
      }
    }
    return dict;
  }

  private static void generateHuffmanCodeDictionary(Scanner sc) {
    char[] symbols = readSymbols(sc);
    generateHuffmanDictHelper(symbols, sc);
  }

  private static void generateHuffmanDictHelper(char[] symbols, Scanner sc) {
    String plainMessage = "she sells sea shells at the sea shore";
    plainMessage = readText(plainMessage, sc);


    HuffmanCodeGenerator huffmanDictionary = new HuffmanCodeGenerator();
    Map<Character, String> huffmanCodeDictionary
            = huffmanDictionary.generateHuffmanCodes(plainMessage, symbols);
    System.out.println(huffmanCodeDictionary.toString());
    StringBuilder huffmanDict = new StringBuilder();
    for (Character c : huffmanCodeDictionary.keySet()) {
      huffmanDict.append(c).append(":").append(huffmanCodeDictionary.get(c)).append("\n");
    }

    System.out.println("Do you want to write the dictionary to a file? Enter F, otherwise "
                               + "enter any other key to print on console");

    if (sc.next().equalsIgnoreCase("f")) {

      String dictOutputFilePath = "src/codingtrees/files/outputDict";
      writeToFile(huffmanDict.toString(), dictOutputFilePath, sc);
    } else {
      System.out.println(huffmanDict.toString());
    }
  }

  private static void generateSpecificHuffmanCodeDictionary(String encodingType, Scanner sc) {
    char[] symbols = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };
    if (encodingType.equalsIgnoreCase("binary")) {
      symbols = new char[]{'0', '1'};
    }
    generateHuffmanDictHelper(symbols, sc);
  }

  private static char[] readSymbols(Scanner sc) {
    System.out.println("How many symbols do you want to encode with?");
    int numSymbols = sc.nextInt();
    char[] symbols = new char[numSymbols];
    System.out.println("Enter the symxbols one at a time and press enter. \nIf space, type space. "
                               + "If tab, type tab. If new line, type new");
    for (int i = 0; i < numSymbols; i++) {
      String symbol = sc.next();

      char sym;
      if (symbol.equalsIgnoreCase("space")) {
        sym = ' ';
      } else if (symbol.equalsIgnoreCase("new")) {
        sym = '\n';
      } else if (symbol.equalsIgnoreCase("tab")) {
        sym = '\t';
      } else {
        sym = symbol.toCharArray()[0];
      }
      symbols[i] = sym;
    }
    return symbols;
  }

  private static String readText(String plainMessage, Scanner sc) {
    System.out.println("If you want to read input from file, type F");
    System.out.println("If you want to provide input through keyboard, type K");
    System.out.println("If you want to run through hardcoded text \"" + plainMessage
                               + "\", type any other key");

    String inputFormat = sc.next();
    if (inputFormat.equalsIgnoreCase("f")) {

      String filePath = "src/codingtrees/files/inputFile";
      plainMessage = readFromFile(filePath, sc);

    } else if (inputFormat.equalsIgnoreCase("k")) {
      System.out.println("Enter plain text that needs to be encoded");
      sc.nextLine(); // to ignore enter pressed before this
      plainMessage = sc.nextLine();
    }
    return plainMessage;
  }

  private static String readFromFile(String filePath, Scanner sc) {
    String message = "";
    boolean continueFlag = true;

    while (continueFlag) {
      continueFlag = false;

      System.out.println("If you want to read input from h"
                                 + "hardcoded file path, " + filePath + " type H, "
                                 + "otherwise type any other key");

      if (!sc.next().equalsIgnoreCase("h")) {
        System.out.println("Enter the path of the input file");
        filePath = sc.next();
      }

      try {
        File file = new File(filePath);
        Scanner fileScanner = new Scanner(file);

        fileScanner.useDelimiter("\\Z");
        message = fileScanner.next();

      } catch (FileNotFoundException e) {
        System.out.println("Incorrect path");
        continueFlag = true;
      }
    }
    return message;
  }

  private static void writeToFile(String message, String outputFilePath, Scanner sc) {
    boolean continueFlag = true;

    while (continueFlag) {
      continueFlag = false;

      System.out.println("If you want to write to hardcoded file path" + outputFilePath
                                 + ", type H, otherwise type any other key");

      if (!sc.next().equalsIgnoreCase("h")) {
        System.out.println("Enter the path of the output file");
        outputFilePath = sc.next();
      }

      try {
        FileWriter myWriter = new FileWriter(outputFilePath);
        myWriter.write(message);
        myWriter.close();
        System.out.println("Write to file is successful!");
      } catch (FileNotFoundException e) {
        System.out.println("Incorrect path, try again...");
        continueFlag = true;
      } catch (IOException e) {
        System.out.println("Error writing to the file");
        e.printStackTrace();
      }
    }
  }

}
