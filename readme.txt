There are two files included with this submission that are able to run the string matching algorithms.

1) main.java
----------------
 In order to run this program, compile it using:

 javac main.java

 Then run it using:

 java main pattern_string text_string

 where pattern_string is the pattern to match in text_string. For example, the line may look like:

 java main abcc abcdefgabcc


2) readfile.java
------------------
This program operates similarly to main.java, but instead takes the pattern string from a text file.
In order to run this program, compile it using:

javac readfile.java

Then run it using:

java readfile pattern_string text_file

where pattern_string is the pattern to match in the txt file text_file. For example, the line may look like:

java readfile abcc text.txt