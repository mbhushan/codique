package main.com.manib.words;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
Word Ladder
 
Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, 
such that only one letter can be changed at a time and each intermediate word must exist in the dictionary. For example, given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
*/

public class WordChain {
	
	private Set<String> dict = new HashSet<String>();
	private String [] words = {"hot","dot","dog","lot","log"};
	public WordChain() {
		Collections.addAll(dict, words);
	}
	
	public static void main(String[] args) {
		WordChain wc = new WordChain();
		String start = "hit";
		String end = "cog";
		List<ArrayList<String>>  result = wc.findChain(start, end);
		for (ArrayList<String> rlist: result) {
			System.out.println(rlist.toString());
		}
		//[hit, hot, lot, log, cog]
		//[hit, hot, dot, dog, cog]
	}
	
	public List<ArrayList<String>>  findChain(String start, String end) {
		
		List<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		Set<String> visited = new HashSet<String>();
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(start));
		visited.add(start);

		while (!queue.isEmpty()) {
			
			Node currNode = queue.remove();
			
			if (related(currNode.word, end)) {
				ArrayList<String> buff = new ArrayList<String>();
				buff.add(end);
				Node node = currNode;
				while (node.parent != null) {
					buff.add(node.word);
					node = node.parent;
				}
				buff.add(start);
				Collections.reverse(buff);
				result.add(buff);
			}
			
			for (String word: dict) {
				if (visited.contains(word)) {
					continue;
				}
				if (related(currNode.word, word)) {
					Node node = new Node(word);
					node.parent = currNode;
					queue.add(node);
					visited.add(word);
				}
			}
		}
		return result;
	}
	
	
	
	private boolean related(String first, String second) {
		int firstLen = first.length();
		int secondLen = second.length();
		
		if (Math.abs(firstLen-secondLen) > 1) {
			return false;
		}
		
		int count = 0;
		int len = firstLen > secondLen? firstLen: secondLen;
		for (int i=0; i<len; i++) {
			if (first.charAt(i) != second.charAt(i)) {
				++count;
			}
			if (count > 1) {
				return false;
			}
		}
		return true;
	}
	
}

class Node {
	String word;
	Node parent;
	
	Node(String word) {
		this.word = word;
		this.parent = null;
	}
	
	public String toString() {
		return "[word " + this.word +"; parent: " + this.parent.word + "]"; 
	}
}
