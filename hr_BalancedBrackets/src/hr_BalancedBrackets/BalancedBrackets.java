package hr_BalancedBrackets;

import java.util.Stack;

/*
A bracket is considered to be any one of the following characters: (, ), {, }, [, or ].

Two brackets are considered to be a matched pair if the an opening bracket (i.e., (, [, or {) occurs to the left of a closing bracket (i.e., ), ], or }) of the exact same type. There are three types of matched pairs of brackets: [], {}, and ().

A matching pair of brackets is not balanced if the set of brackets it encloses are not matched. For example, {[(])} is not balanced because the contents in between { and } are not balanced. The pair of square brackets encloses a single, unbalanced opening bracket, (, and the pair of parentheses encloses a single, unbalanced closing square bracket, ].

By this logic, we say a sequence of brackets is considered to be balanced if the following conditions are met:

It contains no unmatched brackets.
The subset of brackets enclosed within the confines of a matched pair of brackets is also a matched pair of brackets.
Given n strings of brackets, determine whether each sequence of brackets is balanced. If a string is balanced, print YES on a new line; otherwise, print NO on a new line.
 */
public class BalancedBrackets {
	
	public static void main(String[] args) {
		BalancedBrackets bb = new BalancedBrackets();
		String [] S = {
				"{[()]}",
				"{[(])}",
				"{{[[(())]]}}"
		};
		
		for (int i=0; i<S.length; i++) {
			System.out.println(S[i]);
			System.out.println(bb.isBalanced(S[i]));
			System.out.println();
		}
	}

	public  boolean isBalanced(String expression) {
		if (expression == null || expression.length() < 1) {
			return false;
		}
		Stack<Character> stack = new Stack<Character>();
		boolean flag = true;
		
		char [] in = expression.toCharArray();
		
		for (int j=0; j<in.length; j++) {
			char ch = in[j];
			if (isopen(ch)) {
				stack.push(ch);
			} else {
				if (stack.isEmpty()) {
					flag = false;
					break;
				} else {
					char curr = matching(ch);
					char last = stack.peek().charValue();
					if (curr != last) {
						flag = false;
						break;
					} else {
						stack.pop();
					}
				}
			}
		}
	    flag = flag ? stack.isEmpty() : flag;  
		return flag;
    }
	
	private boolean isopen(char ch) {
		if (ch == '(' || ch == '[' || ch == '{') {
			return true;
		}
		return false;
	}
	
	private  char matching(char ch) {
		if (ch == ')') {
			return '(';
		} else if (ch == ']') {
			return '[';
		} else if (ch == '}') {
			return '{';
		}
		return '-';
	}
}
