/*

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

 */
public class FileDiff {

	public static void main(String[] args) {
		FileDiff diff = new FileDiff();
		
		
	}
	
	
}
