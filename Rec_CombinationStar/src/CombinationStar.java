public class CombinationStar {

	public static void main(String[] args) {
		CombinationStar cs = new CombinationStar();

		char[] A = { 'a', 'b', 'c' };
		cs.printCombination(A);
	}

	public void printCombination(char[] A) {
		int index = 0;
		boolean[] result = new boolean[A.length];
		printCombinationUtil(A, index, result);
	}

	private void printCombinationUtil(char[] A, int index, boolean[] result) {
		for (int i = 0; i < result.length; i++) {
			if (result[i]) {
				System.out.print(A[i] + " ");
			} else {
				System.out.print("* ");
			}
		}
		System.out.println();

		for (int i = index; i < A.length; i++) {
			result[i] = true;
			printCombinationUtil(A, i + 1, result);
			result[i] = false;
		}
	}
}
