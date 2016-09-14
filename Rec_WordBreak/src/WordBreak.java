import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class WordBreak {

	private String [] words = {"this", "is", "sentence", "sent", "ence"};
	private Set<String> dict = new HashSet<String>();
	
	public WordBreak() {
		Collections.addAll(dict, words);
	}
	public static void main(String[] args) {
		WordBreak wb = new WordBreak();
		
		String input = "thisissentence";
		
		wb.wordBreak(input);
		
		//output:
		//[this, is, sent, ence]
		//[this, is, sentence]

	}
	
	public void wordBreak(String input) {
		List<String> result = new ArrayList<String>();
		wordBreak(input, 0, new StringBuffer(), result);
	}
	
	private void wordBreak(String text, int index, StringBuffer sb, List<String> result) {
		if (index >= text.length()) {
			int count = 0;
			for (String st: result) {
				count += st.length();
			}
			if (count >= text.length()) {
				System.out.println(result);
			}
			return;
		}
		
		int len = text.length();
		for (int i=index; i<len; i++) {
			char ch = text.charAt(i);
			sb.append(ch);
			if (dict.contains(sb.toString())) {
				result.add(sb.toString());
				wordBreak(text, i+1, new StringBuffer(), result);
				result.remove(result.size()-1);
			} 
			
		}
	}
}
