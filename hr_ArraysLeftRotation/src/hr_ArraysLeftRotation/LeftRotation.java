package hr_ArraysLeftRotation;

import java.util.Arrays;

/*
 A left rotation operation on an array of size  shifts each of the array's elements  unit to the left. For example, if left rotations are performed on array , then the array would become .

 Given an array of  integers and a number, , perform  left rotations on the array. Then print the updated array as a single line of space-separated integers.

 Input Format

 The first line contains two space-separated integers denoting the respective values of  (the number of integers) and  (the number of left rotations you must perform). 
 The second line contains  space-separated integers describing the respective elements of the array's initial state.

 Constraints

 Output Format

 Print a single line of  space-separated integers denoting the final state of the array after performing  left rotations.

 Sample Input

 5 4
 1 2 3 4 5
 Sample Output

 5 1 2 3 4
 Explanation

 When we perform d=4 left rotations, the array undergoes the following sequence of changes:

 Thus, we print the array's final state as a single line of space-separated values, which is 5 1 2 3 4.
 */
public class LeftRotation {

	public static void main(String[] args) {
		LeftRotation lr = new LeftRotation();

		int[] arr = { 1, 2, 3, 4, 5 };
		int d = 2;
		arr = lr.arrayLeftRotation(arr, arr.length, d);
		System.out.println(Arrays.toString(arr));
	}

	public int[] arrayLeftRotation(int[] arr, int n, int d) {
		int i, j, k, temp;
		int gcd = gcd(d, n);
		System.out.println("gcd: " + gcd);
		for (i = 0; i < gcd; i++) {
			/* move i-th values of blocks */
			temp = arr[i];
			j = i;
			while (true) {
				k = j + d;
				if (k >= n)
					k = k - n;
				if (k == i)
					break;
				arr[j] = arr[k];
				j = k;
			}
			arr[j] = temp;
		}

		return arr;
	}

	/* Fuction to get gcd of a and b */
	private int gcd(int a, int b) {
		if (b == 0)
			return a;
		else
			return gcd(b, a % b);
	}
}
