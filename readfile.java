/*
 * James Crocker
 * 250634027
 * April 3, 2016
 * 
 * Description:
 * This file contains the implementation of the readfile function to handle the input of two strings
 * (one from a text file) to perform string matching and subsequently calculate the string matching 
 * runtime for various algorithms
 */


import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;


public class readfile {

	/*
	 * Name: main
	 * Input: Text string, Pattern String
	 * Output: (Runtime of string matching algorithms)
	 */
	
	public static void main(String[] args) {

		//Ensure two parameters were provided as input
		if (args.length != 2){
			System.out.println("Usage: pattern string, text file \n");
		}
		
		//Open the text file
		String pattern = args[0].toLowerCase();
		String text = "";
		try {
			text = new String(Files.readAllBytes(Paths.get(args[1])));
		} catch (IOException e) {
			System.out.println("I/O Exception occured trying to read file.");
			return;
		}
		
		char[] patternChar = pattern.toCharArray();	//Convert the strings to 
		char[] textChar = text.toCharArray();		//character arrays for performance improvements
		int patternLen = pattern.length();			//Length of the pattern string
		int textLen = text.length();				//Length of the text string
		long startTime, endTime;					//Variables hold runtimes
		int ans = -1;								//Answer
		
		//IndexOF() runtime
		System.out.println("Algorithm Name\t\tRun Time (ns)");
		System.out.println("----------------------------------");
		BigInteger total = BigInteger.valueOf(0);
		for (int i = 0; i < 1000; i ++){
			startTime = System.nanoTime();
			ans = text.indexOf(pattern);
			endTime = System.nanoTime();
			total = total.add(BigInteger.valueOf(endTime - startTime));
		}
		System.out.println("indexOf\t\t\t" + (total.longValue() / 1000));
		
		//Naive algorithm runtime
		total = BigInteger.valueOf(0);
		for (int i = 0; i < 1000; i ++){
			startTime = System.nanoTime();
			ans = NaiveAlgorithm.findMatch(text, pattern);
			endTime = System.nanoTime();
			total = total.add(BigInteger.valueOf(endTime - startTime));
		}
		System.out.println("Naive\t\t\t" + (total.longValue() / 1000));
		
		//KMP algorithm runtime
		total = BigInteger.valueOf(0);
		for (int i = 0; i < 1000; i ++){
			startTime = System.nanoTime();
			ans = KnuthMorrisPratt.findMatch(text, pattern);
			endTime = System.nanoTime();
			total = total.add(BigInteger.valueOf(endTime - startTime));
		}
		System.out.println("KMP\t\t\t" + (total.longValue() / 1000));
		
		//LasVegas algorithm runtime
		total = BigInteger.valueOf(0);
		for (int i = 0; i < 1000; i ++){
			startTime = System.nanoTime();
			ans = LasVegasPatternMatching.findMatch(text, pattern);
			endTime = System.nanoTime();
			total = total.add(BigInteger.valueOf(endTime - startTime));
		}
		System.out.println("Las Vegas\t\t" + (total.longValue() / 1000));
		
	}
}
