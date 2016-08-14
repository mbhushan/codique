package com.manib.matrix;

import java.util.Arrays;

/**
 * @author manib
 * 
 */

public class WallAndGates {
	
	public static void main(String[] args) {
		WallAndGates wag = new WallAndGates();
		
		int [][] M = {
				{Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
				{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
				{Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
				{0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}
		};
		
		wag.solveWallGates(M);
	}
	
	public void solveWallGates(int [][] M) {
		int row = M.length;
		int col = M[0].length;
		int dist = 0;
		for (int i=0; i<row; i++) {
			for (int j=0 ;j <col; j++) {
				if (M[i][j] == 0) {
					solveWallGatesUtil(M, i, j, dist);
				}
			}
		}
		
		System.out.println("solved matrix: ");
		for (int i=0; i<row; i++) {
			System.out.println(Arrays.toString(M[i]));
		}
	}
	
	private void solveWallGatesUtil(int [][] M, int row, int col, int dist) {
		if ((row < 0 || row >= M.length) || (col < 0 || col >= M[0].length) || dist > M[row][col]) {
			return ;
		}
		M[row][col] = dist;
		solveWallGatesUtil(M, row, col+1, dist+1);
		solveWallGatesUtil(M, row, col-1, dist+1);
		solveWallGatesUtil(M, row+1, col, dist+1);
		solveWallGatesUtil(M, row-1, col, dist+1);
	}
}
