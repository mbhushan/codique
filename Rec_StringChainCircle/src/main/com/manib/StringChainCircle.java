package main.com.manib;

import java.util.Arrays;

/*
Given an array of strings, find if the strings can be chained to form a circle
Given an array of strings, find if the given strings can be chained to form a circle. 
A string X can be put before another string Y in circle if the last character of X is same as first character of Y.

Examples:

Input: arr[] = {"geek", "king"}
Output: Yes, the given strings can be chained.
Note that the last character of first string is same
as first character of second string and vice versa is
also true.

Input: arr[] = {"for", "geek", "rig", "kaf"}
Output: Yes, the given strings can be chained.
The strings can be chained as "for", "rig", "geek" 
and "kaf"

Input: arr[] = {"aab", "bac", "aaa", "cda"}
Output: Yes, the given strings can be chained.
The strings can be chained as "aaa", "aab", "bac" 
and "cda"

Input: arr[] = {"aaa", "bbb", "baa", "aab"};
Output: Yes, the given strings can be chained.
The strings can be chained as "aaa", "aab", "bbb" 
and "baa"

Input: arr[] = {"aaa"};
Output: Yes

Input: arr[] = {"aaa", "bbb"};
Output: No
*/
public class StringChainCircle {

	public static void main(String [] args) {
		StringChainCircle scc = new StringChainCircle();
		
		String [][] input = {
				{"for", "geek", "rig", "kaf"}
		};
		
		for (int i=0; i<input.length; i++) {
			System.out.println(scc.wordCircle(input[i]));
		}
	}
	
	public boolean wordCircle(String [] words) {
		int index = 1;
		char prev = words[0].charAt(words[0].length()-1);
		return wordCircle(words, index, prev);
	}
	
	private boolean wordCircle(String [] words, int index, char prev) { 
		if (index == words.length) {
			System.out.println(Arrays.toString(words));
			return prev == words[0].charAt(0);
		}
		
		for (int i=index; i<words.length; i++) {
			if(words[i].charAt(0) == prev) {
				String tmp = words[index];
				words[index] = words[i];
				words[i] = tmp;
				
				boolean result = wordCircle(words, index+1, words[index].charAt(words[index].length()-1));
				if (result) {
					return true;
				}
			}
		}
		
		return false;
	}
}
