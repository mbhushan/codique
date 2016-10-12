package hr_MakingAnagrams;

import java.util.HashMap;
import java.util.Map;

/*
Alice is taking a cryptography class and finding anagrams to be very useful. We consider two strings to be anagrams of each other if the first string's letters can be rearranged to form the second string. In other words, both strings must contain the same exact letters in the same exact frequency For example, bacdc and dcbac are anagrams, but bacdc and dcbad are not.

Alice decides on an encryption scheme involving two large strings where encryption is dependent on the minimum number of character deletions required to make the two strings anagrams. Can you help her find this number?

Given two strings,  and , that may or may not be of the same length, determine the minimum number of character deletions required to make  and  anagrams. Any characters can be deleted from either of the strings.

This challenge is also available in the following translations:

Chinese
Russian
Input Format

The first line contains a single string, . 
The second line contains a single string, .

Constraints

It is guaranteed that  and  consist of lowercase English alphabetic letters (i.e.,  through ).
Output Format

Print a single integer denoting the number of characters you must delete to make the two strings anagrams of each other.

Sample Input

cde
abc
Sample Output

4
Explanation

We delete the following characters from our two strings to turn them into anagrams of each other:

Remove d and e from cde to get c.
Remove a and b from abc to get c.
*/
public class MakingAnagrams {

	public static void main(String[] args) {
		MakingAnagrams ma = new MakingAnagrams();
		
		String first = "computer";
		String second = "commuter";
		
		int ans = ma.numberNeeded(first, second);
		System.out.println("ans: " + ans);
	}
	
	public int numberNeeded(String first, String second) {
		
		Map<Character, Integer> firstMap = new HashMap<Character, Integer>();
		Map<Character, Integer> secondMap = new HashMap<Character, Integer>();
		int firstLen = first.length();
		int secondLen = second.length();
		
		for (int i=0; i<firstLen; i++) {
			char ch = first.charAt(i);
			int count = firstMap.containsKey(ch) ? firstMap.get(ch) + 1 : 1;
			firstMap.put(ch, count);
		}
		
		for (int i=0; i<secondLen; i++) {
			char ch = second.charAt(i);
			int count = secondMap.containsKey(ch) ? secondMap.get(ch) + 1 : 1;
			secondMap.put(ch, count);
		}
		int common = 0;
		for (char ch: firstMap.keySet()) {
			if (secondMap.containsKey(ch)) {
				common += Math.min(firstMap.get(ch), secondMap.get(ch));
			}
		}
		
		return (firstLen + secondLen - (2 * common));
	}
}
