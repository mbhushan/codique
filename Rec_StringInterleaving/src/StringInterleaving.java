public class StringInterleaving {

	public static void main(String[] args) {
		StringInterleaving si = new StringInterleaving();
		
		si.testInterleaving();
	}

	public void testInterleaving() {
		System.out.println(isInterleaved("XXY", "XXZ", "XXZXXXY"));
		System.out.println(isInterleaved("XY", "WZ", "WZXY"));
		System.out.println(isInterleaved("XY", "X", "XXY"));
		System.out.println(isInterleaved("YX", "X", "XXY"));
		System.out.println(isInterleaved("XXY", "XXZ", "XXXXZY"));

	}

	public boolean isInterleaved(String A, String B, String C) {
		if (A == null && B == null && C == null) {
			return true;
		}
		if (A.length() + B.length() != C.length()) {
			return false;
		}
		int i = 0, j = 0, index = 0;
		return isInterleaved(A.toCharArray(), B.toCharArray(), C.toCharArray(),
				i, j, index);
	}

	private boolean isInterleaved(char[] A, char[] B, char[] C, int i, int j,
			int index) {
		if (i == A.length && j == B.length && index == C.length) {
			return true;
		}

		boolean flag1 = false;
		boolean flag2 = false;
		if ((i < A.length) && (A[i] == C[index])) {
			flag1 = isInterleaved(A, B, C, i + 1, j, index + 1);
		}  
		if ((j < B.length) && (B[j] == C[index])) {
			flag2 = isInterleaved(A, B, C, i, j + 1, index + 1);
		}

		return flag1 || flag2;
	}
}
