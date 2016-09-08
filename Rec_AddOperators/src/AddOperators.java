import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
Given a string that contains only digits 0-9 and a target value, return all possibilities 
to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
Examples:
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
*/

public class AddOperators {

	public static void main(String[] args) {
		AddOperators ao = new AddOperators();
		String [] digits = {"123", "232", "105", "00", "3456237490" };
		int [] targets = {6, 8, 5, 0, 9191};
		
//		for (int i=0; i<digits.length; i++) {
//			ao.findExpression(digits[i], targets[i]);
//			System.out.println("======================");
//		}
		
		ao.findExpression("105", 5);
		
		int [] A = {1, 0, 5};
		//ao.validCombinations(A);
	}
	
	public List<ArrayList<Integer>>  validCombinations(int [] digits) {
		int index = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		List<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		validCombinations(digits, index, list, result);
		return result;
	}
	
	private void validCombinations(int [] digits, int index, ArrayList<Integer> list, List<ArrayList<Integer>>  result) {
		if (index == digits.length && list.size() > 1) {
			System.out.println(list.toString());
			ArrayList<Integer> tmp = (ArrayList<Integer>) list.clone();
			result.add(tmp);
			return;
		}
		
		for (int i=index; i<digits.length; i++) {
			int num = getNum(digits, index, i);
			list.add(num);
			validCombinations(digits, i+1, list, result);
			list.remove(list.size()-1);
		}
	}
	
	private int getNum(int [] digits, int start, int end) {
		int num = 0;
		for (int i=start; i<=end; i++) {
			num = num*10 + digits[i];
		}
		return num;
	}
	
	public void findExpression(String digits, int target) {
		char [] chArr = digits.toCharArray();
		int [] nums = new int[chArr.length];
		
		for (int i=0; i<chArr.length; i++) {
			nums[i] = Character.getNumericValue(chArr[i]);
		}
		
		List<ArrayList<Integer>> combo = validCombinations(nums);
		System.out.println("combo: " + combo.toString());
		for (List<Integer> list : combo) {
			int index = 0;
			Stack<Integer> stack = new Stack<Integer>();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				stack.push(list.get(i));
			}
			String[] buff = new String[stack.size()-1];
			String[] operators = { "*", "+", "-" };
			System.out.println("list data: " + list.toString());
			System.out.println("stack: " + stack.toString());
			expression(stack, index, target, operators, buff);
		}
	}
	
	public void expression(Stack<Integer> stack, int index, int target, String [] operators, String [] buff) {
		//System.out.println("stack: " + stack.toString());
		if (stack.size() == 1 && stack.peek() == target) {
			System.out.println(Arrays.toString(buff));
			return;
		}
		
		if (index == buff.length) {
			return;
		}
		
		if (stack.size() < 2) {
			return;
		}
		
		for (int i=0; i<operators.length; i++) {
			int x = stack.pop();
			int y = stack.pop();
			int ans = evaluate(x, y, operators[i]);
			//if (ans <= target) {
				stack.push(ans);
				buff[index] = operators[i];
				expression(stack, index+1, target, operators, buff);
				stack.pop();
				
			//}
			stack.push(y);
			stack.push(x);
		}
		
		
	}
	
	private int evaluate(int x, int y, String operator) {
		if (operator.equals("*")) {
			return x*y;
		} else if (operator.equals("+")) {
			return x+y;
		} else if (operator.equals("-")) {
			return y-x;
		}
		//log error condition.
		return 0;
	}
}
