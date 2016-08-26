import java.util.ArrayList;
import java.util.List;

/*
Generate all combination of size k and less of adjacent numbers
 * e.g 1,2,3,4 k = 2
 * 1 2 3 4
 * 12 3 4
 * 1 23 4
 * 1 2 34
 * 12 34
 */
public class AdjacentCombination {

	public static void main(String[] args) {
		AdjacentCombination ac = new AdjacentCombination();
		int n = 4;
		int k = 3;
		
		ac.generateCombination(n, k);
		int [] digits = {1,2,3,4};
		//int count = ac.findCombinations(digits, k);
		//System.out.println("count: " + count);
		System.out.println("another trial");
		ac.combination();
	}
	
	public int findCombinations(int [] digits, int k) {
		return findCombinations(digits, k, 0, new ArrayList<Integer>());
	}
	
	private int findCombinations(int [] digits, int k, int index, ArrayList<Integer> list) {
		if (index >= digits.length) {
			System.out.println(list.toString());
			return 1;
		}
		
		int count = 0;
		for (int i=1; i<=k; i++) {
			if (index+i < digits.length) {
				int num = formNumber(digits, index, index+i);
				list.add(num);
				count += findCombinations(digits, k, index+i, list);
				list.remove(list.size()-1);
			}
		}
		
		return count;
	}
	
	
	
	public void generateCombination(int n, int k) {
		int [] A = new int[n];
		for (int i=0; i<A.length; i++) {
			A[i] = i+1;
		}
		List<Integer> list = new ArrayList<Integer>();
		int [] result = new int[n];
		//generateCombination(A, 0, k, result, 0);
		generateCombination(A, 0, k, list);
	}
	
	private void generateCombination(int [] A, int index, int k, List<Integer> result) {
		if (index == A.length) {
			System.out.println(result.toString());
			return;
		}
		
		for (int i=index; i<index+k && i<A.length; i++) {
			int num = formNumber(A, index, i);
			result.add(num);
			generateCombination(A, i+1, k, result);
			result.remove(result.size()-1);

		}
	}
	
	private void generateCombination(int [] A, int index, int k, int [] result, int pos) {
		if (index == A.length) {
			for (int i=0; i<pos; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i=index; i<index+k && i<A.length; i++) {
			int num = formNumber(A, index, i);
			result[pos] = num;
			generateCombination(A, i+1, k, result, pos+1);

		}
	}
	
	public  void combination() {
		int [] input = {1,2,3, 4};
		int K = 2;
		int [] result = new int[input.length];
		combination(input, result, K, 0, 0);
	}

	public void combination(int [] input, int [] result, int K, int pos, int index) {
		if (pos == input.length) {
			for (int i=0; i< index; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i=pos; i<pos+K && i<input.length; i++) {
			result[index] = formNumber(input, pos, i);
			combination(input, result, K, i+1, index+1);
		}
	}
	
	private int formNumber(int [] A, int start, int end) {
		int num = 0;
		
		for (int i=start; i<= end; i++) {
			num = num*10 + A[i];
		}
		
		return num;
	}
}
