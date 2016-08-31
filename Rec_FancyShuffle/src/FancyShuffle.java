import java.util.Arrays;


public class FancyShuffle {

	public static void main(String[] args) {
		FancyShuffle fs = new FancyShuffle();
		String [] S = {
				"aab",
				"abccc",
				"aaab"
		};
		for (int i=0; i<S.length; i++) {
			fs.fancyShuffle(S[i]);
			System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		}
	}
	
	public void fancyShuffle(String input) {
		char [] A = input.toCharArray();
		int index = 0;
		fancyShuffle(A, index);
	}
	
	private void fancyShuffle(char [] A, int index) {
		if (index == A.length) {
			System.out.println(Arrays.toString(A));
			return ;
		}
		
		for (int i=index; i<A.length; i++) {
			if (i != index && A[i] == A[index]) {
				continue;
			}
			if (index > 0 && A[i] == A[index-1]) {
				continue;
			}
			char ch = A[i];
			A[i] = A[index];
			A[index] = ch;
			fancyShuffle(A, index+1);
			ch = A[i];
			A[i] = A[index];
			A[index] = ch;
		}
	}
}
