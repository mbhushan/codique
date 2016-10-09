package hr_CountPairsK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountPairs {

	public static void main(String[] args) {
		CountPairs cp = new CountPairs();
		
		int [] numbers = {
			1,2,3,4,5,6	
		};
		int k = 2;
		System.out.println(cp.countPairs(numbers, k));
	}
	
	
	public int countPairs(int[] numbers, int k) {
		int pairs = 0;
		Set<Integer> hset = new HashSet<Integer>();
		
		for (int num: numbers) {
			hset.add(num);
		}
		
		List<Integer> sortedList = new ArrayList<Integer>(hset);
		Collections.sort(sortedList);
		
		int size = sortedList.size();
		for (int i=0; i<size; i++) {
			int a = sortedList.get(i);
			int key = a + k;
			if (hset.contains((key))) {
				++pairs;
			}
		}
		
		return pairs;

    }
}
