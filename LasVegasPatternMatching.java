/*
 * James Crocker
 * 250634027
 * April 3, 2016
 * 
 * Description:
 * This file contains the implementation of the Las Vegas string matching algorithm.
 * This function uses a fingerprint to compare two string to check for a match.
 */

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Random;

public class LasVegasPatternMatching {
	
	/*
	 * Name: FindMatch
	 * Input: Text string, Pattern String
	 * Output: Matching position, or -1 if no match occurs
	 * Description: This function tries to locate an occurrence of the pattern string
	 * within the text string
	 */
	
	static int findMatch(String text, String pattern){
//		int bitlength = 5;
//		int certainty = 90;
//		
//		long startTime = System.nanoTime();
//		long p = (new BigInteger(bitlength, certainty, new Random())).intValue();
//		System.out.println("Random Number Generation: " + (System.nanoTime() -startTime)); 
//		System.out.println("P: " + p);
		long p = 31;
		
		long patternFingerPrint = calculatePatternFingerPrint(pattern, p);								//Compute the pattern fingerprint
		long textFingerPrint = calculatePatternFingerPrint(text.substring(0, pattern.length()), p);		//Compute the text fingerprint
		
		int pos = 0;																					
		int len = pattern.length();
		int len2 = text.length();
		long power = (long) Math.pow(26, len - 1);														//Precomputed to save computation time in the loop
		
		//Iterate through the length of the text string
		while (pos + len <= len2){
			//Check for matching fingerprints
			if (patternFingerPrint == textFingerPrint){
				//Confirm matching strings
				if (confirmMatch(text, pattern, pos)) return pos;
			}
			
			if (pos + len >= len2) return -1;
			
			//Recalculate the fingerprint based on the next character
			textFingerPrint -= power * ((int) text.charAt(pos) - 97);
			textFingerPrint = (26 * textFingerPrint + ((int) text.charAt(pos + len) - 97));
			textFingerPrint = textFingerPrint % p;
			if (textFingerPrint < 0) textFingerPrint += p;
			pos ++;
			
		}
		
		if (patternFingerPrint == textFingerPrint && confirmMatch(text, pattern, pos)) return pos;	//Match exists
		else return -1;																				//No match exists
		
	}
	
	/*
	 * Name: calculatePatternFingerPrint
	 * Input: Pattern String, Prime number p
	 * Output: Fingerprint of the string
	 * Description: This function is used to calculate a fingerprint of the string
	 */
	static long calculatePatternFingerPrint(String pattern, long p){
		int len = pattern.length();
		long fingerPrint = 0;
		//Calculate an integer sum based on the string characters
		for (int i = 0; i < len; i ++){
			fingerPrint += Math.pow(26, len - i - 1) * ((int) pattern.charAt(i) - 97);
		}
		//Return the fingerprint
		return fingerPrint % p;
	}
	
	/*
	 * Name: confirmMatch
	 * Input: Text string, Pattern String, position
	 * Output: True if Text[position] == pattern, else false
	 * Description: This function is used to confirm a match between the text and pattern at the position
	 */
	
	static boolean confirmMatch(String text, String pattern, int pos){
		int len = pattern.length();
		int i = 0;
		//Compare the pattern to the text to determine if a match exists
		while (i < len && pattern.charAt(i) == text.charAt(pos + i)) i ++;
		if (i == len) return true;
		else return false;
	}
}
