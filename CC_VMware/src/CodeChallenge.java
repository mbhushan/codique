
public class CodeChallenge {

	public static void main(String[] args) {
		CodeChallenge cc = new CodeChallenge();
		
		int [][] A = {
				{2, 3, 10, 2, 4, 8, 1},
				{5, 4, 3, 1},
				{7, 9, 5, 6, 3, 2}
		};
		
		for (int i=0; i<A.length; i++) {
			System.out.println("max diff: " + cc.maxDifference(A[i]));
		}
	}
	
	public int maxDifference(int[] A) {

		if (A == null || A.length < 1) {
			return -1;
		}
		
		int [] left = new int[A.length];
		int [] right = new int[A.length];
		
		boolean isDec = true;
		
		left[0] = A[0];
		for (int i=1; i<A.length; i++) {
			left[i] = Math.min(left[i-1], A[i]);
			if (A[i] > A[i-1]) {
				isDec = false;
			}
		}
		
		if (isDec) {
			return -1;
		}
		
		right[A.length-1] = A[A.length-1];
		for (int i=A.length-2; i>=0; i--) {
			right[i] = Math.max(right[i+1], A[i]);
		}
		
		int max = Integer.MIN_VALUE;
		for (int i=0; i<A.length; i++) {
			max = Math.max(max, (right[i] - left[i]));
		}
		
		return max;
    }
	

}
