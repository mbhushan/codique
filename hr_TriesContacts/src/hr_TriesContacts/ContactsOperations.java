package hr_TriesContacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactsOperations {
	
	private TrieNode root;
	
	public ContactsOperations() {
		this.root = new TrieNode('0');
	}

	public static void main(String[] args) {
		ContactsOperations co = new ContactsOperations();
		
		String [] names = {"hack", "hackerrank", "mani", "manib", "shona", "shreya", "shoe"};
		
		for (int i=0; i<names.length; i++) {
			co.insert(names[i]);
		}
		
		co.printTrie();
		
		String [] toFind = {"hac", "hak", "man", "sh"};
		
		for (int i=0; i<toFind.length; i++) {
			System.out.println(toFind[i] + "; " + co.find(toFind[i]));
		}
		
	}
	
	public int find(String key) {
		if (key == null || key.length() < 1) {
			return 0;
		}
		int index = 0;
		char [] charr = key.toCharArray();
		return find(root, charr, index);
	}
	
	private int find(TrieNode node, char [] key, int index) {
		if (node == null) {
			return 0;
		}
		if (index == key.length) {
			return node.count;
		}
		
		if (index >= key.length) {
			return 0;
		}
		
		int count = 0;
		for (char ch: node.children.keySet()) {
			if (ch == key[index]) {
				count = find(node.children.get(ch), key, index+1);
			} 
		}
		
		return count;
	}
	
	public void insert(String name) {
		if (name == null || name.length() < 1) {
			return;
		}
		char [] charr = name.toCharArray();
		int index = 0;
		insert(root, charr, index);
	}
	
	private void insert(TrieNode node, char [] name, int index) {
		if (index == name.length) {
			return;
		}
		
		if (node.children.containsKey(name[index])) {
			node = node.children.get(name[index]);
			++node.count;
		} else {
			TrieNode newNode = new TrieNode(name[index]);
			node.children.put(name[index], newNode);
			node = newNode;
		}
		
		if (index == name.length-1) {
			node.isLeaf = true;
		}
		
		insert(node, name, index+1);
	}
	
	public void printTrie() {
		List<String> list = new ArrayList<String>();
		printTrie(root, list);
	}
	
	private void printTrie(TrieNode node, List<String> list) {
		if (node == null || node.children.size() == 0) {
			System.out.println(list);
			return;
		}
		
		for (char ch: node.children.keySet()) {
			String value = ch + ":" + node.children.get(ch).count;
			list.add(value);
			printTrie(node.children.get(ch), list);
			list.remove(list.size()-1);
		}
	}
	
	
	
}

class TrieNode {
	char value;
	Map<Character, TrieNode> children;
	boolean isLeaf;
	int count;
	
	TrieNode(char value) {
		this.value = value;
		this.isLeaf = false;
		this.count = 1;
		this.children = new HashMap<Character, TrieNode>();
	}
	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof TrieNode)) {
			return false;
		}
		
		TrieNode node = (TrieNode) o;
		
		return (this.value == node.value && 
				this.isLeaf == node.isLeaf && 
				this.children.equals(node.children)
				);
		
	}
	
	public String toString() {
		StringBuffer sb =  new StringBuffer();
		sb.append("[" + this.value + "; children: [");
		if (this.children != null && this.children.size() > 0) {
			for (char ch: this.children.keySet()) {
				sb.append(ch +", ");
			}
		}
		sb.append("]; isLeaf: " + this.isLeaf + "]");
		return sb.toString();
	}
}
