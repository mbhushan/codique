import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CombinationSizeK {

	public static void main(String[] args) {
		CombinationSizeK csk = new CombinationSizeK();
		
		int [] A = {1, 2, 1, 3};
		int k = 2;
		csk.printCombination(A, k);
	}
	
	public void printCombination(int [] A, int k) {
		int index = 0;
		List<Integer> list = new ArrayList<Integer>();
		Arrays.sort(A);
		printCombinationUtil(A, k, index, list);
	}
	
	private void printCombinationUtil(int [] A, int k, int index, List<Integer> list) {
		if (list.size() == k) {
			System.out.println(list.toString());
		}
		if (index >= A.length) {
			return;
		}
		
		for (int i=index; i<A.length; i++) {
			if (i != index && A[i] == A[i-1]) continue;
			list.add(A[i]);
			printCombinationUtil(A, k, i+1, list);
			list.remove(list.size()-1);
		}
	}
}
