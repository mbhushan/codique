package unionfind_NumberOfIslands;
/*
Number of Islands II
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water 
at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four 
edges of the grid are all surrounded by water.
Example:
Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]
*/
public class IslandsCountUF {

	private int [][] M ;
	private UnionFind uf = new UnionFind();
	private int islands = 0;
	private int row;
	private int col;
	
	public static void main(String[] args) {
		IslandsCountUF ic = new IslandsCountUF();
		int row = 3;
		int col = 3;
		
		ic.init(row, col);
		System.out.println("islands: " + ic.getIslands());
		int i = 0, j = 0;
		System.out.println("add land: " + i + ", " + j);
		ic.addLand(i, j);
		System.out.println("islands: " + ic.getIslands());
		
		i = 0; j = 1;
		System.out.println("add land: " + i + ", " + j);
		ic.addLand(i, j);
		System.out.println("islands: " + ic.getIslands());
		
		i = 1; j = 2;
		System.out.println("add land: " + i + ", " + j);
		ic.addLand(i, j);
		System.out.println("islands: " + ic.getIslands());
		
		i = 2; j = 1;
		System.out.println("add land: " + i + ", " + j);
		ic.addLand(i, j);
		System.out.println("islands: " + ic.getIslands());
		
		i = 1; j = 1;
		System.out.println("add land: " + i + ", " + j);
		ic.addLand(i, j);
		System.out.println("islands: " + ic.getIslands());
		
	}
	
	public int getIslands() {
		return this.islands;
	}
	
	public void addLand(int r, int c) {
		
		M[r][c] = 1;
		++this.islands;
		int key = r*this.col + c;
		boolean merge = false;
		
		if (r-1 >= 0 && M[r-1][c] == 1) {
			int up = (r-1)*this.col + c;
			updateIslands(key, up);
			merge = true;
		}
		if (r+1 < this.row && M[r+1][c] == 1) {
			int bottom = (r+1)*this.col + c;
			updateIslands(key, bottom);
			merge = true;
		}
		if (c-1 >= 0 && M[r][c-1] == 1) {
			int left = r*col + (c-1);
			updateIslands(key, left);
			merge = true;
		}
		if (c+1 < col && M[r][c+1] == 1) {
			int right = r*col + (c+1);
			updateIslands(key, right);
			merge = true;
		}
		
//		if (!merge) {
//			++this.islands;
//		}
	}
	
	private void updateIslands(int key, int x) {
		if (uf.union(key, x)) {
			--this.islands;
		} 
	}
	/*
	//The way most languages store multi-dimensional arrays is by doing a conversion like the following:
	// If matrix has size, n by m [i.e. i goes from 0 to (n-1) and j from 0 to (m-1) ], then:
	// matrix[ i ][ j ] = array[ i*m + j ].
	 */
	public void init(int row, int col) {
		this.row = row;
		this.col = col;
		M = new int[row][col];
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				int key = i*col + j;
				uf.makeSet(key);
			}
		}
	}
	
	
	
}
