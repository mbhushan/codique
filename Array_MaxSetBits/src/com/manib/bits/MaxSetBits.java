package com.manib.bits;

/**
 * @author manib
 * You are given an integer array with N elements: d[0], d[1], ... d[N - 1]. 
 * You can perform AT MOST one move on the array: choose any two integers [L, R], and 
 * flip all the elements between (and including) the L-th and R-th bits. L and R represent 
 * the left-most and right-most index of the bits marking the boundaries of the segment which 
 * you have decided to flip.
 * What is the maximum number of '1'-bits (indicated by S) which you can obtain in the final bit-string? 
 */

public class MaxSetBits {

	public static void main(String[] args) {
		MaxSetBits msb = new MaxSetBits();
		int [] A = {1, 0, 0, 1, 0, 0, 1, 0};
		
		int ans = msb.maxSetBits(A);
		
		System.out.println("max set bits: " + ans);
		/*
		 * output: 
		 * [start: 1, end: 5]
		 * max set bits: 6
		 */
	}
	
	public int maxSetBits(int [] A) {
		
		int start = 0;
		int end = 0;
		int runningSum = 0;
		int maxSum = 0;
		for (int i=0; i<A.length; i++) {
			runningSum += (A[i] == 0) ? -1: 1;
			if (runningSum < maxSum) {
				maxSum = runningSum;
				end = i;
			} 
			if (runningSum > 0) {
				start = i+1;
				runningSum = 0;
			}
		}
		System.out.println("[start: " + start + ", end: " + end + "]");
		int totalSet = 0;
		for (int i=0; i<A.length; i++) {
			if (i >= start && i <= end) {
				totalSet += (A[i] == 0) ? 1: 0;
			} else {
				totalSet += (A[i] == 1? 1: 0);
			}
		}
		return totalSet;
	}
}
