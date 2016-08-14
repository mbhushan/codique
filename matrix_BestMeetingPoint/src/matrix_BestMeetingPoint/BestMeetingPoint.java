package matrix_BestMeetingPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author manib
 */

public class BestMeetingPoint {
	
	public static void main(String[] args) {
		BestMeetingPoint bmp = new BestMeetingPoint();
		int [][]M = {
				{1, 0, 0, 0, 1},
				{0, 0, 0, 0, 0},
				{0, 0, 1, 0, 0}
		};
		
		System.out.println("min distance: " + bmp.minDistance(M));
		//min distance: 6
	}
	
	public int minDistance(int [][] M) {
		
		int row = M.length;
		int col = M[0].length;
		
		List<Integer> rowList = new ArrayList<Integer>();
		List<Integer> colList = new ArrayList<Integer>();
		
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				if (M[i][j] == 1) {
					rowList.add(i);
					colList.add(j);
				}
			}
		}
		
		int distance = 0;
		int rowMedian = rowList.get(rowList.size()/2);
		
		for (int r: rowList) {
			distance += Math.abs(r - rowMedian);
		}
		
		Collections.sort(colList);
		int colMedian = colList.get(colList.size()/2);
		for (int c: colList) {
			distance += Math.abs(c - colMedian);
		}
		return distance;
	}
}
