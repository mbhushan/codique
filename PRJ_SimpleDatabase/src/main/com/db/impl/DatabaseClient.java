package main.com.db.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DatabaseClient {

	private static final String END = "END";
	
	public static void main(String[] args) {
		DatabaseClient client = new DatabaseClient();
		
		client.read();
		
	}
	
	public void read() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean run = true;
		while(run) {
			try {
				String cmd = br.readLine().trim();
				if (cmd.equalsIgnoreCase(END)) {
					System.out.println("BYE :)");
					run = false;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
}
