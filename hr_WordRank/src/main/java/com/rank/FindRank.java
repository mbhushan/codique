package main.java.com.rank;

public class FindRank {
	public static int findRank(String a) {
	    int n = a.length(); 
		long rank = 1; 
		long mul = fact(n);
		int [] count = new int[256] ;
		int [] P = new int[256] ;
		for(int i = 0; i<n; i++) {
			int ch = a.charAt(i);
			count[ch] += 1;  
			P[ch] += 1;
		}
		
		for(int i = 0; i < n; i++) {
			for (int j = i+1; j<n ; j++) { 
			if (a.charAt(i) > a.charAt(j)) {
				count[a.charAt(i)] += count[a.charAt(j)];
			}
			}
		}
		
		for (int i = 0; i < n; i++) { 
			int x = 0;
			int y = 1;
			for (int j = i+1; j<n ; j++) { 
				if (a.charAt(i) >= a.charAt(j)) {
					++x;
				}
				if (a.charAt(i) == a.charAt(j)) {
					++y;
				}
				}
			
			//int x = count[(int)a.charAt(i)];
			rank = rank+ x*(fact(n-i-1)); 
			//mul /= n-i;
			//rank += x*mul;
			//System.out.println("rank: " + rank + ", p_i: " + P[a.charAt(i)] );
			//rank /= fact(P[a.charAt(i)]);
			rank /= fact(y);
		}
//		for (int i=0; i<n; i++) {
//			System.out.println("rank: " + rank + ", p_i: " + P[a.charAt(i)] );
//			rank /= fact(P[a.charAt(i)]);
//			P[a.charAt(i)] = 1;
//		}
		rank = rank % 1000003;
		return (int)rank;
	}
		private static long fact(int input){
		long output = 1;
		for(int i = 2;i<=input;i++){
			output = output * i;			
		}		
		return output;
	}
		
		public static void main(String [] args) {
			String st = "caabbc";
			System.out.println("rank for: " + st + ": " + findRank(st));
		}

}
