import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinationRangeK {

	public static void main(String[] args) {
		CombinationRangeK cr = new CombinationRangeK();

		String[] input = { "redbluebluered", "dogdogdogdog", "dogdogdogdog",
				"dogcatcatdig" };
		String[] pattern = { "abba", "abba", "aaaa", "abbc" };

		for (int i = 0; i < input.length; i++) {
			System.out.println("input: " + input[i]);
			System.out.println("pattern: " + pattern[i]);
			System.out.println("pattern match-2: "
					+ cr.isPatternMatch(input[i], pattern[i]));
			System.out.println();
		}

	}

	public boolean isPatternMatch(String input, String pattern) {
		if (input == null || pattern == null) {
			return false;
		}

		List<ArrayList<String>> combos = combination(input, pattern);

		for (ArrayList<String> list : combos) {
			String[] data = list.toArray(new String[0]);
			if (matchPattern(data, pattern)) {
				return true;
			}
		}
		return false;
	}

	public List<ArrayList<String>> combination(String data, String pattern) {
		char[] input = data.toCharArray();
		int K = 5;
		int patLen = pattern.length();
		List<String> result = new ArrayList<String>();
		List<ArrayList<String>> combos = new ArrayList<ArrayList<String>>();
		combination(input, result, K, 0, 0, patLen, combos);

		return combos;
	}

	public void combination(char[] input, List<String> result, int K, int pos,
			int index, int patLen, List<ArrayList<String>> combos) {
		if (index > patLen) {
			return;
		}

		if (pos == input.length) {
			if (index != patLen) {
				return;
			}
			combos.add(new ArrayList<String>(result));
			return;
		}

		for (int i = pos; i < pos + K && i < input.length; i++) {
			String str = formString(input, pos, i);
			result.add(str);
			combination(input, result, K, i + 1, index + 1, patLen, combos);
			result.remove(result.size() - 1);
		}

	}

	private String formString(char[] A, int start, int end) {
		StringBuffer sb = new StringBuffer();
		for (int i = start; i <= end; i++) {
			sb.append(A[i]);
		}

		return sb.toString();
	}

	public boolean matchPattern(String[] strArr, String pattern) {
		if (strArr == null || pattern == null) {
			return false;
		}

		char[] patArr = pattern.toCharArray();

		if (patArr.length != strArr.length) {
			return false;
		}

		Map<Character, String> hmap = new HashMap<Character, String>();
		hmap.put(patArr[0], strArr[0]);

		for (int i = 1; i < patArr.length; i++) {
			if (hmap.containsKey(patArr[i])) {
				if (!hmap.get(patArr[i]).equals(strArr[i])) {
					return false;
				}
			} else if (hmap.containsValue(strArr[i])) {
				return false;
			} else {
				hmap.put(patArr[i], strArr[i]);
			}
		}

		return true;
	}
}
