/*
 * James Crocker
 * 250634027
 * April 3, 2016
 * 
 * Description:
 * This file contains the implementation of the Naive string matching algorithm.
 * By comparing the patter string to the text string at every position, the
 * algorithm is able to determine if a match occurs.
 */
public class NaiveAlgorithm {
	
	/*
	 * Name: FindMatch
	 * Input: Text string, Pattern String
	 * Output: Matching position, or -1 if no match occurs
	 * Description: This function compares the pattern string to the text
	 * string at every position looking for a match
	 */
	
	static int findMatch(String text, String pattern){
		int textLen = text.length();				//Length of the text
		int patLen = pattern.length();				//Length of the patter
		int num = textLen - patLen;					//Total number of positions to iterate
		char[] text2 = text.toCharArray();			//Form char array for faster
		char[] pattern2 = pattern.toCharArray();	//computations
		int j;										//Position counter
		
		//Iterate through the text string, and for each position...
		for (int i = 0; i <= num; i ++){
			j = 0;
			//Compare the pattern string to the text string
			while (j < patLen && text2[i + j] == pattern2[j]) j ++;
			//If the entire pattern string length has been iterated through,
			//then a match has occurred
			if (j == patLen) return i;
		}
		//No match occurred
		return -1;
	}

}
