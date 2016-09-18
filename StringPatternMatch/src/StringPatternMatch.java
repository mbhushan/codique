import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/*
Given a pattern and a string - find if the string follows the same pattern Eg: Pattern : [a b b a], String : cat dog dog cat 
*/

public class StringPatternMatch {

	public static void main(String[] args) {
		
		StringPatternMatch spm = new StringPatternMatch();
		
		String [] input = {
				"red blue blue red",
				"dog dog dog dog",
				"dog cat cat dog"
				};
		
		String [] pattern = {
				"abba",
				"abba",
				"aaaa"
		};
		
		for (int i=0; i<input.length; i++) {
			System.out.println("input: " + input[i]);
			System.out.println("pattern: " + pattern[i]);
			System.out.println("match pattern: " + spm.matchPattern(input[i], pattern[i]));
			System.out.println();
		}
		
	}
	
	public boolean matchPattern(String input, String pattern) {
		if (input ==null || pattern == null) {
			return false;
		}
		
		char [] patArr = pattern.toCharArray();
		String [] strArr = input.split(" ");
		
		if (patArr.length != strArr.length) {
			return false;
		}
		
		Map<Character, String> hmap = new HashMap<Character, String>();
		hmap.put(patArr[0], strArr[0]);
		
		for (int i=1; i<patArr.length; i++) {
			if (hmap.containsKey(patArr[i])) {
				if (!hmap.get(patArr[i]).equals(strArr[i])) {
					return false;
				}
			} else if (hmap.containsValue(strArr[i])) {
				return false;
			} else {
				hmap.put(patArr[i], strArr[i]);
			}
		}
		
		return true;
	}
	
	
	public boolean patternMatch(String input, String pattern) {
		
		int plen = pattern.length();
		char [] patArr = pattern.toCharArray();
		Set<Character> hset = new HashSet<Character>();
		
		for (char ch: patArr) {
			hset.add(ch);
		}
		
		int uniqlen = hset.size();
		int inputLen = input.length();
		
		int maxLen = (inputLen - uniqlen)/2;
		int minLen = 1;
		
		return false;
	}
	
	
}
