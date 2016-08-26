package main.java.com.manib.brackets;

import java.util.ArrayList;
import java.util.List;


/**
 * @author manib
 * Find all valid bracket combinations.
 */

public class BracketCombination {

	public static void main(String[] args) {
		BracketCombination bc = new BracketCombination();
		int n = 3;
		bc.combinations(n);
	}
	
	public List<String> combinations(int n) {
		List<String> result = new ArrayList<String>();
		combinatin(n, 0, 0, 0, new StringBuffer(), result);
		return result;
	}
	
	private void combinatin(int len, int index, int left, int right, StringBuffer sb, List<String> result) {
		if (index == len*2) {
			System.out.println(sb.toString());
			result.add(sb.toString());
			return;
		}
		
		if (left < len) {
			sb.append('(');
			combinatin(len, index+1, left+1, right, sb, result);
			sb.deleteCharAt(sb.length()-1);
		}
		if (right < left) {
			sb.append(')');
			combinatin(len, index+1, left, right+1, sb, result);
			sb.deleteCharAt(sb.length()-1);
		}
	}
}
