import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class WordBreak {
	
	private Set<String> dict = new HashSet<String>();
	private String [] words = {"this", "is", "a", "sent", "sentence", "off", "offer", "in", "inside", "comp", "computer",
			"we", "awe", "you", "are", "is", "live", "love", "awesome", "some", "programming", "pro", "gramming", "side", "ence"};
	
	public WordBreak() {
		Collections.addAll(dict, words);
	}

	public static void main(String[] args) {
		WordBreak wb = new WordBreak();

		String [] text = {"thisissent", "weloveprogramming", "insideawesomeprogramming"};
		
		//for (int i=0; i<text.length; i++) {
		for (int i=0; i<1; i++) {
			System.out.println(wb.wordBreak(text[i]));
		}
		
	}
	
	public String wordBreak(String text) {
		List<String> result = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		int index = 0;
		wordBreakUtil(text.toCharArray(), index, sb, result);
		
		return null;
	}
	
	private void wordBreakUtil(char [] text, int index, StringBuffer sb, List<String> result) {
		if (index >= text.length) {
			//print result if result.len == text.len
			int count = 0;
			for (String st: result) {
				count += st.length();
			}
			if (count == text.length) {
				System.out.println(result.toString());
			}
			return;
		}
		
		for (int i=index; i<text.length; i++) {
			System.out.println(i+"," +index+" tl,"+text.length);
			char ch = text[i];
			sb.append(ch);
			if (dict.contains(sb.toString())) {
				result.add(sb.toString());
				wordBreakUtil(text, i+1, new StringBuffer(), result);
				result.remove(result.size()-1);
				//wordBreakUtil(text, i+1, sb, result);
				
			}
		}
	}
}
