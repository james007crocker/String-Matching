/*
 * James Crocker
 * 250634027
 * April 3, 2016
 * 
 * Description:
 * This file contains the implementation of the KMP string matching algorithm.
 * Unlike the naive algorithm, a 'next table' is used to speed up the comparisons
 * when a mismatch has occurred
 */
import java.util.Arrays;

public class KnuthMorrisPratt {
	
	/*
	 * Name: FindMatch
	 * Input: Text string, Pattern String
	 * Output: Matching position, or -1 if no match occurs
	 * Description: This function tries to locate an occurrence of the pattern string
	 * within the text string
	 */
	
	static int findMatch(String text, String pattern){
		int n = text.length();							//Text length
		int m = pattern.length();						//Pattern length
		int[] next = computePrefixFunction(pattern);	//Next table
		int q = 0;
		//Iterate along the text looking for a match
		for (int i = 0; i < n; i ++){
			//While a mismatch is encountered, use the next table to find the next comparison position
			while (q > 0 && pattern.charAt(q) != text.charAt(i)){
				q = next[q];
			}
			//If matched, increment the number of characters matched
			if (pattern.charAt(q) == text.charAt(i)){
				++ q;
			}
			//If the number of characters matched equals the length of the pattern, return the matching position
			if (q == m){
				return i - m + 1;
			}
		}
		//No match found
		return -1;
	}
	
	/*
	 * Name: computePrefixFunction
	 * Input: Pattern String
	 * Output: Next table based on the pattern
	 * Description: This function compares the pattern string to itself to 
	 * produce a table for speeding up string comparisons after a mismatch
	 */
	
	public static int[] computePrefixFunction(String pattern){
		int m = pattern.length();		//Length of the pattern string
		int[] next = new int[m + 1];	//Next table
		next[1] = 0;					
		int k = 0;
		
		//Iterate from the beginning of the pattern to the end
		for (int q = 2; q <= m; q ++){
			//Iterate while no match has occurred, and the beginning of the string has not been reached
			while (k > 0 && pattern.charAt(k) != pattern.charAt(q - 1)) {
				k = next[k];
			}
			//If a match occurs with itself, then increment the size of the matched prefix length by 1
			if (pattern.charAt(k) == pattern.charAt(q - 1)){
				++ k;
			}
			//Update the next table for the given position q
			next[q] = k;
		}
		//Return the computed next table
		return next;
	}
}
