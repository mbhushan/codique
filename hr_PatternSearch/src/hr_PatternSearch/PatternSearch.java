package hr_PatternSearch;

import java.util.Arrays;

/*
You are provided a string S of length N. the only characters contained in this string are A, B, C and D.
you are given a pattern P of length M, the only characters contained in this pattern are A, B, C, D and * (stands for A, B, C or D)
figure out how many times the pattern P occur in string S.
 */
public class PatternSearch {

	public static void main(String[] args) {
		
		PatternSearch ps = new PatternSearch();
		
		String S = "ABCDDDDABCDABCDDCCCB";
		//String P = "AB*D";
		String P = "DD";
		
		System.out.println(ps.search(S, P));
	}
	
	public int search(String S, String P) {
		if (S == null || P == null) {
			return 0;
		}
		int count = 0;
		
		int slen = S.length();
		int plen = P.length();
		
		boolean [][] T = new boolean[slen+1][plen+1];
		for (int i=0; i<=slen; i++) {
			T[i][0] = true;
		}
		System.out.println(Arrays.toString(T[0]));
		for (int i=1; i<=slen; i++) {
			for (int j=1; j<=plen; j++) {
				if ((S.charAt(i-1) == P.charAt(j-1)) || (P.charAt(j-1) == '*')) {
					T[i][j] = T[i-1][j-1];
				} else {
					T[i][j] = false;
				}
			}
			System.out.println(Arrays.toString(T[i]));
		}
	
		for (int i=0; i<=slen; i++) {
			if (T[i][plen]) {
				++count;
			}
		}
		
		return count;
	}
	
}
