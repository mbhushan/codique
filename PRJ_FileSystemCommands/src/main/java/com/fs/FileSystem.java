package main.java.com.fs;

import java.util.Stack;

public class FileSystem {
	
	private FileNode root;
	private FileNode pwd;
	
	public FileSystem() {
		this.root = new FileNode("/");
		this.root.isDirectory(true);
		this.pwd = this.root;
	}
	
	
	public void mkdir(String dirName) {
		FileNode dir = new FileNode(dirName);
		dir.isDirectory(true);
		
		dir.setParent(this.pwd);
		this.pwd.addFile(dirName, dir);
	}
	
	public void pwd() {
		Stack<String> stack = new Stack<String>();
		FileNode node = this.pwd;
		stack.push(node.getName());
		
		while(node.getParent() != null) {
			node = node.getParent();
			stack.push(node.getName());
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(stack.pop());
		while(!stack.isEmpty()) {
			sb.append("/");
			sb.append(stack.pop());
		}
		System.out.println(sb.toString());
	}
	
	public void ls() {
		if (this.pwd == null) {
			return;
		}
		StringBuffer sb = new StringBuffer();
		for (String name: this.pwd.getFiles().keySet()) {
			sb.append(this.pwd.getFiles().get(name) + "\n");
		}
		System.out.println(sb.toString());
	}
}
