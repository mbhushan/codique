package array_HouseRobber1;
/*
House Robber (Java)
 
You are a professional robber planning to rob houses along a street. Each house has a certain 
amount of money stashed, the only constraint stopping you from robbing each of them is that 
adjacent houses have security system connected and it will automatically contact the police if 
two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine 
the maximum amount of money you can rob tonight without alerting the police.
*/

public class HouseRobber1 {
	
	public static void main(String[] args) {
		HouseRobber1 hr = new HouseRobber1();
		
		int [][] houses = { 
				{50, 1, 1, 50},
				{22 , 25, 1}
		};
		
		for (int i=0; i<houses.length; i++) {
			System.out.println("rob value: " + hr.robHouse(houses[i]));
		}
		//rob value: 100
		// rob value: 25

	}
	
	public int robHouse(int [] houses) {
		int index = 0;
		return  robHouse(houses, index);
	}
	
	private int robHouse(int [] houses, int index) {
		if (index >= houses.length) {
			return 0;
		}
		
		return Math.max(robHouse(houses, index+2) + houses[index], robHouse(houses, index+1));
	}
}
