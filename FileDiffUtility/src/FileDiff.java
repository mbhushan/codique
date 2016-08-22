import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
PROBLEM: Write a diff program comparing two files, that gives a unix like output. 

$ cat f1.txt 
apples
oranges
kiwis
carrots

$ cat f2.txt 
apples
mango
carrots
grapefruits

$ diff -u f1.txt f2.txt 
--- f1.txt	2016-08-20 07:55:34.000000000 -0700
+++ f2.txt	2016-08-20 08:17:57.000000000 -0700
@@ -1,4 +1,4 @@
 apples
-oranges
-kiwis
+mango
 carrots
+grapefruits

Ouput:
printing file: file1.txt
apples
oranges
kiwis
carrots
printing file: file2.txt
apples
mango
carrots
grapefruits
carrots
[0, 1, 2, 3, 4]
[1, 0, 1, 2, 3]
[2, 1, 2, 3, 4]
[3, 2, 3, 4, 3]
[4, 3, 4, 5, 4]
[5, 4, 5, 6, 5]
--- file1.txt
+++ file2.txt
apples
-oranges
-kiwis
+mango
carrots
+grapefruits
+carrots

 */
public class FileDiff {
	
	List<String> f1 = new ArrayList<String>();
	List<String> f2 = new ArrayList<String>();
	String firstFile;
	String secondFile;
	
	
	public static void main(String[] args) {
		FileDiff diff = new FileDiff();
		
		String file1 = "file1.txt";
		String file2 = "file2.txt";
	
		diff.readFile(file1, file2);
		diff.findDiff();
	}
	
	public void findDiff() {
		diff(f1, f2);
	}
	
	public void diff(List<String> first, List<String> second) {
		int row = second.size();
		int col = first.size();
		int [][] dp = new int[row+1][col+1];
		
		dp[0][0] = 0;
		
		for (int i=1; i<=col; i++) {
			dp[0][i] = dp[0][i-1]+1;
		}
		
		for (int i=1; i<=row; i++) {
			dp[i][0] = dp[0][i-1]+1;
		}
		
		for (int i=1; i<=row; i++) {
			for (int j=1; j<=col; j++) {
				if (first.get(j-1).equals(second.get(i-1))) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					//dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1; 
					//only add and delete operations allowed unlike min edit distance problem where update is also ok
					dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1; 
				}
			}
		}
		
		for (int i=0; i<=row; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		
		System.out.println("--- " + this.firstFile);
		System.out.println("+++ " + this.secondFile);
		Stack<String> stack = new Stack<String>();
		
		int i = row;
		int j = col;
		while (i >= 1 && j >= 1) {
			if (dp[i][j] == dp[i-1][j] + 1){
				stack.push("+" + second.get(i-1));
				--i;
			} else if (dp[i][j] == dp[i][j-1] + 1){
				stack.push("-" + first.get(j-1));
				--j;
			} else if (dp[i][j] == dp[i-1][j-1]) {
				stack.push(first.get(j-1));
				--i;
				--j;
			} 
		}
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
	
	public void readFile(String first, String second) {
		this.firstFile = first;
		this.readFile(first, f1);
		System.out.println("printing file: " + first);
		this.printFile(f1);
		
		this.secondFile = second;
		this.readFile(second, f2);
		System.out.println("printing file: " + second);
		this.printFile(f2);
	}

	
	public void printFile(List<String> file) {
		for (String line: file) {
			System.out.println(line);
		}
	}
	
	public void readFile(String fname, List<String> file) {
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader("data/" + fname));
			String in = null;
			while ((in = br.readLine()) != null ) {
				file.add(in);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
