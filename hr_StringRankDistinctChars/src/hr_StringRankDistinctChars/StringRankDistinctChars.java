package hr_StringRankDistinctChars;

/*
 * Finding Rank of a Word. (No Repetition)
 * Given a string. Tell its rank among all its permutations sorted lexicographically.
http://blog.suryaelite.com/2010/01/finding-rank-of-word-no-repetition.html
http://blog.suryaelite.com/2010/02/finding-rank-of-word-with-repetition.html
 */
public class StringRankDistinctChars {

	public static void main(String[] args) {
		
		StringRankDistinctChars obj = new StringRankDistinctChars();
		
		String [] S = {
				"bac", "sunil", "surya", "string"
		};
		
		
		for (int i=0; i<S.length; i++) {
			int r = obj.rank(S[i]);
			System.out.println(S[i] +": " + r);
		}
	}
	
	
	public int rank(String S) {
		if (S == null || S.length() < 1) {
			return -1;
		}
		
		int len = S.length();
		int rank = 0; //0 based indexing.
		
		for (int i=0; i<len; i++) {
			int prefix = 0;
			for (int j=i+1; j<len; j++) {
				if (S.charAt(j) < S.charAt(i)) {
					++prefix;
				}
			}
			rank = rank + prefix * fact(len-i-1);
		}
		
		return rank;
	}
	
	private int fact(int n) {
		if (n == 0 || n == 1) {
			return 1;
		}
		int ans = 1;
		for (int i=2; i<=n; i++) {
			ans = ans * i;
		}
		return ans;
	}
}
