import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class AlphabetCoding {
	
	private String S = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private Map<Integer, Character> hmap = new HashMap<Integer, Character>();

	public static void main(String[] args) {
		AlphabetCoding ac = new AlphabetCoding();
		ac.init();
		
		int [][] A = {
				{1,1},
				{1,2,1},
				{9,1,8}
		};
		
		for (int i=0; i<A.length; i++) {
			System.out.println(Arrays.toString(A[i]));
			int count = ac.countInterpretations(A[i]);
			System.out.println();
			System.out.println("count: " + count);
		}
	}
	
	public void init() {
		char [] chArr = S.toCharArray();
		for (int i=0; i< chArr.length; i++) {
			hmap.put(i+1, chArr[i]);
		}
	}
	
	public int countInterpretations(int []A) {
		int index = 0;
		StringBuffer sb = new StringBuffer();
		return countInterpretations(A, index, sb);
	}
	
	private int countInterpretations(int [] A, int index, StringBuffer sb) {
		if (index >= A.length) {
			System.out.print(sb.toString() + " ");
			return 1;
		}
		
		int count = 0;
		sb.append(hmap.get(A[index]));
		count += countInterpretations(A, index+1, sb);
		sb.deleteCharAt(sb.length()-1);
		if (index+1 < A.length) {
			int num = A[index]*10 + A[index+1];
			if (num > 9 && num < 27) {
				sb.append(hmap.get(num));
				count += countInterpretations(A, index+2, sb);
				sb.deleteCharAt(sb.length()-1);
			}
		}
		return count;
	}
	
}
