package hr_StringRank;

import java.util.HashMap;
import java.util.Map;

public class StringRank {
	
	public static int[] get_ranks(String[] words) {
		
		int [] ranks = new int[words.length];
		
		for (int i=0; i<words.length; i++) {
			ranks[i] = rankPerm(words[i]);
		}
		
		return ranks;

    }

	 
	public static int rankPerm(String perm) {
	    long rank = 0;
	    long sfxCount = 1;
	    Map<Character, Long> hmap = new HashMap<Character, Long>();
	    for (int i = perm.length() - 1; i > -1; i--) {
	        char x = perm.charAt(i);
	        Long xCount = hmap.containsKey(x) ? hmap.get(x) + 1 : 1;
	        hmap.put(x, xCount);
	        for (Map.Entry<Character, Long> e : hmap.entrySet()) {
	            if (e.getKey() < x) {
	                rank += (sfxCount * (e.getValue() / xCount)) ;
	            	rank = rank % 1000000007;

	            }
	        }
	        sfxCount *= perm.length() - i;
	        sfxCount /= xCount;
	    }
	    return (int)rank;
	}
	public static void main(String[] args) {
		
		String [] words = {"bac", "aaa", "abba", "caabbc", "axaelixedhtshsixbuzouqtjrkpyafthezfuehcovcqlbvmkbrwxhzrxymricmehktxepyxomxcx"};
		int [] ranks = get_ranks(words);
		for (int r: ranks) {
			System.out.println(r);
		}
		System.out.println("=======");
		long ans = rankPerm("abba");
		
		System.out.println(ans);
	}
 
}