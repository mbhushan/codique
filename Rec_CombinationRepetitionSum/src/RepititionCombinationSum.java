import java.util.ArrayList;
import java.util.List;


public class RepititionCombinationSum {

	public static void main(String[] args) {
		RepititionCombinationSum rs = new RepititionCombinationSum();
		int [] A = {2,3,5};
		int target = 10;
		
		rs.combinationSum(A, target);
	}
	
	public void combinationSum(int [] A, int target) {
		int sum = 0;
		int index = 0;
		List<Integer> result = new ArrayList<Integer>();
		combinationSum(A, target, index, sum, result);
	}
	
	private void combinationSum(int [] A, int target, int index, int sum, List<Integer> result) {
		if (sum == target) {
			System.out.println(result.toString());
			return;
		}
		
		if (index >= A.length) {
			return;
		}
		
		if (sum > target) {
			return;
		}
	
		if (sum < target) {
			sum += A[index];
			result.add(A[index]);
			combinationSum(A, target, index, sum, result);
			sum -= A[index];
			result.remove(result.size()-1);
			combinationSum(A, target, index+1, sum, result);
		}
	}
}
