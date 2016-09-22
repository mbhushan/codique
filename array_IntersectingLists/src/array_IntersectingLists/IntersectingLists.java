package array_IntersectingLists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IntersectingLists {

	public static void main(String[] args) {
		IntersectingLists IL = new IntersectingLists();
		
		Integer [] A = {2, 4, 5, 6, 7, 8, 9, 11};
		Integer [] B = {1, 2, 3, 6, 9, 10, 4, 13, 12, 11};
		
		List<Integer> first = new ArrayList<Integer>();
		List<Integer> second = new ArrayList<Integer>();
		
		Collections.addAll(first, A);
		Collections.addAll(second, B);
		
		Set<Integer> intersection = IL.findIntersections(first, second);
		System.out.println("intersection: ");
		System.out.println(intersection);
		
	}
	
	
	public Set<Integer> findIntersections(List<Integer> first, List<Integer> second) {
		if (first == null || second == null) {
			return null;
		}
		
		Set<Integer> result = new HashSet<Integer>();
		
		Collections.sort(first);
		Collections.sort(second);
		
		int firstSize = first.size();
		int secondSize = second.size();
		
		int i=0, j=0;
		
		while (i<firstSize && j<secondSize) {
			if (first.get(i).intValue() == second.get(j).intValue()) {
				result.add(first.get(i));
				++i;
				++j;
			} else if (first.get(i).intValue() < second.get(j).intValue()) {
				++i;
			} else {
				++j;
			}
		}
		return result;
	}
	
	
}
