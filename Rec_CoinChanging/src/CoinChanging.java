import java.util.ArrayList;
import java.util.List;

public class CoinChanging {

	public static void main(String[] args) {
		CoinChanging cc = new CoinChanging();
		int[] coins = { 1, 2, 3 };
		int target = 5;
		cc.coinChange(coins, target);
	}

	public void coinChange(int[] coins, int target) {
		int index = 0;
		List<Integer> changes = new ArrayList<Integer>();
		int sum = 0;
		coinChange(coins, index, target, sum, changes);
	}

	private void coinChange(int[] coins, int i, int target, int sum,
			List<Integer> changes) {
		if (target == sum) {
			System.out.println(changes.toString());
			return;
		}

		if (sum > target) {
			return;
		}

		if (i >= coins.length) {
			return;
		}

		changes.add(coins[i]);
		sum += coins[i];
		coinChange(coins, i, target, sum, changes);
		sum -= coins[i];
		changes.remove(changes.size() - 1);

		coinChange(coins, i + 1, target, sum, changes);
	}
}
