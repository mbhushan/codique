
public class RPNMinimumEdits {

	public static void main(String[] args) {
		RPNMinimumEdits rpn = new RPNMinimumEdits();
		String [] input = { "xxxx*x*x*", "xx*xx**", "x*x", "*xx*"};
		
		for (int i=0; i<input.length; i++) {
			System.out.println("input string: " + input[i]);
			System.out.println("min edits for RPN: " + rpn.minEdits(input[i]));
		}
	}
	
	public int minEdits(String input) {
		if (input == null || input.length() < 1) {
			return 0;
		}
		
		char [] A = input.toCharArray();
		int index = 0;
		int xCount = 0;
		return minEdits(A, index, xCount);
	}
	
	private int minEdits(char [] A, int index, int xCount) {
		if (index == A.length) {
			return (xCount == 1) ? 0: Integer.MAX_VALUE;
		}
		
		if (A[index] == 'x') {
			//we can use the x as it is, delete it or replace it with *
			//a. using as it is
			int v1 = minEdits(A, index+1, xCount+1);
			
			//b. deleting it
			int v2 = Integer.MAX_VALUE;
			if (xCount > 1) {
				v2 = minEdits(A, index+1, xCount-1);
				v2 = (v2 != Integer.MAX_VALUE) ? v2+1: Integer.MAX_VALUE;
			}
			
			//c. replace it with *
			int v3 = minEdits(A, index+1, xCount);
			v3 = (v3 != Integer.MAX_VALUE) ? v3+1: Integer.MAX_VALUE;
			
			return Math.min(v1, Math.min(v2, v3));
			
		} else {
			//if its * then we can reduce 2 x to 1 x, delete it or replace with x
			int v0 = Integer.MAX_VALUE;
			if (xCount >= 2) {
				v0 =  minEdits(A, index+1, xCount-1);
				//v0 = (v0 != Integer.MAX_VALUE) ? v0 +1: Integer.MAX_VALUE;
			}  
			int v1 = minEdits(A, index+1, xCount);
			v1 = (v1 != Integer.MAX_VALUE) ? v1 +1: Integer.MAX_VALUE;
			
			int v2 = minEdits(A, index+1, xCount+1);
			v2 = (v2 != Integer.MAX_VALUE) ? v2+1: Integer.MAX_VALUE;
			
			return Math.min(v0, Math.min(v1, v2));
		}
	}
}
