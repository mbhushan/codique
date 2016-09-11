import java.util.ArrayList;
import java.util.List;


public class ImageRectangle {

	public static void main(String[] args) {
		int[][] image = {
			    {1, 0, 1, 1, 1, 1, 1},
			    {1, 0, 0, 1, 0, 1, 1},
			    {1, 1, 1, 0, 0, 0, 1},
			    {1, 0, 1, 1, 0, 1, 1},
			    {1, 0, 1, 1, 1, 1, 1},
			    {1, 0, 0, 0, 0, 1, 1},
			    {1, 1, 1, 0, 0, 1, 1},
			    {1, 1, 1, 1, 1, 1, 1},
			  };
		
		ImageRectangle ir = new ImageRectangle();
		ir.findPatterns(image);
	}
	
	public void findPatterns(int [][] M) {
		int row = M.length;
		int col = M[0].length;
		
		boolean [][] visited = new boolean[row][col];
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				if (!visited[i][j] && M[i][j] == 0) {
					List<Cell> list = new ArrayList<Cell>();
					findPatternsUtil(M, i, j, visited, list);
					System.out.println(list.toString());
				}
			}
		}
	}
	
	private void findPatternsUtil(int [][] M, int i, int j, boolean [][] visited, List<Cell> list) {
		if (i < 0 || i >= M.length || j < 0 || j >= M[0].length) {
			return;
		}
		if (M[i][j] == 1) {
			return;
		}
		
		if (visited[i][j]) {
			return;
		}
		
		if (M[i][j] == 0) {
			visited[i][j] = true;
			list.add(new Cell(i, j));
			findPatternsUtil(M, i+1, j, visited, list);
			findPatternsUtil(M, i-1, j, visited, list);
			findPatternsUtil(M, i, j+1, visited, list);
			findPatternsUtil(M, i, j-1, visited, list);
			
			//System.out.println(list.toString());
			
		} 
		//System.out.println(list.toString());
		//list.remove(list.size()-1);
		
	}
}

class Cell {
	int x;
	int y;
	
	Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}
}
