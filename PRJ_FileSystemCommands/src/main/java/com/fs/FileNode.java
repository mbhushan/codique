package main.java.com.fs;

import java.util.HashMap;
import java.util.Map;

public class FileNode {
	
	private String name;
	private StringBuffer content;
	private boolean isDirectory;
	
	private FileNode parent; //parent directory
	
	private Map<String, FileNode> files; //list of files in the current directory.
	
	public FileNode(String name) {
		this.name = name;
		this.content = null;
		this.parent = this;
		this.files = new HashMap<String, FileNode>();
		this.isDirectory = false;
	}
	
	public FileNode getParent() {
		return this.parent;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setContent(StringBuffer sb) {
		this.content = sb;
	}
	
	public void setParent(FileNode node) {
		this.parent = node;
	}
	
	public boolean isDirectory() {
		return this.isDirectory;
	}
	
	public void setDirectory(boolean flag) {
		this.isDirectory = flag;
	}
	
	public void addFile(String name, FileNode node) {
		this.files.put(name, node);
	}
	
	public  Map<String, FileNode> getFiles() {
		return this.files;
	}
	
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("[");
		sb.append("file: " + this.name + "; dir: " + this.isDirectory);
		if (this.files != null && this.files.size() > 0) {
			sb.append("; files: ");
			for (String name: this.files.keySet()) {
				sb.append(name + ", ");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}
	
}
