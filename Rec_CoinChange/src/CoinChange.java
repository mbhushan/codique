import java.util.ArrayList;
import java.util.List;


/*
Given a set of possible change, such as {1, 5, 10, 25}, and an amount such as 13, 
return all possible ways you can give change back to someone
*/

public class CoinChange {

	public static void main(String[] args) {
		CoinChange cc = new CoinChange();
		int coins [] = {1, 5, 10, 25};
		int amount = 13;
		
		cc.change(coins, amount);
	}
	
	public void change(int [] coins, int amount) {
		int index = 0;
		List<Integer> result = new ArrayList<Integer>();
		change(coins, amount, index, result);
	}
	
	private void change(int [] coins, int amount, int index, List<Integer> result) {
		if (amount == 0) {
			System.out.println(result);
			return;
		}
		
		if (amount < 0) {
			return;
		}
		
		if (index == coins.length) {
			return;
		}
		
		result.add(coins[index]);
		change(coins, amount - coins[index], index, result);
		result.remove(result.size()-1);
		change(coins, amount, index+1, result);
	}
}
