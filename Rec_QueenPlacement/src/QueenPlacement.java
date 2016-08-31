
public class QueenPlacement {

	public static void main(String[] args) {
		QueenPlacement qp = new QueenPlacement();
		int numQueen = 4;
		qp.placeQueens(numQueen);
	}
	
	
	public void placeQueens(int numQueen) {
		Cell [] cells = new Cell[numQueen];
		int row = 0;
		placeQueens(numQueen, row, cells);
		
		System.out.println("queen placements: ");
		for (Cell cell: cells) {
			System.out.println(cell.toString());
		}
		
	}
	
	private boolean placeQueens(int numQueen, int row, Cell [] cells) {
		if (row == numQueen) {
			return true;
		}
		
		for (int col=0; col < numQueen; col++) {
			boolean found = true;
			
			for (int queen=0; queen<row; queen++) {
				if (cells[queen].row == row || cells[queen].col == col || cells[queen].row - cells[queen].col == row-col ||
						cells[queen].row + cells[queen].col == row+col) {
					found = false;
					break;
				}
			}
			if (found) {
				cells[row] = new Cell(row, col);
				if (placeQueens(numQueen, row+1, cells)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
}

class Cell {
	int row;
	int col;
	
	Cell(int x, int y) {
		this.row = x;
		this.col = y;
	}
	
	public String toString() {
		return "[" + this.row + ", " + this.col + "]";
	}
}
