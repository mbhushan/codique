import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StringCartesian {

	public static void main(String[] args) {
		StringCartesian sc = new StringCartesian();
		ArrayList<ArrayList<String>> slist = new ArrayList<ArrayList<String>>();

		ArrayList<String> A = new ArrayList<String>(Arrays.asList("Hello", "World"));
		ArrayList<String> B = new ArrayList<String>(Arrays.asList("Game"));
		ArrayList<String> C = new ArrayList<String>(Arrays.asList("Go", "Home"));

		slist.add(A);
		slist.add(B);
		slist.add(C);
		
		sc.cartesian(slist);
	}
	
	public void cartesian(ArrayList<ArrayList<String>> list) {
		int index = 0;
		List<String> result = new ArrayList<String>();
		int size = list.size();
		cartesian(list, size, index, result);
	}
	
	private void cartesian(ArrayList<ArrayList<String>> list, int size, int index, List<String> result) {
		if (result.size() == list.size()) {
			System.out.println(result.toString());
			return ;
		}

		ArrayList<String> currList = list.get(index);
		int len = currList.size();
		for (int j = 0; j < len; j++) {
			result.add(currList.get(j));
			cartesian(list, size, index + 1, result);
			result.remove(result.size() - 1);
		}

	}
}
