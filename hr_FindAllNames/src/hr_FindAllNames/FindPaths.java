package hr_FindAllNames;

//This is the text editor interface. 
//Anything you type or change here will be seen by the other person in real time.

//char [][] m = {{'a','y','a','l'},
//              {'m','a','n','k'},
//              {'a','y','l','n'},
//              {'m','a','n','k'},
//              }

//  String str = "mayank"

//  [1,0][0,0,][0,1][0,2][1,2][1,3]
//       [[3,0], [3,1], [2, 1], [1,1], [1, 2], [1,3]]

/*

Output:
[[1, 0], [2, 0], [2, 1], [3, 1], [3, 2], [3, 3]]
[[1, 0], [2, 0], [2, 1], [1, 1], [1, 2], [1, 3]]
[[1, 0], [0, 0], [0, 1], [1, 1], [1, 2], [1, 3]]
[[1, 0], [0, 0], [0, 1], [0, 2], [1, 2], [1, 3]]
[[1, 0], [1, 1], [2, 1], [3, 1], [3, 2], [3, 3]]
[[1, 0], [1, 1], [0, 1], [0, 2], [1, 2], [1, 3]]
[[3, 0], [2, 0], [2, 1], [3, 1], [3, 2], [3, 3]]
[[3, 0], [2, 0], [2, 1], [1, 1], [1, 2], [1, 3]]
[[3, 0], [3, 1], [2, 1], [1, 1], [1, 2], [1, 3]]
*/

import java.util.ArrayList;
import java.util.List;



public class FindPaths {
 public static void main(String [] args) {
     FindPaths fp = new FindPaths();
     
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
        return "[" + x + ", " + y + "]";
    }
}

