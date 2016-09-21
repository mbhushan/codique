package main.java.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HangmanEngine {

	private Set<Character> usedCharset;
	private final static String charFreq = "etaoinshrdlcumwfgypbvkjxqz";
	
	private DictionaryStore suggestion;
	private char [] vowels = {'a', 'e', 'i', 'o', 'u'};
	
	public HangmanEngine(String phrase) {
		this.usedCharset = new HashSet<Character>();
		this.suggestion = new DictionaryStore();
	}
	

	public char getNextChar(String phrase) {
		
		for (int i=0; i<vowels.length; i++) {
			if (!usedCharset.contains(vowels[i])) {
				usedCharset.add(vowels[i]);
				return vowels[i];
			}
		}
		
		Map<String, String> hmap = new HashMap<String, String>();
		String [] words = phrase.split(" ");
		for (int i=0; i<words.length; i++) {
			String word = words[i];
			if (isComplete(word)) {
				continue;
			}
			int len = word.length();
			
			ArrayList<String> wlist = this.suggestion.getWords(len);
			String closest = findClosestWord(word, wlist);
			hmap.put(word, closest);
			
		}
		
		int close = Integer.MIN_VALUE;
		String res = "";
		for (String str: hmap.keySet()) {
			int dist = match(str, hmap.get(str));
			if (dist > close) {
				res = hmap.get(str);
				close = dist;
			}
		}
		int len = res.length();
		for (int i=0; i<len; i++) {
			if (!usedCharset.contains(res.charAt(i))) {
				usedCharset.add(res.charAt(i));
				return res.charAt(i);
			}
		}
		
		char ch = charFreq.charAt(0);
		 len = charFreq.length();
		for (int i=0; i<len; i++) {
			if (!usedCharset.contains(charFreq.charAt(i))) {
				usedCharset.add(charFreq.charAt(i));
				ch = charFreq.charAt(i);
				break;
			}
		}
		
		return ch;
		
	}
	
	private boolean allEmpty(String word) {
		int len = word.length();
		
		for (int i=0; i<len; i++) {
			if (word.charAt(i) != '_') {
				return false;
			}
		}
		return true;
	}
	
	private boolean isComplete(String word) {
		int len = word.length();
		
		for (int i=0; i<len; i++) {
			if (!Character.isAlphabetic(word.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	private String findClosestWord(String word, ArrayList<String> wlist) {
		int maxMatch = Integer.MIN_VALUE;
		String ans = wlist.get(0);
		
		for (String str: wlist) {
			int mat = match(word, str);
			if (mat > maxMatch) {
				maxMatch = mat;
				ans = str;
			}
		}
		//System.out.println("ans: " + ans);
		return ans;
	}
	
	private int match(String first, String second) {
		int len = first.length();
		int match = 0;
		for (int i=0; i <len; i++) {
			if (first.charAt(i) == second.charAt(i)) {
				++match;
			}
		}
		return match;
	}
	
	private int numGuess(String phrase) {
		int guess = 0;
		String [] words = phrase.split(" ");
		for (int i=0; i<words.length; i++) {
			char [] chArr = words[i].toCharArray();
			
			for (int j=0; j<chArr.length; j++) {
				if (!Character.isAlphabetic(j)) {
					++guess;
				}
			}
		}
		return guess;
	}
}
