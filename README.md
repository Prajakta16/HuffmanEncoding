# HUFFMAN CODING USING TREES

### DESCRIPTION
Encoding and decoding are common operations on data. Given data in the form of symbols (e.g. text), it can be encoded by translating each symbol into a unique code, possibly consisting of many symbols. Decoding applies this process in reverse. The unique codes may be made of a different set of symbols (e.g. the original symbols may be characters, but the codes are bits). We call this set "coding symbols".
<br>
<br>
The main component of encoding and decoding can be thought of as a symbol -> code dictionary representing a coding scheme. For example, consider that all input messages are made of symbols {a,b,c,d,e}, and the coding symbols are {0,1}. An example dictionary could be {a -> 10, b -> 1100, c -> 1101, d -> 01, e -> 00}. Then, an input message "dab" will be encoded as "01101100" by replacing the symbols 'd', 'a', 'b' with their respective codes from the dictionary. Similarly, an encoded string "11001001" will be decoded to "bad" using the same dictionary.
<br>
<br>
Coding schemes may have some unique characteristics. For example, certain encoding schemes generate codes such that no code is a prefix of another code (e.g. 10 and 101 cannot both be codes, because the first is a prefix of the second). Such schemes are called prefix codes and are often used since a sequence of codes then requires no delimiters between codes, and will have only one possible decoding. You can verify that the dictionary in the above example shows a prefix coding scheme. One way to look at prefix codes is by using prefix trees.

<br>
Encoding
How is a prefix code generated? One can arbitrarily assign a prefix code for a symbol set. However it is possible to generate "better" prefix codes. One way is to use a scheme called Huffman encoding. This encoding generates a custom coding scheme for the message to be encoded, such that the length of the encoded message (in terms of number of symbols) is minimized.

<br>

#### DECODING
Given an encoded message "10001101", we can decode it as follows: <br>

Start at the root of the tree. <br>
Read the next symbol. <br>
Turn "left" or "right" depending on the read symbol. <br>
If a leaf is reached, output the character at that leaf, restart at the root. <br>
Go to step 2. <br>

<br>

#### ENCODING
The Huffman encoding algorithm to generate a binary prefix code, given the source message M is as follows: <br>

Create a frequency table (a,f(a)) where a is a symbol in M and f(a) is the number of times a occurs in M. <br>
Initialize a coding table (a,g(a)) where a is a symbol in M and g(a) is the desired code for a. All codes are initially empty. <br>
Add all symbols into a priority queue Q, using their frequencies as priorities. When the frequencies are the same, the item that is lexicographically earlier has the lower priority. In general Q contains items with one or more symbols in them. <br>
Pop 2 items x and y from the queue with the lowest frequencies f(x) and f(y) respectively. <br>
For each symbol xi in x, add a prefix of 0 to g(xi). Similarly, for each symbol yi in y, add a prefix of 1 to g(yi). <br>
Add a new entry (x.y, f(x)+f(y)) to Q, where x.y is the concatenation of x and y. <br>
If there is more than one item in Q, go to step 4. Otherwise, report the resulting coding table. <br>


#### REQUIREMENT
Design and implement the interfaces/classes to be able to encode and decode these types of coding sets. Your implementation should be able to:

1. decode messages given a symbol->code dictionary containing a prefix code.
2. encode messages given a symbol->code dictionary containing a prefix code.
3. be able to handle code sets with any number of symbols, not just binary. For example, a code set using hexadecimal code symbols would have "0123456789abcdef" as the coding symbols, and will result in a tree where each transition node has up to 16 children.
4. be able to read a message of arbitrary length and be able to generate a Huffman encoding using the algorithm described above.
5. be able to handle any character set including punctuation, line breaks, spaces, etc.
6. You are free to use any existing Java classes and interfaces (within the JDK), as well as any implementations provided to you in this course. You may also design your own classes and interfaces. The only constraint is that you implement the decoding algorithm using a coding tree faithfully (as described above).

7. In addition, develop a driver program that can:

read messages from either the keyboard or a file, write messages to the screen or to a file, write a binary encoding to a file using either binary or hexadecimal. For example, the binary encoding:
read and write an prefix encoding to a human-readable text file.

### HOW TO USE
All the code is in src/.
All the tests are in be test/.
Original, revised design document, the JAR file, example runs of program are in res/.

The program can be run using the JAR file within the res/ folder in the zipped file.
1. Unzip submission folder
2. Go to the to res/ folder using command prompt
3. Execute "java -jar HuffmanEncoding.jar"
4. Program should successfully execute

### PARTS COMPLETE
All the parts and requirements of the problem statement are completed and the tests have a good coverage of all program features.

### ASSUMPTIONS
1. Input can be provided either through a file or through keyboard
2. Plain text can contain any character including special ones
3. Code symbols can contain any character including special ones
4. While encoding, every character in plain text should have a code in the code dict, otherwise exception will be thrown
5. While decoding, every sequence of characters in encoded msg should have a code in the code dict, otherwise exception will be thrown
6. Dictionary provided in correct in all aspects otherwise exception will be thrown
7. If frequency of 2 elements is same, Huffman will prefer lexicographically smaller element first.


### DESIGN AND JUSTIFICATION
The class design and association between various elements like Decoder, Encoder, Huffman code generator and n-children trees. Encoder, Decoder and Huffman code generator interface will make the code more reusable and efficient. All the features that required encoding and decoding are as part of methods in the respective classes.

Decoder is implemented by using a custom tree which is n-children tree. There is a group node in the tree that contains a dictionary mapping of tress at every branch. Moreover, the leaf node will contain the actual character data. All the tree interfaces and classes are package private to avoid outside access.

Finally, many use cases have been handled in the Driver class which allows you to execute either hardcoded sample cases for encoding, decoding, generating prefix codes or give a custom input the run the program.








