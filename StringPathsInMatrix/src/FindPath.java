import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class FindPath {
    public static void main(String [] args) {
        FindPath fp = new FindPath();
        
          char [][] m = {
        	   {'a','y','a','l'},
               {'m','a','n','k'},
               {'a','y','l','n'},
               {'m','a','n','k'},
               };

        String str = "mayank";
        
        fp.findPaths(m, str);
        
    }
    
    public void findPaths(char [][] m, String str) {
    	int row = m.length;
    	int col = m[0].length;
    	
    	boolean[][] visited = new boolean[row][col];
    	
    	for (int i=0; i<row; i++) {
    		for (int j=0; j<col; j++) {
    			if (m[i][j] == str.charAt(0)) {
    				printPaths(visited, m, str, i, j, new StringBuffer(), new ArrayList<Cell>());
    			}
    		}
    	}
    }
    
    private void printPaths(boolean[][] visited, char [][]m, String str, int i, int j, StringBuffer sb, List<Cell> result) {
    	
    	if (i < 0 || i >= m.length || j < 0 || j >= m[0].length) {
    		return;
    	}
    	
    	if (visited[i][j]) {
    		return;
    	}
    	visited[i][j] = true;
    	sb.append(m[i][j]);
    	//System.out.println(sb.toString());
    	result.add(new Cell(i,j, m[i][j]));
    	if (sb.toString().equals(str)) {
    		//System.out.println(str);
    		System.out.println(result);
    	}
    	
    	printPaths(visited, m, str,  i+1, j, sb, result);
    	printPaths(visited, m, str,  i-1, j, sb, result);
    	printPaths(visited, m, str,  i, j-1, sb, result);
    	printPaths(visited, m, str,  i, j+1, sb, result);
    	
    	visited[i][j] = false;
    	sb.deleteCharAt(sb.length()-1);
    	result.remove(result.size()-1);
    }
    

    void printAllPaths(char[][] m, String str){
        if (str == null || str.length() < 1) {
            return;
        }
       char first = str.charAt(0);
       char end = str.charAt(str.length()-1);
       for (int i=0; i<m.length; i++) {
           for (int j=0; j<m[0].length; j++) {
               if (m[i][j] == first) {
                   printPaths(m, str, new Cell(i,j), end);
               }
           }
       }
   }
   
   private void printPaths(char [][] m, String str, Cell start, char end) {
       Queue<Cell> queue = new LinkedList<Cell>();
       queue.add(start);
       
       int row = m.length;
       int col = m[0].length;
       boolean [][] visited = new boolean[row][col];
       
       int i = 1;
       int len = str.length();
       while (!queue.isEmpty() && i < len) {
           
    	   Cell currCell = queue.remove();
           
           List<Cell> neighbors = getNeighbors(m, currCell, str.charAt(i), row, col);
           for (Cell cell: neighbors) {
               if (!visited[cell.x][cell.y]) {
                   
                   cell.parent = currCell;
                   queue.add(cell);
                   System.out.print(cell + ", " + m[cell.x][cell.y]);
                   if (m[cell.x][cell.y] == end) {
                       showPath(cell);
                   }
                   visited[cell.x][cell.y] = true;
               }
           }
           System.out.println();
           ++i;
       }
   }
   
   private void showPath(Cell cell) {
       if (cell == null) {
           return;
       }
       showPath(cell.parent);
       System.out.println(cell);
   }
   
   private List<Cell> getNeighbors(char [][]m ,Cell cell, char ch, int row, int col) {
       int i = cell.x;
       int j = cell.y;
       //check for visited.
       List<Cell> result = new ArrayList<Cell>();
       if (i+1 < row && (m[i+1][j] == ch)) {
           result.add(new Cell(i+1, j));
       } 
       if (i-1 >= 0 && (m[i-1][j] == ch)) {
           result.add(new Cell(i-1, j));
       }
       if (j+1 < col && (m[i][j+1] == ch)) {
           result.add(new Cell(i, j+1));
       } 
       if (j-1 >= 0 && (m[i][j-1] == ch)) {
           result.add(new Cell(i, j-1));
       }
       
       return result;
   }
}
   
   class Cell {
       int x;
       int y;
       char ch;
       
       Cell parent;
       
       Cell(int x, int y) {
           this.x = x;
           this.y = y;
           this.parent = null;
       }
       
 Cell(int x, int y, char ch) {
           this.x = x;
           this.y = y;
           this.ch = ch;
           this.parent = null;
       }
       
       public String toString() {
           return "[" + x + ", " + y + ", " + ch + "]";
       }
   }