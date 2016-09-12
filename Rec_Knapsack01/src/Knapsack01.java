import java.util.ArrayList;
import java.util.List;


public class Knapsack01 {

	public static void main(String[] args) {
		Knapsack01 kn = new Knapsack01();
		
		int [] weights = {2, 2, 4, 5};
		int [] values = { 2, 4, 6, 9};
		int total = 8;
		
		kn.knapsack(weights, values, total);
	}
	
	
	public void knapsack(int [] weights, int [] values, int target) {
		int index = 0;
		List<Integer> list = new ArrayList<Integer>();
		int currSum = 0;
		int maxValue = Integer.MIN_VALUE;
		Result ans = new Result(maxValue);
		knapsack(weights, values, target, currSum, index, list, ans );
		System.out.println("result: " + ans);
	}
	
	public void knapsack(int [] weights, int [] values, int target, int currSum, int index, List<Integer> list, Result ans) {
		if (currSum <= target) {
			int sum = list.stream().mapToInt(Integer::intValue).sum();
			if (sum > ans.maxValue) {
				ans.maxValue = sum;
				ans.indices = ((List<Integer>) ((ArrayList<Integer>) list).clone());
			}
		}
		
		if (currSum > target) {
			return;
		}
		
		if (index >= weights.length) {
			return;
		}
		
		list.add(values[index]);
		knapsack(weights, values, target, currSum+weights[index], index+1, list, ans);
		list.remove(list.size()-1);
		knapsack(weights, values, target, currSum, index+1, list, ans);
	}
}

class Result {
	int maxValue;
	List<Integer> indices ;
	
	Result(int maxValue) {
		this.maxValue = maxValue;
		this.indices = new ArrayList<Integer>();
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(this.maxValue + ", " + this.indices.toString());
		sb.append("]");
		return sb.toString();
	}
}
