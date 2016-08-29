public class OneEditStrings {

	public static void main(String[] args) {
		OneEditStrings od = new OneEditStrings();
		System.out.println(od.isOneEdit("cat", "dog"));
		System.out.println(od.isOneEdit("cat", "cats"));
		System.out.println(od.isOneEdit("cat", "cut"));
		System.out.println(od.isOneEdit("cats", "casts"));
		System.out.println(od.isOneEdit("catsts", "casts"));
	}

	public boolean isOneEdit(String A, String B) {
		if (A == null || B == null) {
			return false;
		}
		int alen = A.length();
		int blen = B.length();
		if (Math.abs(alen - blen) > 1) {
			return false;
		}
		int i = 0;
		int j = 0;
		int dist = isOneEdit(A.toCharArray(), i, B.toCharArray(), j);
		return dist == 1;
	}

	private int isOneEdit(char[] A, int i, char[] B, int j) {
		if (i == A.length) {
			return B.length - j;
		} else if (j == B.length) {
			return A.length - i;
		}

		if (A[i] == B[j]) {
			return isOneEdit(A, i + 1, B, j + 1);
		} else {
			return 1 + Math.min(
					isOneEdit(A, i + 1, B, j + 1),
					Math.min(isOneEdit(A, i + 1, B, j),
							isOneEdit(A, i, B, j + 1)));
		}
	}
}
