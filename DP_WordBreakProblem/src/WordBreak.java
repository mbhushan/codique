import java.util.ArrayList;
import java.util.Arrays;
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
		
		//wb.wordBreak("weloveprogramming");
		
		for (int i=0; i<text.length; i++) {
		//for (int i=0; i<1; i++) {
			System.out.println(wb.wordBreak(text[i]));
			System.out.println("DP: " + wb.wordBreakDP(text[i]));
		}
		
		
	}
	
	public String wordBreakDP(String text) {
		if (text == null || text.length() < 1) {
			return new String(text);
		}
		int n = text.length();
		int [][] dp = new int[n][n];
		
		for (int i=0; i<n; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		for (int len=1; len<=n; len++) {
			for (int i=0; i<n-len+1; i++) {
				int j = i + len -1;
				String buff = text.substring(i, j+1);
				if (dict.contains(buff)) {
					dp[i][j] = i;
					continue;
				}
				for (int k=i+1; k<=j; k++) {
					if (dp[i][k-1] != -1 && dp[k][j] != -1) {
						dp[i][j] = k;
						break;
					}
				}
			}
		}
		if (dp[0][n-1] != -1) {
			System.out.println("word break possible!");
		} else {
			System.out.println("word break NOT possible!");
			return null;
		}
		int start = 0;
		int end = dp[0][n-1];
		List<String> result = new ArrayList<String>();
		while (start != end) {
			String buff = text.substring(start, end);
			result.add(buff);
			start = end;
			end = dp[end][n-1];
			if (start == end) {
				result.add(text.substring(start, n));
			}
		}
		return result.toString();
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
			//System.out.println(i+"," +index+" tl,"+text.length);
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
