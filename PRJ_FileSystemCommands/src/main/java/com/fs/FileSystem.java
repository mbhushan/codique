package main.java.com.fs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class FileSystem {
	
	private FileNode root;
	private FileNode pwd;
	private static final String EOF = "EOF";
	
	public FileSystem() {
		this.root = new FileNode("/");
		this.root.setDirectory(true);
		this.pwd = this.root;
	}
	
	public void cat(String [] args) {
		StringBuffer buff = new StringBuffer();
		
		for (int i=0; i<args.length; i++) {
			String name = args[i];
			if (this.pwd.getFiles().containsKey(name)) {
				FileNode file = this.pwd.getFiles().get(name);
				if (!file.isDirectory()) {
					buff.append(file.getContent() + "\n");
				}
			}
		}
		System.out.println(buff.toString());
	}
	
	public void mv(String first, String second) {
		if (this.pwd.getFiles().containsKey(second) && !this.pwd.getFiles().get(second).isDirectory()) {
			System.out.println("already text file");
			return; //there is already a text file with second arg, do nothing and return.
		}

		//moves the first argument into the directory; 
		FileNode secondFile = this.pwd.getFiles().get(second);
		FileNode firstFile = this.pwd.getFiles().get(first);
		
		if (secondFile != null && secondFile.isDirectory()) {
			if (secondFile.getFiles().containsKey(first)) {
				return; //the directory must not already contain a file whose name is the first argument.
			}
			firstFile.setParent(secondFile);
			secondFile.getFiles().put(first, firstFile);
			this.pwd.getFiles().remove(first);
		} else {
			firstFile.setName(second);
			this.pwd.getFiles().put(second, firstFile);
			this.pwd.getFiles().remove(first);
		}
	}
	
	public void rm(String arg) {
		if (this.pwd.getFiles().containsKey(arg)) {
			if (!this.pwd.getFiles().get(arg).isDirectory()) {
				this.pwd.getFiles().remove(arg);
			} else {
				System.out.println(arg + " is directory, use rmdir cmd.");
			}
		} else {
			System.out.println("no such file!");
		}
	}
	
	public void rmdir(String arg) {
		if (this.pwd.getFiles().containsKey(arg)) {
			if (this.pwd.getFiles().get(arg).isDirectory()) {
				if (this.pwd.getFiles().get(arg).getFiles().isEmpty()) {
					this.pwd.getFiles().remove(arg);
				} else {
					System.out.println("directory is not empty!");
				}
			} else {
				System.out.println(arg + " is file, use rm cmd.");
			}
		} else {
			System.out.println("no such directory!");
		}
	}
	
	
	public void cd(String arg) {
		if (this.pwd.getFiles().containsKey(arg)) {
			this.pwd = this.pwd.getFiles().get(arg);
		} else if (arg.equals("..")) {
			this.pwd = this.pwd.getParent();
		} else if (arg.equals("/")) {
			this.pwd = this.root;
		} 
		return;
	}
	
	public void mkdir(String dirName) {
		FileNode dir = new FileNode(dirName);
		dir.setDirectory(true);
		
		dir.setParent(this.pwd);
		this.pwd.addFile(dirName, dir);
	}
	
	public void create(String fileName) {
		FileNode file = new FileNode(fileName);
		
		file.setParent(this.pwd);
		this.pwd.addFile(fileName, file);
		
		writeFileContent(file);
	}
	
	private void writeFileContent(FileNode file) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		StringBuffer sb = new StringBuffer();
		try {
			while (true) {
				line = br.readLine();
				if (line.equalsIgnoreCase(EOF)) {
					break;
				}
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		file.setContent(sb);
	}
	
	public void pwd() {
		StringBuffer path = new StringBuffer();
		if (this.pwd.equals(this.root)) {
			path.append("/");
		} else {
			pwd(this.pwd, path);
			path.deleteCharAt(path.length()-1);
		}
		System.out.println(path);
	}
	
	private void pwd(FileNode node, StringBuffer path) {
		if (!node.equals(node.getParent())) {
			pwd(node.getParent(), path);
		}
		if (node.getName().equals("/")) {
			path.append(node.getName());
		} else {
			path.append(node.getName() + "/");
		}
		//System.out.print(node.getName());
	}
	
	public void pwd1() {
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
		List<FileNode> flist = new ArrayList<FileNode>();
		for (String name: this.pwd.getFiles().keySet()) {
			flist.add(this.pwd.getFiles().get(name));
		}
		Collections.sort(flist, new FileNodeComparator());
		for (FileNode node: flist) {
			System.out.println(node);
		}
	}
}

class FileNodeComparator implements Comparator<FileNode> {

	@Override
	public int compare(FileNode o1, FileNode o2) {
		return o1.getName().compareToIgnoreCase(o2.getName());
	}
	
}
