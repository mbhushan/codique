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

		String[] input = { "red blue blue red", "dog dog dog dog",
				"dog cat cat dog" };

		String[] pattern = { "abba", "abba", "aaaa" };

		for (int i = 0; i < input.length; i++) {
			System.out.println("input: " + input[i]);
			System.out.println("pattern: " + pattern[i]);
			System.out.println("match pattern: "
					+ spm.matchPattern(input[i], pattern[i]));
			System.out.println();
		}

		String[] input1 = { "redbluebluered", "dogdogdogdog", "dogcatcatdog" };

		// for (int i=0; i<input.length; i++) {
		// System.out.println("input: " + input[i]);
		// System.out.println("pattern: " + pattern[i]);
		// System.out.println("pattern match-2: " + spm.patternMatch(input1[i],
		// pattern[i]));
		// System.out.println();
		// }

	}

	public boolean matchPattern(String input, String pattern) {
		if (input == null || pattern == null) {
			return false;
		}

		char[] patArr = pattern.toCharArray();
		String[] strArr = input.split(" ");

		if (patArr.length != strArr.length) {
			return false;
		}

		Map<Character, String> hmap = new HashMap<Character, String>();
		hmap.put(patArr[0], strArr[0]);

		for (int i = 1; i < patArr.length; i++) {
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

	// public boolean patternMatch(String input, String pattern) {
	//
	// char [] patArr = pattern.toCharArray();
	// Set<Character> hset = new HashSet<Character>();
	// Map<Character, Integer> hmap = new HashMap<Character, Integer>();
	//
	// for (char ch: patArr) {
	// int count = 0;
	// if (hmap.containsKey(ch)) {
	// count = hmap.get(ch);
	// }
	// ++count;
	// hmap.put(ch, count);
	// hset.add(ch);
	// }
	//
	// int maxRepeat = 1;
	// for (char ch: hmap.keySet()) {
	// if (hmap.get(ch) > maxRepeat) {
	// maxRepeat = hmap.get(ch);
	// }
	// }
	//
	// int uniqlen = hset.size();
	// int inputLen = input.length();
	//
	// int maxLen = (inputLen - uniqlen + 1)/maxRepeat;
	// int minLen = 1;
	//
	// return isPatternMatching(patArr, input, maxLen, minLen);
	// }
	//
	// private boolean isPatternMatching(char [] pattern, String input, int max,
	// int min) {
	//
	// Map<Character, String> hmap = null;
	// boolean flag = false;
	// for (int i=max; i>= min; i--) {
	// hmap = new HashMap<Character, String>();
	// int start = 0;
	// hmap.clear();
	// hmap.put(pattern[0], input.substring(start, i));
	// start = i;
	//
	// boolean found = true;
	// for (int k = 1; k < pattern.length; k++) {
	// found = true;
	//
	// for (int j = 1; j <= max; j++) {
	// String str = input.substring(start, start + j);
	// if (hmap.containsKey(pattern[k])) {
	// if (!hmap.get(pattern[k]).equals(str)) {
	// found = false;
	// break;
	// }
	// } else if (hmap.containsValue(str)) {
	// found = false;
	// break;
	// } else {
	// hmap.put(pattern[k], str);
	// }
	// }
	// if (!found) {
	// break;
	// }
	// }
	// if (found) {
	// flag = true;
	// break;
	// }
	// }
	// return flag;
	// }

}
