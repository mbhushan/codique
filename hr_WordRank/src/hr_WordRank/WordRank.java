package hr_WordRank;

public class WordRank {

	public static int findRank(String a) {
		int n = a.length();
		long mul = fact(n);
		long rank = 1;
		int[] count = new int[256];
		for (int i = 0; i < n; i++) {
			int ch = a.charAt(i);
			++count[ch];
		}

		for (int i = 1; i < 256; ++i) {
			count[i] += count[i - 1];
		}
		for (int i = 0; i < n - 1; i++) {
			int x = 0;
			for (int j = i + 1; j < n; j++) {
				if (a.charAt(i) > a.charAt(j)) {
					x++;
				}
			}
			System.out.println(a.charAt(i) + " x: " + x);
			x = count[a.charAt(i) - 1];
			System.out.println("x_after: " + x);
			mul /= n - i;
			rank += (x * mul);
			rank = rank % 1000000007;

			// for( i = a.charAt(i); i < 256; ++i )
			// --count[i];
		}

		return (int) rank - 1;
	}

	private static long fact(int input) {
		long output = 1;
		for (int i = 2; i <= input; i++) {
			output = output * i;
		}
		return output;
	}

	public static void main(String[] args) {
		// String st = "DTNGJPURFHYEW";
		String st = "caabbc";
		System.out.println("rank of " + st + ": " + findRank(st));
	}
}