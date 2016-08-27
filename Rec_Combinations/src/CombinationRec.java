import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationRec {

	public static void main(String args[]) {
		CombinationRec cr = new CombinationRec();
		String input = "abc";
		
		cr.printCombinations(input.toCharArray());

	}
	
	public void printCombinations(char [] input) {
		int index = 0;
		List<Character> list = new ArrayList<Character>();
		printCombinationUtil(input, index, list);
	}
	
	private void printCombinationUtil(char [] input, int index, List<Character> list) {
		if (list.size() > 0) {
			System.out.println(list.toString());
		}
		
		for (int i=index; i<input.length; i++) {
			if (i != index && input[i] == input[i-1]) {
				continue;
			}
			list.add(input[i]);
			printCombinationUtil(input, i+1, list);
			list.remove(list.size()-1);
		}
	}

	public void combinationEasy(char[] input) {
		List<Character> r = new ArrayList<>();
		Arrays.sort(input);
		combinationEasy(input, 0, r);
	}

	private void combinationEasy(char[] input, int pos, List<Character> r) {

		r.forEach(r1 -> System.out.print(r1 + " "));
		System.out.println();
		for (int i = pos; i < input.length; i++) {
			if (i != pos && input[i] == input[i - 1]) {
				continue;
			}
			r.add(input[i]);
			combinationEasy(input, i + 1, r);
			r.remove(r.size() - 1);
		}
	}

}
