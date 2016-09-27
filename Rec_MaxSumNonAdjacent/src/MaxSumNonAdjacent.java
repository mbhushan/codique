import java.util.Arrays;

public class MaxSumNonAdjacent {

	public static void main(String[] args) {
		
		MaxSumNonAdjacent ms = new MaxSumNonAdjacent();
		
		int[][] M = { { 4, 1, 1, 4, 2, 1 }, { 2, 5, 3, 1, 7 } };
		
		for (int i=0; i<M.length; i++) {
			System.out.println(Arrays.toString(M[i]));
			System.out.println("max sum: " + ms.maxSumNonAdj(M[i]));
		}
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
