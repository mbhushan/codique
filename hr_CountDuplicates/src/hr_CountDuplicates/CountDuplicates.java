package hr_CountDuplicates;

import java.util.HashMap;
import java.util.Map;

public class CountDuplicates {

	public static void main(String[] args) {
		CountDuplicates cd = new CountDuplicates();
		
		int [] A = {1,1,2,2,2,3};
		
		System.out.println(cd.countDuplicates(A));
		
	}
	
	public int countDuplicates(int[] A) {
		if (A == null || A.length < 1) {
			return 0;
		}
		
		Map<Integer, Integer> hmap = new HashMap<Integer, Integer>();
		int dups = 0;
		for (int num: A) {
			int count = 0;
			if (hmap.containsKey(num)) {
				count = hmap.get(num);
			} 
			++count;
			hmap.put(num, count);
		}
		
		for (int key: hmap.keySet()) {
			if (hmap.get(key) > 1) {
				++dups;
			}
		}
		
		return dups;
	}
}
