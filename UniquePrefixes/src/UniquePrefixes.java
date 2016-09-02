import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
Find shortest unique prefix for every word in a given list
Given an array of words, find all shortest unique prefixes to represent each word in the given array. 
Assume that no word is prefix of another.

Examples:

Input: arr[] = {"zebra", "dog", "duck", "dove"}
Output: dog, dov, du, z
Explanation: dog => dog
             dove = dov 
             duck = du
             z   => zebra 

Input: arr[] =  {"geeksgeeks", "geeksquiz", "geeksforgeeks"};
Output: geeksf, geeksg, geeksq}
*/

public class UniquePrefixes {
	
	private TrieNode trie = new TrieNode('0');
	
	public static void main(String[] args) {
		UniquePrefixes up = new UniquePrefixes();
		String [] input = {"zebra", "dog", "duck", "dove"};
		
		for (int i=0; i<input.length; i++) {
			up.insert(input[i]);
		}
		up.printTrie();
		
		up.uniquePrefixes();
	}
	
	
	public void uniquePrefixes() {
		StringBuffer sb = new StringBuffer();
		uniquePrefixes(trie, sb);
	}
	
	private void uniquePrefixes(TrieNode node, StringBuffer sb) {
		if (node == null) {
			return;
		}
		
		if (node.count == 1) {
			System.out.println(sb.toString());
			return;
		}
		
		for (char ch : node.children.keySet()) {
			sb.append(ch);
			uniquePrefixes(node.children.get(ch), sb);
			sb.deleteCharAt(sb.length() - 1);
		}
		
	}
	
	
	public void insert(String word) {
		if (word == null || word.length() < 1) {
			return;
		}
		char [] wordChars = word.toCharArray();
		int index = 0;
		++trie.count;
		insert(wordChars, index, trie);
	}
	
	public void insert(char [] wordChars, int index, TrieNode currNode) {
		if (index == wordChars.length) {
			return ;
		}
		
		char ch = wordChars[index];
		if (currNode.children.containsKey(ch)) {
			++currNode.children.get(ch).count;
			currNode = currNode.children.get(ch);
		} else {
			TrieNode node = new TrieNode(ch);
			currNode.children.put(ch, node);
			currNode = node;
		}
		if (index == wordChars.length-1) {
			currNode.isLeaf = true;
		}
		insert(wordChars, index+1, currNode);
	}
	
	public void printTrie() {
		StringBuffer sb = new StringBuffer();
		List<String> buff = new ArrayList<String>();
		//printTrie(trie, sb);
		printTrie(trie, buff);
	}
	
	//private void printTrie(TrieNode node, StringBuffer sb) {
	private void printTrie(TrieNode node, List<String> buff) {
		if (node == null || node.isLeaf) {
			System.out.println(buff.toString());
			return;
		}
		
		for (Character ch: node.children.keySet()) {
			String value = ch + String.valueOf(node.children.get(ch).count);
			//sb.append(value);
			buff.add(value);
			printTrie(node.children.get(ch), buff);
			//sb.deleteCharAt(sb.length()-1);
			buff.remove(buff.size()-1);
		}
	}
}

class TrieNode {
	char ch;
	Map<Character, TrieNode> children;
	boolean isLeaf = false;
	int count = 0;
	
	TrieNode(char ch) {
		this.ch = ch;
		this.children = new HashMap<Character, TrieNode>();
		this.count = 1;
	}
	
	public String toString() {
		return "[" + ch + ", " + children.toString() + ", leaf: " + isLeaf + "]";
	}
}
