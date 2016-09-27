package main.java.com.map.client;

import java.util.Arrays;

import main.java.com.map.XHashMap;

public class XHashMapClient {

	private XHashMap<String, String> xmap;
	
	public XHashMapClient() {
		this.xmap = new XHashMap<String, String>();
	}
	
	public static void main(String[] args) {
		XHashMapClient client = new XHashMapClient();
		String [] S = {"apple","boy","cat","drama","elephant","frog","graph","high",
				"icecream","joy","karma","lamp","money","noise","ostrich","people"};
		
		client.initMap(S);
		
		client.test(S.length);
	}
	
	public void test(int len) {
		System.out.println("len: " + len);
		for (int i=0; i<len; i++) {
			String key = this.xmap.getRandomKey();
			String value = this.xmap.get(key);
			System.out.println("[" + key + ", " + value + "]");
			this.xmap.delete(key);
			System.out.println(this.xmap.toString());
			System.out.println();
		}
	}
	
	public void initMap(String [] S) {
		for (int i=0; i<S.length; i++) {
			char [] data = S[i].toCharArray();
			Arrays.sort(data);
			String sorted = new String(data);
			this.xmap.insert(S[i], sorted);
		}
	}
}
