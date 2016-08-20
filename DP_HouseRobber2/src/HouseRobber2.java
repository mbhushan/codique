/*
House Robber II (Java)
 
After robbing those houses on that street, the thief has found himself a new place for his thievery 
so that he will not get too much attention. This time, all houses at this place are arranged in a circle. 
That means the first house is the neighbor of the last one. Meanwhile, the security system for these 
houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the 
maximum amount of money you can rob tonight without alerting the police.

Analysis
This is an extension of House Robber. There are two cases here 1) 1st element is included and last 
is not included 2) 1st is not included and last is included. Therefore, we can use the similar dynamic 
programming approach to scan the array twice and get the larger value.
*/
public class HouseRobber2 {
	
	public static void main(String[] args) {
		HouseRobber2 hr = new HouseRobber2();

		int [][] houses = { 
				{50, 1, 1, 50},
				{22 , 25, 5}
		};
		
		for (int i=0; i<houses.length; i++) {
			System.out.println("max rob value: " + hr.robHouse(houses[i]));
		}
		
	}
	
	public int robHouse(int [] houses) {
		if (houses == null || houses.length < 1) {
			return 0;
		}
		
		int val1 = robHouse(houses, 0, houses.length-2);
		int val2 = robHouse(houses, 1, houses.length-1);
		return Math.max(val1, val2);
	}
	
	private int robHouse(int [] houses, int index , int len) {
		if (index == len) {
			return houses[index];
		}
		
		int [] dp = new int[houses.length];
		dp[index] = houses[index];
		dp[index+1] = Math.max(dp[index], houses[index+1]);
		
		for (int i=index+2; i<=len; i++) {
			dp[i] = Math.max(dp[i-2] + houses[i], dp[i-1]);
		}
		
		return dp[len];

	}
}
