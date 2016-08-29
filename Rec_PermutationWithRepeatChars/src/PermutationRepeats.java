import java.util.Arrays;


public class PermutationRepeats {

	public static void main(String[] args) {
		PermutationRepeats pr = new PermutationRepeats();
		String st = "abc";
		pr.permute(st.toCharArray());
	}
	
	public void permute(char [] A) {
		char [] data = new char[A.length];
		int index = 0;
		permute(A, index, data);
	}
	
	private void permute(char [] A, int index, char [] data) {
		if (index >= A.length) {
			System.out.println(Arrays.toString(data));
			return;
		}
		
		for (int i=0; i<A.length; i++) {
			data[index] = A[i];
			permute(A, index+1, data);
		}
	}
}
