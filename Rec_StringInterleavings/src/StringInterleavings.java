import java.util.Arrays;


public class StringInterleavings {

	public static void main(String[] args) {
		StringInterleavings si = new StringInterleavings();
		String [] A = {"to", "this", "day"};
		String [] B = {"my", "buddy", "cool"};
		
		for (int i=0; i<A.length; i++) {
			si.interleave(A[i], B[i]);
		}
	}
	
	public void interleave(String A, String B) {
		int i=0, j=0, index=0;
		char [] data = new char[A.length() + B.length()];
		interleave(A.toCharArray(), i, B.toCharArray(), j, index, data);
	}
	
	private void interleave(char [] A, int i, char [] B, int j, int index, char [] data) {
		if (index == data.length) {
			System.out.println(Arrays.toString(data));
			return;
		}
		
		if (i < A.length) {
			data[index] = A[i];
			interleave(A, i+1, B, j, index+1, data);
		}
		
		if (j < B.length) {
			data[index] = B[j];
			interleave(A, i, B, j+1, index+1, data);
		}
	}
}
