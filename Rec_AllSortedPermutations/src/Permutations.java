import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Permutations {

	public static void main(String[] args) {
		Permutations perm = new Permutations();
		
		String [] st = {"abc"};
		
		for (int i=0; i<st.length; i++) {
			perm.printPerms(st[i].toCharArray());
			//perm.stringPerm(st[i].toCharArray());
		}
		
	}
	

	public void stringPerm(char [] input) {
		stringPerm(input, 0);
	}
	
	private void stringPerm(char [] input, int index) {
		if (index == input.length) {
			System.out.println(Arrays.toString(input));
			return;
		}
		
		for (int i=index; i < input.length; i++) {
			char ch = input[index];
			input[index] = input[i];
			input[i] = ch;
			stringPerm(input, index+1);
			ch = input[index];
			input[index] = input[i];
			input[i] = ch;
		}
	}
	
	
	public void printPerms(char [] input) {
		int index = 0;
		List<String> perms = new ArrayList<String>();
		permute(input, index, perms);
		
		Collections.sort(perms);
		System.out.println(perms.toString());
	}
	
	private void permute(char [] input, int index, List<String> perms) {
		if (index == input.length) {
			perms.add(new String(input));
			System.out.println(Arrays.toString(input));
		}
		
		for (int i=index; i<input.length; i++) {
			char ch = input[i];
			input[i] = input[index];
			input[index] = ch;
			permute(input, index+1, perms);
			ch = input[i];
			input[i] = input[index];
			input[index] = ch;
			
		}
	}
}
