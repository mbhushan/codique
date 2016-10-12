package hr_FirstRepeatedWord;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedWord {

	public static void main(String[] args) {
		RepeatedWord rp = new RepeatedWord();
		
		String S = "mani, this is non-valid		list.of:words;; lets, see: what.list happens";
		
		//System.out.println(Arrays.toString(S.split("\\s+")));
		
		rp.extractWords(S);
	}
	
	public void extractWords(String S) {
		
		int len = S.length();
		StringBuffer sb = new StringBuffer();
		List<String> words = new ArrayList<String>();
		Set<String> hset = new HashSet<String>();
		for (int i=0; i<len; i++) {
			
			if (isDelimiter(S.charAt(i))) {
				String key = sb.toString();
				if (key.length() > 0) {
					if (hset.contains(key)) {
						System.out.println("FIRST REPEATED: " + key);
					}
					words.add(key);
					hset.add(key);
					
				}
				sb = new StringBuffer();
			} else {
				sb.append(S.charAt(i));
			}
		}
		System.out.println(words);
	}
	
	public boolean isDelimiter(char ch) {
		if (ch == '.' || ch == ',' || ch == ':' || ch == ';' || ch == '-' || ch == ' ' || ch == '\t') {
			return true;
		}
		return false;
	}
}
