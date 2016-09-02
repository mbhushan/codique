import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MatrixPaths {

	public static void main(String[] args) {
		MatrixPaths mp = new MatrixPaths();
		
		int[][] M = { 
				{ 1, 2, 3 }, 
				{ 4, 5, 6 }, 
				{ 7, 8, 9 } };

		mp.printPaths(M);
	}
	
	public void printPaths(int [][] M) {
		int i=0, j=0;
		int len = M.length + M[0].length-1;
		List<Integer> path = new ArrayList<Integer>();
		for (int x=0; x<len; x++) {
			path.add(0);
		}
		printPaths(M, i, j, path);
	}
	
	private void printPaths(int [][] M, int i, int j, List<Integer> path) {
		
		if (i == M.length-1 && j == M[0].length-1) {
			path.set(i+j, M[i][j]);
			System.out.println(path.toString());
		}
		
		if (i >= M.length || j >= M[0].length) {
			return;
		}
		
		path.set(i+j, M[i][j]);
		printPaths(M, i+1, j, path);
		printPaths(M, i, j+1, path);
	}
}
