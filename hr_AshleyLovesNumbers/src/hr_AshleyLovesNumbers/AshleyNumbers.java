package hr_AshleyLovesNumbers;

import java.util.HashSet;
import java.util.Set;

public class AshleyNumbers {

	public static void main(String[] args) {
		AshleyNumbers an = new AshleyNumbers();
		int [][] arr = {
				{7, 8},
				{52, 80},
				{57, 64},
				{74, 78}
		};
		
		an.countNumbers(arr);
	}
	
	public void countNumbers(int[][] arr) {
		int row = arr.length;
		for (int i=0; i<row; i++) {
			int start = arr[i][0];
			int end = arr[i][1];
			int count = 0;
			for (int j=start; j<=end; j++) {
				char [] charr = String.valueOf(j).toCharArray();
				Set<Character> hset = new HashSet<Character>();
				boolean found = true;
				for (char ch: charr) {
					if (hset.contains(ch)) {
						found = false;
						break;
					} 
					hset.add(ch);
				}
				if (found) {
					++count;
				}
			}
			System.out.println(count);
		}

    }
}
