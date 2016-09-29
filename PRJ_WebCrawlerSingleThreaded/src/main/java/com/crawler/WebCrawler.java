package main.java.com.crawler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WebCrawler {

	private String startUrl;
	private Set<String> visited;
	private Queue<String> queue;
	private MessageDigest md ; 
	
	public WebCrawler(String startUrl) {
		this.startUrl = startUrl;
		this.visited = new HashSet<String>();
		this.queue = new LinkedList<String>();
		try {
			this.md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public void crawl() {
		
	}
	
	public String getMD5(String input) {
		this.md.reset();
		this.md.update(input.getBytes());
		byte [] bytes = this.md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b: bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
}
