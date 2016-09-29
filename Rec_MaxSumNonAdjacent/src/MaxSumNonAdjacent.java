import java.util.Arrays;

public class MaxSumNonAdjacent {

	public static void main(String[] args) {
		
		MaxSumNonAdjacent ms = new MaxSumNonAdjacent();
		
		int[][] M = { { 4, 1, 1, 4, 2, 1 }, 
				{ 2, 5, 3, 1, 7 },
				{ 2, 10, 13, 4, 2, 15, 10 },
				{5,  5, 10, 40, 50, 35},
				{5, 5, 10, 100, 10, 5},
				{3, 2, 7, 10}
		};
		
		for (int i=0; i<M.length; i++) {
			System.out.println(Arrays.toString(M[i]));
			System.out.println("max sum: " + ms.maxSumNonAdj(M[i]));
			System.out.println("max sum iter: " + ms.maxSumIter(M[i]));
			System.out.println();
		}
	}
	
	public int maxSumIter(int [] A) {
		int len = A.length;
		
		int incl = A[0];
		int excl = 0;
		for (int i=1; i<len; i++) {
			int tmp = incl;
			incl = Math.max(incl, excl+A[i]);
			excl = tmp;
					
		}
		
		return Math.max(incl, excl);
	}

	public Integer  maxSumNonAdj(int[] A) {
		if (A == null || A.length < 1) {
			return null; 
		}
		int index = 0;
		return maxSumNonAdj(A, index);
	}

	private int maxSumNonAdj(int[] A, int index) {
		if (index >= A.length) {
			return 0;
		}

		return Math.max(A[index] + maxSumNonAdj(A, index + 2),
				maxSumNonAdj(A, index + 1));
	}
}
