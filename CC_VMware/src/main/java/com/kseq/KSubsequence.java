package main.java.com.kseq;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KSubsequence {

	public static void main(String[] args) {
		KSubsequence ks = new KSubsequence();
		
		int [] A = {1, 2, 3, 4, 1};
		int k = 3;
		
		long ans = ks.kSub(k, A);
		System.out.println("ans: " + ans);
		
	}
	
	public long kSub(int k, int[] nums) {
		if (nums == null || nums.length < 1) {
			return 0;
		}
		
		int pos = 0;
		long [] count = new long[1];
		List<Integer> list = new ArrayList<Integer>();
		Set<String> set = new HashSet<String>();
		kSub(nums, k, pos, count, set);
		
		return count[0];

    }
	
	private void kSub(int [] A, int k, int pos, long [] count, Set<String> set) {
		if (pos >= A.length) {
			//System.out.println(list.toString());
			return;
		}
		
		for (int i=pos; i<A.length; i++) {
			long sum = getSum(A, pos, i);
			
			if ((sum > 0) && (sum%k == 0)) {
				String key = pos + "," + i;
				if (!set.contains(key)) {
					System.out.println("pos: " + pos + ", i: " + i);
					set.add(key);
					++count[0];
				}
				
			}
			kSub(A, k, i+1, count, set);
		}
	}
	
	public long getSum(int [] A, int start, int end) {
		long sum = 0;
		for (int i=start; i<= end; i++) {
			sum += A[i];
		}
		
		return sum;
	}


}

class Cell {
	int x;
	int y;
	
	Cell (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
