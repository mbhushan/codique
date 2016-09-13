package main.com.db.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DatabaseClient {
	
	private Command command;

	private static final String END = "END";
	
	public DatabaseClient() {
		command = new Command();
	}
	
	public static void main(String[] args) {
		DatabaseClient client = new DatabaseClient();
		
		client.read();
		
	}
	
	public void read() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			try {
				String cmd = br.readLine().trim();
				if (cmd.equalsIgnoreCase(END)) {
					System.out.println("BYE :)");
					break;
				}
				command.execute(cmd);
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
}
