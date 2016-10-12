package hr_RansonNote;

import java.util.HashMap;
import java.util.Map;

/*
A kidnapper wrote a ransom note but is worried it will be traced back to him. He found a magazine and wants to know if he can cut out whole words from it and use them to create an untraceable replica of his ransom note. The words in his note are case-sensitive and he must use whole words available in the magazine, meaning he cannot use substrings or concatenation to create the words he needs.

Given the words in the magazine and the words in the ransom note, print Yes if he can replicate his ransom note exactly using whole words from the magazine; otherwise, print No.

Input Format

The first line contains two space-separated integers describing the respective values of  (the number of words in the magazine) and  (the number of words in the ransom note). 
The second line contains  space-separated strings denoting the words present in the magazine. 
The third line contains  space-separated strings denoting the words present in the ransom note.

Constraints

.
Each word consists of English alphabetic letters (i.e.,  to  and  to ).
The words in the note and magazine are case-sensitive.
Output Format

Print Yes if he can use the magazine to create an untraceable replica of his ransom note; otherwise, print No.

Sample Input

6 4
give me one grand today night
give one grand today
Sample Output

Yes
 */
public class RansomNote {
	
	Map<String, Integer> magazineMap;
    Map<String, Integer> noteMap;
    String magazine ;
    String note;
    
    public RansomNote(String magazine, String note) {
		this.magazine = magazine;
		this.note = note;
		this.magazineMap = new HashMap<String, Integer>();
		this.noteMap = new HashMap<String, Integer>();
		
	}
    
	public static void main(String[] args) {
		String magazine = "give me one grand today night";
		String note = "give one grand today";
		RansomNote rn = new RansomNote(magazine, note);
		
		System.out.println(rn.solve());
	}
	
	public boolean solve() {
		
		String [] magWords = this.magazine.split(" ");
		String [] noteWords = this.note.split(" ");
		
		for (int i=0; i<magWords.length; i++) {
			int count = this.magazineMap.containsKey(magWords[i]) ? this.magazineMap.get(magWords[i]) + 1: 1;
			this.magazineMap.put(magWords[i], count);
		}
		
		for (int i=0; i<noteWords.length; i++) {
			int count = this.noteMap.containsKey(noteWords[i]) ? this.noteMap.get(noteWords[i]) + 1: 1;
			this.noteMap.put(noteWords[i], count);
		}
		
		for (String word: this.noteMap.keySet()) {
			if (!this.magazineMap.containsKey(word)) {
				return false;
			}
			int magValue = this.magazineMap.get(word);
			int noteValue = this.noteMap.get(word);
			if (noteValue > magValue) {
				return false;
			}
		}
		
		return true;
        
    }
}
