package main.java.demo;

import java.util.TreeSet;



public class DemoRank {
	 
	public static int fact(int n){
		if(n<=1){
			return 1;
		}
		return n*fact(n-1);
	}
	public static void printRank(String str){
        int len=str.length();
        TreeSet<Character> treeSet=new TreeSet<Character>();
        for(int i=0;i<len;i++){
        	treeSet.add(str.charAt(i));
        }
        int rank=0;
        for(int i =0;i<len;i++){
        	rank=rank+(fact(len-i-1)* (treeSet.headSet(str.charAt(i)).size()+1-1));
        	treeSet.remove(str.charAt(i));
        }
 
        System.out.println(rank+1);
 
    }
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		    printRank("caabbc");
	}
 
}
