package rec_IslandsNumber;

public class IslandsNumber {

	public static void main(String[] args) {
		IslandsNumber in = new IslandsNumber();
		int [][] M = {
				{1, 1, 1, 1, 0},
				{1, 1, 0, 1, 0},
				{1, 1, 0, 0, 1},
				{0, 0, 0, 1, 0}	
		};
		
		System.out.println("number of islands: " + in.numOfIslands(M));
	}
	
	public int numOfIslands(int [][] M) {
		if (M == null || M.length < 1) {
			return 0;
		}
		
		int row = M.length;
		int col = M[0].length;
		
		int count = 0;
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				if (M[i][j] == 1) {
					numOfIslands(M, i, j);
					++count;
				}
			}
		}
		
		return count;
	}
	
	private void numOfIslands(int [][] M, int r, int c) {
		if ((r < 0 || r >= M.length) || (c < 0 || c >= M[0].length) || M[r][c] != 1) {
			return;
		}
		
		M[r][c] = 2; //mark it discovered.
		
		numOfIslands(M, r+1, c);
		numOfIslands(M, r-1, c);
		numOfIslands(M, r, c+1);
		numOfIslands(M, r, c-1);
	}
}
