import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
We are given a list of words that have both 'simple' and 'compound' words in them. 
Write an algorithm that prints out a list of words without the compound words that are made up of the simple words.
Input: chat, ever, webchat, snap, salesperson, per, person, sales, son, whatsoever, what so.
Output should be: 
chat, ever, web, per, sales, son, what, so

chat, ever, snap, per, sales, son, what, so, web,
*/
public class WordFinder {

	TrieNode trie;
	
	WordFinder() {
		trie = new TrieNode('0');
	}
	
	public static void main(String[] args) {
		WordFinder wf = new WordFinder();
		String [] words = {"chat", "ever", "webchat", "snap", "salesperson", "per", 
				"person", "sales", "son", "whatsoever", "what", "so", "web"
				};
		
		for (int i=0; i<words.length; i++) {
			wf.insert(words[i]);
		}
		
		wf.printTrie();
		
		wf.findSimpleWords();
		System.out.println("==========");
		wf.findPrefix("n");
		Set<String> dict = new HashSet<String>();
		Collections.addAll(dict, words);
		Set<String> complex = new HashSet<String>();
		for (String word: words) {
			String orig = new String(word);
			String prefix = wf.findPrefix(word);
			StringBuffer sb = new StringBuffer();
			int count = 0;
			boolean flag = false;
			while(dict.contains(prefix)) {
				sb.append(prefix);
				++count;
				word = word.substring(prefix.length());
				if (dict.contains(word)) {
					flag = true;
					break;
				}
				prefix = wf.findPrefix(word);
			}
			if (flag) {
				System.out.println("COMPOSITE: " + orig);
				complex.add(orig);
			}
			if (sb.toString().equals(orig)) {
				System.out.println("found: " + orig + ", sb: " + sb.toString() +", count: " + count);
			} else {
				System.out.println("not found: " + orig + ", sb: " + sb.toString() + ", count: " + count);
			}
		}
		
		System.out.println("Simple words: ");
		
		for (String word: words) {
			if (!complex.contains(word)) {
				System.out.print(word + ", ");
			}
		}
		System.out.println("");
		
	}
	
	public String findPrefix(String word) {
		if (word == null || word.length() < 1) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		int index = 0;
		findPrefix(trie, word.toCharArray(), index, sb);
		//System.out.println("prefix: " + sb.toString());
		return sb.toString();
	}
	
	private void findPrefix(TrieNode node, char [] word, int index, StringBuffer sb) {
		if (node == null || node.isLeaf) {
			return;
		}
		
		if (node.children.containsKey(word[index])) {
			node = node.children.get(word[index]);
			sb.append(node.ch);
			findPrefix(node, word, index+1, sb);
		}
	}
	
	public void findSimpleWords() {
		StringBuffer sb = new StringBuffer();
		findSimpleWords(trie, sb);
	}
	
	private void findSimpleWords(TrieNode node, StringBuffer sb) {
		if (node == null || (node.isLeaf && node.children.size() != 0)) {
			System.out.println(sb.toString());
			return;
		}
		
		for (char ch: node.children.keySet()) {
			sb.append(ch);
			findSimpleWords(node.children.get(ch), sb);
			sb.deleteCharAt(sb.length()-1);
		}
		
	}
	
	public void insert(String word) {
		if (word == null || word.length() < 1) {
			return;
		}
		
		char [] chArr = word.toCharArray();
		int index = 0;
		++this.trie.count;
		insert(chArr, index, trie);
		//System.out.println(trie);
		
	}
	
	private void insert(char [] word, int index, TrieNode node) {
		if (index == word.length) {
			return;
		}
		
		if (node.children.containsKey(word[index])) {
			node = node.children.get(word[index]);
			++node.count;
		} else {
			TrieNode newNode = new TrieNode(word[index]);
			node.children.put(word[index], newNode);
			node = newNode;
		}
		if (index == word.length-1) {
			node.isLeaf = true;
		}
		insert(word, index+1, node);
	}
	
	public void printTrie() {
		StringBuffer sb = new StringBuffer();
		List<String> list = new ArrayList<String>();
		printTrie(trie, list);
	}
	
	private void printTrie(TrieNode node, List<String> list) {
		if (node == null || node.children.size() == 0) {
			System.out.println(list.toString());
			return;
		}
		
		for (Character ch: node.children.keySet()) {
			String value = ch + String.valueOf(node.children.get(ch).count);
			list.add(value);
			printTrie(node.children.get(ch), list);
			list.remove(list.size()-1);
		}
	}
}

class TrieNode {
	char ch;
	Map<Character, TrieNode> children;
	boolean isLeaf;
	int count;
	
	public TrieNode(char ch) {
		this.ch = ch;
		this.children = new HashMap<Character, TrieNode>();
		this.isLeaf = false;
		this.count = 1;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[char: " + this.ch + ", children: ");
		if (this.children != null) {
			for (Character ch: this.children.keySet()) {
				sb.append(ch + " ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
