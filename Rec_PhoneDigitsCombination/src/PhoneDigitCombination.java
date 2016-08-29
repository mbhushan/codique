import java.util.HashMap;
import java.util.Map;


public class PhoneDigitCombination {

	Map<Integer, String> map = new HashMap<Integer, String>();
	
	public static void main(String[] args) {
		PhoneDigitCombination pdc = new PhoneDigitCombination();
		pdc.init();
		int [] A = {2, 3, 2};
		pdc.letterCombinations(A);
	}
	
	private void init() {
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");
		map.put(0, "");
	}
	
	public void letterCombinations(int [] A) {
		int index = 0;
		StringBuffer sb = new StringBuffer();
		letterCombination(A, index, sb);
	}
	
	private void letterCombination(int [] A, int index, StringBuffer sb) {
		if (index == A.length) {
			if (sb.length() == A.length) {
				System.out.print(sb.toString() + " ");
			}
		}
		for (int i=index; i<A.length; i++) {
			String str = map.get(A[i]);
			char [] chArr = str.toCharArray();
			for (int j=0; j<chArr.length; j++) {
				sb.append(chArr[j]);
				letterCombination(A, i+1, sb);
				sb.deleteCharAt(sb.length()-1);
			}
		}
	}
}
