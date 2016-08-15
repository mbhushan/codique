package com.manib.matrix;

public class PaintHouse1 {

	public static void main(String[] args) {
		PaintHouse1 ph = new PaintHouse1();
		
		int [][] costMatrix = {
				{1, 2, 3},
				{3, 5, 7},
				{5, 2, 4},
				{8, 6, 1}
		};
		
		System.out.println("min cost: " + ph.calcMinCost(costMatrix));
		//min cost: 8
	}
	
	public int calcMinCost(int [][] cost) {
		int row = cost.length;
		int col = cost[0].length;
		
		int [][] dp = new int[row][col];
		
		dp[0][0] = cost[0][0];
		dp[0][1] = cost[0][1];
		dp[0][2] = cost[0][2];
		
		for (int i=1; i<row; i++) {
			dp[i][0] = cost[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
			dp[i][1] = cost[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
			dp[i][2] = cost[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
		}
		
		return Math.min(dp[row-1][0], Math.min(dp[row-1][1], dp[row-1][2]));
	}
}
